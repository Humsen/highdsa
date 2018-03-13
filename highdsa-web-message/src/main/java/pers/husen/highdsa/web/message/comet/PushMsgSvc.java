package pers.husen.highdsa.web.message.comet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.service.message.PushMsg;

/**
 * @Desc 推送消息业务调用服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午4:27:26
 * 
 * @Version 1.0.0
 */
@Service
public class PushMsgSvc {
	private static final Logger logger = LogManager.getLogger(PushMsgSvc.class.getName());
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private PushMsg pushMsg;

	public String initGoEasy() throws UnsupportedEncodingException, IOException {
		pushMsg.initGoEasy();

		logger.info("初始化go easy完成");

		Map<String, Object> map = new HashMap<String, Object>(200);
		map.put("success", "true");

		return objectMapper.writeValueAsString(map);
	}

	public String publish(String channel, String content) throws UnsupportedEncodingException, IOException {
		Boolean reply = null;

		reply = pushMsg.publish(channel, content);
		logger.info("返回结果为: {}", reply);

		Map<String, Object> map = new HashMap<String, Object>(200);
		map.put("success", "true");

		return objectMapper.writeValueAsString(map);
	}

	public String getRestHost() throws UnsupportedEncodingException, IOException {
		String reply = null;

		reply = pushMsg.getRestHost();
		logger.info("返回结果为: restHost={}", reply);

		Map<String, Object> map = new HashMap<String, Object>(200);
		map.put("success", "true");
		map.put("rest_host", reply);
		
		return objectMapper.writeValueAsString(map);

	}

	public String getAppKey() throws UnsupportedEncodingException, IOException {
		String reply = null;

		reply = pushMsg.getAppKey();
		logger.info("返回结果为: appKey={}", reply);

		Map<String, Object> map = new HashMap<String, Object>(200);
		map.put("success", "true");
		map.put("app_key", reply);
		
		return objectMapper.writeValueAsString(map);
	}
}