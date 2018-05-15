package pers.husen.highdsa.web.app.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;

import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;
import pers.husen.highdsa.service.mybatis.CustUserManager;

/**
 * @Desc 注册服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月15日 上午1:27:29
 * 
 * @Version 1.0.0
 */
@Service
public class RegisterSvc {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private CustUserManager custUserManager;

	public ResponseJson createUser(Map<String, String> jsonMap) throws Exception {
		ResponseJson simpleJson;

		String jsonStr = objectMapper.writeValueAsString(jsonMap);
		objectMapper.setPropertyNamingStrategy(SnakeCaseStrategy.SNAKE_CASE);
		CustUser user = objectMapper.readValue(jsonStr, CustUser.class);

		// 密码解密
		// user.setUserPassword(TripleDesEncrypt.decrypt(user.getUserPassword()));

		try {
			String userName = custUserManager.createUser(user);
			simpleJson = new ResponseJson(true, userName);
		} catch (Exception e) {
			simpleJson = new ResponseJson(false, "注册失败");
		}

		return simpleJson;
	}
}