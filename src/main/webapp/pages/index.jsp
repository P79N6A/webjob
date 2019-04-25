<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link href="/pms/css/indexc.css" rel="stylesheet">
<link href="/pms/plug-in/boxDialog/dist/skins/default.css" rel="stylesheet">
<style>
label.key{
display:inline-block;
vertical-align: top;
}
div.comfirm {
text-align:left;
word-break:break-all; /*支持IE，chrome，FF不支持*/
word-wrap:break-word;/*支持IE，chrome，FF*/
}
</style>
</head>
<body>
  hello,boy11!
</body>
</html>