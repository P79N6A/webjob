<%@ page contentType="text/html; charset=UTF-8" language="java"  import="webfood.vo.ItemVo,java.util.List"%>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../css/css.css">
<script type="text/javascript" src="../js/hwork.js"></script>
</head>

<body>
<div id="PrintDiv">
  <table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
	  <td class="tdButtonActive" id="searchtd"><a href="javascript:void(0);">数据字典维护</a></td>
      <td align="right">
      <input type="button" class="button_search3" value="增加字典类" onClick="location='dic_class_add?r=<%=Math.random()%>'">
      <%@ include file="../public/public_function.jsp" %></td>
	</tr>
  </table>

  <table border="0" cellpadding="5" cellspacing="0" class="tableList">
    <tr>


<th class="thList">字典类名称 &nbsp;</th>

<th class="thList">备注 &nbsp;</th>

<th class="thList">项名称 &nbsp;</th>

<th class="thList">项值 &nbsp;</th>

<th class="thList">项顺序 &nbsp;</th>

<th class="thList">项状态 &nbsp;</th>

<th class="thList">项备注 &nbsp;</th>

<th class="thList">操作</th>
    </tr>
<%
    List<ItemVo> list = (List)request.getAttribute("list");
	int class_id = 0;
	int old_class_id = 0;
	int totalRow = list.size();
	for (int i = 0; i < totalRow; i++) {
	  class_id = list.get(i).getClassId();
	  if(class_id != old_class_id){
	    //新的字典类
		old_class_id = class_id;
		int count = i;
		while(true){
		  if(count == totalRow)
		    break;
		  if(list.get(count).getClassId() == class_id){
		    count ++;
		  }
		  else
		    break;
		}
		count = count - i + 1;//加上字典类 菜单 (修改 删除 增加字典项) 所占的一行
%>
    <tr onMouseOver="this.style.backgroundColor='#F0F9FE';" onMouseOut="this.style.backgroundColor='';">

<td class="tdList" rowspan="<%=count%>"><%=list.get(i).getClassName()%></td>

<td class="tdList" rowspan="<%=count%>"><%=list.get(i).getRemark()%></td>

<td class="tdList" colspan="6">&nbsp;&nbsp;&nbsp;<input type="button" class="button_search3" name="add" value="增加字典项" onClick="location='dic_item_add?id=<%=list.get(i).getClassId()%>&r=<%=Math.random()%>'"  ></td>
    </tr>
	
	<tr onMouseOver="this.style.backgroundColor='#F0F9FE';" onMouseOut="this.style.backgroundColor='';">
<%
    boolean isNull = list.get(i).getItemId() == null;//是否没有字典条目
%>
<td class="tdList"><%=list.get(i).getItemName()%></td>

<td class="tdList"><%=list.get(i).getItemValue()%></td>

<td class="tdList"><%=list.get(i).getItemOrder()%></td>

<td class="tdList"><% if(!isNull) out.print(list.get(i).getItemState()==0?"正常":"暂停");	 else out.print("&nbsp;");%></td>

<td class="tdList"><%=list.get(i).getItemRemark()%></td>

<td align="center" class="tdList"><% if(!isNull){ %><input type="button" class="button_search" name="d" value="删除" onClick="confirmOperation('确定要删除本字典项吗？','dic_item_delete?id=<%=list.get(i).getItemId()%>');"><% } %>&nbsp;</td>
    </tr>
<%
	  }else{
	    //新的字典条目
		 boolean isNull = list.get(i).getItemId()== null;//是否没有字典条目
%>
    <tr onMouseOver="this.style.backgroundColor='#F0F9FE';" onMouseOut="this.style.backgroundColor='';">

<td class="tdList"><%=list.get(i).getItemName()%></td>

<td class="tdList"><%=list.get(i).getItemValue()%></td>

<td class="tdList"><%=list.get(i).getItemOrder()%></td>

<td class="tdList"><%if(!isNull) out.print(list.get(i).getItemState()==0?"正常":"暂停");	 else out.print("&nbsp;");%>&nbsp;</td>

<td class="tdList"><%=list.get(i).getItemRemark()%></td>

<td align="center" class="tdList"><% if(!isNull){ %><input type="button" class="button_search" name="d" value="删除" onClick="confirmOperation('确定要删除本字典项吗？','dic_item_delete?id=<%=list.get(i).getItemId()%>');"><%}%></td>
    </tr>
<%		
	  }
	}
%>
  </table>
<br>

</div>
<%--<input type="button" class="button_search" name="m" value="修改" onClick="go('dic_class_modify.jsp?id=<%=class_id%>');">
<input type="button" class="button_search" name="m" value="修改" onClick="go('dic_item_modify.jsp?id=<%=list.get(i).getItemId()%>');">
<input type="button" class="button_search" name="m" value="修改" onClick="go('dic_item_modify.jsp?id=<%=list.get(i).getItemId()%>');">
<input type="button" class="button_search" name="d" value="删除" onClick="confirmOperation('确定要删除本字典类吗(其下所有字典项均将被删除)？','dic_class_delete.jsp?id=<%=class_id%>');">
<input type="button" class="button_search" name="d" value="删除" onClick="confirmOperation('确定要删除本字典项吗？','dic_item_delete.jsp?id=<%=list.get(i).getItemId()%>');">
<input type="button" class="button_search" name="d" value="删除" onClick="confirmOperation('确定要删除本字典项吗？','dic_item_delete.jsp?id=<%=list.get(i).getItemId()%>');">--%>
</body>
</html>