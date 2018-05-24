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

import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.entity.vo.message.SmsQueryRequest;
import pers.husen.highdsa.common.entity.vo.message.SmsSendResponse;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;
import pers.husen.highdsa.common.utility.RandomCode;
import pers.husen.highdsa.service.message.SendSms;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc 发送短信业务调用服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 上午10:11:18
 * 
 * @Version 1.0.7
 */
@Service
public class SendSmsSvc {
	private static final Logger logger = LogManager.getLogger(SendSmsSvc.class.getName());
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private SendSms sendSms;
	@Autowired
	private RedisOperation redisOperation;

	public String sendSmsCaptcha(String phoneNumber) throws UnsupportedEncodingException, ClientException, IOException {
		String reply = null;
		ResponseJson responseJson;
		String chptcha = RandomCode.producedRandomCodeStr6();

		logger.trace("缓存验证码:{}", chptcha);
		// 验证码缓存到redis
		redisOperation.set(RedisCacheConstants.REGISTER_REDIS_CODE + phoneNumber, chptcha, 3600);

		try {
			SmsSendResponse smsSendResponse = sendSms.sendSmsCaptcha(phoneNumber, chptcha);
			logger.info("返回结果为: {}", smsSendResponse);

			responseJson = new ResponseJson(true, chptcha);
		} catch (Exception e) {
			responseJson = new ResponseJson(false, chptcha);
		}

		reply = objectMapper.writeValueAsString(responseJson);

		return reply;
	}

	public String validateSmsCaptcha(String phoneNumber, String captcha) throws UnsupportedEncodingException, ClientException, IOException {
		String reply = null;
		ResponseJson simpleJson;

		String cacheCode = redisOperation.get(RedisCacheConstants.REGISTER_REDIS_CODE + phoneNumber);
		logger.trace("缓存的验证码:{}, 用户输入的验证码:{}", cacheCode, captcha);

		if (captcha != null && captcha.equals(cacheCode)) {
			simpleJson = new ResponseJson(true, "手机验证成功");
		} else {
			simpleJson = new ResponseJson(false, "手机验证失败");
		}

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	public String sendSmsNotice(String phoneNumber, String userName, String chptcha) throws UnsupportedEncodingException, ClientException, IOException {
		SmsSendResponse smsSendResponse = sendSms.sendSmsNotice(phoneNumber, userName, chptcha);
		logger.trace("发送通知, 手机号:{}, 姓名:{}, 验证码:{}", phoneNumber, chptcha, chptcha);

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