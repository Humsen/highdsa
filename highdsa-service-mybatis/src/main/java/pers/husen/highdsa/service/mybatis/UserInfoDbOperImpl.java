package pers.husen.highdsa.service.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.entity.po.UserInfo;
import pers.husen.highdsa.service.mybatis.core.SqlSessionFactoryManager;
import pers.husen.highdsa.service.mybatis.dao.UserInfoMapper;

/**
 * @Desc dubbo服务接口实现类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午1:47:39
 * 
 * @Version 1.0.0
 */
public class UserInfoDbOperImpl implements UserInfoDbOper {
	private static final Logger logger = LogManager.getLogger(UserInfoDbOperImpl.class.getName());

	@Override
	public UserInfo selectById(Integer userId) {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

		UserInfo userInfo = userInfoMapper.selectById(1);
		sqlSession.commit();

		logger.info("select by id[{}] reply: {}", userId, userInfo);

		return userInfo;
	}

	@Override
	public List<UserInfo> selectAll() {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

		List<UserInfo> userInfos = userInfoMapper.selectAll();
		sqlSession.commit();

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