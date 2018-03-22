package pers.husen.highdsa.service.activemq.mqreceiver.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.vo.email.EmailParams;
import pers.husen.highdsa.service.email.SimpleHtmlEmail;

/**
 * @Desc 队列消息接收简单邮件之后，反射调用这里的类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午9:10:46
 * 
 * @Version 1.0.1
 */
@Service
public class EmailQueueMsgSvc {
	private static final Logger logger = LogManager.getLogger(EmailQueueMsgSvc.class.getName());

	@Autowired
	private SimpleHtmlEmail simpleHtmlEmail;

	/** ------------ 发送给用户 --------------- */

	public void sendEmail4RetrivePwd(EmailParams emailParams) {
		String mailTo = emailParams.getMailTo();
		int randomCode = emailParams.getRandomCode();

		logger.info("mailTo:{}, randomCode:{}", mailTo, randomCode);

		simpleHtmlEmail.sendEmail4RetrivePwd(mailTo, randomCode);
	}

	public void sendEmail4Register(EmailParams emailParams) {
		String mailTo = emailParams.getMailTo();
		int randomCode = emailParams.getRandomCode();

		logger.info("mailTo:{}, randomCode:{}", mailTo, randomCode);

		simpleHtmlEmail.sendEmail4Register(mailTo, randomCode);
	}

	public void sendEmail4ModufyEmailAuth(EmailParams emailParams) {
		String mailTo = emailParams.getMailTo();
		int randomCode = emailParams.getRandomCode();

		logger.info("mailTo:{}, randomCode:{}", mailTo, randomCode);

		simpleHtmlEmail.sendEmail4ModifyEmailAuth(mailTo, randomCode);
	}

	public void sendEmail4ModufyEmailBind(EmailParams emailParams) {
		String mailTo = emailParams.getMailTo();
		int randomCode = emailParams.getRandomCode();

		logger.info("mailTo:{}, randomCode:{}", mailTo, randomCode);

		simpleHtmlEmail.sendEmail4ModifyEmailBind(mailTo, randomCode);
	}

	public void sendEmail2User(EmailParams emailParams) {
		String mailTo = emailParams.getMailTo();
		String subject = emailParams.getSubject();
		String content = emailParams.getContent();

		logger.info("mailTo:{}, subject:{}, content:{}", mailTo, subject, content);

		simpleHtmlEmail.sendEmail2User(mailTo, subject, content);
	}

	/* --------------------- 分割线 ------------------------ */

	/** ------------ 发送给管理员 --------------- */

	public void sendEmail4UserFeedback(EmailParams emailParams) {
		String nameFrom = emailParams.getNameFrom();
		String mailFrom = emailParams.getMailFrom();
		String phoneFrom = emailParams.getPhoneFrom();
		String content = emailParams.getContent();

		logger.info("nameFrom:{}, mailFrom:{}, phoneFrom:{}, content:{}", nameFrom, mailFrom, phoneFrom, content);

		simpleHtmlEmail.sendEmail4UserFeedback(nameFrom, mailFrom, phoneFrom, content);
	}

	public void sendEmail2Admin(EmailParams emailParams) {
		String nameFrom = emailParams.getNameFrom();
		String mailFrom = emailParams.getMailFrom();
		String phoneFrom = emailParams.getPhoneFrom();
		String content = emailParams.getContent();

		logger.info("nameFrom:{}, mailFrom:{}, phoneFrom:{}, content:{}", nameFrom, mailFrom, phoneFrom, content);

		simpleHtmlEmail.sendEmail2Admin(nameFrom, mailFrom, phoneFrom, content);
	}
}