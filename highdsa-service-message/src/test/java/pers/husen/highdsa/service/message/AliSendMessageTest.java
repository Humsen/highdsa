package pers.husen.highdsa.service.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.constant.MessageParams;
import pers.husen.highdsa.common.entity.vo.message.UserAdvice;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.message.sms.AliSendSms;
import pers.husen.highdsa.service.message.sms.template.SmsQueryParams;
import pers.husen.highdsa.service.message.sms.template.SmsSendParams;

/**
 * @Desc 测试阿里大于发送短信
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午5:00:41
 * 
 * @Version 1.0.0
 */
public class AliSendMessageTest {
	public static void main(String[] args)
			throws ClientException, InterruptedException, UnsupportedEncodingException, IOException {
		Logger logger = LogManager.getLogger(AliSendMessageTest.class.getName());

		/** 1. */
		String phoneNumbers = "18626422426";
		// String phoneNumbers = "13883534569";

		String name = "何明胜";
		String code = "123456";

		/** 2. */
		// RegisterCaptcha registerCaptchaVo = new RegisterCaptcha(code);
		UserAdvice userAdviceVo = new UserAdvice(name, code);

		String templateParam = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			/** 3. */
			// templateParam = mapper.writeValueAsString(registerCaptchaVo);
			templateParam = mapper.writeValueAsString(userAdviceVo);
		} catch (JsonProcessingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		SmsSendParams smsSendParams = new SmsSendParams();
		smsSendParams.setSignName(MessageParams.SIGN_NAME);
		/** 4. */
		// smsSendParams.setTemplateCode(MessageParams.TEMPLATE_REGISTER);
		smsSendParams.setTemplateCode(MessageParams.TEMPLATE_ADVICE);
		smsSendParams.setPhoneNumbers(phoneNumbers);
		smsSendParams.setTemplateParam(templateParam);

		smsSendParams.setOutId("husenOutId");

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = smsSendParams.getRequest();

		// 发短信
		SendSmsResponse response = new AliSendSms().sendSms(request);
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());

		Thread.sleep(3000L);

		SmsQueryParams smsQueryParams = new SmsQueryParams();
		smsQueryParams.setPhoneNumber(phoneNumbers);
		smsQueryParams.setSendDate("20180311");
		smsQueryParams.setPageSize(10L);
		smsQueryParams.setCurrentPage(1L);
		smsQueryParams.setBizId(response.getBizId());

		QuerySendDetailsRequest querySendDetailsRequest = smsQueryParams.getRequest();

		// 查明细
		String replyCode = "OK";
		if (response.getCode() != null && response.getCode().equals(replyCode)) {
			QuerySendDetailsResponse querySendDetailsResponse = new AliSendSms()
					.querySendDetails(querySendDetailsRequest);
			System.out.println("短信明细查询接口返回数据----------------");
			System.out.println("Code=" + querySendDetailsResponse.getCode());
			System.out.println("Message=" + querySendDetailsResponse.getMessage());
			int i = 0;
			for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse
					.getSmsSendDetailDTOs()) {
				System.out.println("SmsSendDetailDTO[" + i + "]:");
				System.out.println("Content=" + smsSendDetailDTO.getContent());
				System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
				System.out.println("OutId=" + smsSendDetailDTO.getOutId());
				System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
				System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
				System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
				System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
				System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
			}
			System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
			System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
		}
	}
}