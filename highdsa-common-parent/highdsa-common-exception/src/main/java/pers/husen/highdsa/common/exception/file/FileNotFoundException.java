package pers.husen.highdsa.common.exception.file;

import pers.husen.highdsa.common.exception.HighdsaException;

/**
 * @Desc 文件未找到
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午1:46:16
 * 
 * @Version 1.0.1
 */
public class FileNotFoundException extends HighdsaException {
	private static final long serialVersionUID = -7470844938891322046L;

	public FileNotFoundException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileNotFoundException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public FileNotFoundException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public FileNotFoundException(int errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public FileNotFoundException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileNotFoundException(Throwable cause) {
		super(cause);
	}
}