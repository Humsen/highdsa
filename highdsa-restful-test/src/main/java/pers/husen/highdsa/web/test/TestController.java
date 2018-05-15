package pers.husen.highdsa.web.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;

/**
 * @Desc 测试用的控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月14日 下午11:57:12
 * 
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/test/v1")
public class TestController {
	@ResponseBody
	@RequestMapping(value = "/go", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String testString(String phone) throws JsonProcessingException {
		System.out.println("手机号：" + phone);
		ResponseJson simpleJson = new ResponseJson(true, "测试通过");

		return new ObjectMapper().writeValueAsString(simpleJson);
	}

	@ResponseBody
	@RequestMapping(value = "/come", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseJson testBean(String phone) throws JsonProcessingException {
		System.out.println("手机号：" + phone);
		ResponseJson simpleJson = new ResponseJson(true, "测试通过");

		return simpleJson;
	}
}