package pers.husen.highdsa.service.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Desc 认证过滤器。因为是多项目登录，比如如果是从其他应用中重定向过来的，首先检查Session 中是否有
 *       “authc.fallbackUrl”属性，如果有就认为它是默认的重定向地址；否则使用Server 自己的
 *       successUrl作为登录成功后重定向到的地址
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月4日 上午10:55:17
 * 
 * @Version 1.0.0
 */
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws IOException {
		String fallbackUrl = (String) getSubject(request, response).getSession().getAttribute("authc.fallbackUrl");
		if (StringUtils.isEmpty(fallbackUrl)) {
			fallbackUrl = getSuccessUrl();
		}

		WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
	}
}