package pers.husen.highdsa.service.shiro.session;

import org.apache.shiro.session.Session;

public class ShiroProvidedSessionStore extends ShiroSessionStore{

	private Session session;
	
	public ShiroProvidedSessionStore(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	protected Session getSession(final boolean createSession) {
        return getSession();
    }
}