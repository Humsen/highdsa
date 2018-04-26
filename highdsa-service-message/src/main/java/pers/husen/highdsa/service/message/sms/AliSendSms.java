package pers.husen.highdsa.service.message.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

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
import pers.husen.highdsa.common.entity.vo.message.RegisterCaptcha;
import pers.husen.highdsa.common.entity.vo.message.SmsQueryRequest;
import pers.husen.highdsa.common.entity.vo.message.SmsSendResponse;
import pers.husen.highdsa.common.entity.vo.message.UserAdvice;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.message.SendSms;
import pers.husen.highdsa.service.message.sms.core.SendSmsCore;
import pers.husen.highdsa.service.message.sms.template.SmsQueryParams;
import pers.husen.highdsa.service.message.sms.template.SmsSendParams;

/**
 * 
 * @Desc 阿里大于发送短信
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午3:30:59
 * 
 * @Version 1.0.1
 */
public class AliSendSms implements SendSms {
	private static final Logger logger = LogManager.getLogger(AliSendSms.class.getName());
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public SmsSendResponse sendSmsCaptcha(String phoneNumber, String templateId, String chptcha)
			throws UnsupportedEncodingException, ClientException, IOException {
		RegisterCaptcha registerCaptchaVo = new RegisterCaptcha(chptcha);

		String templateParam = null;

		try {
			templateParam = mapper.writeValueAsString(registerCaptchaVo);
		} catch (JsonProcessingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		SmsSendParams smsSendParams = new SmsSendParams();
		smsSendParams.setSignName(MessageParams.SIGN_NAME);
		// 现在写死了,以后要动态设置为templateId
		smsSendParams.setTemplateCode(MessageParams.TEMPLATE_REGISTER);
		smsSendParams.setPhoneNumbers(phoneNumber);
		smsSendParams.setTemplateParam(templateParam);

		smsSendParams.setOutId("husenOutId");

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = smsSendParams.getRequest();

		// 发短信
		SendSmsResponse sendSmsResponse = SendSmsCore.sendSms(request);
		String responseJson = mapper.writeValueAsString(sendSmsResponse);
		SmsSendResponse response = mapper.readValue(responseJson, SmsSendResponse.class);

		logger.info("发送成功, {}", mapper.writeValueAsString(request));

		return response;
	}

	@Override
	public SmsSendResponse sendSmsNotice(String phoneNumber, String userName, String chptcha)
			throws UnsupportedEncodingException, ClientException, IOException {
		UserAdvice userAdviceVo = new UserAdvice(userName, chptcha);

		String templateParam = null;

		try {
			templateParam = mapper.writeValueAsString(userAdviceVo);
		} catch (JsonProcessingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		SmsSendParams smsSendParams = new SmsSendParams();
		smsSendParams.setSignName(MessageParams.SIGN_NAME);

		smsSendParams.setTemplateCode(MessageParams.TEMPLATE_ADVICE);
		smsSendParams.setPhoneNumbers(phoneNumber);
		smsSendParams.setTemplateParam(templateParam);

		smsSendParams.setOutId("husenOutId");

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = smsSendParams.getRequest();

		// 发短信
		SendSmsResponse sendSmsResponse = SendSmsCore.sendSms(request);
		String responseJson = mapper.writeValueAsString(sendSmsResponse);
		SmsSendResponse response = mapper.readValue(responseJson, SmsSendResponse.class);

		logger.info("发送成功, {}", mapper.writeValueAsString(request));

		return response;
	}

	@Override
	public Map<?, ?> querySendDetailsByBizId(String phoneNumber, String bizId)
			throws UnsupportedEncodingException, ClientException, IOException {
		SmsQueryParams smsQueryParams = new SmsQueryParams();

		smsQueryParams.setPhoneNumber(phoneNumber);
		smsQueryParams.setSendDate("20180313");
		smsQueryParams.setPageSize(10L);
		smsQueryParams.setCurrentPage(1L);
		smsQueryParams.setBizId(bizId);

		QuerySendDetailsRequest querySendDetailsRequest = smsQueryParams.getRequest();

		QuerySendDetailsResponse querySendDetailsResponse = SendSmsCore.querySendDetails(querySendDetailsRequest);
		String responseJson = mapper.writeValueAsString(querySendDetailsResponse);
		Map<?, ?> response = mapper.readValue(responseJson, Map.class);

		logger.info("查询成功, {}", mapper.writeValueAsString(querySendDetailsRequest));

		return response;
	}

	@Override
	public Map<?, ?> querySendDetails(SmsQueryRequest paramMap)
			throws UnsupportedEncodingException, ClientException, IOException {

		String paramMapJson = mapper.writeValueAsString(paramMap);
		QuerySendDetailsRequest smsQueryRequest = mapper.readValue(paramMapJson, QuerySendDetailsRequest.class);

		QuerySendDetailsResponse querySendDetailsResponse = SendSmsCore.querySendDetails(smsQueryRequest);
		String responseJson = mapper.writeValueAsString(querySendDetailsResponse);
		Map<?, ?> response = mapper.readValue(responseJson, Map.class);

		logger.info("查询成功, {}", mapper.writeValueAsString(smsQueryRequest));

		return response;
	}
}