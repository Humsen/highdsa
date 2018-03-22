package pers.husen.highdsa.common.exception;

/**
 * @Desc highdsa系统异常基类
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 下午11:58:54
 * 
 * @Version 1.0.1
 */
public class HighdsaException extends RuntimeException {
	private static final long serialVersionUID = 7462624691843766045L;
	
	/** 错误编码 */
	private int errorCode;

	public HighdsaException() {
		super();
	}

	/* ----------------------- added constructors ---------------------------*/
	
	/**
	 * @param errorCode
	 * @param cause
	 */
	public HighdsaException(int errorCode,Throwable cause) {
		super(cause);
	}
	
	/**
	 * @param errorCode
	 * @param message
	 */
	public HighdsaException(int errorCode,String message) {
		super(message);
	}
	
	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public HighdsaException(int errorCode,String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HighdsaException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/* ----------------------- origin constructors ---------------------------*/
	
	/**
	 * @param cause
	 */
	public HighdsaException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 */
	public HighdsaException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public HighdsaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HighdsaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/* ----------------------- setter/getter ---------------------------*/
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}