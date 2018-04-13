package pers.husen.highdsa.shiro.config.sysuser.service;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.SysPermission;

/**
 * @Desc 权限服务接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:08:13
 * 
 * @Version 1.0.0
 */
public interface PermissionService {
	Long addPermission(SysPermission permission);

	void deletePermission(Long permissionId);

	void deleteMorePermissions(Long... permIds);

	SysPermission findById(Long permId);

	List<SysPermission> getPermissionsByRoleId(Long roleId);

	List<SysPermission> getAllPermissions();

	void updatePermission(SysPermission permission);
}