package pers.husen.highdsa.web.shiro.service;

import pers.husen.highdsa.common.entity.po.shiro.SysPermission;

/**
 * @Desc 系统权限管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:21:50
 * 
 * @Version 1.0.0
 */
public interface SysPermissionManager {
	/**
	 * 创建权限
	 * 
	 * @param permission
	 * @return
	 */
	public int createPermission(SysPermission permission);

	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	public void deletePermission(Long permissionId);
}