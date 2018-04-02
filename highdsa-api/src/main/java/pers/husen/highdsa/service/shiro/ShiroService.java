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
	/**
	 * 获取shiro session
	 * 
	 * @param sessionId
	 * @return
	 */
	public Session getSession(Serializable sessionId);

	/**
	 * 创建shiro session
	 * 
	 * @param session
	 * @return
	 */
	Serializable createSession(Session session);

	/**
	 * 更新shiro session
	 * 
	 * @param session
	 */
	public void updateSession(Session session);

	/**
	 * 删除shiro session
	 * 
	 * @param session
	 */
	public void deleteSession(Session session);

	/**
	 * 获取当前用户所有角色和权限
	 * 
	 * @param userName
	 * @return
	 */
	public SysUser getPermissions(String userName);

	/**
	 * 用户登录接口
	 * 
	 * @param token
	 * @return
	 */
	public Serializable login(UsernamePasswordToken token);
}