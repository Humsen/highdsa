package pers.husen.highdsa.security.client.cas.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import pers.husen.highdsa.common.entity.po.customer.CustRole;
import pers.husen.highdsa.common.entity.po.customer.CustRolePermission;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.service.mybatis.CustUserManager;

/**
 * @Desc 用户域
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:00
 * 
 * @Version 1.0.1
 */
public class UserRealm extends CasRealm {

	private CustUserManager custUserManager;

	/**
	 * @param sysUserManager
	 */
	public UserRealm(CustUserManager custUserManager) {
		super();
		this.custUserManager = custUserManager;
	}

	/**
	 * @return the custUserManager
	 */
	public CustUserManager getCustUserManager() {
		return custUserManager;
	}

	/**
	 * @param custUserManager
	 *            the custUserManager to set
	 */
	public void setCustUserManager(CustUserManager custUserManager) {
		this.custUserManager = custUserManager;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户身份
		String userName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 根据用户名查询当前用户拥有的角色
		Set<String> roleNames = new HashSet<String>();

		CustUser userRole = custUserManager.findRolesByUserName(userName);
		List<CustRole> roleList = userRole.getCustRoleList();

		for (CustRole role : roleList) {
			roleNames.add(role.getRoleCode());
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);

		// 根据用户名查询当前用户权限
		Set<String> permissionNames = new HashSet<String>();

		CustUser userPermission = custUserManager.findPermissionsByUserName(userName);
		List<CustRolePermission> rolePermissionList = userPermission.getCustRolePermissionList();

		for (CustRolePermission rolePermission : rolePermissionList) {
			permissionNames.add(rolePermission.getCustPermission().getPermissionCode());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
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