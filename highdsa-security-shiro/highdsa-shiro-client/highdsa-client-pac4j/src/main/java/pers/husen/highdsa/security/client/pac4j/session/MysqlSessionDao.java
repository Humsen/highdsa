package pers.husen.highdsa.security.client.pac4j.session;

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

	int readCount = 0;
	int updateCount = 0;

	private SysSessionsManager sysSessionsManager;

	/**
	 * @param custSessionsManager
	 */
	public MysqlSessionDao(SysSessionsManager sysSessionsManager) {
		super();
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

		return sessionId;
	}

	@Override
	protected void doUpdate(Session session) {
		System.out.println("更新次数：" + (++updateCount));

		// 更新数据库会话
		String sessionValue = ShiroSessionSerializer.serialize(session);
		SysSessions sysSessions = new SysSessions(String.valueOf(session.getId()), sessionValue);
		sysSessions.setSessionLastModifyTime(new Date());
		sysSessions.setSessionValid(true);
		sysSessionsManager.modifyBySessionId(sysSessions);
	}

	@Override
	protected void doDelete(Session session) {
		sysSessionsManager.deleteBySessionId(String.valueOf(session.getId()));
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;

		if (sessionId != null) {
			SysSessions sysSessions = sysSessionsManager.findBySessionId(String.valueOf(sessionId));
			session = ShiroSessionSerializer.deserialize(sysSessions.getSessionValue());
		}

		return session;
	}
}