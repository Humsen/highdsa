package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.husen.highdsa.common.entity.po.customer.CustRolePermission;

/**
 * @Desc 客户角色-权限映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午11:50:28
 * 
 * @Version 1.0.1
 */
public interface CustRolePermissionMapper {
	/**
	 * 根据主键更新
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	/**
	 * 插入新记录
	 * 
	 * @param record
	 * @return
	 */
	int insert(CustRolePermission record);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustRolePermission> selectAll();
}