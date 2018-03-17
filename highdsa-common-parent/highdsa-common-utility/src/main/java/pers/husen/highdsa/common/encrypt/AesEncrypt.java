package pers.husen.highdsa.common.encrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc AES加密
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月17日 下午7:35:14
 * 
 * @Version 1.0.0
 */
public class AesEncrypt {
	private static final Logger logger = LogManager.getLogger(AesEncrypt.class.getName());
	
	public static final String aesKey = "IwL1EObaoKTjhTN8";

	/**
	 * AES加密,使用默认密钥
	 * 
	 * @param sSrc
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc) throws Exception {
		return encrypt(sSrc, aesKey);
	}

	/**
	 * AES加密
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		return Base64.getEncoder().encodeToString(encrypted);
	}

	/**
	 * AES解密,使用默认密钥
	 * 
	 * @param sSrc
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sSrc) throws Exception {
		return decrypt(sSrc, aesKey);
	}

	/**
	 * AES解密
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			// 先用base64转码
			byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;
			} catch (Exception e) {
				logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
				
				return null;
			}
		} catch (Exception ex) {
			logger.info(StackTrace2Str.exceptionStackTrace2Str(ex));
			
			return null;
		}
	}
}