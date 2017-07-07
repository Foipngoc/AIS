package com.nci.test;

import java.util.List;
import java.util.Map;

import com.nci.data.DataDriver;
import com.nci.exception.DataException;

public class DriverTest {
	
	public static void main(String[] args){
		DataDriver driver = null;
		try{
			driver = DataDriver.getInstance();
			driver.setHost("10.100.70.101");
			driver.setPort(8090);
			driver.setUser("csp");
			driver.setPwd("password");
			driver.connect();
			String str = "浙湖州货3081";
			List<Map> list = driver.getAissbh(str);//根据船舶名称查询ais编号
			if(list!=null&&list.size()>0){
				System.out.println("cbmc:"+str+"  对应的ais9位码是："+list.get(0).get("aissbh"));
				List<Map> list1 = driver.getAis2cbmc((String)list.get(0).get("aissbh"));//根据ais9位码查询船舶名称
				if(list1!=null&&list1.size()>0){
					System.out.println("ais:"+(String)list.get(0).get("aissbh")+"  对应的船舶名称是："+list1.get(0).get("cbmc"));
				}
			}
			List<Map> list1 = driver.getAis2cbmc("413760191");
			if(list1!=null&&list1.size()>0){
				System.out.println("ais:"+"413760191"+"  对应的船舶名称是："+list1.get(0).get("cbmc"));
			}
			
			boolean result = driver.setAissbh("432678", "皖江顺229");//保存船舶的9位码信息
			System.out.println("设置aissbh编号信息成功");
			List<Map> list2 = driver.getAis2cbmc("432678");
			if(list2!=null&&list2.size()>0){
				System.out.println("cbmc:432678  对应的ais9位码是："+list2.get(0).get("cbmc"));
			}
		}catch(DataException e){
			e.printStackTrace();
		}finally{
			if(driver!=null){
				driver.close();
			}
		}
	}

}
