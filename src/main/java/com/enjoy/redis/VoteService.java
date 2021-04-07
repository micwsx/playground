package com.enjoy.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface VoteService {
	void publisArticle(Article article);

	void voteArticle(String userId, String articleId);

	void showDetail();
}

class Article {
	private String id;
	private String content;
	private String createdTime;
	private String userId;
	private String votes = "1";
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * @return the votes
	 */
	public String getVotes() {
		return votes;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	public Article(String id, String content, String userId) {
		this.id = id;
		this.content = content;
		this.createdTime = format.format(new Date());
		this.userId = userId;

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	public long getTime() {
		try {
			return format.parse(this.createdTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

}

class VoteServiceImpl implements VoteService {

	private Jediser jediser = new Jediser();

	@Override
	public void publisArticle(Article article) {

		// create an article
		Map<String, String> hash = new HashMap<>();
		hash.put("id", article.getId());
		hash.put("content", article.getContent());
		hash.put("createdTime", article.getCreatedTime());
		hash.put("userId", article.getUserId());
		hash.put("votes", article.getVotes());
		if ("OK".equals(jediser.hmset("article:" + article.getId(), hash))) {
			// record the user who has voted
			jediser.sadd("vote:" + article.getId(), "user:" + article.getUserId());
			jediser.expire("vote:" + article.getId(), 10);

			// add article into a zset
			jediser.zadd("score:info", article.getTime(), article.getId());
		}
	}

	@Override
	public void voteArticle(String userId, String articleId) {

		if (!jediser.exists("vote:" + articleId)) {
			System.out.println("vote:" + articleId + " has been expired!");
		}else{
			if (jediser.sismember("vote:" + articleId, "user:" + userId)) {
				System.out.println("user:" + userId + " has voted in article:" + articleId);
			} else {
				System.out.println(userId+" votes "+articleId);
				jediser.hincrby("article:" + articleId, "votes", Long.valueOf(1));
				jediser.sadd("vote:" + articleId, "user:" + userId);
			}
		}
	}

	@Override
	public void showDetail() {

		Set<String> members = jediser.zrevrange("score:info", 0, -1);
		for (String articleId : members) {
			Map<String, String> map = jediser.hgetall("article:" + articleId);
			String item = "id=" + map.get("id") + "|content=" + map.get("content") + "|createdTime="
					+ map.get("createdTime") + "|userId=" + map.get("userId") + "|votes:" + map.get("votes");
			System.out.println(item);
			System.out.println("----------------");
		}

	}

}