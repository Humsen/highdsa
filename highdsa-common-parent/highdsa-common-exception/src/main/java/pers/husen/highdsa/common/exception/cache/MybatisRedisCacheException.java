package pers.husen.highdsa.common.exception.cache;

import pers.husen.highdsa.common.exception.HighdsaException;

/**
 * @Desc mybatis redis缓存异常
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月1日 下午11:22:50
 * 
 * @Version 1.0.0
 */
public class MybatisRedisCacheException extends HighdsaException {

	private static final long serialVersionUID = 7613399559863282306L;

	public MybatisRedisCacheException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MybatisRedisCacheException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public MybatisRedisCacheException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public MybatisRedisCacheException(int errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public MybatisRedisCacheException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MybatisRedisCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MybatisRedisCacheException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MybatisRedisCacheException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MybatisRedisCacheException(Throwable cause) {
		super(cause);
	}
}