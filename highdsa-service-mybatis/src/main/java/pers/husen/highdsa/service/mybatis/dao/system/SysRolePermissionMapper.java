package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.husen.highdsa.common.entity.po.shiro.SysRolePermission;

/**
 * @Desc 系统 角色-权限 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:19:48
 * 
 * @Version 1.0.0
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
	 * @param record
	 * @return
	 */
	int insert(SysRolePermission record);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<SysRolePermission> selectAll();
}