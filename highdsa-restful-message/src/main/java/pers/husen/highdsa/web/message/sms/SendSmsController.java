package pers.husen.highdsa.web.message.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.exceptions.ClientException;

import pers.husen.highdsa.common.transform.ConvertRequestParams;
import pers.husen.highdsa.web.message.sms.handler.SendSmsSvc;

/**
 * @Desc 发送短信控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月12日 下午7:15:17
 * 
 * @Version 1.0.4
 */
@RestController
@RequestMapping("/sms/v1")
public class SendSmsController {
	@Autowired
	SendSmsSvc sendSmsSvc;

	@ResponseBody
	@RequestMapping(value = "/captcha", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendSmsCaptcha(@RequestParam("phone_number") String phoneNumber, String chptcha) throws UnsupportedEncodingException, ClientException, IOException {

		return sendSmsSvc.sendSmsCaptcha(phoneNumber, chptcha);
	}

	@ResponseBody
	@RequestMapping(value = "/notice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String sendSmsNotice(@RequestParam("phone_number") String phoneNumber, @RequestParam("user_name") String userName, String chptcha)
			throws UnsupportedEncodingException, ClientException, IOException {

		return sendSmsSvc.sendSmsNotice(phoneNumber, userName, chptcha);
	}

	@ResponseBody
	@RequestMapping(value = "/reply", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String querySendDetailsByBizId(@RequestParam("phone_number") String phoneNumber, String bizId) throws UnsupportedEncodingException, ClientException, IOException {

		return sendSmsSvc.querySendDetailsByBizId(phoneNumber, bizId);
	}

	@ResponseBody
	@RequestMapping(value = "/record", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String querySendDetails(HttpServletRequest request) throws UnsupportedEncodingException, ClientException, IOException {
		Map<String, String> jsonMap = ConvertRequestParams.params2Map(request);

		return sendSmsSvc.querySendDetails(jsonMap);
	}
}