package pers.husen.highdsa.service.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * @Desc 发送短信服务接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午8:40:21
 * 
 * @Version 1.0.0
 */
public interface SendSms {
	/**
	 * 发送短信
	 * 
	 * @param sendSmsRequest
	 * @return
	 * @throws ClientException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public SendSmsResponse sendSms(SendSmsRequest sendSmsRequest)
			throws ClientException, UnsupportedEncodingException, IOException;

	/**
	 * 查询发送结果详情
	 * 
	 * @param bizId
	 * @return
	 * @throws ClientException
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest querySendDetailsRequest)
			throws ClientException, UnsupportedEncodingException, IOException;
}