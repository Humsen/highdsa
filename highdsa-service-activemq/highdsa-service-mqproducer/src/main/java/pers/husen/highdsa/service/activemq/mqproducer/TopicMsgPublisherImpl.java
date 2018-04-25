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

import pers.husen.highdsa.service.activemq.TopicMsgPublisher;

/**
 * @Desc 订阅消息,所有订阅这个topic的服务都能得到这个消息
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:13:28
 * 
 * @Version 1.0.2
 */
@Service("topicMsgPublisher")
public class TopicMsgPublisherImpl implements TopicMsgPublisher {
	private static final Logger logger = LogManager.getLogger(TopicMsgPublisherImpl.class.getName());

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	@Override
	public void publishMessage(String destination, final Object message) {
		jmsTopicTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return jmsTopicTemplate.getMessageConverter().toMessage(message, session);
			}
		});

		logger.info("destination:{}, message:{}", destination, message);
	}

	@Override
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