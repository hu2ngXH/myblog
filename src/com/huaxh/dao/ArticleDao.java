package com.huaxh.dao;


import com.huaxh.bean.Article;
import com.huaxh.bean.Pager;
import com.huaxh.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {
    /**
     * 根据查询条件 查询文章分页信息
     *
     * @param searchModel 封装查询条件
     * @param pageNum     查询第几页数据
     * @param pageSize    每页显示多少条记录
     * @return 查询结果
     */
    public Pager<Article> findArticle(Article searchModel, int pageNum, int pageSize) {
        Pager<Article> result = null;
        List<Object> paramList = new ArrayList<>();
        //存放查询参数
        int id = searchModel.getId();
        int sCategoryId = searchModel.getsCategoryId();
        int mCategoryId = searchModel.getmCategoryId();
        StringBuilder sql = new StringBuilder("select a.*, m.name mname, s.name sname from " +
                " article a left join maincategory m on a.mCategoryId=m.id" +
                " left join subcategory s on a.sCategoryId= s.id where 1=1");//联表查询 查找外连接
        StringBuilder countSql = new StringBuilder("select count(id) as totalRecord from article where 1=1");//函数返回匹配指定条件的函数 意思就是返回article的总数量
        if (id != 0) {
            sql.append(" and a.id =?");
            countSql.append(" and id=?");
            paramList.add(id);
        }
        if (sCategoryId != 0) {
            sql.append(" and a.sCategoryId = ?");
            countSql.append(" and sCategoryId = ?");
            paramList.add(sCategoryId);
        }
        if (mCategoryId != 0) {
            sql.append(" and a.mCategoryId = ?");
            countSql.append(" and mCategoryId = ?");
            paramList.add(mCategoryId);
        }
        sql.append(" ORDER BY top desc,createdDate desc"); // 对结果集进行排序 降序
        countSql.append(" ORDER BY top desc,createdDate desc");
        //起始索引
        int fromIndex = pageSize * (pageNum - 1);// 每页偏移量 一定是前面的页数都用完了
        //使用limit关键字 实现分页
        sql.append(" limit " + fromIndex + "," + pageSize);//查询限制条数  位置偏移量 行数
        //存放所有查询出的文章对象
        List<Article> studentList = new ArrayList<>();
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            //链接数据库
            jdbcUtil.getConnection();
            //获取总记录数
            List<Map<String, Object>> countResult = jdbcUtil.findResult(countSql.toString(), paramList);
            Map<String, Object> countMap = countResult.get(0);//获取数量
            int totalRecord = ((Number) countMap.get("totalRecord")).intValue();
            //获取查询文章记录
            List<Map<String, Object>> studentResult = jdbcUtil.findResult(sql.toString(), paramList);
            if (studentResult != null) {
                for (Map<String, Object> map : studentResult) {
                    Article s = new Article(map);
                    studentList.add(s);
                }
            }
            //获取总页数
            int totalPage = totalRecord / pageSize;
            if (totalRecord % pageSize != 0) {
                totalPage++;
            }
            //组装pager对象
            result = new Pager<Article>(pageSize, pageNum, totalRecord, totalPage, studentList);

        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据异常！", e);
        } finally {
            if (jdbcUtil != null) {
                jdbcUtil.releaseConn();//释放资源
            }
        }
        return result;
    }

    /**
     * 添加新文章
     *
     * @param article 文章对象
     * @return 插入结果
     */
    public boolean addArticle(Article article) {
        boolean result = false;
        StringBuilder sql;
        if (article.getsCategoryId() != 0) {
            sql = new StringBuilder("insert into article(title,digest,mdContent,htmlContent," +
                    "createdDate,mCategoryId,sCategoryId,top) values(?,?,?,?,?,?,?,?);");
        } else {
            sql = new StringBuilder("insert into article(title,digest,mdContent,htmlContent," +
                    "createdDate,mCategoryId,top) values(?,?,?,?,?,?,?);");
        }
        JdbcUtil jdbcUtil = null;
        System.out.println("sql: " + sql);
        System.out.println("article: " + article.toList());
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            result = jdbcUtil.updateByPstmt(sql.toString(), article.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }

    /**
     * 修改文章
     *
     * @param article 文章对象
     * @param id      文章
     * @return result 结果
     */
    public boolean updateArticle(Article article, int id) {
        boolean result = false;
        StringBuilder sql;
        if (article.getsCategoryId() != 0) {
            sql = new StringBuilder("update article set title = ?,digest = ?,mdContent = ?,htmlContent = ?," +
                    "createdDate = ?,sCategoryId = ?,mCategoryId= ?,top = ?  where id = ? ");
        } else {
            sql = new StringBuilder("update article set title = ?,digest = ?,mdContent = ?,htmlContent = ?," +
                    "createdDate = ?,mCategoryId= ?,top = ?  where id = ? ");
        }
        JdbcUtil jdbcUtil = null;
        ArrayList paramList = (ArrayList) article.toList();
        paramList.add(id);
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            result = jdbcUtil.updateByPstmt(sql.toString(), paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return result 删除结果
     */
    public boolean deleteArticle(int id) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("delete from article where id = ?;");
        JdbcUtil jdbcUtil = null;
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(id);
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            result = jdbcUtil.updateByPstmt(sql.toString(), paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }
}
