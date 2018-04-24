package pers.husen.highdsa.service.mybatis.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.encrypt.Md5Encrypt;
import pers.husen.highdsa.common.entity.po.customer.CustNavigation;
import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.entity.po.customer.CustUserRole;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.sequence.SequenceManager;
import pers.husen.highdsa.service.mybatis.CustUserManager;
import pers.husen.highdsa.service.mybatis.dao.customer.CustPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserRoleMapper;

/**
 * @Desc 客户管理实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:26:55
 * 
 * @Version 1.0.0
 */
@Service("custUserManager")
public class CustUserManagerImpl implements CustUserManager {
	@Autowired
	private CustUserMapper custUserMapper;
	@Autowired
	private CustUserRoleMapper custUserRoleMapper;
	@Autowired
	private CustRoleMapper custRoleMapper;
	@Autowired
	private CustPermissionMapper custPermissionMapper;

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	@Override
	public int createUser(CustUser custUser) {
		// 加密密码
		encryptPassword(custUser);
		return custUserMapper.insert(custUser);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	@Override
	public void modifyPassword(Long userId, String newPassword) {
		CustUser user = custUserMapper.selectByPrimaryKey(userId);
		user.setUserPassword(newPassword);
		encryptPassword(user);
		custUserMapper.updateByPrimaryKey(user);
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
			custUserRoleMapper.insert(new CustUserRole(userId, roleId));
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
			custUserRoleMapper.deleteByPrimaryKey(userId, roleId);
		}
	}

	@Override
	public CustUser addUser(CustUser custUser, Long... roleIds) {
		// 密码加密
		encryptPassword(custUser);
		// 设置分布式用户id
		Long userId = SequenceManager.getNextId();
		if (userId != null) {
			custUser.setUserId(userId);
		} else {
			throw new NullPointerException("获取的userId为空");
		}

		// 设置正常状态
		custUser.setUserState("100");

		custUserMapper.insert(custUser);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				custUserRoleMapper.insert(new CustUserRole(custUser.getUserId(), roleId));
			}
		}

		return custUser;
	}

	@Override
	public void deleteUser(Long userId) {
		custUserRoleMapper.deleteByUserId(userId);
		custUserMapper.deleteByPrimaryKey(userId);
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
	public CustUser findUserByUserName(String userName) {
		return custUserMapper.selectUserByUserName(userName);
	}

	@Override
	public List<CustUser> getAllUsers() {
		return custUserMapper.selectAll();
	}

	@Override
	public void updateUserRoles(Long userId, Long... roleIds) {
		custUserRoleMapper.deleteByUserId(userId);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				custUserRoleMapper.insert(new CustUserRole(userId, roleId));
			}
		}
	}

	@Override
	public CustUser findRolesByUserName(String userName) {
		return custUserMapper.selectRolesByUserName(userName);
	}

	@Override
	public CustUser findPermissionsByUserName(String userName) {
		return custUserMapper.selectPermissionsByUserName(userName);
	}

	@Override
	public List<CustNavigation> findNavigationBar(String userName) {
		List<CustNavigation> navigationBar = new ArrayList<CustNavigation>();
		CustNavigation navigation;

		List<CustRole> roles = custRoleMapper.selectRolesByUserName(userName);

		for (CustRole role : roles) {
			navigation = new CustNavigation();
			navigation.setNavigationName(role.getRoleDesc());
			navigation.setChildNavigations(custPermissionMapper.findNavisByRoleId(role.getRoleId()));
			navigationBar.add(navigation);
		}

		return navigationBar;
	}

	/**
	 * 密码加密
	 * 
	 * @param user
	 */
	public void encryptPassword(CustUser custUser) {
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		// String algorithmName = "md5";
		final int hashIterations = 2;

		custUser.setUserPwdSalt(randomNumberGenerator.nextBytes().toHex());
		// String encryptedPwd1 = new SimpleHash(algorithmName, user.getUserPassword(),
		// ByteSource.Util.bytes(user.getUserName() + user.getUserPwdSalt()),
		// hashIterations).toHex();
		String encryptedPwd = Md5Encrypt.getMD5Code(custUser.getUserPassword(), custUser.getUserName() + custUser.getUserPwdSalt(), hashIterations);

		custUser.setUserPassword(encryptedPwd);
	}
}