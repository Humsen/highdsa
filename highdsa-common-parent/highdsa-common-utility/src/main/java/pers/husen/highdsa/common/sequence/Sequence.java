package pers.husen.highdsa.common.sequence;

/**
 * 
 * @Desc 基于Twitter的Snowflake算法实现分布式高效有序ID生产黑科技(sequence)
 * 
 *       <br>
 *       SnowFlake的结构如下(每部分用-分开):<br>
 *       <br>
 *       0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 -
 *       000000000000 <br>
 *       <br>
 *       1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 *       <br>
 *       41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 *       得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T
 *       = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 *       <br>
 *       10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 *       <br>
 *       12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 *       <br>
 *       <br>
 *       加起来刚好64位，为一个Long型。<br>
 *       SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月16日 下午9:11:03
 * 
 * @Version 1.0.0
 */
public class Sequence {
	/** 起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量 **/
	private final long twepoch = 1288834974657L;
	/** workerId占用的位数5（表示只允许workId的范围为：0-1023） **/
	private final long workerIdBits = 5L;
	/** dataCenterId占用的位数：5 **/
	private final long datacenterIdBits = 5L;
	/** workerId可以使用的最大数值：31 **/
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	/** dataCenterId可以使用的最大数值：31 **/
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	/** 序列号占用的位数：12（表示只允许workId的范围为：0-4095） **/
	private final long sequenceBits = 12L;

	private final long workerIdShift = sequenceBits;
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	/** 用mask防止溢出:位与运算保证计算的结果范围始终是 0-4095 **/
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;

	/**
	 * 基于Snowflake创建分布式ID生成器
	 * <p>
	 * 注：sequence
	 *
	 * @param workerId
	 *            工作机器ID,数据范围为0~31
	 * @param dataCenterId
	 *            数据中心ID,数据范围为0~31
	 */
	public Sequence(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}

		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	/**
	 * 获取下一个ID
	 * 
	 * @return
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();

		// 闰秒：如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (timestamp < lastTimestamp) {
			long offset = lastTimestamp - timestamp;
			int maxOffset = 5;
			if (offset <= maxOffset) {
				try {
					this.wait(offset << 1);
					timestamp = this.timeGen();
					if (timestamp < lastTimestamp) {
						throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else {
				throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
			}
		}

		// 解决跨毫秒生成ID序列号始终为偶数的缺陷:如果是同一时间生成的，则进行毫秒内序列
		if (lastTimestamp == timestamp) {
			// 通过位与运算保证计算的结果范围始终是 0-4095
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = this.tilNextMillis(lastTimestamp);
			}
		} else {
			// 时间戳改变，毫秒内序列重置
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		/*
		 * 1.左移运算是为了将数值移动到对应的段(41、5、5，12那段因为本来就在最右，因此不用左移)
		 * 2.然后对每个左移后的值(la、lb、lc、sequence)做位或运算，是为了把各个短的数据合并起来，合并成一个二进制数
		 * 3.最后转换成10进制，就是最终生成的id
		 */
		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	}

	/**
	 * 保证返回的毫秒数在参数之后(阻塞到下一个毫秒，直到获得新的时间戳)
	 *
	 * @param lastTimestamp
	 * @return
	 */
	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}

		return timestamp;
	}

	/**
	 * 获得系统当前毫秒数
	 *
	 * @return timestamp
	 */
	protected long timeGen() {
		// 解决高并发下获取时间戳的性能问题
		return SystemClock.now();
	}
}