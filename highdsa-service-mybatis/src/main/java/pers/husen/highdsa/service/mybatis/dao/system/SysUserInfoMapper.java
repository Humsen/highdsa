package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;
import pers.husen.highdsa.common.entity.po.shiro.SysUserInfo;

/**
 * @Desc 系统 用户信息 SQL映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月29日 下午3:19:40
 * 
 * @Version 1.0.0
 */
public interface SysUserInfoMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByPrimaryKey(Long userId);

	/**
	 * 插入新用户信息
	 * 
	 * @param record
	 * @return
	 */
	int insert(SysUserInfo record);

	/**
	 * 根据主键查找
	 * 
	 * @param userId
	 * @return
	 */
	SysUserInfo selectByPrimaryKey(Long userId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<SysUserInfo> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(SysUserInfo record);
}