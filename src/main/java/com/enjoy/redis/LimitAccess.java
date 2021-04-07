package com.enjoy.redis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class LimitAccess {

	private Jediser jediser = new Jediser();
	private int times = 5;
	private int interval = 6;

	// java 代码实现redis,限制客户端IP，6秒内超过5次请求。
	public void access(String ip) {
		String key = ip;
		if (jediser.exists(key)) {
			// existed
			long result = jediser.hincrBy(key, "count", Long.valueOf(1));
			if (result > times) {
				System.out.println("["+ip + "] are limited!!!"+"[count:"+result+"]");
			}else{
				System.out.println(key+"访问成功[count:"+result+"]");
			}
		} else {
			jediser.hset(key, "count", "1");
			jediser.expire(key, interval);
			System.out.println(key+"初次访问成功");
		}
	}
	
	// lua脚本解决多线程并发控制实现客户端IP访问限制
	public void luaAccess(String ip){
		File file = new File(LimitAccess.class.getClassLoader().getResource("").getPath());
		String rootDir = file.getParentFile().getParent();
		String luaFilePath=file.getAbsoluteFile()+"\\ipCount.lua";
		String script=readScript(luaFilePath);
		Object result =jediser.evalsha(script, Arrays.asList(ip), Arrays.asList(String.valueOf(this.times),String.valueOf(this.interval)));
		String msg=Thread.currentThread().getName();
		msg+=((Long)result==1)?"访问成功":"失败";
		System.out.println(msg);
	}

	public static void main(String[] args) throws InterruptedException {
		
//		LimitAccess limitAccess =new LimitAccess();
//		for (int i = 0; i < 10; i++) {
//			limitAccess.luaAccess("localhost");	
//		}

		luaMultiThread();
	
		//NoMultiThread();
		
		//oneStepOne();
		


	}
	
	private static String readScript(String filePath){
		StringBuilder sb=new StringBuilder();
	
		try {
			File file=new File(filePath);
			Long fileLength=file.length();
			
			byte[] fileContent=new byte[fileLength.intValue()];
			
			FileInputStream fileInputStream=new FileInputStream(file);
			fileInputStream.read(fileContent);
			fileInputStream.close();
			return new String(fileContent,"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static void luaMultiThread() {
		LimitAccess limitAccess =new LimitAccess();
		try {
			CountDownLatch latch=new CountDownLatch(1);
			for (int i = 0; i <10; i++) {
				new Thread(new Runnable() {
					public void run() {
						try {
							latch.await();
							limitAccess.luaAccess("localhost");	
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				},"Thread["+i+"]").start();
			}
			System.out.println("trigger 3s later...");
			Thread.sleep(3000);
			latch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void oneStepOne() throws InterruptedException {
		
		//依次10次访问
		LimitAccess limitAccess =new LimitAccess();
		for (int i = 0; i < 10; i++) {
			limitAccess.access("localhost");	
		}
		System.out.println("waiting 6s...");
		Thread.sleep(6000);
		limitAccess.access("localhost");	
		limitAccess.access("localhost");
	}

	// 模拟10个线程同时访问
	private static void NoMultiThread() {
		LimitAccess limitAccess =new LimitAccess();
		try {
			CountDownLatch latch=new CountDownLatch(1);
			for (int i = 0; i <10; i++) {
				new Thread(new Runnable() {
					public void run() {
						try {
							latch.await();
							limitAccess.access("localhost");	
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			System.out.println("trigger 3s later...");
			Thread.sleep(3000);
			latch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
