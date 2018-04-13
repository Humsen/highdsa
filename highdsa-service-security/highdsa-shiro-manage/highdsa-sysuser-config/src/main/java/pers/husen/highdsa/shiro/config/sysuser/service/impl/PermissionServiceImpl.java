package pers.husen.highdsa.shiro.config.sysuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysPermission;
import pers.husen.highdsa.service.mybatis.dao.system.SysPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRolePermissionMapper;
import pers.husen.highdsa.shiro.config.sysuser.service.PermissionService;

/**
 * @Desc 权限服务实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:23
 * 
 * @Version 1.0.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private SysPermissionMapper permissionMapper;
	@Autowired
	private SysRolePermissionMapper rolePermissionMapper;

	@Override
	public Long addPermission(SysPermission permission) {
		// TODO-插入分布式id
		permission.setPermissionId(10001L);

		// 设置权限有效
		permission.setPermissionValid(true);

		permissionMapper.insert(permission);
		return permission.getPermissionId();
	}

	@Override
	public void deletePermission(Long permissionId) {
		rolePermissionMapper.deleteByPermissionId(permissionId);
		permissionMapper.deleteByPrimaryKey(permissionId);
	}

	@Override
	public void deleteMorePermissions(Long... permIds) {
		if (permIds != null && permIds.length > 0) {
			for (Long permId : permIds) {
				deletePermission(permId);
			}
		}
	}

	@Override
	public SysPermission findById(Long permId) {
		return permissionMapper.selectByPrimaryKey(permId);
	}

	@Override
	public List<SysPermission> getPermissionsByRoleId(Long roleId) {
		return permissionMapper.findPermissionsByRoleId(roleId);
	}

	@Override
	public List<SysPermission> getAllPermissions() {
		return permissionMapper.selectAll();
	}

	@Override
	public void updatePermission(SysPermission permission) {
		permissionMapper.updateByPrimaryKey(permission);
	}
}