package com.enjoy.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class VoteArticleTest {

	
	private static void delAllKeys(Jedis jedis){
		// 删除所有key
		Set<String> keySets=jedis.keys("*");
		for (String k : keySets) {
			jedis.del(k);
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
	
		VoteService voteService=new VoteServiceImpl();
		
		Article article1=new Article("A001", "Ni hao!", "U001");
		voteService.publisArticle(article1);
		
//		Article article2=new Article("A002", "Hello Wordl!", "U002");
//		voteService.publisArticle(article2);
		
		voteService.voteArticle("U001", "A001");
		voteService.voteArticle("U002", "A001");
		voteService.showDetail();
		
		System.out.println("waiting 10s...");
		Thread.sleep(10000);
		voteService.voteArticle("U003", "A001");
		
		voteService.showDetail();
		
	}
}
