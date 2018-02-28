package pers.husen.highdsa.common.exception.custom;

/**
 * @Desc 文件未找到
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午1:46:16
 * 
 * @Version 1.0.0
 */
public class FileNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	/** 错误编码 */
	private String errorCode;

	public FileNotFoundException() {
		super();
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param message
	 *            信息描述
	 */
	public FileNotFoundException(String message) {
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
	public FileNotFoundException(String errorCode, String message) {
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