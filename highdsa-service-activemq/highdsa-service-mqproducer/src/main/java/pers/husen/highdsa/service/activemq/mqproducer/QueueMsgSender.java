package pers.husen.highdsa.service.activemq.mqproducer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * @Desc 队列消息发送
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:11:14
 * 
 * @Version 1.0.1
 */
@Resource
@Service("queueMsgSender")
public class QueueMsgSender {
	private static final Logger logger = LogManager.getLogger(QueueMsgSender.class.getName());

	/** 通过@Qualifier修饰符来注入对应的bean */
	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	/**
	 * 发送对象消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void sendMessage(String destination, final Object message) {
		jmsQueueTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return jmsQueueTemplate.getMessageConverter().toMessage(message, session);
			}
		});

		logger.info("destination:{}, message:{}", destination, message);
	}

	/**
	 * 发送文本消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void sendMessage(String destination, final String message) {
		jmsQueueTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});

		logger.info("destination:{}, message:{}", destination, message);
	}
}