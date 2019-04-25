<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
    <head>
		<meta charset="UTF-8">
        <title>注册</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ include file="public/meta.jsp" %>
		<script src="js/regex.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#gotologin").click(function(){
				location.href='/client_login';
			});
		});
		</script>
	</head>
<body class="ub ub-fh ub-fv ub-ver ub-f1 bc-bg" ontouchstart>
            <!--header开始-->
            <!--header开始-->
            <div id="header" class="uh ub border_b">
                <div class="nav-btn green_color ulev1" id="nav-left" onClick="location.href='mine_index'">
                    <div class="fa fa-angle-left fa-2x "></div>
                    <div class="ub ub-ac ub-pc umar-l">返回</div>
                </div>
                <h1 class="ut ub-f1 ut-s tx-c ulev1" tabindex="0">手机注册</h1>
                <div class="nav-btn nav-bt green_color" id="nav-right" ></div>
            </div>
            <!--header结束-->
            <!--content开始-->
            <section class="ub ub-ver ub-f1 ub-fv bc-bg uinn2" style="overflow-y: auto;margin-top:2em">
				<form name="form1" action="addregister1" method="post" onSubmit="return checkPass()">
                    <div class="umar-a">
                        <div class="ub ub-ac umh5 border_b phone_num" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请输入您的手机号" maxlength="11" type="text" class="ub-f1 umh4" onKeyUp="btnChange()" id="phone" name="username" value="${username}">
                            </div>
                        </div>
                        <div class="ub ub-ac umh5 border_b yanzm" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请输入验证码" maxlength="11" type="text" class="ub-f1 umh4" name="verification_code" value="" >
                            </div>
                            <div class="ub uinn deep_gray_c" id="verifyNum">获取验证码</div>
                            <div class="ub uinn deep_gray_c myhide">重新获取     <span class="green_color">78</span> 秒</div>
                        </div>
                        <div class="ub ub-ac umh5 border_b password" style="padding-left:3em">
                            <div class=" uinput ub ub-f1">
                                <input placeholder="请输入您的密码" type="password" class="umh4 ub-f1" id="password" name="password" value="">
                            </div>
                            <div class="ub uinn password_hide password_view=" id="viewPw"></div>
                        </div>
                        <div class="ub ub-ac umh5 border_b invite_num" style="padding-left:3em">
                            <div class=" uinput ub ub-f1">
                                <input placeholder="我的邀请码（选填）" class="umh4 ub-f1" name="invitedCode" value="${sessionScope.invitedCode}">
                            </div>
                        </div>
                    </div>
                    <div class="uinn">
                        <div class="ub ub-ac bc-text-head ub-pc uc-a1 mybtn_gray umh4"  id="login" >
                            完成注册
                        </div>
                    </div>
                    <div class="uinn">
                        <div class="ub ub-f1 uinn ub-ac ub-pc">
                            <span class="fa fa-check-circle-o ulev2 green_color"></span>
                            <span class="gray_c">注册即为我同意</span>
                            <a class="ubb" style="border-color:rgb(104,174,0);color:rgb(104,174,0); ">无忧土味注册协议</a>
                        </div>
						<div class="ub ub-f1 uinn ub-ac ub-pc">
                            <a class="ubb" style="border-color:rgb(104,174,0);color:rgb(104,174,0); cursor:pointer " id="gotologin">已有账号，去登录</a>
                        </div>
                    </div>
            </form>
            
			<c:choose>
				<c:when test="${result == 'false'}">
					<script  type="text/javascript">
						alert(' 注册失败！失败原因：${loginfo}');
					</script>
				</c:when>
				<c:when test="${result == 'true'}">
					<script  type="text/javascript">
						alert(' 注册成功，去登录');
						window.location.href='client_login';
					</script>
				</c:when>
			</c:choose>
			
			
           
            </section>
			
            <!--content结束-->
            <footer id="footer"  class="ub ubt basic-border">

   
            </footer>
    </body>
    <script src="js/project/register.js"></script>
    <script>
        window.onload = function(){                 
            $('#password').togglePassword({
                el: '#viewPw'
            });

        }
        
        
        var isHide = true;
        $('#viewPw').on('click',function(){
            var input =  $(this);
            if('' ==$('#password').val() || 0 == $('#password').val().length)
            {
                return;
            }
            else
            {
                if(isHide)
                {
                    $(this).addClass('password_view');
                    $(this).removeClass('password_hide');
                    $(input).attr('type','text');
                    isHide = false;
                }
                else
                {
                    $(this).removeClass('password_view');
                    $(this).addClass('password_hide');
                    $(input).attr('type','password');
                    isHide = true;
                }
            }
            
        });
		
		$(function(){
			btnChange();
		});
    </script>
</html>
