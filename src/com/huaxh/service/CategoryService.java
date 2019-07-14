package com.huaxh.service;

import com.huaxh.bean.Category;
import com.huaxh.bean.MainCategory;
import com.huaxh.bean.SubCategory;
import com.huaxh.dao.CategoryDao;

import java.util.List;

/**
 * 对于分类提供的服务
 */
public class CategoryService {
    CategoryDao categoryDao = null;

    public CategoryService() {
        categoryDao = new CategoryDao();
    }

    /**
     * 获取所有分类的信息
     *
     * @return 主分类查询结果列表
     */
    public List<Category> getCategory(Category searchModel) {
        return categoryDao.getCategory(searchModel);
    }

    /**
     * 获取所有主分类的键值对
     *
     * @return 主分类查询结果的列表
     */
    public List<MainCategory> getMainCategory() {
        return categoryDao.getMainCategoryLsit();
    }

    /**
     * 获取选中主分类对应的二级分类的键值对
     *
     * @return 二级分类查询结构的列表
     */
    public List<SubCategory> getSubCategory(int mCategoryId) {
        return categoryDao.getSubCategory(mCategoryId);
    }

    /**
     * 添加主分类
     */
    public boolean addMainCategory(MainCategory mainCategory) {
        return categoryDao.addMainCategory(mainCategory);
    }

    /**
     * 添加二级分类
     */
    public boolean addSubCategory(SubCategory subCategory) {
        return categoryDao.addSubCategory(subCategory);
    }

    /**
     * 修改主分类
     */
    public boolean updateMainCategory(MainCategory mainCategory) {
        return categoryDao.updateMainCategory(mainCategory);
    }

    /**
     * 修改二级分类
     */
    public boolean updateSubCategory(SubCategory subCategory) {
        return categoryDao.updateSubCategory(subCategory);
    }

    /**
     * 删除主分类
     */
    public boolean deleteMainCategory(int id) {
        return categoryDao.deleteMainCategory(id);
    }

    /**
     * 删除二级分类
     */
    public boolean deleteSubCategory(int id) {
        return categoryDao.deleteSubCategory(id);
    }
}
