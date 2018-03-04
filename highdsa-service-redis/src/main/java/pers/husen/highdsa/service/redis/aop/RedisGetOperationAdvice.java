package pers.husen.highdsa.service.redis.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import pers.husen.highdsa.common.aop.BaseSpringAspect;

/**
 * @Desc redis get操作切面通知
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月4日 下午10:06:51
 * 
 * @Version 1.0.0
 */
@Aspect
public class RedisGetOperationAdvice extends BaseSpringAspect {
	private static final Logger logger = LogManager.getLogger(RedisGetOperationAdvice.class.getName());

	@Override
	@Pointcut("execution(* pers.husen.highdsa.service.redis.RedisOperationImpl.get*(..))")
	protected void aspectJMethod() {
	};

	@Override
	@Around("aspectJMethod()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

		Object key = getArgs(joinPoint)[0];
		String methodName = getAimMethodName(joinPoint);
		// 核心逻辑
		Object retval = joinPoint.proceed();

		logger.info("<{}> redis cache get success, key={},value={}", methodName, key, retval);

		return retval;
	}

	@Override
	public void doBefore(JoinPoint joinPoint) {
	}

	@Override
	public void doAfter(JoinPoint joinPoint) {
	}

	@Override
	public void doReturn(JoinPoint joinPoint, String retval) {

	}

	@Override
	public void doThrowing(JoinPoint joinPoint, Exception e) {
	}
}