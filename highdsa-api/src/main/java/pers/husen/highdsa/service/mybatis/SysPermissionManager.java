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
 * @Version 1.0.1
 */
public interface SysPermissionManager {
	/**
	 * 创建权限
	 * 
	 * @param permission
	 * @return
	 */
	public SysPermission createPermission(SysPermission sysPermission);

	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	public void deletePermission(Long permissionId);

	/**
	 * 根据权限id删除权限
	 * 
	 * @param permIds
	 */
	void deleteMorePermissions(Long... permissionsIds);

	/**
	 * 根据权限id查找权限
	 * 
	 * @param permId
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
	List<SysPermission> getAllPermissions();

	/**
	 * 根据权限id更新权限
	 * 
	 * @param permission
	 */
	void updatePermissionByPrimaryKey(SysPermission sysPermission);
}