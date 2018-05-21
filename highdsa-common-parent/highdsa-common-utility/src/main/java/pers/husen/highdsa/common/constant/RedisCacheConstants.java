package pers.husen.highdsa.common.constant;

/**
 * @Desc redis缓存的相关常量
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 下午1:12:46
 * 
 * @Version 1.0.3
 */
public class RedisCacheConstants {
	/** mybatis缓存 */
	public static final String MYBATIS_REDIS_CACHE = "mybatis_redis_cache:";

	/** shiro缓存 */
	public static final String SHIRO_REDIS_CACHE = "shiro_redis_cache:";
	/** 系统配置shiro会话缓存 */
	public static final String SHIRO_REDIS_SESSION_SYSTEM = "shiro_redis_session_system:";
	/** 客户配置shiro会话缓存 */
	public static final String SHIRO_REDIS_SESSION_CUSTOMER = "shiro_redis_session_customer:";
	/** app配置shiro会话缓存 */
	public static final String SHIRO_REDIS_SESSION_APP = "shiro_redis_session_app:";
	/** 注册验证码缓存 */
	public static final String REGISTER_REDIS_CODE = "register_redis_code:";

	/** 登录失败次数限制缓存 */
	public static final String SHIRO_LOGIN_FAIL_COUNT_SYSTEM = "shiro_login_fail_count_system:";
	public static final String SHIRO_LOGIN_FAIL_COUNT_CUSTOMER = "shiro_login_fail_count_customer:";
	public static final String SHIRO_LOGIN_FAIL_COUNT_APP = "shiro_login_fail_count_app:";
	/** shiro 登录失败最大次数 */
	public static final int SHIRO_LOGIN_FAIL_MAX_COUNT = 5;
}