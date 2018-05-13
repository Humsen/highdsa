package pers.husen.highdsa.service.activemq;

/**
 * @Desc ActiveMQ队列（点对点）消息dubbo API
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月21日 下午2:20:54
 * 
 * @Version 1.0.0
 */
public interface QueueMsgSender {
	/**
	 * 发送对象消息
	 * 
	 * @param destination
	 *            目的地
	 * @param message
	 */
	public void sendMessage(String destination, final Object message);

	/**
	 * 发送文本消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void sendMessage(String destination, final String message);
}