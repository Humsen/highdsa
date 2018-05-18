package pers.husen.highdsa.web.redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc dubbo消费者测redis缓存
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月18日 上午12:26:14
 * 
 * @Version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/dubbo-consumer-redis.xml" })
public class DuddoRedisOperationTest extends AbstractJUnit4SpringContextTests {

	@Before
	public void before() {
		System.out.println("=============== redis消费者已经启动... ==================");
	}

	@Test
	public void testRedisOperation() {
		RedisOperation redisOperation = (RedisOperation) applicationContext.getBean("redisOperation");

		String redisTestValue = "I am dubbo redis test";
		String dubboRedisKey = "dubbo_redis_cache:" + "test";

		String reply = redisOperation.set(dubboRedisKey, redisTestValue, 10);
		System.out.println("设置缓存返回结果：" + reply);

		String getRedisTestValue = redisOperation.get(dubboRedisKey);
		System.out.println("获取缓存返回结果：" + getRedisTestValue);

		try {
			System.out.println("开始休眠10秒钟...");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("=== 休眠10秒钟结束 ===");
		String getRedisTestValue1 = redisOperation.get(dubboRedisKey);
		System.out.println("10秒钟后获取缓存返回结果：" + getRedisTestValue1);
	}
}