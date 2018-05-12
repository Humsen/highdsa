package pers.husen.highdsa.web.shiro.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

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
 * @Version 1.0.0
 */
@Service
public class AppSvc {
	private static final Logger logger = LogManager.getLogger(AppSvc.class.getName());

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