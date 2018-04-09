package pers.husen.highdsa.web.shiro.handler;

import org.apache.shiro.SecurityUtils;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import io.buji.pac4j.subject.Pac4jPrincipal;

/**
 * @Desc shiro 控制器服务类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:22:19
 * 
 * @Version 1.0.0
 */
@Service
public class ShiroSvc {
	/**
	 * 测试未登录,会跳转到登录界面
	 * 
	 * @return
	 */
	public String helloWorld() {
		return "hello world";
	}

	public String index(ModelMap map) {
		// 获取用户身份
		Pac4jPrincipal p = SecurityUtils.getSubject().getPrincipals().oneByType(Pac4jPrincipal.class);

		if (p != null) {
			CommonProfile profile = p.getProfile();
			map.put("profile", profile);
		}

		return "index";
	}
}