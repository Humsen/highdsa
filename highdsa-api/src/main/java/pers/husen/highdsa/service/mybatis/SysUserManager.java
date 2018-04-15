package pers.husen.highdsa.service.mybatis;

import java.util.List;

import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysNavigation;
import pers.husen.highdsa.common.entity.po.system.SysUser;

/**
 * @Desc 系统用户管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:21:33
 * 
 * @Version 1.0.4
 */
@Service("sysUserManager")
public interface SysUserManager {
	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
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
	 * 增加用户和用户-角色关联
	 * 
	 * @param user
	 * @param roleIds
	 * @return
	 */
	SysUser addUser(SysUser user, Long... roleIds);

	/**
	 * 根据id删除用户
	 * 
	 * @param userId
	 */
	void deleteUser(Long userId);

	/**
	 * 删除一组用户
	 * 
	 * @param userIds
	 */
	void deleteMoreUsers(Long... userIds);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	SysUser findUserByUserName(String userName);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<SysUser> getAllUsers();

	/**
	 * 更新用户-角色关联
	 * 
	 * @param userId
	 * @param roleIds
	 */
	void updateUserRoles(Long userId, Long... roleIds);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	SysUser findRolesByUserName(String userName);

	/**
	 * 根据用户名查找权限集合
	 * 
	 * @param userName
	 * @return
	 */
	SysUser findPermissionsByUserName(String userName);

	/**
	 * 根据用户名查找导航栏
	 * 
	 * @param userName
	 * @return
	 */
	List<SysNavigation> findNavigationBar(String userName);
}