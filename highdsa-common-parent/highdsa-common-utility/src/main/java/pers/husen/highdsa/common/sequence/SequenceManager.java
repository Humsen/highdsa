package pers.husen.highdsa.common.sequence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.utility.ReadConfigFile;

/**
 * @Desc 雪花算法管理类,读取配置文件的workerId和datacenterId,产生id
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午10:07:43
 * 
 * @Version 1.0.0
 */
public class SequenceManager {
	private static final Logger logger = LogManager.getLogger(SequenceManager.class.getName());

	/** 分布式Id的配置文件 */
	private static Properties sequenceConfig;
	/** 工作区id */
	private static String workerId = null;
	/** 数据中心Id */
	private static String datacenterId = null;

	/** 分布式Id生成序列 */
	private static Sequence sequence = null;

	/**
	 * 返回Long类型的id
	 * 
	 * @return
	 */
	public static Long getNextId() {
		if (sequence == null) {
			init();
		}

		return new Long(sequence.nextId());
	}

	/**
	 * 返回字符串类型的id
	 * 
	 * @return
	 */
	public static String getNextIdStr() {
		if (sequence == null) {
			init();
		}

		return String.valueOf(sequence.nextId());
	}

	/**
	 * 初始化,单例模式
	 */
	private static void init() {
		try {
			sequenceConfig = ReadConfigFile.readByRelativePath("sequence/sequence.properties");
		} catch (UnsupportedEncodingException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} catch (IOException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		workerId = sequenceConfig.getProperty("workerId", "0");
		logger.debug("workerId: {}", getWorkerId());

		datacenterId = sequenceConfig.getProperty("datacenterId", "0");
		logger.debug("datacenterId: {}", getDatacenterId());

		sequence = new Sequence(Long.valueOf(workerId), Long.valueOf(datacenterId));
	}

	private static String getWorkerId() {
		return workerId;
	}

	private static String getDatacenterId() {
		return datacenterId;
	}
}