package pers.husen.highdsa.web.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.husen.highdsa.web.shiro.handler.ShiroSvc;

/**
 * @Desc 业务访问入口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:23:54
 * 
 * @Version 1.0.2
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

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map) {

		return shiroSvc.index(map);
	}
}