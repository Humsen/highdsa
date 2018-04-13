package pers.husen.highdsa.shiro.config.sysuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.Navigation;
import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.common.entity.po.system.SysUserRole;
import pers.husen.highdsa.service.mybatis.dao.system.SysPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserRoleMapper;
import pers.husen.highdsa.shiro.config.sysuser.service.UserService;

/**
 * @Desc 用户服务实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:59
 * 
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysPermissionMapper permissionMapper;
	@Autowired
	private PasswordService passwordService;

	@Override
	public void addUser(SysUser user, Long... roleIds) {
		// 密码加密
		passwordService.encryptPassword(user);
		// TODO-设置分布式id
		user.setUserId(10004L);
		// 设置正常状态
		user.setUserState("100");

		userMapper.insert(user);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				userRoleMapper.insert(new SysUserRole(user.getUserId(), roleId));
			}
		}
	}

	@Override
	public void deleteUser(Long userId) {
		userRoleMapper.deleteByUserId(userId);
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public void deleteMoreUsers(Long... userIds) {
		if (userIds != null && userIds.length > 0) {
			for (Long userId : userIds) {
				deleteUser(userId);
			}
		}
	}

	@Override
	public SysUser getUserByUserName(String userName) {
		return userMapper.selectUserByUserName(userName);
	}

	@Override
	public List<SysUser> getAllUsers() {
		return userMapper.selectAll();
	}

	@Override
	public void updateUserRoles(Long userId, Long... roleIds) {
		userRoleMapper.deleteByUserId(userId);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				userRoleMapper.insert(new SysUserRole(userId, roleId));
			}
		}
	}

	@Override
	public SysUser findRolesByUserName(String userName) {
		return userMapper.selectRolesByUserName(userName);
	}

	@Override
	public SysUser findPermissionsByUserName(String userName) {
		return userMapper.selectPermissionsByUserName(userName);
	}

	@Override
	public List<Navigation> getNavigationBar(String userName) {
		List<Navigation> navigationBar = new ArrayList<Navigation>();
		Navigation navigation;

		List<SysRole> roles = roleMapper.selectRolesByUserName(userName);

		for (SysRole role : roles) {
			navigation = new Navigation();
			navigation.setNavigationName(role.getRoleDesc());
			navigation.setChildNavigations(permissionMapper.findNavisByRoleId(role.getRoleId()));
			navigationBar.add(navigation);
		}

		return navigationBar;
	}
}