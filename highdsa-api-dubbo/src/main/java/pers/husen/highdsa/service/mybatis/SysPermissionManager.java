package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.SysPermission;

/**
 * @Desc 系统权限管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:21:50
 * 
 * @Version 1.0.2
 */
public interface SysPermissionManager {
	/**
	 * 创建权限
	 * 
	 * @param sysPermission
	 * @return
	 */
	public SysPermission createPermission(SysPermission sysPermission);

	/**
	 * 根据权限id查找权限
	 * 
	 * @param permissionId
	 * @return
	 */
	SysPermission findSysPermissionById(Long permissionId);

	/**
	 * 根据角色id查找权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<SysPermission> findPermissionsByRoleId(Long roleId);

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	List<SysPermission> findAllPermissions();

	/**
	 * 根据权限id更新权限
	 * 
	 * @param sysPermission
	 */
	void updatePermissionByPrimaryKey(SysPermission sysPermission);

	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	public void deletePermission(Long permissionId);

	/**
	 * 根据权限id删除权限
	 * 
	 * @param permissionsIds
	 */
	void deleteMorePermissions(Long... permissionsIds);
}