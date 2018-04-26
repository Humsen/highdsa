package pers.husen.highdsa.common.constant;

/**
 * @Desc 常用的http状态码
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月22日 下午2:36:45
 * 
 * @Version 1.0.0
 */
public class HttpStatusCode {
	/** 正确的请求返回正确的结果 */
	public static final int OK = 200;

	/** [POST/PUT/PATCH]：资源被正确的创建,如新建用户 */
	public static final int CREATED = 201;

	/** 请求时正确的,但是结果正在处理中（异步任务） */
	public static final int ACCEPTED = 202;

	/** [DELETE]：请求正确,但是没有需要返回的内容,如用户删除数据成功 */
	public static final int NO_CONTENT = 204;

	/** 请求的资源可在多处得到 */
	public static final int MUTIPLE_CHOICES = 300;

	/** 删除请求数据 */
	public static final int MOVED_PERMANENTLY = 301;

	/** 在其他地址发现了请求数据 */
	public static final int MOVED_TEMPORARILY = 302;

	/** [POST/PUT/PATCH]：用户发出的请求有错误,服务器没有进行新建或修改数据的操作 */
	public static final int INVALID_REQUEST = 400;

	/** 表示用户没有权限（令牌、用户名、密码错误） */
	public static final int UNAUTHORIZED = 401;

	/** 表示用户得到授权（与401错误相对）,但是访问是被禁止的 */
	public static final int FORBIDDEN = 403;

	/** 用户发出的请求针对的是不存在的记录,没有发现文件、查询或URl */
	public static final int NOT_FOUND = 404;

	/** [GET]：用户请求的格式不可得（比如用户请求JSON格式,但是只有XML格式） */
	public static final int NOT_ACCEPTABLE = 406;

	/** [GET]：用户请求的资源被永久删除,且不会再得到的 */
	public static final int GONE = 410;

	/** [POST/PUT/PATCH] 当创建一个对象时,发生一个验证错误 */
	public static final int UNPROCESABLE_ENTITY = 422;

	/** 服务器发生错误,用户将无法判断发出的请求是否成功 */
	public static final int INTERNAL_SERVER_ERROR = 500;
}