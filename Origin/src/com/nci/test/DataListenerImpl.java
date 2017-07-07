package com.nci.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nci.data.IDataListener;

public class DataListenerImpl implements IDataListener {

	@Override
	public void onMessage(List list) {
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Map map = (HashMap)iter.next();
			System.out.println("消息内容："+map);
		}
	}

	@Override
	public void onError(String str) {
		System.out.println("错误消息内容："+str);		
	}

}
