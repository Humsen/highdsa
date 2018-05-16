package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustPermission;

/**
 * @Desc 客户权限管理, dubbo api函数名称和 mybatis数据查询的对应关系为： create -> insert, find ->
 *       select, modify -> update, delete -> delete
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:15:18
 * 
 * @Version 1.0.1
 */
public interface CustPermissionManager {
	/**
	 * 创建权限
	 * 
	 * @param custPermission
	 * @return
	 */
	public CustPermission createPermission(CustPermission custPermission);

	/**
	 * 根据权限id查找权限
	 * 
	 * @param permissionId
	 * @return
	 */
	CustPermission findSysPermissionById(Long permissionId);

	/**
	 * 根据角色id查找权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<CustPermission> findPermissionsByRoleId(Long roleId);

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	List<CustPermission> findAllPermissions();

	/**
	 * 根据权限id更新权限
	 * 
	 * @param custPermission
	 */
	void modifyPermissionByPrimaryKey(CustPermission custPermission);

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