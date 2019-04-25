<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
    <head>
        <title>重置密码</title>
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
        
        <script src="../js/jquery.min.js"></script>
        <script src="../js/regex.js"></script>
		<script type="text/javascript">
		var ver_code = "";//验证码信息
		
		$(function(){
			//判断手机号是否可用
			$("input[name='username']").blur(function(){
				//ver_code = "";
			});
		});
		
		//弹出提示信息
		function showTipTitle(args0,args){
			alert(args);
		}
		
		
		function checkPass(){//提交前验证
		//alert('wer'+ver_code+"344");
			//showTipTitle('false','cccsss!');
			var verification_code = $("input[name='verification_code']").val();
			//showTipTitle('false','23432342!'+verification_code+"----");
			if(ver_code==''){
				showTipTitle('false','请先获取验证码!');
				return false;	
			}
			if(verification_code==''){
				showTipTitle('false','验证码不能为空!');
				return false;
			}
			if(ver_code!=verification_code){
				//showTipTitle('false','验证码不正确，请重新输入!'+ver_code+"-"+verification_code+";");
				showTipTitle('false','验证码不正确，请重新输入!');
				return false;
			}
			
			var newpw = $("#newpw").val();
			if(newpw==''){
				alert('新密码不能为空.');
				return false;
			}
			
			var newpw2 = $("#newpw2").val();
			if(newpw2==''){
				alert('确认密码不能为空.');
				return false;
			}
			
			if(newpw!=newpw2){
				alert('新密码与确认密码必须一致.');
				return false;
			}
			
			form1.submit();
		}
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
                <h1 class="ut ub-f1 ut-s tx-c ulev1" tabindex="0">重置密码</h1>
                <div class="nav-btn nav-bt green_color" id="nav-right" ></div>
            </div>
            <!--header结束-->
            <!--content开始-->
            <section class="ub ub-ver ub-f1 ub-fv bc-bg uinn2" style="overflow-y: auto;margin-top:2em">
				<form name="form1" action="client_resetpass1" method="post">
                    <div class="umar-a">
                        <div class="ub ub-ac umh5 border_b phone_num" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请输入您的手机号" maxlength="11" type="text" class="ub-f1 umh4" id="phone" onKeyUp="btnChange()" name="username" value="${username}" >
                            </div>
                        </div>
                        <div class="ub ub-ac umh5 yanzm" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请输入验证码" maxlength="11" type="text" class="ub-f1 umh4" id="yanzhengma" name="verification_code" value="">
                            </div>
                            <div class="ub uinn deep_gray_c" id="verifyNum">获取验证码</div>
                            <div class="ub uinn deep_gray_c myhide">重新获取     <span class="green_color">58</span> 秒</div>
                        </div>
                        
						<div class="ub ub-ac umh5 border_b password" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请输入新密码" maxlength="11" type="password" class="ub-f1 umh4" id="newpw" name="newpw">
                            </div>
                        </div>
                        
                        <div class="ub ub-ac umh5 border_b password" style="padding-left:3em">
                            <div class="uinput ub ub-f1">
                                <input placeholder="请确认新密码" maxlength="11" type="password" class="ub-f1 umh4" id="newpw2" name="newpw2">
                            </div>
                        </div>
						
                    </div>
                    <div class="uinn">
                        <div class="ub ub-ac bc-text-head ub-pc uc-a1 mybtn_view umh4"  id="login">
                            下一步
                        </div>
                    </div>
                    
                   
            </form>
            
            <c:choose>
				<c:when test="${result == 'false'}">
					<script  type="text/javascript">
						alert(' ${loginfo}');
					</script>
				</c:when>
				<c:when test="${result == 'true'}">
					<script  type="text/javascript">
						alert(' ${loginfo}');
						//重置密码成功 跳转到登录界面
						location.href='/client_login';
					</script>
				</c:when>
			</c:choose>
            </section>
            <!--content结束-->
            <footer id="footer"  class="ub ubt basic-border">
   
            </footer>
    </body>
    <script>
        window.onload = function(){                 
            
        }
        
function btnChange()
{
    if(0 !=$('#phone').val().toString())
    {
        if(!isPhone($("#phone").val()))
        {
            $('#login').unbind('click');
            $("#verifyNum").css("color", "#848484");//不可获取验证码
            $("#verifyNum").removeAttr("onclick");
        }
        else
        {
            if(isopen)
            {
                $("#verifyNum").css("color", "rgb(104,174,0)");//获取验证码
                $("#verifyNum").attr("onclick","sendMessage()");
            }
            
            $("#login").on('click',function(){     //点击下一步的绑定事件
                checkPass();
            });
        }
    }
    else
    {
        $('#login').unbind('click');
        $("#verifyNum").css("color", "#848484");//不可获取验证码
        $("#verifyNum").removeAttr("onclick");
    }
}


var count = 30; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var id = "verifyNum";
var isopen = true;//验证码颜色是否会变，是否可点击

function sendMessage() {
	var phoneVal = $("#phone").val();
    if(!isPhone(phoneVal))
    {
        return;//启用按钮
    }
  　curCount = count;
　　//设置button效果，开始计时
     $("#"+id).css("color", "#848484");
     $("#"+id).removeAttr("onclick");
     $("#"+id).html(curCount + "秒后重新获取");
     InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
     isopen = false;
　　  //向后台发送处理数据
    /** 
    $.ajax({
     　type: "POST", //用POST方式传输
         　dataType: "text", //数据格式:JSON
         　　 url: 'Login.ashx', //目标地址
        　　  data: {},
        　    success: function (data) {
            alert("获取成功！");
        },
     　 error: function (){
            alert("获取失败！");
        }
     });
     **/
	 
	 $.ajax({
	   type: "post",
	   dataType: "json",//返回json格式的数据
	   url: "client_resetpass_getcode",
	   data: {"username":phoneVal},
	   timeout: 30000, //超时时间：30秒
	   success: function(msg){
		  if(msg.result=='true'){//获取成功取
			ver_code = msg.ver_code;
		  }
		  else{
		  	showTipTitle('false',msg.loginfo);
		  }
		  //alert("获取到的验证码："+ver_code);
		  //alert("qq:"+msg);
		  //alert("qq:"+msg.result);
	   },
	   error : function(XMLHttpRequest, textStatus, errorThrown) {
			if(XMLHttpRequest.status=='500'){
				showTipTitle('false',"处理出错，请联系管理员!");
			}else if(XMLHttpRequest.status=='12029'){
				showTipTitle('false',"连接服务器失败!");
			}
		}
	});
	 
	 
}

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {                
        window.clearInterval(InterValObj);//停止计时器
        isopen = true;
        $("#"+id).css("color", "rgb(104,174,0)");//启用按钮
        $("#"+id).attr("onclick","sendMessage()");
        $("#"+id).html("重新获取验证码");
    }
    else {
        curCount--;
        $("#"+id).html(curCount + "秒后重新获取");
    }
}

    $(function(){
		btnChange();
	});
    </script>
</html>