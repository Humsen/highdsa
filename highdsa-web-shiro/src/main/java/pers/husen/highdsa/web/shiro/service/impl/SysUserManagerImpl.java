package pers.husen.highdsa.web.shiro.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;
import pers.husen.highdsa.common.entity.po.shiro.SysUserRole;
import pers.husen.highdsa.web.shiro.dao.SysUserMapper;
import pers.husen.highdsa.web.shiro.dao.SysUserRoleMapper;
import pers.husen.highdsa.web.shiro.service.SysUserManager;
import pers.husen.highdsa.web.shiro.utils.PasswordHelper;

/**
 * @Desc 系统用户管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:29:44
 * 
 * @Version 1.0.0
 */
@Service
public class SysUserManagerImpl implements SysUserManager {

	@Autowired
	private SysUserMapper userDao;
	@Autowired
	private SysUserRoleMapper userRoleMapper;

	private PasswordHelper passwordHelper = new PasswordHelper();

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public int createUser(SysUser user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		return userDao.insert(user);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void modifyPassword(Long userId, String newPassword) {
		SysUser user = userDao.selectByPrimaryKey(userId);
		user.setUserPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDao.updateByPrimaryKey(user);
	}

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(Long userId, Long... roleIds) {
		for (Long roleId : roleIds) {
			userRoleMapper.insert(new SysUserRole(userId, roleId));
		}
	}

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		for (Long roleId : roleIds) {
			userRoleMapper.deleteByPrimaryKey(userId, roleId);
		}
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public SysUser findByUserName(String userName) {
		return userDao.selectUserInfoByUserName(userName);
	}

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param userName
	 * @return
	 */
	public Set<String> findRoles(String userName) {
		return userDao.selectRolesByUserName(userName);
	}

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param userName
	 * @return
	 */
	public Set<String> findPermissions(String userName) {
		return userDao.selectPermissionsByUserName(userName);
	}
}