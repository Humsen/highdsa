package pers.husen.highdsa.client.restful.filter;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.constant.HttpConstants;
import pers.husen.highdsa.common.constant.HttpStatusCode;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;

/**
 * @Desc 自定义退出过滤器,退出后返回json字符串
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月25日 上午2:55:19
 * 
 * @Version 1.0.0
 */
public class CustomLogoutFilter extends LogoutFilter {
	private static final Logger logger = LogManager.getLogger(CustomLogoutFilter.class.getName());

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);

		// Check if POST only logout is enabled
		if (isPostOnlyLogout()) {

			// check if the current request's method is a POST, if not redirect
			if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
				return onLogoutRequestNotAPost(request, response);
			}
		}

		try {
			subject.logout();
		} catch (SessionException ise) {
			logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
		}

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		httpServletResponse.setStatus(HttpStatusCode.UNAUTHORIZED);
		httpServletResponse.setContentType(HttpConstants.RESPONSE_JSON);
		httpServletResponse.setCharacterEncoding(Encode.DEFAULT_ENCODE);
		PrintWriter writer = httpServletResponse.getWriter();

		ResponseJson responseJson = new ResponseJson(true, "退出登录成功");
		String reply = new ObjectMapper().writeValueAsString(responseJson);
		logger.info(reply);
		writer.write(reply);

		writer.flush();
		writer.close();

		return false;
	}
}