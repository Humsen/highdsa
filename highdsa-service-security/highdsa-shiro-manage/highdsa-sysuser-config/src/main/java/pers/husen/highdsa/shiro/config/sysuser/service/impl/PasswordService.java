package pers.husen.highdsa.shiro.config.sysuser.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysUser;

/**
 * @Desc 密码服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月13日 下午3:07:13
 * 
 * @Version 1.0.0
 */
@Service
public class PasswordService {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(SysUser user) {

		user.setUserPwdSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getUserPassword(), ByteSource.Util.bytes(user.getUserName() + user.getUserPwdSalt()), hashIterations).toHex();
		user.setUserPassword(newPassword);
	}
}