package pers.husen.highdsa.web.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.shiro.SysPermission;
import pers.husen.highdsa.web.shiro.dao.SysPermissionMapper;
import pers.husen.highdsa.web.shiro.service.SysPermissionManager;

/**
 * @Desc 系统权限管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午10:18:13
 * 
 * @Version 1.0.0
 */
@Service
public class SysPermissionManagerImpl implements SysPermissionManager {

	@Autowired
	private SysPermissionMapper permissionDao;

	public int createPermission(SysPermission permission) {
		return permissionDao.insert(permission);
	}

	public void deletePermission(Long permissionId) {
		permissionDao.deleteByPrimaryKey(permissionId);
	}
}