package pers.husen.highdsa.common.utility.encrypt;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import pers.husen.highdsa.common.encrypt.Md5Encrypt;

/**
 * @Desc 测试shiro多次加密
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月11日 下午9:10:23
 * 
 * @Version 1.0.0
 */
public class ShiroMutiEncrypt {
	public static void main(String[] args) {
		//testResultIsSame();
		
		generatorMd5TwicePwd();
	}

	public static void generatorMd5TwicePwd() {
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		String algorithmName = "md5";
		final int hashIterations = 2;

		String userName = "user";
		System.out.println("用户名：" + userName);

		String password = "highdsa";
		System.out.println("密码：" + password);

		String pwdSalt = randomNumberGenerator.nextBytes().toHex();
		System.out.println("盐值：" + pwdSalt);
		System.out.println("盐值长度：" + pwdSalt.length());

		String shiroPwd = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(userName + pwdSalt), hashIterations).toHex();
		System.out.println("\n加密后的密码：" + shiroPwd);

		String custPwd = Md5Encrypt.getMD5Code(password, userName + pwdSalt, hashIterations);
		System.out.println("\n自定义加密加密后的密码：" + custPwd);
		
		System.out.println("\n是否相等：" + (shiroPwd.equals(custPwd)));
		System.out.println("加密密码长度："+custPwd.length());

	}

	public static void testResultIsSame() {
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		String algorithmName = "md5";
		final int hashIterations = 2;

		String userName = "husen";
		String password = "wqrwqt325325SAF..";
		String pwdSalt = randomNumberGenerator.nextBytes().toHex();
		System.out.println("盐值：" + pwdSalt.length());

		String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(userName + pwdSalt), hashIterations).toHex();
		System.out.println("加密后的密码：" + newPassword);

		String custPwd = Md5Encrypt.getMD5Code(password, userName + pwdSalt, hashIterations);
		System.out.println("自定义加密加密后的密码：" + custPwd);
		System.out.println("是否相等：" + (newPassword.equals(custPwd)));

		String oncePwd = Md5Encrypt.getMD5Code(password, userName + pwdSalt);
		System.out.println("加密一次：" + oncePwd);
		String twicePwd = Md5Encrypt.getMD5Code(oncePwd, userName + pwdSalt);
		System.out.println("加密两次：" + twicePwd);
	}
}