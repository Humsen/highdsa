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
 * @Version 1.0.1
 */
public class RegisterMybatisService {
	public static void main(String[] args) {
		String[] configLocation = new String[] { "spring/dubbo-mybatis.xml", "spring/spring-context.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		context.start();

		// SysUserInfoManagerImpl sysUserInfoManagerImpl = (SysUserInfoManagerImpl)
		// context.getBean("sysUserInfoManager");
		System.out.println("=============== mybatis已经启动... ==================");

		// SysUserInfo sysUserInfo = sysUserInfoManagerImpl.selectById(1000L);
		// System.out.println(sysUserInfo);

		System.out.println("=============== 阻塞开始... ==================");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.close();
	}
}