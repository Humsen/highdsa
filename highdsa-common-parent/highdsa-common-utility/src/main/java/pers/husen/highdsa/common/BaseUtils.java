package pers.husen.highdsa.common;

/**
 * @Desc 基本工具类,可被继承
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月1日 下午10:13:04
 * 
 * @Version 1.0.0
 */
public class BaseUtils {
	/**
	 * 判断字节数组是否为空
	 * 
	 * @param data
	 * @return
	 */
	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}