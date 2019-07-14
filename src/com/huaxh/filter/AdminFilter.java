package com.huaxh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    private FilterConfig config;

    public void destroy() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String noFilterPaths = config.getInitParameter("noFilterPaths");// 获取web.xml中配置的初始化参数
        if (noFilterPaths != null) {
            String[] noFilter = noFilterPaths.split(";");
            for (String path : noFilter) {
                if (path == null || "".equals(path)) {
                    continue;
                }
                if (req.getRequestURI().contains(path) == false) { // 匹配到不需要过滤的路径
                    chain.doFilter(request, response); // yunxu fangxing
                    return;
                }
            }
        }
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            chain.doFilter(request, response);// 允许放行
        } else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
