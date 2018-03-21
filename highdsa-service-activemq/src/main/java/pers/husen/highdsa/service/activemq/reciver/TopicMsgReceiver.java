package pers.husen.highdsa.service.activemq.reciver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
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
 * @Version 1.0.0
 */
@Component("topicMsgReceiver")
public class TopicMsgReceiver extends MessageListenerAdapter {
	private static final Logger logger = LogManager.getLogger(TopicMsgReceiver.class.getName());

	@Override
	@JmsListener(destination = MqDefination.SIMPLE_EMAIL_TOPIC, concurrency = "5-10")
	public void onMessage(Message message, Session session) throws JMSException {
		try {
			SimpleEmailParams emailParams = (SimpleEmailParams) getMessageConverter().fromMessage(message);
			logger.info("订阅者收到：" + emailParams.getMailTo());
			logger.info("当前会话详情：{}", session);
			
			message.acknowledge();
		} catch (MessageConversionException | JMSException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}
}