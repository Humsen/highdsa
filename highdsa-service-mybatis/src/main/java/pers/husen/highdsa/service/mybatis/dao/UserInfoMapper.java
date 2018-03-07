package pers.husen.highdsa.service.mybatis.dao;

import java.util.List;

import pers.husen.highdsa.common.entity.po.UserInfo;

/**
 * @Desc 数据库操作接口 dao
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 上午10:42:06
 * 
 * @Version 1.0.0
 */
public interface UserInfoMapper {
	public UserInfo selectById(Integer userId);

	public List<UserInfo> selectAll();

	public Integer insertUserInfo(UserInfo userInfo);

	public Integer updateUserInfo(UserInfo userInfo);

	public Integer deleteUserInfo(Integer userId);
}