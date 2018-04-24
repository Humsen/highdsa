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
 * @Version 1.0.2
 */
public interface CustUserMapper {
	/**
	 * 根据主键删除
	 * @param userId
	 * @return
	 */
	int deleteByPrimaryKey(Long userId);

	/**
	 * 插入新记录
	 * @param record
	 * @return
	 */
	int insert(CustUser record);

	/**
	 * 根据主键查找
	 * @param userId
	 * @return
	 */
	CustUser selectByPrimaryKey(Long userId);

	/**
	 * 查找所有
	 * @return
	 */
	List<CustUser> selectAll();

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(CustUser record);
	
	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 */
	CustUser selectUserByUserName(String userName);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser selectRolesByUserName(String userName);

	/**
	 * 根据用户名查找权限集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser selectPermissionsByUserName(String userName);
}