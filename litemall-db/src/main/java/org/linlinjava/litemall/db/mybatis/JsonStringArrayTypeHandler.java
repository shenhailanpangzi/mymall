package org.linlinjava.litemall.db.mybatis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
JsonStringArrayTypeHandler类，实现String[]和varchar的转换，保存的JSON数据格式是[string0, string1, ...]
这里通过自定义TypeHandler，可以实现Java的String[]和数据库类型varchar的自动转换：
1、实现JsonStringArrayTypeHandler类；
2、在mybatis generator配置文件中，配置需要的字段；
3、使用mybatis generator自动生成代码，可以看到LitemallGoods的gallery是String[]类型。
   <columnOverride column="urls" javaType="java.lang.String[]" typeHandler="JsonStringArrayTypeHandler"/>
 */
public class JsonStringArrayTypeHandler extends BaseTypeHandler<String[]> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter));
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName));
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex));
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex));
    }

    private String toJson(String[] params) {
        try {
            return mapper.writeValueAsString(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "[]";
    }

    private String[] toObject(String content) {
        if (content != null && !content.isEmpty()) {
            try {
                return (String[]) mapper.readValue(content, String[].class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}