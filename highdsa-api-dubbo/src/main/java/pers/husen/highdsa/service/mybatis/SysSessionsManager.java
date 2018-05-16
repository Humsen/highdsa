package pers.husen.highdsa.service.mybatis;

import java.util.List;

import pers.husen.highdsa.common.entity.po.system.SysSessions;

/**
 * @Desc 系统会话持久化dubbo接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 上午9:47:01
 * 
 * @Version 1.0.1
 */
public interface SysSessionsManager {

	/**
	 * 插入新记录
	 * 
	 * @param sysSessions
	 * @return
	 */
	int createSession(SysSessions sysSessions);

	/**
	 * 根据主键查找
	 * 
	 * @param sessionId
	 * @return
	 */
	SysSessions findBySessionId(String sessionId);

	/**
	 * 分页查找
	 * 
	 * @param start
	 *            起始记录id
	 * @param size
	 *            每页大小
	 * @return
	 */
	List<SysSessions> findListByPage(int start, int size);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<SysSessions> findAll();

	/**
	 * 根据主键更新
	 * 
	 * @param sysSessions
	 * @return
	 */
	int modifyBySessionId(SysSessions sysSessions);

	/**
	 * 根据主键删除
	 * 
	 * @param sessionId
	 * @return
	 */
	int deleteBySessionId(String sessionId);
}