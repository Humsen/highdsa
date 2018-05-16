package pers.husen.highdsa.service.mybatis.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysUserInfo;
import pers.husen.highdsa.service.mybatis.SysUserInfoManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserInfoMapper;

/**
 * @Desc dubbo服务接口实现类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午1:47:39
 * 
 * @Version 1.0.3
 */
@Service("sysUserInfoManager")
public class SysUserInfoManagerImpl implements SysUserInfoManager {
	private static final Logger logger = LogManager.getLogger(SysUserInfoManagerImpl.class.getName());

	@Autowired
	private SysUserInfoMapper sysUserInfoMapper;

	@Override
	public Integer createUserInfo(SysUserInfo sysUserInfo) {
		Integer reply = sysUserInfoMapper.insert(sysUserInfo);

		logger.info("insert reply: {}", reply);

		return reply;
	}

	@Override
	public SysUserInfo findByUserId(Long userId) {
		SysUserInfo sysUserInfo = sysUserInfoMapper.selectByPrimaryKey(userId);

		logger.info("select by id[{}] reply: {}", userId, sysUserInfo);

		return sysUserInfo;
	}

	@Override
	public List<SysUserInfo> findAll() {
		List<SysUserInfo> userInfos = sysUserInfoMapper.selectAll();

		logger.info("select all reply: {}", userInfos);

		return userInfos;
	}

	@Override
	public Integer updateUserInfo(SysUserInfo sysUserInfo) {
		Integer reply = sysUserInfoMapper.updateByPrimaryKey(sysUserInfo);

		logger.info("update reply: {}", reply);

		return reply;
	}

	@Override
	public Integer deleteUserInfo(Long userId) {
		// SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		// SysUserInfoMapper sysUserInfoMapper =
		// sqlSession.getMapper(SysUserInfoMapper.class);

		Integer reply = sysUserInfoMapper.deleteByPrimaryKey(userId);
		// sqlSession.commit();

		logger.info("delete reply: {}", reply);

		return reply;
	}
}