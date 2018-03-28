package pers.husen.highdsa.common.entity.vo.message;

import java.io.Serializable;

/**
 * @Desc 查询短信发送结果Vo
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月13日 下午2:47:27
 * 
 * @Version 1.0.0
 */
public class SmsQueryRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long resourceOwnerId;

	private String resourceOwnerAccount;

	private String sendDate;

	private String phoneNumber;

	private String bizId;

	private Long pageSize;

	private Long currentPage;

	private Long ownerId;

	@Override
	public String toString() {
		return "SmsQueryRequest [resourceOwnerId=" + resourceOwnerId + ", resourceOwnerAccount=" + resourceOwnerAccount
				+ ", sendDate=" + sendDate + ", phoneNumber=" + phoneNumber + ", bizId=" + bizId + ", pageSize="
				+ pageSize + ", currentPage=" + currentPage + ", ownerId=" + ownerId + "]";
	}

	/**
	 * @return the resourceOwnerId
	 */
	public Long getResourceOwnerId() {
		return resourceOwnerId;
	}

	/**
	 * @param resourceOwnerId
	 *            the resourceOwnerId to set
	 */
	public void setResourceOwnerId(Long resourceOwnerId) {
		this.resourceOwnerId = resourceOwnerId;
	}

	/**
	 * @return the resourceOwnerAccount
	 */
	public String getResourceOwnerAccount() {
		return resourceOwnerAccount;
	}

	/**
	 * @param resourceOwnerAccount
	 *            the resourceOwnerAccount to set
	 */
	public void setResourceOwnerAccount(String resourceOwnerAccount) {
		this.resourceOwnerAccount = resourceOwnerAccount;
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
	 * @return the ownerId
	 */
	public Long getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}