package pers.husen.highdsa.common.entity.po.shiro;

import java.io.Serializable;

/**
 * @Desc 系统权限实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:20:55
 * 
 * @Version 1.0.3
 */
public class SysPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long permissionId;

	private String permissionName;

	private String permissionDesc;

	private Boolean permissionValid;

	@Override
	public String toString() {
		return "SysPermission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", permissionDesc=" + permissionDesc + ", permissionValid=" + permissionValid + "]";
	}

	public SysPermission(Long permissionId, String permissionName, String permissionDesc, Boolean permissionValid) {
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.permissionDesc = permissionDesc;
		this.permissionValid = permissionValid;
	}

	public SysPermission() {
		super();
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName == null ? null : permissionName.trim();
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc == null ? null : permissionDesc.trim();
	}

	public Boolean getPermissionValid() {
		return permissionValid;
	}

	public void setPermissionValid(Boolean permissionValid) {
		this.permissionValid = permissionValid;
	}
}