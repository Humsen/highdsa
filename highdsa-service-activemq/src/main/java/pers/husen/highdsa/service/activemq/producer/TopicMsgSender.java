package pers.husen.highdsa.service.activemq.producer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @Desc 订阅消息
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:13:28
 * 
 * @Version 1.0.0
 */
@Component("topicMsgSender")
public class TopicMsgSender {
	private static final Logger logger = LogManager.getLogger(TopicMsgSender.class.getName());
	
	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param destination
	 *            目的地
	 * @param message
	 */
	public void publish(String destination, final Object message) {
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return jmsTemplate.getMessageConverter().toMessage(message, session);
			}
		});
		
		logger.info("destination:{}, message:{}", destination, message);
	}
}