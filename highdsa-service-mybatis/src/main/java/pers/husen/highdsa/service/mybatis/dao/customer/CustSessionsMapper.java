package pers.husen.highdsa.service.mybatis.dao.customer;

import java.util.List;
import pers.husen.highdsa.common.entity.po.customer.CustSessions;

/**
 * @Desc 客户会话映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:28:30
 * 
 * @Version 1.0.0
 */
public interface CustSessionsMapper {
	/**
	 * 根据主键删除
	 * 
	 * @param sessionId
	 * @return
	 */
	int deleteByPrimaryKey(String sessionId);

	/**
	 * 插入新记录
	 * 
	 * @param sysSessions
	 * @return
	 */
	int insert(CustSessions custSessions);

	/**
	 * 根据主键查找
	 * 
	 * @param sessionId
	 * @return
	 */
	CustSessions selectByPrimaryKey(String sessionId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustSessions> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param sysSessions
	 * @return
	 */
	int updateByPrimaryKey(CustSessions custSessions);

	/**
	 * 分页查找
	 * 
	 * @param start
	 *            起始记录id
	 * @param size
	 *            每页大小
	 * @return
	 */
	List<CustSessions> selectListByPage(int start, int size);
}