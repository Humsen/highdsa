package pers.husen.highdsa.security.client.cas.session;

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
 * @Desc mysql 会话存储,先super判断redis缓存,没有再从数据库取
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午3:45:41
 * 
 * @Version 1.0.1
 */
public class MysqlSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(MysqlSessionDao.class.getName());

	private CustSessionsManager custSessionsManager;

	/**
	 * @return the custSessionsManager
	 */
	public CustSessionsManager getCustSessionsManager() {
		return custSessionsManager;
	}

	/**
	 * @param custSessionsManager
	 *            the custSessionsManager to set
	 */
	public void setCustSessionsManager(CustSessionsManager custSessionsManager) {
		this.custSessionsManager = custSessionsManager;
	}

	@Override
	protected Serializable doCreate(Session session) {
		logger.trace("--------super.doCreate-----");
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
		logger.trace("--------super.update-----");

		String sessionValue = ShiroSessionSerializer.serialize(session);
		logger.debug("session长度：{}", sessionValue.length());
		CustSessions custSessions = new CustSessions(String.valueOf(session.getId()), sessionValue);
		custSessions.setSessionLastModifyTime(new Date());
		custSessions.setSessionValid(true);

		custSessionsManager.modifyBySessionId(custSessions);
	}

	@Override
	protected void doDelete(Session session) {
		logger.trace("--------super.delete-----");
		custSessionsManager.deleteBySessionId(String.valueOf(session.getId()));
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.trace("--------super.doReadSession-----");
		CustSessions custSessions = custSessionsManager.findBySessionId(String.valueOf(sessionId));

		return ShiroSessionSerializer.deserialize(custSessions.getSessionValue());
	}
}