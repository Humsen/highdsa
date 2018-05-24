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
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.client.restful.token.CustomerAccountPasswordToken;
import pers.husen.highdsa.common.BaseUtils;
import pers.husen.highdsa.common.entity.enums.CustUserState;
import pers.husen.highdsa.common.entity.enums.LoginType;
import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustRolePermission;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.transform.ByteSourceSerializable;
import pers.husen.highdsa.service.mybatis.CustUserManager;

/**
 * @Desc 手机登录realm
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月14日 下午5:53:55
 * 
 * @Version 1.0.4
 */
public class CustUserPhoneRealm extends AuthorizingRealm {
	private static final Logger logger = LogManager.getLogger(CustUserPhoneRealm.class.getName());

	private Cache<Object, AuthenticationInfo> authenticationCache;
	private Cache<Object, AuthorizationInfo> authorizationCache;

	@Autowired
	private CustUserManager custUserManager;

	/**
	 * 仅支持邮箱方式登录
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		CustomerAccountPasswordToken custToken = (CustomerAccountPasswordToken) token;

		return LoginType.PHONE == custToken.getLoginType();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 根据用户名查询当前用户拥有的角色
		Set<String> roleNames = new HashSet<String>();

		CustUser userRole = custUserManager.findRolesByUserPhone(userName);

		if (userRole == null) {
			return null;
		}

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

		CustUser userPermission = custUserManager.findPermissionsByUserPhone(userName);
		if (userPermission == null) {
			return authorizationInfo;
		}

		List<CustRolePermission> rolePermissionList = userPermission.getCustRolePermissionList();

		for (CustRolePermission rolePermission : rolePermissionList) {
			permissionNames.add(rolePermission.getCustPermission().getPermissionCode());
			logger.trace("从数据库获取到的权限：" + rolePermission.getCustPermission().getPermissionCode());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);
		logger.trace("获取当前所有权限：" + authorizationInfo.getStringPermissions());

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();

		CustUser user = custUserManager.findUserByUserPhone(username);

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

	public void clearCachedAuthorizationInfo(String userPhone) {
		if (BaseUtils.isEmpty(userPhone)) {
			return;
		}

		Cache<Object, AuthorizationInfo> cache = getAvailableAuthorizationCache();
		// cache instance will be non-null if caching is enabled:
		if (cache != null) {
			cache.remove(userPhone);
		}
	}

	public void clearCachedAuthenticationInfo(String userPhone) {
		if (BaseUtils.isNotEmpty(userPhone)) {
			Cache<Object, AuthenticationInfo> cache = getAvailableAuthenticationCache();
			// cache instance will be non-null if caching is enabled:
			if (cache != null) {
				cache.remove(userPhone);
			}
		}
	}

	public void clearCache(String userPhone) {
		this.clearCachedAuthenticationInfo(userPhone);
		this.clearCachedAuthorizationInfo(userPhone);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		this.clearAllCachedAuthenticationInfo();
		this.clearAllCachedAuthorizationInfo();
	}

	private Cache<Object, AuthenticationInfo> getAvailableAuthenticationCache() {
		Cache<Object, AuthenticationInfo> cache = getAuthenticationCache();
		boolean authcCachingEnabled = isAuthenticationCachingEnabled();
		if (cache == null && authcCachingEnabled) {
			cache = getAuthenticationCacheLazy();
		}
		return cache;
	}

	private Cache<Object, AuthenticationInfo> getAuthenticationCacheLazy() {
		if (this.authenticationCache == null) {

			CacheManager cacheManager = getCacheManager();

			if (cacheManager != null) {
				String cacheName = getAuthenticationCacheName();
				this.authenticationCache = cacheManager.getCache(cacheName);
			}
		}

		return this.authenticationCache;
	}

	private Cache<Object, AuthorizationInfo> getAvailableAuthorizationCache() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache == null && isAuthorizationCachingEnabled()) {
			cache = getAuthorizationCacheLazy();
		}
		return cache;
	}

	private Cache<Object, AuthorizationInfo> getAuthorizationCacheLazy() {

		if (this.authorizationCache == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("No authorizationCache instance set.  Checking for a cacheManager...");
			}

			CacheManager cacheManager = getCacheManager();

			if (cacheManager != null) {
				String cacheName = getAuthorizationCacheName();
				if (logger.isDebugEnabled()) {
					logger.debug("CacheManager [" + cacheManager + "] has been configured.  Building " + "authorization cache named [" + cacheName + "]");
				}
				this.authorizationCache = cacheManager.getCache(cacheName);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("No cache or cacheManager properties have been set.  Authorization cache cannot " + "be obtained.");
				}
			}
		}

		return this.authorizationCache;
	}
}