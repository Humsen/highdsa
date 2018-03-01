package pers.husen.highdsa.common.po;

import java.io.Serializable;

/**
 * @Desc 测试用的po
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月1日 下午4:59:24
 * 
 * @Version 1.0.0
 */
public class UserInfoPo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserInfoPo [username=" + username + ", password=" + password + "]";
	}
}