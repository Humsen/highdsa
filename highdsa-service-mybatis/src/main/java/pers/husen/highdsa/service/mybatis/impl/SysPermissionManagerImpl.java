package pers.husen.highdsa.service.mybatis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.shiro.SysPermission;
import pers.husen.highdsa.service.mybatis.SysPermissionManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysPermissionMapper;

/**
 * @Desc 系统权限管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午10:18:13
 * 
 * @Version 1.0.1
 */
@Service("sysPermissionManager")
public class SysPermissionManagerImpl implements SysPermissionManager {

	@Autowired
	@Qualifier("sysPermissionMapper")
	private SysPermissionMapper permissionMapper;

	@Override
	public int createPermission(SysPermission permission) {
		return permissionMapper.insert(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionMapper.deleteByPrimaryKey(permissionId);
	}
}