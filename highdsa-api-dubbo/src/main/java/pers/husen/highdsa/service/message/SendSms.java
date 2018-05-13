package pers.husen.highdsa.service.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.aliyuncs.exceptions.ClientException;

import pers.husen.highdsa.common.entity.vo.message.SmsQueryRequest;
import pers.husen.highdsa.common.entity.vo.message.SmsSendResponse;

/**
 * @Desc 发送短信服务接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午8:40:21
 * 
 * @Version 1.0.4
 */
public interface SendSms {
	/**
	 * 发送短信验证码
	 * 
	 * @param phoneNumber
	 * @param chptcha
	 *            验证码
	 * @return
	 * @throws IOException
	 * @throws ClientException
	 * @throws UnsupportedEncodingException
	 */
	public SmsSendResponse sendSmsCaptcha(String phoneNumber, String chptcha) throws UnsupportedEncodingException, ClientException, IOException;

	/**
	 * 发送短信通知
	 * 
	 * @param phoneNumber
	 * @param userName
	 * @param chptcha
	 * @return
	 * @throws IOException
	 * @throws ClientException
	 * @throws UnsupportedEncodingException
	 */
	public SmsSendResponse sendSmsNotice(String phoneNumber, String userName, String chptcha) throws UnsupportedEncodingException, ClientException, IOException;

	/**
	 * 根据流水号查询发送结果详情
	 * 
	 * @param phoneNumber
	 * @param bizId
	 *            流水号
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ClientException
	 * @throws IOException
	 */
	public Map<?, ?> querySendDetailsByBizId(String phoneNumber, String bizId) throws UnsupportedEncodingException, ClientException, IOException;

	/**
	 * 多条件查询发送结果详情
	 * 
	 * @param smsQueryRequest
	 * @return
	 * @throws IOException
	 * @throws ClientException
	 * @throws UnsupportedEncodingException
	 */
	public Map<?, ?> querySendDetails(SmsQueryRequest smsQueryRequest) throws UnsupportedEncodingException, ClientException, IOException;
}