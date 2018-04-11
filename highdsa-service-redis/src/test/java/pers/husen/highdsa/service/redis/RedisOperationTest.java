package pers.husen.highdsa.service.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;

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
		assertEquals(redisOperationImpl.del("username"), new Long(1));
		assertEquals(redisOperationImpl.del("username"), new Long(0));
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
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜");
		custUserInfo.setUserDesc("测试设置用户缓存");
		custUserInfo.setUserLastLoginTime(new Date());

		assertEquals(redisOperationImpl.setObject("userId_1003", custUserInfo, 0), "OK");
	}

	@Test
	public void test09GetObject() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜");
		custUserInfo.setUserDesc("测试设置用户缓存");
		custUserInfo.setUserLastLoginTime(new Date());

		assertEquals(redisOperationImpl.getObject("userId_1003").toString(), custUserInfo.toString());
	}

	@Test
	public void test10ExistsObject() {
		assertEquals(redisOperationImpl.existsObject("userId_1003"), true);
		assertEquals(redisOperationImpl.existsObject("userId_10031"), false);
	}

	@Test
	public void test11DelObject() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜");
		custUserInfo.setUserDesc("测试设置用户缓存");
		custUserInfo.setUserLastLoginTime(new Date());

		redisOperationImpl.setObject("userId_1003_1", custUserInfo, 0);

		assertEquals(redisOperationImpl.delObject("userId_1003_1"), new Long(1));
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

		assertEquals(redisOperationImpl.appendList("language", "js"), new Long(4));
		assertEquals(redisOperationImpl.getList("language"), list);
	}

	@Test
	public void test15SetObjectList() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜");
		custUserInfo.setUserDesc("测试设置用户缓存");
		custUserInfo.setUserLastLoginTime(new Date());

		List<Object> list = new ArrayList<Object>();
		list.add(custUserInfo);
		list.add(custUserInfo);
		list.add(custUserInfo);

		assertEquals(redisOperationImpl.setObjectList("userList_1003", list, 0), "OK");
	}

	@Test
	public void test16GetObjectList() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜");
		custUserInfo.setUserDesc("测试设置用户缓存");
		custUserInfo.setUserLastLoginTime(new Date());

		List<Object> list = new ArrayList<Object>();
		list.add(custUserInfo);
		list.add(custUserInfo);
		list.add(custUserInfo);

		assertEquals(redisOperationImpl.getObjectList("userList_1003").toString(), list.toString());
	}

	@Test
	public void test17AppendObjectList() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜");
		custUserInfo.setUserDesc("测试设置用户缓存");
		custUserInfo.setUserLastLoginTime(new Date());

		List<Object> list = new ArrayList<Object>();
		list.add(custUserInfo);
		list.add(custUserInfo);
		list.add(custUserInfo);
		list.add(custUserInfo);

		String result = redisOperationImpl.appendObjectList("userList_1003", custUserInfo);
		assertTrue("OK".equals(result));
		assertEquals(redisOperationImpl.getObjectList("userList_1003").toString(), list.toString());
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
		assertEquals(redisOperationImpl.appendSet("javafarmwork", "servlet"), new Long(1));
	}

	@Test
	public void test23SetObjectSet() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜1");
		custUserInfo.setUserDesc("测试设置用户缓存1");
		custUserInfo.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo2 = new CustUserInfo();
		custUserInfo2.setUserId(1004L);
		custUserInfo2.setUserNickName("何明胜2");
		custUserInfo2.setUserDesc("测试设置用户缓存2");
		custUserInfo2.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo3 = new CustUserInfo();
		custUserInfo3.setUserId(1005L);
		custUserInfo3.setUserNickName("何明胜3");
		custUserInfo3.setUserDesc("测试设置用户缓存3");
		custUserInfo3.setUserLastLoginTime(new Date());

		Set<Object> set = new HashSet<Object>();
		set.add(custUserInfo);
		set.add(custUserInfo2);
		set.add(custUserInfo3);

		assertEquals(redisOperationImpl.setObjectSet("userSet_100:3_4_5", set, 0), "OK");
	}

	@Test
	public void test24GetObjectSet() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜1");
		custUserInfo.setUserDesc("测试设置用户缓存1");
		custUserInfo.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo2 = new CustUserInfo();
		custUserInfo2.setUserId(1004L);
		custUserInfo2.setUserNickName("何明胜2");
		custUserInfo2.setUserDesc("测试设置用户缓存2");
		custUserInfo2.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo3 = new CustUserInfo();
		custUserInfo3.setUserId(1005L);
		custUserInfo3.setUserNickName("何明胜3");
		custUserInfo3.setUserDesc("测试设置用户缓存3");
		custUserInfo3.setUserLastLoginTime(new Date());

		Set<Object> set = new HashSet<Object>();
		set.add(custUserInfo);
		set.add(custUserInfo2);
		set.add(custUserInfo3);

		Set<Object> result = redisOperationImpl.getObjectSet("userSet_100:3_4_5");
		assertTrue(result.toString() != "");
	}

	@Test
	public void test25AppendObjectSet() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1003L);
		custUserInfo.setUserNickName("何明胜1");
		custUserInfo.setUserDesc("测试设置用户缓存1");
		custUserInfo.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo2 = new CustUserInfo();
		custUserInfo2.setUserId(1004L);
		custUserInfo2.setUserNickName("何明胜2");
		custUserInfo2.setUserDesc("测试设置用户缓存2");
		custUserInfo2.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo3 = new CustUserInfo();
		custUserInfo3.setUserId(1005L);
		custUserInfo3.setUserNickName("何明胜3");
		custUserInfo3.setUserDesc("测试设置用户缓存3");
		custUserInfo3.setUserLastLoginTime(new Date());

		CustUserInfo custUserInfo4 = new CustUserInfo();
		custUserInfo4.setUserId(1005L);
		custUserInfo4.setUserNickName("何明胜4");
		custUserInfo4.setUserDesc("测试设置用户缓存4");
		custUserInfo4.setUserLastLoginTime(new Date());

		Set<Object> set = new HashSet<Object>();
		set.add(custUserInfo);
		set.add(custUserInfo2);
		set.add(custUserInfo3);
		set.add(custUserInfo4);

		String result = redisOperationImpl.appendObjectSet("userSet_100:3_4_5", new Object[] { custUserInfo4 });
		assertEquals(result, "OK");
		String resultStr = redisOperationImpl.getObjectSet("userSet_100:3_4_5").toString();
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
		assertEquals(redisOperationImpl.removeMap("redisSet", "redisSet"), new Long(1));
	}

	@Test
	public void test30existsMap() {
		assertEquals(redisOperationImpl.existsMap("redisSet", "redisSet"), false);
		assertEquals(redisOperationImpl.existsMap("redisSet", "redisSet1"), true);
	}

	@Test
	public void test31setObjectMap() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1005L);
		custUserInfo.setUserNickName("何明胜4");
		custUserInfo.setUserDesc("测试设置用户缓存4");
		custUserInfo.setUserLastLoginTime(new Date());

		Map<String, Object> map = new HashMap<>(4);
		map.put("uesrinfoMap", custUserInfo);
		map.put("uesrinfoMap1", custUserInfo);
		map.put("uesrinfoMap2", custUserInfo);

		assertEquals(redisOperationImpl.setObjectMap("redisObjectMap", map, 0), "OK");
	}

	@Test
	public void test32getObjectMap() {
		assertTrue(redisOperationImpl.getObjectMap("redisObjectMap").toString() != "");
	}

	@Test
	public void test33appendObjectMap() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1005L);
		custUserInfo.setUserNickName("何明胜4");
		custUserInfo.setUserDesc("测试设置用户缓存4");
		custUserInfo.setUserLastLoginTime(new Date());

		Map<String, Object> map1 = new HashMap<>(4);
		map1.put("uesrinfoMap3", custUserInfo);
		map1.put("uesrinfoMap4", custUserInfo);

		assertEquals(redisOperationImpl.appendObjectMap("redisObjectMap", map1), "OK");
	}

	@Test
	public void test34appendObjectMap() {
		assertEquals(redisOperationImpl.removeObjectMap("redisObjectMap", "uesrinfoMap4"), new Long(1));
		assertEquals(redisOperationImpl.removeObjectMap("redisObjectMap", "uesrinfoMap5"), new Long(0));
	}

	@Test
	public void test35existsObjectMap() {
		assertTrue(redisOperationImpl.existsObjectMap("redisObjectMap", "uesrinfoMap3"));
		assertFalse(redisOperationImpl.existsObjectMap("redisObjectMap", "uesrinfoMap6"));
	}

	@Test
	public void test36DeleteAll() {
		assertEquals(redisOperationImpl.flushAll(), "OK");
	}

	@Test
	public void test37SetObject() {
		CustUserInfo custUserInfo = new CustUserInfo();
		custUserInfo.setUserId(1005L);
		custUserInfo.setUserNickName("何明胜4");
		custUserInfo.setUserDesc("测试设置用户缓存4");
		custUserInfo.setUserLastLoginTime(new Date());

		assertEquals(redisOperationImpl.setObject(custUserInfo, custUserInfo, 0), "OK");
	}
}