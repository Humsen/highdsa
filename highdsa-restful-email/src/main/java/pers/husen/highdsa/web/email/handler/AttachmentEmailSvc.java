package pers.husen.highdsa.web.email.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.MsgQueueDefine;
import pers.husen.highdsa.common.entity.vo.email.AttachEmailParams;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;
import pers.husen.highdsa.service.activemq.QueueMsgSender;

/**
 * @Desc 调用带附件的邮件 业务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午8:29:40
 * 
 * @Version 1.0.3
 */
@Service
public class AttachmentEmailSvc {
	private static final Logger logger = LogManager.getLogger(AttachmentEmailSvc.class.getName());

	private final ObjectMapper objectMapper = new ObjectMapper();
	private ResponseJson simpleJson;

	@Autowired
	private QueueMsgSender queueMsgSender;

	public String sendAttachmentEmail2User(String mailTo, String subject, String content, String attachUrl,
			String attachName) throws JsonProcessingException {
		String reply = null;

		AttachEmailParams attachEmailParams = new AttachEmailParams(mailTo, subject, content,
				Thread.currentThread().getStackTrace()[1].getMethodName(), attachUrl, attachName);

		queueMsgSender.sendMessage(MsgQueueDefine.ATTACH_EMAIL_QUEUE, attachEmailParams);
		simpleJson = new ResponseJson(true, "给用户发附件邮件成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendAttachEmail2Admin(String nameFrom, String mailFrom, String phoneFrom, String content,
			String attachUrl, String attachName) throws JsonProcessingException {
		String reply = null;

		AttachEmailParams attachEmailParams = new AttachEmailParams(nameFrom, mailFrom, phoneFrom, content,
				Thread.currentThread().getStackTrace()[1].getMethodName(), attachUrl, attachName);

		queueMsgSender.sendMessage(MsgQueueDefine.ATTACH_EMAIL_QUEUE, attachEmailParams);
		simpleJson = new ResponseJson(true, "给管理员发附件邮件成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}
}