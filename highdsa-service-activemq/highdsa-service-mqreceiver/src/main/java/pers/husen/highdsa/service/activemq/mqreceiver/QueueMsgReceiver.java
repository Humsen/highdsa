package pers.husen.highdsa.service.activemq.mqreceiver;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import pers.husen.highdsa.common.constant.MqDefination;
import pers.husen.highdsa.common.entity.vo.email.SimpleEmailParams;
import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc 队列接收，根据队列名称调用相关服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:16:02
 * 
 * @Version 1.0.1
 */
@Component("queueMsgReceiver")
public class QueueMsgReceiver extends MessageListenerAdapter {
	private static final Logger logger = LogManager.getLogger(QueueMsgReceiver.class.getName());

	/** 通过@Qualifier修饰符来注入对应的bean */
	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	@Override
	@JmsListener(containerFactory="jmsListenerContainerFactory", destination = MqDefination.SIMPLE_EMAIL_QUEUE, concurrency = "5-10")
	public synchronized void onMessage(Message message, Session session) throws JMSException {
		try {
			// TextMessage msg = (TextMessage) message;
			// final String ms = msg.getText();
			SimpleEmailParams emailParams = (SimpleEmailParams) getMessageConverter().fromMessage(message);
			logger.info("点对点收到：" + emailParams.getMailTo());
			logger.info("当前会话详情：{}", session);

			message.acknowledge();
		} catch (MessageConversionException | JMSException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			// 发送异常，重新放回队列
			jmsQueueTemplate.send(MqDefination.SIMPLE_EMAIL_QUEUE, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return jmsQueueTemplate.getMessageConverter().toMessage(message, session);
				}
			});
		}
	}
}