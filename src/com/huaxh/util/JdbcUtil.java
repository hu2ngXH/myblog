package com.huaxh.util;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JdbcUtil {
    //类变量
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;
    private static String URL;

    static {
        try {
            //path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取
            InputStream inputStream = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
            //加载数据表配置
            Properties prop = new Properties();
            prop.load(inputStream);
            USERNAME = prop.getProperty("jdbc.username");
            PASSWORD = prop.getProperty("jdbc.password");
            DRIVER = prop.getProperty("jdbc.driver");
            URL = prop.getProperty("jdbc.url");
        } catch (Exception e) {
            throw new RuntimeException("读取数据库配置文件异常！", e);
        }
    }

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public JdbcUtil() {

    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("get connection error!", e);
        }
        return connection;
    }

    /**
     * 执行更新操作
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return 执行结果
     * @throws java.sql.SQLException
     */
    public boolean updateByPstmt(String sql, List<?> params) throws SQLException {
        boolean flag = false;
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (params != null && !params.isEmpty()) { // 不等于null和不是空的是两个概念
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        int result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 执行查询操作
     */
    public List<Map<String, Object>> findResult(String sql, List<?> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();//描述数据的数据
        int cols_len = metaData.getColumnCount();//返回所有字段的数目
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnLabel(i + 1);//获取列的别名
                Object cols_value = rs.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 释放资源
     */
    public void releaseConn() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
