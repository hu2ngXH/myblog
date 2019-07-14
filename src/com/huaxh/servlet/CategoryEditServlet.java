package com.huaxh.servlet;

import com.huaxh.bean.MainCategory;
import com.huaxh.bean.SubCategory;
import com.huaxh.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryEditServlet extends HttpServlet {
    SubCategory subCategory;
    MainCategory mainCategory;
    boolean result;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if (action.equals("delete")) {
            if (request.getParameter("type").equals("main")) {
                if (new CategoryService().deleteMainCategory(Integer.parseInt(request.getParameter("id")))) {
                    response.getWriter().write("删除成功");
                }
            } else {
                if (new CategoryService().deleteSubCategory(Integer.parseInt(request.getParameter("id")))) {
                    response.getWriter().write("删除成功");
                }
            }
        } else {
            int main_id = 0;
            if (request.getParameter("maincategory") != null)
                main_id = Integer.parseInt(request.getParameter("maincategory")); // 没有主分类为0
            System.out.println("main_id" + main_id);
            subCategory = new SubCategory();
            mainCategory = new MainCategory();
            subCategory.setName(request.getParameter("subcategory"));
            subCategory.setmCategoryId(main_id);
            subCategory.setCount(0);
            if (action.equals("add")) {
                if (main_id != 0)
                    result = new CategoryService().addSubCategory(subCategory);
                else {
                    mainCategory.setName(request.getParameter("subcategory"));
                    result = new CategoryService().addMainCategory(mainCategory);
                }
            } else if (action.equals("update")) {
                if (request.getParameter("sub_id") == null) {
                    mainCategory.setName(request.getParameter("maincategoryname"));
                    mainCategory.setId(Integer.parseInt(request.getParameter("main_id")));
                    result = new CategoryService().updateMainCategory(mainCategory);
                } else {
                    subCategory.setId(Integer.parseInt(request.getParameter("sub_id")));
                    result = new CategoryService().updateSubCategory(subCategory);
                }
            }
            if (result)
                request.getRequestDispatcher("success.jsp").forward(request, response);
            else
                request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}
