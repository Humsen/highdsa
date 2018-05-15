package pers.husen.highdsa.common.entity.vo.message;

import java.io.Serializable;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * @Desc 继承SendSmsResponse,实现序列化
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 上午11:25:34
 * 
 * @Version 1.0.1
 */
public class SmsSendResponse extends SendSmsResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "SmsSendResponse [getRequestId()=" + getRequestId() + ", getBizId()=" + getBizId() + ", getCode()=" + getCode() + ", getMessage()=" + getMessage() + ", checkShowJsonItemName()="
				+ checkShowJsonItemName() + "]";
	}
}