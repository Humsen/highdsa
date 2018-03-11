package pers.husen.highdsa.common.entity.vo.redis;

/**
 * @Desc RESTful API返回json
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月5日 下午3:32:54
 * 
 * @Version 1.0.0
 */
public class RedisJson {
	private String msg;
	
	public RedisJson() {
	}

	public RedisJson(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}