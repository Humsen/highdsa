package pers.husen.highdsa.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import pers.husen.highdsa.common.entity.po.shiro.SysRole;
import pers.husen.highdsa.common.entity.po.shiro.SysRolePermission;
import pers.husen.highdsa.common.entity.po.shiro.SysUser;
import pers.husen.highdsa.service.shiro.ShiroService;

/**
 * @Desc 客户端用户域
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 上午12:48:37
 * 
 * @Version 1.0.0
 */
public class ClientUserRealm extends AuthorizingRealm {
	private ShiroService shiroService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser sysUser = shiroService.getPermissions(userName);

		// 设置角色信息
		Set<String> roleNames = new HashSet<String>();
		List<SysRole> roleList = sysUser.getSysRoleList();
		for (SysRole role : roleList) {
			roleNames.add(role.getRoleName());
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);

		// 设置权限信息
		Set<String> permissionNames = new HashSet<String>();
		List<SysRolePermission> rolePermissionList = sysUser.getSysRolePermissionList();
		for (SysRolePermission rolePermission : rolePermissionList) {
			permissionNames.add(rolePermission.getSysPermission().getPermissionName());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 永远不会被调用
		throw new UnsupportedOperationException("永远不会被调用");
	}
}