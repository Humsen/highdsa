package pers.husen.highdsa.web.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 带附件的邮件REST API
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 上午9:27:56
 * 
 * @Version 1.0.1
 */
@RestController
@RequestMapping("/email/attachment/v1")
public class AttachmentEmailController {
	@Autowired
	AttachmentEmailSvc attachmentEmailSvc;

	@RequestMapping(value = "/formal/2user.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult sendAttachmentEmail2User(String email, String subject, String content, String filepath) {
		
		return attachmentEmailSvc.sendAttachmentEmail2User(email, subject, content, filepath);
	}

	@RequestMapping(value = "/formal/2admin.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseResult sendEmail2Admin(String name, @RequestParam(value = "sender_email") String senderEmail,
			String phone, String content, String filepath) {
		
		return attachmentEmailSvc.sendEmail2Admin(name, senderEmail, phone, content, filepath);
	}
}