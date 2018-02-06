package pers.husen.highdsa.web.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.husen.highdsa.common.constant.ResponseConstants;
import pers.husen.highdsa.common.response.json.OperationResult;
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
	@Autowired
	private SimpleHtmlEmail simpleHtmlEmail;

	private ResponseResult test() {
		int result = ResponseConstants.RESPONSE_OPERATION_FAILURE;
		OperationResult operationResult = null;
		
		if(result == ResponseConstants.RESPONSE_OPERATION_SUCCESS) {
			operationResult = new OperationResult(new ResultSuccessJsonVo());
			Map<String, Object> resultJson = new HashMap<>();
			resultJson.put("测试", "成功");
			operationResult.setResultJson(resultJson);
		}else {
			operationResult = new OperationResult(new ResultFailureJsonVo());
			Map<String, Object> resultJson = new HashMap<>();
			resultJson.put("测试", "失败");
			operationResult.setResultJson(resultJson);
		}
		
		return operationResult.getResultJsonVo();
	}
	
	@RequestMapping(value = "/code/retrive-pwd.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4RetrivePwd(String email, @RequestParam(value="random_code") int randomCode) {
		//int result = simpleHtmlEmail.sendEmail4RetrivePwd(email, randomCode);
		return test();
	}

	@RequestMapping(value = "/code/register.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4Register(String email, @RequestParam(value="random_code") int randomCode) {
		//int result = simpleHtmlEmail.sendEmail4Register(email, randomCode);
		return test();
	}
	
	@RequestMapping(value = "/code/modify-email-auth.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4ModufyEmailAuth(String email, @RequestParam(value="random_code") int randomCode) {
		//int result = simpleHtmlEmail.ModufyEmailAuth(email, randomCode);
		return test();
	}
	
	@RequestMapping(value = "/code/modify-email-bind.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4ModufyEmailBind(String email, @RequestParam(value="random_code") int randomCode) {
		//int result = simpleHtmlEmail.sendEmail4ModufyEmailBind(email, randomCode);
		return test();
	}
	
	@RequestMapping("/formal/2user.hms")
	@ResponseBody
	public String sendEmail2User(String email, String subject, String content) {
		simpleHtmlEmail.sendEmail2User(email, subject, content);

		return "OK";
	}

	/** --------------------- 分割线 ------------------------*/
	
	@RequestMapping(value = "/formal/user-feedback.hms", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseResult sendEmail4UserFeedback(String name, String userEmail, String userPhone, String content) {
		//int result = simpleHtmlEmail.sendEmail4ModufyEmailBind(email, randomCode);
		return test();
	}
	
	@RequestMapping("/formal/2admin.hms")
	@ResponseBody
	public String sendEmail2Admin(String name, String userEmail, String userPhone, String content) {
		simpleHtmlEmail.sendEmail2Admin(name, userEmail, userPhone, content);

		return "OK";
	}

}