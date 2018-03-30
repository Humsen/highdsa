package pers.husen.highdsa.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Desc 自定义缓存管理器
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 上午8:29:21
 * 
 * @Version 1.0.0
 */
public class ShiroRedisCacheManager implements CacheManager {
	
	public <K, V> Cache<K, V> getCache() throws CacheException {
		return new ShiroRedisCache<K, V>();
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new ShiroRedisCache<K, V>();
	}
}