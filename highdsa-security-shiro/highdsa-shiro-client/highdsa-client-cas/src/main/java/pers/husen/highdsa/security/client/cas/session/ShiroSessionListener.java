package pers.husen.highdsa.security.client.cas.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Desc 发现用户推出后，Session没有从Redis中销毁，虽然当前重新new了一个，但会对统计带来干扰，通过SessionListener解决这个问题
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月21日 上午8:10:52
 * 
 * @Version 1.0.0
 */
public class ShiroSessionListener implements SessionListener {
	private static final Logger logger = LogManager.getLogger(ShiroSessionListener.class.getName());

	@Autowired
	private MysqlRedisSessionDao sessionDao;

	@Override
	public void onStart(Session session) {
		// 会话创建时触发
		logger.info("ShiroSessionListener session {} 被创建", session.getId());
	}

	@Override
	public void onStop(Session session) {
		sessionDao.delete(session);
		// 会话被停止时触发
		logger.info("ShiroSessionListener session {} 被销毁", session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		sessionDao.delete(session);
		// 会话过期时触发
		logger.info("ShiroSessionListener session {} 过期", session.getId());
	}
}