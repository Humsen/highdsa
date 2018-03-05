package pers.husen.highdsa.service.redis.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import pers.husen.highdsa.common.aop.BaseSpringAspect;
import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc redis append操作切面通知
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月5日 上午8:35:26
 * 
 * @Version 1.0.0
 */
@Aspect
public class RedisAppendOperationAdvice extends BaseSpringAspect{
	private static final Logger logger = LogManager.getLogger(RedisAppendOperationAdvice.class.getName());
	
	@Override
	@Pointcut("execution(* pers.husen.highdsa.service.redis.RedisOperationImpl.append*(..))")
	protected void aspectJMethod() {
	}

	@Override
	@Before("aspectJMethod()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			String signatureMap = getFieldsNameValueString(joinPoint);

			logger.info("redis cache [append]: {}", signatureMap);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}

	@Override
	@AfterReturning(value = "aspectJMethod()", returning = "returnValue")
	public void doReturn(JoinPoint joinPoint, Object returnValue) {
		String methodName = getAimMethodName(joinPoint);
		logger.info("<{}> reply: {}", methodName, returnValue);
	}
	
	@Override
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		return null;
	}
	
	@Override
	public void doAfter(JoinPoint joinPoint) {
		
	}

	@Override
	public void doThrowing(JoinPoint joinPoint, Exception e) {
	}
}