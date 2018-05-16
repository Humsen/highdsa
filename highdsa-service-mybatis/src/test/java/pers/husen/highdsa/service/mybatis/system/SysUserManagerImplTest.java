package pers.husen.highdsa.service.mybatis.system;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.enums.SysUserState;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl;

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
public class SysUserManagerImplTest extends AbstractJUnit4SpringContextTests {
	private SysUserManagerImpl sysUserManagerImpl;

	@Before
	public void before() {
		sysUserManagerImpl = (SysUserManagerImpl) applicationContext.getBean("sysUserManager");
		System.out.println("=============== dubbo已经启动... ==================");
	}

	@Test
	public void selectById() {
		SysUser sysUser = sysUserManagerImpl.findUserByUserId(1000L);
		System.out.println(sysUser);
	}

	@Test
	public void selectAll() {
		List<SysUser> sysUsers = sysUserManagerImpl.findAllUsers();
		System.out.println(sysUsers);
	}

	@Test
	public void insertUser() {
		SysUser sysUser = new SysUser();
		sysUser.setUserId(1006L);
		sysUser.setUserName("测试1");
		sysUser.setUserPassword("123");
		sysUser.setUserPwdSalt("123");
		sysUser.setUserState(SysUserState.VALID);

		sysUserManagerImpl.createUser(sysUser);
	}

	@Test
	public void updateUser() {
		SysUser sysUser = new SysUser();
		sysUser.setUserId(1006L);
		sysUser.setUserName("测试1");
		sysUser.setUserPassword("123");
		sysUser.setUserPwdSalt("123");
		sysUser.setUserState(SysUserState.INVALID);

		sysUserManagerImpl.modifyByUserId(sysUser);
	}

	@Test
	public void deleteUser() {
		sysUserManagerImpl.deleteUser(1005L);
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