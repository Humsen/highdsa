package pers.husen.highdsa.web.shiro.dao;

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
	int deleteByPrimaryKey(Long roleId);

	int insert(SysRole record);

	SysRole selectByPrimaryKey(Long roleId);

	List<SysRole> selectAll();

	int updateByPrimaryKey(SysRole record);
}