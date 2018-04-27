package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import pers.husen.highdsa.common.entity.constants.CustUserState;
import pers.husen.highdsa.common.entity.po.system.SysUser;

/**
 * @Desc 客户用户实体类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午1:36:36
 * 
 * @Version 1.0.2
 */
public class CustUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userName;

	private String userEmail;

	private String userPhone;

	private String userPassword;

	private String userPwdSalt;

	private CustUserState userState;

	private List<CustRole> custRoleList;

	private List<CustRolePermission> custRolePermissionList;

	@Override
	public String toString() {
		return "CustUser [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userPassword=" + userPassword + ", userPwdSalt=" + userPwdSalt
				+ ", userState=" + userState + ", custRoleList=" + custRoleList + ", custRolePermissionList=" + custRolePermissionList + "]";
	}

	public CustUser(Long userId, String userName, String userEmail, String userPhone, String userPassword, String userPwdSalt, CustUserState userState) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.userPwdSalt = userPwdSalt;
		this.userState = userState;
	}

	/**
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @param userPhone
	 * @param userPassword
	 * @param userPwdSalt
	 */
	public CustUser(Long userId, String userName, String userEmail, String userPhone, String userPassword, String userPwdSalt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.userPwdSalt = userPwdSalt;
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

	public CustUserState getUserState() {
		return userState;
	}

	public void setUserState(CustUserState userState) {
		this.userState = userState;
	}

	/**
	 * @return the custRoleList
	 */
	public List<CustRole> getCustRoleList() {
		return custRoleList;
	}

	/**
	 * @param custRoleList
	 *            the custRoleList to set
	 */
	public void setCustRoleList(List<CustRole> custRoleList) {
		this.custRoleList = custRoleList;
	}

	/**
	 * @return the custRolePermissionList
	 */
	public List<CustRolePermission> getCustRolePermissionList() {
		return custRolePermissionList;
	}

	/**
	 * @param custRolePermissionList
	 *            the custRolePermissionList to set
	 */
	public void setCustRolePermissionList(List<CustRolePermission> custRolePermissionList) {
		this.custRolePermissionList = custRolePermissionList;
	}

	/**
	 * 重写equals和hashCode来定义判断相等的规则
	 */
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (!(object instanceof SysUser)) {
			return false;
		}
		CustUser user = (CustUser) object;

		return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(userPhone, user.userPhone)
				&& Objects.equals(userPassword, user.userPassword) && Objects.equals(userPwdSalt, user.userPwdSalt) && Objects.equals(userState, user.userState);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, userPassword);
	}
}