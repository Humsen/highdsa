package pers.husen.highdsa.service.redis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
 * @Version 1.0.1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisOperationTest {
	RedisOperationImpl redisOperationImpl = new RedisOperationImpl();

	@Test
	public void test01Set() {
		assertEquals(redisOperationImpl.set("username", "root"), "OK");
	}

	@Test
	public void test02Set() {
		assertEquals(redisOperationImpl.set("username", "root", 1), "OK");
	}

	@Test
	public void test03Get() {
		assertEquals(redisOperationImpl.get("username"), "root");
	}

	@Test
	public void test04Append() {
		assertTrue(5 == redisOperationImpl.append("username", "1"));
	}

	@Test
	public void test05Exist() {
		assertEquals(redisOperationImpl.exists("username"), true);
	}

	@Test
	public void test06Del() {
		assertEquals(redisOperationImpl.del("username"), 1);
		assertEquals(redisOperationImpl.del("username"), 0);
	}

	@Test
	public void test07Del() {
		assertEquals(redisOperationImpl.set("username3", "root"), "OK");
		assertEquals(redisOperationImpl.set("username4", "root"), "OK");

		assertEquals(redisOperationImpl.del(new String[] { "username3", "username4" }), new Long(2));
		assertEquals(redisOperationImpl.del(new String[] { "username3", "username4" }), new Long(0));
	}

	@Test
	public void test08SetObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		assertEquals(redisOperationImpl.setObject("userinfo", userInfoPo, 0), "OK");
	}

	@Test
	public void test09GetObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		assertEquals(redisOperationImpl.getObject("userinfo").toString(), userInfoPo.toString());
	}

	@Test
	public void test10ExistsObject() {
		assertEquals(redisOperationImpl.existsObject("userinfo"), true);
		assertEquals(redisOperationImpl.existsObject("userinfo1"), false);
	}

	@Test
	public void test11DelObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		redisOperationImpl.setObject("userinfo123", userInfoPo, 0);

		assertEquals(redisOperationImpl.delObject("userinfo123"), 1);
	}

	@Test
	public void test12SetList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");

		assertEquals(redisOperationImpl.setList("language", list, 0), new Long(3));
	}

	@Test
	public void test13GetList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");

		assertEquals(redisOperationImpl.getList("language"), list);
	}

	@Test
	public void test14AppendList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");
		list.add("js");

		assertEquals(redisOperationImpl.appendList("language", "js"), 4);
		assertEquals(redisOperationImpl.getList("language"), list);
	}

	@Test
	public void test15SetObjectList() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		List<Object> list = new ArrayList<Object>();
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);

		assertEquals(redisOperationImpl.setObjectList("userinfoList", list, 0), "OK");
	}

	@Test
	public void test16GetObjectList() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		List<Object> list = new ArrayList<Object>();
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);

		assertEquals(redisOperationImpl.getObjectList("userinfoList").toString(), list.toString());
	}

	@Test
	public void test17AppendObjectList() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		List<Object> list = new ArrayList<Object>();
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);
		list.add(userInfoPo);

		String result = redisOperationImpl.appendObjectList("userinfoList", userInfoPo);
		assertTrue("OK".equals(result));
		assertEquals(redisOperationImpl.getObjectList("userinfoList").toString(), list.toString());
	}

	@Test
	public void test18Mset() {
		String[] strArray = new String[] { "user1", "tom", "user2", "jack" };

		assertEquals(redisOperationImpl.mset(strArray), "OK");
	}

	@Test
	public void test19Mget() {
		assertEquals(redisOperationImpl.get("user1"), "tom");
		assertEquals(redisOperationImpl.get("user2"), "jack");
	}

	@Test
	public void test20SetSet() {
		Set<String> set = new HashSet<String>();

		set.add("Spring");
		set.add("Struts");
		set.add("Hibernate");

		assertEquals(redisOperationImpl.setSet("javafarmwork", set, 0), new Long(3));
	}

	@Test
	public void test21GetSet() {
		Set<String> set = new HashSet<String>();

		set.add("Spring");
		set.add("Struts");
		set.add("Hibernate");

		assertEquals(redisOperationImpl.getSet("javafarmwork"), set);
	}

	@Test
	public void test22AppendSet() {
		assertEquals(redisOperationImpl.appendSet("javafarmwork", "servlet"), 1L);
	}

	@Test
	public void test23SetObjectSet() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		UserInfoPo userInfoPo1 = new UserInfoPo();
		userInfoPo1.setUsername("何明胜1");
		userInfoPo1.setPassword("123123");

		UserInfoPo userInfoPo2 = new UserInfoPo();
		userInfoPo2.setUsername("何明胜2");
		userInfoPo2.setPassword("123123");

		Set<Object> set = new HashSet<Object>();
		set.add(userInfoPo);
		set.add(userInfoPo1);
		set.add(userInfoPo2);

		assertEquals(redisOperationImpl.setObjectSet("userinfoSet", set, 0), "OK");
	}

	@Test
	public void test24GetObjectSet() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		UserInfoPo userInfoPo1 = new UserInfoPo();
		userInfoPo1.setUsername("何明胜1");
		userInfoPo1.setPassword("123123");

		UserInfoPo userInfoPo2 = new UserInfoPo();
		userInfoPo2.setUsername("何明胜2");
		userInfoPo2.setPassword("123123");

		Set<Object> set = new HashSet<Object>();
		set.add(userInfoPo);
		set.add(userInfoPo1);
		set.add(userInfoPo2);

		Set<Object> result = redisOperationImpl.getObjectSet("userinfoSet");
		assertTrue(result.toString() != "");
	}

	@Test
	public void test25AppendObjectSet() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		UserInfoPo userInfoPo1 = new UserInfoPo();
		userInfoPo1.setUsername("何明胜1");
		userInfoPo1.setPassword("123123");

		UserInfoPo userInfoPo2 = new UserInfoPo();
		userInfoPo2.setUsername("何明胜2");
		userInfoPo2.setPassword("123123");

		Set<Object> set = new HashSet<Object>();
		set.add(userInfoPo);
		set.add(userInfoPo1);
		set.add(userInfoPo2);
		set.add(userInfoPo1);
		set.add(userInfoPo2);

		String result = redisOperationImpl.appendObjectSet("userinfoSet", new Object[] { userInfoPo1, userInfoPo2 });
		assertEquals(result, "OK");
		String resultStr = redisOperationImpl.getObjectSet("userinfoSet").toString();
		assertTrue(resultStr != "");
	}

	@Test
	public void test26SetMap() {
		Map<String, String> map = new HashMap<>(4);
		map.put("redisSet", "java");
		map.put("redisSet1", "js");
		map.put("redisSet2", "python");

		assertEquals(redisOperationImpl.setMap("redisSet", map, 0), "OK");
	}

	@Test
	public void test27GetMap() {
		Map<String, String> map = new HashMap<>(4);
		map.put("redisSet", "java");
		map.put("redisSet1", "js");
		map.put("redisSet2", "python");

		assertTrue(redisOperationImpl.getMap("redisSet").toString() != "");
	}

	@Test
	public void test28AppendMap() {
		Map<String, String> map = new HashMap<>(4);
		map.put("redisSet", "java");
		map.put("redisSet1", "js");
		map.put("redisSet2", "python");

		assertTrue(redisOperationImpl.appendMap("redisSet", map).toString() != "");
	}

	@Test
	public void test29removelMap() {
		assertEquals(redisOperationImpl.removeMap("redisSet", "redisSet"), 1);
	}

	@Test
	public void test30existsMap() {
		assertEquals(redisOperationImpl.existsMap("redisSet", "redisSet"), false);
		assertEquals(redisOperationImpl.existsMap("redisSet", "redisSet1"), true);
	}

	@Test
	public void test31setObjectMap() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		Map<String, Object> map = new HashMap<>(4);
		map.put("uesrinfoMap", userInfoPo);
		map.put("uesrinfoMap1", userInfoPo);
		map.put("uesrinfoMap2", userInfoPo);

		assertEquals(redisOperationImpl.setObjectMap("redisObjectMap", map, 0), "OK");
	}

	@Test
	public void test32getObjectMap() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		Map<String, Object> map = new HashMap<>(4);
		map.put("uesrinfoMap", userInfoPo);
		map.put("uesrinfoMap1", userInfoPo);
		map.put("uesrinfoMap2", userInfoPo);

		assertTrue(redisOperationImpl.getObjectMap("redisObjectMap").toString() != "");
	}

	@Test
	public void test33appendObjectMap() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		Map<String, Object> map = new HashMap<>(10);
		map.put("uesrinfoMap", userInfoPo);
		map.put("uesrinfoMap1", userInfoPo);
		map.put("uesrinfoMap2", userInfoPo);
		map.put("uesrinfoMap3", userInfoPo);
		map.put("uesrinfoMap4", userInfoPo);

		Map<String, Object> map1 = new HashMap<>(4);
		map1.put("uesrinfoMap3", userInfoPo);
		map1.put("uesrinfoMap4", userInfoPo);

		assertEquals(redisOperationImpl.appendObjectMap("redisObjectMap", map1), "OK");
	}

	@Test
	public void test34appendObjectMap() {
		assertEquals(redisOperationImpl.removeObjectMap("redisObjectMap", "uesrinfoMap4"), 1);
		assertEquals(redisOperationImpl.removeObjectMap("redisObjectMap", "uesrinfoMap5"), 0);
	}

	@Test
	public void test35existsObjectMap() {
		assertTrue(redisOperationImpl.existsObjectMap("redisObjectMap", "uesrinfoMap3"));
		assertFalse(redisOperationImpl.existsObjectMap("redisObjectMap", "uesrinfoMap6"));
	}

	@Test
	public void test36DeleteAll() {
		assertEquals(redisOperationImpl.deleteAll(), "OK");
	}
}