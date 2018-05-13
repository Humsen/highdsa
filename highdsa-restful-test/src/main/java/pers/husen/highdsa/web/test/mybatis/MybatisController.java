package pers.husen.highdsa.web.test.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.common.entity.po.system.SysUserInfo;
import pers.husen.highdsa.web.test.mybatis.handler.MybatisSvc;

/**
 * @Desc 测试mybatis
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 上午10:22:15
 * 
 * @Version 1.0.2
 */
@RestController
@RequestMapping("/mybatis/v1")
public class MybatisController {
	@Autowired
	MybatisSvc mybatisSvc;

	@ResponseBody
	@RequestMapping(value = "/user.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public SysUserInfo selectById(@RequestParam("user_id") Long userId) throws JsonProcessingException {

		return mybatisSvc.selectById(userId);
	}
}