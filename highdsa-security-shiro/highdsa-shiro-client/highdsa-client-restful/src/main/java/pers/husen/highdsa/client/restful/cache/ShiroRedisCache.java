package pers.husen.highdsa.client.restful.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.exception.ParamsException;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.transform.Serializer;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc shiro使用redis缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 上午12:07:58
 * 
 * @Version 1.0.5
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ShiroRedisCache<K, V> implements Cache<K, V> {
	private static final Logger logger = LogManager.getLogger(ShiroRedisCache.class.getName());

	private static RedisOperation redisOperation;
	private String keyPrefix = RedisCacheConstants.SHIRO_REDIS_CACHE;

	/**
	 * @return the redisOperation
	 */
	public static RedisOperation getRedisOperation() {
		return redisOperation;
	}

	/**
	 * @param redisOperation
	 *            the redisOperation to set
	 */
	public static void setRedisOperation(RedisOperation redisOperation) {
		ShiroRedisCache.redisOperation = redisOperation;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	/**
	 * Constructs a cache instance with the specified Redis manager and using a
	 * custom key prefix.
	 * 
	 * @param cache
	 *            The cache manager instance
	 * @param prefix
	 *            The Redis key prefix
	 */
	public ShiroRedisCache(String prefix) {
		// set the prefix
		this.keyPrefix = prefix;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key) {
		//logger.trace(key instanceof Serializable);
		//logger.trace(key instanceof String);
		//logger.trace(key.getClass().getName());

		if (!(key instanceof Serializable)) {
			throw new ParamsException("序列化参数没有实现Serializable接口");
		}

		logger.debug("获取string的byte数组：" + key);
		String preKey = this.keyPrefix + key;

		return preKey.getBytes();
	}

	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		V value = null;

		if (key == null) {
			return null;
		}

		try {
			byte[] rawValue = redisOperation.get(getByteKey(key));
			value = (V) Serializer.unserialize(rawValue);

			return value;
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("出错", e));

			throw new CacheException("获取缓存出错", e);
		}
	}

	/**
	 * 将shiro的缓存保存到redis中
	 */
	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key存储, key [" + key + "]");
		try {
			redisOperation.set(getByteKey(key), Serializer.serialize(value));

			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");

		try {
			V previous = get(key);
			redisOperation.del(getByteKey(key));

			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * 清空所有缓存
	 */
	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");

		try {
			Set<byte[]> keys = redisOperation.keys(this.keyPrefix + "*");

			if (!CollectionUtils.isEmpty(keys)) {
				for (byte[] key : keys) {
					redisOperation.del(key);
				}
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * 缓存的个数
	 */
	@Override
	public int size() {
		try {
			Set<byte[]> keys = redisOperation.keys(this.keyPrefix + "*");

			return keys.size();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * 获取所有的key
	 */
	@Override
	public Set<K> keys() {
		try {
			Set<byte[]> keys = redisOperation.keys(this.keyPrefix + "*");

			if (CollectionUtils.isEmpty(keys)) {
				return Collections.emptySet();
			} else {
				Set<K> newKeys = new HashSet<K>();
				for (byte[] key : keys) {
					newKeys.add((K) key);
				}
				return newKeys;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * 获取所有的value
	 */
	@Override
	public Collection values() {
		try {
			Set<byte[]> keys = redisOperation.keys(this.keyPrefix + "*");

			if (!CollectionUtils.isEmpty(keys)) {
				List<V> values = new ArrayList<V>(keys.size());
				for (byte[] key : keys) {
					V value = get((K) key);
					if (value != null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}
}