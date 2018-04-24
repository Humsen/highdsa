package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustPermission;

/**
 * @Desc 客户权限类映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午11:44:55
 * 
 * @Version 1.0.2
 */
public interface CustPermissionMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param permissionId
	 * @return
	 */
	int deleteByPrimaryKey(Long permissionId);

	/**
	 * 插入新记录
	 * 
	 * @param custPermission
	 * @return
	 */
	int insert(CustPermission custPermission);

	/**
	 * 根据主键查找
	 * 
	 * @param permissionId
	 * @return
	 */
	CustPermission selectByPrimaryKey(Long permissionId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustPermission> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param custPermission
	 * @return
	 */
	int updateByPrimaryKey(CustPermission custPermission);

	/**
	 * 根据角色id查询所有导航结点
	 * 
	 * @param roleId
	 * @return
	 */
	List<CustPermission> findNavisByRoleId(Long roleId);

	/**
	 * 根据角色id查询所有权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<CustPermission> findPermissionsByRoleId(Long roleId);
}