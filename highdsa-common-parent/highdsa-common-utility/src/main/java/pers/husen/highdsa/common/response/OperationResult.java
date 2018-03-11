package pers.husen.highdsa.common.response;

import java.util.Map;

import pers.husen.highdsa.common.entity.vo.ResultSuccessJson;
import pers.husen.highdsa.web.response.ResponseResult;

/**
 * @Desc 操作成功返回json的模板
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 下午3:00:21
 * 
 * @Version 1.0.0
 */
public class OperationResult {
	private ResponseResult responseResultVo;
	private Map<String, Object> resultJson;

	/**
	 * 默认初始化
	 */
	public OperationResult(ResponseResult responseResult) {
		responseResultVo = responseResult;
	}

	/**
	 * 初始化Vo,并给MAP赋值
	 */
	public OperationResult(ResponseResult responseResult, Map<Object, Object> successJson) {
		responseResultVo = responseResult;

		responseResultVo = new ResultSuccessJson();
	}

	/**
	 * 获取最终结果
	 * 
	 * @return
	 */
	public ResponseResult getResultJsonVo() {
		// 调用结果的时候再设置进去
		responseResultVo.setResultMap(resultJson);
		return responseResultVo;
	}

	/**
	 * 设置最终结果
	 * 
	 * @param responseResultVo
	 */
	public void setResultJsonVo(ResponseResult responseResultVo) {
		this.responseResultVo = responseResultVo;
	}

	/**
	 * 获取MAP
	 * 
	 * @return
	 */
	public Map<String, Object> getResultJson() {
		return resultJson;
	}

	/**
	 * 设置MAP
	 * 
	 * @param resultJson
	 */
	public void setResultJson(Map<String, Object> resultJson) {
		this.resultJson = resultJson;
	}
	
	/**
	 * 追加结果到当前MAP
	 * @param addJson
	 */
	public void addResultJson(Map<String, Object> addJson) {
		this.resultJson.putAll(addJson);
	}
}