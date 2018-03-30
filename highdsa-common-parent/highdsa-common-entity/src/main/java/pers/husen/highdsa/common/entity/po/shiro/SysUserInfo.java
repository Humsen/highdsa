package pers.husen.highdsa.common.entity.po.shiro;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 系统用户信息实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:35:04
 * 
 * @Version 1.0.2
 */
public class SysUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userNickName;

	private String userHeadUrl;

	private Integer userSex;

	private String userBirthday;

	private Date userRegisterDate;

	private String userDesc;

	private Date userLastLoginTime;

	@Override
	public String toString() {
		return "SysUserInfo [userId=" + userId + ", userNickName=" + userNickName + ", userHeadUrl=" + userHeadUrl + ", userSex=" + userSex + ", userBirthday=" + userBirthday + ", userRegisterDate="
				+ userRegisterDate + ", userDesc=" + userDesc + ", userLastLoginTime=" + userLastLoginTime + "]";
	}

	public SysUserInfo(Long userId, String userNickName, String userHeadUrl, Integer userSex, String userBirthday, Date userRegisterDate, String userDesc, Date userLastLoginTime) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.userHeadUrl = userHeadUrl;
		this.userSex = userSex;
		this.userBirthday = userBirthday;
		this.userRegisterDate = userRegisterDate;
		this.userDesc = userDesc;
		this.userLastLoginTime = userLastLoginTime;
	}

	public SysUserInfo() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName == null ? null : userNickName.trim();
	}

	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl == null ? null : userHeadUrl.trim();
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday == null ? null : userBirthday.trim();
	}

	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc == null ? null : userDesc.trim();
	}

	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}

	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}
}