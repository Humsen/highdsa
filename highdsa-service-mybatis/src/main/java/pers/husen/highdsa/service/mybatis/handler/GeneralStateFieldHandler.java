package pers.husen.highdsa.service.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import pers.husen.highdsa.common.entity.constants.BaseEnum;

/**
 * @Desc 实现Mybatis中系统用户状态枚举转换成状态码,通用状态字段处理类
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月27日 下午1:33:27
 * 
 * @Version 1.0.0
 */
@SuppressWarnings("rawtypes")
public final class GeneralStateFieldHandler<E extends BaseEnum> extends BaseTypeHandler<E> {
	private Class<E> type;
	private E[] enums;

	/**
	 * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
	 * 
	 * @param type
	 *            配置文件中设置的转换类
	 */
	public GeneralStateFieldHandler(Class<E> type) {
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");

		this.type = type;
		this.enums = type.getEnumConstants();

		if (this.enums == null)
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		// baseTypeHandler已经帮我们做了parameter的null判断
		ps.setObject(i, parameter.getStateCode());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// 根据数据库存储类型决定获取类型,本例子中数据库中存放String类型
		String userState = rs.getString(columnName);

		if (rs.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值，定位EnumStatus子类
			return locateEnumStatus(userState);
		}
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// 根据数据库存储类型决定获取类型,本例子中数据库中存放String类型
		String userState = rs.getString(columnIndex);

		if (rs.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值，定位EnumStatus子类
			return locateEnumStatus(userState);
		}
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		String userState = cs.getString(columnIndex);

		if (cs.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值,定位EnumStatus子类
			return locateEnumStatus(userState);
		}
	}

	/**
	 * 枚举类型转换，由于构造函数获取了枚举的子类enums,让遍历更加高效快捷
	 * 
	 * @param code
	 *            数据库中存储的自定义code属性
	 * @return code对应的枚举类
	 */
	private E locateEnumStatus(String stateCode) {
		for (E e : enums) {
			if (e.getStateCode().equals(stateCode)) {
				return e;
			}
		}

		throw new IllegalArgumentException("未知的枚举类型：" + stateCode + ",请核对" + type.getSimpleName());
	}
}