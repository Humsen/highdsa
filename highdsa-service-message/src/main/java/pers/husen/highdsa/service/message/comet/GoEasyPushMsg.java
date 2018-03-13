package pers.husen.highdsa.service.message.comet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.goeasy.GoEasy;
import pers.husen.highdsa.common.utility.ReadConfigFile;
import pers.husen.highdsa.service.message.PushMsg;
import pers.husen.highdsa.service.message.comet.template.CustomPublishListener;

/**
 * @Desc GoEasy 推送方案
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午1:24:53
 * 
 * @Version 1.0.1
 */
public class GoEasyPushMsg implements PushMsg {
	private static final Logger logger = LogManager.getLogger(GoEasyPushMsg.class.getName());

	private GoEasy goEasy = null;
	private static String REST_HOST = null;
	private static String APP_KEY = null;

	/**
	 * 初始化go easy
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Override
	@PostConstruct
	public void initGoEasy() throws UnsupportedEncodingException, IOException {
		logger.info("开始执行初始化操作...");
		Properties properties = ReadConfigFile.readByRelativePath("appkey.properties");

		REST_HOST = properties.getProperty("rest_host");
		APP_KEY = properties.getProperty("common_key");

		goEasy = new GoEasy(REST_HOST, APP_KEY);
	}

	@Override
	public Boolean publish(String channel, String content) throws UnsupportedEncodingException, IOException {
		if (goEasy == null) {
			logger.info("暂无实例, 开始初始化...");
			initGoEasy();
		}

		CustomPublishListener customPublishListener = new CustomPublishListener();
		goEasy.publish(channel, content, customPublishListener);

		logger.info("发布信息成功, channel={},content={}", channel, content);

		return customPublishListener.getResult();
	}

	@Override
	public String getRestHost() throws UnsupportedEncodingException, IOException {
		if (REST_HOST == null) {
			logger.info("restHost为空, 开始初始化...");
			initGoEasy();
		}
		
		logger.info("获取restHost成功, restHost={}", REST_HOST);

		return REST_HOST;
	}

	@Override
	public String getAppKey() throws UnsupportedEncodingException, IOException {
		if (APP_KEY == null) {
			logger.info("restHost为空, 开始初始化...");
			initGoEasy();
		}

		logger.info("获取appKey成功, appKey={}", APP_KEY);
		
		return APP_KEY;
	}
}