package pers.husen.highdsa.service.message.sms.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import pers.husen.highdsa.common.constant.MessageConstants;
import pers.husen.highdsa.common.utility.ReadConfigFile;

/**
 * @Desc 阿里发送短信核心类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午7:37:35
 * 
 * @Version 1.0.2
 */
public class SendSmsCore {
	private static final Logger logger = LogManager.getLogger(SendSmsCore.class.getName());

	/**
	 * 初始化
	 * 
	 * @return
	 * @throws ClientException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static IAcsClient initIAcsClient() throws ClientException, UnsupportedEncodingException, IOException {
		// 可自助调整超时时间
		System.setProperty(MessageConstants.DEFAULT_CONNECT_TIMEOUT, MessageConstants.CONNECT_TIMEOUT_PERIOD);
		System.setProperty(MessageConstants.DEFAULT_READ_TIMEOUT, MessageConstants.READ_TIMEOUT_PERIOD);

		Properties properties = ReadConfigFile.readByRelativePath(MessageConstants.SMS_CONFIG_FILE);
		String accessKeyId = properties.getProperty(MessageConstants.ACCESS_KEY_ID);
		String accessKeySecret = properties.getProperty(MessageConstants.ACCESS_KEY_SECRET);

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile(MessageConstants.SMS_REGION_ID, accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint(MessageConstants.SMS_REGION_ID, MessageConstants.SMS_REGION_ID, MessageConstants.PRODUCT, MessageConstants.DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		logger.trace("初始化短信发送客户端完成");

		return acsClient;
	}

	/**
	 * 发送短信
	 * 
	 * @param sendSmsRequest
	 * @return
	 * @throws ClientException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static SendSmsResponse sendSms(SendSmsRequest sendSmsRequest) throws ClientException, UnsupportedEncodingException, IOException {
		IAcsClient acsClient = initIAcsClient();

		// hint 此处可能会抛出异常,注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);

		logger.trace("发送短信成功");

		return sendSmsResponse;
	}

	/**
	 * 查询发送结果详情
	 * 
	 * @param querySendDetailsRequest
	 * @return
	 * @throws ClientException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest querySendDetailsRequest) throws ClientException, UnsupportedEncodingException, IOException {
		IAcsClient acsClient = initIAcsClient();

		// hint 此处可能会抛出异常,注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(querySendDetailsRequest);

		logger.trace("查询短信成功");

		return querySendDetailsResponse;
	}
}