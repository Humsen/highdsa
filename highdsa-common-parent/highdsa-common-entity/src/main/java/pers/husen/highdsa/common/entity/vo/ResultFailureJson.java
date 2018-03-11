package pers.husen.highdsa.common.entity.vo;

import java.util.Map;

import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 存放Json结果的vO
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 下午2:25:15
 * 
 * @Version 1.0.2
 */
public class ResultFailureJson implements ResponseResult {
	private Map<String, Object> failure;

	/**
	 * @return the failure
	 */
	public Map<String, Object> getFailure() {
		return failure;
	}

	/**
	 * @param failure the failure to set
	 */
	public void setFailure(Map<String, Object> failure) {
		this.failure = failure;
	}

	@Override
	public void setResultMap(Map<String, Object> resultMap) {
		failure = resultMap;
	}
}