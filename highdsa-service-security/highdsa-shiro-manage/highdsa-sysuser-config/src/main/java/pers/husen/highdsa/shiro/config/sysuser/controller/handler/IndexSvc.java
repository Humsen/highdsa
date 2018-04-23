package pers.husen.highdsa.shiro.config.sysuser.controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.buji.pac4j.subject.Pac4jPrincipal;
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

		// 获取用户身份
		Pac4jPrincipal p = SecurityUtils.getSubject().getPrincipals().oneByType(Pac4jPrincipal.class);

		if (p != null) {
			CommonProfile profile = p.getProfile();
			map.put("profile", profile);
		}

		String userName = p.getProfile().getId();
		List<?> navigationBar = sysUserManager.findNavigationBar(userName);
		
		HttpSession session = request.getSession();
		session.setAttribute("navibar", navigationBar);
		session.setAttribute("userName", userName);

		return mov;
	}
}