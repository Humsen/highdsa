package pers.husen.highdsa.shiro.config.sysuser.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 将导航栏属性写入会话
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:06:40
 * 
 * @Version 1.0.1
 */
public class WithNavibarFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private SysUserManager sysUserManager;

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpReq = (HttpServletRequest) request;

		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		List<?> navigationBar = sysUserManager.findNavigationBar(userName);
		httpReq.getSession().setAttribute("navibar", navigationBar);

		return super.onLoginSuccess(token, subject, request, response);
	}
}