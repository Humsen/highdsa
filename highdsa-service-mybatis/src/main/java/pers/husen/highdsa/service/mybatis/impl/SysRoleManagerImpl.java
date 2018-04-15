package pers.husen.highdsa.service.mybatis.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysRolePermission;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.service.mybatis.SysRoleManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRolePermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserRoleMapper;

/**
 * @Desc 系统角色管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午10:10:57
 * 
 * @Version 1.0.2
 */
@Service("sysRoleManager")
public class SysRoleManagerImpl implements SysRoleManager {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public int createSysRole(SysRole role) {
		return sysRoleMapper.insert(role);
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
			sysRolePermissionMapper.insert(new SysRolePermission(roleId, permissionId));
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
			sysRolePermissionMapper.deleteByPrimaryKey(roleId, permissionId);
		}
	}

	@Override
	public SysRole addRole(SysRole sysRole, Long... permissionIds) {
		// TODO-设置角色分布式id
		sysRole.setRoleId(10003L);

		// 设置角色有效
		sysRole.setRoleValid(true);

		sysRoleMapper.insert(sysRole);

		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				sysRolePermissionMapper.insert(new SysRolePermission(sysRole.getRoleId(), permissionId));
			}
		}

		return sysRole;
	}

	@Override
	public void deleteRole(Long roleId) {
		sysUserRoleMapper.deleteByRoleId(roleId);
		sysRolePermissionMapper.deleteByRoleId(roleId);
		sysRoleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public void deleteMoreRoles(Long... roleIds) {
		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				deleteRole(roleId);
			}
		}
	}

	@Override
	public SysRole findRoleById(Long roleId) {
		return sysRoleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public SysUser findRolesByUserName(String userName) {
		return sysUserMapper.selectRolesByUserName(userName);
	}

	@Override
	public List<SysRole> findAllRoles() {
		return sysRoleMapper.selectAll();
	}

	@Override
	public void updateRole(SysRole role, Long... permIds) {
		//设置角色有效
		role.setRoleValid(true);
		//设置最后更新时间
		role.setRoleLastModifyTime(new Date());
		
		sysRoleMapper.updateByPrimaryKey(role);
		sysRolePermissionMapper.deleteByRoleId(role.getRoleId());
		addRolePermissions(role.getRoleId(), permIds);
	}

	@Override
	public void addRolePermissions(Long roleId, Long... permissionIds) {
		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				sysRolePermissionMapper.insert(new SysRolePermission(roleId, permissionId));
			}
		}
	}
}