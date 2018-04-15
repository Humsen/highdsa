package pers.husen.highdsa.service.mybatis;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.common.entity.po.system.SysUserInfo;
import pers.husen.highdsa.service.mybatis.cache.RedisCache;
import pers.husen.highdsa.service.mybatis.core.SqlSessionFactoryManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysUserInfoMapper;
import pers.husen.highdsa.service.mybatis.impl.SysUserInfoManagerImpl;
import pers.husen.highdsa.service.mybatis.impl.SysUserManagerImpl;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc 测试redis服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午1:03:55
 * 
 * @Version 1.0.3
 */
//使用junit4进行测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置文件
@ContextConfiguration(locations = { "classpath:spring/mybatis-provider.xml", "classpath:spring/redis-consumer.xml", "classpath:spring/spring-context.xml" }) 
public class MybatisAndRedisCacheTest extends AbstractJUnit4SpringContextTests{

	@Before
	public void before() {
		System.out.println("=============== dubbo已经启动... ==================");
	}

	@Test
	public void testRedisOperation() {
		RedisOperation redisOperation = (RedisOperation) applicationContext.getBean("redisOperation");

		System.out.println(redisOperation.getDbSize());
	}

	@Test
	public void testRedisCache() {
		RedisCache redisCache = (RedisCache) applicationContext.getBean("redisCache");

		System.out.println(redisCache.getSize());
	}

	@Test
	public void testSysUserInfoManagerImpl() {
		SysUserInfoManagerImpl sysUserInfoManagerImpl = (SysUserInfoManagerImpl) applicationContext.getBean("sysUserInfoManager");

		// userInfoDbOperImpl.deleteUserInfo(2);
		List<SysUserInfo> userInfos = sysUserInfoManagerImpl.selectAll();
		System.out.println(userInfos);

		List<SysUserInfo> userInfos1 = sysUserInfoManagerImpl.selectAll();
		System.out.println(userInfos1);
	}

	@Test
	public void testMybatisRedisCache() {
		SqlSession sqlSession = SqlSessionFactoryManager.openSession();
		System.out.println("--------------------");
		SysUserInfoMapper sysUserInfoMapper = sqlSession.getMapper(SysUserInfoMapper.class);
		System.out.println("============= session1 ================");
		System.out.println("第1次查询：" + sysUserInfoMapper.selectByPrimaryKey(1000L).toString());
		System.out.println("第2次查询：" + sysUserInfoMapper.selectByPrimaryKey(1000L));
		sqlSession.commit();

		System.out.println("第3次查询：" + sysUserInfoMapper.selectByPrimaryKey(1000L));

		System.out.println("============= session2 ================");
		SqlSession sqlSession1 = SqlSessionFactoryManager.openSession();
		SysUserInfoMapper sysUserInfoMapper2 = sqlSession1.getMapper(SysUserInfoMapper.class);
		System.out.println("第4次查询：" + sysUserInfoMapper2.selectByPrimaryKey(1000L));
	}

	@Test
	public void testShiroRedisCache() {
		SysUserManagerImpl sysUserManager = (SysUserManagerImpl) applicationContext.getBean("sysUserManager");

		SysUser sysUser = sysUserManager.findUserByUserName("user");
		System.out.println(sysUser);

		SysUser sysUser1 = sysUserManager.findUserByUserName("user");
		System.out.println(sysUser1);
	}

	@After
	public void after() {
		try {
			System.out.println("=============== 阻塞开始... ==================");
			// 为保证服务一直开着，利用输入流的阻塞来模拟
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("=============== 消费者关闭... ==================");
	}
}