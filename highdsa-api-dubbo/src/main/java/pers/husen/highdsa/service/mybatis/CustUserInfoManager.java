package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;

/**
 * @Desc dubbo客户信息表操作接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:15:51
 * 
 * @Version 1.0.1
 */
public interface CustUserInfoManager {
	/**
	 * 插入新的用户信息
	 * 
	 * @param custUserInfo
	 * @return
	 */
	public Integer createUserInfo(CustUserInfo custUserInfo);

	/**
	 * 根据id查询
	 * 
	 * @param userId
	 * @return
	 */
	public CustUserInfo findUserInfoById(Long userId);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<CustUserInfo> findAll();

	/**
	 * 根据id更新用户信息
	 * 
	 * @param custUserInfo
	 * @return
	 */
	public Integer modifyUserInfo(CustUserInfo custUserInfo);

	/**
	 * 删除用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public Integer deleteUserInfo(Long userId);
}