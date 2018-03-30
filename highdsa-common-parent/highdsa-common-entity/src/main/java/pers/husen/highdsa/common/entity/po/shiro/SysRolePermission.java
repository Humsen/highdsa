package pers.husen.highdsa.common.entity.po.shiro;

import java.io.Serializable;

/**
 * @Desc 系统角色-权限实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:34:32
 * 
 * @Version 1.0.2
 */
public class SysRolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private Long permissionId;

	private SysPermission sysPermission;

	@Override
	public String toString() {
		return "SysRolePermission [roleId=" + roleId + ", permissionId=" + permissionId + ", sysPermission=" + sysPermission + "]";
	}

	public SysRolePermission(Long roleId, Long permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public SysRolePermission() {
		super();
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @return the sysPermission
	 */
	public SysPermission getSysPermission() {
		return sysPermission;
	}

	/**
	 * @param sysPermission
	 *            the sysPermission to set
	 */
	public void setSysPermission(SysPermission sysPermission) {
		this.sysPermission = sysPermission;
	}
}