package pers.husen.highdsa.service.mybatis;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.husen.highdsa.common.entity.po.UserInfo;
import pers.husen.highdsa.service.mybatis.cache.RedisCache;
import pers.husen.highdsa.service.mybatis.core.SqlSessionFactoryManager;
import pers.husen.highdsa.service.mybatis.dao.UserInfoMapper;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc 测试redis服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午1:03:55
 * 
 * @Version 1.0.0
 */
public class RegisterMybatisServiceTest {
	private ClassPathXmlApplicationContext context;

	@Before
	public void before() {
		String[] configLocation = new String[] { "spring/mybatis-provider.xml", "spring/redis-consumer.xml","spring/spring-context.xml" };

		context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
		System.out.println("=============== dubbo已经启动... ==================");
	}

	@Test
	public void testRedisOperation() {
		RedisOperation redisOperation = (RedisOperation) context.getBean("redisOperation");

		System.out.println(redisOperation.getDbSize());
	}

	@Test
	public void testRedisCache() {
		RedisCache redisCache = (RedisCache) context.getBean("redisCache");

		System.out.println(redisCache.getSize());
	}

	@Test
	public void testUserInfoDbOperImpl() {
		UserInfoDbOperImpl userInfoDbOperImpl = (UserInfoDbOperImpl) context.getBean("userInfoDbOperImpl");

		// userInfoDbOperImpl.deleteUserInfo(2);
		List<UserInfo> userInfos = userInfoDbOperImpl.selectAll();
		System.out.println(userInfos);

		List<UserInfo> userInfos1 = userInfoDbOperImpl.selectAll();
		System.out.println(userInfos1);
	}

	@Test
	public void testMybatisRedisCache() {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		System.out.println("--------------------");
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		System.out.println("============= session1 ================");
		System.out.println("第1次查询：" + userInfoMapper.selectById(1).toString());
		System.out.println("第2次查询：" + userInfoMapper.selectById(1));
		sqlSession.commit();

		System.out.println("第3次查询：" + userInfoMapper.selectById(1));

		System.out.println("============= session2 ================");
		SqlSession sqlSession1 = SqlSessionFactoryManager.openSession();
		UserInfoMapper userInfoMapper1 = sqlSession1.getMapper(UserInfoMapper.class);
		System.out.println("第4次查询：" + userInfoMapper1.selectById(1));
	}

	@After
	public void after() {
		try {
			// 为保证服务一直开着，利用输入流的阻塞来模拟
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("=============== 消费者关闭... ==================");
		context.close();
	}
}