package pers.husen.highdsa.service.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.encrypt.Md5Encrypt;
import pers.husen.highdsa.common.entity.constants.SysUserState;
import pers.husen.highdsa.common.entity.po.system.SysNavigation;
import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.common.entity.po.system.SysUserRole;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.sequence.SequenceManager;
import pers.husen.highdsa.service.mybatis.SysUserManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserMapper;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserRoleMapper;

/**
 * @Desc 系统用户管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:29:44
 * 
 * @Version 1.0.8
 */
@Service("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	@Override
	public int createUser(SysUser sysUser) {
		// 加密密码
		encryptPassword(sysUser);
		return sysUserMapper.insert(sysUser);
	}

	/**
	 * 根据userId更新
	 * 
	 * @param userId
	 */
	@Override
	public void updateByUserId(SysUser sysUser) {
		sysUserMapper.updateByUserId(sysUser);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	@Override
	public void modifyPassword(Long userId, String newPassword) {
		SysUser user = sysUserMapper.selectUserByUserId(userId);
		user.setUserPassword(newPassword);
		encryptPassword(user);
		sysUserMapper.updateByUserId(user);
	}

	/**
	 * 根据用户id查找用户
	 * 
	 * @param userId
	 * @param newPassword
	 */
	@Override
	public SysUser findUserByUserId(Long userId) {
		SysUser sysUser = sysUserMapper.selectUserByUserId(userId);

		return sysUser;
	}

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		for (Long roleId : roleIds) {
			sysUserRoleMapper.insert(new SysUserRole(userId, roleId));
		}
	}

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		for (Long roleId : roleIds) {
			sysUserRoleMapper.deleteByPrimaryKey(userId, roleId);
		}
	}

	@Override
	public SysUser addUser(SysUser sysUser, Long... roleIds) {
		// 密码加密
		encryptPassword(sysUser);
		// 设置分布式用户id
		Long userId = SequenceManager.getNextId();
		if (userId != null) {
			sysUser.setUserId(userId);
		} else {
			throw new NullPointerException("获取的userId为空");
		}

		// 设置正常状态
		sysUser.setUserState(SysUserState.VALID);

		sysUserMapper.insert(sysUser);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				sysUserRoleMapper.insert(new SysUserRole(sysUser.getUserId(), roleId));
			}
		}

		return sysUser;
	}

	@Override
	public void deleteUser(Long userId) {
		sysUserRoleMapper.deleteByUserId(userId);
		sysUserMapper.deleteByPrimaryKey(userId);
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
	public SysUser findUserByUserName(String userName) {
		return sysUserMapper.selectUserByUserName(userName);
	}

	@Override
	public List<SysUser> getAllUsers() {
		return sysUserMapper.selectAll();
	}

	@Override
	public void updateUserRoles(Long userId, Long... roleIds) {
		sysUserRoleMapper.deleteByUserId(userId);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				sysUserRoleMapper.insert(new SysUserRole(userId, roleId));
			}
		}
	}

	@Override
	public SysUser findRolesByUserName(String userName) {
		return sysUserMapper.selectRolesByUserName(userName);
	}

	@Override
	public SysUser findPermissionsByUserName(String userName) {
		return sysUserMapper.selectPermissionsByUserName(userName);
	}

	@Override
	public List<SysNavigation> findNavigationBar(String userName) {
		List<SysNavigation> navigationBar = new ArrayList<SysNavigation>();
		SysNavigation navigation;

		List<SysRole> roles = sysRoleMapper.selectRolesByUserName(userName);

		for (SysRole role : roles) {
			navigation = new SysNavigation();
			navigation.setNavigationName(role.getRoleDesc());
			navigation.setChildNavigations(sysPermissionMapper.findNavisByRoleId(role.getRoleId()));
			navigationBar.add(navigation);
		}

		return navigationBar;
	}

	/**
	 * 密码加密
	 * 
	 * @param user
	 */
	public void encryptPassword(SysUser user) {
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		// String algorithmName = "md5";
		final int hashIterations = 2;

		user.setUserPwdSalt(randomNumberGenerator.nextBytes().toHex());
		// String encryptedPwd1 = new SimpleHash(algorithmName, user.getUserPassword(),
		// ByteSource.Util.bytes(user.getUserName() + user.getUserPwdSalt()),
		// hashIterations).toHex();
		String encryptedPwd = Md5Encrypt.getMD5Code(user.getUserPassword(), user.getUserName() + user.getUserPwdSalt(), hashIterations);

		user.setUserPassword(encryptedPwd);
	}
}