<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String str=request.getHeader("User-Agent");
System.out.println("User-Agent:"+str);
System.out.println("\r\n\r\n打开登录首页\r\n\r\n");
boolean popNewWin = false;



%>
<head>
<title>欢迎使用后宅街道工程管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
*{ margin:0; padding:0; font-size:12px}
body,html { height:100%; background:#064f78}
.input1 { border:1px solid #c8c8c8; width:160px; height:23px; line-height:23px; padding-left:3px;}
.input2 { border:1px solid #c8c8c8; width:137px; height:23px; line-height:23px; padding-left:3px;}
.input3 { border:1px solid #c8c8c8; width:15px; height:23px; line-height:23px; padding-left:3px;}
.form_table { float:left; margin-top:60px; margin-left:25px;}
.form_table  td { line-height:40px; line-height:40px;}
.tdInput {
	width: 168px;
	height:16px;
	font-size: 12px;
}
.tdInput_cx {
	width: 68px;
	height:16px;
	font-size: 12px;
}
.text_grey{
	color:#999999;
}
.text_black{
	color:#000000;
}
.button_search {
	width:60px; height:21px; background:url(images/bn_bg.gif) repeat; color:#000; font-size:12px; text-align:center; line-height:18px; border-top:solid 1px #B1D7FC; border-left:solid 1px #B1D7FC; border-bottom:solid 1px #000000; border-right:solid 1px #000000;
}
.login_ct { position:absolute; height:492px; width:822px; left:50%; margin-left:-421px; top:50%; margin-top:-276px;}
</style>
<script language="javascript" src="js/jyjs.js"></script>
<script type="text/javascript" src="artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function checkName(){ 
		var name1=$("input[name='company']").val();
		$.ajax({ 
			   type: "post", 
			   url: "company_reg_check.jsp?name1="+name1, 
			   dataType: "json", 
			   cache:"false",										
			   success:function(da){
					if(da[0]!=null && da[0].isHas=='true'){
						document.getElementById("sname").innerHTML="公司名称已经存在，不可使用！";
						$("#reg-form-company").attr("value","");
						$("#reg-form-company").focus();
						
					}else if(da[0]!=null && da[0].isHas=='false' && name1.length!=0){
						document.getElementById("sname").innerHTML="可以使用!";     //+da[0].name1+";"+da[0].name2 
						
					}else{
						
						document.getElementById("sname").innerHTML="请输入公司名称";  
						$("#reg-form-company").focus();
						}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert('XMLHttpRequest:'+XMLHttpRequest+'/r/ntextStatus:'+textStatus+'/r/nerrorThrown:'+errorThrown);
				}	   
			   });					
	}
</script>
<script language="JavaScript">
<!--
function gokeyup1()
{
	key=window.event.keyCode; 
	if(key==0xD)//判断是否按下回车键 
	{ 
		document.getElementById("login").focus();
		return false;
	}
	else
		return true;	
}
function gokeyup2()
{
	key=window.event.keyCode; 
	if(key==0xD)//判断是否按下回车键 
	{ 
		document.getElementById("pw").focus();
		return false;
	}
	else
		return true;	
}
function gokeyup3()
{
	key=window.event.keyCode; 
	if(key==0xD)//判断是否按下回车键 
	{ 
		enter();
		return false;
	}
	else
		return true;	
}
function check() 
{  
  if(validateForm(document.getElementById("loginForm")))
	  return true;
  else
  	  return false;

}

function companyReg(){
	var dialog = art.dialog({id: 'N3690',title: false});
	
	// jQuery ajax   
	$.ajax({
		url: 'company_reg.jsp',
		success: function (data) {
			dialog.content(data);
		},
		cache: false
	});
}
function typeClicked(ctrl){
	var tdqyd = document.getElementById("td_qyd");
	var tdzfd = document.getElementById("td_zfd");
	var login = document.getElementById("login");
	var login2 = document.getElementById("login2");
	var login3 = document.getElementById("login3");
	if(ctrl.value == 'manager'){
		tdzfd.style.display = '';
		tdqyd.style.display = 'none';
		login.setAttribute("canNull", "false");
		login2.setAttribute("canNull", "true");
		login3.setAttribute("canNull", "true");
	}else if(ctrl.value == 'user'){
		tdzfd.style.display = 'none';
		tdqyd.style.display = '';
		login.setAttribute("canNull", "true");
		login2.setAttribute("canNull", "false");
		login3.setAttribute("canNull", "false");
	}
}
function enter(){
	
	if(check())
	{
		
		var width=screen.width-10; //parseInt(1024-8); //
		 height=screen.height-68; //parseInt(768-102);//
		 if('<%= popNewWin %>' == 'true'){
		 	window.open("about:blank", "111", "location=0,menubar=0,resizable=0,status=0,left=0,top=0,height="+height+",width="+width);
		 }
		 document.getElementById("loginForm").submit();
		 return;
	}
	else
		return ;
}

//-->
</script>
<%
//session.setAttribute(com.jinyitech.model.SS.LIP, request.getRemoteAddr());
Cookie cookies[]=request.getCookies(); 
Cookie cookie=null; 
String cvalue=null; 
String cname=null; 
String user_type = "";
if(cookies != null){
	for(int i=0; i<cookies.length; i++){
	  cookie = cookies[i];
	  cname = cookie.getName();
	  System.out.println("\r\nCookie名称:"+cname);
	  if("aa".equals(cname)){
		cvalue = cookie.getValue();
		if(cvalue.indexOf("|")>=0){
			String[] a = org.apache.commons.lang.StringUtils.split(cvalue, "|");
			cvalue = a[0];
			user_type = a[0];	
		}
		//System.out.println("\r\n值："+cvalue);
		break;
	  }
	}
}
%> 
</head>
<body >
<div id="container" class="login_ct">
<table width="100%" height="100%;" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="middle" align="center">
<table  width="858" height="542" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<img src="images/login_new_01.jpg" width="17" height="22" alt=""></td>
		<td>
			<img src="images/login_new_02.jpg" width="36" height="22" alt=""></td>
		<td>
			<img src="images/login_new_03.jpg" width="403" height="22" alt=""></td>
		<td>
			<img src="images/login_new_04.jpg" width="326" height="22" alt=""></td>
		<td>
			<img src="images/login_new_05.jpg" width="57" height="22" alt=""></td>
		<td>
			<img src="images/login_new_06.jpg" width="19" height="22" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_new_07.jpg" width="17" height="73" alt=""></td>
		<td>
			<img src="images/login_new_08.jpg" width="36" height="73" alt=""></td>
		<td>
			<img src="images/login_new_09.jpg" width="403" height="73" alt=""></td>
		<td background="images/login_new_10.jpg" align="right"> </td>
		<td>
			<img src="images/login_new_11.jpg" width="57" height="73" alt=""></td>
		<td>
			<img src="images/login_new_12.jpg" width="19" height="73" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_new_13.jpg" width="17" height="281" alt=""></td>
		<td>
			<img src="images/login_new_14.jpg" width="36" height="281" alt=""></td>
		<td>
			<img src="images/indexHz.jpg" width="403" height="281" alt=""></td>
		<td background="images/login_new_16.jpg" width="326" height="281" valign="top">
			<table width="260" border="0" cellspacing="0" cellpadding="0" class="form_table">
 <form name="loginForm" id="loginForm" method="post" target="_self" action="login">

  <tr>
    <td>账        号：</td>
    <td id="td_zfd" ><input name="login" id="login" type="text" onKeyDown="return gokeyup2()" class="input1"  maxLength="20" caption="账号" validator="" maxLen="20" minLen="3" canNull="false" value="" /></td>
  </tr>
  <tr>
    <td>密        码：</td>
    <td><input name="password" id="pw" type="password" class="input1" onKeyDown="return gokeyup3()"  maxLength="20" caption="密码" validator="密码字符" maxLen="16" minLen="3" canNull="false"  value=""/></td>
  </tr>
  <tr>
    <td></td>
    <td align="left">  </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="left" valign="middle" colspan="2">   <img src="images/wl_bn.gif"  onClick="enter();"  style="cursor:hand"/> </td>
  </tr>
  <input type="hidden" name="from" value="${from}">
  </form>
</table>


		</td>
		<td><img src="images/login_new_17.jpg" width="57" height="281" alt="" /></td>
		<td>
			<img src="images/login_new_18.jpg" width="19" height="281" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_new_19.jpg" width="17" height="58" alt=""></td>
		<td>
			<img src="images/login_new_20.jpg" width="36" height="58" alt=""></td>
		<td background="images/login_new_22.jpg" align="left" valign="middle"> </td>
		<td background="images/login_new_22.jpg" align="left" valign="middle"> </td>
		<td>
			<img src="images/login_new_23.jpg" width="57" height="58" alt=""></td>
		<td>
			<img src="images/login_new_24.jpg" width="19" height="58" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_new_25.jpg" width="17" height="58" alt=""></td>
		<td>
			<img src="images/login_new_26.jpg" width="36" height="58" alt=""></td>
		<td colspan="2" background="images/login_new_27.jpg" align="center" valign="middle" style="color:#605e5e; ">
			后宅街道      技术支持QQ：495394356</a> </td>
		<td>
			<img src="images/login_new_28.jpg" width="57" height="58" alt=""></td>
		<td>
			<img src="images/login_new_29.jpg" width="19" height="58" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_new_30.jpg" width="17" height="31" alt=""></td>
			<td>
			<img src="images/login_new_31.jpg"  alt=""></td>
		<td colspan="3" background="images/login_new_32.jpg"> </td>
		<td>
			<img src="images/login_new_35.jpg" width="19" height="31" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="images/login_new_36.jpg" width="17" height="19" alt=""></td>
		<td>
			<img src="images/login_new_37.jpg" width="36" height="19" alt=""></td>
		<td>
			<img src="images/login_new_38.jpg" width="403" height="19" alt=""></td>
		<td>
			<img src="images/login_new_39.jpg" width="326" height="19" alt=""></td>
		<td>
			<img src="images/login_new_40.jpg" width="57" height="19" alt=""></td>
		<td>
			<img src="images/login_new_41.jpg" width="19" height="19" alt=""></td>
	</tr>
</table>
</td>
  </tr>
</table>
<%
if(cvalue != null){
  
%>
<script language="javascript">
  document.getElementById("login").value = '<%=cvalue%>';
  document.getElementById("login2").value = '<%=org.apache.commons.lang.StringUtils.substring(cvalue, 0, cvalue.length()-1)%>';
  document.getElementById("login3").value = '<%=org.apache.commons.lang.StringUtils.substring(cvalue, cvalue.length()-1)%>';
  if("<%= cvalue %>" != ""){
    document.getElementById("pw").focus();
  }
  else{
    document.getElementById("login").focus();
  }
  if("<%=user_type%>" == "manager"){
	  var ctrl = document.getElementById("u_manager");
	  typeClicked(ctrl);
	  ctrl.checked = "checked";
  }else if("<%=user_type%>" == "user"){
	  var ctrl = document.getElementById("u_user");
	  typeClicked(ctrl);
	  ctrl.checked = "checked";
  }
</script>
<%
}else{
%>
<script language="javascript">
  //document.getElementById("logi_code").focus();
  document.getElementById("login").focus();
</script>
<%
}
%>

</div>
  


</body>
</html>

        