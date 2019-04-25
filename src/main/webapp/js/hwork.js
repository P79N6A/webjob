<!--
function shiftSearch() {
	if(searchtd.className=="tdButtonNormal") {
		searchtd.className = "tdButtonActive";
		searchtb.style.display = "block";
	}else {
		searchtd.className = "tdButtonNormal";
		searchtb.style.display = "none";
	}
}
var _alert_timer;

function resultAlert(result, msg, url, seconds){
	art.dialog({
	  //lock:true,
		width: 220,
   	  	height: 55,
	  	title:'处理结果 3',
		content: msg,
		icon: result,
		ok:function () {
			if(url != null && url != '') {
				location = url;
			}
			return true;
		},
		init:function() {
			var that = this, i = 3;
			//alert(seconds);
			if(seconds){
				var t = parseInt(seconds);
				if(t>=0) {
					i = t;
				}
			}
			//alert(i);
			var fn = function () {
			  that.title('处理结果 ' + i);
			  !i && that.close();
			  i --;
			}
			_alert_timer = setInterval(fn, 1000);
			fn();
		},
		close: function () {
			clearInterval(_alert_timer);
			if(url != null && url != '') {
				location = url;
			}
		}
	});
}

function showPic(path){
	artDialog({title:'图片查看', content:'<img width="800" src="'+path+'" />', fixed:false});
	return false;
}

function closeSelfWin(){
	top.closeCurrTab();
}
//执行某个方法后再跳转到URL
function resultAlertFunc(result, msg, url, func){
	art.dialog({
	  	//lock:true,
		width: 220,
   	 	height: 55,
	  	title:'处理结果 3',
	 	content: msg,
	  	icon: result,
	  	ok:function () {
			if(url != '' && url != null) {
	    		location = url;
			}
			return true;
	  	},
	  	init:function () {
			var that = this, i = 3;
			var fn = function () {
				that.title('处理结果 ' + i);
				!i && that.close();
				i --;
			}
			_alert_timer = setInterval(fn, 1000);
			fn();
		},
		close: function () {
			clearInterval(_alert_timer);
			eval(func);
			if(url != '' && url != null) {
				location = url;
			}
		}
	});
}
function resultConfirm(result, msg, url1, url2){
    art.dialog({
	  width: 220,
   	  height: 55,
	  title:'处理结果',
	  content: msg,
	  icon: result,
	  button:[{
		name:'是',
		callback:function(){
		  location = url1;
		  return true;  
		},
		focus:true
	  },
	  {
		name:'否',
		callback:function(){
		  location = url2;
		  return true;  
		},
		focus:false
	  }]

    });
}

function getPrinterName(pname) {
	count = LODOP.GET_PRINTER_COUNT();
	if(pname ==""||pname==null) {
		return "";
	}else {
		if(count==0) {
			return "";
		}else {
			for(i=0; i<count; i++){
		  		name = LODOP.GET_PRINTER_NAME(i);
		  		if(name == pname) {
					return name;
				}
			}
		}
	}
}

function consignSetup(consObj, top,left, pname, version) {
	if(version == '2009版运单' || version == '')	{
		if(!CreateConsignPage(consObj,top,left)) {
			return;
		}
	}else if(version == '2013版运单'){
		if(!CreateConsignPage2013Wzscb(consObj,top,left)) {
			return;
		}
	}else{
		alert('不支持的运单版式，不能打印！');
		return;	
	}
	printer_name = getPrinterName(pname);
	
	if(printer_name != "" && printer_name!=null) {
		LODOP.SET_PRINTER_INDEXA(printer_name);
	}

	LODOP.PREVIEW();	
	//LODOP.PRINT_SETUP();
}

function consignDesign(consObj, top,left, pname, version) {		
	if(version == '2009版运单' || version == '')	{
		if(!CreateConsignPage(consObj,top,left)) {
			return;
		}
	}if(version == '2013版运单'){
		if(!CreateConsignPage2013Wzscb(consObj,top,left)) {
			return;
		}
	}else{
		alert('不支持的运单版式，不能打印！');
		return;	
	}
	printer_name = getPrinterName();
	if(printer_name != "" && printer_name!=null) {
		LODOP.SET_PRINTER_INDEXA(printer_name);
	}
	return LODOP.PRINT_DESIGN();		
}

function consignPrint(consObj,top,left, pname, version) {		
	if(version == '2009版运单' || version == '')	{
		if(!CreateConsignPage(consObj,top,left)) {
			return;
		}
	}else if(version == '2013版运单'){
		if(!CreateConsignPage2013Wzscb(consObj,top,left)) {
			return;
		}
	}else {
		alert('不支持的运单版式，不能打印！');
		return;	
	}
	printer_name = getPrinterName(pname);
	if(printer_name != "" && printer_name!=null) {
		LODOP.SET_PRINTER_INDEXA(printer_name);
	}
	return LODOP.PRINT();
}


