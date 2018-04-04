package pers.husen.highdsa.web.shiro;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.husen.highdsa.common.controller.BaseController;

/**
 * @Desc 请求转发调度器,继承BaseController拦截request和response
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月4日 上午10:00:05
 * 
 * @Version 1.0.0
 */
@Controller
@RequestMapping(value = "/sys/v1")
public class DispatcherController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void login() throws ServletException, IOException {
		System.out.println("开始登陆。。。");
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void logout() throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/logoutSuccess.jsp").forward(request, response);
	}
}