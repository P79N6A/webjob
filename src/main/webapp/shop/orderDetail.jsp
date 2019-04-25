    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
        <head>
        <title></title>
        <meta charset="utf-8">
        <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" href="css/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="css/ui-box.css">
        <link rel="stylesheet" href="css/ui-base.css">
        <link rel="stylesheet" href="css/ui-color.css">
        <link rel="stylesheet" href="css/appcan.icon.css">
        <link rel="stylesheet" href="css/appcan.control.css">
        <link rel="stylesheet" href="css/newStyle.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        </head>
        <body class="um-vp " ontouchstart>
        <div id="page_0" class="up ub ub-ver bc-bg" tabindex="0">
        <!--header开始-->
        <div id="header" class="uh ub">
        <div class="nav-btn green_color ulev1" id="nav-left" onclick="location.href='<%=request.getParameter("url")%>'">
        <div class="fa fa-angle-left fa-2x "></div>
        <div class="ub ub-ac ub-pc umar-l">返回</div>
        </div>
        <h1 class="ut ub-f1 ulev1 ut-s tx-c" tabindex="0">订单详情</h1>
        <div class="nav-btn nav-bt green_color" id="nav-right">
        <!-- <div class="fa fa-ellipsis-h ulev2"></div> -->
        </div>
        </div>
        <!--header结束--><!--content开始-->
        <section class="ub ub-ver ub-f1 ub-fv sc-bg" style="overflow-y: auto">

        <div class="ub-ver umar-t">

        <div class="ub-ver">
        <div class="ub ub-f1 statusBg03 ub-ver uinn">
        <div class="ub ub-f1 ulev1" style="margin-top:2em;margin-left:2em">交易成功</div>
        <!-- <div class="ub ub-f1" style="margin-left: 2.4em">还剩<span>1</span>天<span>11</span>小时自动确认</div> -->
        </div>
        <div class="ub ub-f1 uinn bc-bg umar-t ub-ver addressItem" id="222" data-orderid = '222222222'>
        <div class="ub ub-f1 uinn">
        <div class="ub ub-f1">收货人：${addr.truename}</div>
        <div class="ub ub-f1 ub-pe">${addr.telephone}</div>
        </div>
        <div class="ub ub-f1 uinn">收获地址：${addr.cityname}&nbsp;${addr.areainfo}</div>
        </div>
        <div class="ub umar-t sc-bg ub-ver">
        <div class="ub border_b ub-ver uinn bc-bg">
        <div class="ub ub-f1 umar-l umar-t">
        <div class="">订单编号：</div>
        <div class="ub ub-f1">${order.orderNo}</div>
        </div>
        <div class="ub ub-f1 umar-l umar-t">
        <div class="">订单金额：</div>
        <div class=" green_color">￥${order.totalprice}</div>
        </div>
        <div class="ub ub-f1 umar-l umar-t umar-b">
        <div class="">下单时间：</div>
        <div class="">${order.addtime}</div>
        </div>
        </div>
        

        <c:forEach var="obj" items="${odList}">

            <div class="ub ub-f1 uinn bc-bg ub-ver umar-b">
            <div class="ub ub-f1">
            <div class="ub umar-r uinn">
            <img src="${obj.goods.icon}" style="width:6em;height:6em;"/>
            </div>
            <div class="ub ub-f1 uinn ub-ver ub-pc">
            <div class="ub ub-f1">
            <div class="ub ub-f1 ulev1 ub-ac">${obj.goods.goodsName}</div>
            <div class="ub ub-f1 ub-ac green_color ub-pe">￥${obj.goods.goodsPrice}</div>
            </div>
            <div class="ub ub-f1 deep_gray_c">
            <div class="ub ub-f1 ub-ac">${obj.goods.standard}</div>
            <c:if test="${obj.goods.goodsPrePrice != ''&&obj.goods.goodsPrePrice !=null}">
            <div class="ub ub-f1 ub-ac ub-pe" style="text-decoration: line-through">￥${obj.goods.goodsPrePrice}</div>
            </c:if>
            </div>
            <div class="ub ub-f1 umar-t">

            <div class="ub ub-ac green_color uba umw4 ub-ac ub-pc ulev-1" style="height:1.4em"> <c:if test="${obj.gameName != ''&&obj.gameName !=undefined}">满  减</c:if>&nbsp;</div>
            <div class="ub ub-f1 ub-ac ub-pe">x ${obj.num}</div>
            </div>
            </div>
            </div>

            </div>
        </c:forEach>

        </div>
        </div>
        <!--有订单 end-->

        </div>

        </section>
        </div>
        <script src="js/appcan.js"></script>
        <script src="js/appcan.control.js"></script>
        </body>

        </html>