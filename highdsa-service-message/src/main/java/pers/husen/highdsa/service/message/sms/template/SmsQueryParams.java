package pers.husen.highdsa.service.message.sms.template;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;

/**
 * @Desc 发送短信后查询结果详情
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午5:09:16
 * 
 * @Version 1.0.0
 */
public class SmsQueryParams {
	QuerySendDetailsRequest request = new QuerySendDetailsRequest();

	private String phoneNumber;
	/** 发送日期 支持30天内记录查询，格式yyyyMMdd */
	private String sendDate;
	private Long pageSize;
	/** 当前页码从1开始计数 */
	private Long currentPage;

	/** 流水号 */
	private String bizId;

	@Override
	public String toString() {
		return "SmsQueryParams [request=" + request + ", phoneNumber=" + phoneNumber + ", sendDate=" + sendDate
				+ ", PageSize=" + pageSize + ", currentPage=" + currentPage + ", bizId=" + bizId + "]";
	}

	public SmsQueryParams() {
	}

	/**
	 * @param request
	 * @param phoneNumber
	 * @param sendDate
	 * @param pageSize
	 * @param currentPage
	 * @param bizId
	 */
	public SmsQueryParams(QuerySendDetailsRequest request, String phoneNumber, String sendDate, Long pageSize,
			Long currentPage, String bizId) {
		super();
		this.request = request;
		this.phoneNumber = phoneNumber;
		this.sendDate = sendDate;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.bizId = bizId;
	}

	/**
	 * @return the request
	 */
	public QuerySendDetailsRequest getRequest() {
		request.setPhoneNumber(phoneNumber);
        request.setSendDate(new SimpleDateFormat(sendDate).format(new Date()));
        request.setPageSize(pageSize);
        request.setCurrentPage(currentPage);
        
        if(bizId != null) {
        	request.setBizId(bizId);
        }
        
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(QuerySendDetailsRequest request) {
		this.request = request;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the sendDate
	 */
	public String getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate
	 *            the sendDate to set
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @return the pageSize
	 */
	public Long getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public Long getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the bizId
	 */
	public String getBizId() {
		return bizId;
	}

	/**
	 * @param bizId
	 *            the bizId to set
	 */
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
}