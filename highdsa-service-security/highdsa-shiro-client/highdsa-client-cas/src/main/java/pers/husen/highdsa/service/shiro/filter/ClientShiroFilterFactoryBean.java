package pers.husen.highdsa.service.shiro.filter;

import java.io.IOException;
import java.io.InputStream;

import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.config.Ini;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * @Desc 扩展ShiroFilterFactoryBean,加载自定义的Ini配置文件.使得每个引用此jar包的应用都可以自定义url.ini配置
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午1:28:31
 * 
 * @Version 1.0.0
 */
public class ClientShiroFilterFactoryBean extends ShiroFilterFactoryBean implements ApplicationContextAware {
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setFilterChainDefinitionsLocation(String filterChainDefinitionsLocation) {
		if (StringUtils.isEmpty(filterChainDefinitionsLocation)) {
			return;
		}
		
		Ini ini = null;
		if (StringUtils.hasText(filterChainDefinitionsLocation)) {
			InputStream is = null;
			
			try {
				is = ResourceUtils.getInputStreamForPath(filterChainDefinitionsLocation);
			} catch (IOException e) {
				throw new ConfigurationException(e);
			}
		
			if (is != null) {
				ini = new Ini();
				ini.load(is);
			} else {
				throw new ConfigurationException("Unable to load resource path '" + filterChainDefinitionsLocation + "'");
			}

			Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
			if (CollectionUtils.isEmpty(section)) {
				section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
			}

			setFilterChainDefinitionMap(section);
		}
	}
}