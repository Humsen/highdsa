package pers.husen.highdsa.service.mybatis.system;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.po.system.SysUserInfo;
import pers.husen.highdsa.service.mybatis.impl.SysUserInfoManagerImpl;

/**
 * @Desc 测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午4:36:51
 * 
 * @Version 1.0.5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/dubbo-service-mybatis.xml", "classpath:spring/spring-context.xml" })
public class SysUserInfoManagerImplTest extends AbstractJUnit4SpringContextTests {
	private SysUserInfoManagerImpl sysUserInfoManagerImpl;

	@Before
	public void before() {
		sysUserInfoManagerImpl = (SysUserInfoManagerImpl) applicationContext.getBean("sysUserInfoManager");
		System.out.println("=============== dubbo已经启动... ==================");
	}

	@Test
	public void selectById() {
		SysUserInfo sysUserInfo = sysUserInfoManagerImpl.findByUserId(1000L);
		System.out.println(sysUserInfo);
	}

	@Test
	public void selectAll() {
		List<SysUserInfo> sysUserInfos = sysUserInfoManagerImpl.findAll();
		System.out.println(sysUserInfos);
	}

	@Test
	public void insertUserInfo() {
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setUserId(1005L);
		sysUserInfo.setUserNickName("哈哈");
		sysUserInfo.setUserRegisterTime(new Date());
		sysUserInfo.setUserLastLoginTime(new Date());

		sysUserInfoManagerImpl.createUserInfo(sysUserInfo);
	}

	@Test
	public void updateUserInfo() {
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setUserId(1005L);
		sysUserInfo.setUserNickName("更新用户信息");
		sysUserInfo.setUserRegisterTime(new Date());
		sysUserInfo.setUserLastLoginTime(new Date());

		sysUserInfoManagerImpl.updateUserInfo(sysUserInfo);
	}

	@Test
	public void deleteUserInfo() {
		sysUserInfoManagerImpl.deleteUserInfo(1005L);
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