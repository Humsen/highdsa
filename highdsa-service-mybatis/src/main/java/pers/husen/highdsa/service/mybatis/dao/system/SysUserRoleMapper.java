package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.husen.highdsa.common.entity.po.system.SysUserRole;

/**
 * @Desc 系统 用户-角色 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:19:12
 * 
 * @Version 1.0.3
 */
public interface SysUserRoleMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

	/**
	 * 插入新角色
	 * 
	 * @param sysUserRole
	 * @return
	 */
	int insert(SysUserRole sysUserRole);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	List<SysUserRole> selectAll();

	/**
	 * 根据用户id删除 用户-角色联系, 删除用户时此用户联系的所有角色都失效
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByUserId(Long userId);

	/**
	 * 根据角色id删除 用户-角色 联系, 删除角色时拥有此角色的所有用户的此角色默认都失效
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Long roleId);
}