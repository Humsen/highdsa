package pers.husen.highdsa.service.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc redis作为mybatis二级缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午9:11:07
 * 
 * @Version 1.0.0
 */
@Repository(value = "redisCache")
public class RedisCache implements Cache {
	private static final Logger logger = LogManager.getLogger(RedisCache.class.getName());

	private static RedisOperation redisOperation;

	public static void setRedisOperation(RedisOperation redisOperation) {
        RedisCache.redisOperation = redisOperation;
    }
	
	@Value(value = "redis-cache")
	private String id;

	/**
	 * The {@code ReadWriteLock}.
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public RedisCache() {
	}

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.info("RedisCache init: id=" + id);

		this.id = id;
	}

	@Override
	public void clear() {
		redisOperation.flushDB();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		Object object = null;
		try {
			 object = redisOperation.getObject(key);
		} catch (Exception e) {
			logger.fatal(StackTrace2Str.exceptionStackTrace2Str("出错", e));
		}
		
		return object;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		return redisOperation.getDbSize();
	}

	@Override
	public void putObject(Object key, Object value) {
		redisOperation.setObject(key, value, 0);
	}

	@Override
	public Object removeObject(Object key) {
		return redisOperation.removeObject(key);
	}
}