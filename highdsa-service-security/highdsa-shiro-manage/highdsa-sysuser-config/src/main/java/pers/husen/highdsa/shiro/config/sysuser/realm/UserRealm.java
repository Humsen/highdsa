package pers.husen.highdsa.shiro.config.sysuser.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysRolePermission;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.shiro.config.sysuser.service.UserService;

/**
 * @Desc 用户域
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:00
 * 
 * @Version 1.0.0
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 根据用户名查询当前用户拥有的角色
		Set<String> roleNames = new HashSet<String>();

		SysUser userRole = userService.findRolesByUserName(userName);
		List<SysRole> roleList = userRole.getSysRoleList();

		for (SysRole role : roleList) {
			roleNames.add(role.getRoleCode());
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);

		// 根据用户名查询当前用户权限
		Set<String> permissionNames = new HashSet<String>();

		SysUser userPermission = userService.findPermissionsByUserName(userName);
		List<SysRolePermission> rolePermissionList = userPermission.getSysRolePermissionList();

		for (SysRolePermission rolePermission : rolePermissionList) {
			permissionNames.add(rolePermission.getSysPermission().getPermissionCode());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		SysUser user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), ByteSource.Util.bytes(user.getUserName() + user.getUserPwdSalt()),
				getName());
		return authenticationInfo;
	}
}