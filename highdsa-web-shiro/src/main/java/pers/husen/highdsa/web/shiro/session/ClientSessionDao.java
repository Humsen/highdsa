package pers.husen.highdsa.web.shiro.session;

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
	private ShiroService shiroService;

	@Override
	protected void doDelete(Session session) {
		shiroService.deleteSession(session);
	}

	@Override
	protected void doUpdate(Session session) {
		shiroService.updateSession(session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = shiroService.createSession(session);
		assignSessionId(session, sessionId);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return shiroService.getSession(sessionId);
	}
}