package pers.husen.highdsa.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.JsonKey3Value;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.exception.custom.SqlException;

/**
 * @Desc 全局异常拦截器
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 下午11:29:05
 * 
 * @Version 1.0.0
 */
public class ExceptionInterceptor implements HandlerExceptionResolver {
	private final Logger logger = LogManager.getLogger(ExceptionInterceptor.class.getName());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		logger.fatal(StackTrace2Str.exceptionStackTrace2Str(exception));
		// 判断是否ajax请求
		//int acceptJson = request.getHeader("accept").indexOf("application/json");
		boolean isAjax = true;

		if (!isAjax) {
			logger.fatal("不是ajax");
			// 如果不是ajax，JSP格式返回
			// 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
			Map<String, Object> map = new HashMap<String, Object>(200);
			map.put("success", false);

			if (exception instanceof SqlException) {
				response.setStatus(400);
				map.put(JsonKey3Value.ERROR_MSG, exception.getMessage());
				logger.error("show business exception:{}", exception.getMessage());
			} else {
				map.put(JsonKey3Value.ERROR_MSG, JsonKey3Value.SYS_EXCEPTION);
				logger.error("show exception:{}", JsonKey3Value.SYS_EXCEPTION);
			}

			// 对于非ajax请求，我们都统一跳转到error.jsp页面
			return new ModelAndView("redirect:/error.jsp", map);
		} else {
			logger.fatal("是ajax");
			Map<String, Object> map = new HashMap<String, Object>(200);
			// 如果是ajax请求，JSON格式返回
			try {
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();

				map.put("success", false);
				// 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
				if (exception instanceof SqlException) {
					map.put(JsonKey3Value.ERROR_MSG, exception.getMessage());
					logger.error("show business exception:{}", exception.getMessage());
				} else {
					map.put(JsonKey3Value.ERROR_MSG, JsonKey3Value.SYS_EXCEPTION);
					logger.error("show exception:{}", JsonKey3Value.SYS_EXCEPTION);
				}

				ObjectMapper mapper = new ObjectMapper();
				writer.write(mapper.writeValueAsString(map));
				writer.flush();
				writer.close();
				return null;
			} catch (IOException e) {
				logger.error("show exception error:{}", e.getMessage());
				// map.put(OssRespCodes.ERROR_MSG, ErrorMessage.SYS_EXCEPTION);
				// 异常中的异常返回到错误页面
				return new ModelAndView("redirect:/error.jsp", map);
			}
		}
	}
}