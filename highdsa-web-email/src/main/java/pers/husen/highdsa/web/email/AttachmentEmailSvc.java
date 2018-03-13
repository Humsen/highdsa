package pers.husen.highdsa.web.email;

import java.io.File;
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
import pers.husen.highdsa.service.email.EmailWithAttachment;
import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 调用带附件的邮件 业务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午8:29:40
 * 
 * @Version 1.0.0
 */
@Service
public class AttachmentEmailSvc {
	private final Logger logger = LogManager.getLogger(AttachmentEmailSvc.class.getName());

	@Autowired
	private EmailWithAttachment emailWithAttachment;

	public ResponseResult sendAttachmentEmail2User(String email, String subject, String content, String filepath) {
		File file = new File(filepath);
		int result = emailWithAttachment.sendEmail2User(email, subject, content, file);
		return test(result);
	}
	
	public ResponseResult sendEmail2Admin(String name, @RequestParam(value="sender_email")String senderEmail, String phone, String content, String filepath) {
		File file = new File(filepath);
		int result = emailWithAttachment.sendEmail2Admin(name, senderEmail, phone, content, file);

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