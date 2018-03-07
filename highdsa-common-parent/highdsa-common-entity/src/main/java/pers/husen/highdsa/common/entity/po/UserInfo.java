package pers.husen.highdsa.common.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Desc 用户信息表
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月5日 下午1:44:04
 * 
 * @Version 1.0.0
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 存放对应的PO类名称，通过反射实例化
	 */
	@JsonProperty(value = "class_name")
	private String className;
	
	private Integer userId;
	private String userName;
	private String userPassword;
	private Date userCreateDate;
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the userCreateDate
	 */
	public Date getUserCreateDate() {
		return userCreateDate;
	}
	/**
	 * @param userCreateDate the userCreateDate to set
	 */
	public void setUserCreateDate(Date userCreateDate) {
		this.userCreateDate = userCreateDate;
	}
	
	@Override
	public String toString() {
		return "UserInfo [className=" + className + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userCreateDate=" + userCreateDate + "]";
	}
}