package pers.husen.highdsa.web.shiro.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import pers.husen.highdsa.common.entity.po.shiro.SysUser;

/**
 * @Desc 密码加密助手
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 上午9:31:53
 * 
 * @Version 1.0.1
 */
public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String algorithmName = "md5";
	private final int hashIterations = 2;

	public void encryptPassword(SysUser user) {

		user.setUserPwdSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getUserPassword(), ByteSource.Util.bytes(user.getUserName() + user.getUserPwdSalt()), hashIterations).toHex();

		user.setUserPassword(newPassword);
	}
}