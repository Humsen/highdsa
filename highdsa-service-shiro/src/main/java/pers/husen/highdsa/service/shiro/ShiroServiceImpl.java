package pers.husen.highdsa.service.shiro;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;
import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc shiro 服务实现
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月2日 下午10:47:05
 * 
 * @Version 1.0.0
 */
@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private SysUserManager sysUserManager;

	@Override
	public Session getSession(Serializable sessionId) {
		return sessionDAO.readSession(sessionId);
	}

	@Override
	public Serializable createSession(Session session) {
		return sessionDAO.create(session);
	}

	@Override
	public void updateSession(Session session) {
		sessionDAO.update(session);
	}

	@Override
	public void deleteSession(Session session) {
		sessionDAO.delete(session);
	}

	@Override
	public SysUser getPermissions(String userName) {
		SysUser sysUser = new SysUser();
		// 根据用户名查询当前用户拥有的角色
		Set<SysUser> sysUserSet = sysUserManager.findRoles(userName);
		Iterator<SysUser> it = sysUserSet.iterator();
		while (it.hasNext()) {
			sysUser = it.next();
		}

		// 根据用户名查询当前用户权限
		Set<SysUser> userPermissions = sysUserManager.findPermissions(userName);
		sysUser.setSysRolePermissionList(userPermissions.iterator().next().getSysRolePermissionList());

		return sysUser;
	}

	@Override
	public Serializable login(UsernamePasswordToken token) {
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);

		return subject.getSession().getId();
	}
}