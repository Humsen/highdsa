package pers.husen.highdsa.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.constant.HttpConstants;
import pers.husen.highdsa.common.constant.HttpStatusCode;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;

/**
 * @Desc restful请求异常拦截器
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月19日 上午1:13:11
 * 
 * @Version 1.0.1
 */
public class RestfulExceptionInterceptor implements HandlerExceptionResolver {
	private final Logger logger = LogManager.getLogger(RestfulExceptionInterceptor.class.getName());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		// 未登录异常
		if (exception instanceof UnauthenticatedException) {
			response.setStatus(HttpStatusCode.UNAUTHORIZED);

			logger.error(StackTrace2Str.exceptionStackTrace2Str("捕获未登录异常", exception));
			handleUnauthenticatedException(response);

			return null;
		}
		// 无权限异常
		if (exception instanceof UnauthorizedException || exception instanceof AuthorizationException) {
			response.setStatus(HttpStatusCode.UNAUTHORIZED);

			logger.error(StackTrace2Str.exceptionStackTrace2Str("捕获无权限异常", exception));
			handleUnauthorizedException(response);

			return null;
		}

		return null;
	}

	/**
	 * 处理未登录异常
	 * 
	 * @param response
	 */
	private void handleUnauthenticatedException(HttpServletResponse response) {
		try {
			response.setContentType(HttpConstants.RESPONSE_JSON);
			response.setCharacterEncoding(Encode.DEFAULT_ENCODE);
			PrintWriter writer = response.getWriter();

			ResponseJson responseJson = new ResponseJson(false, "您尚未登录, 请先登录!");
			writer.write(new ObjectMapper().writeValueAsString(responseJson));

			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("异常系统捕获处理时再次异常", e));
		}
	}

	/**
	 * 处理无权限异常
	 * 
	 * @param response
	 */
	private void handleUnauthorizedException(HttpServletResponse response) {
		try {
			response.setContentType(HttpConstants.RESPONSE_JSON);
			response.setCharacterEncoding(Encode.DEFAULT_ENCODE);
			PrintWriter writer = response.getWriter();

			ResponseJson responseJson = new ResponseJson(false, "您没有相应的操作权限!");
			writer.write(new ObjectMapper().writeValueAsString(responseJson));

			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("异常系统捕获处理时再次异常", e));
		}
	}
}