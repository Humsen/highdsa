package pers.husen.highdsa.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.ExceptionName;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;
import pers.husen.highdsa.common.exception.db.SqlException;

/**
 * @Desc 全局异常拦截器
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 下午11:29:05
 * 
 * @Version 1.0.2
 */
public class ExceptionInterceptor implements HandlerExceptionResolver {
	private static final Logger logger = LogManager.getLogger(ExceptionInterceptor.class.getName());
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
		logger.fatal(StackTrace2Str.exceptionStackTrace2Str(exception));
		ResponseJson responseJson = new ResponseJson(false);

		// 判断是否ajax请求
		if (!isAjax(request)) {
			// 如果被识别为相应异常则做出相应处理, 否则统一归为系统异常
			if (exception instanceof SqlException) {
				response.setStatus(400);

				responseJson.setMessage(ExceptionName.SQL_EXCEPTION);
				logger.error(StackTrace2Str.exceptionStackTrace2Str("捕获SQL异常", exception));
			} else {
				responseJson.setMessage(ExceptionName.SYS_EXCEPTION);
				logger.error(StackTrace2Str.exceptionStackTrace2Str("捕获系统异常", exception));
			}

			// 对于非ajax请求,我们都统一跳转到error.jsp页面
			return new ModelAndView("redirect:/error.jsp", responseJson2Map(responseJson));
		} else {

			// 如果是ajax请求,JSON格式返回
			try {
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();

				// 为安全起见,只有业务异常我们对前端可见,否则统一归为系统异常
				if (exception instanceof SqlException) {
					responseJson.setMessage(ExceptionName.SQL_EXCEPTION);
					logger.error(StackTrace2Str.exceptionStackTrace2Str("捕获SQL异常", exception));
				} else {
					responseJson.setMessage(ExceptionName.SYS_EXCEPTION);
					logger.error(StackTrace2Str.exceptionStackTrace2Str("捕获系统异常", exception));
				}

				writer.write(objectMapper.writeValueAsString(responseJson));
				writer.flush();
				writer.close();
				
				return null;
			} catch (IOException e) {
				logger.error(StackTrace2Str.exceptionStackTrace2Str("异常系统捕获处理时再次异常", e));

				// 异常中的异常返回到错误页面
				return new ModelAndView("redirect:/error.jsp", responseJson2Map(responseJson));
			}
		}
	}

	/**
	 * 判断是否是ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");

		if (header != null && "XMLHttpRequest".equalsIgnoreCase(header)) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private Map<String, ?> responseJson2Map(ResponseJson responseJson) {
		Map<?, ?> map = new HashMap<>(2);

		try {
			String jsonStr = objectMapper.writeValueAsString(responseJson);

			map = objectMapper.readValue(jsonStr, Map.class);
		} catch (IOException e) {
			logger.fatal(StackTrace2Str.exceptionStackTrace2Str("jackson转换异常", e));
		}

		return (Map<String, ?>) map;
	}
}