package pers.husen.highdsa.service.message;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Desc 测试注册短信和推送服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午1:03:55
 * 
 * @Version 1.0.1
 */
public class RegisterMessageService {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/dubbo-message.xml" });
		context.start();
		
		System.out.println("========== 短信和消息服务已经启动... ===========");
		
		System.in.read();
		context.close();
	}
}