package pers.husen.highdsa.web.email;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.husen.highdsa.common.constant.ResponseConstants;
import pers.husen.highdsa.common.response.OperationResult;
import pers.husen.highdsa.common.vo.ResultFailureJsonVo;
import pers.husen.highdsa.common.vo.ResultSuccessJsonVo;
import pers.husen.highdsa.service.email.SimpleHtmlEmail;
import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 发送邮件消费者
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月4日 下午4:28:22
 * 
 * @Version 1.0.4
 */
@RestController
@RequestMapping("/email/simple/v1")
public class SimpleEmail {
	private final Logger logger = LogManager.getLogger(SimpleEmail.class.getName());
	
	@Autowired
	private SimpleHtmlEmail simpleHtmlEmail;

	private ResponseResult test(int isSuccess) {
		OperationResult operationResult = null;
		
		if(isSuccess == ResponseConstants.RESPONSE_OPERATION_SUCCESS) {
			operationResult = new OperationResult(new ResultSuccessJsonVo());
			Map<String, Object> resultJson = new HashMap<>(10);
			resultJson.put("测试", "成功");
			operationResult.setResultJson(resultJson);
			logger.info("发送成功");
		}else {
			operationResult = new OperationResult(new ResultFailureJsonVo());
			Map<String, Object> resultJson = new HashMap<>(10);
			resultJson.put("测试", "失败");
			operationResult.setResultJson(resultJson);
			logger.info("发送失败");
		}
		
		return operationResult.getResultJsonVo();
	}
	
	/** ------------ 发送给用户  --------------- */
	
	@RequestMapping(value = "/code/retrive-pwd.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4RetrivePwd(String email, @RequestParam(value="random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4RetrivePwd(email, randomCode);
		return test(result);
	}

	@RequestMapping(value = "/code/register.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4Register(String email, @RequestParam(value="random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4Register(email, randomCode);
		return test(result);
	}
	
	@RequestMapping(value = "/code/modify-email-auth.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4ModufyEmailAuth(String email, @RequestParam(value="random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4ModufyEmailAuth(email, randomCode);
		return test(result);
	}
	
	@RequestMapping(value = "/code/modify-email-bind.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4ModufyEmailBind(String email, @RequestParam(value="random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4ModufyEmailBind(email, randomCode);
		return test(result);
	}
	
	@RequestMapping("/formal/2user.hms")
	@ResponseBody
	public ResponseResult sendEmail2User(String email, String subject, String content) {
		int result = simpleHtmlEmail.sendEmail2User(email, subject, content);

		return test(result);
	}

	/** --------------------- 分割线 ------------------------*/
	
	/** ------------ 发送给管理员  --------------- */
	
	@RequestMapping(value = "/formal/user-feedback.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4UserFeedback(String name, @RequestParam(value="user_email")String userEmail, @RequestParam(value="user_phone")String userPhone, String content) {
		int result = simpleHtmlEmail.sendEmail4UserFeedback(name, userEmail, userPhone, content);
		return test(result);
	}
	
	@RequestMapping("/formal/2admin.hms")
	@ResponseBody
	public ResponseResult sendEmail2Admin(String name, @RequestParam(value="user_email")String userEmail, @RequestParam(value="user_phone") String userPhone, String content) {
		int result = simpleHtmlEmail.sendEmail2Admin(name, userEmail, userPhone, content);

		return test(result);
	}
}