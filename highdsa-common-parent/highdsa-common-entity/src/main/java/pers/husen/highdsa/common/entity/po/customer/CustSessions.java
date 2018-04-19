package pers.husen.highdsa.common.entity.po.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 客户会话持久化
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:30:47
 * 
 * @Version 1.0.0
 */
public class CustSessions implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sessionId;

	private Date sessionCreateTime;

	private Date sessionLastModifyTime;

	private Boolean sessionValid;

	private String sessionValue;

	@Override
	public String toString() {
		return "CustSessions [sessionId=" + sessionId + ", sessionCreateTime=" + sessionCreateTime + ", sessionLastModifyTime=" + sessionLastModifyTime + ", sessionValid=" + sessionValid
				+ ", sessionValue=" + sessionValue + "]";
	}

	public CustSessions(String sessionId, Date sessionCreateTime, Date sessionLastModifyTime, Boolean sessionValid, String sessionValue) {
		this.sessionId = sessionId;
		this.sessionCreateTime = sessionCreateTime;
		this.sessionLastModifyTime = sessionLastModifyTime;
		this.sessionValid = sessionValid;
		this.sessionValue = sessionValue;
	}

	public CustSessions() {
		super();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId == null ? null : sessionId.trim();
	}

	public Date getSessionCreateTime() {
		return sessionCreateTime;
	}

	public void setSessionCreateTime(Date sessionCreateTime) {
		this.sessionCreateTime = sessionCreateTime;
	}

	public Date getSessionLastModifyTime() {
		return sessionLastModifyTime;
	}

	public void setSessionLastModifyTime(Date sessionLastModifyTime) {
		this.sessionLastModifyTime = sessionLastModifyTime;
	}

	public Boolean getSessionValid() {
		return sessionValid;
	}

	public void setSessionValid(Boolean sessionValid) {
		this.sessionValid = sessionValid;
	}

	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue == null ? null : sessionValue.trim();
	}
}