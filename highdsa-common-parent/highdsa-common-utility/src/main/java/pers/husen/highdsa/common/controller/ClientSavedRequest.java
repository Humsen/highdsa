package pers.husen.highdsa.common.controller;

import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc 保存请求,能保存schema://domain:port
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月3日 下午4:14:46
 * 
 * @Version 1.0.0
 */
public class ClientSavedRequest extends SavedRequest {
	private static final long serialVersionUID = 1L;

	private String scheme;
	private String domain;
	private int port;
	private String contextPath;
	/** 登录成功之后重定向的地址 */
	private String backUrl;

	public ClientSavedRequest(HttpServletRequest request, String backUrl) {
		super(request);
		this.scheme = request.getScheme();
		this.domain = request.getServerName();
		this.port = request.getServerPort();
		this.backUrl = backUrl;
		this.contextPath = request.getContextPath();
	}

	public String getScheme() {
		return scheme;
	}

	public String getDomain() {
		return domain;
	}

	public int getPort() {
		return port;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getBackUrl() {
		return backUrl;
	}

	/**
	 * 获取请求Url
	 */
	public String getRequestUrl() {
		String requestURI = getRequestURI();
		if (backUrl != null) {
			// 如果从外部传入了successUrl（登录成功之后重定向的地址），且以http://或https://开头那么直接返回
			if (backUrl.toLowerCase().startsWith("http://") || backUrl.toLowerCase().startsWith("https://")) {
				return backUrl;
			} else if (!backUrl.startsWith(contextPath)) {
				// 如果successUrl有值但没有上下文，拼上上下文
				requestURI = contextPath + backUrl;
			} else {
				// 否则，如果successUrl有值，直接赋值给requestUrl即可；否则，如果successUrl没值，那么requestUrl就是当前请求的地址；
				requestURI = backUrl;
			}
		}

		// 拼上url前边的schema, 如http或https
		StringBuilder requestUrl = new StringBuilder(scheme);
		requestUrl.append("://");
		// 拼上域名
		requestUrl.append(domain);

		if ("http".equalsIgnoreCase(scheme) && port != 80) {
			requestUrl.append(":").append(String.valueOf(port));
		} else if ("https".equalsIgnoreCase(scheme) && port != 443) {
			requestUrl.append(":").append(String.valueOf(port));
		}
		// 拼上重定向到的地址（带上下文）
		requestUrl.append(requestURI);
		// 如果successUrl没值，且有查询参数，拼上
		if (backUrl == null && getQueryString() != null) {
			requestUrl.append("?").append(getQueryString());
		}

		return requestUrl.toString();
	}
}