package pers.husen.highdsa.shiro.config.customer.controller.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.service.mybatis.SysPermissionManager;
import pers.husen.highdsa.service.mybatis.SysRoleManager;

/**
 * @Desc 角色服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:26:55
 * 
 * @Version 1.0.0
 */
@Service
public class RoleSvc {
	@Autowired
	private SysRoleManager sysRoleManager;
	@Autowired
	private SysPermissionManager sysPermissionManager;

	public ModelAndView showRoleList() {
		List<?> list = sysRoleManager.findAllRoles();

		ModelAndView mav = new ModelAndView("role-list");
		mav.addObject("roles", list);
		return mav;
	}

	public List<?> getPerms() {
		return sysPermissionManager.getAllPermissions();
	}

	public SysRole addRole(SysRole role, Long... permIds) {
		SysRole sysRole = sysRoleManager.addRole(role, permIds);

		return sysRole;
	}

	public void deleteRole(Long roleId) {
		sysRoleManager.deleteRole(roleId);
	}

	public void deleteMoreRoles(Long... roleIds) {
		sysRoleManager.deleteMoreRoles(roleIds);
	}

	public List<?> showRolePerms(Long roleId) {
		List<?> perms = sysPermissionManager.findPermissionsByRoleId(roleId);

		return perms;
	}

	public SysRole getRoleById(Long roleId) {
		return sysRoleManager.findRoleById(roleId);
	}

	public void updateRole(SysRole role, Long... permIds) {
		sysRoleManager.updateRole(role, permIds);
	}
}