package pers.husen.highdsa.common.exception.json;

import pers.husen.highdsa.common.exception.HighdsaException;

/**
 * @Desc Jackson转换异常
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 上午11:17:48
 * 
 * @Version 1.0.1
 */
public class JacksonException extends HighdsaException {
	private static final long serialVersionUID = 5729574813014360321L;

	public JacksonException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public JacksonException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public JacksonException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public JacksonException(int errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public JacksonException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public JacksonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JacksonException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public JacksonException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JacksonException(Throwable cause) {
		super(cause);
	}
}