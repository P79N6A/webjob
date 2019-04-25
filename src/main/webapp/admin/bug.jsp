<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../css/css.css">
</head>

<body>
<form name="form1" method="post" action="bug1" autocomplete="off" onSubmit="return submitForm(this);">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
	  <td class="tdButtonActive"><b>提bug</b></td>
	</tr>
  </table>
<table border="0" cellspacing="0" cellpadding="5" class="tableForm">
    <tr>
      <th colspan="3" align="left" class="thForm">请填写下表：</th>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">bug内容</span></td>
      <td class="tdForm"><textarea name="content" cols="50" rows="20">${content}</textarea></td>
      <td class="tdForm"><span class="tdOther"><font color='red'>*</font> </span> &nbsp;</td>
    </tr>
</table><br>
  <p>备注：br用来换行，内容在主页显示 </p>
  <input type="submit" name="Submit" value="提交" class="button_search">&nbsp;&nbsp;
</form>

</body>
</html>