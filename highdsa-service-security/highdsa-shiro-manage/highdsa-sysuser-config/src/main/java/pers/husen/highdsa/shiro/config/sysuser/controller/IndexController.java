package pers.husen.highdsa.shiro.config.sysuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pers.husen.highdsa.common.controller.BaseController;
import pers.husen.highdsa.shiro.config.sysuser.controller.handler.IndexSvc;

/**
 * @Desc 首页控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:05:05
 * 
 * @Version 1.0.0
 */
@Controller
public class IndexController extends BaseController{
	@Autowired
	private IndexSvc indexSvc;

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView index(ModelMap map) {
		return indexSvc.index(map, request);
	}
}