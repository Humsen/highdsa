package pers.husen.highdsa.service.shiro.session;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import pers.husen.highdsa.common.entity.po.system.SysSessions;
import pers.husen.highdsa.common.transform.ShiroSessionSerializer;
import pers.husen.highdsa.service.mybatis.SysSessionsManager;

/**
 * @Desc mysql 会话存储,先super判断redis缓存,没有再从数据库取
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午3:45:41
 * 
 * @Version 1.0.0
 */
public class MysqlSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(MysqlSessionDao.class.getName());

	private SysSessionsManager sysSessionsManager;

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
		logger.trace("--------super.doCreate-----");
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		logger.debug("sessionId: {}", sessionId);

		SysSessions sysSessions = new SysSessions(String.valueOf(sessionId), ShiroSessionSerializer.serialize(session));
		sysSessions.setSessionCreateTime(new Date());
		sysSessions.setSessionLastModifyTime(new Date());
		sysSessions.setSessionValid(true);
		
		sysSessionsManager.createSession(sysSessions);

		return session.getId();
	}

	@Override
	protected void doUpdate(Session session) {
		logger.trace("--------super.update-----");

		SysSessions sysSessions = new SysSessions(String.valueOf(session.getId()), ShiroSessionSerializer.serialize(session));
		sysSessions.setSessionLastModifyTime(new Date());
		sysSessions.setSessionValid(true);
		
		sysSessionsManager.updateBySessionId(sysSessions);
	}

	@Override
	protected void doDelete(Session session) {
		logger.trace("--------super.delete-----");
		sysSessionsManager.deleteBySessionId(String.valueOf(session.getId()));
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.trace("--------super.doReadSession-----");
		SysSessions sysSessions = sysSessionsManager.findBySessionId(String.valueOf(sessionId));

		return ShiroSessionSerializer.deserialize(sysSessions.getSessionValue());
	}
}