package pers.husen.highdsa.shiro.config.sysuser.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Desc 默认异常处理
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:06:25
 * 
 * @Version 1.0.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(UnauthorizedException e) {
		ModelAndView mav = new ModelAndView("unauthorized");
		mav.addObject("exception", e);
		return mav;
	}
}