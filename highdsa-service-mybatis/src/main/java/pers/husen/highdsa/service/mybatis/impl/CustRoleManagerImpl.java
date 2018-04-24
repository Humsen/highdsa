package pers.husen.highdsa.service.mybatis.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustRolePermission;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.sequence.SequenceManager;
import pers.husen.highdsa.service.mybatis.CustRoleManager;
import pers.husen.highdsa.service.mybatis.dao.customer.CustRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustRolePermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserRoleMapper;

/**
 * @Desc 客户角色管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:25:36
 * 
 * @Version 1.0.0
 */
@Service("custRoleManager")
public class CustRoleManagerImpl implements CustRoleManager {
	@Autowired
	private CustRoleMapper custRoleMapper;
	@Autowired
	private CustRolePermissionMapper custRolePermissionMapper;
	@Autowired
	private CustUserRoleMapper custUserRoleMapper;
	@Autowired
	private CustUserMapper custUserMapper;

	@Override
	public int createSysRole(CustRole custRole) {
		return custRoleMapper.insert(custRole);
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
			custRolePermissionMapper.insert(new CustRolePermission(roleId, permissionId));
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
			custRolePermissionMapper.deleteByPrimaryKey(roleId, permissionId);
		}
	}

	@Override
	public CustRole addRole(CustRole custRole, Long... permissionIds) {
		// 设置分布式角色id
		Long roleId = SequenceManager.getNextId();
		if (roleId != null) {
			custRole.setRoleId(roleId);
			;
		} else {
			throw new NullPointerException("获取的roleId为空");
		}

		// 设置角色有效
		custRole.setRoleValid(true);

		custRoleMapper.insert(custRole);

		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				custRolePermissionMapper.insert(new CustRolePermission(custRole.getRoleId(), permissionId));
			}
		}

		return custRole;
	}

	@Override
	public void deleteRole(Long roleId) {
		custUserRoleMapper.deleteByRoleId(roleId);
		custRolePermissionMapper.deleteByRoleId(roleId);
		custRoleMapper.deleteByPrimaryKey(roleId);
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
	public CustRole findRoleById(Long roleId) {
		return custRoleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public CustUser findRolesByUserName(String userName) {
		return custUserMapper.selectRolesByUserName(userName);
	}

	@Override
	public List<CustRole> findAllRoles() {
		return custRoleMapper.selectAll();
	}

	@Override
	public void updateRole(CustRole custRole, Long... permissionIds) {
		// 设置角色有效
		custRole.setRoleValid(true);
		// 设置最后更新时间
		custRole.setRoleLastModifyTime(new Date());

		custRoleMapper.updateByPrimaryKey(custRole);
		custRolePermissionMapper.deleteByRoleId(custRole.getRoleId());
		addRolePermissions(custRole.getRoleId(), permissionIds);
	}

	@Override
	public void addRolePermissions(Long roleId, Long... permissionIds) {
		if (permissionIds != null && permissionIds.length > 0) {
			for (Long permissionId : permissionIds) {
				custRolePermissionMapper.insert(new CustRolePermission(roleId, permissionId));
			}
		}
	}
}