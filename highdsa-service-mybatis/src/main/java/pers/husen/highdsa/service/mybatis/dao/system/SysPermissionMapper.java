package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;
import pers.husen.highdsa.common.entity.po.system.SysPermission;

/**
 * @Desc 系统 权限 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:20:11
 * 
 * @Version 1.0.3
 */
public interface SysPermissionMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param permissionId
	 * @return
	 */
	int deleteByPrimaryKey(Long permissionId);

	/**
	 * 插入新的权限记录
	 * 
	 * @param sysPermission
	 * @return
	 */
	int insert(SysPermission sysPermission);

	/**
	 * 根据主键查询
	 * 
	 * @param permissionId
	 * @return
	 */
	SysPermission selectByPrimaryKey(Long permissionId);

	/**
	 * 查询所有权限
	 * 
	 * @return
	 */
	List<SysPermission> selectAll();

	/**
	 * 根据主键更新记录
	 * 
	 * @param sysPermission
	 * @return
	 */
	int updateByPrimaryKey(SysPermission sysPermission);

	/**
	 * 根据角色id查询所有导航结点
	 * 
	 * @param roleId
	 * @return
	 */
	List<SysPermission> findNavisByRoleId(Long roleId);

	/**
	 * 根据角色id查询所有权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<SysPermission> findPermissionsByRoleId(Long roleId);
}