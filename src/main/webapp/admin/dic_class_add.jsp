<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../css/css.css">
</head>

<body>
<form name="form1" method="post" action="dic_class_add1" autocomplete="off" onSubmit="return submitForm(this);">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
	  <td class="tdButtonActive"><b>增加字典类</b></td>
	</tr>
  </table>
<table border="0" cellspacing="0" cellpadding="5" class="tableForm">
    <tr>
      <th colspan="3" align="left" class="thForm">请填写下表：</th>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">字典类</span></td>
      <td class="tdForm"><input name="className" type="text" class="tdInputLong" canNull="false" validator='' caption="字典类" minLen="1" maxLen="50" minNum="" maxNum=""></td>
      <td class="tdForm"><span class="tdOther"><font color='red'>*</font> </span> &nbsp;</td>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">是否显示</span></td>
      <td class="tdForm"><input type="radio" name="showable" value="TRUE" checked="checked">显示  <input type="radio" name="showable" value="FALSE"  >隐藏</td>
      <td class="tdForm"><span class="tdOther">是否显示在管理界面上</span> &nbsp;</td>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">备注</span></td>
      <td class="tdForm"><input name="remark" type="text" class="tdInputLong" canNull="true" validator='' caption="remark" minLen="" maxLen="" minNum="" maxNum=""></td>
      <td class="tdForm"><span class="tdOther"></span> &nbsp;</td>
    </tr>
  </table><br>
  <input type="submit" name="Submit" value="提交" class="button_search">&nbsp;&nbsp;<input type="button" value="取消" name="r" class="button_search" onClick="javascript:history.go(-1);">
</form>

</body>
</html>