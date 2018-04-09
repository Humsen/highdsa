package pers.husen.highdsa.cas.server.core;

import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pers.husen.highdsa.cas.server.auth.UsernamePasswordAuthenticationHandler;

/**
 * @Desc 登录验证计划配置
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月8日 下午10:44:57
 * 
 * @Version 1.0.0
 */
@Configuration()
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthenticationEventExecutionPlanConfigurer implements AuthenticationEventExecutionPlanConfigurer {
	@Autowired
	@Qualifier("servicesManager")
	private ServicesManager servicesManager;

	/**
	 * 注册验证器
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationHandler authenticationHandler() {
		// 优先验证
		final UsernamePasswordAuthenticationHandler handler = new UsernamePasswordAuthenticationHandler(UsernamePasswordAuthenticationHandler.class.getSimpleName(), servicesManager,
				new DefaultPrincipalFactory(), 10);

		return handler;
	}

	/**
	 * 注册自定义认证器
	 */
	@Override
	public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
		plan.registerAuthenticationHandler(authenticationHandler());
	}
}