package pers.husen.highdsa.common.constant;

/**
 * @Desc redis缓存key前缀常量
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 下午1:12:46
 * 
 * @Version 1.0.0
 */
public class RedisCacheKeyPrefix {
	/** 登录失败次数限制 */
	public static final String SHIRO_LOGIN_FAIL_COUNT = "shiro_login_fail_count:";

	/** shiro缓存 */
	public static final String SHIRO_REDIS_CACHE = "shiro_redis_cache:";
}