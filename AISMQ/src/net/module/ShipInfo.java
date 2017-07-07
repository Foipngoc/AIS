package net.module;

import java.util.Date;

public class ShipInfo {
	private String shipname;// 船舶名称
	private double longitude;// 经度
	private double latitude;// 纬度
	private double speed;// 速度
	private double cruisedirection;// 航向
	private double shipdirection;// 航向
	private Date shipdate;// 事件
	private String ais;
	private String gps;
	private int messagetype;// 消息类型
	private int shiptype;// 船舶类型
	private Date adddate;
	public static final int TYPE_GPS = 1;//gps
	public static final int TYPE_AIS = 2;//ais



	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getCruisedirection() {
		return cruisedirection;
	}

	public void setCruisedirection(double cruisedirection) {
		this.cruisedirection = cruisedirection;
	}

	public double getShipdirection() {
		return shipdirection;
	}

	public void setShipdirection(double shipdirection) {
		this.shipdirection = shipdirection;
	}

	public Date getShipdate() {
		return shipdate;
	}

	public void setShipdate(Date shipdate) {
		this.shipdate = shipdate;
	}

	public String getAis() {
		return ais;
	}

	public void setAis(String ais) {
		this.ais = ais;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public int getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(int messagetype) {
		this.messagetype = messagetype;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public int getShiptype() {
		return shiptype;
	}

	public void setShiptype(int shiptype) {
		this.shiptype = shiptype;
	}

	public ShipInfo() {
		super();
	}

	public ShipInfo(String shipname, double longitude, double latitude,
			double speed, double cruisedirection, double shipdirection,
			Date shipdate, String ais, String gps, int messagetype, Date adddate) {
		super();
		this.shipname = shipname;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speed = speed;
		this.cruisedirection = cruisedirection;
		this.shipdirection = shipdirection;
		this.shipdate = shipdate;
		this.ais = ais;
		this.gps = gps;
		this.messagetype = messagetype;
		this.adddate = adddate;
	}

	public ShipInfo(String shipname, double longitude, double latitude,
			double speed, double cruisedirection, double shipdirection,
			Date shipdate, String ais, String gps, int messagetype,
			int shiptype, Date adddate) {
		super();
		this.shipname = shipname;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speed = speed;
		this.cruisedirection = cruisedirection;
		this.shipdirection = shipdirection;
		this.shipdate = shipdate;
		this.ais = ais;
		this.gps = gps;
		this.messagetype = messagetype;
		this.shiptype = shiptype;
		this.adddate = adddate;
	}

	

}
