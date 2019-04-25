<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java"  %><%@ include file="../public/head.jsp" %><%
String rid = "0";
if(org.apache.commons.lang.StringUtils.isEmpty(rid))
  rid = "-1";
long time = System.currentTimeMillis();
%><html xmlns="http://www.w3.org/1999/xhtml">
 <head id="Head1">
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>欢迎使用后宅街道工程管理系统</title>
  <link href="css/reset.css" rel="stylesheet" type="text/css" />
 <link href="css/default.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css" />
 <link rel="stylesheet" type="text/css" href="css/themes/icon.css" />
 <script language="javascript">
 	var RID = '6';
 </script>
 <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
 <script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
 <script type="text/javascript" src='js/outlook2.js'></script>

 <script type="text/javascript">
var _menus = {"menus":[
<%
	boolean first3class = false;
	StringBuffer menuStr = new StringBuffer();
	java.util.List<String> menus = (java.util.List<String>)session.getAttribute("menu");
	String menuRecord = null;
	String[] menuValues = null;
	if(menus != null){
		for(int i=0; i<menus.size(); i++){
			menuRecord = menus.get(i);
			menuValues = org.apache.commons.lang.StringUtils.splitPreserveAllTokens(menuRecord, "|");
			if(menuValues.length>=4){
				if(menuValues[1].indexOf(".") == -1){
					//一级菜单
					first3class = true;
					if(i > 0){
						//补齐上个菜单
						menuStr.append("]},");
					}
					menuStr.append("{\"menuid\":\""+(i+1)+"\",\"icon\":\""+(org.apache.commons.lang.StringUtils.isEmpty(menuValues[4])?"icon-nav":menuValues[4])+"\",\"menuname\":\""+menuValues[0]+"\",");
					menuStr.append("\"menus\":[");
				}else if(menuValues[1].indexOf(".") == menuValues[1].lastIndexOf(".")){
					//二级菜单
					continue;				
				}else{
					//三级菜单
					if(!first3class)
						menuStr.append(",");
					menuStr.append("{\"menuid\":\""+(i+1)+"\",\"menuname\":\""+menuValues[0]+"\",\"icon\":\"icon-nav\",\"url\":\"../"+menuValues[2]+"\"}");
					first3class = false;
				}
			}else{
				continue;
			}
		}
		menuStr.append("]}");
	}
	out.print(menuStr.toString());
%>

				]};
        //设置登录窗口
        function openPwd() {
			//$('#w').show();
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

        $(function() {

            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                /*$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '../logout.jsp?r='+Math.random();
                    }
                });*/
				art.dialog({
					content: '您确定要退出本次登录吗?',
					ok: function () {
						location.href = '../logout.jsp?r='+Math.random();
						return false;
					},
					lock: true,
					background: '#B3D5DE', // 背景色
					//background: '#75B2CE', // 背景色
					opacity: 0.7,	// 透明度

					icon: 'question',
					cancelVal: '取消',
					cancel: true //为true等价于function(){}
				});
            })
        });

//打开关闭左边		
function layoutsetting(){ 
setTimeout(function(){ 
    $('body').layout('collapse','west'); 
},0); 
} 
function hideleft() 
{   
  layoutsetting(); 
} 
//$(document).ready(hideleft); 

/*$('#tt').tabs('add',{   
  title:'New Tab',   
     content:'Tab Body',   
     closable:true  
 });  */
function refreshCurrent(){
	var currTab = $('#tabs').tabs('getSelected');
	/*var url = $(currTab.panel('options').content).attr('src');
	$('#tabs').tabs('update',{
		tab:currTab,
		options:{
			content:createFrame(url)
		}
	})	*/
	
	var frame=$('iframe', currTab);
	if(frame.length>0){frame[0].contentWindow.location.reload();}


}
function prnt_preview(){
	var currTab = $('#tabs').tabs('getSelected');
	var frame=$('iframe', currTab);
	var iframeObj;
	if(frame.length>0){
		iframeObj = frame[0];
	}
	if(iframeObj){
		var pfunc = iframeObj.contentWindow.prnt_preview;
		if (pfunc && (typeof pfunc == 'function') ) {  
			iframeObj.contentWindow.prnt_preview();  
		}else{
			LODOP.PRINT_INIT(iframeObj.contentWindow.document.title);
			pfunc = iframeObj.contentWindow.hide;
			if (pfunc && (typeof pfunc == 'function') ) {  
				iframeObj.contentWindow.hide();  
			}
			LODOP.SET_PRINT_PAGESIZE(0,0,0,"A4");
			LODOP.ADD_PRINT_HTM("5mm","5mm","95%","95%",iframeObj.contentWindow.document.documentElement.innerHTML);
			pfunc = iframeObj.contentWindow.show;
			if (pfunc && (typeof pfunc == 'function') ) {  
				iframeObj.contentWindow.show();  
			}
			LODOP.PREVIEW();
		}
	}
	else
		alert('还没打开任何功能页面，此功能不能使用！');
}

