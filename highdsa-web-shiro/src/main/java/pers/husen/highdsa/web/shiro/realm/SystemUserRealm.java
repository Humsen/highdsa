package pers.husen.highdsa.web.shiro.realm;

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

import pers.husen.highdsa.common.constant.SysUserState;
import pers.husen.highdsa.common.entity.po.shiro.SysRole;
import pers.husen.highdsa.common.entity.po.shiro.SysRolePermission;
import pers.husen.highdsa.common.entity.po.shiro.SysUser;
import pers.husen.highdsa.common.utility.ByteSourceSerializable;
import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 后台系统用户域
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午8:36:54
 * 
 * @Version 1.0.3
 */
public class SystemUserRealm extends AuthorizingRealm {
	private static final Logger logger = LogManager.getLogger(SystemUserRealm.class.getName());

	private SysUserManager sysUserManager;

	/**
	 * @param sysUserManager
	 *            the sysUserManager to set
	 */
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 根据用户名查询当前用户拥有的角色
		Set<String> roleNames = new HashSet<String>();

		Set<SysUser> userRoles = sysUserManager.findRoles(userName);
		for (SysUser userRole : userRoles) {
			List<SysRole> roleList = userRole.getSysRoleList();

			for (SysRole role : roleList) {
				roleNames.add(role.getRoleName());
				logger.trace("从数据库获取到的角色：" + role.getRoleName());
			}
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);

		logger.trace("获取当前所有获取角色：" + authorizationInfo.getRoles());

		// 根据用户名查询当前用户权限
		Set<String> permissionNames = new HashSet<String>();

		Set<SysUser> userPermissions = sysUserManager.findPermissions(userName);
		for (SysUser userPermission : userPermissions) {
			List<SysRolePermission> rolePermissionList = userPermission.getSysRolePermissionList();

			for (SysRolePermission rolePermission : rolePermissionList) {
				permissionNames.add(rolePermission.getSysPermission().getPermissionName());
				logger.trace("从数据库获取到的权限：" + rolePermission.getSysPermission().getPermissionName());
			}
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);
		logger.trace("获取当前所有权限：" + authorizationInfo.getStringPermissions());

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();

		SysUser user = sysUserManager.findByUserName(username);

		if (user == null) {
			// 没找到帐号
			throw new UnknownAccountException();
		}

		if (SysUserState.VALID.equals(user.getUserState())) {
			// 帐号锁定
			throw new LockedAccountException(); 
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		// 用户名, 密码, salt=username+salt, realm name
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), 
				user.getUserPassword(), 
				new ByteSourceSerializable(user.getUserName() + user.getUserPwdSalt()), 
				getName() 
		);

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