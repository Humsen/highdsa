package pers.husen.highdsa.service.shiro.mybatis;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;
import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 测试用户管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午12:11:59
 * 
 * @Version 1.0.3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-shiro.xml", "classpath:spring/system-consumer.xml" })
public class SysUserManagerTest {

	@Autowired
	public SysUserManager sysUserManager;

	/**
	 * 创建角色之前必须建立用户-角色关系
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#createUser(pers.husen.highdsa.web.shiro.po.SysUser)}.
	 */
	@Test
	public void testCreateUser() {
		SysUser sysUser = new SysUser(1003L, "husen", "123456", "123@123.com", "18626422426", "abc4444", "100");

		int reply = sysUserManager.createUser(sysUser);

		System.out.println("创建 reply:" + reply);
	}

	/**
	 * 修改密码
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#modifyPassword(java.lang.Long, java.lang.String)}.
	 */
	@Test
	public void testModifyPassword() {
		sysUserManager.modifyPassword(1002L, "654321");
	}

	/**
	 * 建立对象-角色关系
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#correlationRoles(java.lang.Integer, java.lang.Integer[])}.
	 */
	@Test
	public void testCorrelationRoles() {
		sysUserManager.correlationRoles(1004L, 1002L);
	}

	/**
	 * 取消对象-角色关系之前必须删除用户
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#uncorrelationRoles(java.lang.Integer, java.lang.Integer[])}.
	 */
	@Test
	public void testUncorrelationRoles() {
		sysUserManager.uncorrelationRoles(1004L, 1002L);
	}

	/**
	 * Test method for
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#findByUserName(java.lang.String)}.
	 */
	@Test
	public void testFindByUserName() {
		SysUser reply = sysUserManager.findByUserName("super_admin");

		System.out.println(reply);
	}

	/**
	 * 根据用户名查询结果集
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#findRoles(java.lang.String)}.
	 */
	@Test
	public void testFindRoles() {
		Set<SysUser> reply = sysUserManager.findRoles("admin");

		System.out.println("查询角色结果：" + reply);
	}

	/**
	 * 根据用户名查询权限集
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl#findPermissions(java.lang.String)}.
	 */
	@Test
	public void testFindPermissions() {
		Set<SysUser> reply = sysUserManager.findPermissions("super_admin");

		System.out.println("查询权限结果：" + reply);
	}
}