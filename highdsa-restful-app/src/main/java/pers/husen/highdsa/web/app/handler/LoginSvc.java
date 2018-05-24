package pers.husen.highdsa.web.app.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.client.restful.realm.CustUserEmailRealm;
import pers.husen.highdsa.client.restful.realm.CustUserNameRealm;
import pers.husen.highdsa.client.restful.realm.CustUserPhoneRealm;
import pers.husen.highdsa.client.restful.token.CustomerAccountPasswordToken;
import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.entity.enums.LoginType;
import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.service.mybatis.CustUserInfoManager;
import pers.husen.highdsa.service.mybatis.CustUserManager;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc shiro 控制器服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:22:19
 * 
 * @Version 1.0.8
 */
@Service
public class LoginSvc {
	private static final Logger logger = LogManager.getLogger(LoginSvc.class.getName());

	private final ObjectMapper objectMapper = new ObjectMapper();
	private ResponseJson responseJson = new ResponseJson();

	@Autowired
	private CustUserManager custUserManager;
	@Autowired
	private CustUserInfoManager custUserInfoManager;
	@Autowired
	private RedisOperation redisOperation;

	/**
	 * 测试未登录,会跳转到登录界面
	 * 
	 * @return
	 */
	public String helloWorld() {
		String reply = null;

		Subject subject = SecurityUtils.getSubject();
		System.out.println("是否登录：" + subject.isAuthenticated());

		boolean isLogin = subject.isAuthenticated();

		reply = "{\"login\":" + isLogin + "}";

		return reply;
	}

	/**
	 * 接收登录操作的参数并执行登录
	 * 
	 * @param token
	 * @return
	 * @throws JsonProcessingException
	 */
	public String loginWithName(String userName, String userPassword) throws JsonProcessingException {
		String reply = null;

		CustomerAccountPasswordToken token = new CustomerAccountPasswordToken(userName, userPassword, true, LoginType.USERNAME);
		responseJson = login(token);

		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}

	/**
	 * 使用手机登录
	 * 
	 * @param phone
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	public String loginWithPhone(String phone, String userPassword) throws Exception {
		String reply = null;

		// 密码解密
		// String password = TripleDesEncrypt.decrypt(userPassword);
		// logger.info("解密后的密码:{}", password);
		logger.info("用户:{}, 密码：{}", phone, userPassword);

		CustomerAccountPasswordToken token = new CustomerAccountPasswordToken(phone, userPassword, true, LoginType.PHONE);
		responseJson = login(token);

		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}

	/**
	 * 使用邮箱登录
	 * 
	 * @param phone
	 * @param userPassword
	 * @return
	 * @throws JsonProcessingException
	 */
	public String loginWithEmail(String email, String userPassword) throws JsonProcessingException {
		String reply = null;

		CustomerAccountPasswordToken token = new CustomerAccountPasswordToken(email, userPassword, true, LoginType.EMAIL);
		responseJson = login(token);

		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}

	/**
	 * 三种方式登录统一入口
	 * 
	 * @param token
	 * @return
	 */
	private ResponseJson login(CustomerAccountPasswordToken token) {
		try {
			SecurityUtils.getSubject().login(token);

			responseJson.setSuccess(true);

			CustUser user = null;
			if (token.getLoginType() == LoginType.USERNAME) {
				// 用户名登录
				user = custUserManager.findUserByUserName(token.getUsername());
			} else if (token.getLoginType() == LoginType.PHONE) {
				// 手机登录
				user = custUserManager.findUserByUserPhone((String) token.getPrincipal());
			} else {
				// 邮箱登录
				user = custUserManager.findUserByUserEmail((String) token.getPrincipal());
			}

			// 防止空指针异常
			if (user == null) {
				return responseJson;
			}
			CustUserInfo userInfo = custUserInfoManager.findUserInfoById(user.getUserId());

			Map<String, Object> map = new HashMap<>(2);
			Map<String, Object> userMap = new HashMap<>(8);
			userMap.put("id", user.getUserId());
			userMap.put("username", userInfo.getUserNickName() == null ? user.getUserName() : userInfo.getUserNickName());

			if (user.getUserEmail() != null) {
				userMap.put("email", user.getUserEmail());
			}
			if (user.getUserPhone() != null) {
				userMap.put("mobi", user.getUserPhone());
			}
			if (userInfo.getUserHeadUrl() != null) {
				userMap.put("logo_url", userInfo.getUserHeadUrl());
			}

			map.put("token", SecurityUtils.getSubject().getSession().getId());
			map.put("data", userMap);

			responseJson.setMessage(map);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str("登录失败", e));

			responseJson.setSuccess(false);
			responseJson.setMessage("登录失败");
		}

		return responseJson;
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

		responseJson = new ResponseJson(true, "退出登录成功");

		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}

	/**
	 * 校验密码并重置密码
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String retrivePassword(String phoneNumber, String captcha, String newPwd) throws JsonProcessingException {
		String reply = null;
		String cacheCode = redisOperation.get(RedisCacheConstants.REGISTER_REDIS_CODE + phoneNumber);
		logger.trace("缓存的验证码:{}, 用户输入的验证码:{}", cacheCode, captcha);

		if (captcha != null && captcha.equals(cacheCode)) {
			try {
				custUserManager.modifyPasswordByUserPhone(phoneNumber, newPwd);

				// 清空认证缓存
				RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
				Collection<Realm> realms = securityManager.getRealms();

				CustUser user = custUserManager.findUserByUserPhone(phoneNumber);
				for (Realm realm : realms) {
					if (realm instanceof CustUserPhoneRealm) {
						((CustUserPhoneRealm) realm).clearCachedAuthenticationInfo(phoneNumber);
					} else if (realm instanceof CustUserEmailRealm) {
						((CustUserEmailRealm) realm).clearCachedAuthenticationInfo(user.getUserEmail());
					} else {
						((CustUserNameRealm) realm).clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
					}
				}

				responseJson = new ResponseJson(true, "修改密码成功");
			} catch (Exception e) {
				logger.error(StackTrace2Str.exceptionStackTrace2Str("修改密码失败", e));
				responseJson = new ResponseJson(true, "修改密码失败");
			}
		} else {
			responseJson = new ResponseJson(false, "手机验证失败");
		}

		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}

	/**
	 * 根据手机号获取用户信息
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws JsonProcessingException
	 */
	public String getUserInfoByPhone(@RequestParam("phone_number") String phoneNumber) throws JsonProcessingException {
		String reply = null;
		CustUser user = null;

		// 手机号查询ID
		user = custUserManager.findUserByUserPhone(phoneNumber);

		// 防止空指针异常
		if (user == null) {
			responseJson = new ResponseJson(false, "获取用户信息失败");

			return objectMapper.writeValueAsString(responseJson);
		}

		CustUserInfo userInfo = custUserInfoManager.findUserInfoById(user.getUserId());

		responseJson.setSuccess(true);
		responseJson.setMessage(userInfo);
		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}

	public String getUserInfoByUserName(String userName) throws JsonProcessingException {
		String reply = null;
		CustUser user = null;

		// 用户名查询Id
		user = custUserManager.findUserByUserName(userName);

		// 防止空指针异常
		if (user == null) {
			responseJson = new ResponseJson(false, "获取用户信息失败");

			return objectMapper.writeValueAsString(responseJson);
		}

		CustUserInfo userInfo = custUserInfoManager.findUserInfoById(user.getUserId());

		responseJson.setSuccess(true);
		responseJson.setMessage(userInfo);
		reply = objectMapper.writeValueAsString(responseJson);
		logger.info(reply);

		return reply;
	}
}