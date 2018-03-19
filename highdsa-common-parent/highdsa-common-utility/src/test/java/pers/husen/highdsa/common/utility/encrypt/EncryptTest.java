package pers.husen.highdsa.common.utility.encrypt;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pers.husen.highdsa.common.encrypt.AesEncrypt;
import pers.husen.highdsa.common.encrypt.DesEncrypt;
import pers.husen.highdsa.common.encrypt.Md5Encrypt;
import pers.husen.highdsa.common.encrypt.TripleDesEncrypt;

/**
 * @Desc 测试几种加密方法
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月17日 下午7:36:46
 * 
 * @Version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncryptTest {
	@Test
	public void test01AESEncrypt() {
		String dataSource = "husen";
		String aesKey = "1234567812345678";
		String aesReply = null;
		String aesOrigin = null;

		try {
			aesReply = AesEncrypt.encrypt(dataSource, aesKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("aesKey: " + aesKey + ", reply: " + aesReply);

		try {
			aesOrigin = AesEncrypt.decrypt(aesReply, aesKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(dataSource, aesOrigin);
	}

	@Test
	public void test02AesEncryptDefault() {
		String dataSource = "husen";
		String aesReply = null;
		String aesOrigin = null;

		try {
			aesReply = AesEncrypt.encrypt(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("aesKeyDefault: " + AesEncrypt.DEFAULT_AES_KEY + ", reply: " + aesReply);

		try {
			aesOrigin = AesEncrypt.decrypt(aesReply);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(aesOrigin, dataSource);
	}

	@Test
	public void test03DesEncryptDefault() {
		String dataSource = "husen";
		String desReply = null;
		String desOrigin = null;

		try {
			desReply = DesEncrypt.encrypt(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("desKeyDefault: " + DesEncrypt.DEFAULT_DES_KEY + ", reply: " + desReply);

		try {
			desOrigin = DesEncrypt.decrypt(desReply);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(desOrigin, dataSource);
	}

	@Test
	public void test04TripleDesEncryptDefault() {
		String dataSource = "husen";
		String desReply = null;
		String desOrigin = null;

		try {
			desReply = TripleDesEncrypt.encrypt(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("3desKeyDefault: " + TripleDesEncrypt.DEFAULT_TRIPLE_DES_KEY + ", reply: " + desReply);

		try {
			desOrigin = TripleDesEncrypt.decrypt(desReply);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(desOrigin, dataSource);
	}

	@Test
	public void test05Md5Encrypt() {
		String dataSource = "husen";

		try {
			System.out.println("md5 key: " + dataSource + ", reply: " + Md5Encrypt.getMD5Code(dataSource));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}