package pers.husen.highdsa.service.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Desc 推送消息接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午5:34:43
 * 
 * @Version 1.0.0
 */
public interface PushMsg {
	/**
	 * 初始化参数和实例
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void initGoEasy() throws UnsupportedEncodingException, IOException;

	/**
	 * 发布消息
	 * 
	 * @param channel
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public Boolean publish(String channel, String content) throws UnsupportedEncodingException, IOException;

	/**
	 * 获取restHost
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public String getRestHost() throws UnsupportedEncodingException, IOException;

	/**
	 * 获取appKey
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public String getAppKey() throws UnsupportedEncodingException, IOException;
}