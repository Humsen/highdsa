package pers.husen.highdsa.common.entity.vo.email;

/**
 * @Desc 所有邮件参数在一个Vo,在队列接收端反序列化,发送邮件时只取相应的参数. 添加附件参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午10:28:19
 * 
 * @Version 1.0.0
 */
public class AttachEmailParams extends EmailParams {
	private static final long serialVersionUID = 4241493720885897931L;
	
	private String attachment;

	@Override
	public String toString() {
		return "AttachEmailParams [attachment=" + attachment + ", toString()=" + super.toString() + "]";
	}

	public AttachEmailParams() {
		super();
	}

	/**
	 * @param mailTo
	 * @param subject
	 * @param content
	 * @param mailFor
	 * @param attachment
	 */
	public AttachEmailParams(String mailTo, String subject, String content, String mailFor, String attachment) {
		super(mailTo, subject, content, mailFor);
		this.attachment = attachment;
	}

	/**
	 * @param nameFrom
	 * @param mailFrom
	 * @param phoneFrom
	 * @param content
	 * @param mailFor
	 * @param attachment
	 */
	public AttachEmailParams(String nameFrom, String mailFrom, String phoneFrom, String content, String mailFor,
			String attachment) {
		super(nameFrom, mailFrom, phoneFrom, content, mailFor);
		this.attachment = attachment;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}