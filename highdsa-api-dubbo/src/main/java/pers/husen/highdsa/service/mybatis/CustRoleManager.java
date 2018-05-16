package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustUser;

/**
 * @Desc 客户角色管理, dubbo api函数名称和 mybatis数据查询的对应关系为： create -> insert, find ->
 *       select, modify -> update, delete -> delete
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:15:39
 * 
 * @Version 1.0.1
 */
public interface CustRoleManager {
	/**
	 * 创建角色
	 * 
	 * @param custRole
	 * @return
	 */
	public int createRole(CustRole custRole);

	/**
	 * 添加角色
	 * 
	 * @param custRole
	 * @param permissionIds
	 * @return
	 */
	CustRole addRolePermissions(CustRole custRole, Long... permissionIds);

	/**
	 * 增加角色权限关联
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	void addRolePermissions(Long roleId, Long... permissionIds);

	/**
	 * 根据角色id查找角色
	 * 
	 * @param roleId
	 * @return
	 */
	CustRole findRoleById(Long roleId);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser findRolesByUserName(String userName);

	/**
	 * 获取所有角色
	 * 
	 * @return
	 */
	List<CustRole> findAllRoles();

	/**
	 * 更新角色和权限关联
	 * 
	 * @param custRole
	 * @param permissionIds
	 */
	void modifyRolePermissions(CustRole custRole, Long... permissionIds);

	/**
	 * 根据id删除
	 * 
	 * @param roleId
	 */
	void deleteRole(Long roleId);

	/**
	 * 删除一组角色
	 * 
	 * @param roleIds
	 */
	void deleteMoreRoles(Long... roleIds);

	/**
	 * 添加角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/**
	 * 移除角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}