package pers.husen.highdsa.service.cas.server.auth;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import pers.husen.highdsa.common.encrypt.Md5Encrypt;

/**
 * @Desc 登录验证类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月8日 下午10:33:36
 * 
 * @Version 1.0.0
 */
public class UsernamePasswordAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
	private final static Logger logger = LogManager.getLogger(UsernamePasswordAuthenticationHandler.class.getName());

	/**
	 * @param name
	 * @param servicesManager
	 * @param principalFactory
	 * @param order
	 */
	public UsernamePasswordAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
		super(name, servicesManager, principalFactory, order);
	}

	/**
	 * 用于判断用户的Credential(换而言之，就是登录信息)，是否是俺能处理的 就是有可能是，子站点的登录信息中不止有用户名密码等信息，还有部门信息的情况
	 */
	@Override
	public boolean supports(Credential credential) {
		// 判断传递过来的Credential 是否是自己能处理的类型
		return credential instanceof UsernamePasswordCredential;
	}

	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential, String originalPassword) throws GeneralSecurityException, PreventedException {
		DriverManagerDataSource d = new DriverManagerDataSource();
		d.setDriverClassName("com.mysql.jdbc.Driver");
		d.setUrl("jdbc:mysql://rm-wz9lp2i9322g0n06zvo.mysql.rds.aliyuncs.com:3306/highdsa?useSSL=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
		d.setUsername("webuser");
		d.setPassword("123456");
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(d);

		String username = transformedCredential.getUsername();
		String currentPwd = transformedCredential.getPassword();
		logger.info("用户输入的密码：" + currentPwd);

		// 查询数据库加密的的密码
		Map<String, Object> selectUser = template.queryForMap("SELECT user_name, user_password, user_pwd_salt FROM sys_user WHERE user_name = ?", transformedCredential.getUsername());

		if (selectUser == null) {
			throw new FailedLoginException("没有该用户");
		}

		// 返回多属性（暂时不知道怎么用，没研究）
		Map<String, Object> map = new HashMap<>(5);
		map.put("email", "XXXXX@qq.com");

		// md5加密两次
		String encryptedSelectPwd = Md5Encrypt.getMD5Code(currentPwd, selectUser.get("user_name").toString() + selectUser.get("user_pwd_salt").toString(), 2);
		logger.info("md5加密两次后的密码：{}", encryptedSelectPwd);

		if (encryptedSelectPwd.equals(selectUser.get("user_password").toString())) {
			logger.info("密码对比成功");

			return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(username, map), null);
		}

		throw new FailedLoginException("Sorry, login attemp failed.");
	}
}