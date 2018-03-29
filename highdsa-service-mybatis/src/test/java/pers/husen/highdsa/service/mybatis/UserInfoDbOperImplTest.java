package pers.husen.highdsa.service.mybatis;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.husen.highdsa.common.entity.po.UserInfo;
import pers.husen.highdsa.service.mybatis.impl.UserInfoDbOperImpl;

/**
 * @Desc 测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午4:36:51
 * 
 * @Version 1.0.0
 */
public class UserInfoDbOperImplTest {
	private UserInfoDbOperImpl userInfoDbOperImpl;
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void before() {
		String[] configLocation = new String[] { "spring/mybatis-provider.xml", "spring/redis-consumer.xml","spring/spring-context.xml" };

		context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
		userInfoDbOperImpl = (UserInfoDbOperImpl) context.getBean("userInfoDbOperImpl");
		System.out.println("=============== dubbo已经启动... ==================");
	}
	
	@Test
	public void selectById() {
		UserInfo userInfo = userInfoDbOperImpl.selectById(1);
		System.out.println(userInfo);
	}

	@Test
	public void selectAll() {
		List<UserInfo> userInfos = userInfoDbOperImpl.selectAll();
		System.out.println(userInfos);
	}

	@Test
	public void insertUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("哈哈");
		userInfo.setUserPassword("qweasd");
		userInfo.setUserCreateDate(new Date());
		
		userInfoDbOperImpl.insertUserInfo(userInfo);
	}

	@Test
	public void updateUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("哈哈sadsa");
		userInfo.setUserPassword("123123");
		userInfo.setUserCreateDate(new Date());
		userInfo.setUserId(3);
		
		userInfoDbOperImpl.updateUserInfo(userInfo);
	}

	@Test
	public void deleteUserInfo() {
		userInfoDbOperImpl.deleteUserInfo(3);
	}
	
	@After
	public void after() {
		try {
			System.out.println("=============== 阻塞开始... ==================");
			// 为保证服务一直开着，利用输入流的阻塞来模拟
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("=============== 消费者关闭... ==================");
		context.close();
	}
}