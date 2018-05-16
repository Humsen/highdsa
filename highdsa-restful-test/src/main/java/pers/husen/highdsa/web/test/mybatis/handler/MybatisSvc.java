package pers.husen.highdsa.web.test.mybatis.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.common.entity.po.system.SysUserInfo;
import pers.husen.highdsa.service.mybatis.SysUserInfoManager;

/**
 * @Desc mybatis调用服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月20日 上午10:24:38
 * 
 * @Version 1.0.3
 */
@Service
public class MybatisSvc {
	private static final Logger logger = LogManager.getLogger(MybatisSvc.class.getName());

	@Autowired
	private SysUserInfoManager sysUserInfoManager;

	public SysUserInfo selectById(Long userId) throws JsonProcessingException {
		SysUserInfo sysUserInfo = sysUserInfoManager.findByUserId(userId);

		logger.info("返回结果为: {}", sysUserInfo);

		return sysUserInfo;
	}
}