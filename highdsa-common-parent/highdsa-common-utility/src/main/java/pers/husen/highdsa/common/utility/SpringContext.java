package pers.husen.highdsa.common.utility;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Desc 获取web应用上下文
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 下午1:13:11
 * 
 * @Version 1.0.0
 */
public class SpringContext {
	public static WebApplicationContext getContext() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

		return webApplicationContext;
	}
}
