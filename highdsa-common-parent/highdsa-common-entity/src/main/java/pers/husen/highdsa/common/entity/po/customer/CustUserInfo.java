package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 客户信息实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:34:35
 * 
 * @Version 1.0.0
 */
public class CustUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String userNickName;

	private Date userRegisterDate;

	private String userHeadUrl;

	private Boolean userSex;

	private String userAddress;

	private String userAge;

	private String userBirthday;

	private String userDesc;

	private Date userLastLoginTime;

	@Override
	public String toString() {
		return "CustUserInfo [userId=" + userId + ", userNickName=" + userNickName + ", userRegisterDate=" + userRegisterDate + ", userHeadUrl=" + userHeadUrl + ", userSex=" + userSex
				+ ", userAddress=" + userAddress + ", userAge=" + userAge + ", userBirthday=" + userBirthday + ", userDesc=" + userDesc + ", userLastLoginTime=" + userLastLoginTime + "]";
	}

	public CustUserInfo(Integer userId, String userNickName, Date userRegisterDate, String userHeadUrl, Boolean userSex, String userAddress, String userAge, String userBirthday, String userDesc,
			Date userLastLoginTime) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.userRegisterDate = userRegisterDate;
		this.userHeadUrl = userHeadUrl;
		this.userSex = userSex;
		this.userAddress = userAddress;
		this.userAge = userAge;
		this.userBirthday = userBirthday;
		this.userDesc = userDesc;
		this.userLastLoginTime = userLastLoginTime;
	}

	public CustUserInfo(Integer userId, String userNickName, Date userRegisterDate, String userHeadUrl, String userAddress, String userAge, String userBirthday, String userDesc,
			Date userLastLoginTime) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.userRegisterDate = userRegisterDate;
		this.userHeadUrl = userHeadUrl;
		this.userAddress = userAddress;
		this.userAge = userAge;
		this.userBirthday = userBirthday;
		this.userDesc = userDesc;
		this.userLastLoginTime = userLastLoginTime;
	}

	public CustUserInfo() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName == null ? null : userNickName.trim();
	}

	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl == null ? null : userHeadUrl.trim();
	}

	public Boolean getUserSex() {
		return userSex;
	}

	public void setUserSex(Boolean userSex) {
		this.userSex = userSex;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress == null ? null : userAddress.trim();
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge == null ? null : userAge.trim();
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday == null ? null : userBirthday.trim();
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