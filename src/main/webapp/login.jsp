<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
    <head>
        <title>首页</title>
        <meta charset="utf-8">
        <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" href="css/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="css/ui-box.css">
        <link rel="stylesheet" href="css/ui-base.css">
        <link rel="stylesheet" href="css/ui-color.css">
        <link rel="stylesheet" href="css/appcan.icon.css">
        <link rel="stylesheet" href="css/appcan.control.css">
        <link rel="stylesheet" href="css/newStyle.css" />
        
        <script src="../js/jquery.js"></script>
        <script src="../js/regex.js"></script>
		<script type="text/javascript">

		//弹出提示信息
		function showTipTitle(args0,args){
			alert(args);
		}
		
		
		$(function(){
			//忘记密码
			$("#client_resetpass").click(function(){
				location.href='/client_resetpass';
			});
			
			//注册
			$("#register,#nav-right").click(function(){
				location.href='/register';
			});
		});
		
		//是否是微信登录
		function isWeiXin(){
			try{
				var ua = window.navigator.userAgent.toLowerCase();
				if(ua.match(/MicroMessenger/i) == 'micromessenger'){
					return true;
				}else{
					return false;
				}
			}catch(exception){
				//alert('ddd');
			}
			return false;
		}
		
		$(function(){
			if(isWeiXin()){//如果是微信登录 则直接跳转到授权界面
				$("#weixinkehuduan").show();
				location.href='/weixinLogin';
			}
		});
		
		</script>
    </head>
    <body class="ub ub-fh ub-fv ub-ver ub-f1 bc-bg" ontouchstart>
			<!--如果已经登录了，则跳转到首页-->
			<c:if test="${logclient != null }">
				<!--<c:redirect url="/mine_index"/>-->
			</c:if>
			
            <!--header开始-->
            <!--header开始-->
            <div id="header" class="uh ub border_b">
                <div class="nav-btn green_color ulev1" id="nav-left" onClick="location.href='mine_index'">
                    <div class="fa fa-angle-left fa-2x "></div>
                    <div class="ub ub-ac ub-pc umar-l">返回</div>
                </div>
                <h1 class="ut ub-f1 ut-s tx-c ulev1" tabindex="0">登录</h1>
                <div class="nav-btn nav-bt green_color" id="nav-right">注册</div>
            </div>
            <!--header结束-->
            <!--content开始-->
            <section class="ub ub-ver ub-f1 ub-fv bc-bg uinn2" style="overflow-y: auto;margin-top:2em">
			    <span id="weixinkehuduan" style="display:none;color:#FF0000" >&nbsp;&nbsp;微信客户端打开自动登录中...</span>
                <form id="form1" action="client_login1" method="post">
                    <div class="umar-a">
                        <div class="ub ub-ac umh5 border_b phone_num" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请输入您的手机号" maxlength="11" type="text" class="ub-f1 umh4" onChange="btnChange()" id="username" name="username" value="${username}">
                            </div>
                        </div>
                        <div class="ub ub-ac umh5 bc-border password" style="padding-left:3em">
                            <div class=" uinput ub ub-f1">
                                <input placeholder="请输入您的密码" type="password" class="umh4 ub-f1" onChange="btnChange()" id="password" name="password">
                            </div>
                        </div>
                    </div>
                    <div class="uinn">
                        <div class="ub ub-ac bc-text-head ub-pc uc-a1 mybtn_gray umh4"  id="login">
                            登录
                        </div>
                    </div>
                   <div class="uinn" id="register">
                        <div class="ub ub-ac bc-text-head ub-pc uc-a1 mybtn_view umh4">
                            注册
                        </div>
                    </div>
            </form>
            <div class="ub uinn">
                <div class="ub ub-f1 uinn ub-pe" ><a class="ubb" id="client_resetpass" style="border-color:rgb(104,174,0);color:rgb(104,174,0); ">忘记密码？</a></div>
            </div>
         
            </section>
            <!--content结束-->
            <footer id="footer"  class="ub ubt basic-border">
            </footer>
			<c:if test="${result == 'false' }">
			 <script>
			 alert('${message}');
			 </script>
			</c:if>
    </body>
    <script>
        window.onload = function(){                 
            //$("#username").focus();
        }
        function btnChange()
        {
            if(0 !=$('#username').val().toString())
            {
                if(!isPhone($("#username").val()))
                {
                    $('#login').addClass('mybtn_gray');
                    $('#login').removeClass('mybtn');
                    $('#login').unbind('click');
                    alert("请输入正确的手机号码！");
                }
                else
                {
                    $('#login').removeClass('mybtn_gray');
                    $('#login').addClass('mybtn');
                    $("#login").on('click',function(){
                        //alert("登录！");
						$("#form1").submit();
                    });
                }
                
            }
            else
            {
                $('#login').addClass('mybtn_gray');
                $('#login').removeClass('mybtn');
                $('#login').unbind('click');
            }
        }
        
		$(function(){
			btnChange();
		});
    </script>
</html>