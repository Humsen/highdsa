package pers.husen.highdsa.common.utility.encrypt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pers.husen.highdsa.common.encrypt.AesEncrypt;

/**
 * @Desc TODO
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月17日 下午7:36:46
 * 
 * @Version 1.0.0
 */
public class EncryptTest {
	String aesKey = "1234567812345678";
	String aesReply = "IwL1EObaoKTjhTN8zTpLfg==";

	@Test
	public void testAESEncrypt() {
		String result = null;

		try {
			result = AesEncrypt.encrypt("husen", aesKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
		assertEquals(result, aesReply);
	}

	@Test
	public void testAESDecrypt() {
		String result = null;

		try {
			result = AesEncrypt.decrypt(aesReply, aesKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
		assertEquals(result, "husen");
	}
	
	@Test
	public void testAesEncryptDefault() {
		String result = null;

		try {
			result = AesEncrypt.encrypt("husen");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
		assertEquals(result, aesReply);
	}
}