function prnt_print(){
	var currTab = $('#tabs').tabs('getSelected');
	var frame=$('iframe', currTab);
	var iframeObj;
	if(frame.length>0){
		iframeObj = frame[0];
	}
	if(iframeObj){
		var pfunc = iframeObj.contentWindow.prnt_print;
		if (pfunc && (typeof pfunc == 'function') ) {  
			iframeObj.contentWindow.prnt_print();  
		}else{
			LODOP.PRINT_INIT(iframeObj.contentWindow.document.title);
			pfunc = iframeObj.contentWindow.hide;
			if (pfunc && (typeof pfunc == 'function') ) {  
				iframeObj.contentWindow.hide();  
			}
			LODOP.SET_PRINT_PAGESIZE(0,0,0,"A4");
			LODOP.ADD_PRINT_HTM("5mm","5mm","95%","95%",iframeObj.contentWindow.document.documentElement.innerHTML);
			pfunc = iframeObj.contentWindow.show;
			if (pfunc && (typeof pfunc == 'function') ) {  
				iframeObj.contentWindow.show();  
			}
			LODOP.PRINT();	
		}
	}
	else
		alert('还没打开任何功能页面，此功能不能使用！');

}
</script>
 


 </head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<div region="north" split="true" border="false" style=" overflow: hidden; height:81px; background:#73b3cf url(images/headerbg.gif) repeat-x; line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体"><img src="../images/logo.jpg" class="logo" />
<ul class="software_nav">
<li><a href="javascript:void(0);" onclick="refreshCurrent();"><img src="images/prefsupd.png" /><span>刷新</span></a></li>
<li><a href="javascript:void(0)" onclick="prnt_preview();"><img src="images/printset_bnt.gif" /><span>打印预览</span></a></li>
<li><a href="javascript:void(0)" onclick="prnt_print();"><img src="images/print_bnt.gif" /><span>直接打印</span></a></li>
<li><a href="javascript:void(0)" onclick="addTab('修改密码','../public/pass_modify.jsp?r=<%=Math.random()%>','icon icon-nav')"><img src="images/password_bnt.gif" /><span>修改密码</span></a></li>
<!--li><a href="javascript:void(0)" id="editpass"><img src="images/password_bnt.gif" /><span>修改密码</span></a></li>
<li><a href="javascript:void(0)" onclick="refreshCurrent()"><img src="images/refresh_bnt.gif" /><span>刷新</span></a></li-->
<li><a href="logout"  id="loginOut"><img src="images/exit_bnt.gif" /><span>安全退出</span></a></li>
</ul>

</div>
<div region="south" split="true" style="height: 30px; background:url(images/footerbg.gif) repeat-x; ">
   <div class="footer"><div class="left_side">单位名称: 后宅街道  </div></div>
</div>

<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
   <div id="nav" class="easyui-accordion" fit="true" border="false"> 
    <!--  导航内容 --> 
  </div>
</div>

<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
   <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
    
  </div>
 </div>

<!--修改密码窗口-->
<div id="w" class="easyui-window"title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa; display:none">
   <div class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
       <table cellpadding=3>
        <tr>
           <td>新密码：</td>
           <td><input id="txtNewPass" type="Password" class="txt01" /></td>
         </tr>
        <tr>
           <td>确认密码：</td>
           <td><input id="txtRePass" type="Password" class="txt01" /></td>
         </tr>
      </table>
     </div>
    <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;"> <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" > 确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a> </div>
  </div>
 </div>

 
 
<div id="mm" class="easyui-menu" style="width:150px; display:none">
   <!--div id="mm-tabupdate">刷新</div>
   <div class="menu-sep"></div-->
   <div id="mm-tabclose">关闭</div>
   <div id="mm-tabcloseall">全部关闭</div>
   <div id="mm-tabcloseother">除此之外全部关闭</div>
   <div class="menu-sep"></div>
   <div id="mm-tabcloseright">当前页右侧全部关闭</div>
   <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
   <!--div class="menu-sep"></div>
   <div id="mm-exit">退出</div-->
 </div>
<script language="javascript">
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function confirmLogout(){
	if(confirm("您确定要注销本次登录吗？"))
		location = '../logout.jsp?r=<%=Math.random()%>';	
}
	
</script>

</body>
</html>