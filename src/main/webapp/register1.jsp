    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8"/>
        <title>后台管理系统</title>
        <meta name="author" content="DeathGhost" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!--[if lt IE 9]>
        <script src="js/html5.js"></script>
        <![endif]-->
        <script src="js/jquery.js"></script>
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
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
        </aside>

        <section class="rt_wrap content mCustomScrollbar">
        <h2><strong style="color:grey;">
		注册<c:if test="${result=='1'}">成功</c:if>
		<c:if test="${result=='0'}">失败</c:if></strong></h2>
        <ul class="ulColumn2">
        <li>
        <span class="item_name" style="width:120px;">登录账户：</span>
        ${username}
        </li>
        <li>
            <span class="item_name" style="width:120px;">登录密码：</span>
            ${password}
        </li>
		<li>
            <span class="item_name" style="width:120px;">注册提示：</span>
            ${loginfo}
        </li>
        <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="submit" class="link_btn" value="确认"/>
        </li>
        </ul>
        </section>


        </body>
        </html>
