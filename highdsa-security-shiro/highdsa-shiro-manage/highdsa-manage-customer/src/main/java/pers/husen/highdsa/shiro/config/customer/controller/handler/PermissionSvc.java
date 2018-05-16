package pers.husen.highdsa.shiro.config.customer.controller.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.customer.CustPermission;
import pers.husen.highdsa.service.mybatis.CustPermissionManager;

/**
 * @Desc 权限服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:26:41
 * 
 * @Version 1.0.2
 */
@Service
public class PermissionSvc {
	@Autowired
	private CustPermissionManager custPermissionManager;

	public ModelAndView showRoleList() {
		List<CustPermission> list = custPermissionManager.findAllPermissions();

		ModelAndView mav = new ModelAndView("permission-list");
		mav.addObject("perms", list);

		return mav;
	}

	public CustPermission addPermission(CustPermission custPermission) {
		CustPermission permission = custPermissionManager.createPermission(custPermission);

		return permission;
	}

	public void deletePermission(Long permissionId) {
		custPermissionManager.deletePermission(permissionId);
	}

	public void deleteMorePerms(Long... permissionIds) {
		custPermissionManager.deleteMorePermissions(permissionIds);
	}

	public CustPermission getPermById(Long permissionId) {
		return custPermissionManager.findSysPermissionById(permissionId);
	}

	public void updatePermission(CustPermission custPermission) {
		custPermissionManager.modifyPermissionByPrimaryKey(custPermission);
	}
}