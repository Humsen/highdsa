package pers.husen.highdsa.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Desc 返回程序调用的堆栈轨迹
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月3日 下午4:59:14
 * 
 * @Version 1.0.0
 */
public class StackTrace2Str {
	/**
	 * 返回 e.printStackTrace()的内容
	 * 
	 * @param e
	 * @return
	 */
	public static String exceptionStackTrace2Str(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		// 将出错的栈信息输出到printWriter中
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();

		return sw.toString();
	}

	/**
	 * 返回 自定义错误信息 + e.printStackTrace()的内容
	 * 
	 * @param errorMsg
	 * @param e
	 * @return
	 */
	public static String exceptionStackTrace2Str(String errorMsg, Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		// 将出错的栈信息输出到printWriter中
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();

		return errorMsg + "\n" + sw.toString();
	}
}