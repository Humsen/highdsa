package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.husen.highdsa.common.entity.po.system.SysRolePermission;

/**
 * @Desc 系统 角色-权限 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:19:48
 * 
 * @Version 1.0.3
 */
public interface SysRolePermissionMapper {
	/**
	 * 根据主键删除记录
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	/**
	 * 插入新记录
	 * 
	 * @param sysRolePermission
	 * @return
	 */
	int insert(SysRolePermission sysRolePermission);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<SysRolePermission> selectAll();

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