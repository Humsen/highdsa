package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustUserInfo;

/**
 * @Desc 客户信息映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午11:51:33
 * 
 * @Version 1.0.3
 */
public interface CustUserInfoMapper {
	/**
	 * 根据主键更新
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByPrimaryKey(Long userId);

	/**
	 * 插入新记录
	 * 
	 * @param custUserInfo
	 * @return
	 */
	int insert(CustUserInfo custUserInfo);

	/**
	 * 根据主键查找
	 * 
	 * @param userId
	 * @return
	 */
	CustUserInfo selectByPrimaryKey(Long userId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustUserInfo> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param custUserInfo
	 * @return
	 */
	int updateByPrimaryKey(CustUserInfo custUserInfo);

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 */
	CustUserInfo selectUserInfoByUserName(String userName);
}