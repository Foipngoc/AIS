package net.service;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import net.tools.Convertor;
import net.tools.DateTimeUtil;
import net.tools.Point;
import net.tools.PropertyLoader;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

@Service("topicconsumerservice")
public class TopicConsumer  implements MessageListener
{
	private java.sql.Connection con = null;
	private static TopicConsumer instance = null;
	//final String url="jdbc:mysql://localhost:3306/lsais?user=root&password=111111&useUnicode=true&characterEncoding=utf-8";

	public TopicConsumer() {
		if (instance != null)
			throw new RuntimeException();
		instance = this;
	}

	public TopicConsumer getInstance() {
		return instance;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage txtMsg = (TextMessage) message;

			double startlongitude = 119.41;
			double endlongitude = 123.26;
			double startlatitude = 30.9;
			double endlatitude = 27.25;

			/*double startlongitude = 118.41;
			double endlongitude = 120.26;
			double startlatitude = 27.25;
			double endlatitude = 28.57;*/

			try {
				String shipname = "";// 船舶名称
				double longitude = 0;// 经度
				double latitude = 0;// 纬度
				double speed = 0;// 速度
				double cruisedirection = 0;// 航向
				double shipdirection = 0;// 航向
				String shipdate = null;// 事件
				String ais = "";
				String gps = "";
				int messagetype = 0;// 消息类型
				Date adddate = new Date();
				int shiptype = 0;
				String content = txtMsg.getText();
				//System.out.println("感知数据是：\n" + content);
				String[] arrcontent = content.split("\n");
				con.setAutoCommit(false);
				Statement statement = con.createStatement();
				for (int i = 0; i < arrcontent.length; i++) {
					String[] shipinfo = arrcontent[i].split(",");
					if (!shipinfo[0].trim().equals("")
							&& !shipinfo[0].trim().equals("null")) {
						shipname = shipinfo[0].trim();
					} else {
						continue;
					}
					if (!shipinfo[1].equals("") && !shipinfo[1].equals("null"))
						longitude = Double.parseDouble(shipinfo[1]);
					if (!shipinfo[2].equals("") && !shipinfo[2].equals("null"))
						latitude = Double.parseDouble(shipinfo[2]);
					if (!shipinfo[3].equals("") && !shipinfo[3].equals("null"))
						speed = Double.parseDouble(shipinfo[3]);
					if (!shipinfo[4].equals("") && !shipinfo[4].equals("null"))
						cruisedirection = Double.parseDouble(shipinfo[4]);
					if (!shipinfo[5].equals("") && !shipinfo[5].equals("null"))
						shipdirection = Double.parseDouble(shipinfo[5]);
					if (!shipinfo[6].equals("") && !shipinfo[6].equals("null"))
						shipdate = DateTimeUtil.getTimeFmt(DateTimeUtil
								.getDateByStringFmt(shipinfo[6]));
					if (!shipinfo[7].equals("") && !shipinfo[7].equals("null")) {
						ais = shipinfo[7];
					} else {
						ais = "";
					}
					if (!shipinfo[8].equals("") && !shipinfo[8].equals("null")) {
						gps = shipinfo[8];
					} else {
						gps = "";
					}
					if (!shipinfo[9].equals("") && !shipinfo[9].equals("null")) {
						messagetype = Integer.parseInt(shipinfo[9]);
					}
					if (!shipinfo[10].equals("")
							&& !shipinfo[10].equals("null")) {
						shiptype = Integer.parseInt(shipinfo[10]);
					}
					if (longitude >= startlongitude
							&& longitude <= endlongitude
							&& latitude >= endlatitude
							&& latitude <= startlatitude && !"".equals(ais)&& messagetype == 2) {
						System.out.println(txtMsg);
						Point poit=Convertor.wgs_gcj_encrypts(latitude,longitude);
						String sql = "call aissave('" + shipname + "',"
								+ poit.getLng() + "," + poit.getLat() + "," + speed
								+ "," + cruisedirection + "," + shipdirection
								+ ",'" + shipdate + "','" + ais + "','" + gps
								+ "'," + messagetype + "," + shiptype + ")";

						statement.addBatch(sql);
					}
				}

				statement.executeBatch();
				con.commit();
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//@PostConstruct
	public void init() {
		try {
			Properties jdbcprop = PropertyLoader.getPropertiesFromClassPath(
					"jdbc.properties", "UTF-8");
			String driverClassName = jdbcprop .getProperty("jdbc.driver");
			String jdbcurl = jdbcprop.getProperty("jdbc.url");
			String uname = jdbcprop.getProperty("jdbc.username");
			String pwd = jdbcprop.getProperty("jdbc.password");
			Class.forName(driverClassName);
			con = DriverManager.getConnection(jdbcurl + "&user=" + uname
					+ "&password=" + pwd);
			//con = DriverManager.getConnection(url);
			receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void destroy() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void receive() {
		// 消费者的主要流程
		Connection connection = null;
		Properties properties = PropertyLoader.getPropertiesFromClassPath(
				"activemq.properties", "UTF-8");
		String topic = properties.getProperty("topic");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String ipaddress = properties.getProperty("ipaddress");
		String port = properties.getProperty("port");
		String brokerURL = "failover://tcp://" + ipaddress + ":" + port;
		try {
			// 1.初始化connection工厂
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					username, password, brokerURL);

			// 2.创建Connection
			connection = connectionFactory.createConnection();

			// 3.打开连接
			connection.start();

			System.out.println("连接成功..................");

			// 4.创建session
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// 5.创建消息目标
			Destination destination = session.createTopic(topic);

			// 6.创建消费者
			MessageConsumer consumer = session.createConsumer(destination);

			// 7.配置监听
			consumer.setMessageListener(getInstance());

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
