package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.husen.highdsa.common.entity.po.customer.CustUserRole;

/**
 * @Desc 客户用户-角色映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午11:54:23
 * 
 * @Version 1.0.1
 */
public interface CustUserRoleMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

	/**
	 * 插入新记录
	 * 
	 * @param record
	 * @return
	 */
	int insert(CustUserRole record);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustUserRole> selectAll();
}