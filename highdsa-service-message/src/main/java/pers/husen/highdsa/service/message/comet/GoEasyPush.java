package pers.husen.highdsa.service.message.comet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import pers.husen.highdsa.common.utility.ReadConfigFile;

/**
 * @Desc GoEasy 推送方案
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午1:24:53
 * 
 * @Version 1.0.0
 */
public class GoEasyPush extends PublishListener {
	private static final Logger logger = LogManager.getLogger(GoEasyPush.class.getName());

	GoEasy goEasy = null;
	Boolean result = null;

	/**
	 * 初始化go easy
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void init() throws UnsupportedEncodingException, IOException {
		Properties properties = ReadConfigFile.readByRelativePath("appkey.properties");

		String restHost = properties.getProperty("rest_host");
		String appKey = properties.getProperty("common_key");

		goEasy = new GoEasy(restHost, appKey);
	}

	public Boolean publish(String channel, String content) throws UnsupportedEncodingException, IOException {
		if(goEasy == null) {
			init();
		}
		
		goEasy.publish(channel, content, new GoEasyPush());

		return result;
	}

	@Override
	public void onSuccess() {
		logger.info("消息发布成功");
		result = true;
	}

	@Override
	public void onFailed(GoEasyError error) {
		logger.error("消息发布失败, 错误编码：{}, 错误信息： {}", error.getCode() + error.getContent());
		result = false;
	}
}