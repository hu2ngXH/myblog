<%--
  Created by IntelliJ IDEA.
  User: hrz
  Date: 2019/7/13
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%
            String context = request.getContextPath();
        %>
        <link rel="shortcut icon" href="<%=context%>/img/favicon.ico"/>
        <title>success</title>
    </head>
    <body>
        <h1>操作成功！</h1>
        <a href="index.html">返回主页</a>
    </body>
</html>
