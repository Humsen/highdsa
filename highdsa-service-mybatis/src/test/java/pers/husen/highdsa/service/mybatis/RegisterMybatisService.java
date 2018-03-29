package pers.husen.highdsa.service.mybatis;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Desc 注册mybatis服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午10:13:28
 * 
 * @Version 1.0.0
 */
public class RegisterMybatisService {
	public static void main(String[] args) {
		String[] configLocation = new String[] { "spring/mybatis-provider.xml", "spring/redis-consumer.xml", "spring/spring-context.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
		// UserInfoDbOperImpl userInfoDbOperImpl = (UserInfoDbOperImpl)
		// context.getBean("userInfoDbOper");
		System.out.println("=============== dubbo已经启动... ==================");

		// UserInfo userInfo = userInfoDbOperImpl.selectById(1);
		// System.out.println(userInfo);

		System.out.println("=============== 阻塞开始... ==================");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.close();
	}
}