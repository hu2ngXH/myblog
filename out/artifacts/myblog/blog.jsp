<%--
  Created by IntelliJ IDEA.
  User: hrz
  Date: 2019/7/12
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Huazh</title>
        <%
            String context = request.getContextPath();
        %>
        <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link rel="shortcut icon" href="<%=context %>/img/favicon.ico"/>
        <link rel="stylesheet" href="<%=context %>/css/blog.css"/>
        <link rel="stylesheet" href="<%=context %>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=context %>/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=context %>/css/pagination.css">
        <script src="<%=context %>/js/jquery.min.js"></script>
        <script src="<%=context %>/js/blog.js"></script>
        <script src="<%=context %>/js/bootstrap.min.js"></script>
        <script src="<%=context %>/js/jquery.pagination.js"></script>
        <style type="text/css">
            a:link {
                text-decoration: none;
            }

            a:visited {
                text-decoration: none;
            }

            a:hover {
                text-decoration: none;
            }

            a:active {
                text-decoration: none;
            }

            body {
                background-image: url("./img/home.jpg");
                background-color: #e2e2e2;
                background-repeat: no-repeat;
                background-position: center;
            }
        </style>
        <script>
            // 点击分页按钮以后触发的动作
            function handlePaginationClick(new_page_index, pagination_container) {
                $("#postForm").attr("action", "<%=context %>/PostlistServlet?role=0&pageNum=" + (new_page_index + 1));
                $("#postForm").submit();
                return false;
            };
            //当文档载入完毕就可以执行
            $(function () {
                $("#News-Pagination").pagination(${result.totalRecord}, {
                    items_per_page:${result.pageSize}, // 每页显示多少条记录
                    current_page: ${result.currentPage} -1, // 当前显示第几页数据
                    num_display_entries: 3, // 分页显示的条目数 连续分页主体部分显示的分页条目数
                    next_text: "下一页",
                    prev_text: "上一页",
                    num_edge_entries: 2, // 连接分页主体，两侧显示的首尾分页的条目数
                    callback: handlePaginationClick
                });
            })
        </script>
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
                    <a href="<%=context%>/PostlistServlet?role=1">
                        <li>控制台</li>
                    </a>
                </ul>
            </div>
        </div>
        <div id="wrap">
            <div id="top">
                <div class="info">
                    <div class="bg-title">
                        Huazh Blog
                    </div>
                    <div class="md-title">
                        BUILD CODING WORLD
                    </div>
                </div>
            </div>
            <div id="main">
                <form id="postForm" method="POST" action="<%=context %>/PostlistServlet">
                    <div class="container main-inner">
                        <div class="row">
                            <div class="article-wrap col-md-10 col-md-offset-1 col-xs-12">
                                <c:forEach items="${result.dataList }" var="article">
                                    <article class="index-article">
                                        <div class="post-info">
                                            <h2>
                                                <a href="<%=context %>/PostlistServlet?role=2&id=${article.id}">${article.title }</a>
                                            </h2>
                                            <div class="post-detial">
                                                <span>${article.sCategoryName}</span>
                                                <span>${fn:substring(article.createdDate,0,10)}</span>
                                            </div>
                                        </div>
                                        <p>${article.digest }</p>
                                        <center>
                                            <button class="more"><a
                                                    href="<%=context %>/PostlistServlet?role=2&id=${article.id}"
                                                    style="color: #000;">Read More</a></button>
                                        </center>
                                    </article>
                                </c:forEach>
                                <div id="News-Pagination"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
</body>
</html>
