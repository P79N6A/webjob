<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../css/css.css">
</head>

<body>

<form name="form1" method="post" action="dic_item_add1" autocomplete="off" onSubmit="return submitForm(this);">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
	  <td class="tdButtonActive"><b>增加字典项</b></td>
	</tr>
  </table>
<table border="0" cellspacing="0" cellpadding="5" class="tableForm">
    <tr>
      <th colspan="3" align="left" class="thForm">为字典类 ${dclass.className}增加字典项目，请填写下表：</th>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">字典项名称</span></td>
      <td class="tdForm"><input name="itemName" type="text" class="tdInputLong" canNull="false" validator='' caption="字典项名称" minLen="1" maxLen="50" minNum="" maxNum=""></td>
      <td class="tdForm"><span class="tdOther"><font color='red'>*</font> 显示名称</span> &nbsp;</td>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">字典项值</span></td>
      <td class="tdForm"><input name="itemValue" type="text" class="tdInputLong" canNull="false" validator='' caption="字典项值" minLen="1" maxLen="50" minNum="" maxNum=""></td>
      <td class="tdForm"><span class="tdOther"><font color='red'>*</font> 实际保存值</span> &nbsp;</td>
    </tr>

<tr>
      <td class="tdForm"><span class="tdTitle">顺序号</span></td>
      <td class="tdForm"><input name="itemOrder" type="text" class="tdInputLong" canNull="false" validator='' caption="顺序号" minLen="" maxLen="" minNum="0" maxNum="8000"></td>
      <td class="tdForm"><span class="tdOther"><font color='red'>*</font> 在该字典的所有字典项中的排序</span> &nbsp;</td>
    </tr>
    <input type="hidden" name="itemState" value="0">
<tr>
      <td class="tdForm"><span class="tdTitle">备注</span></td>
      <td class="tdForm"><input name="remark" type="text" class="tdInputLong" canNull="true" validator='' caption="备注" minLen="" maxLen="" minNum="" maxNum=""></td>
      <td class="tdForm"><span class="tdOther"></span> &nbsp;</td>
    </tr>	
  </table>
  <br><input type="submit" name="Submit" value="提交" class="button_search">&nbsp;&nbsp;<input type="button" name="r" value="取消" class="button_search" onClick="javascript:history.go(-1);">
  
  
  <input type="hidden" name="classId" value="${dclass.id}">
  
</form>


</body>
</html>