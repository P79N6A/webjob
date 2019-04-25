<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script language="javascript" src="<%= request.getContextPath() %>/public/pageUtil.js"
	type="text/javascript"></script>
<%
	if(pagedb.row() > 0){
%>
		&nbsp;&nbsp;共<%= pagedb.getAllPageTotalRow() %>条记录，第<%= pagedb.getPageIndex() %>页&nbsp;/&nbsp;共<%= pagedb.getAllPageNum() %>页&nbsp;
				[<a href="javascript:doQueryForm(this.form,1,'<%= pagedb.getAllPageNum() %>');" style= "text-decoration:none; ">首&nbsp;&nbsp;页</a>]
				[<a href="javascript:doQueryForm(this.form,prviousPage('<%= pagedb.getPageIndex() %>'),'<%= pagedb.getAllPageNum() %>');" style= "text-decoration:none; ">上一页</a>]
				[<a href="javascript:doQueryForm(this.form,nextPage('<%= pagedb.getPageIndex() %>','<%= pagedb.getAllPageNum() %>'),'<%= pagedb.getAllPageNum() %>');" style= "text-decoration:none; ">下一页</a>]
				[<a href="javascript:doQueryForm(this.form,'<%= pagedb.getAllPageNum() %>','<%= pagedb.getAllPageNum() %>');" style= "text-decoration:none; ">尾&nbsp;&nbsp;页</a>]
		&nbsp;跳转到第
			<input type="text" size="2" name="select" id="pageno" value="<%= pagedb.getPageIndex() %>" />页&nbsp;<input type="button" class="button_search" value="跳转" name="page_jump" onclick="javascript:doQueryForm(this.form, document.getElementById('pageno').value,'<%= pagedb.getAllPageNum() %>');"/>
<%
	}else{
%>
		<br /><span id="resultNull" style="height:30; vertical-align:baseline">&nbsp;&nbsp;查询结果为空！</span>
<%
	}
%>
