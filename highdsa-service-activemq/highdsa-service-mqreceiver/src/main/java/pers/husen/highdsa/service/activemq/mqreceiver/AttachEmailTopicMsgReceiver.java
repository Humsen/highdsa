package pers.husen.highdsa.service.activemq.mqreceiver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import pers.husen.highdsa.common.constant.MsgQueueDefine;
import pers.husen.highdsa.common.entity.vo.email.AttachEmailParams;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.activemq.mqreceiver.handler.AttachEmailQueueMsgSvc;

/**
 * @Desc 主题订阅(一对多),根据队列名称调用相关服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:16:02
 * 
 * @Version 1.0.1
 */
@Component("attachEmailTopicMsgReceiver")
public class AttachEmailTopicMsgReceiver extends MessageListenerAdapter {
	private static final Logger logger = LogManager.getLogger(AttachEmailTopicMsgReceiver.class.getName());

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	@Autowired
	AttachEmailQueueMsgSvc attachEmailQueueMsgSvc;

	/**
	 * 定义Topic名称,在配置文件中的bean(messageListenerContainer)注入监听容器
	 * 
	 * @return
	 */
	@Bean(name = "attachEmailTopicMsgDestination")
	private ActiveMQTopic initTopicDestination() {
		return new ActiveMQTopic(MsgQueueDefine.ATTACH_EMAIL_TOPIC);
	}

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		try {
			AttachEmailParams attachEmailParams = (AttachEmailParams) getMessageConverter().fromMessage(message);
			logger.trace("订阅者收到：" + attachEmailParams.getMailTo());
			logger.debug("当前会话详情：{}", session);

			// 根据函数名称和参数类型获取方法
			Method method = AttachEmailQueueMsgSvc.class.getMethod(attachEmailParams.getMailFor(), AttachEmailParams.class);
			method.invoke(attachEmailQueueMsgSvc, attachEmailParams);

			message.acknowledge();
		} catch (MessageConversionException | JMSException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.warn(StackTrace2Str.exceptionStackTrace2Str("发送异常,重新放回队列", e));

			// 发送异常,重新放回队列
			jmsTopicTemplate.send(MsgQueueDefine.ATTACH_EMAIL_TOPIC, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return jmsTopicTemplate.getMessageConverter().toMessage(message, session);
				}
			});
		}
	}
}