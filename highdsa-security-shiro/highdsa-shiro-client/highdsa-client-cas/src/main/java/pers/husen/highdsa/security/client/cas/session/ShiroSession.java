package pers.husen.highdsa.security.client.cas.session;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @Desc 由于SimpleSession lastAccessTime更改后也会调用SessionDao update方法, 增加标识位,
 *       如果只是更新lastAccessTime SessionDao update方法直接返回
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月21日 上午3:19:40
 * 
 * @Version 1.0.0
 */
public class ShiroSession extends SimpleSession {
	private static final long serialVersionUID = 5626390506651715231L;

	/** 除lastAccessTime以外其他字段发生改变时为true */
	private boolean isChanged;

	public ShiroSession() {
		super();
		this.setChanged(true);
	}

	public ShiroSession(String host) {
		super(host);
		this.setChanged(true);
	}

	/**
	 *  更新最后访问时间不更新redis
	 */
	@Override
	public void touch() {
		this.setChanged(false);
		super.touch();
	}

	@Override
	public void setId(Serializable id) {
		super.setId(id);
		this.setChanged(true);
	}

	@Override
	public void setStopTimestamp(Date stopTimestamp) {
		super.setStopTimestamp(stopTimestamp);
		this.setChanged(true);
	}

	@Override
	public void setExpired(boolean expired) {
		super.setExpired(expired);
		this.setChanged(true);
	}

	@Override
	public void setTimeout(long timeout) {
		super.setTimeout(timeout);
		this.setChanged(true);
	}

	@Override
	public void setHost(String host) {
		super.setHost(host);
		this.setChanged(true);
	}

	@Override
	public void setAttributes(Map<Object, Object> attributes) {
		super.setAttributes(attributes);
		this.setChanged(true);
	}

	@Override
	public void setAttribute(Object key, Object value) {
		super.setAttribute(key, value);
		this.setChanged(true);
	}

	@Override
	public Object removeAttribute(Object key) {
		this.setChanged(true);
		return super.removeAttribute(key);
	}

	/**
	 * 停止
	 */
	@Override
	public void stop() {
		super.stop();
		this.setChanged(true);
	}

	/**
	 * 设置过期
	 */
	@Override
	protected void expire() {
		this.stop();
		this.setExpired(true);
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected boolean onEquals(SimpleSession simpleSession) {
		return super.onEquals(simpleSession);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "ShiroSession [isChanged=" + isChanged + ", toString()=" + super.toString() + "]";
	}
}