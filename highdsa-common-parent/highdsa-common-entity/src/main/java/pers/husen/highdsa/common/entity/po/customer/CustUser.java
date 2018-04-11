package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;

/**
 * @Desc 客户用户实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:36:36
 * 
 * @Version 1.0.0
 */
public class CustUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userName;

	private String userEmail;

	private String userPhone;

	private String userPassword;

	private String userPwdSalt;

	private String userState;

	@Override
	public String toString() {
		return "CustUser [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userPassword=" + userPassword + ", userPwdSalt=" + userPwdSalt
				+ ", userState=" + userState + "]";
	}

	public CustUser(Long userId, String userName, String userEmail, String userPhone, String userPassword, String userPwdSalt, String userState) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.userPwdSalt = userPwdSalt;
		this.userState = userState;
	}

	public CustUser() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail == null ? null : userEmail.trim();
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUserPwdSalt() {
		return userPwdSalt;
	}

	public void setUserPwdSalt(String userPwdSalt) {
		this.userPwdSalt = userPwdSalt == null ? null : userPwdSalt.trim();
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState == null ? null : userState.trim();
	}
}