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
 * @Version 1.0.2
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

	/**
	 * 根据角色id删除,删除角色时，此角色对应的所有权限关联失效
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Long roleId);

	/**
	 * 根据权限id删除角色-权限 联系,刹车农户权限时拥有此权限的角色的此权限默认全部失效
	 * 
	 * @param permissionId
	 * @return
	 */
	int deleteByPermissionId(Long permissionId);
}