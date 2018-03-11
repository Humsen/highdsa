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
	/**
	 * 根据Id查询
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo selectById(Integer userId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<UserInfo> selectAll();

	/**
	 * 插入用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer insertUserInfo(UserInfo userInfo);

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer updateUserInfo(UserInfo userInfo);

	/**
	 * 根据Id删除用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public Integer deleteUserInfo(Integer userId);
}