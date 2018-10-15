package com.yihaomen.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description: 自定义处理日期Hander,
 *                将Date类型转换为时间戳字符串戳存入到数据库中
 *  1.@MappedJdbcTypes定义的是JdbcType类型，这里的类型不可自己随意定义，
 *   必须要是枚举类org.apache.ibatis.type.JdbcType所枚举的数据类型。
 *  2.@MappedTypes定义的是JavaType的数据类型，描述了哪些Java类型可被拦截。
 *  3.在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤
 */
@MappedTypes({Date.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyDateTypeHandler extends BaseTypeHandler<Date> {
    /**
     * 将时间戳字符串存入数据库
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.valueOf(parameter.getTime()));
    }

    /**
     * 把时间戳类型的字符串取出转换为Date
     */
    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new Date(rs.getLong(columnName));
    }

    /**
     * 把时间戳类型的字符串取出转换为Date
     */
    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Date(rs.getLong(columnIndex));
    }

    /**
     * 把时间戳类型的字符串取出转换为Date
     */
    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getDate(columnIndex);
    }
}
