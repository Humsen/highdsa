package pers.husen.highdsa.shiro.config.sysuser.service;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.Navigation;
import pers.husen.highdsa.common.entity.po.system.SysUser;

/**
 * @Desc 用户服务接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:08:42
 * 
 * @Version 1.0.0
 */
public interface UserService {
	void addUser(SysUser user, Long... roleIds);// 添加用户

	void deleteUser(Long userId);// 删除用户

	void deleteMoreUsers(Long... userIds);// 批量删除用户

	SysUser getUserByUserName(String userName);// 根据用户名获取用户

	List<SysUser> getAllUsers();// 获取所有用户

	void updateUserRoles(Long userId, Long... roleIds);// 添加用户角色关联

	SysUser findRolesByUserName(String userName);// 根据用户名获取用户所有角色

	SysUser findPermissionsByUserName(String userName);// 根据用户名获取用户所有权限

	List<Navigation> getNavigationBar(String userName);// 获取导航栏内容
}