<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../css/css.css">
</head>
<body>


<div>
  <table border="0" cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td  id="searchtd"></td>
		<td align="right"><%@ include file="../public/public_function.jsp" %></td>
	</tr>
  </table>
<h2 align="center">后宅街道建设工程竣工验收记录表</h2>
<table width="100%" border="0" >
	<tr>
    	<td align="left" width="30%">&nbsp;</td>
        <td align="left" width="70%">竣工验收时间：&nbsp;${project.completeCheckTime}</td>
    </tr>
</table>
<table border="0" align="center" cellpadding="5" cellspacing="0" class="tableForm2">
  <tr>
    <td  width="18%" class="tdForm"><strong>工程名称</strong></td>
    <td width="23%"  class="tdForm">${project.project}</td>
    <td width="19%"  class="tdForm"><strong>工程地址</strong></td>
    <td width="40%" class="tdForm">${project.address}</td>
    </tr>
   
  <tr>
    <td class="tdForm"><strong>施工单位</strong></td>
    <td class="tdForm">&nbsp;</td>
    <td class="tdForm"><strong>工程类型</strong></td>
    <td class="tdForm">&nbsp;</td>
  </tr>
  <tr>
    <td class="tdForm"><strong>监理单位</strong></td>
    <td class="tdForm">&nbsp;</td>
    <td class="tdForm"><strong>工程造价</strong></td>
    <td class="tdForm">&nbsp;</td>
  </tr>
  <tr>
    <td class="tdForm"><strong>设计单位</strong></td>
    <td class="tdForm">&nbsp;</td>
    <td class="tdForm"><strong>开工日期</strong></td>
    <td class="tdForm">&nbsp;</td>
  </tr>
  <tr>
    <td class="tdForm"><strong>勘察日期</strong></td>
    <td class="tdForm">&nbsp;</td>
    <td class="tdForm"><strong>完工日期</strong></td>
    <td class="tdForm">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center" class="tdForm"><strong>竣工验收意见及签名</strong></td>
    </tr>
   <tr>
      <td  class="tdForm"><strong>施工单位</strong></td>
      <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
        <tr>
    <td class="tdForm"><strong>监理单位</strong></td>
    <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
    <tr>
          <td class="tdForm"><strong>设计单位</strong></td>
         <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
    <tr>
          <td class="tdForm"><strong>勘察单位</strong></td>
         <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
    <tr>
          <td class="tdForm"><strong>建设单位</strong></td>
         <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
    <tr>
      <td class="tdForm"><strong>竣工验收发现的问题</strong></td>
      <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
    <tr>
      <td class="tdForm"><strong>巡查办监督意见</strong></td>
    <td colspan="3" class="tdForm">&nbsp;</td>
    </tr>
  </table>
<br/>
  <input type="button" id="HiddenDiv" class="button_search" name="r" value="返回" onClick="location='update_finish?id=${id}'">
  
</div>
</body>
</html>


