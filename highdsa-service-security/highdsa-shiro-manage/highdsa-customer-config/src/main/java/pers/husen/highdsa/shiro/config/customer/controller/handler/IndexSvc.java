package pers.husen.highdsa.shiro.config.customer.controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 首页服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:26:30
 * 
 * @Version 1.0.0
 */
@Service
public class IndexSvc {
	@Autowired
	private SysUserManager sysUserManager;

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView index(ModelMap map, HttpServletRequest request) {
		ModelAndView mov = new ModelAndView("index");

		HttpServletRequest httpReq = (HttpServletRequest) request;

		String userName = (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		List<?> navigationBar = sysUserManager.findNavigationBar(userName);
		httpReq.getSession().setAttribute("navibar", navigationBar);

		return mov;
	}
}