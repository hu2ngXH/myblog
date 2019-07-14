package com.huaxh.service;

import com.huaxh.bean.Article;
import com.huaxh.bean.Pager;
import com.huaxh.dao.ArticleDao;

// 关于文章的服务
public class ArticleService {
    private ArticleDao articleDao;

    //todo 这里使用依赖注入会更好
    public ArticleService() {
        articleDao = new ArticleDao();
    }

    //根据条件查询 查询文章分页信息
    public Pager<Article> findArticle(Article searchModel, int pageNum, int pageSize) {
        return articleDao.findArticle(searchModel, pageNum, pageSize);
    }

    //插入文章
    public boolean addArticle(Article article) {
        return articleDao.addArticle(article);
    }

    //修改文章
    public boolean updateArticle(Article article, int id) {
        return articleDao.updateArticle(article, id);
    }

    //删除文章
    public boolean deleteArticle(int id) {
        return articleDao.deleteArticle(id);
    }
}
