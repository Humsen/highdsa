package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;

/**
 * @Desc 客户角色实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:35:21
 * 
 * @Version 1.0.0
 */
public class CustRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String roleDesc;

	private Boolean roleValid;

	@Override
	public String toString() {
		return "CustRole [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleValid=" + roleValid + "]";
	}

	public CustRole(Long roleId, String roleName, String roleDesc, Boolean roleValid) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleValid = roleValid;
	}

	public CustRole() {
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