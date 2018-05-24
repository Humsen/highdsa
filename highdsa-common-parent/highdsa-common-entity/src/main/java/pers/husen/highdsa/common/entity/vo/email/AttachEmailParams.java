package pers.husen.highdsa.common.entity.vo.email;

import java.io.File;

/**
 * @Desc 所有邮件参数在一个Vo,在队列接收端反序列化,发送邮件时只取相应的参数. 添加附件参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午10:28:19
 * 
 * @Version 1.0.1
 */
public class AttachEmailParams extends EmailParams {
	private static final long serialVersionUID = 4241493720885897931L;

	/** http url */
	private String attachUrl;
	/** 附件名称 */
	private String attachName;

	/** 附件文件 */
	private File attachFile;

	@Override
	public String toString() {
		return "AttachEmailParams [attachUrl=" + attachUrl + ", attachName=" + attachName + ", attachFile=" + attachFile + ", toString()=" + super.toString() + "]";
	}

	public AttachEmailParams() {
		super();
	}

	/**
	 * @param mailTo
	 * @param subject
	 * @param content
	 * @param mailFor
	 * @param attachUrl
	 */
	public AttachEmailParams(String mailTo, String subject, String content, String mailFor, String attachUrl, String attachName) {
		super(mailTo, subject, content, mailFor);
		this.attachUrl = attachUrl;
		this.attachName = attachName;
	}

	/**
	 * @param nameFrom
	 * @param mailFrom
	 * @param phoneFrom
	 * @param content
	 * @param mailFor
	 * @param attachment
	 */
	public AttachEmailParams(String nameFrom, String mailFrom, String phoneFrom, String content, String mailFor, String attachUrl, String attachName) {
		super(nameFrom, mailFrom, phoneFrom, content, mailFor);
		this.attachUrl = attachUrl;
		this.attachName = attachName;
	}

	/**
	 * @return the attachUrl
	 */
	public String getAttachUrl() {
		return attachUrl;
	}

	/**
	 * @param attachUrl
	 *            the attachUrl to set
	 */
	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}

	/**
	 * @return the attachName
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * @param attachName
	 *            the attachName to set
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/**
	 * @return the attachFile
	 */
	public File getAttachFile() {
		return attachFile;
	}

	/**
	 * @param attachFile
	 *            the attachFile to set
	 */
	public void setAttachFile(File attachFile) {
		this.attachFile = attachFile;
	}
}