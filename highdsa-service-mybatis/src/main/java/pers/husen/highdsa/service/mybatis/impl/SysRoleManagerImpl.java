package pers.husen.highdsa.service.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysRolePermission;
import pers.husen.highdsa.service.mybatis.SysRoleManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRolePermissionMapper;

/**
 * @Desc 系统角色管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午10:10:57
 * 
 * @Version 1.0.0
 */
@Service("sysRoleManager")
public class SysRoleManagerImpl implements SysRoleManager {
	@Autowired
	private SysRoleMapper roleDao;

	@Autowired
	private SysRolePermissionMapper rolePermissionMapper;

	@Override
	public int createSysRole(SysRole role) {
		return roleDao.insert(role);
	}

	@Override
	public void deleteSysRole(Long roleId) {
		roleDao.deleteByPrimaryKey(roleId);
	}

	/**
	 * 添加角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
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
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		for (Long permissionId : permissionIds) {
			rolePermissionMapper.deleteByPrimaryKey(roleId, permissionId);
		}
	}
}