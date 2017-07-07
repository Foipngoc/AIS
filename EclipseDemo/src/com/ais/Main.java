package com.ais;

import java.text.SimpleDateFormat;
import java.util.List;
import com.nci.data.DataDriver;  

public class Main 
{
	public static SimpleDateFormat sdDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	public static void main(String[] args) 
	{
		DataDriver driver=DataDriver.getInstance();
		//RDataDriver driver=new RDataDriver();
        driver.setHost("10.100.70.101");
        driver.setPort(8090);
        driver.setUser("csp");
        driver.setPwd("password");
        try {
        	driver.connect();
			//List list=driver.getShipAisTrack("413853389", sdDateFormat.parse("2017-01-12 14:00"), sdDateFormat.parse("2017-01-14 06:00"));
			//
			double[][] envelope = new double[5][2];
			//第一个点
			envelope[0][0] = 120;//119.27;
			envelope[0][1] = 30.31;//30.27925745871254;
			
			//第二个点
			envelope[1][0] = 121;//121.27;
			envelope[1][1] = 30.31;//30.27925745871254;
			
			//第三个点
			envelope[2][0] = 121;//121.27;
			envelope[2][1] =29.12;// 29.27925745871254;
			
			//第四个点
			envelope[3][0] = 120;//119.27;
			envelope[3][1] = 29.12;//29.27925745871254;
			
			//第五个点
			envelope[4][0] = 120;//119.27;
			envelope[4][1] = 30.31;//30.27925745871254;
			//List list=driver.getShips(envelope);
			//List list=driver.getShips(119, 121,28, 31);
			//List list=driver.getAis2cbmc("147147147");
			//List list=driver.getAissbh("浙湖州货0067");
			List list=driver.getShipAisTrack("413857444", sdDateFormat.parse("2017-03-25 14:00"), sdDateFormat.parse("2017-03-26 06:00"));
			//int n=driver.getShipCountOnRiver(list);
			
		
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
