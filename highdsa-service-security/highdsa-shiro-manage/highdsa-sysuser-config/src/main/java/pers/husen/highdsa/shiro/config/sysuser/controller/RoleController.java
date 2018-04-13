package pers.husen.highdsa.shiro.config.sysuser.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.shiro.config.sysuser.service.PermissionService;
import pers.husen.highdsa.shiro.config.sysuser.service.RoleService;

/**
 * @Desc 角色控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:05:50
 * 
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("role:list")
	@RequestMapping("/list")
	public ModelAndView showRoleList() {
		List<?> list = roleService.getAllRoles();

		ModelAndView mav = new ModelAndView("role-list");
		mav.addObject("roles", list);
		return mav;
	}

	@RequiresPermissions("role:showperms")
	@RequestMapping("/listperms")
	@ResponseBody
	public List<?> getPerms() {
		return permissionService.getAllPermissions();
	}

	@RequiresPermissions("role:add")
	@RequestMapping("/add")
	@ResponseBody
	public SysRole addRole(SysRole role, Long... permIds) {
		roleService.addRole(role, permIds);

		return role;
	}

	@RequiresPermissions("role:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteRole(Long roleId) {
		roleService.deleteRole(roleId);
	}

	@RequiresPermissions("role:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMoreRoles(Long... roleIds) {
		roleService.deleteMoreRoles(roleIds);
	}

	@RequiresPermissions("role:showperms")
	@RequestMapping("/showroleperms")
	@ResponseBody
	public List<?> showRolePerms(Long roleId) {
		return permissionService.getPermissionsByRoleId(roleId);
	}

	@RequiresPermissions("role:findinfo")
	@RequestMapping("/getrole")
	@ResponseBody
	public SysRole getRoleById(Long roleId) {
		return roleService.getRoleById(roleId);
	}

	@RequiresPermissions("role:corelationperm")
	@RequestMapping("/updaterole")
	@ResponseBody()
	public void updateRole(SysRole role, Long... permIds) {
		roleService.updateRole(role, permIds);
	}
}