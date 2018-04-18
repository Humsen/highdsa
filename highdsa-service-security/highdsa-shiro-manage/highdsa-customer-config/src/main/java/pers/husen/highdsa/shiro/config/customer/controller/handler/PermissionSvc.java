package pers.husen.highdsa.shiro.config.customer.controller.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysPermission;
import pers.husen.highdsa.service.mybatis.SysPermissionManager;

/**
 * @Desc 权限服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:26:41
 * 
 * @Version 1.0.0
 */
@Service
public class PermissionSvc {
	@Autowired
	private SysPermissionManager sysPermissionManager;

	public ModelAndView showRoleList() {
		List<SysPermission> list = sysPermissionManager.getAllPermissions();

		ModelAndView mav = new ModelAndView("permission-list");
		mav.addObject("perms", list);

		return mav;
	}

	public SysPermission addPermission(SysPermission permission) {
		SysPermission sysPermission = sysPermissionManager.createPermission(permission);

		return sysPermission;
	}

	public void deletePermission(Long permId) {
		sysPermissionManager.deletePermission(permId);
	}

	public void deleteMorePerms(Long... permIds) {
		sysPermissionManager.deleteMorePermissions(permIds);
	}

	public SysPermission getPermById(Long permId) {
		return sysPermissionManager.findSysPermissionById(permId);
	}

	public void updatePermission(SysPermission permission) {
		sysPermissionManager.updatePermissionByPrimaryKey(permission);
	}
}