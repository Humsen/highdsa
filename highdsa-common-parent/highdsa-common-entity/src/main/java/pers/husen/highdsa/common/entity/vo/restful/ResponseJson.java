package pers.husen.highdsa.common.entity.vo.restful;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Desc RESTful 返回json
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月21日 下午3:52:46
 * 
 * @Version 1.0.1
 */
@JsonInclude(Include.NON_NULL)
public class ResponseJson implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 不使用封装,默认为false */
	private Boolean success;
	private Object message;

	@Override
	public String toString() {
		return "SimpleJson [success=" + success + ", message=" + message + "]";
	}

	public ResponseJson() {
		super();
	}

	/**
	 * 只有结果
	 * 
	 * @param success
	 */
	public ResponseJson(Boolean success) {
		super();
		this.success = success;
	}

	/**
	 * 结果带说明信息
	 * 
	 * @param success
	 * @param message
	 */
	public ResponseJson(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * @return the message
	 */
	public Object getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(Object message) {
		this.message = message;
	}
}