package pers.husen.highdsa.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.app.handler.LoginSvc;

/**
 * @Desc app 调用
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月12日 下午5:30:05
 * 
 * @Version 1.0.4
 */
@Controller
@RequestMapping(value = "/app/v1")
public class LoginController {
	@Autowired
	LoginSvc loginSvc;

	@ResponseBody
	@RequestMapping(value = "/hello", produces = "application/json;charset=UTF-8")
	public boolean helloWorld() {
		return loginSvc.helloWorld();
	}

	@ResponseBody
	@RequestMapping(value = "/login/username", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String loginWithName(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String userPassword) throws JsonProcessingException {

		return loginSvc.loginWithName(userName, userPassword);
	}

	@ResponseBody
	@RequestMapping(value = "/login/phone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String loginWithPhone(@RequestParam(value = "phone") String phone, @RequestParam(value = "password") String userPassword) throws Exception {

		return loginSvc.loginWithPhone(phone, userPassword);
	}

	@ResponseBody
	@RequestMapping(value = "/login/email", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String loginWithEmail(String email, @RequestParam(value = "password") String userPassword) throws JsonProcessingException {

		return loginSvc.loginWithEmail(email, userPassword);
	}

	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String logout() throws JsonProcessingException {

		return loginSvc.logout();
	}
}