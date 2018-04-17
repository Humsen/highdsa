package pers.husen.highdsa.common.entity.po.system;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 系统会话持久化
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 上午9:40:03
 * 
 * @Version 1.0.1
 */
public class SysSessions implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sessionId;

	private String sessionValue;

	private Date sessionCreateTime;

	private Date sessionLastModifyTime;

	private Boolean sessionValid;

	@Override
	public String toString() {
		return "SysSessions [sessionId=" + sessionId + ", sessionValue=" + sessionValue + ", sessionCreateTime=" + sessionCreateTime + ", sessionLastModifyTime=" + sessionLastModifyTime
				+ ", sessionValid=" + sessionValid + "]";
	}

	public SysSessions(String sessionId, String sessionValue, Date sessionCreateTime, Date sessionLastModifyTime, Boolean sessionValid) {
		this.sessionId = sessionId;
		this.sessionValue = sessionValue;
		this.sessionCreateTime = sessionCreateTime;
		this.sessionLastModifyTime = sessionLastModifyTime;
		this.sessionValid = sessionValid;
	}

	public SysSessions() {
		super();
	}

	public SysSessions(String sessionId, String sessionValue) {
		this.sessionId = sessionId;
		this.sessionValue = sessionValue;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId == null ? null : sessionId.trim();
	}

	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue == null ? null : sessionValue.trim();
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
}