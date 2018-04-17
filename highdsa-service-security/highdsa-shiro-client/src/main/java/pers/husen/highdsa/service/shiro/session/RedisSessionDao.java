package pers.husen.highdsa.service.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;

import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.exception.db.NullPointerException;
import pers.husen.highdsa.common.transform.Serializer;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc 会话缓存管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 下午3:35:46
 * 
 * @Version 1.0.1
 */
public class RedisSessionDao extends MysqlSessionDao {
	private static final Logger logger = LogManager.getLogger(RedisSessionDao.class.getName());

	private static RedisOperation redisOperation;
	private static final String PREFIX = RedisCacheConstants.SHIRO_REDIS_SESSION;
	private static final int EXPRIE = 10000;

	/**
	 * @return the redisOperation
	 */
	public static RedisOperation getRedisOperation() {
		return redisOperation;
	}

	/**
	 * @param redisOperation
	 *            the redisOperation to set
	 */
	public static void setRedisOperation(RedisOperation redisOperation) {
		RedisSessionDao.redisOperation = redisOperation;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		// 如果会话过期/停止 没必要再更新了
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
			return;
		}
		if (session == null) {
			logger.error("session is null");
			return;
		}
		if (session.getId() == null) {
			logger.error("session id is null");
			return;
		}

		super.doUpdate(session);
		logger.trace("--------update-----");

		logger.debug("sessionId: {}", session.getId());
		byte[] key = this.getByteKey(session.getId());
		byte[] value = Serializer.serialize(session);
		session.setTimeout(EXPRIE * 1000);

		redisOperation.set(key, value, EXPRIE * 1000);
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		
		super.doDelete(session);
		
		logger.trace("--------delete-----");
		logger.debug("sessionId: {}", session.getId());
		
		redisOperation.del(this.getByteKey(session.getId()));
	}

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

	@Override
	protected Serializable doCreate(Session session) {
		// 数据库先创建
		super.doCreate(session);
		logger.trace("--------doCreate-----");

		Serializable sessionId = this.generateSessionId(session);

		logger.debug("sessionId: {}", sessionId);
		this.assignSessionId(session, sessionId);

		byte[] key = this.getByteKey(session.getId());
		byte[] value = Serializer.serialize(session);
		session.setTimeout(EXPRIE * 1000);

		redisOperation.set(key, value, EXPRIE * 1000);

		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// 先从缓存中获取session，如果没有再去数据库中获取
		logger.trace("--------doReadSession-----");

		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}

		logger.debug("sessionId: {}", sessionId);
		Session session = (Session) Serializer.unserialize(redisOperation.get(this.getByteKey(sessionId)));

		if (session == null) {
			session = super.doReadSession(sessionId);
		}

		if (session != null) {
			this.doCreate(session);
		} else {
			throw new NullPointerException("获取会话为空");
		}
		logger.debug("session.getId():{}", session.getId());

		return session;
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