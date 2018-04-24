package pers.husen.highdsa.shiro.config.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.customer.CustUser;
import pers.husen.highdsa.shiro.config.customer.controller.handler.UserSvc;

/**
 * @Desc 用户控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:06:00
 * 
 * @Version 1.0.2
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserSvc userSvc;

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		return userSvc.login(request);
	}

	@RequiresPermissions("user:list")
	@RequestMapping("/list")
	public ModelAndView showUserList() {
		return userSvc.showUserList();
	}

	@RequiresPermissions("user:add")
	@RequestMapping("/add")
	@ResponseBody
	public CustUser addUser(CustUser custUser, Long... roleIds) {
		return userSvc.addUser(custUser, roleIds);
	}

	@RequiresPermissions("user:showroles")
	@RequestMapping("/showroles")
	@ResponseBody
	public List<?> shwoRoles(String userName) {
		return userSvc.shwoRoles(userName);
	}

	@RequiresPermissions("role:list")
	@RequestMapping("/listRoles")
	@ResponseBody
	public List<?> getRoles() {
		return userSvc.getRoles();
	}

	@RequiresPermissions("user:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteUser(Long userId) {
		userSvc.deleteUser(userId);
	}

	@RequiresPermissions("user:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMoreUsers(Long... userIds) {
		userSvc.deleteMoreUsers(userIds);
	}

	@RequiresPermissions("user:corelationrole")
	@RequestMapping("/corelationRole")
	@ResponseBody
	public void corelationRole(Long userId, Long... roleIds) {
		userSvc.corelationRole(userId, roleIds);
	}
}