package pers.husen.highdsa.common.constant;

/**
 * @Desc redis缓存的相关常量
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 下午1:12:46
 * 
 * @Version 1.0.1
 */
public class RedisCacheConstants {
	/** mybatis缓存 */
	public static final String MYBATIS_REDIS_CACHE = "mybatis_redis_cache:";

	/** shiro缓存 */
	public static final String SHIRO_REDIS_CACHE = "shiro_redis_cache:";

	/** 登录失败次数限制缓存 */
	public static final String SHIRO_LOGIN_FAIL_COUNT = "shiro_login_fail_count:";
	/** shiro会话缓存 */
	public static final String SHIRO_REDIS_SESSION = "shiro_redis_session:";
	/** shiro 登录失败最大次数 */
	public static final int SHIRO_LOGIN_FAIL_MAX_COUNT = 5;
}