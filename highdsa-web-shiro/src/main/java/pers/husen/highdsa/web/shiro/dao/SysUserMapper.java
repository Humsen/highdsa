package pers.husen.highdsa.web.shiro.dao;

import java.util.List;
import java.util.Set;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;

/**
 * @Desc 系统 用户 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:19:33
 * 
 * @Version 1.0.0
 */
public interface SysUserMapper {
	int deleteByPrimaryKey(Long userId);

	int insert(SysUser record);

	SysUser selectByPrimaryKey(Long userId);

	List<SysUser> selectAll();

	int updateByPrimaryKey(SysUser record);

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 */
	SysUser selectUserInfoByUserName(String userName);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	Set<String> selectRolesByUserName(String userName);

	/**
	 * 根据用户名查找权限集合
	 * 
	 * @param userName
	 * @return
	 */
	Set<String> selectPermissionsByUserName(String userName);
}