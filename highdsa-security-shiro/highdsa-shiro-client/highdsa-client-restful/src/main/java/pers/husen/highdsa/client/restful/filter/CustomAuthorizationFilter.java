package pers.husen.highdsa.client.restful.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.constant.HttpConstants;
import pers.husen.highdsa.common.constant.HttpStatusCode;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;

/**
 * @Desc 自定义权限过滤器,未登录时返回json字符串
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月19日 上午4:16:45
 * 
 * @Version 1.0.3
 */
public class CustomAuthorizationFilter extends AuthorizationFilter {
	private static final Logger logger = LogManager.getLogger(CustomAuthorizationFilter.class.getName());

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String[] perms = (String[]) mappedValue;

		boolean isPermitted = true;
		if (perms != null && perms.length > 0) {
			if (perms.length == 1) {
				if (!subject.isPermitted(perms[0])) {
					isPermitted = false;
				}
			} else {
				if (!subject.isPermittedAll(perms)) {
					isPermitted = false;
				}
			}
		}

		return isPermitted;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse servletResponse) throws IOException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.setStatus(HttpStatusCode.UNAUTHORIZED);
		response.setContentType(HttpConstants.RESPONSE_JSON);
		response.setCharacterEncoding(Encode.DEFAULT_ENCODE);
		PrintWriter writer = response.getWriter();

		ResponseJson responseJson = new ResponseJson(false, "您没有相应的权限!");
		String reply = new ObjectMapper().writeValueAsString(responseJson);
		logger.info(reply);
		writer.write(reply);

		writer.flush();
		writer.close();

		return false;
	}
}