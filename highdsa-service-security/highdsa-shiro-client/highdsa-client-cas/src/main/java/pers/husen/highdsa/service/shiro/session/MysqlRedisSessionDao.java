package pers.husen.highdsa.service.shiro.session;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import pers.husen.highdsa.common.constant.RedisCacheConstants;
import pers.husen.highdsa.common.entity.po.system.SysSessions;
import pers.husen.highdsa.common.transform.Serializer;
import pers.husen.highdsa.common.transform.ShiroSessionSerializer;
import pers.husen.highdsa.service.mybatis.SysSessionsManager;
import pers.husen.highdsa.service.redis.RedisOperation;

/**
 * @Desc mysql 会话存储,先判断redis缓存,没有再从数据库取
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:10:04
 * 
 * @Version 1.0.0
 */
public class MysqlRedisSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(MysqlRedisSessionDao.class.getName());

	private SysSessionsManager sysSessionsManager;
	private RedisOperation redisOperation;
	private static final String PREFIX = RedisCacheConstants.SHIRO_REDIS_SESSION;
	private static final int EXPRIE = 10000;

	/**
	 * @return the redisOperation
	 */
	public RedisOperation getRedisOperation() {
		return redisOperation;
	}

	/**
	 * @param redisOperation
	 *            the redisOperation to set
	 */
	public void setRedisOperation(RedisOperation redisOperation) {
		this.redisOperation = redisOperation;
	}

	/**
	 * @return the sysSessionsManager
	 */
	public SysSessionsManager getSysSessionsManager() {
		return sysSessionsManager;
	}

	/**
	 * @param sysSessionsManager
	 *            the sysSessionsManager to set
	 */
	public void setSysSessionsManager(SysSessionsManager sysSessionsManager) {
		this.sysSessionsManager = sysSessionsManager;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		logger.debug("sessionId: {}", sessionId);

		SysSessions sysSessions = new SysSessions(String.valueOf(sessionId), ShiroSessionSerializer.serialize(session));
		sysSessions.setSessionCreateTime(new Date());
		sysSessions.setSessionLastModifyTime(new Date());
		sysSessions.setSessionValid(true);
		sysSessionsManager.createSession(sysSessions);

		byte[] key = this.getByteKey(sessionId);
		byte[] value = Serializer.serialize(session);
		session.setTimeout(EXPRIE * 1000);
		redisOperation.set(key, value, EXPRIE * 1000);

		return sessionId;
	}

	@Override
	protected void doUpdate(Session session) {
		String sessionValue = ShiroSessionSerializer.serialize(session);
		logger.debug("session长度：{}", sessionValue.length());
		SysSessions sysSessions = new SysSessions(String.valueOf(session.getId()), sessionValue);
		sysSessions.setSessionLastModifyTime(new Date());
		sysSessions.setSessionValid(true);
		sysSessionsManager.updateBySessionId(sysSessions);

		byte[] key = this.getByteKey(session.getId());
		byte[] value = Serializer.serialize(session);
		session.setTimeout(EXPRIE * 1000);
		redisOperation.set(key, value, EXPRIE * 1000);
	}

	@Override
	protected void doDelete(Session session) {
		logger.trace("--------super.delete-----");
		sysSessionsManager.deleteBySessionId(String.valueOf(session.getId()));

		redisOperation.del(this.getByteKey(session.getId()));
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = (Session) Serializer.unserialize(redisOperation.get(this.getByteKey(sessionId)));

		if (session == null) {
			logger.trace("redis缓存为空，从数据库获取");

			SysSessions sysSessions = sysSessionsManager.findBySessionId(String.valueOf(sessionId));
			session = ShiroSessionSerializer.deserialize(sysSessions.getSessionValue());
		}

		if (session != null) {
			byte[] key = this.getByteKey(session.getId());
			byte[] value = Serializer.serialize(session);
			session.setTimeout(EXPRIE * 1000);

			redisOperation.set(key, value, EXPRIE * 1000);
		}

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