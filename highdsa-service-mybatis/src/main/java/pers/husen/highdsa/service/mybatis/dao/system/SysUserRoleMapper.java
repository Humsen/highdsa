package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.husen.highdsa.common.entity.po.shiro.SysUserRole;

/**
 * @Desc 系统 用户-角色 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:19:12
 * 
 * @Version 1.0.0
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
	 * @param record
	 * @return
	 */
	int insert(SysUserRole record);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	List<SysUserRole> selectAll();
}