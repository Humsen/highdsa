package pers.husen.highdsa.service.shiro;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Desc 注册shiro服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月2日 下午11:08:06
 * 
 * @Version 1.0.0
 */
public class RegisterShiroService {
	public static void main(String[] args) {
		String[] configLocation = new String[] { "classpath:spring/spring-context.xml", "classpath:spring/spring-shiro.xml", "classpath:spring/system-consumer.xml",
				"classpath:spring/shiro-provider.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();

		System.out.println("=============== shiro服务已经启动... ==================");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.close();
	}
}