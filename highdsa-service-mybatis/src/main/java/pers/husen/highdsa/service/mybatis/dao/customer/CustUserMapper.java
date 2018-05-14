package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustUser;

/**
 * @Desc 客户映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午11:52:51
 * 
 * @Version 1.0.4
 */
public interface CustUserMapper {
	/**
	 * 根据主键删除
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
	int insert(CustUser record);

	/**
	 * 根据id查找
	 * 
	 * @param userId
	 * @return
	 */
	CustUser selectUserByUserId(Long userId);

	/**
	 * 查找所有记录
	 * 
	 * @return
	 */
	List<CustUser> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param custUser
	 * @return
	 */
	int updateByUserId(CustUser custUser);

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 */
	CustUser selectUserByUserName(String userName);

	/**
	 * 根据用户邮箱查找用户信息
	 * 
	 * @param userPhone
	 * @return
	 */
	CustUser selectUserByUserPhone(String userPhone);

	/**
	 * 根据用户邮箱查找用户信息
	 * 
	 * @param userEmail
	 * @return
	 */
	CustUser selectUserByUserEmail(String userEmail);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser selectRolesByUserName(String userName);

	/**
	 * 根据用户手机号查找角色集合
	 * 
	 * @param userPhone
	 * @return
	 */
	CustUser selectRolesByUserPhone(String userPhone);

	/**
	 * 根据用户邮箱查找角色集合
	 * 
	 * @param userEmail
	 * @return
	 */
	CustUser selectRolesByUserEmail(String userEmail);

	/**
	 * 根据用户名查找权限集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser selectPermissionsByUserName(String userName);

	/**
	 * 根据用户手机号查找权限集合
	 * 
	 * @param userPhone
	 * @return
	 */
	CustUser selectPermissionsByUserPhone(String userPhone);

	/**
	 * 根据用户邮箱查找权限集合
	 * 
	 * @param userEmail
	 * @return
	 */
	CustUser selectPermissionsByUserEmail(String userEmail);
}