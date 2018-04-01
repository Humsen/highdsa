package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;
import pers.husen.highdsa.common.entity.po.shiro.SysRole;

/**
 * @Desc 系统 角色 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:20:01
 * 
 * @Version 1.0.0
 */
public interface SysRoleMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteByPrimaryKey(Long roleId);

	/**
	 * 插入新的记录
	 * 
	 * @param record
	 * @return
	 */
	int insert(SysRole record);

	/**
	 * 根据主键查找
	 * 
	 * @param roleId
	 * @return
	 */
	SysRole selectByPrimaryKey(Long roleId);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	List<SysRole> selectAll();

	/**
	 * 根据主键更新记录
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(SysRole record);
}