package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;

/**
 * @Desc 客户角色权限实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:35:59
 * 
 * @Version 1.0.2
 */
public class CustRolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private Long permissionId;

	private CustPermission custPermission;

	@Override
	public String toString() {
		return "CustRolePermission [roleId=" + roleId + ", permissionId=" + permissionId + ", custPermission=" + custPermission + "]";
	}

	public CustRolePermission(Long roleId, Long permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public CustRolePermission() {
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
	 * @return the custPermission
	 */
	public CustPermission getCustPermission() {
		return custPermission;
	}

	/**
	 * @param custPermission
	 *            the custPermission to set
	 */
	public void setCustPermission(CustPermission custPermission) {
		this.custPermission = custPermission;
	}
}