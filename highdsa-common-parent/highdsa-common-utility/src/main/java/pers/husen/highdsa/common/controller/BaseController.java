package pers.husen.highdsa.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @Desc spring mvc 控制器基类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月5日 下午3:42:44
 * 
 * @Version 1.0.0
 */
public class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * ModelAttribute 表示先执行
	 * 
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();

		// 设置编码
		response.setContentType("text/html;charset=UTF-8");
	}
}