package pers.husen.highdsa.common.utility.encrypt;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import pers.husen.highdsa.common.encrypt.Md5Encrypt;

/**
 * @Desc MD5加盐测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月28日 下午1:49:06
 * 
 * @Version 1.0.0
 */
public class Md5SaltTest {
	public static void main(String[] args) {
		String username = "husen";
		String password = "123123";
		String salt = "qsf24";

		String finalSalt = username + salt;
		System.out.println("finalSalt: " + finalSalt);

		System.out.println("---------------------------------");
		
		String simpleHash = new SimpleHash("MD5", password, finalSalt, 1).toString();
		System.out.println("SimpleHash: " + simpleHash);

		String md5Hash = new Md5Hash(password, finalSalt).toString();
		System.out.println("Md5Hash: " + md5Hash);

		String md5Reply2 = Md5Encrypt.getMD5Code(password, finalSalt);
		System.out.println("md5加盐加密: " + md5Reply2);
		
		System.out.println("---------------------------------");
		
		String md5Reply = Md5Encrypt.getMD5Code(password + finalSalt);
		System.out.println("md5加密: " + md5Reply);

		String md5Reply1 = Md5Encrypt.getMD5CodeUpperCase(password + finalSalt);
		System.out.println("md5加密(返回大写): " + md5Reply1);
		
		System.out.println("---------------------------------");
		
		String md5Hash2 = new Md5Hash(password, finalSalt, 2).toString();
		System.out.println("Md5Hash 加盐加密两次: " + md5Hash2);

		String md5Reply3 = Md5Encrypt.getMD5Code(password, finalSalt, 2);
		System.out.println("md5加盐加密两次: " + md5Reply3);
	}
}