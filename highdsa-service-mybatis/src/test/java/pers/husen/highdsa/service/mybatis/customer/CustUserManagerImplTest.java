package pers.husen.highdsa.service.mybatis.customer;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.enums.CustUserState;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.service.mybatis.impl.CustUserManagerImpl;

/**
 * @Desc 系统用户测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月27日 下午2:44:29
 * 
 * @Version 1.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/dubbo-service-mybatis.xml", "classpath:spring/spring-context.xml" })
public class CustUserManagerImplTest extends AbstractJUnit4SpringContextTests {
	private CustUserManagerImpl custUserManagerImpl;

	@Before
	public void before() {
		custUserManagerImpl = (CustUserManagerImpl) applicationContext.getBean("custUserManager");
		System.out.println("=============== dubbo已经启动... ==================");
	}

	@Test
	public void selectById() {
		CustUser custUser = custUserManagerImpl.findUserByUserId(1000L);
		System.out.println(custUser);
	}

	@Test
	public void selectAll() {
		List<CustUser> custUsers = custUserManagerImpl.findAllUsers();
		System.out.println(custUsers);
	}

	@Test
	public void insertUser() {
		CustUser custUser = new CustUser();
		custUser.setUserId(1006L);
		custUser.setUserName("测试1");
		custUser.setUserPassword("123");
		custUser.setUserPwdSalt("123");
		custUser.setUserState(CustUserState.VALID);

		custUserManagerImpl.createUser(custUser);
	}

	@Test
	public void updateUser() {
		CustUser custUser = new CustUser();
		custUser.setUserId(1006L);
		custUser.setUserName("测试1");
		custUser.setUserPassword("123");
		custUser.setUserPwdSalt("123");
		custUser.setUserState(CustUserState.INVALID);

		custUserManagerImpl.modifyUserByUserId(custUser);
	}

	@Test
	public void deleteUser() {
		custUserManagerImpl.deleteUser(1006L);
	}

	@After
	public void after() {
		try {
			System.out.println("=============== 阻塞开始... ==================");
			// 为保证服务一直开着,利用输入流的阻塞来模拟
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("=============== 消费者关闭... ==================");
	}
}