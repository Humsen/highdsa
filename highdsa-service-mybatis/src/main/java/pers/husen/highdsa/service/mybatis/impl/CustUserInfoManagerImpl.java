package pers.husen.highdsa.service.mybatis.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;
import pers.husen.highdsa.service.mybatis.CustUserInfoManager;
import pers.husen.highdsa.service.mybatis.dao.customer.CustUserInfoMapper;

/**
 * @Desc 客户信息服务接口实现类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:26:33
 * 
 * @Version 1.0.0
 */
@Service("custUserInfoManager")
public class CustUserInfoManagerImpl implements CustUserInfoManager {
	private static final Logger logger = LogManager.getLogger(CustUserInfoManagerImpl.class.getName());

	@Autowired
	private CustUserInfoMapper custUserInfoMapper;

	@Override
	public CustUserInfo selectById(Long userId) {
		CustUserInfo custUserInfo = custUserInfoMapper.selectByPrimaryKey(userId);

		logger.info("select by id[{}] reply: {}", userId, custUserInfo);

		return custUserInfo;
	}

	@Override
	public List<CustUserInfo> selectAll() {
		List<CustUserInfo> userInfos = custUserInfoMapper.selectAll();

		logger.info("select all reply: {}", userInfos);

		return userInfos;
	}

	@Override
	public Integer insertUserInfo(CustUserInfo custUserInfo) {
		Integer reply = custUserInfoMapper.insert(custUserInfo);

		logger.info("insert reply: {}", reply);

		return reply;
	}

	@Override
	public Integer updateUserInfo(CustUserInfo custUserInfo) {
		Integer reply = custUserInfoMapper.updateByPrimaryKey(custUserInfo);

		logger.info("update reply: {}", reply);

		return reply;
	}

	@Override
	public Integer deleteUserInfo(Long userId) {
		// SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		// SysUserInfoMapper sysUserInfoMapper =
		// sqlSession.getMapper(SysUserInfoMapper.class);

		Integer reply = custUserInfoMapper.deleteByPrimaryKey(userId);
		// sqlSession.commit();

		logger.info("delete reply: {}", reply);

		return reply;
	}
}