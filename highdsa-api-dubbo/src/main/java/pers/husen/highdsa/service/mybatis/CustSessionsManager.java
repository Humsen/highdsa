package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.customer.CustSessions;

/**
 * @Desc 客户会话持久化dubbo接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:32:39
 * 
 * @Version 1.0.1
 */
public interface CustSessionsManager {
	/**
	 * 插入新记录
	 * 
	 * @param custSessions
	 * @return
	 */
	int createSession(CustSessions custSessions);

	/**
	 * 根据主键查找
	 * 
	 * @param sessionId
	 * @return
	 */
	CustSessions findBySessionId(String sessionId);

	/**
	 * 分页查找
	 * 
	 * @param start
	 *            起始记录id
	 * @param size
	 *            每页大小
	 * @return
	 */
	List<CustSessions> findListByPage(int start, int size);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CustSessions> findAll();

	/**
	 * 根据主键更新
	 * 
	 * @param custSessions
	 * @return
	 */
	int modifyBySessionId(CustSessions custSessions);

	/**
	 * 根据主键删除
	 * 
	 * @param sessionId
	 * @return
	 */
	int deleteBySessionId(String sessionId);
}