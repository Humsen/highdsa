package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.SysUserInfo;

/**
 * @Desc dubbo用户信息表操作接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 下午4:08:27
 * 
 * @Version 1.0.0
 */
public interface SysUserInfoManager {
	/**
	 * 根据id查询
	 * 
	 * @param userId
	 * @return
	 */
	public SysUserInfo selectById(Long userId);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<SysUserInfo> selectAll();

	/**
	 * 插入新的用户信息
	 * 
	 * @param sysUserInfo
	 * @return
	 */
	public Integer insertUserInfo(SysUserInfo sysUserInfo);

	/**
	 * 根据id更新用户信息
	 * 
	 * @param sysUserInfo
	 * @return
	 */
	public Integer updateUserInfo(SysUserInfo sysUserInfo);

	/**
	 * 删除用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public Integer deleteUserInfo(Long userId);
}