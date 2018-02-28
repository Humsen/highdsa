package pers.husen.highdsa.common.utility;

/**
 * @Desc 获取指定长度的验证码
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午12:11:20
 * 
 * @Version 1.0.1
 */
public class RandomCode {
	public static int producedRandomCode(double length) {
		int randomCode = (int) (Math.random() * Math.pow(10, length));
		// 进制系统，这里是10进制
		int decimalSystem = 10;

		while (randomCode < Math.pow(decimalSystem, length - 1) || randomCode >= Math.pow(decimalSystem, length)) {
			randomCode = (int) (Math.random() * Math.pow(10, length));
		}

		return randomCode;
	}
}