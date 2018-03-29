package pers.husen.highdsa.web.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.utility.ConvertType;
import pers.husen.highdsa.service.redis.RedisOperation;
import pers.husen.highdsa.service.redis.RedisPools;

/**
 * @Desc shiro使用redis缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 上午12:07:58
 * 
 * @Version 1.0.0
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {
	private static final Logger logger = LogManager.getLogger(ShiroRedisCache.class.getName());

	private static RedisOperation redisOperation;
	private static RedisPools redisPools;

	public static void setRedisOperation(RedisOperation redisOperation) {
		ShiroRedisCache.redisOperation = redisOperation;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	private String keyPrefix = "shiro_redis_session:";

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Object key) {
		if (key instanceof String) {
			String preKey = this.keyPrefix + key;
			return preKey.getBytes();
		} else {
			return ConvertType.serialize(key);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(Object key) throws CacheException {
		Object object = null;
		try {
			object = redisOperation.getObject(key);
		} catch (Exception e) {
			logger.fatal(StackTrace2Str.exceptionStackTrace2Str("出错", e));
		}

		return (V) object;
	}

	/**
	 * 将shiro的缓存保存到redis中
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) throws CacheException {
		redisOperation.setObject(key, value, 0);

		return get(key);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object remove(Object key) throws CacheException {
		return redisOperation.removeObject(key);
	}

	/**
	 * 清空所有缓存
	 */
	@Override
	public void clear() throws CacheException {
		redisOperation.flushDB();
	}

	/**
	 * 缓存的个数
	 */
	@Override
	public int size() {
		return redisOperation.getDbSize();
	}

	/**
	 * 获取所有的key
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		Set<byte[]> keys = redisPools.getJedis().keys(new String("*").getBytes());
		Set<K> set = new HashSet<K>();
		for (byte[] bs : keys) {
			set.add((K) ConvertType.unserialize(bs));
		}
		return set;
	}

	/**
	 * 获取所有的value
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection values() {
		Set<?> keys = this.keys();

		List<Object> values = new ArrayList<Object>();
		for (Object key : keys) {
			byte[] bytes = redisPools.getJedis().get(getByteKey(key));
			values.add(ConvertType.unserialize(bytes));
		}
		return values;
	}
}