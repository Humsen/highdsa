package pers.husen.highdsa.service.mybatis.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.encrypt.Md5Encrypt;
import pers.husen.highdsa.common.entity.enums.CustUserState;
import pers.husen.highdsa.common.entity.po.customer.CustNavigation;
import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;
import pers.husen.highdsa.common.entity.po.customer.CustUserRole;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.sequence.SequenceManager;
import pers.husen.highdsa.common.utility.DateFormat;
import pers.husen.highdsa.service.mybatis.CustUserManager;
import pers.husen.highdsa.service.mybatis.dao.customer.CustPermissionMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustRoleMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserInfoMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserMapper;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserRoleMapper;

/**
 * @Desc 客户管理实现, dubbo api函数名称和 mybatis数据查询的对应关系为： create -> insert, find ->
 *       select, modify -> update, delete -> delete
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:26:55
 * 
 * @Version 1.0.6
 */
@Service("custUserManager")
public class CustUserManagerImpl implements CustUserManager {
	private static final Logger logger = LogManager.getLogger(CustUserManagerImpl.class.getName());

	@Autowired
	private CustUserMapper custUserMapper;
	@Autowired
	private CustUserInfoMapper custUserInfoMapper;
	@Autowired
	private CustUserRoleMapper custUserRoleMapper;
	@Autowired
	private CustRoleMapper custRoleMapper;
	@Autowired
	private CustPermissionMapper custPermissionMapper;

	@Override
	public String createUser(CustUser custUser) {
		// 注册用户设置默认用户名
		custUser.setUserName("用户" + custUser.getUserPhone() + DateFormat.formatDateYMD("ss"));

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
		custUser.setUserState(CustUserState.VALID);

		// 创建用户
		custUserMapper.insert(custUser);
		// 创建用户用户信息
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(userId);
		custUserInfo.setUserRegisterTime(new Date());
		custUserInfo.setUserLastLoginTime(new Date());
		custUserInfoMapper.insert(custUserInfo);

		return custUser.getUserName();
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
		custUser.setUserState(CustUserState.VALID);

		custUserMapper.insert(custUser);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				custUserRoleMapper.insert(new CustUserRole(custUser.getUserId(), roleId));
			}
		}

		return custUser;
	}

	@Override
	public CustUser findUserByUserId(Long userId) {
		CustUser custUser = custUserMapper.selectUserByUserId(userId);

		return custUser;
	}

	@Override
	public CustUser findUserByUserName(String userName) {
		return custUserMapper.selectUserByUserName(userName);
	}

	@Override
	public CustUser findUserByUserPhone(String userPhone) {
		return custUserMapper.selectUserByUserPhone(userPhone);
	}

	@Override
	public CustUser findUserByUserEmail(String userEmail) {
		return custUserMapper.selectUserByUserEmail(userEmail);
	}

	@Override
	public CustUser findRolesByUserName(String userName) {
		return custUserMapper.selectRolesByUserName(userName);
	}

	@Override
	public CustUser findRolesByUserPhone(String userPhone) {
		return custUserMapper.selectRolesByUserPhone(userPhone);
	}

	@Override
	public CustUser findRolesByUserEmail(String userEmail) {
		return custUserMapper.selectRolesByUserEmail(userEmail);
	}

	@Override
	public CustUser findPermissionsByUserName(String userName) {
		return custUserMapper.selectPermissionsByUserName(userName);
	}

	@Override
	public CustUser findPermissionsByUserPhone(String userPhone) {
		return custUserMapper.selectPermissionsByUserPhone(userPhone);
	}

	@Override
	public CustUser findPermissionsByUserEmail(String userEmail) {
		return custUserMapper.selectPermissionsByUserEmail(userEmail);
	}

	@Override
	public List<CustUser> findAllUsers() {
		return custUserMapper.selectAll();
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
	 * 根据userId更新
	 * 
	 * @param userId
	 */
	@Override
	public void modifyUserByUserId(CustUser custUser) {
		custUserMapper.updateByUserId(custUser);
	}

	@Override
	public void modifyUserRoles(Long userId, Long... roleIds) {
		custUserRoleMapper.deleteByUserId(userId);

		if (roleIds != null && roleIds.length > 0) {
			for (Long roleId : roleIds) {
				custUserRoleMapper.insert(new CustUserRole(userId, roleId));
			}
		}
	}

	@Override
	public void modifyPassword(Long userId, String newPassword) {
		CustUser user = custUserMapper.selectUserByUserId(userId);
		user.setUserPassword(newPassword);
		encryptPassword(user);
		custUserMapper.updateByUserId(user);
	}

	@Override
	public void modifyPasswordByUserPhone(String userPhone, String newPassword) {
		CustUser user = custUserMapper.selectUserByUserPhone(userPhone);
		user.setUserPassword(newPassword);
		encryptPassword(user);
		logger.debug("新密码：{}", user.getUserPassword());
		custUserMapper.updateByUserId(user);
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
	public void correlationRoles(Long userId, Long... roleIds) {
		for (Long roleId : roleIds) {
			custUserRoleMapper.insert(new CustUserRole(userId, roleId));
		}
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		for (Long roleId : roleIds) {
			custUserRoleMapper.deleteByPrimaryKey(userId, roleId);
		}
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