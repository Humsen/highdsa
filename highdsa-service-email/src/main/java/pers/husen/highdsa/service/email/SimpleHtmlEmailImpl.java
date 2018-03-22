package pers.husen.highdsa.service.email;

import java.io.IOException;
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

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.constant.ResponseConstants;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.email.SimpleHtmlEmail;
import pers.husen.highdsa.service.email.core.SaveEmail;
import pers.husen.highdsa.service.email.core.SendEmailCore;

/**
 * @Desc 发送邮件类，提供调用
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月3日 下午5:22:44
 * 
 * @Version 1.0.3
 */
public class SimpleHtmlEmailImpl implements SimpleHtmlEmail {
	private final Logger logger = LogManager.getLogger(SimpleHtmlEmailImpl.class.getName());

	/** ------------ 发送给用户  --------------- */
	
	@Override
	public int sendEmail4RetrivePwd(String email, int randomCode) {
		String subject = "【highdsa项目组】找回密码邮箱验证";
		String mode = "您正在使用找回密码功能。";
		String content = "尊敬的用户：" + "<br/>&emsp;&emsp;您好!" + "<br/>&emsp;&emsp;" + mode + "<br/>"
				+ "<br/>&emsp;&emsp;您的邮箱验证码【" + randomCode + "】，请于10分钟内输入，任何人都不会向您索取，请勿泄露。"
				+ "<br/>&emsp;&emsp;注：如果不是您发出的请求，说明您的邮箱可能被人冒用或者存在风险，请留意。" + "<br/><br/><br/>此致，highdsa项目组";

		logger.info("发送邮件给用户-->找回密码 <method:sendEmail4RetrivePwd>");
		return sendEmail2User(email, subject, content);
	}

	@Override
	public int sendEmail4Register(String email, int randomCode) {
		String subject = "【highdsa项目组】新用户注册邮箱验证";
		String mode = "欢迎在【highdsa项目组】注册账号。";
		String content = "尊敬的用户：" + "<br/>&emsp;&emsp;您好!" + "<br/>&emsp;&emsp;" + mode + "<br/>"
				+ "<br/>&emsp;&emsp;您的邮箱验证码【" + randomCode + "】，请于10分钟内输入，任何人都不会向您索取，请勿泄露。"
				+ "<br/>&emsp;&emsp;注：如果不是您发出的请求，说明您的邮箱可能被人冒用或者存在风险，请留意。" + "<br/><br/><br/>此致，highdsa项目组";

		logger.info("发送邮件给用户-->注册 <method:sendEmail4Register>");
		return sendEmail2User(email, subject, content);
	}

	@Override
	public int sendEmail4ModifyEmailAuth(String email, int randomCode) {
		String subject = "【highdsa项目组】用户修改邮箱验证原邮箱";
		String mode = "您正在使用修改邮箱功能。第一步，验证码您的原邮箱。";
		String content = "尊敬的用户：" + "<br/>&emsp;&emsp;您好!" + "<br/>&emsp;&emsp;" + mode + "<br/>"
				+ "<br/>&emsp;&emsp;您的邮箱验证码【" + randomCode + "】，请于10分钟内输入，任何人都不会向您索取，请勿泄露。"
				+ "<br/>&emsp;&emsp;注：如果不是您发出的请求，说明您的邮箱可能被人冒用或者存在风险，请留意。" + "<br/><br/><br/>此致，highdsa项目组";

		logger.info("发送邮件给用户-->修改邮箱验证 <method:sendEmail4ModufyEmailAuth>");
		return sendEmail2User(email, subject, content);
	}

	@Override
	public int sendEmail4ModifyEmailBind(String email, int randomCode) {
		String subject = "【highdsa项目组】用户修改邮箱绑定新邮箱";
		String mode = "您正在使用修改邮箱功能。第二步，绑定您的新邮箱。";
		String content = "尊敬的用户：" + "<br/>&emsp;&emsp;您好!" + "<br/>&emsp;&emsp;" + mode + "<br/>"
				+ "<br/>&emsp;&emsp;您的邮箱验证码【" + randomCode + "】，请于10分钟内输入，任何人都不会向您索取，请勿泄露。"
				+ "<br/>&emsp;&emsp;注：如果不是您发出的请求，说明您的邮箱可能被人冒用或者存在风险，请留意。" + "<br/><br/><br/>此致，highdsa项目组";

		logger.info("发送邮件给用户-->修改邮箱绑定新邮箱 <method:sendEmail4ModufyEmailBind>");
		return sendEmail2User(email, subject, content);
	}

