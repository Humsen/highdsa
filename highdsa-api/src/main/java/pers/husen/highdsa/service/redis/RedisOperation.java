package pers.husen.highdsa.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Desc redis 操作服务接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月3日 下午5:13:32
 * 
 * @Version 1.0.0
 */
public interface RedisOperation {
	/**
	 * 设置String
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value);

	/**
	 * 设置String
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间,0为不超时
	 * @return
	 */
	public String set(String key, String value, int cacheSeconds);

	/**
	 * 获取String
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * 在key的值后面拼接字符串
	 * 
	 * @param key
	 * @param addString
	 * @return
	 */
	public Long append(String key, String addString);

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(String key);

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public Long del(String key);

	/**
	 * 删除多个
	 * 
	 * @param key
	 * @return
	 */
	public Long del(String... key);;

	/**
	 * 设置对象
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public String setObject(String key, Object value, int cacheSeconds);

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(String key);

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean existsObject(String key);

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public Long delObject(String key);

	/**
	 * 根据Object key设置对象
	 * 
	 * @param key
	 * @param value
	 */
	public String setObject(Object key, Object value, int cacheSeconds);

	/**
	 * 根据Object key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(Object key);

	/**
	 * 删除Object key的缓存
	 * 
	 * @param key
	 * @return
	 */
	public Long removeObject(Object key);

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public Long setList(String key, List<String> value, int cacheSeconds);

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getList(String key);

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long appendList(String key, String... value);

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public String setObjectList(String key, List<Object> value, int cacheSeconds);

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> getObjectList(String key);

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String appendObjectList(String key, Object... value);

	/**
	 * 一次设置多个 key 的值，成功返回 ok 表示所有的值都设置了，失败返回 0 表示没有任何值被设置 key1, value1, key2, value2
	 * 
	 * @param keys3Values
	 * @return
	 */
	public String mset(String... keys3Values);

	/**
	 * 返回所有(一个或多个)给定 key 的值
	 * 
	 * @param keys
	 * @return
	 */
	public List<String> mget(String... keys);

	/**
	 * 设置Set缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public Long setSet(String key, Set<String> value, int cacheSeconds);

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> getSet(String key);

	/**
	 * 向Set缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long appendSet(String key, String... value);

	/**
	 * 设置ObjectSet缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public String setObjectSet(String key, Set<Object> value, int cacheSeconds);

	/**
	 * 获取Object缓存
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> getObjectSet(String key);

	/**
	 * 向ObjectSet缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String appendObjectSet(String key, Object... value);

	/**
	 * 设置Map缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public String setMap(String key, Map<String, String> value, int cacheSeconds);

	/**
	 * 获取Map缓存
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key);

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String appendMap(String key, Map<String, String> value);

	/**
	 * 移除Map缓存中的值
	 * 
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public Long removeMap(String key, String mapKey);

	/**
	 * 删除map中的多个Key值
	 * 
	 * @param key
	 * @param mapKeys
	 * @return
	 */
	public Long removeMap(String key, String... mapKeys);

	/**
	 * 判断Map缓存中的Key是否存在
	 * 
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public Boolean existsMap(String key, String mapKey);

	/**
	 * 设置Map缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public String setObjectMap(String key, Map<String, Object> value, int cacheSeconds);

	/**
	 * 获取Map缓存
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, Object> getObjectMap(String key);

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String appendObjectMap(String key, Map<String, Object> value);

	/**
	 * 移除ObjectMap缓存中的值
	 * 
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public Long removeObjectMap(String key, String mapKey);

	/**
	 * 判断ObjectMap缓存中的Key是否存在
	 * 
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public Boolean existsObjectMap(String key, String mapKey);

	/**
	 * 清空整个 Redis 服务器的数据(删除所有数据库的所有 key )
	 * 
	 * @return
	 */
	public String flushAll();

	/**
	 * 清空当前数据库的所有key
	 * 
	 * @return
	 */
	public String flushDB();

	/**
	 * 获取数据库的数量
	 * 
	 * @return
	 */
	public Integer getDbSize();
}