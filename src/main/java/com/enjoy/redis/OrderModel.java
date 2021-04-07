package com.enjoy.redis;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class OrderModel {
	private final String orderId;
	private final String money;
	private final String time;
	
	public OrderModel(String orderId, String money) {
		this.orderId = orderId;
		this.money = money;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss",Locale.CHINA);
		this.time = format.format(new Date());
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	public HashMap<String, String> toHashMap(){
		HashMap<String, String> map=new HashMap<>();
		Field[] fields=	this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				String propertyName= field.getName();
				String propertyValue = (String)field.get(this);
				map.put(propertyName, propertyValue);
				//System.out.println("propertyName: "+propertyName+"-propertyValue: "+propertyValue);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		OrderModel orderModel=new OrderModel("1", "45.3");
		
		HashMap<String, String> map=orderModel.toHashMap();
		
		System.out.println(map);
	}
}
