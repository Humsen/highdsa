package pers.husen.highdsa.shiro.config.customer.controller.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.service.mybatis.CustPermissionManager;
import pers.husen.highdsa.service.mybatis.CustRoleManager;

/**
 * @Desc 角色服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:26:55
 * 
 * @Version 1.0.3
 */
@Service
public class RoleSvc {
	@Autowired
	private CustRoleManager custRoleManager;
	@Autowired
	private CustPermissionManager custPermissionManager;

	public ModelAndView showRoleList() {
		List<?> list = custRoleManager.findAllRoles();

		ModelAndView mav = new ModelAndView("role-list");
		mav.addObject("roles", list);
		return mav;
	}

	public List<?> getPerms() {
		return custPermissionManager.findAllPermissions();
	}

	public CustRole addRole(CustRole custRole, Long... permissionIds) {
		CustRole role = custRoleManager.addRolePermissions(custRole, permissionIds);

		return role;
	}

	public void deleteRole(Long roleId) {
		custRoleManager.deleteRole(roleId);
	}

	public void deleteMoreRoles(Long... roleIds) {
		custRoleManager.deleteMoreRoles(roleIds);
	}

	public List<?> showRolePerms(Long roleId) {
		List<?> perms = custPermissionManager.findPermissionsByRoleId(roleId);

		return perms;
	}

	public CustRole getRoleById(Long roleId) {
		return custRoleManager.findRoleById(roleId);
	}

	public void updateRole(CustRole custRole, Long... permissionIds) {
		custRoleManager.modifyRolePermissions(custRole, permissionIds);
	}
}