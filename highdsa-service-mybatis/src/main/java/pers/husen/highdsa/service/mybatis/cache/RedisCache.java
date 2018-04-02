package pers.husen.highdsa.service.mybatis.cache;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.exception.ParamsException;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.exception.cache.MybatisRedisCacheException;
import pers.husen.highdsa.common.utility.Serializer;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc redis作为mybatis二级缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午9:11:07
 * 
 * @Version 1.0.3
 */
@Repository(value = "redisCache")
public class RedisCache implements Cache {
	private static final Logger logger = LogManager.getLogger(RedisCache.class.getName());

	private static RedisOperation redisOperation;
	/** The {@code ReadWriteLock}. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String keyPrefix = RedisCacheConstants.MYBATIS_REDIS_CACHE;

	public static void setRedisOperation(RedisOperation redisOperation) {
		RedisCache.redisOperation = redisOperation;
	}

	@Value(value = "redis-cache")
	private String id;

	public RedisCache() {
	}

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.trace("RedisCache init: id=" + id);

		this.id = id;
	}

	@Override
	public void clear() {
		logger.trace("从redis中删除所有元素");

		try {
			// redisOperation.flushDB();
			Set<byte[]> keys = redisOperation.keys(this.keyPrefix + "*");

			if (!CollectionUtils.isEmpty(keys)) {
				for (byte[] key : keys) {
					redisOperation.del(key);
				}
			}
		} catch (Exception e) {
			throw new MybatisRedisCacheException("从redis中删除所有元素出错", e);
		}
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		Object value = null;

		if (key == null) {
			return null;
		}

		try {
			byte[] rawValue = redisOperation.get(getByteKey(key));
			value = Serializer.unserialize(rawValue);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("出错", e));

			throw new CacheException("获取缓存出错", e);
		}

		return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		Set<byte[]> keys = redisOperation.keys(this.keyPrefix + "*");
		return keys.size();
	}

	@Override
	public void putObject(Object key, Object value) {
		logger.debug("mybatis根据key存储, key [" + key + "]");
		logger.debug(key instanceof Serializable);
		logger.debug(key instanceof String);
		logger.debug(key.getClass().getName());

		try {
			redisOperation.set(getByteKey(key), Serializer.serialize(value));
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public Object removeObject(Object key) {
		logger.debug("从redis中删除 key [" + key + "]");

		try {
			Object previous = this.getObject(key);
			redisOperation.del(getByteKey(key));

			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/**
	 * 获取带前缀的字节数组Key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Object key) {
		if (!(key instanceof Serializable)) {
			throw new ParamsException("序列化参数没有实现Serializable接口");
		}

		String preKey = this.keyPrefix + (Serializable) key;
		return preKey.getBytes();
	}
}