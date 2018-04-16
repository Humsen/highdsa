package pers.husen.highdsa.service.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysRolePermission;
import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 用户域
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:00
 * 
 * @Version 1.0.1
 */
public class UserRealm extends Pac4jRealm {

	@Autowired
	private SysUserManager sysUserManager;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户身份
		Pac4jPrincipal prPac4jPrincipal = SecurityUtils.getSubject().getPrincipals().oneByType(Pac4jPrincipal.class);
		String userName = prPac4jPrincipal.getProfile().getId();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 根据用户名查询当前用户拥有的角色
		Set<String> roleNames = new HashSet<String>();

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		sysUserManager = (SysUserManager) wac.getBean("sysUserManager");
		SysUser userRole = sysUserManager.findRolesByUserName(userName);
		List<SysRole> roleList = userRole.getSysRoleList();

		for (SysRole role : roleList) {
			roleNames.add(role.getRoleCode());
		}
		// 将角色名称提供给info
		authorizationInfo.setRoles(roleNames);

		// 根据用户名查询当前用户权限
		Set<String> permissionNames = new HashSet<String>();

		SysUser userPermission = sysUserManager.findPermissionsByUserName(userName);
		List<SysRolePermission> rolePermissionList = userPermission.getSysRolePermissionList();

		for (SysRolePermission rolePermission : rolePermissionList) {
			permissionNames.add(rolePermission.getSysPermission().getPermissionCode());
		}
		// 将权限名称提供给info
		authorizationInfo.setStringPermissions(permissionNames);

		return authorizationInfo;
	}
}