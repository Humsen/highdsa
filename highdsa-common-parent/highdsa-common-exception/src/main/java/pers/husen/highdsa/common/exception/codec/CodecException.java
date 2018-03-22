package pers.husen.highdsa.common.exception.codec;

import pers.husen.highdsa.common.exception.HighdsaException;

/**
 * @Desc 编码解码异常
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 下午4:21:35
 * 
 * @Version 1.0.1
 */
public class CodecException extends HighdsaException{
	private static final long serialVersionUID = 6017110757917075553L;

	public CodecException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CodecException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public CodecException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public CodecException(int errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public CodecException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CodecException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CodecException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CodecException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CodecException(Throwable cause) {
		super(cause);
	}
}