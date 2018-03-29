package pers.husen.highdsa.service.mybatis;

import pers.husen.highdsa.common.entity.po.shiro.SysRole;

/**
 * @Desc 系统角色管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:21:03
 * 
 * @Version 1.0.0
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
	 * 删除角色
	 * 
	 * @param roleId
	 */
	public void deleteSysRole(Long roleId);

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