package pers.husen.highdsa.common.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Desc MD5加密工具, 支持加盐和定义加密次数
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午12:09:52
 * 
 * @Version 1.0.3
 */
public class Md5Encrypt {
	/**
	 * 获取md5实例
	 * 
	 * @return
	 */
	public static MessageDigest getMD5Instance() {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return messageDigest;
	}

	/**
	 * MD5加密, 返回结果为小写字母+数字
	 * 
	 * @param dataSource
	 *            待加密的数据
	 * @return
	 * @throws Exception
	 */
	public static String getMD5Code(String dataSource) {
		MessageDigest md = getMD5Instance();

		byte[] byteArray = md.digest(dataSource.getBytes());
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String[] stringDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

			int iRet = byteArray[i];
			if (iRet < 0) {
				iRet += 256;
			}
			int iD1 = iRet / 16;
			int iD2 = iRet % 16;

			sBuffer.append(stringDigits[iD1] + stringDigits[iD2]);
		}

		return sBuffer.toString();
	}

	/**
	 * MD5加密, 返回结果为大写字母+数字
	 * 
	 * @param dataSource
	 *            待加密的数据
	 * @return
	 */
	public static String getMD5CodeUpperCase(String dataSource) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = getMD5Instance();

		try {
			byte[] btInput = dataSource.getBytes();

			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * MD5加盐加密, 返回结果为小写字母+数字
	 * 
	 * @param dataSource
	 *            待加密的数据
	 * @param salt
	 *            盐
	 * @return
	 */
	public static String getMD5Code(String dataSource, String salt) {
		StringBuffer stingBuffer = new StringBuffer();
		MessageDigest digest = getMD5Instance();
		digest.update(salt.getBytes());
		byte[] byteArray = digest.digest(dataSource.getBytes());

		for (byte b : byteArray) {
			int i = b & 0xff;
			String hexString = Integer.toHexString(i);
			if (hexString.length() < 2) {
				hexString = "0" + hexString;
			}
			stingBuffer.append(hexString);
		}

		return stingBuffer.toString();
	}

	/**
	 * MD5加盐多次加密, 返回结果为小写字母+数字
	 * 
	 * @param dataSource
	 *            待加密的数据
	 * @param salt
	 *            盐
	 * @param iterations
	 *            加密次数
	 * @return
	 */
	public static String getMD5Code(String dataSource, String salt, int iterations) {
		MessageDigest digest = getMD5Instance();

		if (salt != null) {
			digest.reset();
			digest.update(salt.getBytes());
		}

		byte[] byteArray = digest.digest(dataSource.getBytes());

		for (int i = 0; i < iterations - 1; i++) {
			digest.reset();
			byteArray = digest.digest(byteArray);
		}

		return new BigInteger(1, byteArray).toString(16);
	}
}