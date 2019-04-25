    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8"/>
        <title>购物车</title>
        <meta name="author" content="DeathGhost" />
        <link rel="stylesheet" type="text/css" href="../css/style.css" />
        <!--[if lt IE 9]>
        <script src="js/html5.js"></script>
        <![endif]-->
        <script src="../js/jquery.js"></script>
        <script src="../js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script>
        (function($){
        $(window).load(function(){

        $("a[rel='load-content']").click(function(e){
        e.preventDefault();
        var url=$(this).attr("href");
        $.get(url,function(data){
        $(".content .mCSB_container").append(data); //load new content inside .mCSB_container
        //scroll-to appended content
        $(".content").mCustomScrollbar("scrollTo","h2:last");
        });
        });

        $(".content").delegate("a[href='top']","click",function(e){
        e.preventDefault();
        $(".content").mCustomScrollbar("scrollTo",$(this).attr("href"));
        });

        });
        })(jQuery);
        </script>
        </head>
        <body>
        <!--header-->
        <header>
        <h1><img src="images/admin_logo.png"/></h1>
        <ul class="rt_nav">
        <li><a href="http://www.baidu.com" target="_blank" class="website_icon">站点首页</a></li>
        <li><a href="#" class="admin_icon">DeathGhost</a></li>
        <li><a href="#" class="set_icon">账号设置</a></li>
        <li><a href="login.php" class="quit_icon">安全退出</a></li>
        </ul>
        </header>
            <!--aside nav-->
            <aside class="lt_aside_nav content mCustomScrollbar">
            <h2><a href="index.php">起始页</a></h2>
            <ul>
            <li>
            <dl>
            <dt>商品信息</dt>
            <!--当前链接则添加class:active-->
            <dd><a href="goods_list" class="active">商品列表</a></dd>
            <dd><a href="goodsClass_list">商品分类</a></dd>
            <dd><a href="#">商品属性</a></dd>
            <dd><a href="#">品牌管理</a></dd>
            </dl>
            </li>

            </ul>
            </aside>

            <section class="rt_wrap content mCustomScrollbar">
            <a href="goods_add" class="link_btn" >新增商品</a>
        <table class="table">
        <tr >
        <th>创建时间</th>
        <th>商品名称</th>
        <th>价格</th>
        <th>类别</th>
        </tr>
        <c:forEach var="obj" items="${list}">
        <tr>
        <th>${obj.addTime}</th>
        <th>${obj.goodsName}</th>
        <th>${obj.goodsPrice}</th>
        <th>${obj.gcId}</th>
        </tr>
        </c:forEach>
        </table>


            </section>
          
            <%@ include file="../public/foot.jsp" %>
        </body>
        </html>
