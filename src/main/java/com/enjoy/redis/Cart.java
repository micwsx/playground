package com.enjoy.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

public class Cart {

	private static final String HOST = "192.168.1.111";
	private static final int PORT = 6379;
	private static final String PASSWORD = "p@ssw0rd";
	private static Jedis jedis = new Jedis(HOST, PORT);

	private static void delAllKeys(Jedis jedis){
		// 删除所有key
		Set<String> keySets=jedis.keys("*");
		for (String k : keySets) {
			jedis.del(k);
		}
	}
	
	
	static {
		jedis.auth(PASSWORD);
	}

	public void addProduct(Product prod) {
		Map<String, Double> scoreMembers = new HashMap<>();
		scoreMembers.put("prod:" + prod.getId(), Double.valueOf(prod.getCount()));

		long is_exist = jedis.zadd("cart", scoreMembers, ZAddParams.zAddParams().nx());
		if (is_exist == 0) {
			editProductCount(prod);
		}
	}

	public void editProductCount(Product prod) {
		jedis.zincrby("cart", Double.valueOf(prod.getCount()), "prod:" + prod.getId(),
				ZIncrByParams.zIncrByParams().xx());
	}

	public void delProductFromCart(String id) {
		jedis.zrem("cart", "prod:" + id);
	}

	public void listProudct() {
		Set<Tuple> tuples = jedis.zrangeWithScores("cart", 0, -1);
		double totalCount=0;
		System.out.println("********************");
		for (Tuple tuple : tuples) {
			System.out.println(tuple.getElement()+":"+tuple.getScore());
			totalCount+=tuple.getScore();
		}
		System.out.println("---------------------------");
		System.out.println("Total Count:" +totalCount);
	}

	public static void main(String[] args) {

		Product prod1 = new Product("0001", "1");
		Product prod2 = new Product("0002", "1");
		
		// 加入商品
		Cart cart = new Cart();
		cart.addProduct(prod1);
		cart.addProduct(prod2);
		// 添加商品
		cart.addProduct(new Product("0001", "2"));
		
		cart.listProudct();
		
		// 删除商品
		cart.delProductFromCart("0002");
		
		System.out.println("-------------------after del-0002");
		cart.listProudct();
		

	}

}

class Product {

	private String id;
	private String count;

	public Product(String id, String count) {
		this.id = id;
		this.count = count;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

}
