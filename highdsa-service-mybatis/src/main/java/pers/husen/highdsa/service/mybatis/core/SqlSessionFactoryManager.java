package pers.husen.highdsa.service.mybatis.core;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc 会话管理工具类
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月7日 上午10:40:48
 * 
 * @Version 1.0.0
 */
public class SqlSessionFactoryManager {
	private static final Logger logger = LogManager.getLogger(SqlSessionFactoryManager.class.getName());
	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * 得到会话工厂
	 * 
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (Exception e) {
				logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
			}
		}
		return sqlSessionFactory;
	}

	/**
	 * 打开新的会话,默认不自动提交
	 * 
	 * @return
	 */
	public static SqlSession openSession() {
		return getSqlSessionFactory().openSession();
	}

	/**
	 * 打开新的会话
	 * 
	 * @param autoCommit
	 *            是否自动提交
	 * @return
	 */
	public static SqlSession openSession(boolean autoCommit) {
		return getSqlSessionFactory().openSession();
	}
}