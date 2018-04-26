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
 * @Version 1.0.1
 */
public class AesEncrypt {
	private static final Logger logger = LogManager.getLogger(AesEncrypt.class.getName());

	/** 加密算法 */
	private static final String AES_ALGORITHM = "AES";
	/** 算法/模式/补码方式 */
	private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";

	/** 16位默认密钥 */
	public static final String DEFAULT_AES_KEY = "IwL1EObaoKTjhTN8";
	/** 密钥长度 */
	public static final int AES_KEY_LENGTH = 16;

	/**
	 * AES加密,使用默认密钥
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(final String dataSource) throws Exception {
		return encrypt(dataSource, DEFAULT_AES_KEY);
	}

	/**
	 * AES加密
	 * 
	 * @param dataSource
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(final String dataSource, final String secretKey) throws Exception {
		if (secretKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (secretKey.length() != AES_KEY_LENGTH) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = secretKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_ALGORITHM);
		Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(dataSource.getBytes("utf-8"));
		// 此处使用BASE64做转码功能,同时能起到2次加密的作用.
		return Base64.getEncoder().encodeToString(encrypted);
	}

	/**
	 * AES解密,使用默认密钥
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(final String dataSource) throws Exception {
		return decrypt(dataSource, DEFAULT_AES_KEY);
	}

	/**
	 * AES解密
	 * 
	 * @param dataSource
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(final String dataSource, final String secretKey) throws Exception {
		try {
			// 判断Key是否正确
			if (secretKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (secretKey.length() != AES_KEY_LENGTH) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = secretKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, AES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			// 先用base64转码
			byte[] encrypted1 = Base64.getDecoder().decode(dataSource);
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