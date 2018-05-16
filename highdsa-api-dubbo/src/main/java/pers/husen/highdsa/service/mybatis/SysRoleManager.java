package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysUser;

/**
 * @Desc 系统角色管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:21:03
 * 
 * @Version 1.0.2
 */
public interface SysRoleManager {
	/**
	 * 创建角色
	 * 
	 * @param sysRole
	 * @return
	 */
	public int createSysRole(SysRole sysRole);

	/**
	 * 添加角色
	 * 
	 * @param sysRole
	 * @param permissionIds
	 * @return
	 */
	SysRole addRolePermissions(SysRole sysRole, Long... permissionIds);

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
	SysRole findRoleById(Long roleId);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	SysUser findRolesByUserName(String userName);

	/**
	 * 获取所有角色
	 * 
	 * @return
	 */
	List<SysRole> findAllRoles();

	/**
	 * 更新角色和权限关联
	 * 
	 * @param role
	 * @param permIds
	 */
	void modifyRolePermissions(SysRole role, Long... permIds);

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