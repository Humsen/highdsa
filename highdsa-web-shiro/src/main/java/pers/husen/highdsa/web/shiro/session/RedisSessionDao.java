package pers.husen.highdsa.web.shiro.session;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import pers.husen.highdsa.web.shiro.cache.ShiroRedisCache;

/**
 * @Desc 会话缓存管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月30日 下午3:35:46
 * 
 * @Version 1.0.0
 */
public class RedisSessionDao extends CachingSessionDAO {
	private static final String PREFIX = "shiro_session_id";

	private static final int EXPRIE = 10000;

	private static final Logger logger = LogManager.getLogger(RedisSessionDao.class.getName());

	@Override
	protected Serializable doCreate(Session session) {
		logger.info("--------doCreate-----");
		Serializable serializable = this.generateSessionId(session);
		assignSessionId(session, serializable);

		session.setTimeout(EXPRIE * 1000);

		ShiroRedisCache.getRedisOperation().setObject(PREFIX + serializable, session, EXPRIE);

		return serializable;
	}

	@Override
	protected Session doReadSession(Serializable serializable) {
		logger.info("--------doReadSession-----");
		Session session = null;

		Object value = ShiroRedisCache.getRedisOperation().getObject(PREFIX + serializable);
		if (value != null) {
			session = (Session) value;
			// jedis.expire((PREFIX + serializable).getBytes(), EXPRIE);
		}
		// 判断是否有会话 没有返回NULL
		if (session == null) {
			return null;
		}

		return session;
	}

	@Override
	protected void doUpdate(Session session) {
		logger.info("--------doUpdate-----");
		if (session == null) {
			return;
		}
		session.setTimeout(EXPRIE * 1000);

		ShiroRedisCache.getRedisOperation().setObject(PREFIX + session.getId(), session, EXPRIE);
	}

	@Override
	protected void doDelete(Session session) {
		logger.info("--------doDelete-----");

		ShiroRedisCache.getRedisOperation().removeObject(PREFIX + session.getId());
	}
}