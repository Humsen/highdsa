package pers.husen.highdsa.web.shiro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.po.shiro.SysRole;
import pers.husen.highdsa.web.shiro.service.SysRoleManager;

/**
 * @Desc 测试角色管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午1:26:15
 * 
 * @Version 1.0.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class SysRoleManagerTest {

	@Autowired
	private SysRoleManager sysRoleManager;

	/**
	 * 创建角色之前必须先创建角色-权限关系
	 * {@link pers.husen.highdsa.web.shiro.service.impl.SysRoleManagerImpl#createSysRole(pers.husen.highdsa.web.shiro.po.SysRole)}.
	 */
	@Test
	public void testCreateSysRole() {
		SysRole sysRole = new SysRole(1004L, "员工", "普通用户", true);

		sysRoleManager.createSysRole(sysRole);
	}

	/**
	 * 删除角色
	 * {@link pers.husen.highdsa.web.shiro.service.impl.SysRoleManagerImpl#deleteSysRole(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteSysRole() {
		sysRoleManager.deleteSysRole(1004L);
	}

	/**
	 * 创建角色-权限关系
	 * {@link pers.husen.highdsa.web.shiro.service.impl.SysRoleManagerImpl#correlationPermissions(java.lang.Integer, java.lang.Integer[])}.
	 */
	@Test
	public void testCorrelationPermissions() {
		sysRoleManager.correlationPermissions(1004L, 1003L);
	}

	/**
	 * 取消角色-权限关系之前必须先删除相应角色
	 * {@link pers.husen.highdsa.web.shiro.service.impl.SysRoleManagerImpl#uncorrelationPermissions(java.lang.Integer, java.lang.Integer[])}.
	 */
	@Test
	public void testUncorrelationPermissions() {
		sysRoleManager.uncorrelationPermissions(1004L, 1003L);
	}
}