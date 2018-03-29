package pers.husen.highdsa.web.shiro.service;

import java.util.Set;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;

/**
 * @Desc 系统用户管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:21:33
 * 
 * @Version 1.0.0
 */
public interface SysUserManager {
	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public int createUser(SysUser user);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void modifyPassword(Long userId, String newPassword);

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(Long userId, Long... roleIds);

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public SysUser findByUserName(String userName);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String userName);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String userName);
}