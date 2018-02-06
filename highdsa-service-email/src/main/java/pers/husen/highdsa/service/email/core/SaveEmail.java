package pers.husen.highdsa.service.email.core;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @Desc 保存邮件到已发送
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 下午5:36:33
 * 
 * @Version 1.0.0
 */
public class SaveEmail {
	public SaveEmail(MimeMessage msg, Properties properties) throws MessagingException, IOException {
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
		saveIntoSent(msg, properties);
	}

	public void saveIntoSent(MimeMessage msg, Properties properties) throws MessagingException, IOException {
		try {
			properties.setProperty("mail.store.protocol", "imap"); 
			properties.setProperty("mail.imap.host", "imap.qq.com");
			properties.setProperty("mail.imap.port", "993");
			properties.put("mail.imap.ssl.enable", "true");
			
			// 开启SSL
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			// 3、开启认证
			properties.put("mail.imap.auth", "true");
			// 4、开启SSL
			
			properties.put("mail.imap.ssl.socketFactory", sf);

			//
			properties.setProperty("mail.imap.socketFactory.fallback", "false");
			properties.setProperty("mail.imap.starttls.enable", "true");
			properties.setProperty("mail.imap.socketFactory.port", "993");
			//

			Session imapSession = Session.getInstance(properties, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("yige_robot@foxmail.com", "xjsvsdyrekodfiah");
				}
			});
			IMAPStore store = (IMAPStore) imapSession.getStore("imap");
			store.connect("yige_robot@foxmail.com", "xjsvsdyrekodfiah");
			IMAPFolder folder = (IMAPFolder) store.getFolder("Sent Messages");
			if (!folder.exists()) {
				folder.create(Folder.HOLDS_MESSAGES);
			}
			folder.open(Folder.READ_WRITE);
			folder.appendMessages(new Message[] { msg });
			folder.expunge();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}