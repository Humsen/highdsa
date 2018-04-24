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
 * @Version 1.0.2
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
	 * @param record
	 * @return
	 */
	int insert(CustUserInfo record);

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
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(CustUserInfo record);

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 */
	CustUserInfo selectUserInfoByUserName(String userName);
}