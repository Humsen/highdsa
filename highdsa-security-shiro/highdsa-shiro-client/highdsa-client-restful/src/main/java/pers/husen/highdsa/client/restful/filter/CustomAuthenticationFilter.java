package pers.husen.highdsa.client.restful.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.client.restful.token.CustomerAccountPasswordToken;
import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.constant.HttpConstants;
import pers.husen.highdsa.common.entity.enums.LoginType;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;

/**
 * @Desc 自定义认证过滤器,未登录时返回json字符串
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月19日 上午12:45:50
 * 
 * @Version 1.0.0
 */
public class CustomAuthenticationFilter extends FormAuthenticationFilter {
	/**
	 * 只要没有登录,默认返回json
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (this.isLoginRequest(request, response)) {
			if (this.isLoginSubmission(request, response)) {
				boolean isGoOn = this.executeLogin(request, response);

				if (!isGoOn) {
					response.setContentType(HttpConstants.RESPONSE_JSON);
					response.setCharacterEncoding(Encode.DEFAULT_ENCODE);
					PrintWriter writer = response.getWriter();

					ResponseJson responseJson = new ResponseJson(false, "登录失败");
					writer.write(new ObjectMapper().writeValueAsString(responseJson));

					writer.flush();
					writer.close();

					return false;
				}

				return true;
			} else {

				return true;
			}
		} else {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding(Encode.DEFAULT_ENCODE);
			PrintWriter writer = response.getWriter();

			ResponseJson responseJson = new ResponseJson(false, "您尚未登录, 请先登录!");
			writer.write(new ObjectMapper().writeValueAsString(responseJson));

			writer.flush();
			writer.close();
		}

		return false;

	}

	/**
	 * 重写创建token,加入登录类型枚举
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);

		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		String requestURI = getPathWithinApplication(request);
		if (LoginType.EMAIL.getUri().equals(requestURI)) {
			return new CustomerAccountPasswordToken(username, password, rememberMe, host, LoginType.EMAIL);
		}
		if (LoginType.PHONE.getUri().equals(requestURI)) {
			return new CustomerAccountPasswordToken(username, password, rememberMe, host, LoginType.PHONE);
		}

		return new CustomerAccountPasswordToken(username, password, rememberMe, host, LoginType.USERNAME);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		return false;
	}
}