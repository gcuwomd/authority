package com.example.authority.handler;

import com.alibaba.fastjson.JSON;
import com.example.authority.pojo.UnitySystemApi;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PageApiTypeHandler implements TypeHandler<List<UnitySystemApi>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<UnitySystemApi> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setString(i, null);
        } else {
            ps.setString(i, JSON.toJSONString(parameter));
        }
    }

    @Override
    public List<UnitySystemApi> getResult(ResultSet rs, String columnName) throws SQLException {
        String jsonString = rs.getString(columnName);
        return parseJsonString(jsonString);
    }

    @Override
    public List<UnitySystemApi> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonString = rs.getString(columnIndex);
        return parseJsonString(jsonString);
    }

    @Override
    public List<UnitySystemApi> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonString = cs.getString(columnIndex);
        return parseJsonString(jsonString);
    }

    private List<UnitySystemApi> parseJsonString(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        } else {
            System.out.println("in type handler");
            System.out.println(jsonString);
            return JSON.parseArray(jsonString, UnitySystemApi.class);
        }
    }
}