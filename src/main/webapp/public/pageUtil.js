var is_flag=true;

function trim(str){  //删除左右两端的空格
 return str.replace(/(^\s*)|(\s*$)/g, "");
}
//设置上一页码
function prviousPage(indexPage){
   	if(parseInt(indexPage) == 1){
   		alert("已经是第一页了!");
		is_flag=false;
	} else {
		is_flag=true;
	}
    if(parseInt(indexPage)-1 <= 1){
		return 1;
	}else{
		return parseInt(indexPage)-1;
	}
}
//设置下一页码
function nextPage(indexPage,totalPage){
	if(parseInt(indexPage) == parseInt(totalPage)){
		alert("已经是最后一页了!");
		is_flag=false;
	} else {
		is_flag=true;
	}
	if(parseInt(indexPage)+1 >= parseInt(totalPage)){
		return parseInt(totalPage);
	}else{
		return parseInt(indexPage)+1;
	}
}

//跳转
function doQueryForm(form ,currentPage, totalPages){
		if(isNaN(currentPage))
			currentPage = trim(currentPage);
		if(currentPage == '' || isNaN(currentPage) || parseInt(currentPage)<=0)
		{
			alert('请填入正确的页码！');
			return;
		}
		if(parseInt(currentPage)>parseInt(totalPages)){
				alert("此页码不存在!");
				return;
		}
		//is_flag = true;
		var pageFormName = document.getElementById("pageFormName");
		var pageno = document.getElementById("pageno");
		if(pageFormName!=null){
			form=document.forms[pageFormName.value];
		}
		document.getElementById("pagepageno").value = pageno.value;
		pageno = document.getElementById("pagepageno");
	    if(currentPage=="-1"){
	  		if(parseInt(pageno.value)!=pageno.value){
	    		is_flag=false;
	    		document.getElementById("pageno").value="";
	    		alert("请填入正确的页码!");
	    	}else if(parseInt(pageno.value)>parseInt(totalPages)||parseInt(pageno.value)<1){
	  			is_flag=false;
	  			document.getElementById("pageno").value="";
	    		alert("此页码不存在!");
	    	}else 
	  			currentPage = pageno.value;
	  	}

	    if(is_flag){
	   		pageno.value=currentPage;
    		form.target="_self";
    		form.submit();
	    }
	    is_flag=true;
	    
}