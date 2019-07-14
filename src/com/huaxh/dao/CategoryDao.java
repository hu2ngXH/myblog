package com.huaxh.dao;

import com.huaxh.bean.Category;
import com.huaxh.bean.MainCategory;
import com.huaxh.bean.SubCategory;
import com.huaxh.util.JdbcUtil;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryDao {
    /**
     * 获取所有分类的信息
     *
     * @return 分类查询列表
     */
    public List<Category> getCategory(Category searchModel) {
        List<Category> result = new ArrayList<>();

        List<Object> paramList = new ArrayList<>();
        int mCategoryId = searchModel.getmCategoryId();
        int sCategoryId = searchModel.getsCategoryId();
        StringBuilder sql = new StringBuilder("select m.id id ,m.name name ,s.id sCategoryId , s.name sCategoryName, (select count(id) from article where mCategoryId = s.mCategoryId and sCategoryId = s.id) count from maincategory m,subcategory s where m.id = s.mCategoryId;");
        if (mCategoryId != 0) {
            sql.append(" and s.mCategoryId=?");
            paramList.add(mCategoryId);
        }
        if (sCategoryId != 0) {
            sql.append(" and s.sCategoryId=?");
            paramList.add(sCategoryId);
        }
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            List<Map<String, Object>> categoryResult = jdbcUtil.findResult(sql.toString(), paramList);
            if (categoryResult != null) {
                for (Map<String, Object> map : categoryResult) {
                    Category s = new Category(map);
                    result.add(s);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询数据异常！", e);
        } finally {
            if (jdbcUtil != null) {
                jdbcUtil.releaseConn();
            }
        }
        return result;
    }


    /**
     * 获取所有主分类的列表
     * return 主分类查询结果的列表
     */
    public List<MainCategory> getMainCategoryLsit() {
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        StringBuilder sql = new StringBuilder("select *,(select count(*) from article where mCategoryId = m.id) count " +
                "from maincategory m;");
        List<Map<String, Object>> CategoryList = null;
        List<MainCategory> result = new ArrayList<>();
        List<SubCategory> sublist = null;
        try {
            CategoryList = jdbcUtil.findResult(sql.toString(), new ArrayList<>());
            if (CategoryList != null) {
                for (Map<String, Object> map : CategoryList) {
                    MainCategory category = new MainCategory(map);
                    sublist = getSubCategory(Integer.parseInt(map.get("id").toString()));
                    category.setSublist(sublist);
                    result.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }

    /**
     * 获取指定主分类对应的二级分类
     *
     * @return 二级分类查询结果的列表
     */
    public List<SubCategory> getSubCategory(int mCategoryId) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();//获取数据库链接
        StringBuilder sql = new StringBuilder("select *,(select count(*) from article where sCategoryId = s.id) count" +
                " from subcategory s where 1=1 ");
        List<Object> paramList = new ArrayList<>();
        List<Map<String, Object>> subCategoryList = null;
        List<SubCategory> result = new ArrayList<>();
        if (mCategoryId != 0) {
            sql.append(" and mCategoryId = ?");
            paramList.add(mCategoryId);
        }
        try {
            subCategoryList = jdbcUtil.findResult(sql.toString(), paramList);
            if (subCategoryList != null) {
                for (Map<String, Object> map : subCategoryList) {
                    result.add(new SubCategory(map));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }

    /**
     * 添加主分类
     */
    public boolean addMainCategory(MainCategory mainCategory) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("insert into maincategory(name) values(?);");
        JdbcUtil jdbcUtil = null;
        List<Object> paramList = new ArrayList<>();
        paramList.add(mainCategory.getName());
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
     * 添加二级分类
     */
    public boolean addSubCategory(SubCategory subCategory) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("insert into subcategory(name,mCategoryId) values(?,?);");
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection();
            result = jdbcUtil.updateByPstmt(sql.toString(), subCategory.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }


    /**
     * 修改主分类
     */
    public boolean updateMainCategory(MainCategory mainCategory) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("update maincategory set name = ? where id = ?");
        List<Object> paramList = new ArrayList<>();
        paramList.add(mainCategory.getName());
        paramList.add(mainCategory.getId());
        JdbcUtil jdbcUtil = null;
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
     * 修改二级分类
     */
    public boolean updateSubCategory(SubCategory subCategory) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("update subcategory set name = ? , mCategoryId = ? where id = ?");
        List<Object> paramList = new ArrayList<>();
        paramList.add(subCategory.getName());
        paramList.add(subCategory.getmCategoryId());
        paramList.add(subCategory.getId());
        JdbcUtil jdbcUtil = null;
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
     * 删除主分类
     */
    public boolean deleteMainCategory(int id) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("delete from maincategory where id = ?;");//这里应该要把所有这个分类的子分类也删掉
        List<Object> paramList = new ArrayList<>();
        paramList.add(id);
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPstmt(sql.toString(), paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }

    /**
     * 删除二级分类
     */
    public boolean deleteSubCategory(int id) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("delete from subcategory where id = ?;");//这里应该要把所有这个分类的子分类也删掉
        List<Object> paramList = new ArrayList<>();
        paramList.add(id);
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPstmt(sql.toString(), paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return result;
    }
}
