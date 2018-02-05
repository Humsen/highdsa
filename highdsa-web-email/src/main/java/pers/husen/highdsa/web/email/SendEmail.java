package pers.husen.highdsa.web.email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.husen.highdsa.service.email.EmailWithAttachment;
import pers.husen.highdsa.service.email.SimpleHtmlEmail;

/**
 * @Desc 发送邮件消费者
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月4日 下午4:28:22
 * 
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/service/email/v1")
public class SendEmail {
	@Autowired
	private SimpleHtmlEmail simpleHtmlEmail;

	@Autowired
	private EmailWithAttachment emailWithAttachment;

	@RequestMapping("/sendEmail2User.hms")
	@ResponseBody
	public String sendEmail2User(String email, String subject, String content) {
		simpleHtmlEmail.sendEmail2User(email, subject, content);

		return "OK";
	}

	@RequestMapping("/sendEmail2Admin.hms")
	@ResponseBody
	public String sendEmail2Admin(String name, String email, String phone, String content) {
		simpleHtmlEmail.sendEmail2Admin(name, email, phone, content);

		return "OK";
	}

	@RequestMapping("/sendAttachmentEmail2User.hms")
	@ResponseBody
	public String sendAttachmentEmail2User(String email, String subject, String content, File file) {
		emailWithAttachment.sendEmail2User(email, subject, content, file);

		return "OK";
	}
}