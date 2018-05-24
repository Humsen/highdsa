package pers.husen.highdsa.common.transform;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pers.husen.highdsa.common.constant.Encode;

/**
 * @Desc json和request请求参数直接相互转化
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午3:43:05
 * 
 * @Version 1.0.2
 */
public class ConvertRequestParams {
	/**
	 * 请求参数的字符串形式(如name=husen&pwd=123)转化为map
	 * 
	 * @param param
	 *            request.getQueryString()
	 * @return
	 */
	public static Map<String, String> paramsStr2Map(String param) {
		Map<String, String> map = new HashMap<String, String>(10);

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

		int length = sb.length();
		char andChar = '&';
		if (andChar == sb.charAt(length - 1)) {
			sb.deleteCharAt(length - 1);
		}

		return sb.toString();
	}

	/**
	 * map转化为请求参数的字符串形式(如name=husen&pwd=123),并对键值对进行URL编码
	 * 
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String map2ParamsEncodeStr(Map<String, String> map) throws UnsupportedEncodingException {
		if (map == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		String key = null, value = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			key = URLEncoder.encode(entry.getKey(), Encode.DEFAULT_ENCODE);
			value = URLEncoder.encode(entry.getValue(), Encode.DEFAULT_ENCODE);
			sb.append(key + "=" + value);
			sb.append("&");
		}

		int length = sb.length();
		char andChar = '&';
		if (andChar == sb.charAt(length - 1)) {
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