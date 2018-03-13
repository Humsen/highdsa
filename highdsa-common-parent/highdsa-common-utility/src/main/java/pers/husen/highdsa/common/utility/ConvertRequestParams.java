package pers.husen.highdsa.common.utility;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc json和request请求参数直接相互转化
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午3:43:05
 * 
 * @Version 1.0.0
 */
public class ConvertRequestParams {
	/**
	 * 请求参数的字符串形式(如name=husen&pwd=123)转化为map
	 * 
	 * @param param request.getQueryString()
	 * @return
	 */
	public static Map<String, Object> paramsStr2Map(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);

		if (param == null || param.length() == 0) {
			return map;
		}

		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * map转化为请求参数的字符串形式(如name=husen&pwd=123)
	 * 
	 * @param map
	 * @return
	 */
	public static String map2ParamsStr(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}

		int length = sb.length() - 1;
		char andChar = '&';
		if(andChar == sb.charAt(length-1)) {
			sb.deleteCharAt(length - 1);
		}
		
		return sb.toString();
	}

	/**
	 * 请求参数转化为map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> params2Map(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>(100);

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					map.put(paramName, paramValue);
				}
			}
		}
		
		return map;
	}
}