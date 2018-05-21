package pers.husen.highdsa.client.restful.session;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import pers.husen.highdsa.common.entity.po.customer.CustSessions;
import pers.husen.highdsa.common.transform.ShiroSessionSerializer;
import pers.husen.highdsa.service.mybatis.CustSessionsManager;

/**
 * @Desc mysql 会话存储,先判断redis缓存,没有再从数据库取.由于在securityManager里配置了缓存,所以此处不必再多此一举去缓存.
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:10:04
 * 
 * @Version 1.0.2
 */
public class MysqlSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(MysqlSessionDao.class.getName());
	
	private CustSessionsManager custSessionsManager;

	/**
	 * @param custSessionsManager
	 */
	public MysqlSessionDao(CustSessionsManager custSessionsManager) {
		super();
		this.custSessionsManager = custSessionsManager;
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

		return sessionId;
	}

	@Override
	protected void doUpdate(Session session) {
		// 更新数据库会话
		String sessionValue = ShiroSessionSerializer.serialize(session);
		CustSessions custSessions = new CustSessions(String.valueOf(session.getId()), sessionValue);
		custSessions.setSessionLastModifyTime(new Date());
		custSessions.setSessionValid(true);
		custSessionsManager.modifyBySessionId(custSessions);
	}

	@Override
	protected void doDelete(Session session) {
		custSessionsManager.deleteBySessionId(String.valueOf(session.getId()));
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;

		if (sessionId != null) {
			CustSessions custSessions = custSessionsManager.findBySessionId(String.valueOf(sessionId));
			session = ShiroSessionSerializer.deserialize(custSessions.getSessionValue());
		}

		return session;
	}
}