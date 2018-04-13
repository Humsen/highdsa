package pers.husen.highdsa.shiro.config.sysuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysRolePermission;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.service.mybatis.dao.system.SysRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRolePermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserRoleMapper;
import pers.husen.highdsa.shiro.config.sysuser.service.RoleService;

/**
 * @Desc 角色服务实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:48
 * 
 * @Version 1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private SysRolePermissionMapper rolePermissionMapper;

	@Autowired
	private SysUserRoleMapper userRoleMapper;

	@Autowired
	private SysUserMapper userMapper;

	@Override
	public Long addRole(SysRole role, Long... permissionIds) {
		// TODO-设置角色分布式id
		role.setRoleId(10003L);

		// 设置角色有效
		role.setRoleValid(true);

		roleMapper.insert(role);

		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				rolePermissionMapper.insert(new SysRolePermission(role.getRoleId(), permissionId));
			}
		}

		return role.getRoleId();
	}

	@Override
	public void deleteRole(Long roleId) {
		userRoleMapper.deleteByRoleId(roleId);
		rolePermissionMapper.deleteByRoleId(roleId);
		roleMapper.deleteByPrimaryKey(roleId);
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
	public SysRole getRoleById(Long roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public SysUser getRolesByUserName(String userName) {
		return userMapper.selectRolesByUserName(userName);
	}

	@Override
	public List<SysRole> getAllRoles() {
		return roleMapper.selectAll();
	}

	@Override
	public void updateRole(SysRole role, Long... permIds) {
		roleMapper.updateByPrimaryKey(role);
		rolePermissionMapper.deleteByRoleId(role.getRoleId());
		addRolePermissions(role.getRoleId(), permIds);
	}

	@Override
	public void addRolePermissions(Long roleId, Long... permissionIds) {
		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				rolePermissionMapper.insert(new SysRolePermission(roleId, permissionId));
			}
		}
	}
}