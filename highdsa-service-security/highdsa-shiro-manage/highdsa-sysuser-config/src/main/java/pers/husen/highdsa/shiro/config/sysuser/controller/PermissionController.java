package pers.husen.highdsa.shiro.config.sysuser.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysPermission;
import pers.husen.highdsa.shiro.config.sysuser.service.PermissionService;

/**
 * @Desc 权限控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:05:32
 * 
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/perm")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("perm:list")
	@RequestMapping("/list")
	public ModelAndView showRoleList() {
		List<SysPermission> list = permissionService.getAllPermissions();

		ModelAndView mav = new ModelAndView("permission-list");
		mav.addObject("perms", list);

		return mav;
	}

	@RequiresPermissions("perm:add")
	@RequestMapping("/add")
	@ResponseBody
	public SysPermission addPermission(SysPermission permission) {
		permissionService.addPermission(permission);
		return permission;
	}

	@RequiresPermissions("perm:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deletePermission(Long permId) {
		permissionService.deletePermission(permId);
	}

	@RequiresPermissions("perm:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMorePerms(Long... permIds) {
		permissionService.deleteMorePermissions(permIds);
	}

	@RequiresPermissions("perm:update")
	@RequestMapping("/getperm")
	@ResponseBody
	public SysPermission getPermById(Long permId) {
		return permissionService.findById(permId);
	}

	@RequiresPermissions("perm:update")
	@RequestMapping("/update")
	@ResponseBody
	public void updatePermission(SysPermission permission) {
		permissionService.updatePermission(permission);
	}
}