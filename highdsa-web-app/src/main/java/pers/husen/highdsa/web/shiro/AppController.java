package pers.husen.highdsa.web.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.shiro.handler.AppSvc;

/**
 * @Desc app 调用
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月12日 下午5:30:05
 * 
 * @Version 1.0.0
 */
@Controller
@RequestMapping(value = "/app/v1")
public class AppController {
	@Autowired
	AppSvc shiroSvc;

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