package pers.husen.highdsa.service.mybatis;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import pers.husen.highdsa.common.entity.po.UserInfo;

/**
 * @Desc 测试
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午4:36:51
 * 
 * @Version 1.0.0
 */
public class UserInfoDbOperImplTest {
	UserInfoDbOperImpl userInfoDbOperImpl = new UserInfoDbOperImpl();
	
	@Test
	public void selectById() {
		UserInfo userInfo = userInfoDbOperImpl.selectById(1);
		System.out.println(userInfo);
	}

	@Test
	public void selectAll() {
		List<UserInfo> userInfos = userInfoDbOperImpl.selectAll();
		System.out.println(userInfos);
	}

	@Test
	public void insertUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("哈哈");
		userInfo.setUserPassword("qweasd");
		userInfo.setUserCreateDate(new Date());
		
		userInfoDbOperImpl.insertUserInfo(userInfo);
	}

	@Test
	public void updateUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("哈哈sadsa");
		userInfo.setUserPassword("123123");
		userInfo.setUserCreateDate(new Date());
		userInfo.setUserId(3);
		
		userInfoDbOperImpl.updateUserInfo(userInfo);
	}

	@Test
	public void deleteUserInfo() {
		userInfoDbOperImpl.deleteUserInfo(3);
	}
}