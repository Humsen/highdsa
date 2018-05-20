package pers.husen.highdsa.security.client.cas.session;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.AbstractValidatingSessionManager;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.springframework.util.ReflectionUtils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import pers.husen.highdsa.common.entity.po.customer.CustSessions;
import pers.husen.highdsa.common.transform.ShiroSessionSerializer;
import pers.husen.highdsa.service.mybatis.CustSessionsManager;

/**
 * @Desc 数据库会话过期验证 调度器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午3:49:25
 * 
 * @Version 1.0.1
 */
public class MysqlSessionValidationScheduler implements SessionValidationScheduler {
	private static final Logger logger = LogManager.getLogger(MysqlSessionValidationScheduler.class.getName());

	/** 客户会话管理 */
	private CustSessionsManager custSessionsManager;

	/** 会话验证管理 */
	private ValidatingSessionManager sessionManager;
	private ScheduledExecutorService service;
	private boolean enabled = false;

	public MysqlSessionValidationScheduler(CustSessionsManager custSessionsManager, ValidatingSessionManager sessionManager) {
		super();
		this.custSessionsManager = custSessionsManager;
		this.sessionManager = sessionManager;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Creates a single thread {@link java.util.concurrent.ScheduledExecutorService}
	 * to validate sessions at fixed intervals and enables this scheduler. The
	 * executor is created as a daemon thread to allow JVM to shut down Implement an
	 * integration test to test for jvm exit as part of the standalone example (so
	 * we don't have to change the unit test execution model for the core module)
	 */
	@Override
	public void enableSessionValidation() {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("session-validation-%d").build();
		// Common Thread Pool
		ExecutorService pool = new ThreadPoolExecutor(5, 500, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
		pool.execute(() -> {
			// 开始验证
			sessionValidation();
		});
		// gracefully shutdown
		pool.shutdown();
	}

	/**
	 * 会话验证的函数
	 */
	public void sessionValidation() {
		if (logger.isDebugEnabled()) {
			logger.debug("Executing session validation...");
		}
		long startTime = System.currentTimeMillis();

		// 分页获取会话并验证
		// 起始记录
		int start = 0;
		// 每页大小
		int size = 20;
		List<CustSessions> sessionList = custSessionsManager.findListByPage(start, size);

		while (sessionList.size() > 0) {
			for (CustSessions custSessions : sessionList) {
				try {
					Session session = ShiroSessionSerializer.deserialize(custSessions.getSessionValue());
					Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class, "validate", Session.class, SessionKey.class);
					validateMethod.setAccessible(true);
					ReflectionUtils.invokeMethod(validateMethod, sessionManager, session, new DefaultSessionKey(session.getId()));
				} catch (Exception e) {
					// ignore
				}
			}
			start = start + size;

			sessionList = custSessionsManager.findListByPage(start, size);
		}

		long stopTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("Session validation completed successfully in " + (stopTime - startTime) + " milliseconds.");
		}
	}

	@Override
	public void disableSessionValidation() {
		this.service.shutdownNow();
		this.enabled = false;
	}
}