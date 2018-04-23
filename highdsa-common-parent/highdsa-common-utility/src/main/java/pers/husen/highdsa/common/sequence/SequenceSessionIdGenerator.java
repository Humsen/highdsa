package pers.husen.highdsa.common.sequence;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import pers.husen.highdsa.common.sequence.SequenceManager;

/**
 * @Desc 生成分布式id作为会话id
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月20日 上午9:34:32
 * 
 * @Version 1.0.1
 */
public class SequenceSessionIdGenerator implements SessionIdGenerator {
	@Override
	public Serializable generateId(Session session) {
		Long userId = SequenceManager.getNextId();

		return String.valueOf(userId);
	}
}