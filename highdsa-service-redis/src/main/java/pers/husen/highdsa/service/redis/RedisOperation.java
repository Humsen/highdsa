package pers.husen.highdsa.service.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * @Desc redis操作
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午2:19:37
 * 
 * @Version 1.0.0
 */
public class RedisOperation extends RedisPools {
	/**
	 * 设置String
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String setString(String key, String value) {
		Jedis jedis = getJedis();
		String statusCodeReply = jedis.set(key, value);

		return statusCodeReply;
	}

	/**
	 * 获取String
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Jedis jedis = getJedis();
		String statusCodeReply = jedis.get(key);

		return statusCodeReply;
	}

	/**
	 * 在key的值后面拼接字符串
	 * 
	 * @param key
	 * @param addString
	 * @return
	 */
	public static Long appendString(String key, String addString) {
		Jedis jedis = getJedis();
		Long statusCodeReply = jedis.append(key, addString);

		return statusCodeReply;

	}

	/**
	 * 一次设置多个 key 的值，成功返回 ok 表示所有的值都设置了，失败返回 0 表示没有任何值被设置
	 * 
	 * @param keysvalues
	 * @return
	 */
	public static String mset(String... keysvalues) {
		Jedis jedis = getJedis();
		String statusCodeReply = jedis.mset(keysvalues);

		return statusCodeReply;
	}

	/**
	 * 返回所有(一个或多个)给定 key 的值
	 * 
	 * @param keys
	 * @return
	 */
	public static List<String> mget(String... keys) {
		Jedis jedis = getJedis();
		List<String> statusCodeReply = new ArrayList<String>();
		statusCodeReply = jedis.mget(keys);

		return statusCodeReply;
	}

	/**
	 * 刷新
	 */
	public static void flush() {
		Jedis jedis = getJedis();
		jedis.flushAll();
	}

	/**
	 * 删除一个
	 * 
	 * @param key
	 */
	public static long delete(String key) {
		Jedis jedis = getJedis();
		Long statusCodeReply = jedis.del(key);

		return statusCodeReply;
	}

	/**
	 * 删除多个
	 * 
	 * @param key
	 * @return
	 */
	public static long delete(String... key) {
		Jedis jedis = getJedis();
		Long statusCodeReply = jedis.del(key);

		return statusCodeReply;
	}
}