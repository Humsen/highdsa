package pers.husen.highdsa.service.mybatis.dao.system;

import java.util.List;
import pers.husen.highdsa.common.entity.po.system.SysSessions;

/**
 * @Desc 系统会话映射
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 上午9:41:41
 * 
 * @Version 1.0.1
 */
public interface SysSessionsMapper {
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
	int insert(SysSessions sysSessions);

	/**
	 * 根据主键查找
	 * 
	 * @param sessionId
	 * @return
	 */
	SysSessions selectByPrimaryKey(String sessionId);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<SysSessions> selectAll();

	/**
	 * 根据主键更新
	 * 
	 * @param sysSessions
	 * @return
	 */
	int updateByPrimaryKey(SysSessions sysSessions);

	/**
	 * 分页查找
	 * 
	 * @param start
	 *            起始记录id
	 * @param size
	 *            每页大小
	 * @return
	 */
	List<SysSessions> selectListByPage(int start, int size);
}