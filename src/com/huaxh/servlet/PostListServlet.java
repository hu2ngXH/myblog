package com.huaxh.servlet;

import com.huaxh.bean.Article;
import com.huaxh.bean.MainCategory;
import com.huaxh.bean.Pager;
import com.huaxh.service.ArticleService;
import com.huaxh.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//处理列表
public class PostListServlet extends HttpServlet {
    ArticleService articleService = new ArticleService();
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //用来确保发往服务器的参数以汉字的编码来提取，设置从request中取得的值或从数据库中取出的值
        response.setContentType("text/html;charset=utf-8");
        int pageNum = 1;
        int pageSize = 10;
        int mCategoryId = 0;
        int sCategoryId = 0;
        int id = 0;

        String role = request.getParameter("role");
        //组装查询条件
        Article searchModel = new Article();
        if (request.getParameter("main_id") != null)
            mCategoryId = Integer.parseInt(request.getParameter("main_id"));
        if (request.getParameter("sub_id") != null)
            sCategoryId = Integer.parseInt(request.getParameter("sub_id"));
        if (request.getParameter("id") != null)
            id = Integer.parseInt(request.getParameter("id"));
        if (request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        if (request.getParameter("pageSize") != null)
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if (mCategoryId != 0) searchModel.setmCategoryId(mCategoryId);
        if (sCategoryId != 0) searchModel.setsCategoryId(sCategoryId);
        if (id != 0) searchModel.setId(id);

        // 调用service 获取查询结果
        Pager<Article> result = articleService.findArticle(searchModel, pageNum, pageSize);
        List<MainCategory> mainCategoryList = categoryService.getMainCategory();

        // 返回到结果页面
        request.setAttribute("result", result);
        request.setAttribute("mainCategory", mainCategoryList);
        if (role.equals("0")) { // 主页
            request.getRequestDispatcher("/blog.jsp").forward(request, response);
        } else if (role.equals("1")) { // 后台界面
            request.getRequestDispatcher("/admin/postlist.jsp").forward(request, response);
        } else if (role.equals("2")) { //文章页
            request.getRequestDispatcher("/article.jsp").forward(request, response);
        } else if (role.equals("3")) { // 更新文章页
            request.getRequestDispatcher("/admin/updatepost.jsp").forward(request, response);
        } else if (role.equals("4")) { // 项目
            request.getRequestDispatcher(request.getContextPath() + "/project.jsp").forward(request, response);
        }

    }
}
