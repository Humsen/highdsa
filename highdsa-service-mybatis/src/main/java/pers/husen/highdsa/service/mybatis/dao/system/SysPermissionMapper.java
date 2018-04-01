package pers.husen.highdsa.service.mybatis.dao.system;

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
	 * @param record
	 * @return
	 */
	int insert(SysPermission record);

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
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(SysPermission record);
}