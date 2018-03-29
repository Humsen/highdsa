package pers.husen.highdsa.web.shiro.dao;

import java.util.List;
import pers.husen.highdsa.common.entity.po.shiro.SysPermission;

/**
 * @Desc 系统 权限 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:20:11
 * 
 * @Version 1.0.0
 */
public interface SysPermissionMapper {
	int deleteByPrimaryKey(Long permissionId);

	int insert(SysPermission record);

	SysPermission selectByPrimaryKey(Long permissionId);

	List<SysPermission> selectAll();

	int updateByPrimaryKey(SysPermission record);
}