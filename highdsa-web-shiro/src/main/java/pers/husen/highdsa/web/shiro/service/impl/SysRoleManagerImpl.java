package pers.husen.highdsa.web.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.shiro.SysRole;
import pers.husen.highdsa.common.entity.po.shiro.SysRolePermission;
import pers.husen.highdsa.web.shiro.dao.SysRoleMapper;
import pers.husen.highdsa.web.shiro.dao.SysRolePermissionMapper;
import pers.husen.highdsa.web.shiro.service.SysRoleManager;

/**
 * @Desc 系统角色管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午10:10:57
 * 
 * @Version 1.0.0
 */
@Service
public class SysRoleManagerImpl implements SysRoleManager {
	@Autowired
	private SysRoleMapper roleDao;
	
	@Autowired
	private SysRolePermissionMapper rolePermissionMapper;

	public int createSysRole(SysRole role) {
		return roleDao.insert(role);
	}

	public void deleteSysRole(Long roleId) {
		roleDao.deleteByPrimaryKey(roleId);
	}

	/**
	 * 添加角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		for (Long permissionId : permissionIds) {
			rolePermissionMapper.insert(new SysRolePermission(roleId, permissionId));
		}
	}

	/**
	 * 移除角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		for (Long permissionId : permissionIds) {
			rolePermissionMapper.deleteByPrimaryKey(roleId, permissionId);
		}
	}
}