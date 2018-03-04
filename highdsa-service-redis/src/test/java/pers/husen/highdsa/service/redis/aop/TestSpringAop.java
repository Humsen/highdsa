package pers.husen.highdsa.service.redis.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.husen.highdsa.service.redis.RedisOperationImpl;

/**
 * @Desc 测试Spring AOP
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月4日 下午6:30:38
 * 
 * @Version 1.0.0
 */
public class TestSpringAop {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		RedisOperationImpl redisOperationImpl = (RedisOperationImpl) context.getBean("redisOperationImpl");
		redisOperationImpl.set("aopkey", "aopvalue");
		redisOperationImpl.get("aopkey");
		context.close();
	}
}