function CreateConsignPage(obj, top, left) {
	//alert('尚未定义打印模板，调用默认模板打印！');
	LODOP.PRINT_INITA(top,left,718,680,"默认的运单打印设计");
	LODOP.ADD_PRINT_SETUP_BKIMG("d:/project/lufu2.0/hygl/images/cons_sheet/鹿富运单.jpg");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(102,121,50,24,obj.begin_time.substring(0, 4));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(102,183,30,24,obj.begin_time.substring(5, 7));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(102,222,30,24,obj.begin_time.substring(8, 10));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(102,333,55,24,obj.from_city);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	var to = obj.to_city;
	if(obj.trans_to != '')
		to += "转"+obj.trans_to;
	if(obj.busi_type == '特快')
		to += " 特快";
	LODOP.ADD_PRINT_TEXT(102,402,120,24,to);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(102,491,186,24,obj.consign_code);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(129,134,229,24,obj.rc_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(129,448,230,24,obj.rc_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(187,51,156,52,obj.ware_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(187,210,117,52,obj.symbol);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(187,331,102,52,obj.amount);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(187,436,241,52,obj.deal_type_cn+ (obj.remark==''?"":"("+obj.remark+")") + (obj.dshk=='0'?"":' 代收'+obj.dshk+"元货款"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(243,132,116,24,obj.zyf+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(243,340,169,24,obj.zyf_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.SET_PRINT_STYLEA(0,"Angle",1);
	LODOP.ADD_PRINT_TEXT(243,581,96,24,obj.pay_type_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(272,132,241,24,obj.sd_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(272,457,220,24,obj.sd_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(300,132,241,24,obj.bjje+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(300,457,220,24,'');
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(633,472,208,24,obj.user_name+" "+obj.create_time.substring(11, 19));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	if(obj.sshf != '0'){
		LODOP.ADD_PRINT_TEXT(606,240,222,24,"含送货费:"+obj.sshf+"元");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);	
	}
	LODOP.ADD_PRINT_TEXT(633,88,318,24,"网点:"+obj.from_dept+" "+obj.from_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	return true;
  }
 
function CreateConsignPage2013(obj, top, left) {
	//alert('尚未定义打印模板，调用默认模板打印！');
    LODOP.PRINT_INITA(top,left,680,484,"默认的运单打印设计");
	LODOP.ADD_PRINT_SETUP_BKIMG("d:/project/lufu2.0/hygl/images/cons_sheet/鹿富新运单.jpg");
	LODOP.ADD_PRINT_TEXT(90,24,50,24,obj.begin_time.substring(0, 4));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(90,96,30,24,obj.begin_time.substring(5, 7));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(90,144,30,24,obj.begin_time.substring(8, 10));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(59,522,152,24,obj.consign_code);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(90,243,55,24,obj.from_city);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	var to = obj.to_city;
	if(obj.trans_to != '')
		to += "-"+obj.trans_to;
	if(obj.busi_type == '特快')
		to += " 特快";
	LODOP.ADD_PRINT_TEXT(90,341,145,24,to);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	to_dept_phone = obj.to_dept_phone;
	if(to_dept_phone.length>13)
		to_dept_phone = to_dept_phone.substring(0, 13);
	LODOP.ADD_PRINT_TEXT(90,521,152,24,to_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	

	LODOP.ADD_PRINT_TEXT(123,135,229,24,obj.rc_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(123,383,260,24,obj.rc_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(152,135,228,24,obj.sd_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(152,383,260,24,obj.sd_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(209,23,104,52,obj.ware_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(209,133,74,52,obj.symbol);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,213,122,52,obj.amount_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,494,76,55,obj.bjje+"/"+obj.bjf);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,419,76,55,obj.cubage);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,345,76,55,obj.weight);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	

	LODOP.ADD_PRINT_TEXT(300,178,111,24,obj.dshk+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,571,81,55,obj.pay_type_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(269,101,116,24,obj.zyf+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(269,347,302,24,obj.zyf_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(300,363,50,24,(obj.qhd==1?"是":"/"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(300,557,91,24,(obj.dtzps==1?"是":"/"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(328,98,310,27,obj.deal_type_cn+(obj.remark==''?"":"("+obj.remark+")"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(436,233,261,24,obj.from_dept+" "+obj.from_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(436,68,130,24,obj.old_code);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(436,500,165,24,obj.user_name+" "+obj.create_time.substring(11, 16));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	if(obj.sshf != '0'){
		LODOP.ADD_PRINT_TEXT(408,455,194,24,"含送货费:"+obj.sshf+"元");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	
	}
	return true;
}
  
function CreateConsignPage2013Wzscb(obj, top, left) {
	//alert('尚未定义打印模板，调用默认模板打印！');
    LODOP.PRINT_INITA(top,left,680,484,"默认的运单打印设计");
	LODOP.ADD_PRINT_SETUP_BKIMG("d:/project/lufu2.0/hygl/images/cons_sheet/鹿富新运单.jpg");
	LODOP.ADD_PRINT_TEXT(90,24,50,24,obj.order_date.substring(0, 4));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(90,96,30,24,obj.order_date.substring(5, 7));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(90,144,30,24,obj.order_date.substring(8, 10));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(59,522,152,24,obj.order_code);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(90,243,55,24,obj.from_city);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	var to = obj.to_city;
	if(obj.trans_to != '')
		to += "-"+obj.trans_to;
	if(obj.busi_type == '特快')
		to += " 特快";
	LODOP.ADD_PRINT_TEXT(90,341,145,24,to);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	

	LODOP.ADD_PRINT_TEXT(123,135,229,24,obj.to_name+" "+obj.to_linkman);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(123,383,260,24,obj.to_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(152,135,228,24,obj.from_name+" "+obj.from_linkman);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(152,383,260,24,obj.from_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(209,23,104,52,obj.ware_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	/*
	LODOP.ADD_PRINT_TEXT(209,133,74,52,obj.symbol);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);包装*/
	LODOP.ADD_PRINT_TEXT(209,213,122,52,obj.amount_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,494,76,55,obj.bjje+"/"+obj.bjf);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,419,76,55,obj.cubage);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,345,76,55,obj.weight);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	

	LODOP.ADD_PRINT_TEXT(300,178,111,24,obj.dshk+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(209,571,81,55,obj.pay_type_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(269,101,116,24,obj.zyf+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(269,347,302,24,obj.zyf_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(300,363,50,24,(obj.qhd==1?"是":"/"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(300,557,91,24,(obj.dtzps==1?"是":"/"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(328,98,310,27,obj.deal_type_cn+(obj.remark==''?"":"("+obj.remark+")"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(436,233,261,24,obj.dept_name);//+" "+obj.from_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.ADD_PRINT_TEXT(436,500,165,24,obj.user_name+" "+obj.create_time.substring(11, 16));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	
	return true;
}

function CreateConsignPageLlx(obj, top, left) {
	//alert('尚未定义打印模板，调用默认模板打印！');
    LODOP.PRINT_INITA(top,left,733,495,"默认的运单打印设计");
	LODOP.ADD_PRINT_SETUP_BKIMG("d:/project/lufu2.0/hygl/images/cons_sheet/鹿富丽龙线.jpg");
	LODOP.ADD_PRINT_TEXT(55,559,50,24,obj.begin_time.substring(0, 4));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(55,615,30,24,obj.begin_time.substring(5, 7));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(55,653,30,24,obj.begin_time.substring(8, 10));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(56,45,121,24,obj.consign_code);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(90,140,55,24,obj.from_city);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	var to = obj.to_city;
	if(obj.trans_to != '') {
		to += "-"+obj.trans_to;
	}
	LODOP.ADD_PRINT_TEXT(90,401,277,24,to);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(362,450,245,24,"提货电话:"+obj.to_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	

	LODOP.ADD_PRINT_TEXT(120,391,287,24,obj.rc_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(151,391,290,24,obj.rc_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(120,123,200,24,obj.sd_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(151,123,201,24,obj.sd_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(214,65,114,52,obj.ware_name);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	//LODOP.ADD_PRINT_TEXT(209,133,74,52,obj.symbol);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(214,178,67,52,obj.amount_cn);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(214,334,55,52,obj.bjje+"/"+obj.bjf);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	LODOP.ADD_PRINT_TEXT(214,247,81,52,obj.weight+"/"+obj.cubage);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	

	LODOP.ADD_PRINT_TEXT(385,450,198,24,"代收货款:"+obj.dshk+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(214,477,139,52,obj.pay_type_cn+" "+obj.jcyf+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	if(obj.dtzps==1){
		LODOP.ADD_PRINT_TEXT(325,587,50,24,"等通知放货");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	}
	if(obj.qhd==1){
		LODOP.ADD_PRINT_TEXT(349,587,91,24,"签回单");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	}

	LODOP.ADD_PRINT_TEXT(191,624,59,73,obj.deal_type_cn+(obj.remark==''?"":"("+obj.remark+")"));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	//LODOP.ADD_PRINT_TEXT(436,233,205,24,obj.from_dept);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	//LODOP.ADD_PRINT_TEXT(436,68,130,24,obj.old_code);
	//LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(303,132,165,24,obj.user_name+" "+obj.create_time.substring(11, 16));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	
	LODOP.ADD_PRINT_TEXT(266,556,123,31,obj.zyf+"元");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(266,173,31,31,numToChinese(Math.floor(parseInt(obj.zyf)%100000/10000)));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(266,224,31,31,numToChinese(Math.floor(parseInt(obj.zyf)%10000/1000)));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(266,279,31,31,numToChinese(Math.floor(parseInt(obj.zyf)%1000/100)));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(266,324,31,31,numToChinese(Math.floor(parseInt(obj.zyf)%100/10)));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(266,374,31,31,numToChinese(Math.floor(parseInt(obj.zyf)%10/1)));
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.ADD_PRINT_TEXT(266,425,31,31,"零");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);

	return true;
}

  
function chkall(input1,input2){
	alert(11);
    var objForm = document.forms[input1];
    var objLen = objForm.length;
    for (var iCount = 0; iCount < objLen; iCount++){
        if (input2.checked == true){
            if (objForm.elements[iCount].type == "checkbox" && (objForm.elements[iCount].disabled == false)) {
                objForm.elements[iCount].checked = true;
            }
        }else {
            if (objForm.elements[iCount].type == "checkbox" && (objForm.elements[iCount].disabled == false)) {
                objForm.elements[iCount].checked = false;
            }
        }
	}
}

function barcodeDesign(consObj, pname) {		
	printer_name = getPrinterName(pname);
	if(printer_name != "" && printer_name!=null)
		LODOP.SET_PRINTER_INDEXA(printer_name);
	//LODOP.PRINT_INITA(0,0,'61mm','60mm',"默认的条码打印");
	//LODOP.SET_PRINT_PAGESIZE(1,610,600,'默认的条码打印'); 
	LODOP.PRINT_INITA(0,0,'90mm','55mm',"默认的条码打印");
	LODOP.SET_PRINT_PAGESIZE(1,900,550,'默认的条码打印');
    CreateBarcodePage(consObj, 1, consObj.amount);
	return LODOP.PRINT_DESIGN();		
}
  
function barcodePreview(obj, printerName, num) {	
	//var barcodeCtrl = document.getElementById("barcode_num");  
	//alert('尚未定义打印模板，调用默认模板打印！');
	var re = Dict.正整数;///^[1-9]+[0-9*]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/   
	if (!re.test(num)){
		alert("'码单数'请输入正整数！");
		return false;
	}
	if(parseInt(num)>parseInt(obj.amount)){
		alert("'码单数'不能大于件数！");
		barcodeCtrl.focus();
		return false;
	}
	
	//LODOP.PRINT_INITA(0,0,'61mm','60mm',"默认的条码打印");
	//LODOP.SET_PRINT_PAGESIZE(1,610,600,'默认的条码打印'); 
	LODOP.PRINT_INITA(0,0,'90mm','55mm',"默认的条码打印");
	LODOP.SET_PRINT_PAGESIZE(1,900,550,'默认的条码打印');
	
	for(i = 1; i<=num; i++){
		LODOP.NewPage();
		CreateBarcodePage(obj, i, num);
	}
	
	printer_name = getPrinterName(printerName);
	if(printer_name != "" && printer_name!=null) {
		LODOP.SET_PRINTER_INDEXA(printer_name);
	}
	return LODOP.PREVIEW();
}
function barcodePrint(obj, printerName, num) {	
	//var barcodeCtrl = document.getElementById("barcode_num");  
	//alert('尚未定义打印模板，调用默认模板打印！');
	var re = Dict.正整数;///^[1-9]+[0-9*]*$/;   //判断字符串是否为数字     //判断正整数 /^[1-9]+[0-9]*]*$/   
	if (!re.test(num)){
		alert("'码单数'请输入正整数！");
		return false;
	}
	if(parseInt(num)>parseInt(obj.amount)){
		alert("'码单数'不能大于件数！");
		barcodeCtrl.focus();
		return false;
	}
	//LODOP.PRINT_INITA(0,0,'61mm','60mm',"默认的条码打印");
	//LODOP.SET_PRINT_PAGESIZE(1,610,600,'默认的条码打印');
	LODOP.PRINT_INITA(0,0,'90mm','55mm',"默认的条码打印");
	LODOP.SET_PRINT_PAGESIZE(1,900,550,'默认的条码打印'); 
	
	for(i = 1; i<=num; i++){
		LODOP.NewPage();
		CreateBarcodePage(obj, i, num);
	}
	
	printer_name = getPrinterName(printerName);
	if(printer_name != "" && printer_name!=null) {
		LODOP.SET_PRINTER_INDEXA(printer_name);
	}
	return LODOP.PRINT();
}

var barArray = new Array('128A','128B','128C','EAN8','EAN13', 'EAN128A','EAN128B','EAN128C','Code39','39Extended','2_5interleaved','2_5industrial','2_5matrix','UPC_A','UPC_E0','UPC_E1','UPCsupp2','UPCsupp5','Code93','93Extended MSI','PostNet','Codabar');

//90*60的条码纸
function CreateBarcodePage(obj, index, total) {
	var value;	
	LODOP.ADD_PRINT_TEXT(80,6,150,35,obj.rc_name);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	//alert(obj.rc_name+"  "+obj.rc_name.length);
	if(obj.rc_name.length<=5) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
	}
	else if(obj.rc_name.length<=6) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
	}
	else if(obj.rc_name.length<=7) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	}
	else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	value = obj.from_city+"-"+obj.to_city;
    LODOP.ADD_PRINT_TEXT(80,160,170,30,value);
	if(value.length<=8) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
	}
	else if(value.length<=9) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
	}
	else if(value.length<=10) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	}
	else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	
	var str = index+"/"+total+(obj.amount==total?"":"("+obj.amount+")");
	LODOP.ADD_PRINT_TEXT(149,160,170,30,str);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial");
	if(str.length<=12) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	}
	else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	istr = '000';
    istr = istr.substring(0,istr.length-index.toString().length)+index.toString();
	tstr = '000';
    tstr = tstr.substring(0,tstr.length-total.toString().length)+total.toString();
	LODOP.ADD_PRINT_BARCODE(9,15,310,63,"128C",obj.consign_code+istr+tstr);	
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Horient",3);
	
	LODOP.ADD_PRINT_RECT(3,5,328,173,0,1);
	/*LODOP.ADD_PRINT_TEXT(8,2,43,61,"温州\n鹿富\n物流");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);*/
	LODOP.ADD_PRINT_RECT(76,5,155,38,0,1);
	LODOP.ADD_PRINT_RECT(76,159,174,38,0,1);
	
	LODOP.ADD_PRINT_TEXT(149,6,150,37,obj.consign_code);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial");
	if(obj.consign_code.length<=11) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	}else if(obj.consign_code.length<=14) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_RECT(113,5,155,32,0,1);
	LODOP.ADD_PRINT_RECT(113,159,174,32,0,1);
	LODOP.ADD_PRINT_TEXT(120,6,150,30,obj.begin_time.substring(0, 10));
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	value = obj.busi_type+" "+obj.symbol +" 共"+ obj.amount+"件"
	LODOP.ADD_PRINT_TEXT(120,160,170,30,value);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	if(value.length <=10) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	
	
	LODOP.ADD_PRINT_RECT(144,5,155,32,0,1);
	LODOP.ADD_PRINT_IMAGE(177,5,32,28,"<img src='/images/logo_4_barcode.gif'/>");
	LODOP.ADD_PRINT_TEXT(182,38,113,25,"鹿富物流");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(182,150,180,25,obj.from_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial Narrow");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
}
  
//60*60的条码纸
function CreateBarcodePage600(obj, index, total) {	  
	LODOP.ADD_PRINT_TEXT(114,126,72,20,obj.from_city+"-");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.ADD_PRINT_TEXT(129,128,89,32,obj.to_city);
	if(obj.to_city.length<=3) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",18);
	}else if(obj.to_city.length<=4) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}else if(obj.to_city.length<=5) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.ADD_PRINT_TEXT(77,2,119,35,obj.rc_name);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	//alert(obj.rc_name+"  "+obj.rc_name.length);
	if(obj.rc_name.length<=3) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
	}else if(obj.rc_name.length<=4) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",18);
	}else if(obj.rc_name.length<=5) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);

	var str = index+"/"+total+(obj.amount==total?"":"("+obj.amount+")");
	LODOP.ADD_PRINT_TEXT(160,126,94,30,str);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial");
	if(str.length<=8) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	istr = '000';
    istr = istr.substring(0,istr.length-index.toString().length)+index.toString();
	tstr = '000';
    tstr = tstr.substring(0,tstr.length-total.toString().length)+total.toString();
	LODOP.ADD_PRINT_BARCODE(9,26,210,63,"128C",obj.consign_code+istr+tstr);	
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Horient",3);

	LODOP.ADD_PRINT_RECT(3,1,220,187,0,1);
	/*LODOP.ADD_PRINT_TEXT(8,2,43,61,"温州\n鹿富\n物流");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);*/
	LODOP.ADD_PRINT_RECT(76,1,125,38,0,1);
	LODOP.ADD_PRINT_RECT(76,125,96,38,0,1);
	LODOP.ADD_PRINT_TEXT(158,2,121,37,obj.consign_code);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial Narrow");
	if(obj.consign_code.length<=11) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	}else if(obj.consign_code.length<=14) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);

    LODOP.ADD_PRINT_TEXT(76,126,89,20,obj.busi_type);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	
	LODOP.ADD_PRINT_TEXT(92,131,89,20,obj.symbol);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	if(obj.symbol.length<=3) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	}else if(obj.symbol.length<=4) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	}else if(obj.symbol.length<=5) {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
	}else {
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
	}
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);

	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_RECT(113,1,125,42,0,1);
	LODOP.ADD_PRINT_RECT(113,125,96,42,0,1);
	LODOP.ADD_PRINT_TEXT(120,2,121,30,obj.begin_time.substring(0, 10));
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial Narrow");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",18);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_RECT(154,1,125,36,0,1);
	LODOP.ADD_PRINT_IMAGE(191,0,32,28,"<img src='/images/logo_4_barcode.gif'/>");
	LODOP.ADD_PRINT_TEXT(196,33,113,25,"鹿富物流");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_TEXT(199,120,98,25,obj.from_dept_phone);
	LODOP.SET_PRINT_STYLEA(0,"FontName","Arial Narrow");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
}

/* 
//转换成中文大写数字 
*/ 
function numberToChinese (num){ 
	if(!/^\d*(\.\d*)?$/.test(num)){alert("Number is wrong!"); return "Number is wrong!";} 

	var AA = new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖"); 
	var BB = new Array("","拾","佰","仟","万","亿","点","");  
  	var a = (""+ num).replace(/(^0*)/g, "").split("."), k = 0, re = ""; 
  	var re="";
 	for(var i=a[0].length-1; i>=0; i--) { 
		switch(k) { 
			case 0 : 
				re = BB[7] + re; 
				break; 
			case 4 : 
				if(!new RegExp("0{4}\\d{"+ (a[0].length-i-1) +"}$").test(a[0])) {
            		re = BB[4] + re;
				} 
				break; 
			case 8 : 
				re = BB[5] + re; BB[7] = BB[5]; 
				k = 0; 
				break; 
		}
		if(k%4 == 2 && a[0].charAt(i+2) != 0 && a[0].charAt(i+1) == 0) {
			re = AA[0] + re; 
		}
		if(a[0].charAt(i) != 0) {
			re = AA[a[0].charAt(i)] + BB[k%4] + re; 
			k++; 
		}
	} 
	if(a.length>1) { //加上小数部分(如果有小数部分) 
		re += BB[6]; 
		for(var i=0; i<a[1].length; i++) {
			re += AA[a[1].charAt(i)]; 
		}
	} 
	return re;
}


function ClearSelectCtrl(id){
	var ctrl = document.getElementById(id);
	if(ctrl == null) {
		return;
	}
	while(ctrl.options.length>0){
		ctrl.remove(ctrl.options.length-1);
	}
}

function go(url) {
	location=url;
}

function OpenMiddleHint(txt){
	var spanctrl=document.getElementById("notReSubmitDivHintTxt");
	if(spanctrl) {
		spanctrl.innerHTML = txt;
	}
	var div=document.getElementById("notReSubmitDiv");
	if(!div) {
		return;
	}
	var posY;
	var top=(window.screen.height-420)/2;
	if(window.innerHeight){
		posY=window.pageYOffset;
	}else if(document.documentElement&&document.documentElement.scrollTop){
		posY=document.documentElement.scrollTop;
	}else if(document.body){
		posY=document.body.scrollTop;
	}
	div.style.top=(posY+top)+"px";
	div.style.display="";
}

function CloseMiddleHint(){
	var div=document.getElementById("notReSubmitDiv");
	if(!div) {
		return;
	}
	div.style.display="none";
}
function MiddleCenterDiv(divName){
	var div=document.getElementById(divName);
	var posY;
	var top=(window.screen.height-420)/2;
	if(window.innerHeight){
		posY=window.pageYOffset;
	}else if(document.documentElement&&document.documentElement.scrollTop){
		posY=document.documentElement.scrollTop;
	}else if(document.body){
		posY=document.body.scrollTop;
	}
	div.style.top=(posY+top)+"px";
	div.style.display="";
}

//提交表单
function submitForm(theForm) {
	if(validateForm(theForm)){
		theForm.style.display="none";
		MiddleCenterDiv("notReSubmitDiv");
		return true;
	} else {
		return false;
	}
}

function selectAllFun(ckeckbox1,selectAllObj) {
	var chkNum;
	eval("chkNum="+ckeckbox1+".length;");
	for(i=0;i<chkNum;i++) {
		if(eval(ckeckbox1+"[i].type") == 'checkbox') {
			eval(ckeckbox1+"[i].checked=selectAllObj.checked");
		}
	}
}
function atLeastSelectOne(checkbox1){
var chkNum;
eval("chkNum="+checkbox1+".length;");
var result=false;
for(i=0;i<chkNum;i++){
var tmp;
var str="tmp="+checkbox1+"["+i+"].checked";
eval(str);
if(tmp){
result=true;
break;}
else
continue;}
return result;}

function submitDelForm(theForm,delcheckbox) {
	if(atLeastSelectOne(delcheckbox)){
		if(confirm("确定删除所选内容？")){
			MiddleCenterDiv("notReSubmitDiv");
			return true;
		}else {
			return false;
		}
	}else {
		alert("请在删除的内容前打勾选中，然后再按删除按钮！");
		return false;
	}
}

function confirmOperation(promptStr,url) {
	if(confirm(promptStr)){
		location=url;
		return true;
	}else {
		return false;
	}
}

function setValue(ctrlId,value) {
	cmd=ctrlId+".value="+value;
	eval(cmd);
}

function setValue(form,ctrlId,value){
	cmd=form+"."+ctrlId+".value='"+value+"'";
	eval(cmd);
}

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function ltrim(str){
	return str.replace(/(^\s*)/g,"");
}

function rtrim(str){
	return str.replace(/(\s*$)/g,"");
}

var xhr;
function createXhr(){
	xhr=false;
	try{
		xhr=new ActiveXObject('Msxml2.XMLHTTP');
	}catch(e){
		try{
			xhr=new ActiveXObject('Microsoft.XMLHTTP');
		}catch(e){
			xhr=new XMLHttpRequest();
		}
	}
	return xhr;
}

function postJSON(url,postStr,callBack){
	createXhr();
	xhr.open("POST",url,true);
	xhr.onreadystatechange=function(){
		eval(callBack)
	};
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
	xhr.send(encodeURI((postStr)));
}
function postAjax(url,postStr,callBack) {
	createXhr();
	xhr.open("POST",url,true);
	xhr.onreadystatechange=function(){
		eval(callBack)
	};
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
	xhr.send(encodeURI(encodeURI(postStr)));
}

function postAjaxAsync(url, postStr, callBack, async) {
	createXhr();
	xhr.open("POST", url, async);
	xhr.onreadystatechange =  function() {
		eval(callBack)
	};// handleStateChange();
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
	xhr.send(encodeURI(encodeURI(postStr)));//对JSON对象，调用一次就够了，可能是因为服务器端没有用getParameter()吧
}
String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
}

function numToChinese(number){
	var num = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	var cnum = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
	var result = '';
	for(i=0; i<num.length; i++){
		if(num[i] == number){
			result = cnum[i];
			break;
		}
	}
	return result;
}

function convertCurrency(currencyDigits) {
	var MAXIMUM_NUMBER=99999999999.99;
	var CN_ZERO="零";
	var CN_ONE="壹";
	var CN_TWO="贰";
	var CN_THREE="叁";
	var CN_FOUR="肆";
	var CN_FIVE="伍";
	var CN_SIX="陆";
	var CN_SEVEN="柒";
	var CN_EIGHT="捌";
	var CN_NINE="玖";
	var CN_TEN="拾";
	var CN_HUNDRED="佰";
	var CN_THOUSAND="仟";
	var CN_TEN_THOUSAND="万";
	var CN_HUNDRED_MILLION="亿";
	var CN_SYMBOL="";//人民币
	var CN_DOLLAR="元";
	var CN_TEN_CENT="角";
	var CN_CENT="分";
	var CN_INTEGER="整";
	var integral;
	var decimal;
	var outputCharacters;
	var parts;
	var digits,radices,bigRadices,decimals;
	var zeroCount;
	var i,p,d;
	var quotient,modulus;
	currencyDigits=currencyDigits.toString();
	if(currencyDigits=="") {
		alert("Empty input!");
		return "";
	}
	if(currencyDigits.match(/[^,.\d]/)!=null) {
		alert("Invalid characters in the input string!");
		return "";
	}
	currencyDigits=currencyDigits.replace(/,/g,"");
	currencyDigits=currencyDigits.replace(/^0+/,"");
	if(Number(currencyDigits)>MAXIMUM_NUMBER){
		alert("Too large a number to convert!");
		return "";
	}
	parts=currencyDigits.split(".");
	if(parts.length>1){
		integral=parts[0];
		decimal=parts[1];
		decimal=decimal.substr(0,2);
	}else {
		integral=parts[0];
		decimal="";
	}
	digits=new Array(CN_ZERO,CN_ONE,CN_TWO,CN_THREE,CN_FOUR,CN_FIVE,CN_SIX,CN_SEVEN,CN_EIGHT,CN_NINE);
	radices=new Array("",CN_TEN,CN_HUNDRED,CN_THOUSAND);
	bigRadices=new Array("",CN_TEN_THOUSAND,CN_HUNDRED_MILLION);
	decimals=new Array(CN_TEN_CENT,CN_CENT);
	outputCharacters="";
	if(Number(integral)>0) {
		zeroCount=0;
		for(i=0;i<integral.length;i++){
			p=integral.length-i-1;
			d=integral.substr(i,1);
			quotient=p/4;
			modulus=p%4;
			if(d=="0"){
				zeroCount++;
			}else {
				if(zeroCount>0){
					outputCharacters+=digits[0];
				}
				zeroCount=0;
				outputCharacters+=digits[Number(d)]+radices[modulus];
			}
			if(modulus==0&&zeroCount<4){
			outputCharacters+=bigRadices[quotient];
			}
		}
		outputCharacters+=CN_DOLLAR;
	}
	if(decimal!=""){
		for(i=0;i<decimal.length;i++){
			d=decimal.substr(i,1);
			if(d!="0"){
				outputCharacters+=digits[Number(d)]+decimals[i];
			}
		}
	}
	if(outputCharacters==""){
		outputCharacters=CN_ZERO+CN_DOLLAR;
	}
	if(decimal==""){
		outputCharacters+=CN_INTEGER;
	}
	outputCharacters=CN_SYMBOL+outputCharacters;
	return outputCharacters;
}

function formatNumber2(number, scale, showZero){
	var str = number.toFixed(scale);
	if(!showZero){
		if(str.indexOf(".")>=0){
			for(var t=str.length-1; t>str.indexOf("."); t--){
				if(str.charAt(t) == '0') {
					str = str.substring(0, t);
				}else {
					break;
				}
			}
		}
		if(str.indexOf(".")+1 == str.length) {
			str = str.substring(0, str.indexOf("."));
		}
		return str;
	}else {
		return str;
	}
}

function formatNumber(value,num){
	var a_int=parseInt(value*Math.pow(10,(num+1)));
	if(a_int==0) {
		return "0";
	}
	var a_str=a_int.toString();
	if(a_str.substring(0,1)!="-"){
		var b_str=a_str.substring(a_str.length-1,a_str.length);
		var b_int=parseInt(b_str);
		if(b_int>=5) {
			a_int=a_int+10;
		}
	}else {
		var b_str=a_str.substring(a_str.length-1,a_str.length);
		var b_int=parseInt(b_str);
		if(b_int>=5) {
			a_int=a_int-10;
		}
	}
	a_str=a_int.toString();
	var leftlength=a_str.length-(num+1);
	var rightlength=(num+1);
	var leftstr=a_str.substring(0,leftlength);
	var rightstr=a_str.substring(a_str.length-rightlength,a_str.length);
	rightstr=rightstr.substring(0,num);
	if(rightstr!=""&&rightstr!=null){
		nozero_i=-1;
		for(i=rightstr.length-1;i>=0;i--){
			if(rightstr.charAt(i)!='0'){
				nozero_i=i;
				break;
			}
		}
		if(nozero_i==-1) {
			rightstr="";
		}
		else {
			rightstr=rightstr.substring(0,nozero_i+1);
		}
	}
	var c_str=leftstr+"."+rightstr;
	if(rightstr=="") {
		c_str=leftstr;
	}
	if(value.toString().substring(0,3)=="-0.") {
		c_str="-0."+rightstr;
	}
	if(value.toString().substring(0,2)=="0.") {
		c_str="0."+rightstr;
	}
	return c_str;
}

function getToday(){
	var today=new Date();
	var day=today.getDate();
	if(day<10) {
		day="0"+day;
	}
	var month=today.getMonth()+1;
	if(month<10) {
		month="0"+month;
	}
	var year=today.getFullYear();
	var datestr=year+"-"+month+"-"+day;
	return datestr;
}
function Export2Excel(id) {
	var elTable = document.getElementById(id); 
    var oRangeRef = document.body.createTextRange(); 
    oRangeRef.moveToElementText( elTable ); 
    oRangeRef.execCommand( "Copy" ); 
    var appExcel = new ActiveXObject( "Excel.Application" ); 
	var xlBook = appExcel.Workbooks.Add();
	var ExcelSheet = xlBook.ActiveSheet;
	ExcelSheet.Paste();
	ExcelSheet.Rows("3:1000").RowHeight = 15;
    appExcel.Visible = true; 
    appExcel = null;
}

//formCheck
var Dict=new Object();
Dict.整数=/^-?\d+$/;
Dict.中文字符=/[\u4E00-\u9FA5]/g;
Dict.email=/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;　　
Dict.非负整数=/^\d+$/;　　
Dict.正整数=/^[0-9]*[1-9][0-9]*$/;　　
Dict.手机号=/^1[3|4|5|8][0-9]\d{4,8}$/;　　
Dict.数字 = /^[0-9]+$/;　
Dict.负整数=/^-[0-9]*[1-9][0-9]*$/;　　
Dict.非负浮点数=/^\d+(\.\d+)?$/;　　
Dict.正浮点数=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;　　
Dict.非正浮点数=/^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;　　
Dict.负浮点数=/^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;　　
Dict.浮点数=/^(-?\d+)(\.\d+)?$/;　　
Dict.英文=/^[A-Za-z]+$/;　　
Dict.大写英文=/^[A-Z]+$/;　　
Dict.小写英文=/^[a-z]+$/;　　
Dict.数字和英文=/^[A-Za-z0-9]+$/;　　
Dict.密码字符=/^[A-Za-z0-9!@*_-]+$/;　　
Dict.数字和英文和下划线=/^\w+$/;　　
Dict.日期=/^(\d{4})\-(\d{2})\-(\d{2})$/;
Dict.时间=/^(\d{4})\-(\d{2})\-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/;
Dict.年月=/^(\d{4})\-(\d{2})$/;
Dict.日期时间=/^(\d{4})\-(\d{2})\-(\d{2}) (\d{2}):(\d{2})$/;
Dict.邮政编码=/[1-9]\d{5}(?!\d)$/;
Dict.身份证=/^\d{15}(\d{2}[0-9Xx])?$/;
Dict.金额=/^[-]?[0-9]+$|^[-]?[0-9]+\.[0-9]{1,2}$/;
Dict.两位小数=/^[-]?[0-9]+$|^[-]?[0-9]+\.[0-9]{1,2}$/;
Dict.三位小数=/^[-]?[0-9]+$|^[-]?[0-9]+\.[0-9]{1,3}$/;
/*
Dict.金额=/^[-]?[0-9]+$|^[0-9]+\.[0-9]{1,2}$/;
Dict.两位小数=/^[-]?[0-9]+$|^[0-9]+\.[0-9]{1,2}$/;
Dict.三位小数=/^[-]?[0-9]+$|^[0-9]+\.[0-9]{1,3}$/;*/
function validateForm(theForm){
	return(validateEachElement(theForm));
	return false;
}
function alertNotResubmit(theForm){
	if(theForm.state=="submiting"){
		alert("数据正在提交，请勿重复提交表单！");
		return false;
	}
	if(confirm("确定提交？")){
		theForm.state="submiting";
		window.status="数据正在提交，请稍等...（请勿重复提交！）";
		document.title="数据正在提交，请稍等...（请勿重复提交！）";
		return true;
	}else
		return false;
}

function strlen(str){
	var i;
	var len;
	len=0;
	for(i=0;i<str.length;i++){
		if(str.charCodeAt(i)>255) {
			len+=2; 
		}else {
			len++;
		}
	}
	return len;
}

//表单验证
function validateEachElement(theForm){
	var elArr=theForm.elements;
	for(var i=0;i<elArr.length;i++){
		with(elArr[i]){
			if(elArr[i].disabled) {
			  continue;
			}
			var caption=elArr[i].getAttribute("caption");
			var theType=elArr[i].type;
			var value=elArr[i].value;
			var canNull=elArr[i].getAttribute("canNull");
			if(canNull==null) {
				canNull = elArr[i].getAttribute("cannull");
			}
			var minLen=elArr[i].getAttribute("minLen");
			if(minLen==null) {
				minLen = elArr[i].getAttribute("minlen");
			}
			var maxLen=elArr[i].getAttribute("maxLen");
			if(maxLen==null) {
				maxLen = elArr[i].getAttribute("maxlen");
			}
			var validator=elArr[i].getAttribute("validator");
			var maxNum=elArr[i].getAttribute("maxNum");
			if(maxNum==null) {
				maxNum = elArr[i].getAttribute("maxnum");
			}
			var minNum=elArr[i].getAttribute("minNum");
			if(minNum==null) {
				minNum = elArr[i].getAttribute("minnum");
			}
			if(!canNull||canNull=="") {
				canNull="true";
			}
			if(canNull=="false"&&(!value)&&(value=="")){
				alert(caption+"必须填写！");
				try{
					elArr[i].select();
				}catch(E){
				}
				return false;
			} else{
				if(validator&&(canNull=="false"||value!="")){
					var thePat=Dict[validator];
					var gotIt=thePat.exec(value);
					if(!gotIt){
					if(validator.indexOf("浮点数")>=0) {
						alert(caption+" 输入值必须是"+validator.replace("浮点数","数值")+"格式。请修改！");
					}
					else if(validator.indexOf("金额")>=0) {
						alert(caption+" 输入值必须是金额（小数点后至多保留两位小数）。请修改！");
					}
					else if(validator.indexOf("两位小数")>=0) {
						alert(caption+" 输入值必须是数值格式（小数点后至多保留两位小数）。请修改！"); 
					}
					else if(validator.indexOf("三位小数")>=0) {
						alert(caption+" 输入值必须是数值格式（小数点后至多保留三位小数）。请修改！"); 
					}
					else {
						alert(caption+" 输入值必须是"+validator+"格式。请修改！");
					}
					elArr[i].select();
					return false;
				}
				}
			}
			if(maxNum){
				if(parseFloat(value,10)>parseFloat(maxNum,10)){
					alert(caption+" 不能大于"+parseFloat(maxNum,10)+"！");
					elArr[i].select();
					return false;
				}
			}
			if(minNum){
				if(parseFloat(value,10)<parseFloat(minNum,10)){
					alert(caption+" 不能小于"+parseFloat(minNum,10)+"！");
					elArr[i].select();
					return false;
				}
			}
			//alert(value+"  "+minLen);
			if((value!="")&&minLen){
				var thelen=strlen(value);
				if(thelen<parseInt(minLen)){
					alert(caption+" 长度不能小于"+parseInt(minLen)+"个字节！（现为"+thelen+"个字节）");
					elArr[i].select();
					return false;
				}
			}
			if(maxLen){
				var thelen=strlen(value);
				if(thelen>parseInt(maxLen)){
					alert(caption+" 长度不能大于"+parseInt(maxLen)+"个字节！（现为"+thelen+"个字节）");
					elArr[i].select();
					return false;
				}
			}
		}
	}
	return true;
}

function RegCheck(reg,str) {
	if( reg.test(str)) {
		return true;
	}
	return false;
}
/*
http:
2008-07-15
Public Domain.
NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.
See http:
This file creates a global JSON object containing two methods:stringify
and parse.
JSON.stringify(value,replacer,space)
value any JavaScript value,usually an object or array.
replacer an optional parameter that determines how object
values are stringified for objects. It can be a
function or an array.
space an optional parameter that specifies the indentation
of nested structures. If it is omitted,the text will
be packed without extra whitespace. If it is a number,
it will specify the number of spaces to indent at each
level. If it is a string(such as '\t' or '&nbsp;'),
it contains the characters used to indent at each level.
This method produces a JSON text from a JavaScript value.
When an object value is found,if the object contains a toJSON
method,its toJSON method will be called and the result will be
stringified. A toJSON method does not serialize:it returns the
value represented by the name/value pair that should be serialized,
or undefined if nothing should be serialized. The toJSON method
will be passed the key associated with the value,and this will be
bound to the object holding the key.
For example,this would serialize Dates as ISO strings.
Date.prototype.toJSON=function(key){
function f(n){
return n<10?'0'+n:n;}
return this.getUTCFullYear()+'-'+
f(this.getUTCMonth()+1)+'-'+
f(this.getUTCDate())+'T'+
f(this.getUTCHours())+':'+
f(this.getUTCMinutes())+':'+
f(this.getUTCSeconds())+'Z';};
You can provide an optional replacer method. It will be passed the
key and value of each member,with this bound to the containing
object. The value that is returned from your method will be
serialized. If your method returns undefined,then the member will
be excluded from the serialization.
If the replacer parameter is an array,then it will be used to
select the members to be serialized. It filters the results such
that only members with keys listed in the replacer array are
stringified.
Values that do not have JSON representations,such as undefined or
functions,will not be serialized. Such values in objects will be
dropped;in arrays they will be replaced with null. You can use
a replacer function to replace those with JSON values.
JSON.stringify(undefined)returns undefined.
The optional space parameter produces a stringification of the
value that is filled with line breaks and indentation to make it
easier to read.
If the space parameter is a non-empty string,then that string will
be used for indentation. If the space parameter is a number,then
the indentation will be that many spaces.
Example:
text=JSON.stringify(['e',{pluribus:'unum'}]);
text=JSON.stringify(['e',{pluribus:'unum'}],null,'\t');
text=JSON.stringify([new Date()],function(key,value){
return this[key]instanceof Date?
'Date('+this[key]+')':value;});
JSON.parse(text,reviver)
This method parses a JSON text to produce an object or array.
It can throw a SyntaxError exception.
The optional reviver parameter is a function that can filter and
transform the results. It receives each of the keys and values,
and its return value is used instead of the original value.
If it returns what it received,then the structure is not modified.
If it returns undefined then the member is deleted.
Example:
myData=JSON.parse(text,function(key,value){
var a;
if(typeof value==='string'){
a=/^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
if(a){
return new Date(Date.UTC(+a[1],+a[2]-1,+a[3],+a[4],+a[5],+a[6]));}}
return value;});
myData=JSON.parse('["Date(09/09/2001)"]',function(key,value){
var d;
if(typeof value==='string'&&
value.slice(0,5)==='Date('&&
value.slice(-1)===')'){
d=new Date(value.slice(5,-1));
if(d){
return d;}}
return value;});
This is a reference implementation. You are free to copy,modify,or
redistribute.
This code should be minified before deployment.
See http:
USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
NOT CONTROL.*/
if(!this.JSON){
	JSON=function(){
		function f(n){
			return n<10?'0'+n:n;
		}
		Date.prototype.toJSON=function(key){
			return this.getUTCFullYear()+'-'+f(this.getUTCMonth()+1)+'-'+f(this.getUTCDate())+'T'
			+f(this.getUTCHours())+':'+f(this.getUTCMinutes())+':'+f(this.getUTCSeconds())+'Z';
	};
	String.prototype.toJSON=Number.prototype.toJSON=Boolean.prototype.toJSON=function(key){
		return this.valueOf();
	};
	var cx=/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
	escapeable=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,gap,indent,
	meta={
	'\b':'\\b',
	'\t':'\\t',
	'\n':'\\n',
	'\f':'\\f',
	'\r':'\\r',
	'"':'\\"',
	'\\':'\\\\'},rep;

function quote(string){
	escapeable.lastIndex=0;
	return escapeable.test(string)?'"'+string.replace(escapeable,function(a){
		var c=meta[a];
		if(typeof c==='string'){
		return c;
	}
	return '\\u'+('0000'+(+(a.charCodeAt(0))).toString(16)).slice(-4);})+'"':'"'+string+'"';
}
function str(key,holder){
	var i,k,v,length,mind=gap,partial,value=holder[key];
	
	if(value&&typeof value==='object'&& typeof value.toJSON==='function'){
		value=value.toJSON(key);
	}
	if(typeof rep==='function'){
		value=rep.call(holder,key,value);
	}
	switch(typeof value){
		case 'string':
			return quote(value);
		case 'number':
			return isFinite(value)?String(value):'null';
		case 'boolean':
		case 'null':
			return String(value);
		case 'object':
			if(!value){
				return 'null';
			}
			gap+=indent;
			partial=[];
			if(typeof value.length==='number'&&!(value.propertyIsEnumerable('length'))){
				length=value.length;
				for(i=0;i<length;i+=1){
					partial[i]=str(i,value)||'null';
				}
				v=partial.length===0?'[]':gap?'[\n'+gap+partial.join(',\n'+gap)+'\n'+mind+']':'['+partial.join(',')+']';
				gap=mind;
				return v;
			}
			
			if(rep&&typeof rep==='object'){
				length=rep.length;
				for(i=0;i<length;i+=1){
					k=rep[i];
					if(typeof k==='string'){
						v=str(k,value);
						if(v){
							partial.push(quote(k)+(gap?': ':':')+v);
						}
					}
				}
			}else {
				for(k in value){
					if(Object.hasOwnProperty.call(value,k)){
						v=str(k,value);
						if(v) {
							partial.push(quote(k)+(gap?': ':':')+v);
						}
					}
				}
			}
			v=partial.length===0?'{}':gap?'{\n'+gap+partial.join(',\n'+gap)+'\n'+mind+'}':'{'+partial.join(',')+'}';
			gap=mind;
			return v;
	}
}

return{
	stringify:function(value,replacer,space){
	var i;
	gap='';
	indent='';
	if(typeof space==='number') {
		for(i=0;i<space;i+=1){
			indent+=' ';
		}
	}else if(
		typeof space==='string') {
			indent=space;
		}
		rep=replacer;
		if(replacer&&typeof replacer!=='function'&&(typeof replacer!=='object'||typeof replacer.length!=='number')){
			throw new Error('JSON.stringify');
		}
		return str('',{'':value});
	},
	parse:function(text,reviver){
		var j;
		function walk(holder,key){
			var k,v,value=holder[key];
			if(value&&typeof value==='object'){
				for(k in value){
					if(Object.hasOwnProperty.call(value,k)){
						v=walk(value,k);
						if(v!==undefined){
							value[k]=v;
						}else {
							delete value[k];
						}
					}
				}
			}
			return reviver.call(holder,key,value);
		}
		cx.lastIndex=0;
		if(cx.test(text)){
			text=text.replace(cx,function(a){
				return '\\u'+('0000'+(+(a.charCodeAt(0))).toString(16)).slice(-4);});
			}
			if(/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,'@').
				replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,']').
				replace(/(?:^|:|,)(?:\s*\[)+/g,''))){
					j=eval('('+text+')');
					return typeof reviver==='function'?
					walk({'':j},''):j;
			}
			throw new SyntaxError('JSON.parse');
			}
		};
	}();
}

function chkall(input1,input2) {
    var objForm = document.forms[input1];
    var objLen = objForm.length;
    for (var iCount = 0; iCount < objLen; iCount++) {
        if (input2.checked == true) {
            if (objForm.elements[iCount].type == "checkbox" && (objForm.elements[iCount].disabled == false)) {
                objForm.elements[iCount].checked = true;
            }
        }else {
            if (objForm.elements[iCount].type == "checkbox" && (objForm.elements[iCount].disabled == false)) {
                objForm.elements[iCount].checked = false;
            }
        }
    }
}
  
  
function previewImage(_file)  
{  
  if (_file.files && _file.files[0])  
  {  
    var srcs = getObjectURL(_file.files[0]); //获取路径   
    $(_file).parent('div').css('background','url('+srcs+') no-repeat center');
    $(_file).parent('div').css('background-size','100% 100%');
    $(_file).prev('span').removeClass('myhide');
  }  
}  

function getObjectURL(file) {
  var url = null;
  if (window.createObjectURL != undefined) {
   url = window.createObjectURL(file)
  } else if (window.URL != undefined) {
   url = window.URL.createObjectURL(file)
  } else if (window.webkitURL != undefined) {
   url = window.webkitURL.createObjectURL(file)
  }
  return url
 };
 
function delate(_id)
{
    $("#file"+_id).remove();
    idArray.length=0;
    id = _id;
    idArray.push(id);
    for(var i=0;i<idArray.length;i++)
    {
        $('#fileContainer').append('<div class="file" style="margin-left:10px;background: url(images/photo.png) no-repeat center;" id="file'+idArray[i]+'"><span class="fa fa-minus-circle delateIcon ulev1 myhide" id="'+idArray[i]+'" onclick="delate(this.id)"></span><input type="file"  id="fileID'+idArray[i]+'" name="fileID'+idArray[i]+'" onchange="previewImage(this);"></div>');
    }
    
} 

 
function delate1(obj)
{
    $("#file"+obj.id).remove();
	$('#fileContainer').append('<input type="hidden" name="delId" value="'+$(obj).data("id")+'">');//获取要删除图片的id,方便后台删除
    idArray.length=0;
    id = obj.id;
    idArray.push(id);
    for(var i=0;i<idArray.length;i++)
    {
        $('#fileContainer').append('<div class="file" style="margin-left:10px;background: url(images/photo.png) no-repeat center;" id="file'+idArray[i]+'"><span class="fa fa-minus-circle delateIcon ulev1 myhide" id="'+idArray[i]+'" onclick="delate(this.id)"></span><input type="file"  id="fileID'+idArray[i]+'" name="fileID'+idArray[i]+'" onchange="previewImage(this);"></div>');
    }
    
}  

function showPic(pic,index)
	{
	   if(pic!=''&&pic!=null)
	   {
	     $("#file0"+index).css('background','url('+pic+') no-repeat center');
         $("#file0"+index).css('background-size','100% 100%');
         $("#0"+index).removeClass('myhide');
	}	
	}
	
	
function showpPic(pic,index)
{
	   if(pic!=''&&pic!=null)
	   {
	     $("#pfile0"+index).css('background','url('+pic+') no-repeat center');
         $("#pfile0"+index).css('background-size','100% 100%');
         $("#p0"+index).removeClass('myhide');
	}	
	}
	
function picScan(name,index){
	
	window.open ('/project/gaopaiyi.jsp?nameSpace='+name+'&index='+index, 'resource', 'height=600, width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

	function showBigPic(path){
			artDialog({title:'图片查看', content:'<img width="1000" src="'+path+'" />', fixed:false});
			return false;
		}	
		

	