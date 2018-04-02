package pers.husen.highdsa.service.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;

import java.io.Serializable;

/**
 * @Desc shiro API
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月2日 下午10:44:23
 * 
 * @Version 1.0.0
 */
public interface ShiroService {
	public Session getSession(Serializable sessionId);

	Serializable createSession(Session session);

	public void updateSession(Session session);

	public void deleteSession(Session session);

	public SysUser getPermissions(String userName);

	public Serializable login(UsernamePasswordToken token);
}