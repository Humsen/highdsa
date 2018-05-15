package pers.husen.highdsa.web.shiro;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.app.handler.LoginSvc;

/**
 * @Desc 测试shiro服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月2日 下午11:16:09
 * 
 * @Version 1.0.0
 */
public class TestShiroRestful {
	public static void main(String[] args) {
		String[] configLocation = new String[] { "classpath:spring/spring-context.xml", "classpath:spring/shiro-consumer.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();

		System.out.println("=============== shiro消费者已经启动... ==================");

		LoginSvc shiroSvc = (LoginSvc) context.getBean("shiroSvc");

		Serializable sessionId = null;
		try {
			sessionId = shiroSvc.loginWithName("highdsa", "highdsa");
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		System.out.println("登录结果：" + sessionId);

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.close();
	}
}