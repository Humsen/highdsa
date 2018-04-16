package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import pers.husen.highdsa.common.entity.po.customer.CustRole;

/**
 * @Desc 客户角色映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午11:48:51
 * 
 * @Version 1.0.1
 */
public interface CustRoleMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteByPrimaryKey(Long roleId);

	/**
	 * 插入新记录
	 * 
	 * @param custRole
	 * @return
	 */
	int insert(CustRole custRole);

	/**
	 * 根据主键查找
	 * 
	 * @param roleId
	 * @return
	 */
	CustRole selectByPrimaryKey(Long roleId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustRole> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param custRole
	 * @return
	 */
	int updateByPrimaryKey(CustRole custRole);
}