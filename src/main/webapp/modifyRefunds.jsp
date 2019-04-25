<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="webfood.utils.Arith" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
    <head>
    <title>申请退款</title>
    <meta charset="utf-8">
    <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" href="css/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="css/ui-box.css">
    <link rel="stylesheet" href="css/ui-base.css">
    <link rel="stylesheet" href="css/ui-color.css">
    <link rel="stylesheet" href="css/appcan.icon.css">
    <link rel="stylesheet" href="css/appcan.control.css">
    <link rel="stylesheet" href="css/newStyle.css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" />

    <script src="js/jquery.min.js"></script>
    <style>
    .radiobox input[type=radio]:before {
    content: "\f096";
    }

    .radiobox input[type=radio]:checked:before {
    content: "\f14a";
    }
    </style>
    </head>
    <body class="ub ub-fh ub-fv ub-ver ub-f1 bc-bg" ontouchstart>
    <!--header开始-->
    <div id="header" class="uh ub border_b umh6 ub-ac">
    <div class="nav-btn green_color" id="nav-left"  onclick="window.history.back();">
    <div class="fa fa-angle-left fa-2x"></div>
    <div class="ub umar-l">返回</div>
    </div>
    <h1 class="ut ub-f1 ulev-3 ut-s tx-c" tabindex="0">申请退款</h1>
    <div class="nav-btn nav-bt green_color umar-r" id="nav-right"></div>
    </div>
    <!--header结束-->

    <!--content开始-->
    <form action="modifyRefunds1" method="post" name="form1" enctype="multipart/form-data">
    <section class="ub ub-ver ub-f1 ub-fv sc-bg" style="overflow-y: auto">
    <div class="ub ub-ver">
    <div class="ub ub-f1 uinn umh4 ub-ac">退款类型：</div>
    <div class="ub ub-f1 uinn bc-bg border_b umh4 ub-ac">
    <div class="ub ub-f1 uinn deep_gray_c">我要退款</div>
    <div class="radiobox umar-r" name="">
    <input type="radio" class="uabs ub-con" name="type" value="1" checked>
    </div>
    </div>
    <div class="ub ub-f1 uinn bc-bg border_b umh4 ub-ac">
    <div class="ub ub-f1 uinn deep_gray_c">我要退货</div>
    <div class="radiobox umar-r" name="">
    <input type="radio" class="uabs ub-con" name="type" value="2">
    </div>
    </div>


    <div class="ub ub-f1 uinn umh4 ub-ac">退款说明：</div>
    </div>
    <div class="ub ub-ver bc-bg">
    <div class="ub ub-f1">
    <textarea class="ub ub-f1 uinn myComment" name="reason" maxlength="500" style=" font-size:1em;"  placeholder="简单阐述退款原因(最多输入500字)~">${vo.refunds.reason}</textarea>
    </div>
    <div class="ub ub-f1 umar-t border_b uinn" id="fileContainer">

    <div class="file" style="margin-left:10px;background: url(images/photo.png) no-repeat center;" id="file01">
    <span class="fa fa-minus-circle delateIcon ulev1 myhide" id='01' onclick="delate(this.id)"></span>
    <input class="" type="file"  id="fileID01" name="fileID01" onchange="previewImage(this);">
    </div>
    <div class="file" style="margin-left:10px;background: url(images/photo.png) no-repeat center;" id="file02">
    <span class="fa fa-minus-circle delateIcon ulev1 myhide" id='02' onclick="delate(this.id)"></span>
    <input class="" type="file"  id="fileID02" name="fileID02" onchange="previewImage(this);">
    </div>
    <div class="file" style="margin-left:10px;background: url(images/photo.png) no-repeat center;" id="file03">
    <span class="fa fa-minus-circle delateIcon ulev1 myhide" id='03' onclick="delate(this.id)"></span>
    <input class="" type="file"  id="fileID03" name="fileID03" onchange="previewImage(this);">
    </div>
    </div>


    </div>
    <input type="hidden" name="id" value="${vo.refunds.id}">
    <input type="hidden" name="orderId" value="${vo.refunds.orderId}">
    <div class="ub uinn ulev1 uc-a ub-ac ub-pc mybtn umar-r" style="width: 80%;margin:0 auto;margin-top:3em" id="submit" >提交申请</div>
    </section>
    </form>
    <!--content结束-->
    <footer id="footer"  class="ub ubt basic-border">

    </footer>
    </body>
    <script>
    window.onload = function(){

    }

    var idArray = new Array();
    idArray.length=0;
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


    $(".star").on('click',function(){
    if($(this).hasClass('deep_gray_c'))
    {
    $(this).prevAll('span').removeClass('deep_gray_c');
    $(this).removeClass('deep_gray_c');
    $(this).prevAll('span').removeClass('fa-heart-o');
    $(this).removeClass('fa-heart-o');
    $(this).prevAll('span').addClass('orange_color');
    $(this).addClass('orange_color');
    $(this).prevAll('span').addClass('fa-heart');
    $(this).addClass('fa-heart');
    }
    else
    {
    $(this).nextAll('span').addClass('deep_gray_c');
    $(this).nextAll('span').addClass('fa-heart-o');
    $(this).nextAll('span').removeClass('orange_color');
    $(this).nextAll('span').removeClass('fa-heart');

    $(this).addClass('deep_gray_c');
    $(this).addClass('fa-heart-o');
    $(this).removeClass('orange_color');
    $(this).removeClass('fa-heart');

    }
    });


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


    $('#submit').on('click',function(){


      form1.submit();

    });


    </script>
    </html>