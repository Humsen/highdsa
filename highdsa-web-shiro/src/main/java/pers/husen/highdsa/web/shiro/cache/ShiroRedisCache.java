package pers.husen.highdsa.web.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import pers.husen.highdsa.common.constant.RedisCacheKeyPrefix;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.redis.RedisOperation;
import pers.husen.highdsa.service.redis.RedisPools;

/**
 * @Desc shiro使用redis缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 上午12:07:58
 * 
 * @Version 1.0.1
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ShiroRedisCache<K, V> implements Cache<K, V> {
	private static final Logger logger = LogManager.getLogger(ShiroRedisCache.class.getName());

	private static RedisOperation redisOperation;
	//private static RedisPools redisPools;

	private String keyPrefix = RedisCacheKeyPrefix.SHIRO_REDIS_CACHE;

	/**
	 * @return the redisOperation
	 */
	public static RedisOperation getRedisOperation() {
		return redisOperation;
	}

	/**
	 * @param redisOperation the redisOperation to set
	 */
	public static void setRedisOperation(RedisOperation redisOperation) {
		ShiroRedisCache.redisOperation = redisOperation;
	}

	public static void setRedisFactory(RedisOperation redisOperation, RedisPools redisPools) {
		ShiroRedisCache.redisOperation = redisOperation;
		//ShiroRedisCache.redisPools = redisPools;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	/**
	 * 获取带前缀的key
	 * 
	 * @param key
	 * @return
	 */
	public String getKeyWithPrefix(String key) {
		return keyPrefix + key;
	}

	@Override
	public Object get(Object key) throws CacheException {
		Object object = null;

		if (key == null) {
			return null;
		}

		try {
			object = redisOperation.getObject(key);
		} catch (Exception e) {
			logger.fatal(StackTrace2Str.exceptionStackTrace2Str("出错", e));

			throw new CacheException("获取缓存出错", e);
		}

		return (V) object;
	}

	/**
	 * 将shiro的缓存保存到redis中
	 */
	@Override
	public Object put(Object key, Object value) throws CacheException {
		redisOperation.setObject(key, value, 0);

		return get(key);

	}

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
	@Override
	public Set keys() {
		return redisOperation.keys("*");
	}

	/**
	 * 获取所有的value
	 */
	@Override
	public Collection values() {
		return redisOperation.values("*");
	}
}