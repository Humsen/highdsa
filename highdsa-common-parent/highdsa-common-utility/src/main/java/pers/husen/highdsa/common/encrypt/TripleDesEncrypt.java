package pers.husen.highdsa.common.encrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * @Desc 3DES加密
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月17日 下午5:45:02
 * 
 * @Version 1.0.1
 */
public class TripleDesEncrypt {
	/** 加密算法 */
	private static final String TRIPLE_DES_ALGORITHM = "DESede";
	/** 算法/模式/补码方式 */
	private static final String TRIPLE_DES_TRANSFORMATION = "DESede/ECB/PKCS5Padding";

	/** 24位默认密钥 */
	public static final String DEFAULT_TRIPLE_DES_KEY = "IwL1EObaoKTjhTN8wkuY+tXA";

	/**
	 * 3DES加密,使用默认密钥
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(final String dataSource) throws Exception {
		return encrypt(dataSource, DEFAULT_TRIPLE_DES_KEY);
	}

	/**
	 * 3DESECB加密,key必须是长度大于等于 3*8 = 24 位
	 * 
	 * @param dataSource
	 *            数据源
	 * @param secretKey
	 *            密钥,长度必须是8的倍数
	 * @return 返回加密后的数据 Base64编码
	 * @throws Exception
	 */
	public static String encrypt(final String dataSource, final String secretKey) throws Exception {
		final DESedeKeySpec dks = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
		final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(TRIPLE_DES_ALGORITHM);
		final SecretKey securekey = keyFactory.generateSecret(dks);

		final Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		final byte[] b = cipher.doFinal(dataSource.getBytes());

		String res = Base64.getEncoder().encodeToString(b);

		return res;

	}

	/**
	 * 3DES解密,使用默认密钥
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(final String dataSource) throws Exception {
		return decrypt(dataSource, DEFAULT_TRIPLE_DES_KEY);
	}

	/**
	 * 3DESECB解密,key必须是长度大于等于 3*8 = 24 位
	 * 
	 * @param dataSource
	 *            加密之后的数据
	 * @param secretKey
	 *            密钥,长度必须是8的倍数
	 * @return 明码
	 * @throws Exception
	 */
	public static String decrypt(final String dataSource, final String secretKey) throws Exception {
		// --通过base64,将字符串转成byte数组
		// final byte[] bytesrc = Base64.getDecoder().decode(src);
		final byte[] bytesrc = Base64.getMimeDecoder().decode(dataSource);
		// --解密的key
		final DESedeKeySpec dks = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
		final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(TRIPLE_DES_ALGORITHM);
		final SecretKey securekey = keyFactory.generateSecret(dks);

		// --Chipher对象解密
		final Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		final byte[] retByte = cipher.doFinal(bytesrc);

		return new String(retByte);
	}
}