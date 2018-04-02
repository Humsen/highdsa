package pers.husen.highdsa.web.shiro;

import java.io.IOException;
import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;
import pers.husen.highdsa.service.shiro.ShiroService;

/**
 * @Desc 测试shiro服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月2日 下午11:16:09
 * 
 * @Version 1.0.0
 */
public class TestShiroService {
	public static void main(String[] args) {
		String[] configLocation = new String[] { "classpath:spring/spring-context.xml", "classpath:spring/shiro-consumer.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();

		System.out.println("=============== shiro消费者已经启动... ==================");

		ShiroService shiroService = (ShiroService) context.getBean("shiroService");

		UsernamePasswordToken token = new UsernamePasswordToken("user", "1234");
		Serializable sessionId = shiroService.login(token);
		System.out.println("登录结果：" + sessionId);

		Session session = shiroService.getSession(sessionId);
		System.out.println("session：" + session);

		SysUser sysUser = shiroService.getPermissions("user");
		System.out.println("所有权限：" + sysUser);

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.close();
	}
}