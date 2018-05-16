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
 * @Version 1.0.3
 */
@RestController
@RequestMapping("/email/attachment/v1")
public class AttachmentEmailController {
	@Autowired
	AttachmentEmailSvc attachmentEmailSvc;

	@ResponseBody
	@RequestMapping(value = "/formal/2user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendAttachEmail2User(@RequestParam(value = "mail_to") String mailTo, String subject, String content, @RequestParam(value = "attach_url") String attachUrl,
			@RequestParam(value = "attach_name") String attachName) throws JsonProcessingException {

		return attachmentEmailSvc.sendAttachmentEmail2User(mailTo, subject, content, attachUrl, attachName);
	}

	@ResponseBody
	@RequestMapping(value = "/formal/2admin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendAttachEmail2Admin(@RequestParam(value = "name_from") String nameFrom, @RequestParam(value = "mail_from") String mailFrom, @RequestParam(value = "phone_from") String phoneFrom,
			String content, @RequestParam(value = "attach_url") String attachUrl, @RequestParam(value = "attach_name") String attachName) throws JsonProcessingException {

		return attachmentEmailSvc.sendAttachEmail2Admin(nameFrom, mailFrom, phoneFrom, content, attachUrl, attachName);
	}
}