package pers.husen.highdsa.email;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.api.EmailService;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.email.constants.ResponseConstants;
import pers.husen.highdsa.email.core.SendEmailCore;

/**
 * @Desc    发送邮件类，提供调用
 *
 * @Author  何明胜
 *
 * @Created at 2018年2月3日 下午5:22:44
 * 
 * @Version 1.0.0
 */
public class SendEmail implements EmailService{
	private final Logger logger = LogManager.getLogger(SendEmail.class.getName());
	
	@Override
	public int sendEmail2User(String email, int randomCode, String subject, String mode) {
		try {
			Session session = SendEmailCore.setupSession();

			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress("yige_robot@foxmail.com", "一格网站机器人", "UTF-8"));
			// 收件人电子邮箱 可用数组设置多个
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// 设置标题
			message.setSubject(subject);

			// 设置邮件内容 html文本
			String content = "尊敬的用户：" + "<br/>&emsp;&emsp;您好!" + "<br/>&emsp;&emsp;" + mode + "<br/>"
					+ "<br/>&emsp;&emsp;您的邮箱验证码【" + randomCode + "】，请于10分钟内输入，任何人都不会向您索取，请勿泄露。"
					+ "<br/>&emsp;&emsp;注：如果不是您发出的请求，说明您的邮箱可能被人冒用或者存在风险，请留意。" + "<br/><br/><br/>此致，一格";

			message.setContent(content, "text/html;charset=UTF-8");

			// 发送信息的工具
			Transport transport = session.getTransport();
			transport.connect();
			// 对方的地址
			transport.sendMessage(message, new Address[] { new InternetAddress(email) });
			// 关闭连接
			transport.close();

			logger.info("发送邮件成功! 收件人：" + email);

			return ResponseConstants.RESPONSE_OPERATION_SUCCESS;
		} catch (MessagingException | UnsupportedEncodingException | GeneralSecurityException mex) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(mex));
		}

		return ResponseConstants.RESPONSE_OPERATION_FAILURE;
	}

	@Override
	public int sendEmail2Admin(String name, String email, String phone, String content) {
		try {
			Session session = SendEmailCore.setupSession();

			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress("yige_robot@foxmail.com", "一格机器人", "UTF-8"));
			// 收件人电子邮箱 可用数组设置多个
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("husen@hemingsheng.cn"));

			// 设置标题
			message.setSubject("个人网站联系站长邮件");

			// 设置邮件内容 普通文本
			content = "我亲爱的站长：<br/>&emsp;&emsp;你好! 我是您的网站的一格机器人。<br/>&emsp;&emsp;现在有人通过您的网站\"联系站长\"功能给您发邮件，详情如下："
					+ "<br/>" + "<br/>&emsp;&emsp;姓名：" + name + "<br/>&emsp;&emsp;手机：" + phone + "<br/>&emsp;&emsp;邮箱："
					+ email + "<br/>&emsp;&emsp;邮件内容：<br/>&emsp;&emsp;&emsp;&emsp;" + content;
			message.setContent(content, "text/html;charset=UTF-8");

			// 发送信息的工具
			Transport transport = session.getTransport();
			transport.connect();
			// 对方的地址
			transport.sendMessage(message, new Address[] { new InternetAddress("husen@hemingsheng.cn") });
			// 关闭连接
			transport.close();

			logger.info("发送邮件给站长成功! 收件人：" + email);

			return 1;
		} catch (MessagingException | UnsupportedEncodingException | GeneralSecurityException mex) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(mex));
		}

		return 0;
	}
}
