package pers.husen.highdsa.service.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;

import com.google.common.collect.Maps;

import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.transform.ConvertType;
import pers.husen.highdsa.common.transform.Serializer;
import redis.clients.jedis.Jedis;

/**
 * @Desc redis操作
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午2:19:37
 * 
 * @Version 1.0.3
 */
public class RedisOperationImpl extends RedisPoolsImpl implements RedisOperation {
	private static final Logger logger = LogManager.getLogger(RedisOperationImpl.class.getName());

	@Override
	public String set(String key, String value) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.set(key, value);

			// logger.info("redis <String> cache set success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public String set(String key, String value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.set(key, value);

			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <String> cache set success, key={}, value={}, expire={}s",
			// key, value, cacheSeconds);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public String get(String key) {
		String value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			value = jedis.get(key);

			// logger.info("redis <String> cache get success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Long append(String key, String addString) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.append(key, addString);

			// logger.info("redis <String> cache append success, key={}, value={}", key,
			// addString);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Boolean exists(String key) {
		Boolean reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.exists(key);

			// logger.info("redis <String> cache exists, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("判断redis缓存是否存在出错", e));
		}

		return reply;
	}

	@Override
	public Long del(String key) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			if (!jedis.exists(key)) {
				logger.warn("del key [{}] failed, caused by not exists", key);
			}

			reply = jedis.del(key);

			// logger.info("redis <String> cache delete success, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public Long del(String... key) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.del(key);

			// logger.info("redis <String[]> cache delete success, key={}",
			// TypeConvert.strArray2String(key));
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public String setObject(String key, Object value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			reply = jedis.set(key.getBytes(), Serializer.serialize(value));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			// logger.info("redis <String> cache set success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Object getObject(String key) {
		Object value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				value = Serializer.unserialize(jedis.get(key.getBytes()));

				// logger.info("redis <String> cache get success, key={}, value={}", key,
				// value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Boolean existsObject(String key) {
		Boolean reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.exists(key.getBytes());

			// logger.info("redis <String> cache exists, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("判断redis缓存是否存在出错", e));
		}

		return reply;
	}

	@Override
	public Long delObject(String key) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			byte[] keyBytes = key.getBytes();
			if (!jedis.exists(keyBytes)) {
				logger.warn("del key [{}] failed, caused by not exists", key);
			}

			reply = jedis.del(keyBytes);

			// logger.info("redis <Object> cache delete success, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public String setObject(Object key, Object value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();

			String serialKey = String.valueOf((Serializable) key);
			reply = jedis.set(serialKey.getBytes(), Serializer.serialize(value));

			if (cacheSeconds != 0) {
				jedis.expire(serialKey.getBytes(), cacheSeconds);
			}

			logger.debug("------redis <Object> cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;

	}

	@Override
	public Object getObject(Object key) {
		Object value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			String serialKey = String.valueOf((Serializable) key);
			if (jedis.exists(serialKey.getBytes())) {
				value = Serializer.unserialize(jedis.get(serialKey.getBytes()));

				logger.debug("----------redis <Object> cache get success, key={}, value={}", key, value);
				if (value instanceof Session) {
					System.out.println("session id 为：" + ((Session) value).getId());
				}
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Long removeObject(Object key) {
		Long value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			String serialKey = String.valueOf((Serializable) key);
			if (jedis.exists(serialKey.getBytes())) {
				value = jedis.expire(serialKey.getBytes(), 0);

				// logger.info("redis <Object> cache get success, key={}, value={}", key,
				// value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Long setList(String key, List<String> value, int cacheSeconds) {
		Long reply = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();

			if (jedis.exists(key)) {
				jedis.del(key);
			}
			reply = jedis.rpush(key, value.toArray(new String[0]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <List> cache set success, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public List<String> getList(String key) {
		List<String> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				value = jedis.lrange(key, 0, -1);

				// logger.info("redis <Object> cache get success, key={}, value={}", key,
				// value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Long appendList(String key, String... value) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.rpush(key, value);

			// logger.info("redis <List> cache append success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public String setObjectList(String key, List<Object> value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				jedis.del(key);
			}

			reply = jedis.set(key.getBytes(), Serializer.serializeList(value));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <ObjectList> cache set success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public List<Object> getObjectList(String key) {
		List<Object> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				byte[] list = jedis.get(key.getBytes());
				value = (List<Object>) Serializer.unserializeList(list);

				// logger.info("redis <ObjectList> cache get success, key={}, value={}", key,
				// value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public String appendObjectList(String key, Object... value) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			byte[] listTemp = jedis.get(key.getBytes());
			List<Object> list = (List<Object>) Serializer.unserializeList(listTemp);
			if (list == null) {
				list = new ArrayList<Object>();
			}

			for (Object o : value) {
				list.add(o);
			}
			reply = jedis.set(key.getBytes(), Serializer.serializeList(list));

			// logger.info("redis <ObjectList> cache append success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public String mset(String... keys3Values) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.mset(keys3Values);

			// logger.info("redis <mset> cache set success, key3values={}",
			// TypeConvert.strArray2String(keys3Values));
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public List<String> mget(String... keys) {
		List<String> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			value = jedis.mget(keys);

			// logger.info("redis <ObjectList> cache get success, keys={}, values={}",
			// TypeConvert.strArray2String(keys),result.toString());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Long setSet(String key, Set<String> value, int cacheSeconds) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key)) {
				jedis.del(key);
			}
			reply = jedis.sadd(key, value.toArray(new String[0]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <Set<String>> cache set success, key={}, value={}", key,
			// value.toString());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Set<String> getSet(String key) {
		Set<String> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key)) {
				value = jedis.smembers(key);

				// logger.info("redis <Set<String>> cache get success, key={}, value={}", key,
				// value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public Long appendSet(String key, String... value) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.sadd(key, value);

			// logger.info("redis <Set<String>> cache append success, key={}, value={}",
			// key,TypeConvert.strArray2String(value));
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public String setObjectSet(String key, Set<Object> value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			jedis = getJedis();
			if (jedis.exists(key.getBytes())) {
				jedis.del(key);
			}
			reply = jedis.set(key.getBytes(), Serializer.serializeSet(value));

			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <Set<Object>> cache set success, key={}, value={}", key,
			// value.toString());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Set<Object> getObjectSet(String key) {
		Set<Object> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				byte[] list = jedis.get(key.getBytes());
				value = (Set<Object>) Serializer.unserializeSet(list);

				// logger.info("redis <Set<Object>> cache get success, key={}, value={}", key,
				// value.toString());
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public String appendObjectSet(String key, Object... value) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			byte[] listTemp = jedis.get(key.getBytes());
			Set<Object> set = (Set<Object>) Serializer.unserializeSet(listTemp);

			if (set == null) {
				set = new HashSet<Object>();
			}
			for (Object o : value) {
				set.add(o);
			}
			reply = jedis.set(key.getBytes(), Serializer.serializeSet(set));

			// logger.info("redis <Set<Object>> cache append success, key={}, value={}",
			// key, value.toString());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key)) {
				jedis.del(key);
			}
			reply = jedis.hmset(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <Map> cache set success, key={}, value={}", key, value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key)) {
				value = jedis.hgetAll(key);

				// logger.info("redis <Map> cache get success, key={}, value={}", key, value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public String appendMap(String key, Map<String, String> value) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.hmset(key, value);

			// logger.info("redis <Map> cache append success, key={}, value={}", key,
			// value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Long removeMap(String key, String mapKey) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.hdel(key, mapKey);

			// logger.info("redis <Map> cache remove success, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public Long removeMap(String key, String... mapKeys) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.hdel(key, mapKeys);

			// logger.info("redis <Map> cache remove success, key={}", key);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public Boolean existsMap(String key, String mapKey) {
		Boolean reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.hexists(key, mapKey);

			// logger.info("redis <Map> cache exists, key={}, mapkey={}", key, mapKey);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("判断redis缓存是否存在出错", e));
		}

		return reply;
	}

	@Override
	public String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				jedis.del(key);
			}
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put((e.getKey().getBytes()), Serializer.serialize(e.getValue()));
			}
			reply = jedis.hmset(key.getBytes(), (Map<byte[], byte[]>) map);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}

			// logger.info("redis <Map<String, Object>> cache set success, key={},
			// value={}", key, value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("设置redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Map<String, Object> getObjectMap(String key) {
		Map<String, Object> value = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			if (jedis.exists(key.getBytes())) {
				value = Maps.newHashMap();
				Map<byte[], byte[]> map = jedis.hgetAll(key.getBytes());
				for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
					value.put(ConvertType.byteArray2String(e.getKey()), Serializer.unserialize(e.getValue()));
				}

				// logger.info("redis <Map<String, Object>> cache get success, key={},
				// value={}", key, value);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return value;
	}

	@Override
	public String appendObjectMap(String key, Map<String, Object> value) {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put((e.getKey().getBytes()), Serializer.serialize(e.getValue()));
			}
			reply = jedis.hmset(key.getBytes(), (Map<byte[], byte[]>) map);

			// logger.info("redis <Map<String, Object>> cache append success, key={},
			// value={}", key, value);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("添加redis缓存出错", e));
		} finally {
			returnResource(jedis);
		}

		return reply;
	}

	@Override
	public Long removeObjectMap(String key, String mapKey) {
		Long reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.hdel(key.getBytes(), mapKey.getBytes());

			// logger.info("redis <Map<String, Object>> cache remove success, key={},
			// mapKey={}", key, mapKey);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public Boolean existsObjectMap(String key, String mapKey) {
		Boolean reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();
			reply = jedis.hexists(key.getBytes(), mapKey.getBytes());

			// logger.info("redis <Map<String, Object>> cache exists, key={}, mapKey={}",
			// key, mapKey);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("判断redis缓存是否存在出错", e));
		}

		return reply;
	}

	@Override
	public String flushAll() {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.flushAll();

			// logger.info("redis all cache delete success!");
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public String flushDB() {
		String reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = jedis.flushDB();

			// logger.info("redis current db cache delete success!");
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("删除redis缓存出错", e));
		}

		return reply;
	}

	@Override
	public Integer getDbSize() {
		Integer reply = null;
		Jedis jedis = null;

		try {
			jedis = getJedis();

			reply = new Integer(jedis.dbSize().toString());

			// logger.info("redis current db cache delete success!");
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis数据库数量出错", e));
		}

		return reply;
	}

	@Override
	public Set<byte[]> keys(String pattern) {
		Set<byte[]> keys = null;
		Jedis jedis;

		try {
			jedis = getJedis();
			keys = jedis.keys(pattern.getBytes());

			// logger.info("redis current db cache delete success!");
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis数据库key数量出错", e));
		}

		return keys;

		// Set<byte[]> keys = getJedis().keys(Serializer.str2ByteArray(pattern));
	}

	/**
	 * 获取所有的value
	 */
	@Override
	public List<Object> values(String pattern) {
		List<Object> values = new ArrayList<Object>();
		Set<byte[]> keys = this.keys(pattern);
		Jedis jedis;

		try {
			jedis = getJedis();
			for (byte[] object : keys) {
				byte[] bs = jedis.get(Serializer.serialize((Serializable) object));
				values.add(Serializer.unserialize(bs));
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("获取redis数据库所有值", e));
		}

		return values;
	}

	/* ---------------- shiro 新增 ------------------------- */

	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = getJedis();
		value = jedis.get(key);

		return value;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		jedis.set(key, value);

		return value;
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@Override
	public byte[] set(byte[] key, byte[] value, int expire) {
		Jedis jedis = getJedis();
		jedis.set(key, value);
		if (expire != 0) {
			jedis.expire(key, expire);
		}

		return value;
	}

	/**
	 * del
	 * 
	 * @param key
	 */
	@Override
	public void del(byte[] key) {
		Jedis jedis = getJedis();
		jedis.del(key);
	}

	/**
	 * size
	 */
	@Override
	public Long dbSize() {
		Long dbSize = 0L;
		Jedis jedis = getJedis();
		dbSize = jedis.dbSize();

		return dbSize;
	}
}