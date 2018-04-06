package pers.husen.highdsa.service.shiro.client.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import pers.husen.highdsa.common.controller.ClientSavedRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Desc 身份认证拦截器(authc)
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:06:54
 * 
 * @Version 1.0.0
 */
public class ClientAuthenticationFilter extends AuthenticationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		return subject.isAuthenticated();
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 当用户没有身份认证时
		// 首先得到请求参数backUrl,即登录成功重定向到的地址
		String backUrl = request.getParameter("backUrl");
		// 然后保存请求到会话
		saveRequest(request, backUrl, getDefaultBackUrl(WebUtils.toHttp(request)));
		// 并重定向到登录地址(非server模块,此处本地登录,server验证)
		redirectToLogin(request, response);

		return false;
	}

	/**
	 * 保存请求到会话,登录成功后,返回地址按照如下顺序获取：backUrl、保存的当前请求地址、defaultBackUrl(即设置的successUrl)
	 * 
	 * @param request
	 * @param backUrl
	 * @param fallbackUrl
	 */
	protected void saveRequest(ServletRequest request, String backUrl, String fallbackUrl) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		session.setAttribute("authc.fallbackUrl", fallbackUrl);
		SavedRequest savedRequest = new ClientSavedRequest(httpRequest, backUrl);
		session.setAttribute(WebUtils.SAVED_REQUEST_KEY, savedRequest);
	}

	/**
	 * defaultBackUrl(即设置的successUrl)
	 * 
	 * @param request
	 * @return
	 */
	private String getDefaultBackUrl(HttpServletRequest request) {
		String scheme = request.getScheme();
		String domain = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getContextPath();
		StringBuilder backUrl = new StringBuilder(scheme);
		backUrl.append("://");
		backUrl.append(domain);

		if ("http".equalsIgnoreCase(scheme) && port != 80) {
			backUrl.append(":").append(String.valueOf(port));
		} else if ("https".equalsIgnoreCase(scheme) && port != 443) {
			backUrl.append(":").append(String.valueOf(port));
		}

		backUrl.append(contextPath);
		backUrl.append(getSuccessUrl());

		return backUrl.toString();
	}
}