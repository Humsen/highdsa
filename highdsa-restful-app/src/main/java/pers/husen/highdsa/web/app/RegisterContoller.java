package pers.husen.highdsa.web.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.husen.highdsa.common.entity.vo.restful.ResponseJson;
import pers.husen.highdsa.common.transform.ConvertRequestParams;
import pers.husen.highdsa.web.app.handler.RegisterSvc;

/**
 * @Desc 注册控制器
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月15日 上午1:27:06
 * 
 * @Version 1.0.1
 */
@Controller
@RequestMapping(value = "/register/v1")
public class RegisterContoller {
	@Autowired
	RegisterSvc registerSvc;

	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseJson createUser(HttpServletRequest request) throws Exception {
		Map<String, String> jsonMap = ConvertRequestParams.params2Map(request);
		System.out.println("收到请求：" + jsonMap);

		return registerSvc.createUser(jsonMap);
	}
}