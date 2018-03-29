package pers.husen.highdsa.service.mybatis.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.common.entity.po.UserInfo;
import pers.husen.highdsa.service.mybatis.UserInfoDbOper;
import pers.husen.highdsa.service.mybatis.core.SqlSessionFactoryManager;
import pers.husen.highdsa.service.mybatis.dao.user.UserInfoMapper;

/**
 * @Desc dubbo服务接口实现类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午1:47:39
 * 
 * @Version 1.0.1
 */
public class UserInfoDbOperImpl implements UserInfoDbOper {
	private static final Logger logger = LogManager.getLogger(UserInfoDbOperImpl.class.getName());

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo selectById(Integer userId) {
		UserInfo userInfo = userInfoMapper.selectById(userId);

		logger.info("select by id[{}] reply: {}", userId, userInfo);

		return userInfo;
	}

	@Override
	public List<UserInfo> selectAll() {
		List<UserInfo> userInfos = userInfoMapper.selectAll();

		logger.info("select all reply: {}", userInfos);

		return userInfos;
	}

	@Override
	public Integer insertUserInfo(UserInfo userInfo) {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

		Integer reply = userInfoMapper.insertUserInfo(userInfo);
		sqlSession.commit();

		logger.info("insert reply: {}", reply);

		return reply;
	}

	@Override
	public Integer updateUserInfo(UserInfo userInfo) {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

		Integer reply = userInfoMapper.updateUserInfo(userInfo);
		sqlSession.commit();

		logger.info("update reply: {}", reply);

		return reply;
	}

	@Override
	public Integer deleteUserInfo(Integer userId) {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

		Integer reply = userInfoMapper.deleteUserInfo(3);
		sqlSession.commit();

		logger.info("delete reply: {}", reply);

		return reply;
	}
}