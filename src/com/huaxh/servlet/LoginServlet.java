package com.huaxh.servlet;


import com.huaxh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String re = req.getParameter("VerifyCode"); // 传入的答案
        String answer = (String) req.getSession().getAttribute("VerifyCode");// 通过session获取标准答案
        if ((userService.VerifyUser(username, password)) && (re.equals(answer))) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(300 * 60);// 当前会话持续时间 单位秒
            resp.sendRedirect("PostlistServlet?role=1");
        } else
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}
