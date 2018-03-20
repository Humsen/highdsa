package pers.husen.highdsa.web.test.mybatis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.husen.highdsa.common.entity.po.UserInfo;
import pers.husen.highdsa.service.mybatis.UserInfoDbOper;

/**
 * @Desc mybatis调用服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 上午10:24:38
 * 
 * @Version 1.0.0
 */
@Service
public class MybatisSvc {
	private static final Logger logger = LogManager.getLogger(MybatisSvc.class.getName());
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private UserInfoDbOper userInfoDbOper;
	
	public String selectById(int userId) throws JsonProcessingException {
		UserInfo userInfo = userInfoDbOper.selectById(userId);
		
		String reply = objectMapper.writeValueAsString(userInfo);
		logger.info("返回结果为: {}", reply);
		
		return reply;
	}
}