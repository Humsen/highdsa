package pers.husen.highdsa.common.entity.po.system;

import java.io.Serializable;

/**
 * @Desc 系统用户-角色实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:35:18
 * 
 * @Version 1.0.2
 */
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private Long roleId;

	@Override
	public String toString() {
		return "SysUserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}

	public SysUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public SysUserRole() {
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