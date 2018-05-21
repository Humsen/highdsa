package pers.husen.highdsa.security.client.cas.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

/**
 * @Desc 重写session工厂类类注入自定义的session
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月21日 上午3:28:25
 * 
 * @Version 1.0.0
 */
public class ShiroSessionFactory implements SessionFactory {

	@Override
	public Session createSession(SessionContext initData) {
		if (initData != null) {
			String host = initData.getHost();
			if (host != null) {
				return new ShiroSession(host);
			}
		}

		return new ShiroSession();
	}
}