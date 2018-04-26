package pers.husen.highdsa.common.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc DES加密工具
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月19日 上午9:15:27
 * 
 * @Version 1.0.1
 */
public class DesEncrypt {
	private static final Logger logger = LogManager.getLogger(DesEncrypt.class.getName());

	/** 加密算法 */
	private static final String DES_ALGORITHM = "DES";
	/** 8位默认密钥 */
	public static final String DEFAULT_DES_KEY = "IwL1EOba";

	/**
	 * DES加密,使用默认密钥
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(final String dataSource) throws Exception {
		return encrypt(dataSource, DEFAULT_DES_KEY);
	}

	/**
	 * DES加密
	 * 
	 * @param dataSource
	 *            原始字符串
	 * @param secretKey
	 *            加密密钥
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(final String dataSource, final String secretKey) throws Exception {

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(secretKey));

		} catch (NoSuchAlgorithmException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} catch (NoSuchPaddingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} catch (InvalidKeyException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		try {
			// 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must
			// be multiple of 8 when decrypting with padded cipher异常,
			// 不能把加密后的字节数组直接转换成字符串
			byte[] buf = cipher.doFinal(dataSource.getBytes());

			return Base64.getEncoder().encodeToString(buf);

		} catch (IllegalBlockSizeException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("IllegalBlockSizeException", e);
		} catch (BadPaddingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("BadPaddingException", e);
		}

	}

	/**
	 * DES解密,使用默认密钥
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(final String dataSource) throws Exception {
		return decrypt(dataSource, DEFAULT_DES_KEY);
	}

	/**
	 * DES解密
	 * 
	 * @param dataSource
	 *            密码字符串
	 * @param secretKey
	 *            解密密钥
	 * @return 原始字符串
	 * @throws Exception
	 */
	public static String decrypt(final String dataSource, final String secretKey) throws Exception {
		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance(DES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(secretKey));

		} catch (NoSuchAlgorithmException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("NoSuchAlgorithmException", e);
		} catch (NoSuchPaddingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("NoSuchPaddingException", e);
		} catch (InvalidKeyException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("InvalidKeyException", e);
		}

		try {

			byte[] buf = cipher.doFinal(Base64.getDecoder().decode(dataSource));

			return new String(buf);

		} catch (IllegalBlockSizeException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("IllegalBlockSizeException", e);
		} catch (BadPaddingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));

			throw new Exception("BadPaddingException", e);
		}
	}

	/**
	 * 获得秘密密钥
	 * 
	 * @param secretKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 */
	private static SecretKey generateKey(final String secretKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
		DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
		keyFactory.generateSecret(keySpec);
		return keyFactory.generateSecret(keySpec);
	}
}