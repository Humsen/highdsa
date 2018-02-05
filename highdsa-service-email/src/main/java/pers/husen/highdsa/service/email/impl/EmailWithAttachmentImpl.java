package pers.husen.highdsa.service.email.impl;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.email.EmailWithAttachment;
import pers.husen.highdsa.service.email.core.SendEmailCore;

/**
 * @Desc 发送带有附件的邮件
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午5:30:54
 * 
 * @Version 1.0.1
 */
public class EmailWithAttachmentImpl implements EmailWithAttachment {
	private final Logger logger = LogManager.getLogger(EmailWithAttachmentImpl.class.getName());

	@Override
	public void sendEmail2User(String userEmail, String subject, String content, File attachment) {
		try {
			SendEmailCore sendEmailCore = new SendEmailCore();
			Session session = sendEmailCore.getSession("robot2user-mail.properties");

			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(sendEmailCore.getSenderEamilAddr(), sendEmailCore.getSenderNickName(),
					Encode.DEFAULT_ENCODE));

			// 收件人电子邮箱
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

			// 设置标题
			message.setSubject(subject);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);

			// 添加附件的内容
			if (attachment != null) {
				BodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				attachmentBodyPart.setDataHandler(new DataHandler(source));

				// MimeUtility.encodeWord可以避免文件名乱码
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
				multipart.addBodyPart(attachmentBodyPart);
			}

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();

			// 发送信息的工具
			Transport transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect();
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭连接
			transport.close();

			logger.info("发送邮件成功! 主题：{}, 收件人：{}, 内容：{}, 附件名称：{}", subject, userEmail, content, attachment.getName());

		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}

	@Override
	public void sendEmail2Admin(String name, String senderEmail, String phone, String content, File attachment) {
		try {
			SendEmailCore sendEmailCore = new SendEmailCore();
			Session session = sendEmailCore.getSession("all2admin-mail.properties");

			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(sendEmailCore.getSenderEamilAddr(), sendEmailCore.getSenderNickName(),
					Encode.DEFAULT_ENCODE));

			// 收件人电子邮箱
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(sendEmailCore.getRecipients()));

			// 设置标题
			message.setSubject("联系管理员邮件(带附件)");

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);

			// 添加附件的内容
			if (attachment != null) {
				BodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				attachmentBodyPart.setDataHandler(new DataHandler(source));

				// MimeUtility.encodeWord可以避免文件名乱码
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
				multipart.addBodyPart(attachmentBodyPart);
			}

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();

			// 发送信息的工具
			Transport transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect();
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭连接
			transport.close();

			logger.info("发送邮件给站长成功! 虚拟发件人：{}, 收件人：{}, 发件内容：{}, 附件名称：{}", senderEmail, sendEmailCore.getRecipients(),
					content, attachment);

		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}
}