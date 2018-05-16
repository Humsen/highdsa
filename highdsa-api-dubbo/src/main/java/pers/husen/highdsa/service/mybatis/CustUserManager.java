package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustNavigation;
import pers.husen.highdsa.common.entity.po.customer.CustUser;

/**
 * @Desc 客户管理
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月24日 上午10:16:04
 * 
 * @Version 1.0.7
 */
public interface CustUserManager {
	/**
	 * 创建用户
	 * 
	 * @param custUser
	 * @return
	 */
	public String createUser(CustUser custUser);

	/**
	 * 增加用户和用户-角色关联
	 * 
	 * @param custUser
	 * @param roleIds
	 * @return
	 */
	CustUser addUser(CustUser custUser, Long... roleIds);

	/**
	 * 根据用户id查找用户
	 * 
	 * @param userId
	 * @return
	 */
	CustUser findUserByUserId(Long userId);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	CustUser findUserByUserName(String userName);

	/**
	 * 根据用户手机号查找用户
	 * 
	 * @param userPhone
	 * @return
	 */
	CustUser findUserByUserPhone(String userPhone);

	/**
	 * 根据用户邮箱查找用户
	 * 
	 * @param userEmail
	 * @return
	 */
	CustUser findUserByUserEmail(String userEmail);

	/**
	 * 根据用户名查找角色集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser findRolesByUserName(String userName);

	/**
	 * 根据用户手机号查找角色集合
	 * 
	 * @param userPhone
	 * @return
	 */
	CustUser findRolesByUserPhone(String userPhone);

	/**
	 * 根据用户邮箱查找角色集合
	 * 
	 * @param userEmail
	 * @return
	 */
	CustUser findRolesByUserEmail(String userEmail);

	/**
	 * 根据用户名查找权限集合
	 * 
	 * @param userName
	 * @return
	 */
	CustUser findPermissionsByUserName(String userName);

	/**
	 * 根据用户手机号查找权限集合
	 * 
	 * @param userPhone
	 * @return
	 */
	CustUser findPermissionsByUserPhone(String userPhone);

	/**
	 * 根据用户邮箱查找权限集合
	 * 
	 * @param userEmail
	 * @return
	 */
	CustUser findPermissionsByUserEmail(String userEmail);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<CustUser> findAllUsers();

	/**
	 * 根据用户名查找导航栏
	 * 
	 * @param userName
	 * @return
	 */
	List<CustNavigation> findNavigationBar(String userName);

	/**
	 * 根据userId更新
	 * 
	 * @param custUser
	 */
	public void modifyUserByUserId(CustUser custUser);

	/**
	 * 更新用户-角色关联
	 * 
	 * @param userId
	 * @param roleIds
	 */
	void modifyUserRoles(Long userId, Long... roleIds);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void modifyPassword(Long userId, String newPassword);

	/**
	 * 根据手机号更新密码
	 * 
	 * @param userPhone
	 * @param newPassword
	 */
	public void modifyPasswordByUserPhone(String userPhone, String newPassword);

	/**
	 * 根据id删除用户
	 * 
	 * @param userId
	 */
	void deleteUser(Long userId);

	/**
	 * 删除一组用户
	 * 
	 * @param userIds
	 */
	void deleteMoreUsers(Long... userIds);

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(Long userId, Long... roleIds);

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);
}