package pers.husen.highdsa.web.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.email.handler.AttachmentEmailSvc;

/**
 * @Desc 带附件的邮件REST API
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 上午9:27:56
 * 
 * @Version 1.0.2
 */
@RestController
@RequestMapping("/email/attachment/v1")
public class AttachmentEmailController {
	@Autowired
	AttachmentEmailSvc attachmentEmailSvc;

	@ResponseBody
	@RequestMapping(value = "/formal/2user.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendAttachmentEmail2User(String mailTo, String subject, String content, String attachment)
			throws JsonProcessingException {

		return attachmentEmailSvc.sendAttachmentEmail2User(mailTo, subject, content, attachment);
	}

	@ResponseBody
	@RequestMapping(value = "/formal/2admin.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail2Admin(@RequestParam(value = "name_from") String nameFrom,
			@RequestParam(value = "mail_from") String mailFrom, @RequestParam(value = "phone_from") String phoneFrom,
			String content, String attachment) throws JsonProcessingException {

		return attachmentEmailSvc.sendEmail2Admin(nameFrom, mailFrom, phoneFrom, content, attachment);
	}
}