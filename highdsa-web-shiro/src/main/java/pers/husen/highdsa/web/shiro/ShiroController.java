package pers.husen.highdsa.web.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.shiro.handler.ShiroSvc;

/**
 * @Desc 业务访问入口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:23:54
 * 
 * @Version 1.0.1
 */
@Controller
@RequestMapping(value = "/sys/v1")
public class ShiroController {
	@Autowired
	ShiroSvc shiroSvc;

	@ResponseBody
	@RequestMapping(value = "/hello", produces = "application/json;charset=UTF-8")
	public String helloWorld() {
		return shiroSvc.helloWorld();
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String login(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String userPassword) throws JsonProcessingException {
		
		return shiroSvc.login(userName, userPassword);
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String logout() throws JsonProcessingException {
		
		return shiroSvc.logout();
	}
}