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
	int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	int insert(SysRolePermission record);

	List<SysRolePermission> selectAll();
}