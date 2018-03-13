package pers.husen.highdsa.web.email;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import pers.husen.highdsa.common.constant.ResponseConstants;
import pers.husen.highdsa.common.entity.vo.ResultFailureJson;
import pers.husen.highdsa.common.entity.vo.ResultSuccessJson;
import pers.husen.highdsa.common.response.OperationResult;
import pers.husen.highdsa.service.email.SimpleHtmlEmail;
import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 调用简单邮件 业务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午8:34:24
 * 
 * @Version 1.0.0
 */
@Service
public class SimpleEmailSvc {
	private final Logger logger = LogManager.getLogger(SimpleEmailSvc.class.getName());

	@Autowired
	private SimpleHtmlEmail simpleHtmlEmail;

	/** ------------ 发送给用户 --------------- */

	public ResponseResult sendEmail4RetrivePwd(String email, @RequestParam(value = "random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4RetrivePwd(email, randomCode);
		return test(result);
	}

	public ResponseResult sendEmail4Register(String email, @RequestParam(value = "random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4Register(email, randomCode);
		return test(result);
	}

	public ResponseResult sendEmail4ModufyEmailAuth(String email, @RequestParam(value = "random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4ModufyEmailAuth(email, randomCode);
		return test(result);
	}

	public ResponseResult sendEmail4ModufyEmailBind(String email, @RequestParam(value = "random_code") int randomCode) {
		int result = simpleHtmlEmail.sendEmail4ModufyEmailBind(email, randomCode);
		return test(result);
	}

	public ResponseResult sendEmail2User(String email, String subject, String content) {
		int result = simpleHtmlEmail.sendEmail2User(email, subject, content);

		return test(result);
	}

	/** --------------------- 分割线 ------------------------ */

	/** ------------ 发送给管理员 --------------- */

	public ResponseResult sendEmail4UserFeedback(String name, @RequestParam(value = "user_email") String userEmail,
			@RequestParam(value = "user_phone") String userPhone, String content) {
		int result = simpleHtmlEmail.sendEmail4UserFeedback(name, userEmail, userPhone, content);
		return test(result);
	}

	public ResponseResult sendEmail2Admin(String name, @RequestParam(value = "user_email") String userEmail,
			@RequestParam(value = "user_phone") String userPhone, String content) {
		int result = simpleHtmlEmail.sendEmail2Admin(name, userEmail, userPhone, content);

		return test(result);
	}

	/**
	 * 测试工具
	 * 
	 * @param isSuccess
	 * @return
	 */
	private ResponseResult test(int isSuccess) {
		OperationResult operationResult = null;

		if (isSuccess == ResponseConstants.RESPONSE_OPERATION_SUCCESS) {
			operationResult = new OperationResult(new ResultSuccessJson());
			Map<String, Object> resultJson = new HashMap<>(10);
			resultJson.put("测试", "成功");
			operationResult.setResultJson(resultJson);
			logger.info("发送成功");
		} else {
			operationResult = new OperationResult(new ResultFailureJson());
			Map<String, Object> resultJson = new HashMap<>(10);
			resultJson.put("测试", "失败");
			operationResult.setResultJson(resultJson);
			logger.info("发送失败");
		}

		return operationResult.getResultJsonVo();
	}
}