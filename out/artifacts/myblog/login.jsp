<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <%
            String context = request.getContextPath();
        %>
        <meta charset="UTF-8">
        <title>Huazh</title>
        <link rel="shortcut icon" href="<%=context%>/img/favicon.ico"/>
        <link href="<%=context%>/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=context%>/css/login.css" rel="stylesheet">
        <script src="<%=context%>/js/jquery.min.js"></script>

    </head>

    <body>
        <div id="main">
            <div class="container wrap">
                <div class="row col-md-4 col-md-offset-4">
                    <form action="<%=context%>/LoginServlet" method="post">
                        <h2>Login</h2>
                        <input type="text" id="username" class="form-control" placeholder="用户名" name="username"
                               required>
                        <input type="password" id="password" class="form-control" placeholder="密码" name="password"
                               required>
                        <div class="img-wrap">
                            <input type="text" id="VerifyCode" class="form-control" placeholder="验证码" name="VerifyCode"
                                   required>
                            <img onclick="reloadCode()" src="<%=context%>/ImgServlet" id="img"/>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">登录</button>
                        <a href="index.html">返回博客</a>
                    </form>
                    <script>
                        function reloadCode() {
                            $("#img").src = "/ImgServlet?" + Math.random();
                        }
                    </script>
                </div>
            </div>
        </div>
    </body>

</html>