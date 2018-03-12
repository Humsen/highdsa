package pers.husen.highdsa.service.message.sms.template;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;

/**
 * @Desc 发送短信请求参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午3:37:40
 * 
 * @Version 1.0.0
 */
public class SmsSendParams {
	private SendSmsRequest request = new SendSmsRequest();

	private String signName;
	private String templateCode;
	private String phoneNumbers;
	private String templateParam;
	private String outId;

	/**
	 * @return the request
	 */
	public SendSmsRequest getRequest() {
		request.setPhoneNumbers(phoneNumbers);
		request.setSignName(signName);
		request.setTemplateCode(templateCode);
		request.setTemplateParam(templateParam);

		// 选填项
		if (outId != null) {
			request.setOutId(outId);
		}

		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(SendSmsRequest request) {
		this.request = request;
	}

	/**
	 * @return the phoneNumbers
	 */
	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * @param phoneNumbers
	 *            the phoneNumbers to set
	 */
	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	/**
	 * @return the signName
	 */
	public String getSignName() {
		return signName;
	}

	/**
	 * @param signName
	 *            the signName to set
	 */
	public void setSignName(String signName) {
		this.signName = signName;
	}

	/**
	 * @return the templateCode
	 */
	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * @param templateCode
	 *            the templateCode to set
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * @return the templateParam
	 */
	public String getTemplateParam() {
		return templateParam;
	}

	/**
	 * @param templateParam
	 *            the templateParam to set
	 */
	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}

	/**
	 * @return the outId
	 */
	public String getOutId() {
		return outId;
	}

	/**
	 * @param outId
	 *            the outId to set
	 */
	public void setOutId(String outId) {
		this.outId = outId;
	}

	@Override
	public String toString() {
		return "RegisterCaptcha [request=" + request + ", phoneNumbers=" + phoneNumbers + ", signName=" + signName
				+ ", templateCode=" + templateCode + ", templateParam=" + templateParam + ", outId=" + outId + "]";
	}
}