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
	int deleteByPrimaryKey(Long userId);

	int insert(SysUserInfo record);

	SysUserInfo selectByPrimaryKey(Long userId);

	List<SysUserInfo> selectAll();

	int updateByPrimaryKey(SysUserInfo record);
}