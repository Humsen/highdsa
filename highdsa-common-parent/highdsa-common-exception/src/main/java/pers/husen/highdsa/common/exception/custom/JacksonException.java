package pers.husen.highdsa.common.exception.custom;

/**
 * @Desc Jackson转换异常
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午11:17:48
 * 
 * @Version 1.0.0
 */
public class JacksonException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	/** 错误编码 */
	private String errorCode;

	public JacksonException() {
		super();
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param message
	 *            信息描述
	 */
	public JacksonException(String message) {
		super(message);
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param errorCode
	 *            错误编码
	 * @param message
	 *            信息描述
	 */
	public JacksonException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}