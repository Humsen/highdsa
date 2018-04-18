package pers.husen.highdsa.service.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Desc 类功能说明 ,拦截/login请求，重定向到cas登录验证
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月9日 下午10:37:51
 * 
 * @Version 1.0.0
 */
public class Redirect2CasLoginFilter extends AccessControlFilter {

	private Logger log = LoggerFactory.getLogger(Redirect2CasLoginFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		String loginUrl = getLoginUrl();

		HttpServletRequest httpResq = (HttpServletRequest) request;

		String requestUri = httpResq.getRequestURI();

		// 得到企业的eid发到cas服务，但得到的值有可能不是eid而是其他字符串
		String eidParam = requestUri.substring(requestUri.lastIndexOf("/") + 1);

		loginUrl += "&eid=" + eidParam;

		WebUtils.issueRedirect(request, response, loginUrl);
		log.debug("========== redirect to cas server:'{}' ===========", loginUrl);

		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return false;
	}
}