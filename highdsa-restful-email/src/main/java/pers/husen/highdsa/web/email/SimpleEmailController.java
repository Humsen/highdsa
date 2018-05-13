package pers.husen.highdsa.web.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.email.handler.SimpleEmailSvc;

/**
 * @Desc 发送邮件消费者
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月4日 下午4:28:22
 * 
 * @Version 1.0.7
 */
@RestController
@RequestMapping("/email/simple/v1")
public class SimpleEmailController {
	@Autowired
	SimpleEmailSvc simpleEmailSvc;

	/* ------------ 发送给用户 --------------- */

	@ResponseBody
	@RequestMapping(value = "/code/retrive-pwd.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail4RetrivePwd(String email, @RequestParam(value = "random_code") int randomCode)
			throws JsonProcessingException {

		return simpleEmailSvc.sendEmail4RetrivePwd(email, randomCode);
	}

	@ResponseBody
	@RequestMapping(value = "/code/register.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail4Register(String email, @RequestParam(value = "random_code") int randomCode)
			throws JsonProcessingException {

		return simpleEmailSvc.sendEmail4Register(email, randomCode);
	}

	@ResponseBody
	@RequestMapping(value = "/code/modify-email-auth.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail4ModufyEmailAuth(String email, @RequestParam(value = "random_code") int randomCode)
			throws JsonProcessingException {

		return simpleEmailSvc.sendEmail4ModufyEmailAuth(email, randomCode);
	}

	@ResponseBody
	@RequestMapping(value = "/code/modify-email-bind.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail4ModufyEmailBind(String email, @RequestParam(value = "random_code") int randomCode)
			throws JsonProcessingException {

		return simpleEmailSvc.sendEmail4ModufyEmailBind(email, randomCode);
	}

	@ResponseBody
	@RequestMapping(value = "/formal/2user.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail2User(String email, String subject, String content) throws JsonProcessingException {

		return simpleEmailSvc.sendEmail2User(email, subject, content);
	}

	/* --------------------- 分割线 ------------------------ */

	/**
	 * ------------ 发送给管理员 ---------------
	 * 
	 * @throws JsonProcessingException
	 */

	@ResponseBody
	@RequestMapping(value = "/formal/user-feedback.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail4UserFeedback(String name, @RequestParam(value = "user_email") String userEmail,
			@RequestParam(value = "user_phone") String userPhone, String content) throws JsonProcessingException {

		return simpleEmailSvc.sendEmail4UserFeedback(name, userEmail, userPhone, content);
	}

	@ResponseBody
	@RequestMapping(value = "/formal/2admin.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendEmail2Admin(String name, @RequestParam(value = "user_email") String userEmail,
			@RequestParam(value = "user_phone") String userPhone, String content) throws JsonProcessingException {

		return simpleEmailSvc.sendEmail2Admin(name, userEmail, userPhone, content);
	}
}