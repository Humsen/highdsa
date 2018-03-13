package pers.husen.highdsa.web.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.husen.highdsa.common.response.BaseController;

/**
 * @Desc redis消费者
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月3日 下午6:46:16
 * 
 * @Version 1.0.2
 */
@RestController
@RequestMapping(value = "/redis/v1")
public class RedisController extends BaseController {
	@Autowired
	RedisSvc redisSvc;

	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String setString(String key, String value,
			@RequestParam(value = "expire", defaultValue = "-1") int cacheSeconds) {

		return redisSvc.setString(key, value, cacheSeconds);
	}

	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String setObject(String key, String value,
			@RequestParam(value = "expire", defaultValue = "0") int cacheSeconds) {

		return redisSvc.setObject(key, value, cacheSeconds);
	}

	/* ------------------------------- get ------------------------------- */

	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getString(String key) {

		return redisSvc.getString(key);
	}

	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getObject(String key) {

		return redisSvc.getObject(key);
	}

	/* ------------------------------- modify ------------------------------- */

	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public String modifyString(String key, String value,
			@RequestParam(value = "expire", defaultValue = "-1") int cacheSeconds) {

		return redisSvc.modifyString(key, value, cacheSeconds);
	}

	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public String modifyObject(String key, String value,
			@RequestParam(value = "expire", defaultValue = "0") int cacheSeconds) {

		return redisSvc.modifyObject(key, value, cacheSeconds);
	}

	/* ------------------------------- append ------------------------------- */

	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.PATCH, produces = "application/json;charset=UTF-8")
	public String appendString(String key, String addString) {

		return redisSvc.appendString(key, addString);
	}

	/* ------------------------------- delete ------------------------------- */

	@ResponseBody
	@RequestMapping(value = "/cache/string.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String deleteString(String key) {

		return redisSvc.deleteString(key);
	}

	@ResponseBody
	@RequestMapping(value = "/cache/object.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String deleteObject(String key) {

		return redisSvc.deleteObject(key);
	}

	/* ------------------------------- exists ------------------------------- */

	@ResponseBody
	@RequestMapping(value = "/exist/string.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String existsString(String key) {

		return redisSvc.existsString(key);
	}

	@ResponseBody
	@RequestMapping(value = "/exist/object.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String existsObject(String key) {

		return redisSvc.existsObject(key);
	}

	/* ------------------------------ close pools ------------------------------ */

	@RequestMapping(value = "/pool.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String closeRedisPool() {

		return redisSvc.closeRedisPool();
	}

	/* ------------------------------- delete all ------------------------------- */

	@ResponseBody
	@RequestMapping(value = "/exist.hms", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String deleteAll() {

		return redisSvc.deleteAll();
	}
}