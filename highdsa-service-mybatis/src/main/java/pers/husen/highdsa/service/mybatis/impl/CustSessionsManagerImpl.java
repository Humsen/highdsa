package pers.husen.highdsa.service.mybatis.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.husen.highdsa.common.entity.po.customer.CustSessions;
import pers.husen.highdsa.service.mybatis.CustSessionsManager;
import pers.husen.highdsa.service.mybatis.dao.customer.CustSessionsMapper;

/**
 * @Desc 客户会话持久化实现类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午12:34:11
 * 
 * @Version 1.0.0
 */
@Service("custSessionsManager")
public class CustSessionsManagerImpl implements CustSessionsManager {
	private static final Logger logger = LogManager.getLogger(CustSessionsManagerImpl.class.getName());

	@Autowired
	private CustSessionsMapper custSessionsMapper;

	@Override
	public int deleteBySessionId(String sessionId) {
		Integer reply = custSessionsMapper.deleteByPrimaryKey(sessionId);

		logger.info("delete reply: {}", reply);

		return reply;
	}

	@Override
	public int createSession(CustSessions custSessions) {
		Integer reply = custSessionsMapper.insert(custSessions);

		logger.info("insert reply: {}", reply);

		return reply;
	}

	@Override
	public CustSessions findBySessionId(String sessionId) {
		CustSessions custSessions = custSessionsMapper.selectByPrimaryKey(sessionId);

		logger.info("select by id[{}] reply: {}", sessionId, custSessions);

		return custSessions;
	}

	@Override
	public List<CustSessions> selectAll() {
		List<CustSessions> sysSessionsList = custSessionsMapper.selectAll();

		logger.info("select all reply: {}", sysSessionsList);

		return sysSessionsList;
	}

	@Override
	public int updateBySessionId(CustSessions sysSessions) {
		Integer reply = custSessionsMapper.updateByPrimaryKey(sysSessions);

		logger.info("update reply: {}", reply);

		return reply;
	}

	@Override
	public List<CustSessions> findListByPage(int start, int size) {
		List<CustSessions> sysSessionsList = custSessionsMapper.selectListByPage(start, size);

		logger.info("select by page reply: {}", sysSessionsList);

		return sysSessionsList;
	}
}