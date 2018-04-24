package pers.husen.highdsa.service.mybatis.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.customer.CustPermission;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.sequence.SequenceManager;
import pers.husen.highdsa.service.mybatis.CustPermissionManager;
import pers.husen.highdsa.service.mybatis.dao.customer.CustPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustRolePermissionMapper;

/**
 * @Desc 客户权限管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:25:21
 * 
 * @Version 1.0.0
 */
@Service("custPermissionManager")
public class CustPermissionManagerImpl implements CustPermissionManager {

	@Autowired
	private CustPermissionMapper custPermissionMapper;
	@Autowired
	private CustRolePermissionMapper custRolePermissionMapper;

	@Override
	public CustPermission createPermission(CustPermission custPermission) {
		// 设置权限id
		Long permissionId = SequenceManager.getNextId();
		if (permissionId != null) {
			custPermission.setPermissionId(permissionId);
		} else {
			throw new NullPointerException("获取的permissionId为空");
		}

		// 设置权限有效
		custPermission.setPermissionValid(true);
		// 如果是否为导航属性为null,说明创建时没有被勾选，设置为false
		if (custPermission.getPermissionNavi() == null) {
			custPermission.setPermissionNavi(false);
		}
		// 设置创建时间
		custPermission.setPermissionCreateTime(new Date());
		// 设置最后更新时间
		custPermission.setPermissionLastModifyTime(new Date());

		custPermissionMapper.insert(custPermission);

		return custPermission;
	}

	@Override
	public void deletePermission(Long permissionId) {
		custRolePermissionMapper.deleteByPermissionId(permissionId);
		custPermissionMapper.deleteByPrimaryKey(permissionId);
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
	public CustPermission findSysPermissionById(Long permissionId) {
		return custPermissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public List<CustPermission> findPermissionsByRoleId(Long roleId) {
		return custPermissionMapper.findPermissionsByRoleId(roleId);
	}

	@Override
	public List<CustPermission> getAllPermissions() {
		return custPermissionMapper.selectAll();
	}

	@Override
	public void updatePermissionByPrimaryKey(CustPermission custPermission) {
		// 如果是否为导航属性为null,说明创建时没有被勾选，设置为false
		if (custPermission.getPermissionNavi() == null) {
			custPermission.setPermissionNavi(false);
		}
		// 设置权限有效
		custPermission.setPermissionValid(true);
		// 设置最后更新时间
		custPermission.setPermissionLastModifyTime(new Date());

		custPermissionMapper.updateByPrimaryKey(custPermission);
	}
}