package pers.husen.highdsa.common.constant;

/**
 * @Desc activemq消息队列名称的定义
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 下午4:21:40
 * 
 * @Version 1.0.0
 */
public class MqDefination {
	/* --------------------- 点对点模式 ----------------------*/
	/** 简单邮件的队列 */
	public static final String SIMPLE_EMAIL_QUEUE = "queue:pers.husen.service.email.simple";
	
	
	/* --------------------- 发布/订阅模式 ----------------------*/
	/** 简单邮件的队列 */
	public static final String SIMPLE_EMAIL_TOPIC = "topic:pers.husen.service.email.simple";
}