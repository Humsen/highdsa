package pers.husen.highdsa.service.redis;

import redis.clients.jedis.Jedis;

/**
 * @Desc 测试redis
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 上午9:39:08
 * 
 * @Version 1.0.0
 */
public class TestRedis {
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("39.106.185.126", 6379);
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		jedis.set("husen", "hemingsheng");
		System.out.println("husen：" + jedis.get("husen"));
		System.out.println("Server is running: " + jedis.ping());
		jedis.close();
	}
}
