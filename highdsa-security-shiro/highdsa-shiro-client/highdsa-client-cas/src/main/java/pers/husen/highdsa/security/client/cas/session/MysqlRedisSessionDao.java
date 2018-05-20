package pers.husen.highdsa.security.client.cas.session;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
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
 * @Version 1.0.2
 */
public class MysqlRedisSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(MysqlRedisSessionDao.class.getName());
	private static final String PREFIX = RedisCacheConstants.SHIRO_REDIS_SESSION_CUSTOMER;
	/** 默认过期时间 ,单位：毫秒,总共1小时 */
	private static final int EXPRIE = 60 * 60 * 1000;

	int readCount = 0;
	int updateCount = 0;

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

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		logger.debug("sessionId: {}", sessionId);

		CustSessions custSessions = new CustSessions(String.valueOf(sessionId), ShiroSessionSerializer.serialize(session));
		custSessions.setSessionCreateTime(new Date());
		custSessions.setSessionLastModifyTime(new Date());
		custSessions.setSessionValid(true);
		custSessionsManager.createSession(custSessions);

		byte[] key = this.getByteKey(sessionId);
		byte[] value = Serializer.serialize(session);
		session.setTimeout(EXPRIE * 1000);
		redisOperation.set(key, value, EXPRIE * 1000);

		return sessionId;
	}

	@Override
	protected void doUpdate(Session session) {
		System.out.println("更新次数：" + (++updateCount));
		String sessionValue = ShiroSessionSerializer.serialize(session);
		// logger.debug("session长度：{}", sessionValue.length());
		CustSessions custSessions = new CustSessions(String.valueOf(session.getId()), sessionValue);
		custSessions.setSessionLastModifyTime(new Date());
		custSessions.setSessionValid(true);
		custSessionsManager.modifyBySessionId(custSessions);

		byte[] key = this.getByteKey(session.getId());
		byte[] value = Serializer.serialize(session);
		session.setTimeout(EXPRIE * 1000);
		redisOperation.set(key, value, EXPRIE * 1000);
	}

	@Override
	protected void doDelete(Session session) {
		custSessionsManager.deleteBySessionId(String.valueOf(session.getId()));

		redisOperation.del(this.getByteKey(session.getId()));
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("读取次数：" + (++readCount));
		Session session = (Session) Serializer.unserialize(redisOperation.get(this.getByteKey(sessionId)));

		if (session == null) {
			logger.trace("redis缓存为空,从数据库获取");

			CustSessions custSessions = custSessionsManager.findBySessionId(String.valueOf(sessionId));
			session = ShiroSessionSerializer.deserialize(custSessions.getSessionValue());
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