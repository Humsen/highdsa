package pers.husen.highdsa.shiro.config.customer.controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.service.mybatis.CustRoleManager;
import pers.husen.highdsa.service.mybatis.CustUserManager;

/**
 * @Desc 用户服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 上午11:27:02
 * 
 * @Version 1.0.1
 */
@Service
public class UserSvc {
	@Autowired
	private CustUserManager custUserManager;
	@Autowired
	private CustRoleManager custRoleManager;

	public ModelAndView login(HttpServletRequest request) {
		String error = null;
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
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
		List<?> list = custUserManager.getAllUsers();
		ModelAndView mav = new ModelAndView("user-list");
		mav.addObject("users", list);
		return mav;
	}

	public CustUser addUser(CustUser custUser, Long... roleIds) {
		CustUser user = custUserManager.addUser(custUser, roleIds);

		return user;
	}

	public List<?> shwoRoles(String userName) {
		return custRoleManager.findRolesByUserName(userName).getCustRoleList();
	}

	public List<?> getRoles() {
		return custRoleManager.findAllRoles();
	}

	public void deleteUser(Long userId) {
		custUserManager.deleteUser(userId);
	}

	public void deleteMoreUsers(Long... userIds) {
		custUserManager.deleteMoreUsers(userIds);
	}

	public void corelationRole(Long userId, Long... roleIds) {
		custUserManager.updateUserRoles(userId, roleIds);
	}
}