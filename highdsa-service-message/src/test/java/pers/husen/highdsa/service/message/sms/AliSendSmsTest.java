package pers.husen.highdsa.service.message.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.utility.RandomCode;
import pers.husen.highdsa.service.message.sms.AliSendSms;
import pers.husen.highdsa.service.message.sms.core.SendSmsCore;
import pers.husen.highdsa.service.message.sms.template.SmsQueryParams;

/**
 * @Desc 测试阿里大于发送短信
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午5:00:41
 * 
 * @Version 1.0.3
 */
public class AliSendSmsTest {
	AliSendSms aliSendSms = new AliSendSms();

	@Test
	public void testSendSmsCaptcha() {
		String phoneNumber = "18626422426";
		String chptcha = RandomCode.producedRandomCodeStr(8);

		SendSmsResponse response = null;

		try {
			response = aliSendSms.sendSmsCaptcha(phoneNumber, chptcha);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		printSendResponse(response);
	}

	@Test
	public void testSendSmsNotice() {
		String phoneNumber = "18626422426";
		String userName = "何明胜";
		String chptcha = RandomCode.producedRandomCodeStr(7);

		SendSmsResponse response = null;

		try {
			response = aliSendSms.sendSmsNotice(phoneNumber, userName, chptcha);
		} catch (ClientException | IOException e) {
			e.printStackTrace();
		}

		printSendResponse(response);
	}

	/**
	 * 打印发送短信结果
	 * 
	 * @param response
	 */
	public void printSendResponse(SendSmsResponse response) {
		System.out.println("----------------短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());
	}

	@Test
	public void testQuerySendDetailsByBizId() {
		String phoneNumber = "18626422426";
		String bizId = "356008924791413222^0";

		QuerySendDetailsResponse querySendDetailsResponse = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String stringJson = objectMapper.writeValueAsString(aliSendSms.querySendDetailsByBizId(phoneNumber, bizId));
			querySendDetailsResponse = objectMapper.readValue(stringJson, QuerySendDetailsResponse.class);

		} catch (ClientException | IOException e) {
			e.printStackTrace();
		}

		printQueryResponse(querySendDetailsResponse);
	}

	@Test
	public void testQuerySendDetails() {
		SmsQueryParams smsQueryParams = new SmsQueryParams();

		smsQueryParams.setPhoneNumber("18626422426");
		smsQueryParams.setSendDate("20180313");
		smsQueryParams.setPageSize(10L);
		smsQueryParams.setCurrentPage(1L);
		// smsQueryParams.setBizId("489008620917931265^0");

		QuerySendDetailsRequest querySendDetailsRequest = smsQueryParams.getRequest();

		QuerySendDetailsResponse querySendDetailsResponse = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String stringJson = objectMapper.writeValueAsString(SendSmsCore.querySendDetails(querySendDetailsRequest));
			querySendDetailsResponse = objectMapper.readValue(stringJson, QuerySendDetailsResponse.class);
		} catch (ClientException | IOException e) {
			e.printStackTrace();
		}

		printQueryResponse(querySendDetailsResponse);
	}

	/**
	 * 打印查询结果
	 * 
	 * @param response
	 */
	public void printQueryResponse(QuerySendDetailsResponse response) {
		System.out.println("短信明细查询接口返回数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		int i = 0;
		for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : response.getSmsSendDetailDTOs()) {
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
		System.out.println("TotalCount=" + response.getTotalCount());
		System.out.println("RequestId=" + response.getRequestId());
	}
}