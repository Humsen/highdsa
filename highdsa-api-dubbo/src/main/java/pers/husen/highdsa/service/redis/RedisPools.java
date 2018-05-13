package pers.husen.highdsa.service.redis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import redis.clients.jedis.Jedis;

/**
 * @Desc redis连接池操作接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月3日 下午5:29:58
 * 
 * @Version 1.0.0
 */
public interface RedisPools {
	/**
	 * 初始化redis 连接池
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void initRedis() throws UnsupportedEncodingException, IOException;

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public Jedis getJedis();

	/**
	 * 释放redis资源
	 * 
	 * @param jedis
	 */
	public void returnResource(final Jedis jedis);

	/**
	 * 关闭连接池
	 */
	public void closeRedisPool();
}