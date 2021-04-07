package com.enjoy.redis;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class Order {

	private static final String HOST = "192.168.1.111";
	// private static final String HOST = "127.0.0.1";
	private static final int PORT = 6379;
	private static final String PASSWORD = "p@ssw0rd";

	public static void main(String[] args) {

		Jedis jedis = new Jedis(HOST, 6379);
		jedis.auth(PASSWORD);

		// delAllKeys(jedis);

		// createOrderSample(jedis);

		// System.out.println(jedis.ping());
		// Set<Tuple> set= jedis.zrangeWithScores("user:zan", 0, -1);
		// Iterator<Tuple> iterator= set.iterator();
		// while (iterator.hasNext()) {
		// Tuple item = (Tuple) iterator.next();
		// System.out.println(item.getScore()+":"+item.getElement());
		// }
	}

	private static void createOrderSample(Jedis jedis) {
		OrderModel[] orderArray = new OrderModel[3];
		orderArray[0] = new OrderModel("1", "36.6");
		orderArray[1] = new OrderModel("2", "38.6");
		orderArray[2] = new OrderModel("3", "39.6");

		// 1. 创建3个订单
		for (OrderModel item : orderArray) {
			jedis.hmset("order:" + item.getOrderId(), item.toHashMap());
		}

		// 2. 订单id存在指定user:1:order用户队列中。
		for (OrderModel item : orderArray) {
			jedis.lpush("user:1:order", "order:" + item.getOrderId());
		}

		// 3. 创建第4个订单
		OrderModel order4 = new OrderModel("4", "40.6");
		jedis.hmset("order:" + order4.getOrderId(), order4.toHashMap());
		// 4. 追加订单4到用户队列中
		jedis.lpush("user:1:order", "order:" + order4.getOrderId());

		// 5. 查询用户的订单记录
		List<String> list = jedis.lrange("user:1:order", 0, -1);
		System.out.println("********user:1:order********");
		for (String orderKey : list) {
			String orderId = jedis.hget(orderKey, "orderId");
			String money = jedis.hget(orderKey, "money");
			String time = jedis.hget(orderKey, "time");
			System.out.println("orderId:" + orderId + " | money:" + money + " | time:" + time);
		}
	}

	private static void delAllKeys(Jedis jedis) {
		// 删除所有key
		Set<String> keySets = jedis.keys("*");
		for (String k : keySets) {
			jedis.del(k);
		}
	}
}
