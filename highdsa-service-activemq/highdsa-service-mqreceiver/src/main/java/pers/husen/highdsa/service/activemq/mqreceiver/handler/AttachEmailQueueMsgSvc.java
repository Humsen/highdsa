package pers.husen.highdsa.service.activemq.mqreceiver.handler;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.common.BaseUtils;
import pers.husen.highdsa.common.entity.vo.email.AttachEmailParams;
import pers.husen.highdsa.service.email.AttachHtmlEmail;

/**
 * @Desc 队列消息接收带附件的邮件之后,反射调用这里的类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午10:13:35
 * 
 * @Version 1.0.3
 */
@Service
public class AttachEmailQueueMsgSvc {
	private static final Logger logger = LogManager.getLogger(AttachEmailQueueMsgSvc.class.getName());

	@Autowired
	private AttachHtmlEmail attachHtmlEmail;

	public void sendAttachEmail2User(AttachEmailParams attachEmailParams) throws JsonProcessingException {
		String mailTo = attachEmailParams.getMailTo();
		String subject = attachEmailParams.getSubject();
		String content = attachEmailParams.getContent();
		String attachUrl = attachEmailParams.getAttachUrl();

		// 如果url为空,则表明是使用文件附件.否则默认使用url附件.
		if (BaseUtils.isEmpty(attachUrl)) {
			File attachFile = attachEmailParams.getAttachFile();

			logger.debug("mailTo:{}, subject:{}, content:{}, attachFile：{}", mailTo, subject, content, attachFile);
			attachHtmlEmail.sendEmail2User(mailTo, subject, content, attachFile);

			return;
		}

		String attachName = attachEmailParams.getAttachName();

		logger.debug("mailTo:{}, subject:{}, content:{}, attachUrl：{}, attachName:{}", mailTo, subject, content, attachUrl, attachName);

		attachHtmlEmail.sendEmail2User(mailTo, subject, content, attachUrl, attachName);

	}

	public void sendAttachEmail2Admin(AttachEmailParams attachEmailParams) throws JsonProcessingException {
		String nameFrom = attachEmailParams.getNameFrom();
		String mailFrom = attachEmailParams.getMailFrom();
		String phoneFrom = attachEmailParams.getPhoneFrom();
		String content = attachEmailParams.getContent();
		String attachUrl = attachEmailParams.getAttachUrl();

		// 如果url为空,则表明是使用文件附件.否则默认使用url附件.
		if (BaseUtils.isEmpty(attachUrl)) {
			File attachFile = attachEmailParams.getAttachFile();

			logger.debug("nameFrom:{}, mailFrom:{}, phoneFrom:{}, content:{}, attachFile:{}", nameFrom, mailFrom, phoneFrom, content, attachFile);
			attachHtmlEmail.sendEmail2Admin(nameFrom, mailFrom, phoneFrom, content, attachFile);

			return;
		}

		String attachName = attachEmailParams.getAttachName();
		logger.debug("nameFrom:{}, mailFrom:{}, phoneFrom:{}, content:{}, attachUrl:{}, attachName:{}", nameFrom, mailFrom, phoneFrom, content, attachUrl, attachName);

		attachHtmlEmail.sendEmail2Admin(nameFrom, mailFrom, phoneFrom, content, attachUrl, attachName);
	}
}