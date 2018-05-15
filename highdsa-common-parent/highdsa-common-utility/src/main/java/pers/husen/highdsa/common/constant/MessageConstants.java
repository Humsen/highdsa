package pers.husen.highdsa.common.constant;

/**
 * @Desc 短信和消息推送常量
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午4:41:28
 * 
 * @Version 1.0.1
 */
public class MessageConstants {
	/** 产品名称:云通信短信API产品,开发者无需替换 */
	public static final String PRODUCT = "Dysmsapi";
	/** 产品域名,开发者无需替换 */
	public static final String DOMAIN = "dysmsapi.aliyuncs.com";

	/** 短信签名 */
	public static final String SIGN_NAME = "何明胜highdsa项目";

	/** 用户注册短信模板 */
	public static final String TEMPLATE_REGISTER = "SMS_134318564";
	/** 用户通知短信模板 */
	public static final String TEMPLATE_ADVICE = "SMS_126900105";

	/** 短信配置文件名称 */
	public static final String SMS_CONFIG_FILE = "message.properties";
	
	// 以下是相关属性的key
	
	public static final String ACCESS_KEY_ID = "access_key_id";
	public static final String ACCESS_KEY_SECRET = "access_key_secret";

	/** 短信地区id */
	public static final String SMS_REGION_ID = "cn-hangzhou";
	/** 默认连接超时 */
	public static final String DEFAULT_CONNECT_TIMEOUT = "sun.net.client.defaultConnectTimeout";
	public static final String CONNECT_TIMEOUT_PERIOD = "10000";
	/** 默认读取超时 */
	public static final String DEFAULT_READ_TIMEOUT = "sun.net.client.defaultReadTimeout";
	public static final String READ_TIMEOUT_PERIOD = "10000";

	/* ------------------------------- 分割线 ------------------------------ */

	/** 短信配置文件名称 */
	public static final String COMET_CONFIG_FILE = "appkey.properties";
	
	// 以下是相关属性的key
	
	public static final String REST_HOST = "rest_host";
	public static final String COMMON_KEY = "common_key";
}