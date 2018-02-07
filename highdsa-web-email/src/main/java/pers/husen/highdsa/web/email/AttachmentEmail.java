package pers.husen.highdsa.web.email;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.husen.highdsa.common.constant.ResponseConstants;
import pers.husen.highdsa.common.response.json.OperationResult;
import pers.husen.highdsa.common.vo.ResultFailureJsonVo;
import pers.husen.highdsa.common.vo.ResultSuccessJsonVo;
import pers.husen.highdsa.service.email.EmailWithAttachment;
import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 带附件的邮件REST API
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 上午9:27:56
 * 
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/email/attachment/v1")
public class AttachmentEmail {
	private final Logger logger = LogManager.getLogger(AttachmentEmail.class.getName());
	
	@Autowired
	private EmailWithAttachment emailWithAttachment;

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
	
	@RequestMapping("/formal/2user.hms")
	@ResponseBody
	public ResponseResult sendAttachmentEmail2User(String email, String subject, String content, String filepath) {
		File file = new File(filepath);
		int result = emailWithAttachment.sendEmail2User(email, subject, content, file);
		return test(result);
	}
	
	@RequestMapping("/formal/2admin.hms")
	@ResponseBody
	public ResponseResult sendEmail2Admin(String name, String senderEmail, String phone, String content, String filepath) {
		File file = new File(filepath);
		int result = emailWithAttachment.sendEmail2Admin(name, senderEmail, phone, content, file);

		return test(result);
	}
}