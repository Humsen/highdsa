package pers.husen.highdsa.shiro.config.customer.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.customer.CustPermission;
import pers.husen.highdsa.shiro.config.customer.controller.handler.PermissionSvc;

/**
 * @Desc 权限控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:05:32
 * 
 * @Version 1.0.2
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
	public CustPermission addPermission(CustPermission custPermission) {
		return permissionSvc.addPermission(custPermission);
	}

	@RequiresPermissions("perm:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deletePermission(Long permissionId) {
		permissionSvc.deletePermission(permissionId);
	}

	@RequiresPermissions("perm:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMorePerms(Long... permissionIds) {
		permissionSvc.deleteMorePerms(permissionIds);
	}

	@RequiresPermissions("perm:update")
	@RequestMapping("/getperm")
	@ResponseBody
	public CustPermission getPermById(Long permissionId) {
		return permissionSvc.getPermById(permissionId);
	}

	@RequiresPermissions("perm:update")
	@RequestMapping("/update")
	@ResponseBody
	public void updatePermission(CustPermission custPermission) {
		permissionSvc.updatePermission(custPermission);
	}
}