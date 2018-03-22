package pers.husen.highdsa.service.activemq;

/**
 * @Desc ActiveMQ主题（一对多）消息dubbo API
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月21日 下午2:22:42
 * 
 * @Version 1.0.0
 */
public interface TopicMsgPublisher {
	/**
	 * 发布一条对象消息
	 * 
	 * @param destination
	 *            目的地
	 * @param message
	 */
	public void publishMessage(String destination, final Object message);

	/**
	 * 发布一条文本消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void publishMessage(String destination, final String message);
}