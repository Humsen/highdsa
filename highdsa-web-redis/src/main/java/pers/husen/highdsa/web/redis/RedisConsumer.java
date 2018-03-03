package pers.husen.highdsa.web.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.husen.highdsa.service.redis.RedisOperation;
import pers.husen.highdsa.service.redis.RedisPools;

/**
 * @Desc redis消费者
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月3日 下午6:46:16
 * 
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/redis/v1")
public class RedisConsumer {
	private final Logger logger = LogManager.getLogger(RedisConsumer.class.getName());

	@Autowired
	private RedisPools redisPools;

	@Autowired
	private RedisOperation redisOperation;

	@RequestMapping(value = "/cache/string.hms", produces = "application/json")
	@ResponseBody
	public String cacheString(String key, String value) {
		String reply = redisOperation.set(key, value);

		logger.info("缓存成功, 返回{}", reply);

		return reply;
	}

	@RequestMapping(value = "/pool/close.hms", produces = "application/json")
	@ResponseBody
	public String cacheString() {
		redisPools.closeRedisPool();

		logger.info("关闭redis pool 成功!");

		return "OK";
	}
}