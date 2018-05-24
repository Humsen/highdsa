package pers.husen.highdsa.security.client.pac4j.session;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.AbstractValidatingSessionManager;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.springframework.util.ReflectionUtils;

import pers.husen.highdsa.common.entity.po.system.SysSessions;
import pers.husen.highdsa.common.transform.ShiroSessionSerializer;
import pers.husen.highdsa.service.mybatis.SysSessionsManager;

/**
 * @Desc 数据库会话过期验证 调度器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午3:49:25
 * 
 * @Version 1.0.2
 */
public class MysqlSessionValidationScheduler implements SessionValidationScheduler, Runnable {
	private static final Logger logger = LogManager.getLogger(MysqlSessionValidationScheduler.class.getName());

	/** 系统会话管理 */
	private SysSessionsManager sysSessionsManager;

	/** 会话验证管理 */
	private ValidatingSessionManager sessionManager;
	private ScheduledExecutorService service;
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
	private boolean enabled = false;

	public MysqlSessionValidationScheduler() {
		super();
	}

	public SysSessionsManager getSysSessionsManager() {
		return sysSessionsManager;
	}

	public void setSysSessionsManager(SysSessionsManager sysSessionsManager) {
		this.sysSessionsManager = sysSessionsManager;
	}

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
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
		if (this.interval > 1L) {
			this.service = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("system-session-validation-%d").daemon(true).build());

			this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
			this.enabled = true;
		}
	}

	@Override
	public void run() {
		sessionValidation();
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
		List<SysSessions> sessionList = sysSessionsManager.findListByPage(start, size);

		while (sessionList.size() > 0) {
			for (SysSessions sysSessions : sessionList) {
				try {
					Session session = ShiroSessionSerializer.deserialize(sysSessions.getSessionValue());
					Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class, "validate", Session.class, SessionKey.class);
					validateMethod.setAccessible(true);
					ReflectionUtils.invokeMethod(validateMethod, sessionManager, session, new DefaultSessionKey(session.getId()));
				} catch (Exception e) {
					// ignore
				}
			}
			start = start + size;

			sessionList = sysSessionsManager.findListByPage(start, size);
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