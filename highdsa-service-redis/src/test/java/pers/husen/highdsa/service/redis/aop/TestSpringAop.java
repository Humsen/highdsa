package pers.husen.highdsa.service.redis.aop;

import java.util.HashMap;
import java.util.Map;

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
		/*redisOperationImpl.set("aop-key", "hello");
		redisOperationImpl.get("aop-key");
		redisOperationImpl.append("aop-key", " world");
		redisOperationImpl.exists("aop-key");
		redisOperationImpl.del("aop-key");*/
		
		Map<String, String> map = new HashMap<>(4);
		map.put("language1", "java");
		map.put("language2", "python");
		map.put("language3", "JQuery");
		
		redisOperationImpl.setMap("language-map", map, 0);
		redisOperationImpl.getMap("language-map");
		redisOperationImpl.removeMap("language-map", "language1");
		context.close();
	}
}