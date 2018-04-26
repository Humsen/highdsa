package pers.husen.highdsa.service.mybatis.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysPermission;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.sequence.SequenceManager;
import pers.husen.highdsa.service.mybatis.SysPermissionManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRolePermissionMapper;

/**
 * @Desc 系统权限管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午10:18:13
 * 
 * @Version 1.0.5
 */
@Service("sysPermissionManager")
public class SysPermissionManagerImpl implements SysPermissionManager {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	@Override
	public SysPermission createPermission(SysPermission sysPermission) {
		//设置权限id
		Long permissionId = SequenceManager.getNextId();
		if (permissionId != null) {
			sysPermission.setPermissionId(permissionId);
		} else {
			throw new NullPointerException("获取的permissionId为空");
		}
		
		// 设置权限有效
		sysPermission.setPermissionValid(true);
		// 如果是否为导航属性为null,说明创建时没有被勾选,设置为false
		if (sysPermission.getPermissionNavi() == null) {
			sysPermission.setPermissionNavi(false);
		}
		// 设置创建时间
		sysPermission.setPermissionCreateTime(new Date());
		// 设置最后更新时间
		sysPermission.setPermissionLastModifyTime(new Date());

		sysPermissionMapper.insert(sysPermission);

		return sysPermission;
	}

	@Override
	public void deletePermission(Long permissionId) {
		sysRolePermissionMapper.deleteByPermissionId(permissionId);
		sysPermissionMapper.deleteByPrimaryKey(permissionId);
	}

	@Override
	public void deleteMorePermissions(Long... permissionIds) {
		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				deletePermission(permissionId);
			}
		}
	}

	@Override
	public SysPermission findSysPermissionById(Long permissionId) {
		return sysPermissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public List<SysPermission> findPermissionsByRoleId(Long roleId) {
		return sysPermissionMapper.findPermissionsByRoleId(roleId);
	}

	@Override
	public List<SysPermission> getAllPermissions() {
		return sysPermissionMapper.selectAll();
	}

	@Override
	public void updatePermissionByPrimaryKey(SysPermission sysPermission) {
		// 如果是否为导航属性为null,说明创建时没有被勾选,设置为false
		if (sysPermission.getPermissionNavi() == null) {
			sysPermission.setPermissionNavi(false);
		}
		// 设置权限有效
		sysPermission.setPermissionValid(true);
		// 设置最后更新时间
		sysPermission.setPermissionLastModifyTime(new Date());

		sysPermissionMapper.updateByPrimaryKey(sysPermission);
	}
}