package pers.husen.highdsa.common.entity.po.system;

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

	private String permissionCode;

	private String permissionDesc;

	private Boolean permissionValid;

	private Integer permissionNavi;

	private String permissionUrl;

	@Override
	public String toString() {
		return "SysPermission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", permissionCode=" + permissionCode + ", permissionDesc=" + permissionDesc + ", permissionValid="
				+ permissionValid + ", permissionNavi=" + permissionNavi + ", permissionUrl=" + permissionUrl + "]";
	}

	/**
	 * @param permissionId
	 * @param permissionName
	 * @param permissionCode
	 * @param permissionDesc
	 * @param permissionValid
	 * @param permissionNavi
	 * @param permissionUrl
	 */
	public SysPermission(Long permissionId, String permissionName, String permissionCode, String permissionDesc, Boolean permissionValid, Integer permissionNavi, String permissionUrl) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.permissionCode = permissionCode;
		this.permissionDesc = permissionDesc;
		this.permissionValid = permissionValid;
		this.permissionNavi = permissionNavi;
		this.permissionUrl = permissionUrl;
	}

	/**
	 * @param permissionId
	 * @param permissionName
	 * @param permissionDesc
	 * @param permissionValid
	 * @param permissionNavi
	 * @param permissionUrl
	 */
	public SysPermission(Long permissionId, String permissionName, String permissionDesc, Boolean permissionValid, Integer permissionNavi, String permissionUrl) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.permissionDesc = permissionDesc;
		this.permissionValid = permissionValid;
		this.permissionNavi = permissionNavi;
		this.permissionUrl = permissionUrl;
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

	/**
	 * @return the permissionNavi
	 */
	public Integer getPermissionNavi() {
		return permissionNavi;
	}

	/**
	 * @param permissionNavi
	 *            the permissionNavi to set
	 */
	public void setPermissionNavi(Integer permissionNavi) {
		this.permissionNavi = permissionNavi;
	}

	/**
	 * @return the permissionUrl
	 */
	public String getPermissionUrl() {
		return permissionUrl;
	}

	/**
	 * @param permissionUrl
	 *            the permissionUrl to set
	 */
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	/**
	 * @return the permissionCode
	 */
	public String getPermissionCode() {
		return permissionCode;
	}

	/**
	 * @param permissionCode
	 *            the permissionCode to set
	 */
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
}