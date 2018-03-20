package pers.husen.highdsa.common.entity.vo.email;

import java.io.Serializable;

/**
 * @Desc 邮件参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:24:34
 * 
 * @Version 1.0.0
 */
public class SimpleEmailParams implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mailTo;
	/** 主题 */
	private String subject;
	private String content;

	@Override
	public String toString() {
		return "SimpleEmailParams [mailTo=" + mailTo + ", subject=" + subject + ", content=" + content + "]";
	}
	
	/**
	 * @return the mailTo
	 */
	public String getMailTo() {
		return mailTo;
	}

	/**
	 * @param mailTo
	 *            the mailTo to set
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}