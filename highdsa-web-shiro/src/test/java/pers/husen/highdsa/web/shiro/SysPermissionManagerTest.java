package pers.husen.highdsa.web.shiro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.po.shiro.SysPermission;
import pers.husen.highdsa.service.mybatis.SysPermissionManager;

/**
 * @Desc 权限管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午1:26:51
 * 
 * @Version 1.0.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class SysPermissionManagerTest {

	@Autowired
	SysPermissionManager sysPermissionManager;

	/**
	 * 创建权限
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysPermissionManagerImpl#createPermission(pers.husen.highdsa.web.shiro.po.SysPermission)}.
	 */
	@Test
	public void testCreatePermission() {
		SysPermission sysPermission = new SysPermission(1004L, "sys:menu:create", "创建菜单选项", true);

		sysPermissionManager.createPermission(sysPermission);
	}

	/**
	 * 删除权限
	 * {@link pers.husen.highdsa.service.mybatis.impl.SysPermissionManagerImpl#deletePermission(java.lang.Integer)}.
	 */
	@Test
	public void testDeletePermission() {
		sysPermissionManager.deletePermission(1004L);
	}
}