<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>手机注册</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery.js"></script>
<script type="text/javascript">
var ver_code = "";//验证码信息
var isCanGetCode = "true";//是否可以获取验证码，不能重复获取

$(function(){
    //判断手机号是否可用
	$("input[name='username']").blur(function(){
		ver_code = "";
		isCanGetCode = 'true';
	});
	
	//getcode_btn  获取重置密码验证码
	$("input[name='getcode_btn']").click(function(){
		var thisObj = $("input[name='username']");
		var val = thisObj.val();
		if(isCanGetCode=='true'){
			$.ajax({
			   type: "post",
			   dataType: "json",//返回json格式的数据
			   url: "client_resetpass_getcode",
			   data: {"username":val},
			   timeout: 30000, //超时时间：30秒
			   success: function(msg){
				  if(msg.result=='true'){//获取成功
				  	isCanGetCode = "false";//是否可以获取验证码，不能重复获取
					ver_code = msg.ver_code;
				  }
				  showTipTitle('false',msg.loginfo);
				  
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
		}else{
			showTipTitle('false','不能重复获取！');
		}
	});
});

//弹出提示信息
function showTipTitle(args0,args){
	alert(args);
}


function checkPass(){//提交前验证
	//showTipTitle('false','cccsss!');
	var username = $("input[name='username']").val();
	var verification_code = $("input[name='verification_code']").val();
	var password = $("input[name='password']").val();
	var sure_password = $("input[name='sure_password']").val();
	//showTipTitle('false','23432342!'+verification_code+"----");
	if(username==''){
		showTipTitle('false','找回密码手机号不能为空!');
		return false;
	}
	if(verification_code==''){
		showTipTitle('false','验证码不能为空!');
		return false;
	}
	if(password==''){
		showTipTitle('false','密码不能为空!');
		return false;
	}
	if(sure_password==''){
		showTipTitle('false','确认密码不能为空!');
		return false;
	}
	if(sure_password!=password){
		showTipTitle('false','两次输入密码不一致!');
		return false;
	}
	
	return true;
}
</script>
<style type="text/css">
.textbox_190{width:190px;}
</style>
</head>
<body>
	<form action="client_resetpass2" method="post" onSubmit="return checkPass()">
		<input type="hidden" value="${username}" name="username" />
		<input type="hidden" value="${verification_code}" name="verification_code" />
		<h2><strong style="color:grey;">重置密码</strong></h2>
		<ul class="ulColumn2">
		<li>
		<span class="item_name" style="width:120px;">新的登录密码：</span>
		<input type="text" name="password" class="textbox textbox_295" placeholder="请输入新的登录密码..." value="" />
		</li>
		<li>
		<span class="item_name" style="width:120px;">确认登录密码：</span>
		<input type="text" name="sure_password" class="textbox textbox_295" placeholder="确认登录密码..." value="" />
		</li>
		<li>
		<span class="item_name" style="width:120px;"></span>
		<input type="submit" class="link_btn" value="完成"/>
		</li>
		</ul>
	</form>
</body>
</html>
