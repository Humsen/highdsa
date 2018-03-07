package pers.husen.highdsa.service.mybatis;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.husen.highdsa.service.mybatis.cache.RedisCache;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc 测试redis服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午1:03:55
 * 
 * @Version 1.0.0
 */
public class RegisterMybatisConsumer {
	public static void main(String[] args) throws IOException {
		String[] configLocation = new String[] { "dubbo/mybatis-consumer.xml", "spring/spring-context.xml" };
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
		System.out.println("消费者已经启动...");
		
		RedisOperation redisOperation = (RedisOperation) context.getBean("redisOperation");
		System.out.println(redisOperation.getDbSize());
		
		RedisCache redisCache = (RedisCache) context.getBean("redisCache");
		System.out.println(redisCache.getSize());
		
		System.in.read();
		context.close();
	}
}