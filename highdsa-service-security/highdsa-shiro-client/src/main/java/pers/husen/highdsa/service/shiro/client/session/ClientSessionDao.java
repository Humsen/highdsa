package pers.husen.highdsa.service.shiro.client.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import pers.husen.highdsa.service.shiro.ShiroService;

import java.io.Serializable;

/**
 * @Desc 客户端会话管理类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月2日 下午11:59:25
 * 
 * @Version 1.0.0
 */
public class ClientSessionDao extends CachingSessionDAO {
	private static final Logger logger = LogManager.getLogger(ClientSessionDao.class.getName());
	
	private ShiroService shiroService;

	/**
	 * @return the shiroService
	 */
	public ShiroService getShiroService() {
		return shiroService;
	}

	/**
	 * @param shiroService
	 *            the shiroService to set
	 */
	public void setShiroService(ShiroService shiroService) {
		this.shiroService = shiroService;
	}

	@Override
	protected void doDelete(Session session) {
		logger.debug("删除session, sessionId: {}", session.getId());
		
		shiroService.deleteSession(session);
	}

	@Override
	protected void doUpdate(Session session) {
		logger.debug("更新session, sessionId: {}", session.getId());
		
		shiroService.updateSession(session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = shiroService.createSession(session);
		assignSessionId(session, sessionId);
		
		logger.debug("创建session, sessionId: {}", session.getId());
		
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("读取session, sessionId: {}", sessionId);
		
		Session session =  shiroService.getSession(sessionId);
		logger.debug("session.getId():{}",session.getId());
		
		
		return session;
	}
}