package pers.husen.highdsa.common.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc MD5加密工具
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月28日 下午12:09:52
 * 
 * @Version 1.0.2
 */
public class Md5Encrypt {
	private static final Logger logger = LogManager.getLogger(Md5Encrypt.class.getName());

	/** 加密算法 */
	private static final String MD5_ALGORITHM = "MD5";
	/** 全局数组 */
	private static final String[] STRING_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
			"d", "e", "f" };

	/**
	 * MD5加密
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String getMD5Code(String dataSource) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance(MD5_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		// md.digest() 该函数返回值为存放哈希值结果的byte数组
		return byteToString(md.digest(dataSource.getBytes()));
	}

	/**
	 * 返回形式为数字跟字符串
	 * 
	 * @param bByte
	 * @return
	 */
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return STRING_DIGITS[iD1] + STRING_DIGITS[iD2];
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param bByte
	 * @return
	 */
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		
		return sBuffer.toString();
	}
}