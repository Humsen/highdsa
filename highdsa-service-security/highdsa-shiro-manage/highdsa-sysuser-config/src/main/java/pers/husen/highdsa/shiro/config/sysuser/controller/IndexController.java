package pers.husen.highdsa.shiro.config.sysuser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Desc 首页控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:05:05
 * 
 * @Version 1.0.0
 */
@Controller
public class IndexController {

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView index() {
		ModelAndView mov = new ModelAndView("index");

		return mov;
	}
}