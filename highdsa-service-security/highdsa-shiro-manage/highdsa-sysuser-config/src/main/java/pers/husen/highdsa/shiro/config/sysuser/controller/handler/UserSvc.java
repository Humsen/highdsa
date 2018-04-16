package pers.husen.highdsa.shiro.config.sysuser.controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.service.mybatis.SysRoleManager;
import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 用户服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:27:02
 * 
 * @Version 1.0.0
 */
@Service
public class UserSvc {
	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private SysRoleManager sysRoleManager;

	public ModelAndView login(HttpServletRequest req) {
		String error = null;
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("error", error);
		return mav;
	}

	public ModelAndView showUserList() {
		List<?> list = sysUserManager.getAllUsers();
		ModelAndView mav = new ModelAndView("user-list");
		mav.addObject("users", list);
		return mav;
	}

	public SysUser addUser(SysUser user, Long... roleIds) {
		SysUser sysUser = sysUserManager.addUser(user, roleIds);

		return sysUser;
	}

	public List<?> shwoRoles(String userName) {
		return sysRoleManager.findRolesByUserName(userName).getSysRoleList();
	}

	public List<?> getRoles() {
		return sysRoleManager.findAllRoles();
	}

	public void deleteUser(Long userId) {
		sysUserManager.deleteUser(userId);
	}

	public void deleteMoreUsers(Long... userIds) {
		sysUserManager.deleteMoreUsers(userIds);
	}

	public void corelationRole(Long userId, Long... roleIds) {
		sysUserManager.updateUserRoles(userId, roleIds);
	}
}