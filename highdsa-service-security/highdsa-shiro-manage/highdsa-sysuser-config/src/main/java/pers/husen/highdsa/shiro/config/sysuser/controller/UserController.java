package pers.husen.highdsa.shiro.config.sysuser.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.entity.po.system.SysUser;
import pers.husen.highdsa.service.mybatis.SysRoleManager;
import pers.husen.highdsa.service.mybatis.SysUserManager;

/**
 * @Desc 用户控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:06:00
 * 
 * @Version 1.0.1
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private SysRoleManager sysRoleManager;

	@RequestMapping("/login")
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

	@RequiresPermissions("user:list")
	@RequestMapping("/list")
	public ModelAndView showUserList() {
		List<?> list = sysUserManager.getAllUsers();
		ModelAndView mav = new ModelAndView("user-list");
		mav.addObject("users", list);
		return mav;
	}

	@RequiresPermissions("user:add")
	@RequestMapping("/add")
	@ResponseBody
	public SysUser addUser(SysUser user, Long... roleIds) {
		SysUser sysUser = sysUserManager.addUser(user, roleIds);
		
		return sysUser;
	}

	@RequiresPermissions("user:showroles")
	@RequestMapping("/showroles")
	@ResponseBody
	public List<?> shwoRoles(String userName) {
		return sysRoleManager.findRolesByUserName(userName).getSysRoleList();
	}

	@RequiresPermissions("role:list")
	@RequestMapping("/listRoles")
	@ResponseBody
	public List<?> getRoles() {
		return sysRoleManager.findAllRoles();
	}

	@RequiresPermissions("user:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteUser(Long userId) {
		sysUserManager.deleteUser(userId);
	}

	@RequiresPermissions("user:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMoreUsers(Long... userIds) {
		sysUserManager.deleteMoreUsers(userIds);
	}

	@RequiresPermissions("user:corelationrole")
	@RequestMapping("/corelationRole")
	@ResponseBody
	public void corelationRole(Long userId, Long... roleIds) {
		sysUserManager.updateUserRoles(userId, roleIds);
	}
}