package pers.husen.highdsa.security.client.pac4j.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.Session;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.session.SessionStore;

/**
 * @Desc shiro会话存储
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 上午12:00:49
 * 
 * @Version 1.0.1
 */
public class ShiroSessionStore implements SessionStore<J2EContext> {
	private final static Logger logger = LogManager.getLogger(ShiroSessionStore.class.getName());

	/**
	 * 获取shiro session
	 * 
	 * @param createSession
	 * @return
	 */
	protected Session getSession(final boolean createSession) {
		return SecurityUtils.getSubject().getSession(createSession);
	}

	/**
	 * 获取shiro的sessionid
	 */
	@Override
	public String getOrCreateSessionId(final J2EContext context) {
		final Session session = getSession(false);
		if (session != null) {
			return session.getId().toString();
		}
		return null;
	}

	/**
	 * 获取shiro session中的属性
	 */
	@Override
	public Object get(final J2EContext context, final String key) {
		final Session session = getSession(false);
		if (session != null) {
			return session.getAttribute(key);
		}

		return null;
	}

	/**
	 * 设置session属性
	 */
	@Override
	public void set(final J2EContext context, final String key, final Object value) {
		final Session session = getSession(true);
		if (session != null) {
			try {
				session.setAttribute(key, value);
			} catch (final UnavailableSecurityManagerException e) {
				logger.warn("Should happen just once at startup in some specific case of Shiro Spring configuration", e);
			}
		}
	}

	/**
	 * 销毁session
	 */
	@Override
	public boolean destroySession(final J2EContext context) {
		getSession(true).stop();
		return true;
	}

	/**
	 * 获取shiro session并缓存用于单点登出
	 */
	@Override
	public Object getTrackableSession(final J2EContext context) {
		return getSession(true);
	}

	/**
	 * 从getTrackableSession中获取的session来构建SessionStore
	 */
	@Override
	public SessionStore<J2EContext> buildFromTrackableSession(final J2EContext context, final Object trackableSession) {
		if (trackableSession != null) {
			return new ShiroProvidedSessionStore((Session) trackableSession);
		}
		return null;
	}

	/**
	 * 刷新session属性,这里暂返回false,实际应用中需实现
	 */
	@Override
	public boolean renewSession(final J2EContext context) {
		return false;
	}
}