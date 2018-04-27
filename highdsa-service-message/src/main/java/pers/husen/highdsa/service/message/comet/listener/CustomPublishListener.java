package pers.husen.highdsa.service.message.comet.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;

/**
 * @Desc 继承发布消息监听类,自定义日志消息,返回操作结果
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午5:39:13
 * 
 * @Version 1.0.1
 */
public class CustomPublishListener extends PublishListener {
	private static final Logger logger = LogManager.getLogger(CustomPublishListener.class.getName());

	Boolean result = null;

	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}

	@Override
	public void onSuccess() {
		logger.trace("消息发布成功");
		result = true;
	}

	@Override
	public void onFailed(GoEasyError error) {
		logger.warn("消息发布失败, 错误编码：{}, 错误信息： {}", error.getCode() + error.getContent());
		result = false;
	}
}