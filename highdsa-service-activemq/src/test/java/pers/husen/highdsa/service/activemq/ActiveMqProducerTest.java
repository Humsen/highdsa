package pers.husen.highdsa.service.activemq;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.husen.highdsa.common.constant.MqDefination;
import pers.husen.highdsa.common.entity.vo.email.SimpleEmailParams;
import pers.husen.highdsa.service.activemq.producer.QueueMsgSender;
import pers.husen.highdsa.service.activemq.producer.TopicMsgSender;

/**
 * @Desc 测试消息生产者
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:36:20
 * 
 * @Version 1.0.0
 */
public class ActiveMqProducerTest {
	ClassPathXmlApplicationContext context;

	private QueueMsgSender queueSender;
	private TopicMsgSender topicSender;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		context.start();

		queueSender = (QueueMsgSender) context.getBean("queueMsgSender");
		topicSender = (TopicMsgSender) context.getBean("topicMsgSender");

		System.out.println("=============== activemq已经启动... ==================");
	}

	@Test
	public void testSendQueue() {
		SimpleEmailParams emailParams = new SimpleEmailParams();

		for (int i = 0; i < 100; i++) {
			emailParams.setMailTo("第 " + i + " 个收件人");

			queueSender.send(MqDefination.SIMPLE_EMAIL_QUEUE, emailParams);
		}
	}

	@Test
	public void testPublishTopic() {
		SimpleEmailParams emailParams = new SimpleEmailParams();

		for (int i = 0; i < 100; i++) {
			emailParams.setMailTo("第 " + i + " 个收件人");

			topicSender.publish(MqDefination.SIMPLE_EMAIL_QUEUE, emailParams);
		}
	}

	@After
	public void after() {
		try {
			// 为保证服务一直开着，利用输入流的阻塞来模拟
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("=============== 生产者关闭... ==================");
		context.close();
	}
}