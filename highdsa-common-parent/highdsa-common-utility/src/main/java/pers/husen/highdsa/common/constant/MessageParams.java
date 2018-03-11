package pers.husen.highdsa.common.constant;

/**
 * @Desc 短信发送常量
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午4:41:28
 * 
 * @Version 1.0.0
 */
public class MessageParams {
	/** 产品名称:云通信短信API产品,开发者无需替换 */
	public static final String PRODUCT = "Dysmsapi";
	/** 产品域名,开发者无需替换 */
	public static final String DOMAIN = "dysmsapi.aliyuncs.com";

	/** 短信签名 */
	public static final String SIGN_NAME = "highdsa架构测试";

	/** 用户注册短信模板 */
	public static final String TEMPLATE_REGISTER = "SMS_126970475";
	/** 用户通知短信模板 */
	public static final String TEMPLATE_ADVICE = "SMS_126900105";
}