package pers.husen.highdsa.web.shiro.spring;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.service.mybatis.SysUserManager;
import pers.husen.highdsa.web.shiro.realm.SystemUserRealm;

/**
 * @Desc 结合spring测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月28日 上午11:03:01
 * 
 * @Version 1.0.3
 */
@Service
public class ShiroTest {

	@Autowired
	private SysUserManager sysUserManager;

	@Autowired
	private SystemUserRealm systemUserRealm;

	public static void main(String[] args) {
		String[] configLocation = new String[] { "classpath:spring/spring-context.xml", "classpath:spring/spring-shiro.xml", "classpath:spring/system-consumer.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();

		System.out.println("=============== redis服务已经启动... ==================");

		ShiroTest shiroTest = (ShiroTest) context.getBean("shiroTest");
		shiroTest.testShiroRedisCache();

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.close();
	}

	public void testShiroRedisCache() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("user", "123");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());
		System.out.println("开始检查角色");
		subject.checkRole("普通用户");
		subject.checkPermission("sys:user:view");

		sysUserManager.modifyPassword(1002L, "123");
		systemUserRealm.clearCache(subject.getPrincipals());

		token = new UsernamePasswordToken("user", "123");
		subject.login(token);

		System.out.println("=============== 阻塞开始... ==================");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}