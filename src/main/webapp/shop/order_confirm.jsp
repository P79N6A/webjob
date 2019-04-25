<%@ page language="java" contentType="text/html; charset=UTF-8" import="org.apache.commons.lang.StringUtils" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
    <head>
        <title>分类</title>
        <meta charset="utf-8">
        <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" href="../css/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="../css/ui-box.css">
        <link rel="stylesheet" href="../css/ui-base.css">
        <link rel="stylesheet" href="../css/ui-color.css">
        <link rel="stylesheet" href="../css/appcan.icon.css">
        <link rel="stylesheet" href="../css/appcan.control.css">
        <link rel="stylesheet" href="../css/newStyle.css" />
		<link rel="stylesheet" href="../css/switch.css" />
        <%@ include file="../public/meta.jsp" %>
    <style>
    .bc-head{
    background-color: rgb(104,174,0);
    }
    </style>
        <script src="js/jquery.min.js"></script>
    </head>
    <body class="ub ub-fh ub-fv ub-ver ub-f1 bc-bg" ontouchstart>
            <!--header开始-->
            
            <div id="header" class="uh ub border_b umh6 ub-ac">
                <div class="nav-btn green_color" id="nav-left"  onclick="location.href='${preUrl}&url=<%=request.getParameter("url")%>&rank=<%=StringUtils.isEmpty(request.getParameter("rank"))?"0":request.getParameter("rank")%>&classId=<%=StringUtils.isEmpty(request.getParameter("classId"))?"0":request.getParameter("classId")%>'">
                    <div class="fa fa-angle-left fa-2x"></div>
                    <div class="ub umar-l">返回</div>
                </div>
                <h1 class="ut ub-f1 ulev-3 ut-s tx-c" tabindex="0">确认订单</h1>
                <div class="nav-btn nav-bt" id="nav-right"></div>
            </div>
            <!--header结束-->
            <!--content开始-->
            
                <div class="ub ub-f1 ub-ver sc-bg " style="overflow-y: scroll">
                    <!--无地址显示 开始   如果要隐藏则添加类：myhide -->
                      <c:if test="${isAddr == '0'}">
                    <div id="noAddr" class="ub border_b bc-bg umar-b uinn ub-ac ub-pc confirmAddress" >
                          <div class="ub ub-ac ub-pc address umh4">您还没有添加收货人信息 <span class="green_color"><a href="address_select?from=${from}&id=${id}&num=${num}">请添加</a></span></div>
                    </div>
                    </c:if>
                    <!--无地址显示 结束-->
                     <c:if test="${isAddr != '0'}">
                    <!--有地址显示 开始-->
                    <div class="ub border_b bc-bg umar-b uinn umh6 ub-ac confirmAddress" id="youAddr" onClick="location.href='address_select?from=${from}&id=${id}&num=${num}'">
                         <div class="ub ub-f1 ub-ver uinn">
                         <div class="ub umh5">
                                <div class="ub">${addr.truename}</div>
                                <div class="ub">${addr.mobile}</div>
                            </div>
                            <div class="ub ub-f1 umar-t">${addr.areainfo}</div>
                        </div>
                         <div class="ub deep_gray_c umar-l" style="font-size: 2.4em"><span class="fa fa-angle-right"></span></div>
                    </div>
                    </c:if>
                    <!--有地址显示 结束-->
                     <form name="form1"  action="orderConfirm1" method="post">
                    <div class="ub bc-bg uinn umh4 ub-ac umar-b umh5 umar-t">
                        <div class="ub uinn" >支付方式</div>
                        <div class="ub-f1 tx-r ulev-app1 sc-text">在线支付</div>
                    </div>
                    <div class="ub ub-ver bc-bg uinn umar-b umar-t">
                        <div class="ub ub-f1 umar-b uinn" >配送方式：<span>${modelName}</span></div>
                        <div class="ub ub-f1 uinn">运费：<span>￥${modelPrice}</span></div>
                    </div>
                    <input type="hidden" name="deliverNum" value="${modelPrice}">
                     <div class="ub ub-pj uinn bc-bg border_b umh5 umar-t">
                        <div class="ub uinn ub-ac">可提现金券</div>
                        <div class="ub  ub-ac"><span class="green_color ub-ac ub">0</span><span class='fa fa-angle-right umar-l ulev2'></span></div>
                    </div>

                    <div class="bc-bg border_b">
                        <div class="ub ub-pj uinn border_b ub-ac" style="margin-left: 1.2em">
                            <div class="ub uinn">可提现金券支付</div>
    <div class="ub">
    <label>
    <input type="checkbox" name="yue" id="yue" class="ios-switch green bigswitch"  onChange="calMoney()" checked />
    <div><div></div></div>
    </label>
    </div>
    </div>
                        <div class="ub ub-f1 umh5 uinn ub-ac deep_gray_c" style="margin-left:1.2em">余额：￥${availableBalance}元</div>
                    </div>
                     <div class="ub uinn border_b ub-ac bc-bg umh5">
                        <div class="ub ub-f1 uinn">使用现金券</div>
                       
                        <div class="ub">
                            <label>
                                <input type="checkbox" name="xjq" id="xjq" class="ios-switch green bigswitch" onChange="calMoney()" checked />
                                <div><div></div></div>
                            </label>
                        </div>
                    </div>
                    <c:if test="${isAddr != '0'}">
                    
                    <input type="hidden" name="addrId" value="${addr.id}">
                    </c:if>
                    </form>
                     <div class="ub ub-pj uinn border_b ub-ac bc-bg umar-b deep_gray_c umh5">
                        <div class="ub uinn">现金券余额：<span>${freezeBlance}</span>元</div>
                        <div class="ub ub-ac">本次抵用：<span>￥${freezVal}</span>元</div>
                    </div>  
                </div>
            <!--content结束-->
              <footer id="footer"  class="ub ubt basic-border">
            
                <div class="ub ub-f1 border_b ub-ac ub-pj">
                    <div class="ub umar-l ub-ver ub-pc">
                        <div class="ub ulev1">总计：<span class="green_color" id="total">${last_money}</span>元</div>
                        <div class="ub ulev-1">商品金额：${total_money}元（不含运费,满减优惠:${order.manjianBenfit}元）</div>
                    </div>
                    <c:if test="${isAddr == '1'}">
                    <div class="ub umh5 uba ub-ac uinn1 bc-text-head clickEdit" style="background-color: rgb(104,174,0);" id="submit" onClick="form1.submit()">提交订单</div>
                    </c:if>
                     <c:if test="${isAddr == '0'}">
                    <div class="ub umh5 uba ub-ac uinn1 bc-text-head clickEdit" style="background-color: rgb(104,174,0);" id="submit" onClick="alert('请先添加收货地址')">提交订单</div>
                    </c:if>
                </div>
           
                
            </footer>
             <footer id="footer"  class="ub ubt basic-border">
                <div class="ub ub-f1 uinn">
                    <div class="ub ub-f1  ub-pc ub-ac ub-ver" onClick="location.href='index'">
                        <div class="ub ub-img indexImg umw1 umh1"></div>
                        <div class="ub">首页</div>
                    </div>
                    <div class="ub ub-f1  ub-pc ub-ac ub-ver" onClick="location.href='sort_list'">
                        <div class="ub ub-img sortImg umw1 umh1"></div>
                        <div class="ub">分类</div>
                    </div>
                    <div class="ub ub-f1  ub-pc ub-ac ub-ver" onClick="location.href='showCart'">
                        <div class="ub ub-img shopImg umw1 umh1"></div>
                        <div class="ub">购物车</div>
                    </div>
                    <div class="ub ub-f1  ub-pc ub-ac ub-ver green_color" onClick="location.href='/mine_index'">
                        <div class="ub ub-img meImg_active umw1 umh1"></div>
                        <div class="ub">我的</div>
                    </div>
                </div>
                
            </footer>
    <script src="../js/appcan.listview.js"></script>
    </body>
    <script src="../js/appcan.js"></script>
    <script src="../js/appcan.control.js"></script>
    <script src="../js/regex.js"></script>
    <script>
       $(function(){



            var isAddr = '${isAddr}';
			//alert(isAddr);
			//alert($("#noAddr").html());
			if("1"==isAddr)
			{
			//alert($("#noAddr").html());
			$("#noAddr").hide();  	
			
			}
			else
			$("#youAddr").hide();
			
			calMoney();
      });
        
		
		var yue = parseFloat('${availableBalance}');
		var xjq = parseFloat('${freezeBlance}');
		function calMoney()
		{
			var total = parseFloat('${last_money}');
			if($('#yue').is(':checked')) {
				total =  total - yue;
				}
			if($('#xjq').is(':checked')) {
				total =  total - xjq;
				}
			 if(total>0)	
			$('#total').html(total.toFixed(2));	
			else
			$('#total').html(0);	
			}
        
    </script>
</html>