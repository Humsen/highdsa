package pers.husen.highdsa.service.mybatis.cache;

import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc 使用中间类解决RedisCache.jedisConnectionFactory的静态注入,从而使MyBatis实现第三方缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月8日 上午10:58:16
 * 
 * @Version 1.0.0
 */
public class RedisCacheTransfer {
	/**
	 * 注入静态redis操作示例
	 * 
	 * @param redisOperation
	 */
	@Autowired
	public void setRedisOperation(RedisOperation redisOperation) {
		RedisCache.setRedisOperation(redisOperation);
	}
}