package com.huaxh.dao;

import com.huaxh.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    public boolean VerifyUser(String username, String password) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        StringBuilder sql = new StringBuilder("Select * from user where 1=1");
        List<Object> paramList = new ArrayList<>();
        paramList.add(username);
        sql.append(" and username = ?");
        paramList.add(password);
        sql.append(" and password = ?");
        List<Map<String, Object>> result = null;
        try {
            result = jdbcUtil.findResult(sql.toString(), paramList);
            if (result.size() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return false;
    }
}
