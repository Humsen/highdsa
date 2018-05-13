package pers.husen.highdsa.shiro.config.sysuser.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysPermission;
import pers.husen.highdsa.shiro.config.sysuser.controller.handler.PermissionSvc;

/**
 * @Desc 权限控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:05:32
 * 
 * @Version 1.0.1
 */
@Controller
@RequestMapping("/perm")
public class PermissionController {
	@Autowired
	private PermissionSvc permissionSvc;

	@RequiresPermissions("perm:list")
	@RequestMapping("/list")
	public ModelAndView showRoleList() {
		
		return permissionSvc.showRoleList();
	}

	@RequiresPermissions("perm:add")
	@RequestMapping("/add")
	@ResponseBody
	public SysPermission addPermission(SysPermission permission) {
		return permissionSvc.addPermission(permission);
	}

	@RequiresPermissions("perm:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deletePermission(Long permId) {
		permissionSvc.deletePermission(permId);
	}

	@RequiresPermissions("perm:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMorePerms(Long... permIds) {
		permissionSvc.deleteMorePerms(permIds);
	}

	@RequiresPermissions("perm:update")
	@RequestMapping("/getperm")
	@ResponseBody
	public SysPermission getPermById(Long permId) {
		return permissionSvc.getPermById(permId);
	}

	@RequiresPermissions("perm:update")
	@RequestMapping("/update")
	@ResponseBody
	public void updatePermission(SysPermission permission) {
		permissionSvc.updatePermission(permission);
	}
}