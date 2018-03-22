package pers.husen.highdsa.common.entity.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Desc RESTful 简单json
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月21日 下午3:52:46
 * 
 * @Version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class SimpleJson implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 不使用封装，默认为false */
	private Boolean success;
	private String message;

	@Override
	public String toString() {
		return "SimpleJson [success=" + success + ", message=" + message + "]";
	}

	public SimpleJson() {
		super();
	}

	/**
	 * 只有结果
	 * 
	 * @param success
	 */
	public SimpleJson(Boolean success) {
		super();
		this.success = success;
	}

	/**
	 * 结果带说明信息
	 * 
	 * @param success
	 * @param message
	 */
	public SimpleJson(Boolean success, String message) {
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
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}