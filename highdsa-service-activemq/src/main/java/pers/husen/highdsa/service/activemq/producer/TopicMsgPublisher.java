package pers.husen.highdsa.service.activemq.producer;

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
 * @Desc 订阅消息
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:13:28
 * 
 * @Version 1.0.1
 */
@Service("topicMsgSender")
public class TopicMsgPublisher {
	private static final Logger logger = LogManager.getLogger(TopicMsgPublisher.class.getName());

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	/**
	 * 发布一条对象消息
	 * 
	 * @param destination
	 *            目的地
	 * @param message
	 */
	public void publishMessage(String destination, final Object message) {
		jmsTopicTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return jmsTopicTemplate.getMessageConverter().toMessage(message, session);
			}
		});

		logger.info("destination:{}, message:{}", destination, message);
	}

	/**
	 * 发布一条文本消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void publishMessage(String destination, final String message) {
		jmsTopicTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});

		logger.info("destination:{}, message:{}", destination, message);
	}
}