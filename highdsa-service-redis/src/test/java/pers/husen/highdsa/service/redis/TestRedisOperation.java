package pers.husen.highdsa.service.redis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;

import pers.husen.highdsa.common.po.UserInfoPo;
import org.junit.runners.MethodSorters;
/**
 * @Desc 测试redis操作
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月1日 下午3:31:19
 * 
 * @Version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRedisOperation {
	@Test
	public void test01Set() {
		assertEquals(RedisOperation.set("username", "root"), "OK");
	}

	@Test
	public void test02SetTimeout() {
		assertEquals(RedisOperation.set("username", "root", 1), "OK");
	}

	@Test
	public void test03Get() {
		assertEquals(RedisOperation.get("username"), "root");
	}

	@Test
	public void test04Append() {
		assertTrue(5 == RedisOperation.append("username", "1"));
	}

	@Test
	public void test05Exist() {
		assertEquals(RedisOperation.exists("username"), true);
	}

	@Test
	public void test06SetObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		assertEquals(RedisOperation.setObject("userinfo", userInfoPo, 1), "OK");
	}

	@Test
	public void test07GetObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		assertEquals(RedisOperation.getObject("userinfo").toString(), userInfoPo.toString());
	}

	@Test
	public void test08ExistsObject() {
		assertEquals(RedisOperation.existsObject("userinfo"), true);
		assertEquals(RedisOperation.existsObject("userinfo1"), false);
	}

	@Test
	public void test09SetList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");

		assertEquals(RedisOperation.setList("language", list, 0), 3);
	}

	@Test
	public void test10GetList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");

		assertEquals(RedisOperation.getList("language"), list);
	}

	@Test
	public void test11AppendList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");
		list.add("js");

		assertEquals(RedisOperation.appendList("language", "js"), 4);
		assertEquals(RedisOperation.getList("language"), list);
	}

	@Test
	public void test12SetObjectList() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		List<Object> list = new ArrayList<Object>();
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);

		assertEquals(RedisOperation.setObjectList("userinfoList", list, 0), "OK");
	}

	@Test
	public void test13GetObjectList() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		List<Object> list = new ArrayList<Object>();
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);

		assertEquals(RedisOperation.getObjectList("userinfoList").toString(), list.toString());
	}

	@Test
	public void test14AppendObjectList() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		List<Object> list = new ArrayList<Object>();
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);

		String result = RedisOperation.appendObjectList("userinfoList", userInfoPo);
		assertTrue("OK".equals(result));
		assertEquals(RedisOperation.getObjectList("userinfoList").toString(), list.toString());
	}

	@Test
	public void test15Mset() {
		String[] strArray = new String[] { "user1", "tom", "user2", "jack" };

		assertEquals(RedisOperation.mset(strArray), "OK");
	}

	@Test
	public void test16Mget() {
		assertEquals(RedisOperation.get("user1"), "tom");
		assertEquals(RedisOperation.get("user2"), "jack");
	}

	@Test
	public void test17SetSet() {
		Set<String> set = new HashSet<String>();

		set.add("Spring");
		set.add("Struts");
		set.add("Hibernate");

		assertEquals(RedisOperation.setSet("javafarmwork", set, 0), 3);
	}

	@Test
	public void test18GetSet() {
		Set<String> set = new HashSet<String>();

		set.add("Spring");
		set.add("Struts");
		set.add("Hibernate");
		
		assertEquals(RedisOperation.getSet("javafarmwork"), set);
	}

	@Test
	public void test19AppendSet() {
		assertEquals(RedisOperation.appendSet("javafarmwork", "servlet"), 1L);
	}
}