package pers.husen.highdsa.common.po;

import java.io.Serializable;

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
	private String username;
	private String password;

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [className=" + className + ", username=" + username + ", password=" + password + "]";
	}
}