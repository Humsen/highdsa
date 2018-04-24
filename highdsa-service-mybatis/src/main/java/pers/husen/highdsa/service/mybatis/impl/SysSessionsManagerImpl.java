package pers.husen.highdsa.service.mybatis.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.system.SysSessions;
import pers.husen.highdsa.service.mybatis.SysSessionsManager;
import pers.husen.highdsa.service.mybatis.dao.system.SysSessionsMapper;

/**
 * @Desc 会话持久化实现类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月17日 上午9:49:51
 * 
 * @Version 1.0.1
 */
@Service("sysSessionsManager")
public class SysSessionsManagerImpl implements SysSessionsManager {
	private static final Logger logger = LogManager.getLogger(SysSessionsManagerImpl.class.getName());

	@Autowired
	private SysSessionsMapper sysSessionsMapper;

	@Override
	public int deleteBySessionId(String sessionId) {
		int reply = sysSessionsMapper.deleteByPrimaryKey(sessionId);
		// sqlSession.commit();

		logger.info("delete reply: {}", reply);

		return reply;
	}

	@Override
	public int createSession(SysSessions sysSessions) {
		int reply = sysSessionsMapper.insert(sysSessions);

		logger.info("insert reply: {}", reply);

		return reply;
	}

	@Override
	public SysSessions findBySessionId(String sessionId) {
		SysSessions sysSessions = sysSessionsMapper.selectByPrimaryKey(sessionId);

		logger.info("select by id[{}] reply: {}", sessionId, sysSessions);

		return sysSessions;
	}

	@Override
	public List<SysSessions> selectAll() {
		List<SysSessions> sysSessionsList = sysSessionsMapper.selectAll();

		logger.info("select all reply: {}", sysSessionsList);

		return sysSessionsList;
	}

	@Override
	public int updateBySessionId(SysSessions sysSessions) {
		int reply = sysSessionsMapper.updateByPrimaryKey(sysSessions);

		logger.info("update reply: {}", reply);

		return reply;
	}

	@Override
	public List<SysSessions> findListByPage(int start, int size) {
		List<SysSessions> sysSessionsList = sysSessionsMapper.selectListByPage(start, size);

		logger.info("select by page reply: {}", sysSessionsList);

		return sysSessionsList;
	}
}