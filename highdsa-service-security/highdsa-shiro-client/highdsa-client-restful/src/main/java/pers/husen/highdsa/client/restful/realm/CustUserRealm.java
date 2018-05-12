package pers.husen.highdsa.client.restful.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.common.entity.constants.CustUserState;
import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustRolePermission;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.transform.ByteSourceSerializable;
import pers.husen.highdsa.service.mybatis.CustUserManager;

/**
 * @Desc 后台系统用户域
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午8:36:54
 * 
 * @Version 1.0.4
 */
public class CustUserRealm extends AuthorizingRealm {
	private static final Logger logger = LogManager.getLogger(CustUserRealm.class.getName());

	@Autowired
	private CustUserManager custUserManager;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 根据用户名查询当前用户拥有的角色
		Set<String> roleNames = new HashSet<String>();

		CustUser userRole = custUserManager.findRolesByUserName(userName);
		List<CustRole> roleList = userRole.getCustRoleList();

		for (CustRole role : roleList) {
			roleNames.add(role.getRoleName());
			logger.trace("从数据库获取到的角色：" + role.getRoleName());
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);

		logger.trace("获取当前所有获取角色：" + authorizationInfo.getRoles());

		// 根据用户名查询当前用户权限
		Set<String> permissionNames = new HashSet<String>();

		CustUser userPermission = custUserManager.findPermissionsByUserName(userName);
		List<CustRolePermission> rolePermissionList = userPermission.getCustRolePermissionList();

		for (CustRolePermission rolePermission : rolePermissionList) {
			permissionNames.add(rolePermission.getCustPermission().getPermissionName());
			logger.trace("从数据库获取到的权限：" + rolePermission.getCustPermission().getPermissionName());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);
		logger.trace("获取当前所有权限：" + authorizationInfo.getStringPermissions());

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();

		CustUser user = custUserManager.findUserByUserName(username);

		if (user == null) {
			// 没找到帐号
			throw new UnknownAccountException();
		}

		if (CustUserState.LOCKED == user.getUserState()) {
			// 帐号锁定
			throw new LockedAccountException();
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		// 用户名, 密码, salt=username+salt, realm name
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), new ByteSourceSerializable(user.getUserName() + user.getUserPwdSalt()),
				getName());

		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
}