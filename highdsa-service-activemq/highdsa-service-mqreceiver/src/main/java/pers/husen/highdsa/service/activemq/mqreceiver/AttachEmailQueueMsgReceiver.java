package pers.husen.highdsa.service.activemq.mqreceiver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import pers.husen.highdsa.common.constant.MsgQueueDefine;
import pers.husen.highdsa.common.entity.vo.email.AttachEmailParams;
import pers.husen.highdsa.common.exception.HighdsaException;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.activemq.mqreceiver.handler.AttachEmailQueueMsgSvc;

/**
 * @Desc 队列接收,根据队列名称调用相关服务.这里是发送带附件的邮件.
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月26日 上午9:16:14
 * 
 * @Version 1.0.0
 */
@Component("attachEmailQueueMsgReceiver")
public class AttachEmailQueueMsgReceiver extends MessageListenerAdapter {
	private static final Logger logger = LogManager.getLogger(AttachEmailQueueMsgReceiver.class.getName());

	/** 通过@Qualifier修饰符来注入对应的bean */
	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	@Autowired
	AttachEmailQueueMsgSvc attachEmailQueueMsgSvc;

	@Override
	@JmsListener(containerFactory = "jmsListenerContainerFactory", destination = MsgQueueDefine.ATTACH_EMAIL_QUEUE, concurrency = "5-10")
	public synchronized void onMessage(Message message, Session session) throws JMSException {
		try {
			AttachEmailParams attachEmailParams = (AttachEmailParams) getMessageConverter().fromMessage(message);

			logger.trace("带附件点对点收到：{}", attachEmailParams.getMailTo());
			logger.debug("当前会话详情：{}", session);

			// 根据函数名称和参数类型获取方法
			Method method = AttachEmailQueueMsgSvc.class.getMethod(attachEmailParams.getMailFor(), AttachEmailParams.class);
			method.invoke(attachEmailQueueMsgSvc, attachEmailParams);

			message.acknowledge();
		} catch (MessageConversionException | JMSException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.warn(StackTrace2Str.exceptionStackTrace2Str("业务异常,避免循环调用,不再重新放回队列", e));

			// 业务异常,重新放回队列
			/*
			 * jmsQueueTemplate.send(MsgQueueDefine.ATTACH_EMAIL_QUEUE, new MessageCreator()
			 * {
			 * 
			 * @Override public Message createMessage(Session session) throws JMSException {
			 * return jmsQueueTemplate.getMessageConverter().toMessage(message, session); }
			 * });
			 */

			throw new HighdsaException("发送带附件邮件异常,避免循环调用,不再重新放回队列", e);
		}
	}
}