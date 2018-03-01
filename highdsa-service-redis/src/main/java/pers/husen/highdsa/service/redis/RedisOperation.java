package pers.husen.highdsa.service.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import pers.husen.highdsa.common.utility.TypeConvert;
import redis.clients.jedis.Jedis;

/**
 * @Desc redis操作
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午2:19:37
 * 
 * @Version 1.0.1
 */
public class RedisOperation extends RedisPools {
	private static final Logger logger = LogManager.getLogger(RedisOperation.class.getName());

	/**
	 * 设置String
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String set(String key, String value) {
		Jedis jedis = getJedis();
		String statusCodeReply = jedis.set(key, value);

		logger.info("redis cache set success, key={}, value={}", key, value);

		return statusCodeReply;
	}

	/**
	 * 设置String
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间,0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSeconds) {
		String statusCodeReply = null;
		Jedis jedis = getJedis();
		statusCodeReply = jedis.set(key, value);
		if (cacheSeconds != 0) {
			jedis.expire(key, cacheSeconds);
		}

		logger.info("redis cache set success, key={}, value={}, expire={}s", key, value, cacheSeconds);

		return statusCodeReply;
	}

	/**
	 * 获取String
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = getJedis();
		String value = jedis.get(key);

		logger.info("redis cache get success, key={}, value={}", key, value);

		return value;
	}

	/**
	 * 在key的值后面拼接字符串
	 * 
	 * @param key
	 * @param addString
	 * @return
	 */
	public static Long append(String key, String addString) {
		Jedis jedis = getJedis();
		Long statusCodeReply = jedis.append(key, addString);

		return statusCodeReply;

	}

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.exists(key);
			logger.debug("exists {}", key);
		} catch (Exception e) {
			logger.warn("exists {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置对象
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setObject(String key, Object value, int cacheSeconds) {
		String statusCodeReply = null;
		Jedis jedis = getJedis();
		try {
			statusCodeReply = jedis.set(key.getBytes(), TypeConvert.serialize(value));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.error("setObject {} = {}", key, value, e);
		}

		return statusCodeReply;
	}

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object getObject(String key) {
		Object value = null;
		Jedis jedis = getJedis();

		if (jedis.exists(key.getBytes())) {
			value = TypeConvert.unserialize(jedis.get(key.getBytes()));
			logger.info("redis cache get success, key={}, value={}", key, value);
		}

		return value;
	}

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean existsObject(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.exists(key.getBytes());
			logger.debug("existsObject {}", key);
		} catch (Exception e) {
			logger.warn("existsObject {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setList(String key, List<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = getJedis();
		try {
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.rpush(key, value.toArray(new String[0]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 * @return
	 */
	public static List<String> getList(String key) {
		List<String> value = null;
		Jedis jedis = getJedis();
		try {
			if (jedis.exists(key.getBytes())) {
				value = jedis.lrange(key, 0, -1);
			}

			logger.info("redis cache get success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("getList {} = {}", key, value, e);
		}

		return value;
	}

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static long appendList(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.rpush(key, value);
			logger.debug("listAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("listAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setObjectList(String key, List<Object> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = getJedis();
		try {
			if (jedis.exists(key.getBytes())) {
				jedis.del(key);
			}

			result = jedis.set(key.getBytes(), TypeConvert.serializeList(value));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 * @return 
	 */
	public static List<Object> getObjectList(String key) {
		List<Object> value = null;
		Jedis jedis = getJedis();
		try {
			if (jedis.exists(key.getBytes())) {
				byte[] list = jedis.get(key.getBytes());
				value = (List<Object>) TypeConvert.unserializeList(list);
				
				logger.info("redis cache get success, key={}, value={}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String appendObjectList(String key, Object... value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			
			byte[] listTemp = jedis.get(key.getBytes());
			List<Object> list = (List<Object>) TypeConvert.unserializeList(listTemp);
			for (Object o : value) {
				list.add(o);
			}
			result = jedis.set(key.getBytes(), TypeConvert.serializeList(list));
			
			logger.info("redis cache append success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("listObjectAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 一次设置多个 key 的值，成功返回 ok 表示所有的值都设置了，失败返回 0 表示没有任何值被设置 key1, value1, key2, value2
	 * 
	 * @param keys3Values
	 * @return
	 */
	public static String mset(String... keys3Values) {
		Jedis jedis = getJedis();
		String statusCodeReply = jedis.mset(keys3Values);

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
	 * 设置Set缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setSet(String key, Set<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.sadd(key, value.toArray(new String[0]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Set<String> getSet(String key) {
		Set<String> value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				value = jedis.smembers(key);
				logger.info("redis cache get success, key={}, value={}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 向Set缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static long appendSet(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.sadd(key, value);
			logger.info("redis cache append success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setSetAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置Set缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				jedis.del(key);
			}
			Set<byte[]> set = Sets.newHashSet();
			for (Object o : value) {
				set.add(TypeConvert.serialize(o));
			}
			result = jedis.sadd(key.getBytes(), (byte[][]) set.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Set<Object> getObjectSet(String key) {
		Set<Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				value = Sets.newHashSet();
				Set<byte[]> set = jedis.smembers(key.getBytes());
				for (byte[] bs : set) {
					value.add(TypeConvert.unserialize(bs));
				}
				logger.info("redis cache get success, key={}, value={}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 向Set缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static long appendObjectSet(String key, Object... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Set<byte[]> set = Sets.newHashSet();
			for (Object o : value) {
				set.add(TypeConvert.serialize(o));
			}
			result = jedis.rpush(key.getBytes(), (byte[][]) set.toArray());
			logger.debug("setSetObjectAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSetObjectAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置Map缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.hmset(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取Map缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				value = jedis.hgetAll(key);
				logger.info("redis cache get success, key={}, value={}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String appendMap(String key, Map<String, String> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.hmset(key, value);
			logger.debug("mapPut {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("mapPut {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 移除Map缓存中的值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static long removeMap(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.hdel(key, mapKey);
			logger.debug("mapRemove {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapRemove {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 判断Map缓存中的Key是否存在
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean existsMap(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.hexists(key, mapKey);
			logger.debug("mapExists {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapExists {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置Map缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				jedis.del(key);
			}
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put((e.getKey().getBytes()), TypeConvert.serialize(e.getValue()));
			}
			result = jedis.hmset(key.getBytes(), (Map<byte[], byte[]>) map);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.info("redis cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取Map缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Map<String, Object> getObjectMap(String key) {
		Map<String, Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				value = Maps.newHashMap();
				Map<byte[], byte[]> map = jedis.hgetAll(key.getBytes());
				for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
					value.put(TypeConvert.byteArray2String(e.getKey()), TypeConvert.unserialize(e.getValue()));
				}
				logger.info("redis cache get success, key={}, value={}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String appendObjectMap(String key, Map<String, Object> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put((e.getKey().getBytes()), TypeConvert.serialize(e.getValue()));
			}
			result = jedis.hmset(key.getBytes(), (Map<byte[], byte[]>) map);
			logger.debug("mapObjectPut {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("mapObjectPut {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 移除Map缓存中的值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static long removeObjectMap(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.hdel(key.getBytes(), mapKey.getBytes());
			logger.debug("mapObjectRemove {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectRemove {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 判断Map缓存中的Key是否存在
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean existsObjectMap(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.hexists(key.getBytes(), mapKey.getBytes());
			logger.debug("mapObjectExists {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectExists {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static long del(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key)) {
				result = jedis.del(key);
				logger.debug("del {}", key);
			} else {
				logger.debug("del {} not exists", key);
			}
		} catch (Exception e) {
			logger.warn("del {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 删除多个
	 * 
	 * @param key
	 * @return
	 */
	public static long del(String... key) {
		Jedis jedis = getJedis();
		Long statusCodeReply = jedis.del(key);

		return statusCodeReply;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static long delObject(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				result = jedis.del(key.getBytes());
				logger.debug("delObject {}", key);
			} else {
				logger.debug("delObject {} not exists", key);
			}
		} catch (Exception e) {
			logger.warn("delObject {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 刷新
	 */
	public static void flush() {
		Jedis jedis = getJedis();
		jedis.flushAll();
	}
}