	@Override
	public int sendEmail2User(String userEmail, String subject, String content) {
		try {
			SendEmailCore sendEmailCore = new SendEmailCore();
			Session session = sendEmailCore.getSession("email/robot2user-mail.properties");

			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(sendEmailCore.getSenderEamilAddr(), sendEmailCore.getSenderNickName(),
					Encode.DEFAULT_ENCODE));
			// 收件人电子邮箱 可用数组设置多个
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

			// 设置标题
			message.setSubject(subject);

			// 设置邮件内容 html文本
			message.setContent(content, "text/html;charset=UTF-8");

			// 发送信息的工具
			Transport transport = session.getTransport();
			transport.connect();
			// 对方的地址
			transport.sendMessage(message, new Address[] { new InternetAddress(userEmail) });
			// 关闭连接
			transport.close();

			logger.info("发送邮件成功! 主题：{}, 收件人：{}, 内容：{}", subject, userEmail, content);

			// 保存邮件
			new SaveEmail(message);

			return ResponseConstants.RESPONSE_OPERATION_SUCCESS;
		} catch (MessagingException | GeneralSecurityException | IOException mex) {
			logger.error("发送邮件给用户-->出错\n{}",StackTrace2Str.exceptionStackTrace2Str(mex));
		}

		return ResponseConstants.RESPONSE_OPERATION_FAILURE;
	}

	/** ------------------------- 分割线 ----------------------- */

	/** ------------ 发送给管理员 --------------- */
	
	@Override
	public int sendEmail4UserFeedback(String name, String userEmail, String phone, String content) {
		content = "highdsa项目组：<br/>&emsp;&emsp;你好! 我是您的机器人。<br/>&emsp;&emsp;现在有人通过您的\"联系管理员\"功能给您发邮件，详情如下：" + "<br/>"
				+ "<br/>&emsp;&emsp;姓名：" + name + "<br/>&emsp;&emsp;手机：" + phone + "<br/>&emsp;&emsp;邮箱：" + userEmail
				+ "<br/>&emsp;&emsp;邮件内容：<br/>&emsp;&emsp;&emsp;&emsp;" + content;

		logger.info("发送邮件给管理员-->用户反馈 <method:sendEmail4UserFeedback>");
		return sendEmail2Admin(name, userEmail, phone, content);
	}

	@Override
	public int sendEmail2Admin(String name, String senderEmail, String phone, String content) {
		try {
			SendEmailCore sendEmailCore = new SendEmailCore();
			Session session = sendEmailCore.getSession("email/all2admin-mail.properties");

			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(sendEmailCore.getSenderEamilAddr(), sendEmailCore.getSenderNickName(),
					Encode.DEFAULT_ENCODE));
			// 收件人电子邮箱 可用数组设置多个
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(sendEmailCore.getRecipients()));

			// 设置标题
			message.setSubject("联系管理员邮件");

			// 设置邮件内容 普通文本
			message.setContent(content, "text/html;charset=UTF-8");

			// 发送信息的工具
			Transport transport = session.getTransport();
			transport.connect();
			// 对方的地址
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭连接
			transport.close();

			logger.info("发送邮件给站长成功! 虚拟发件人：{}, 收件人：{}, 发件内容：{}", senderEmail, sendEmailCore.getRecipients(), content);

			// 保存邮件
			new SaveEmail(message);
			
			return ResponseConstants.RESPONSE_OPERATION_SUCCESS;
		} catch (MessagingException | UnsupportedEncodingException | GeneralSecurityException mex) {
			logger.error("发送邮件给管理员-->出错\n{}", StackTrace2Str.exceptionStackTrace2Str(mex));
		}

		return ResponseConstants.RESPONSE_OPERATION_FAILURE;
	}
}