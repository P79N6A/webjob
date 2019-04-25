<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
    <head>
		<meta charset="UTF-8">
        <title>我的地址管理</title>
        
		<%@ include file="../public/head.jsp" %>
		<%@ include file="../public/meta.jsp" %>
    <%
        session.setAttribute("fromUrl","address_select");

        %>
	</head>
<body class="ub ub-fh ub-fv ub-ver ub-f1 bc-bg" ontouchstart>
            <!--header开始-->
            <header id="header" class="uh ub bc-bg border_b head">
                <div class="ub uinn green_color" id="nav-left"  onclick="location.href='${sessionScope.from}?id=${sessionScope.from_id}&num=${sessionScope.from_num}'">
                    <div class="fa fa-angle-left fa-2x "></div>
                    <div class="ub ub-ac ub-pc umar-l">返回</div>
                </div>
                <div class="uinn ub ub-f1">
                    <div class="search_c uinput ub basic-border uba uc-a2 ub-f1">
                        <div class="uinn fa fa-search search_fontc"></div>

                    </div>
                </div>
                <div class="nav-btn nav-bt green_color" id="nav-right">
                    管理
                </div>
            </header>
            <!--header结束-->
            <!--content开始-->
            <section class="ub ub-ver ub-f1 ub-fv sc-bg" style="overflow-y: auto">
                <div class="ub-ver" id="myAddress"> 
					<c:forEach var="obj" items="${list}">
						<c:if test="${obj.defaultstate == '1' }">
							<div class="ub ub-f1 uinn bc-bg umar-t ub-ver selected addressItem" data-orderid = '${obj.id}'>
								<div class="ub ub-f1 uinn">
								<div class="ub ub-f1">${obj.truename}</div>
								<div class="ub ub-f1 ub-pe">${obj.mobile}</div>
								</div>
								<div class="ub ub-f1 uinn">${obj.cityname}</div>
								<div class="ub ub-f1 uinn">${obj.areainfo}</div>
							</div>
						</c:if>
						<c:if test="${obj.defaultstate != '1' }">
							<div class="ub ub-f1 uinn bc-bg umar-t ub-ver addressItem" data-orderid = '${obj.id}' >
								<div class="ub ub-f1 uinn">
								<div class="ub ub-f1">${obj.truename}</div>
								<div class="ub ub-f1 ub-pe">${obj.mobile}</div>
								</div>
								<div class="ub ub-f1 uinn">${obj.cityname}</div>
								<div class="ub ub-f1 uinn">${obj.areainfo}</div>
							</div>
						</c:if>
					</c:forEach>
                </div>
            </section>

    <!--content结束-->
    <footer id="footer"  class="ub sc-bg basic-border">
    <div class="ub ub-f1 uinn ub-ac ub-pc">
    <div class="ub uinn addAddressBtn ub-ac ub-pc" onClick="location.href='/shop_address_add'">新增收货地址</div>
    </div>

    </footer>

    </body>
    <script>
 

     
       $('#nav-right').on('click',function(){
           $('.addressItem').unbind("click");
           $('.addressItem').removeClass('selected');
          // $(".addressItem").attr("onclick","editAddress(event)");
       });
       
     
	   
	   
	   $('.addressItem').on('click',function(){
	
	var obj = $(this);
    var id  = $(this).data('orderid');
	
		 $.ajax({
             type: "POST",
             url: "selAddr",
              data:{  
                id : id,
                from: '${from}',
             },  
             dataType: "json",
             success: function(data){
			         //alert(data);
				   if(data.result == 0)
				   {
				    location.href =data.data+"?id=${id}&num=${num}";
				   }else if(data.result == -1002)
				   {
					   location.href = "client_login";
                   }
                      },
			error : function() {  
          
                    alert("网络异常！");  
                    }  
         });      
    
});
        
    </script>
</body>
</html>
