package pers.husen.highdsa.service.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import pers.husen.highdsa.common.constant.RedisCacheConstants;

/**
 * @Desc 密码验证服务,带失败重试次数限制
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午11:30:16
 * 
 * @Version 1.0.2
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	private static final Logger logger = LogManager.getLogger(RetryLimitHashedCredentialsMatcher.class.getName());
	
	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(RedisCacheConstants.SHIRO_LOGIN_FAIL_COUNT + username);
		logger.debug("retryCount: " + retryCount);

		if (retryCount == null) {
			logger.debug("retryCount 为Null, 初始化为0");
			
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > RedisCacheConstants.SHIRO_LOGIN_FAIL_MAX_COUNT) {
			logger.warn("retryCount 大于5,登录失败超过5次");
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}