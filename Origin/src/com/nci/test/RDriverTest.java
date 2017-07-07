package com.nci.test;

import com.nci.data.IDataListener;
import com.nci.data.RDataDriver;
import com.nci.exception.DataException;

public class RDriverTest {
	
	public static void main(String[] args){
		RDataDriver driver = null;
		try{
			IDataListener listener = new DataListenerImpl();
//			driver = RDataDriver.getInstance();
			driver = new RDataDriver();
			driver.setHost("10.100.70.101");
			driver.setPort(8090);
			driver.setUser("csp");
			driver.setPwd("password");
			driver.addEventListener(listener);
			double[][] envelope = new double[5][2];
			//第一个点
			envelope[0][0] = 120.25;
			envelope[0][1] = 30.279257;
			
			//第二个点
			envelope[1][0] = 120.75;
			envelope[1][1] = 30.279257;
			
			//第三个点
			envelope[2][0] = 120.75;
			envelope[2][1] = 29.979257;
			
			//第四个点
			envelope[3][0] = 120.75;
			envelope[3][1] = 29.979257;
			
			//第五个点
			envelope[4][0] = 120.25;
			envelope[4][1] = 30.279257;
			driver.setEnvelope(envelope);//形成的点必须闭合，否则会出错
			driver.connect();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(driver!=null){
				driver.close();
			}
		}
	}

}
