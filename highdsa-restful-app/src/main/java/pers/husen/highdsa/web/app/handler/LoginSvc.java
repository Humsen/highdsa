package pers.husen.highdsa.web.app.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.entity.vo.SimpleJson;

/**
 * @Desc shiro 控制器服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:22:19
 * 
 * @Version 1.0.1
 */
@Service
public class LoginSvc {
	private static final Logger logger = LogManager.getLogger(LoginSvc.class.getName());

	private final ObjectMapper objectMapper = new ObjectMapper();
	private SimpleJson simpleJson;

	/**
	 * 测试未登录,会跳转到登录界面
	 * 
	 * @return
	 */
	public String helloWorld() {
		return "hello world";
	}

	/**
	 * 接收登录操作的参数并执行登录
	 * 
	 * @param token
	 * @return
	 * @throws JsonProcessingException
	 */
	public String login(String userName, String userPassword) throws JsonProcessingException {
		String reply = null;

		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
		token.setRememberMe(true);
		SecurityUtils.getSubject().login(token);

		simpleJson = new SimpleJson(true, "登录成功");
		Subject subject = SecurityUtils.getSubject();
		System.out.println("是否登录：" + subject.isAuthenticated());

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}

	/**
	 * 使用手机登录
	 * 
	 * @param phone
	 * @param userPassword
	 * @return
	 * @throws JsonProcessingException
	 */
	public String loginWithPhone(@RequestParam(value = "phone") String phone, @RequestParam(value = "password") String userPassword) throws JsonProcessingException {
		String reply = null;

		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("id", 10001L);
		userMap.put("username", "何明胜");
		userMap.put("email", "940706904@qq.com");
		userMap.put("mobi", "18626422426");
		userMap.put("logo_url", "https://www.hemingsheng.cn/imageDownload.hms?imageUrl=20180513/71522000.jpg");
		
		map.put("token", SecurityUtils.getSubject().getSession().getId());
		map.put("data", userMap);
		
		reply = objectMapper.writeValueAsString(map);

		logger.info(reply);

		return reply;
	}
	
	/**
	 * 退出登录
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String logout() throws JsonProcessingException {
		String reply = null;

		SecurityUtils.getSubject().logout();

		simpleJson = new SimpleJson(true, "退出登录成功");

		reply = objectMapper.writeValueAsString(simpleJson);
		logger.info(reply);

		return reply;
	}
}