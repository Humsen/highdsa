package pers.husen.highdsa.web.message.comet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc 推送消息控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午4:27:11
 * 
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/comet/v1")
public class PushMsgController {
	@Autowired
	PushMsgSvc pushMsgSvc;

	@ResponseBody
	@RequestMapping(value = "/init.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String initGoEasy() throws UnsupportedEncodingException, IOException {
		pushMsgSvc.initGoEasy();
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/goeasy.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String publish(String channel, String content) throws UnsupportedEncodingException, IOException {
		return pushMsgSvc.publish(channel, content);
	}

	@ResponseBody
	@RequestMapping(value = "/rest_host.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getRestHost() throws UnsupportedEncodingException, IOException {
		return pushMsgSvc.getRestHost();
	}

	@ResponseBody
	@RequestMapping(value = "/app_key.hms", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAppKey() throws UnsupportedEncodingException, IOException {
		return pushMsgSvc.getAppKey();
	}
}