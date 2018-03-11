package pers.husen.highdsa.common.entity.vo;

import java.util.Map;

import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 存放Json结果的vo
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 下午2:25:15
 * 
 * @Version 1.0.2
 */
public class ResultSuccessJson implements ResponseResult{
	private Map<String, Object> success;

	/**
	 * @return the success
	 */
	public Map<String, Object> getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(Map<String, Object> success) {
		this.success = success;
	}

	@Override
	public void setResultMap(Map<String, Object> resultMap) {
		success = resultMap;
	}
}