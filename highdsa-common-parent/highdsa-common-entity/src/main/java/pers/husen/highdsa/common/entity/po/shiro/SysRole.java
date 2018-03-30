package pers.husen.highdsa.common.entity.po.shiro;

import java.io.Serializable;

/**
 * @Desc 系统角色实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:34:16
 * 
 * @Version 1.0.2
 */
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String roleDesc;

	private Boolean roleValid;

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleValid=" + roleValid + "]";
	}

	public SysRole(Long roleId, String roleName, String roleDesc, Boolean roleValid) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleValid = roleValid;
	}

	public SysRole() {
		super();
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc == null ? null : roleDesc.trim();
	}

	public Boolean getRoleValid() {
		return roleValid;
	}

	public void setRoleValid(Boolean roleValid) {
		this.roleValid = roleValid;
	}
}