package pers.husen.highdsa.service.redis.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import pers.husen.highdsa.common.aop.BaseSpringAspect;

/**
 * @Desc redis exists操作切面通知
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月5日 上午8:35:51
 * 
 * @Version 1.0.1
 */
@Aspect
@Component
public class RedisExistsOperationAdvice extends BaseSpringAspect {
	private static final Logger logger = LogManager.getLogger(RedisExistsOperationAdvice.class.getName());

	@Override
	@Pointcut("execution(* pers.husen.highdsa.service.redis.RedisOperationImpl.exists*(..))")
	protected void aspectJMethod() {
	}

	@Override
	@Around("aspectJMethod()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object key = getArgs(joinPoint)[0];
		String methodName = getAimMethodName(joinPoint);

		Object retval = joinPoint.proceed();

		logger.info("<{}> redis cache [exists], key={},reply={}", methodName, key, retval);

		return retval;
	}

	@Override
	public void doBefore(JoinPoint joinPoint) {
	}

	@Override
	public void doAfter(JoinPoint joinPoint) {
	}

	@Override
	public void doReturn(JoinPoint joinPoint, Object returnValue) {
	}

	@Override
	public void doThrowing(JoinPoint joinPoint, Exception e) {
	}
}