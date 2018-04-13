package pers.husen.highdsa.shiro.config.sysuser.service;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.SysRole;
import pers.husen.highdsa.common.entity.po.system.SysUser;

/**
 * @Desc 角色服务接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:08:28
 * 
 * @Version 1.0.0
 */
public interface RoleService {
	Long addRole(SysRole role, Long... permissionIds);

	void deleteRole(Long roleId);

	void deleteMoreRoles(Long... roleIds);

	SysRole getRoleById(Long roleId);

	SysUser getRolesByUserName(String userName);

	List<SysRole> getAllRoles();

	void updateRole(SysRole role, Long... permIds);

	void addRolePermissions(Long roleId, Long... permissionIds);
}