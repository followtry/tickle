package cn.followtry.test.mybatis.handler;
/**
 * 
 */

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 自定义处理实现了{@link IBaseEnum}接口的枚举类
 * 
 * @author jingzz
 * @time 2016年8月16日 下午8:06:49
 * @name esn-palmyy-plugin/com.yonyou.esn.palmyy.common.EnumValueTypeHandler
 * @since 2016年8月16日 下午8:06:49
 */
public class EnumValueTypeHandler<E extends IBaseEnum> extends BaseTypeHandler<E> {

	private Class<E> type;

	private final E[] enums;

	public EnumValueTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null) {
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
	}

	/**
	 * 将参数中的枚举值转为对应的int值
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		// 获取非空的枚举的int值并设置到statement中
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int i = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			try {
				return getEnumByValue(i);
			} catch (Exception ex) {
				throw new IllegalArgumentException(
						"Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
			}
		}
	}

	/**
	 * 通过枚举类型的int值，获取到对应的枚举类型
	 * 
	 * @author jingzz
	 * @param i
	 */
	protected E getEnumByValue(int i) {
		for (E e : enums) {
			if (e.getValue() == i) {
				return e;
			}
		}
		return null;
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int i = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			try {
				return getEnumByValue(i);
			} catch (Exception ex) {
				throw new IllegalArgumentException(
						"Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
			}
		}
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int i = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			try {
				return getEnumByValue(i);
			} catch (Exception ex) {
				throw new IllegalArgumentException(
						"Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
			}
		}
	}


}
