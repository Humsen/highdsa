package pers.husen.highdsa.service.activemq.mqreceiver.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.common.entity.vo.email.AttachEmailParams;
import pers.husen.highdsa.service.email.AttachHtmlEmail;

/**
 * @Desc 队列消息接收带附件的邮件之后，反射调用这里的类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午10:13:35
 * 
 * @Version 1.0.0
 */
public class AttachEmailQueueMsgSvc {
	private static final Logger logger = LogManager.getLogger(AttachEmailQueueMsgSvc.class.getName());

	@Autowired
	private AttachHtmlEmail attachHtmlEmail;

	public void sendAttachmentEmail2User(AttachEmailParams attachEmailParams) throws JsonProcessingException {
		String mailTo = attachEmailParams.getMailTo();
		String subject = attachEmailParams.getSubject();
		String content = attachEmailParams.getContent();
		String attachment = attachEmailParams.getAttachment();

		logger.info("mailTo:{}, subject:{}, content:{}, attachment：{}", mailTo, subject, content, attachment);

		attachHtmlEmail.sendEmail2User(mailTo, subject, content, attachment);
	}

	public void sendEmail2Admin(AttachEmailParams attachEmailParams) throws JsonProcessingException {
		String nameFrom = attachEmailParams.getNameFrom();
		String mailFrom = attachEmailParams.getMailFrom();
		String phoneFrom = attachEmailParams.getPhoneFrom();
		String content = attachEmailParams.getContent();
		String attachment = attachEmailParams.getAttachment();

		logger.info("nameFrom:{}, mailFrom:{}, phoneFrom:{}, content:{}, attachment:{}", nameFrom, mailFrom, phoneFrom,
				content, attachment);

		attachHtmlEmail.sendEmail2Admin(nameFrom, mailFrom, phoneFrom, content, attachment);
	}
}