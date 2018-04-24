package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 客户角色实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:35:21
 * 
 * @Version 1.0.1
 */
public class CustRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String roleCode;

	private String roleDesc;

	private Date roleCreateTime;

	private Date roleLastModifyTime;

	private Boolean roleValid;

	@Override
	public String toString() {
		return "CustRole [roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode + ", roleDesc=" + roleDesc + ", roleCreateTime=" + roleCreateTime + ", roleLastModifyTime="
				+ roleLastModifyTime + ", roleValid=" + roleValid + "]";
	}

	public CustRole(Long roleId, String roleName, String roleCode, String roleDesc, Date roleCreateTime, Date roleLastModifyTime, Boolean roleValid) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.roleDesc = roleDesc;
		this.roleCreateTime = roleCreateTime;
		this.roleLastModifyTime = roleLastModifyTime;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc == null ? null : roleDesc.trim();
	}

	public Date getRoleCreateTime() {
		return roleCreateTime;
	}

	public void setRoleCreateTime(Date roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}

	public Date getRoleLastModifyTime() {
		return roleLastModifyTime;
	}

	public void setRoleLastModifyTime(Date roleLastModifyTime) {
		this.roleLastModifyTime = roleLastModifyTime;
	}

	public Boolean getRoleValid() {
		return roleValid;
	}

	public void setRoleValid(Boolean roleValid) {
		this.roleValid = roleValid;
	}
}