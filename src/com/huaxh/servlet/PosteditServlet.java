package com.huaxh.servlet;

import com.huaxh.bean.Article;
import com.huaxh.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理修改文章 更新文章的操作
public class PosteditServlet extends HttpServlet {
    ArticleService articleService;
    Article article;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        articleService = new ArticleService();
        String action = request.getParameter("action");
        int id = 0;
        if (action.equals("delete")) {
            if (articleService.deleteArticle(Integer.parseInt(request.getParameter("id")))) {
                response.getWriter().write("删除成功");
            }
        } else {
            article = new Article();
            article.setTitle(request.getParameter("title"));//设置时间
            System.out.println("md_text: " + request.getParameter("md_text"));
            article.setMdContent(request.getParameter("md_text"));//存放markdown形式的数据
            article.setHtmlContent(request.getParameter("test-editormd-html-code"));
            System.out.println("===============: " + request.getParameter("test-editormd-html-code"));
            int top = request.getParameter("top") == null ? 0 : 1;
            article.setTop(top);
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String hour = request.getParameter("hour");
            String minute = request.getParameter("minute");
            String createdate = year + "-" + month + "-" + day + " " + hour + ":" + minute;
            article.setCreatedDate(createdate);
            article.setDigest(request.getParameter("digest"));
            article.setmCategoryId(Integer.parseInt(request.getParameter("main_id")));
            if (request.getParameter("sub_id") != null)
                article.setsCategoryId(Integer.parseInt(request.getParameter("sub_id")));
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));
            System.out.println("article: " + article);
            switch (action) {
                case "add":
                    if (articleService.addArticle(article) == true) {
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "update":
                    if (articleService.updateArticle(article, id) == true) {
                        request.getRequestDispatcher(request.getContextPath() + "/success.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
                    }
                    break;
            }
        }
    }
}
