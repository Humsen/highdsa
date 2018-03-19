package pers.husen.highdsa.service.mybatis.druid;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.page.PageServlet;
import com.alibaba.dubbo.container.page.ResourceFilter;

/**
 * @Desc 配置druid监控的jetty容器
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月19日 下午11:59:42
 * 
 * @Version 1.0.0
 */
public class JettyContainer implements Container {
	public static final String JETTY_PORT = "dubbo.jetty.port";
	public static final String JETTY_DIRECTORY = "dubbo.jetty.directory";
	public static final String JETTY_PAGES = "dubbo.jetty.page";
	public static final int DEFAULT_JETTY_PORT = 8082;
	private static final Logger logger = LoggerFactory.getLogger(JettyContainer.class);
	SelectChannelConnector connector;

	@Override
	public void start() {
		String serverPort = ConfigUtils.getProperty(JETTY_PORT);
		int port;
		if (serverPort == null || serverPort.length() == 0) {
			port = DEFAULT_JETTY_PORT;
		} else {
			port = Integer.parseInt(serverPort);
		}
		connector = new SelectChannelConnector();
		connector.setPort(port);
		//
		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);  
		//ServletHandler handler = new ServletHandler();

		String resources = ConfigUtils.getProperty(JETTY_DIRECTORY);
		if (resources != null && resources.length() > 0) {
			FilterHolder resourceHolder = handler.addFilter(ResourceFilter.class, "/*", 0);
			resourceHolder.setInitParameter("resources", resources);
		}

		ServletHolder pageHolder = handler.addServlet(PageServlet.class, "/*");
		pageHolder.setInitParameter("pages", ConfigUtils.getProperty(JETTY_PAGES));
		pageHolder.setInitOrder(2);

		/* -------------------- 添加druid开始  -------------------*/
		
		// 这里是新添加的，作用于druid日志监控系统的
		ServletHolder druidHolder = new ServletHolder(StatViewServlet.class);
		druidHolder.setInitParameter("loginUsername", "husen");
		druidHolder.setInitParameter("loginPassword", "husen");
		
		// handler.addServletWithMapping(StatViewServlet.class, "/druid/*");
		handler.addServlet(druidHolder, "/druid/*");
		/* -------------------- 添加druid结束 -------------------*/
		
		Server server = new Server();
		server.addConnector(connector);
		server.setHandler(handler);
		try {
			server.start();
		} catch (Exception e) {
			throw new IllegalStateException("Failed to start jetty server on " + NetUtils.getLocalHost() + ":" + port
					+ ", cause: " + e.getMessage(), e);
		}
	}

	@Override
	public void stop() {
		try {
			if (connector != null) {
				connector.close();
				connector = null;
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}
}