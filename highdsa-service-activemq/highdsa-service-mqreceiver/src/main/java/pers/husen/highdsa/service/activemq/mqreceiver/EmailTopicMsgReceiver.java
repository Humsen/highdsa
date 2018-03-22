package pers.husen.highdsa.service.activemq.mqreceiver;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import pers.husen.highdsa.common.constant.MsgQueueDefine;
import pers.husen.highdsa.common.entity.vo.email.EmailParams;
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
@Component("topicMsgReceiver")
public class EmailTopicMsgReceiver extends MessageListenerAdapter {
	private static final Logger logger = LogManager.getLogger(EmailTopicMsgReceiver.class.getName());

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	/**
	 * 定义Topic名称,在配置文件中注入监听容器
	 * 
	 * @return
	 */
	@Bean(name = "topicDestination")
	private ActiveMQTopic initTopicDestination() {
		return new ActiveMQTopic(MsgQueueDefine.SIMPLE_EMAIL_TOPIC);
	}

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		try {
			EmailParams emailParams = (EmailParams) getMessageConverter().fromMessage(message);
			logger.info("订阅者收到：" + emailParams.getMailTo());
			logger.info("当前会话详情：{}", session);

			message.acknowledge();
		} catch (MessageConversionException | JMSException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			// 发送异常，重新放回队列
			jmsTopicTemplate.send(MsgQueueDefine.SIMPLE_EMAIL_TOPIC, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return jmsTopicTemplate.getMessageConverter().toMessage(message, session);
				}
			});
		}
	}
}