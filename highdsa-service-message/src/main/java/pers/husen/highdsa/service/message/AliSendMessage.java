package pers.husen.highdsa.service.message;

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

import pers.husen.highdsa.common.constant.MessageParams;
import pers.husen.highdsa.common.utility.ReadConfigFile;

/**
 * 
 * @Desc 阿里大于发送短信
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午3:30:59
 * 
 * @Version 1.0.0
 */
public class AliSendMessage {
	private static final Logger logger = LogManager.getLogger(AliSendMessage.class.getName());
	
	public static IAcsClient initIAcsClient() throws ClientException, UnsupportedEncodingException, IOException {
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		Properties properties = ReadConfigFile.readByRelativePath("message.properties");
		String accessKeyId = properties.getProperty("access_key_id");
		String accessKeySecret = properties.getProperty("access_key_secret");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", MessageParams.PRODUCT, MessageParams.DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		logger.info("初始化短信发送客户端完成");
		
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

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);

		logger.info("发送短信成功");
		
		return sendSmsResponse;
	}

	/**
	 * 查询发送结果详情
	 * 
	 * @param bizId
	 * @return
	 * @throws ClientException
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest querySendDetailsRequest)
			throws ClientException, UnsupportedEncodingException, IOException {
		IAcsClient acsClient = initIAcsClient();

		// hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(querySendDetailsRequest);

		logger.info("查询短信成功");
		
		return querySendDetailsResponse;
	}
}