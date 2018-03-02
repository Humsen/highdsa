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
public class TestRedisOperation {
	@Test
	public void test01Set() {
		assertEquals(RedisOperation.set("username", "root"), "OK");
	}

	@Test
	public void test02Set() {
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
	public void test06Del() {
		assertEquals(RedisOperation.del("username"), 1);
		assertEquals(RedisOperation.del("username"), 0);
	}
	
	@Test
	public void test07Del() {
		assertEquals(RedisOperation.set("username3", "root"), "OK");
		assertEquals(RedisOperation.set("username4", "root"), "OK");
		
		assertEquals(RedisOperation.del(new String[] {"username3","username4"}), 2);
		assertEquals(RedisOperation.del(new String[] {"username3","username4"}), 0);
	}

	@Test
	public void test08SetObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		assertEquals(RedisOperation.setObject("userinfo", userInfoPo, 0), "OK");
	}

	@Test
	public void test09GetObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");

		assertEquals(RedisOperation.getObject("userinfo").toString(), userInfoPo.toString());
	}

	@Test
	public void test10ExistsObject() {
		assertEquals(RedisOperation.existsObject("userinfo"), true);
		assertEquals(RedisOperation.existsObject("userinfo1"), false);
	}
	
	@Test
	public void test11DelObject() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		
		RedisOperation.setObject("userinfo123", userInfoPo, 0);
		
		assertEquals(RedisOperation.delObject("userinfo123"), 1);
	}

	@Test
	public void test12SetList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");

		assertEquals(RedisOperation.setList("language", list, 0), 3);
	}

	@Test
	public void test13GetList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");

		assertEquals(RedisOperation.getList("language"), list);
	}

	@Test
	public void test14AppendList() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("c++");
		list.add("js");

		assertEquals(RedisOperation.appendList("language", "js"), 4);
		assertEquals(RedisOperation.getList("language"), list);
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

		assertEquals(RedisOperation.setObjectList("userinfoList", list, 0), "OK");
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

		assertEquals(RedisOperation.getObjectList("userinfoList").toString(), list.toString());
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

		String result = RedisOperation.appendObjectList("userinfoList", userInfoPo);
		assertTrue("OK".equals(result));
		assertEquals(RedisOperation.getObjectList("userinfoList").toString(), list.toString());
	}

	@Test
	public void test18Mset() {
		String[] strArray = new String[] { "user1", "tom", "user2", "jack" };

		assertEquals(RedisOperation.mset(strArray), "OK");
	}

	@Test
	public void test19Mget() {
		assertEquals(RedisOperation.get("user1"), "tom");
		assertEquals(RedisOperation.get("user2"), "jack");
	}

	@Test
	public void test20SetSet() {
		Set<String> set = new HashSet<String>();

		set.add("Spring");
		set.add("Struts");
		set.add("Hibernate");

		assertEquals(RedisOperation.setSet("javafarmwork", set, 0), 3);
	}

	@Test
	public void test21GetSet() {
		Set<String> set = new HashSet<String>();

		set.add("Spring");
		set.add("Struts");
		set.add("Hibernate");
		
		assertEquals(RedisOperation.getSet("javafarmwork"), set);
	}

	@Test
	public void test22AppendSet() {
		assertEquals(RedisOperation.appendSet("javafarmwork", "servlet"), 1L);
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
		
		assertEquals(RedisOperation.setObjectSet("userinfoSet", set, 0), "OK");
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
		
		Set<Object> result = RedisOperation.getObjectSet("userinfoSet");
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
		
		String result = RedisOperation.appendObjectSet("userinfoSet", new Object[] {userInfoPo1, userInfoPo2});
		assertEquals(result, "OK");
		String resultStr = RedisOperation.getObjectSet("userinfoSet").toString();
		assertTrue(resultStr != "");
	}
	
	@Test
	public void test26SetMap() {
		Map<String, String> map = new HashMap<>();
		map.put("redisSet", "java");
		map.put("redisSet1", "js");
		map.put("redisSet2", "python");
		
		assertEquals(RedisOperation.setMap("redisSet", map, 0), "OK");
	}
	
	@Test
	public void test27GetMap() {
		Map<String, String> map = new HashMap<>();
		map.put("redisSet", "java");
		map.put("redisSet1", "js");
		map.put("redisSet2", "python");
		
		assertTrue(RedisOperation.getMap("redisSet").toString() != "");
	}
	
	@Test
	public void test28AppendMap() {
		Map<String, String> map = new HashMap<>();
		map.put("redisSet", "java");
		map.put("redisSet1", "js");
		map.put("redisSet2", "python");
		
		assertTrue(RedisOperation.appendMap("redisSet", map).toString() != "");
	}
	
	@Test
	public void test29removelMap() {
		assertEquals(RedisOperation.removeMap("redisSet", "redisSet"), 1);
	}
	
	@Test
	public void test30existsMap() {
		assertEquals(RedisOperation.existsMap("redisSet", "redisSet"), false);
		assertEquals(RedisOperation.existsMap("redisSet", "redisSet1"), true);
	}
	
	@Test
	public void test31setObjectMap() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		
		Map<String, Object> map = new HashMap<>();
		map.put("uesrinfoMap", userInfoPo);
		map.put("uesrinfoMap1", userInfoPo);
		map.put("uesrinfoMap2", userInfoPo);

		assertEquals(RedisOperation.setObjectMap("redisObjectMap", map, 0), "OK");
	}
	
	@Test
	public void test32getObjectMap() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		
		Map<String, Object> map = new HashMap<>();
		map.put("uesrinfoMap", userInfoPo);
		map.put("uesrinfoMap1", userInfoPo);
		map.put("uesrinfoMap2", userInfoPo);

		assertTrue(RedisOperation.getObjectMap("redisObjectMap").toString() != "");
	}
	
	@Test
	public void test33appendObjectMap() {
		UserInfoPo userInfoPo = new UserInfoPo();
		userInfoPo.setUsername("何明胜");
		userInfoPo.setPassword("123123");
		
		Map<String, Object> map = new HashMap<>();
		map.put("uesrinfoMap", userInfoPo);
		map.put("uesrinfoMap1", userInfoPo);
		map.put("uesrinfoMap2", userInfoPo);
		map.put("uesrinfoMap3", userInfoPo);
		map.put("uesrinfoMap4", userInfoPo);

		Map<String, Object> map1 = new HashMap<>();
		map1.put("uesrinfoMap3", userInfoPo);
		map1.put("uesrinfoMap4", userInfoPo);
		
		assertEquals(RedisOperation.appendObjectMap("redisObjectMap", map1), "OK");
	}
	
	@Test
	public void test34appendObjectMap() {
		assertEquals(RedisOperation.removeObjectMap("redisObjectMap", "uesrinfoMap4"), 1);
		assertEquals(RedisOperation.removeObjectMap("redisObjectMap", "uesrinfoMap5"), 0);
	}
	
	@Test
	public void test35existsObjectMap() {
		assertTrue(RedisOperation.existsObjectMap("redisObjectMap", "uesrinfoMap3"));
		assertFalse(RedisOperation.existsObjectMap("redisObjectMap", "uesrinfoMap6"));
	}
	
	@Test
	public void test36DeleteAll() {
		assertEquals(RedisOperation.deleteAll(), "OK");
	}
}