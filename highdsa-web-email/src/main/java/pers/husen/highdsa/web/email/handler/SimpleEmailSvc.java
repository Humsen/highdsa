package pers.husen.highdsa.web.email.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.MsgQueueDefine;
import pers.husen.highdsa.common.entity.vo.SimpleJson;
import pers.husen.highdsa.common.entity.vo.email.EmailParams;
import pers.husen.highdsa.service.activemq.QueueMsgSender;

/**
 * @Desc 调用简单邮件 业务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午8:34:24
 * 
 * @Version 1.0.2
 */
@Service
public class SimpleEmailSvc {
	private static final Logger logger = LogManager.getLogger(SimpleEmailSvc.class.getName());

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private SimpleJson simpleJson;

	@Autowired
	private QueueMsgSender queueMsgSender;

	/* ------------ 发送给用户 --------------- */

	public String sendEmail4RetrivePwd(String emailTo, int randomCode) throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(emailTo, randomCode,
				Thread.currentThread().getStackTrace()[1].getMethodName());

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "找回密码邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendEmail4Register(String emailTo, int randomCode) throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(emailTo, randomCode,
				Thread.currentThread().getStackTrace()[1].getMethodName());

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "注册邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendEmail4ModufyEmailAuth(String emailTo, int randomCode) throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(emailTo, randomCode,
				Thread.currentThread().getStackTrace()[1].getMethodName());

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "修改密码验证邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendEmail4ModufyEmailBind(String emailTo, int randomCode) throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(emailTo, randomCode,
				Thread.currentThread().getStackTrace()[1].getMethodName());

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "修改密码绑定邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendEmail2User(String emailTo, String subject, String content) throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(emailTo, subject, content,
				Thread.currentThread().getStackTrace()[1].getMethodName());

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "发送给用户邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	/* --------------------- 分割线 ------------------------ */

	/** ------------ 发送给管理员 --------------- */

	public String sendEmail4UserFeedback(String nameFrom, String mailFrom, String phoneFrom, String content)
			throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(nameFrom, mailFrom, phoneFrom, content);

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "用户反馈邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendEmail2Admin(String nameFrom, String mailFrom, String phoneFrom, String content)
			throws JsonProcessingException {
		String reply = null;
		EmailParams simpleEmailParams = new EmailParams(nameFrom, mailFrom, phoneFrom, content);

		queueMsgSender.sendMessage(MsgQueueDefine.SIMPLE_EMAIL_QUEUE, simpleEmailParams);
		simpleJson = new SimpleJson(true, "发送给管理员邮件发送成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}
}