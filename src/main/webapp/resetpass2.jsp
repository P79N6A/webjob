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
<style type="text/css">
.textbox_190{width:190px;}
</style>
</head>
<body>
	<h2>
		<strong style="color:grey;">
			<c:if test="${result == 'true' }">
			 	重置成功
			</c:if>
			<c:if test="${result == 'false' }">
			 	重置失败
			</c:if>
		</strong>
	</h2>
	<ul class="ulColumn2">
		<li>
			<span class="item_name" style="width:120px;">
				<c:if test="${result == 'true' }">
					密码重置成功。<a href="/client_login" class="link_btn" >登录</a>
				</c:if>
				<c:if test="${result == 'false' }">
					失败原因：${loginfo}<a href="/client_resetpass" class="link_btn" >重新找回</a>
				</c:if>
				
				
			</span>
		</li>
	</ul>
</body>
</html>
