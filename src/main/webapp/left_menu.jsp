<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
  <header>
        <h1><img src="images/admin_logo.png"/></h1>
        <ul class="rt_nav">
        <li><a href="hello.jsp" class="quit_icon">更新说明</a></li>
        <li><a href="#" class="set_icon">密码修改</a></li>
        <li><a href="logout" class="quit_icon">安全退出</a></li>
        </ul>
        </header>
 <aside class="lt_aside_nav content mCustomScrollbar">
            <h2><a href="hello">起始页</a></h2>
            <ul>
            <li>
            <dl>
            <dt>商品信息</dt>
            <!--当前链接则添加class:active-->
            <dd><a href="goods_list" class="active">商品列表</a></dd>
            <dd><a href="goodsClass_list">商品分类</a></dd>
            <dd><a href="game_list">优惠策略</a></dd>
            </dl>
            <dl>
            <dt>系统管理</dt>
            <dd><a href="parameter_list">参数管理</a></dd>
            <dd><a href="source_list">素材管理</a></dd>
            </dl>
			<dl>
            <dt>订单管理</dt>
            <dd><a href="/admin_order_list">订单管理</a></dd>
            <dd><a href="/refunds_list">退款管理</a></dd>
            </dl>
			<dl>
            <dt>会员管理</dt>
			<dd><a href="new_client_list?ftGradeType=-1">会员管理</a></dd>
			<dd><a href="balancerecord_list?ft_sysed=1&ft_amountType=0">现金券/余额记录</a></dd>
			<dd><a href="drawcash_list">提现记录</a></dd>
            </dl>
			<dl>
            <dt>其他管理</dt>
			<dd><a href="grade_list">会员等级管理</a></dd>
			<dd><a href="freightModel_list">运费模板管理</a></dd>
			<dd><a href="helpcenter_list">帮助中心</a></dd>
			<dd><a href="suggest_list">意见反馈</a></dd>
            <dd><a href="log_list">日志</a></dd>
            </dl>
			
            </li>

            </ul>
            </aside>