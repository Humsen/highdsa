package pers.husen.highdsa.service.shiro.client.filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;

/**
 * @Desc 过滤器工厂
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:08:16
 * 
 * @Version 1.0.0
 */
public class ClientShiroFilterFactoryBean extends ShiroFilterFactoryBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 设置拦截器,设置格式如:filterName=filterBeanName;filterName=filterBeanName 多个之间分号分隔;
	 * 然后通过applicationContext 获取 filterBeanName对应的Bean 注册到拦截器Map中
	 * 
	 * @param filters
	 */
	public void setFiltersStr(String filters) {
		if (StringUtils.isEmpty(filters)) {
			return;
		}

		String[] filterArray = filters.split(";");
		for (String filter : filterArray) {
			String[] filterKeyValue = filter.split("=");
			getFilters().put(filterKeyValue[0], (Filter) applicationContext.getBean(filterKeyValue[1]));
		}
	}

	/**
	 * 设置拦截器链,设置格式如 url=filterName1[config],filterName2;
	 * url=filterName1[config],filterName2; 多个之间分号 分隔
	 * 
	 * @param filterChainDefinitions
	 */
	public void setFilterChainDefinitionsStr(String filterChainDefinitions) {
		if (StringUtils.isEmpty(filterChainDefinitions)) {
			return;
		}
		
		String[] chainDefinitionsArray = filterChainDefinitions.split(";");
		
		for (String filter : chainDefinitionsArray) {
			String[] filterKeyValue = filter.split("=");
			getFilterChainDefinitionMap().put(filterKeyValue[0], filterKeyValue[1]);
		}
	}
}