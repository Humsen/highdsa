package pers.husen.highdsa.web.redis.handler;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.entity.vo.redis.RedisJson;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.redis.RedisOperation;
import pers.husen.highdsa.service.redis.RedisPools;

/**
 * @Desc redis调用 业务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午8:52:20
 * 
 * @Version 1.0.1
 */
@Service
public class RedisSvc {
	private final Logger logger = LogManager.getLogger(RedisSvc.class.getName());

	@Autowired
	private RedisPools redisPools;

	@Autowired
	private RedisOperation redisOperation;

	/* ------------------------------- set ------------------------------- */

	/**
	 * 设置string
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	public String setString(String key, String value,
			@RequestParam(value = "expire", defaultValue = "-1") int cacheSeconds) {
		String reply = null;

		if (-1 == cacheSeconds) {
			reply = redisOperation.set(key, value);
			logger.info("缓存成功, 返回{}", reply);

			return getReplyJson(reply);
		}

		reply = redisOperation.set(key, value, cacheSeconds);
		logger.info("缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/**
	 * 设置对象缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	public String setObject(String key, String value,
			@RequestParam(value = "expire", defaultValue = "0") int cacheSeconds) {
		String reply = null;

		reply = redisOperation.setObject(key, getTargetObject(value), cacheSeconds);
		logger.info("缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/* ------------------------------- get ------------------------------- */

	/**
	 * 获取string
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	public String getString(String key) {
		String reply = null;

		reply = redisOperation.get(key);
		logger.info("获取缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getObject(String key) {
		Object reply = null;

		reply = redisOperation.getObject(key);
		logger.info("获取缓存成功, 返回{}", reply);

		return getReplyJson(reply.toString());
	}

	/* ------------------------------- modify ------------------------------- */

	/**
	 * 修改string(客户端提供完整资源)
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public String modifyString(String key, String value,
			@RequestParam(value = "expire", defaultValue = "-1") int cacheSeconds) {
		String reply = null;

		if (-1 == cacheSeconds) {
			reply = redisOperation.set(key, value);
			logger.info("修改缓存成功, 返回{}", reply);

			return getReplyJson(reply);
		}

		reply = redisOperation.set(key, value, cacheSeconds);
		logger.info("修改缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/**
	 * 修改object(客户端提供完整资源)
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public String modifyObject(String key, String value,
			@RequestParam(value = "expire", defaultValue = "0") int cacheSeconds) {
		String reply = null;

		reply = redisOperation.setObject(key, getTargetObject(value), cacheSeconds);
		logger.info("修改缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/* ------------------------------- append ------------------------------- */

	/**
	 * 增加string(客户端提供改变的属性)
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.PATCH, produces = "application/json;charset=UTF-8")
	public String appendString(String key, String addString) {
		String reply = null;

		reply = redisOperation.append(key, addString) + "";
		logger.info("修改缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/* ------------------------------- delete ------------------------------- */

	/**
	 * 删除string
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String deleteString(String key) {
		String reply = null;

		reply = redisOperation.del(key) + "";
		logger.info("删除缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/**
	 * 删除object
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String deleteObject(String key) {
		String reply = null;

		reply = redisOperation.delObject(key) + "";
		logger.info("删除缓存成功, 返回{}", reply);

		return getReplyJson(reply);
	}

	/* ------------------------------- exists ------------------------------- */

	/**
	 * 判断string是否存在
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exist/string.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String existsString(String key) {
		String reply = null;

		reply = redisOperation.exists(key) + "";
		logger.info("判断String缓存是否存在, 返回{}", reply);

		return getReplyJson(reply);
	}

	/**
	 * 判断object缓存是否存在
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exist/object.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String existsObject(String key) {
		String reply = null;

		reply = redisOperation.delObject(key) + "";
		logger.info("判断Object缓存是否存在, 返回{}", reply);

		return getReplyJson(reply);
	}

	/* ------------------------------ close pools ------------------------------ */

	/**
	 * 关闭redis连接池
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pool.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String closeRedisPool() {
		redisPools.closeRedisPool();

		logger.info("关闭redis pool 成功!");

		return "OK";
	}

	/* ------------------------------- delete all ------------------------------- */

	/**
	 * 删除所有缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exist.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String deleteAll() {
		String reply = null;

		reply = redisOperation.flushAll();
		logger.info("删除所有缓存, 返回{}", reply);

		return getReplyJson(reply);
	}

	/* ------------------------------- tools ------------------------------- */

	/**
	 * 根据json字符串中的类名和字段, 反射得到对象实例
	 * 
	 * @param jsonString
	 * @return
	 */
	private Object getTargetObject(String jsonString) {
		Object object = null;
		String targetClassKey = "class_name";

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<?, ?> jsonMap = objectMapper.readValue(jsonString, Map.class);
			String targetClass = (String) jsonMap.get(targetClassKey);

			Class<?> clazz = Class.forName("pers.husen.highdsa.common.po." + targetClass);
			// objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			object = objectMapper.readValue(jsonString, clazz);

			logger.info("json字符串转对象成功, {}", object);
		} catch (IOException | ClassNotFoundException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("转换成目标对象失败", e));
		}

		return object;
	}

	/**
	 * 对象转换成json字符串
	 * 
	 * @param redisJsonVo
	 * @return
	 */
	private String getReplyJson(String reply) {
		String jsonStr = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			RedisJson redisJsonVo = new RedisJson(reply);
			jsonStr = mapper.writeValueAsString(redisJsonVo);
		} catch (JsonProcessingException e) {
			StackTrace2Str.exceptionStackTrace2Str("对象序列化成json失败", e);
		}

		return jsonStr;
	}
}