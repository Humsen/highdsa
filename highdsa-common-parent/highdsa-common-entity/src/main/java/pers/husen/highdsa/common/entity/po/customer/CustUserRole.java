package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;

/**
 * @Desc 客户用户角色实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:37:04
 * 
 * @Version 1.0.1
 */
public class CustUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private Long roleId;

	@Override
	public String toString() {
		return "CustUserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}

	public CustUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public CustUserRole() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}