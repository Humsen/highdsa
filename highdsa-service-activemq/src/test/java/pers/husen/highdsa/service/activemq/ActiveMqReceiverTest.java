package pers.husen.highdsa.service.activemq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Desc 测试消息接收
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月21日 上午9:44:47
 * 
 * @Version 1.0.0
 */
public class ActiveMqReceiverTest {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();
			System.out.println("=============== activemq 接收者已经启动... ==================");

			System.in.read();
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}