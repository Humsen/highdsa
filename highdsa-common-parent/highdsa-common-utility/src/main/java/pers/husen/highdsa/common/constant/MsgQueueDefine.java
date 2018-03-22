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
public class MsgQueueDefine {
	/* --------------------- 点对点模式 ----------------------*/
	
	/** 简单邮件的队列 */
	public static final String SIMPLE_EMAIL_QUEUE = "queue:pers.husen.service.email.simple";
	/** 附件邮件的队列 */
	public static final String ATTACH_EMAIL_QUEUE = "queue:pers.husen.service.email.attach";
	
	
	/* --------------------- 发布/订阅模式 ----------------------*/
	
	/** 简单邮件的订阅 */
	public static final String SIMPLE_EMAIL_TOPIC = "topic:pers.husen.service.email.simple";
	/** 附件邮件的队列 */
	public static final String ATTACH_EMAIL_TOPIC = "topic:pers.husen.service.email.attach";
}