package pers.husen.highdsa.web.message.sms.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;

import pers.husen.highdsa.common.entity.vo.message.SmsQueryRequest;
import pers.husen.highdsa.common.entity.vo.message.SmsSendResponse;
import pers.husen.highdsa.service.message.SendSms;

/**
 * @Desc 发送短信业务调用服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 上午10:11:18
 * 
 * @Version 1.0.3
 */
@Service
public class SendSmsSvc {
	private static final Logger logger = LogManager.getLogger(SendSmsSvc.class.getName());
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private SendSms sendSms;

	public String sendSmsCaptcha(String phoneNumber, String chptcha) throws UnsupportedEncodingException, ClientException, IOException {
		SmsSendResponse smsSendResponse = sendSms.sendSmsCaptcha(phoneNumber, chptcha);

		String reply = objectMapper.writeValueAsString(smsSendResponse);
		logger.info("返回结果为: {}", reply);

		return reply;
	}

	public String sendSmsNotice(String phoneNumber, String userName, String chptcha) throws UnsupportedEncodingException, ClientException, IOException {
		SmsSendResponse smsSendResponse = sendSms.sendSmsNotice(phoneNumber, userName, chptcha);

		String reply = objectMapper.writeValueAsString(smsSendResponse);
		logger.info("返回结果为: {}", reply);

		return reply;
	}

	public String querySendDetailsByBizId(String phoneNumber, String bizId) throws UnsupportedEncodingException, ClientException, IOException {
		Map<?, ?> smsQueryResponse = sendSms.querySendDetailsByBizId(phoneNumber, bizId);

		String reply = objectMapper.writeValueAsString(smsQueryResponse);
		logger.info("返回结果为: {}", reply);

		return reply;
	}

	public String querySendDetails(Map<String, String> jsonMap) throws UnsupportedEncodingException, ClientException, IOException {
		String jsonStr = objectMapper.writeValueAsString(jsonMap);
		objectMapper.setPropertyNamingStrategy(SnakeCaseStrategy.SNAKE_CASE);
		SmsQueryRequest smsQueryRequest = objectMapper.readValue(jsonStr, SmsQueryRequest.class);

		Map<?, ?> smsQueryResponse = sendSms.querySendDetails(smsQueryRequest);
		String reply = objectMapper.writeValueAsString(smsQueryResponse);

		logger.info("返回结果为: {}", reply);

		return reply;
	}
}