package pers.husen.highdsa.common.entity.vo.email;

import java.io.Serializable;

/**
 * @Desc 所有邮件参数在一个Vo,在队列接收端反序列化,发送邮件时只取相应的参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:24:34
 * 
 * @Version 1.0.0
 */
public class EmailParams implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mailTo;
	private Integer randomCode;
	/** 主题 */
	private String subject;
	private String content;

	private String nameFrom;
	private String mailFrom;
	private String phoneFrom;

	/** 发送邮件目标,用于反射调用对应函数 */
	private String mailFor;

	@Override
	public String toString() {
		return "SimpleEmailParams [mailTo=" + mailTo + ", randomCode=" + randomCode + ", subject=" + subject
				+ ", content=" + content + ", nameFrom=" + nameFrom + ", mailFrom=" + mailFrom + ", phoneFrom="
				+ phoneFrom + ", mailFor=" + mailFor + "]";
	}

	public EmailParams() {
	}

	/**
	 * @param mailTo
	 * @param randomCode
	 */
	public EmailParams(String mailTo, Integer randomCode, String mailFor) {
		super();
		this.mailTo = mailTo;
		this.randomCode = randomCode;
		this.mailFor = mailFor;
	}

	/**
	 * @param mailTo
	 * @param subject
	 * @param content
	 */
	public EmailParams(String mailTo, String subject, String content, String mailFor) {
		super();
		this.mailTo = mailTo;
		this.subject = subject;
		this.content = content;
		this.mailFor = mailFor;
	}

	/**
	 * @param content
	 * @param nameFrom
	 * @param mailFrom
	 * @param phoneFrom
	 */
	public EmailParams(String nameFrom, String mailFrom, String phoneFrom, String content, String mailFor) {
		super();
		this.content = content;
		this.nameFrom = nameFrom;
		this.mailFrom = mailFrom;
		this.phoneFrom = phoneFrom;
		this.mailFor = mailFor;
	}

	/**
	 * @return the mailFor
	 */
	public String getMailFor() {
		return mailFor;
	}

	/**
	 * @param mailFor
	 *            the mailFor to set
	 */
	public void setMailFor(String mailFor) {
		this.mailFor = mailFor;
	}

	/**
	 * @return the randomCode
	 */
	public Integer getRandomCode() {
		return randomCode;
	}

	/**
	 * @param randomCode
	 *            the randomCode to set
	 */
	public void setRandomCode(Integer randomCode) {
		this.randomCode = randomCode;
	}

	/**
	 * @return the nameFrom
	 */
	public String getNameFrom() {
		return nameFrom;
	}

	/**
	 * @param nameFrom
	 *            the nameFrom to set
	 */
	public void setNameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param mailFrom
	 *            the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @return the phoneFrom
	 */
	public String getPhoneFrom() {
		return phoneFrom;
	}

	/**
	 * @param phoneFrom
	 *            the phoneFrom to set
	 */
	public void setPhoneFrom(String phoneFrom) {
		this.phoneFrom = phoneFrom;
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
	 * @param subject
	 *            the subject to set
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
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}