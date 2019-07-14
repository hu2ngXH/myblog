<%--
  Created by IntelliJ IDEA.
  User: hrz
  Date: 2019/7/14
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>Huazh</title>
        <%
            String context = request.getContextPath();
        %>
        <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
              name="viewport">
        <meta name="keywords" content=""/>
        <meta name="description" content=""/>
        <link rel="shortcut icon" href="<%=context %>/img/favicon.ico"/>
        <link rel="stylesheet" href="<%=context %>/css/blog.css"/>
        <link rel="stylesheet" href="<%=context %>/css/page.css">
        <link rel="stylesheet" href="<%=context %>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=context %>/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=context %>/css/prism.css">
        <link rel="stylesheet" href="<%=context%>/css/editormd.css"/>
        <script src="<%=context%>/js/editormd.min.js"></script>
        <script src="<%=context %>/js/jquery.min.js"></script>
        <script src="<%=context %>/js/blog.js"></script>
        <script src="<%=context %>/js/bootstrap.min.js"></script>
        <script src="<%=context %>/js/prism.js"></script>
    </head>
    <body>
        <div id="bar" class="scrollbar"></div>
        <div id="gotop"></div>
        <div id="switch">
            <div id="iconfixed">
                <div class="icon"></div>
            </div>
        </div>
        <div id="left-nav">
            <div class="author-nav">
                <img src="<%=context %>/img/avatar.jpg" alt="个人头像">
            </div>
            <div class="main-nav">
                <ul>
                    <a href="<%=context %>/index.html">
                        <li>返回主页</li>
                    </a>
                    <a href="#">
                        <li>博客首页</li>
                    </a>
                    <c:forEach items="${mainCategory}" var="maincatetory">
                        <c:if test="${maincatetory.name!='工程'}">
                            <a href="javascript:void(0)" class="havasub">
                                <li>${maincatetory.name }</li>
                            </a>
                            <ul class="submenu">
                                <c:forEach items="${maincatetory.sublist}" var="subcatetory">
                                    <a href="PostlistServlet?role=0&sub_id=${subcatetory.id}">
                                        <li>
                                                ${subcatetory.name}
                                        </li>
                                    </a>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </c:forEach>
                    <a href="<%=context %>/PostlistServlet?role=1">
                        <li>控制台</li>
                    </a>
                </ul>
            </div>
        </div>
        <div id="wrap">
            <div id="main">
                <div class="container main-inner">
                    <div class="row">
                        <c:forEach items="${result.dataList }" var="article">
                            <div class="col-md-8 col-md-offset-2 col-xs-12">
                                <div class="single-title"><h2>${article.title }</h2></div>
                                <div class="single-info">
                                    发表于${fn:substring(article.createdDate,0,10)}&nbsp;
                                </div>
                                <div id="single-content">${article.htmlContent}</div>
                                <script type="text/javascript">
                                    $(function () {
                                        editormd.markdownToHTML("single-content", {
                                                htmlDecode: "style,script,iframe",
                                                emoji: true,
                                                taskList: true,
                                                tex: true,
                                                flowChart: true,
                                                sequenceDiagram: true
                                            }
                                        );
                                    })
                                </script>
                                <br>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
