package com.huaxh.test;

import com.mysql.cj.jdbc.Driver;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class leetcode {

    //回文串
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;// 这里设置
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 0 3
            int len2 = expandAroundCenter(s, i, i + 1); // 0 0
            int len = Math.max(len1, len2); // 0 3
            if (len > end - start) {
                start = i - (len - 1) / 2; //
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    //中心扩展法
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        //
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) { // 如果 左边大于0 右边小于字符串的长  左右两边的字符相等
            L--;
            R++;
        }
        return R - L - 1;
    }


    //整数反转
    class ReverseSolution {
        public int reverse(int x) {
            int ans = 0;
            while (x != 0) {
                int r = x % 10;
                if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && r > 7)) {
                    return 0;
                } else if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && r < -8)) {
                    return 0;
                }
                x /= 10;
                ans = ans * 10 + r;
            }
            return ans;
        }
    }


    public void demo1() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得链接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "Hrz@123_");
            //3.创建执行sql对象 并且执行sql
            //3.1 创建执行对象
            String sql = "select s.id sCategoryId , s.name sCategoryName,m.id id ,m.name name , (select count(id) from article where mCategoryId = s.mCategoryId and sCategoryId = s.id) count from subcategory s left join maincategory m on m.id = s.mCategoryId;";
            //3.2 执行sql
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " +
                        rs.getInt("sCategoryId") + " " + rs.getString("sCategoryName") + " " + rs.getInt("count"));
            }
//            Statement stmt = conn.createStatement();
//            ResultSet rs = pstmt.executeQuery();
//            ResultSetMetaData metaData = rs.getMetaData();//描述数据的数据
//            int cols_len = metaData.getColumnCount();//返回所有字段的数目
//            while (rs.next()) {
//                Map<String, Object> map = new HashMap<>();
//                List list = new ArrayList();
//                for (int i = 0; i < cols_len; i++) {
//                    String cols_name = metaData.getColumnName(i + 1);//根据字段的索引值取得字段的名称
//                    Object cols_value = rs.getObject(cols_name); // todo 这里不应该用名字获取 应该用列来获取
//
//                    System.out.println("cols_name : " + cols_name);
//                    if (cols_value == null) {
//                        cols_value = "";
//                    }
//                    list.add(cols_value);
//                    map.put(cols_name, cols_value);
//                    System.out.println("map : " + map);
//                    System.out.println("list: " + list);
//                }
//            }
//            System.out.println("no_problem");
            //4.释放资源

        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

    }


    public static void main(String[] args) {
        leetcode L = new leetcode();
        try {
            L.demo1();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Calendar calendar = Calendar.getInstance();
//        System.out.println("Year :" + calendar.get(Calendar.YEAR));

    }
}
