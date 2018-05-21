package pers.husen.highdsa.security.client.cas.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.entity.po.customer.CustSessions;
import pers.husen.highdsa.common.transform.Serializer;
import pers.husen.highdsa.common.transform.ShiroSessionSerializer;
import pers.husen.highdsa.service.mybatis.CustSessionsManager;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc mysql 会话存储,先判断redis缓存,没有再从数据库取
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:10:04
 * 
 * @Version 1.0.3
 */
public class MysqlRedisSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(MysqlRedisSessionDao.class.getName());
	private static final String PREFIX = RedisCacheConstants.SHIRO_REDIS_SESSION_CUSTOMER;
	/** 默认过期时间 ,单位：毫秒,总共1小时 */
	private static final int EXPRIE = 60 * 60 * 1000;

	@Autowired
	private CustSessionsManager custSessionsManager;
	@Autowired
	private RedisOperation redisOperation;

	/**
	 * @param custSessionsManager
	 * @param redisOperation
	 */
	public MysqlRedisSessionDao(CustSessionsManager custSessionsManager, RedisOperation redisOperation) {
		super();
		this.custSessionsManager = custSessionsManager;
		this.redisOperation = redisOperation;
	}

	/**
	 * 如DefaultSessionManager在创建完session后会调用该方法；
	 * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
	 * 返回会话ID；主要此处返回的ID.equals(session.getId())；
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		logger.debug("sessionId: {}", sessionId);

		this.saveSession(session);

		return sessionId;
	}

	/**
	 * 重写CachingSessionDAO中readSession方法,如果Session中没有登陆信息就调用doReadSession方法从Redis中重读
	 */
	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
		Session session = getCachedSession(sessionId);
		
		if (session == null) {
			session = this.doReadSession(sessionId);
			if (session == null) {
				throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
			} else {
				// 缓存
				cache(session, session.getId());
			}
		}

		return session;
	}

	/**
	 * 根据会话ID获取会话
	 *
	 * @param sessionId
	 *            会话ID
	 * @return
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		ShiroSession shiroSession = null;
		try {
			shiroSession = (ShiroSession) Serializer.unserialize(redisOperation.get(this.getByteKey(sessionId)));

			if (shiroSession == null) {
				logger.info("redis缓存为空,从数据库获取");
				CustSessions custSessions = custSessionsManager.findBySessionId(String.valueOf(sessionId));
				shiroSession = (ShiroSession) ShiroSessionSerializer.deserialize(custSessions.getSessionValue());
			}

			if (shiroSession != null && shiroSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
				// 检查session是否过期
				shiroSession.validate();
				// 重置Redis中Session的最后访问时间
				shiroSession.setLastAccessTime(new Date());
				this.updateSession(shiroSession);
				logger.info("sessionId {} name {} 被读取并更新访问时间", sessionId, shiroSession.getClass().getName());
			}
		} catch (Exception e) {
			if (!(e instanceof ExpiredSessionException)) {
				logger.warn("读取Session失败", e);
			} else {
				logger.warn("session已失效:{}", e.getMessage());
			}
		}

		return shiroSession;
	}

	/**
	 * 扩展更新缓存机制，每次请求不重新更新session，更新session会延长session的失效时间
	 */
	@Override
	public void update(Session session) throws UnknownSessionException {
		doUpdate(session);
		if (session instanceof ValidatingSession) {
			if (((ValidatingSession) session).isValid()) {
				// 不更新ehcache中的session，使它在设定的时间内过期
				// cache(session, session.getId());
			} else {
				uncache(session);
			}
		} else {
			cache(session, session.getId());
		}
	}

	/**
	 * 更新会话;如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
	 */
	@Override
	protected void doUpdate(Session session) {
		// 如果会话过期/停止 没必要再更新了
		try {
			if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
				return;
			}
		} catch (Exception e) {
			logger.error("ValidatingSession error");
		}

		try {
			if (session instanceof ShiroSession) {
				// 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
				ShiroSession shiroSession = (ShiroSession) session;
				if (!shiroSession.isChanged()) {
					return;
				}

				shiroSession.setChanged(false);
				this.updateSession(session);
				logger.info("sessionId {} name {} 被更新", session.getId(), session.getClass().getName());

			} else if (session instanceof Serializable) {
				this.updateSession(session);
				logger.info("sessionId {} name {} 作为非ShiroSession对象被更新, ", session.getId(), session.getClass().getName());
			} else {
				logger.warn("sessionId {} name {} 不能被序列化 更新失败", session.getId(), session.getClass().getName());
			}
		} catch (Exception e) {
			logger.warn("更新Session失败", e);
		}
	}

	/**
	 * 删除会话；当会话过期/会话停止（如用户退出时）会调用
	 */
	@Override
	protected void doDelete(Session session) {
		try {
			// 从redis删除
			redisOperation.del(this.getByteKey(session.getId()));
			// 从数据库删除
			custSessionsManager.deleteBySessionId(String.valueOf(session.getId()));

			logger.debug("Session {} 被删除", session.getId());
		} catch (Exception e) {
			logger.warn("修改Session失败", e);
		}

	}

	/**
	 * 删除cache中缓存的Session
	 */
	public void uncache(Serializable sessionId) {
		Session session = this.readSession(sessionId);
		super.uncache(session);
		logger.info("取消session {} 的缓存", sessionId);
	}

	/**
	 * 
	 * 统计当前活动的session
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();

		Set<byte[]> keys = redisOperation.keys(PREFIX + "*");
		if (keys != null && keys.size() > 0) {
			for (byte[] key : keys) {
				Session s = (Session) Serializer.unserialize(redisOperation.get(key));
				sessions.add(s);
			}
		}

		return sessions;
	}

	/**
	 * save session
	 * 
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");

			return;
		}

		session.setTimeout(EXPRIE * 7 * 24);
		// 保存到数据库
		CustSessions custSessions = new CustSessions(String.valueOf(session.getId()), ShiroSessionSerializer.serialize(session));
		custSessions.setSessionCreateTime(new Date());
		custSessions.setSessionLastModifyTime(new Date());
		custSessions.setSessionValid(true);
		custSessionsManager.createSession(custSessions);

		// 保存到redis
		byte[] key = this.getByteKey(session.getId());
		byte[] value = Serializer.serialize(session);
		redisOperation.set(key, value, EXPRIE);
	}

	private void updateSession(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");

			return;
		}

		session.setTimeout(EXPRIE * 7 * 24);
		// 更新数据库会话
		String sessionValue = ShiroSessionSerializer.serialize(session);
		CustSessions custSessions = new CustSessions(String.valueOf(session.getId()), sessionValue);
		custSessions.setSessionLastModifyTime(new Date());
		custSessions.setSessionValid(true);
		custSessionsManager.modifyBySessionId(custSessions);

		// 保存到redis
		byte[] key = this.getByteKey(session.getId());
		byte[] value = Serializer.serialize(session);
		redisOperation.set(key, value, EXPRIE);
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId) {
		String preKey = PREFIX + sessionId;

		return preKey.getBytes();
	}
}