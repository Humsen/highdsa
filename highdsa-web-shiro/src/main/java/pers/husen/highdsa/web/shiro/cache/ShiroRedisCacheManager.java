package pers.husen.highdsa.web.shiro.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Desc 自定义缓存管理器
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 上午8:29:21
 * 
 * @Version 1.0.0
 */
@SuppressWarnings("rawtypes")
public class ShiroRedisCacheManager implements CacheManager {
	private static final Logger logger = LogManager.getLogger(ShiroRedisCacheManager.class.getName());

	// fast lookup by name map
	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

	/**
	 * The Redis key prefix for caches
	 */
	private String keyPrefix = "shiro_redis_cache:";

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	/*
	 * public <K, V> Cache<K, V> getCache() throws CacheException { return new
	 * ShiroRedisCache<K, V>(); }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.info("获取名称为: " + name + " 的RedisCache实例");

		Cache cache = cacheMap.get(name);

		if (cache == null) {

			// create a new cache instance
			cache = new ShiroRedisCache<K, V>(keyPrefix);

			// add it to the cache collection
			cacheMap.put(name, cache);
		}
		return cache;
	}
}