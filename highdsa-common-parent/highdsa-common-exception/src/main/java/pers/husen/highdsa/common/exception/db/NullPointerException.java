package pers.husen.highdsa.common.exception.db;

import pers.husen.highdsa.common.exception.HighdsaException;

/**
 * @Desc 数据库操作异常
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月27日 上午10:54:14
 * 
 * @Version 1.0.1
 */
public class NullPointerException extends HighdsaException {
	private static final long serialVersionUID = 8529456499219406538L;

	public NullPointerException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NullPointerException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public NullPointerException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public NullPointerException(int errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public NullPointerException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NullPointerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NullPointerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NullPointerException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NullPointerException(Throwable cause) {
		super(cause);
	}
}