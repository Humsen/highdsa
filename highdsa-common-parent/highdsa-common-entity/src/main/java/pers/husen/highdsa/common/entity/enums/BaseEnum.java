package pers.husen.highdsa.common.entity.enums;

/**
 * @Desc 数据库状态字段枚举基类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月27日 下午2:59:35
 * 
 * @Version 1.0.0
 */
public interface BaseEnum<E extends Enum<?>, T> {
	/**
	 * 状态码
	 * 
	 * @return
	 */
	public T getStateCode();

	/**
	 * 状态描述
	 * 
	 * @return
	 */
	public String getDescrition();
}