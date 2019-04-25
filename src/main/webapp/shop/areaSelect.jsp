<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<title></title>
        <meta charset="utf-8">
        <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" href="css/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="css/ui-box.css">
        <link rel="stylesheet" href="css/ui-base.css">
        <link rel="stylesheet" href="css/ui-color.css">
        <link rel="stylesheet" href="css/appcan.icon.css">
        <link rel="stylesheet" href="css/appcan.control.css">
        <link rel="stylesheet" href="css/newStyle.css" />

<style type="text/css">


.letter{width:3%;line-height:1.5em;font-size:1.2em;float:right;top:8em;right:0.5em;text-align:center;position: fixed;padding-right:3%}
.letter ul{list-style-type:none;}
.letter ul li a{text-decoration:none;}
.city{width:90%;overflow:hidden;float: left}
.city-list{width:100%;overflow:hidden;}
.city-list .city-letter{color:#6e6e6e;font-size:1.2em;display:inline-block;padding-top:1em;padding-bottom:1em;border-bottom:1px solid #e8ecf1;width:100%;padding-left:1.2em;background-color: #EEEEEE}
.city-list p{color:gray;width:100%;height:3em;line-height:3em;border-bottom:1px solid #e8ecf1;cursor:pointer;text-indent: 1.2em}

ul,li,dl,dt,dd{display:block;list-style:none;}
img{border:0;max-width:100%;height: auto;}
.clear{background:none;border:0;clear:both;display:block;float:none;font-size:0;overflow:hidden;visibility:hidden;width:0;height:0;}
a{text-decoration:none;outline:none;color:rgb(104,174,0);}
.fl{float:left;}
.fr{float:right;}

</style>

</head>

<body  class="um-vp " ontouchstart>
    <div id="page_0" class="up ub ub-ver bc-bg" tabindex="0">
    <!--header开始-->
    <div id="header" class="uh ub border_b">
		<input type="hidden" value="?id=${address.id}&userid=${address.userid}&username=${address.username}&truename=${address.truename}&mobile=${address.mobile}&areainfo=${address.areainfo}" name="paraminfo" />
        <div class="nav-btn green_color ulev1" id="nav-left" onClick="window.history.back();">
            <div class="fa fa-angle-left fa-2x "></div>
            <div class="ub ub-ac ub-pc umar-l" style="margin-top:0.3em">返回</div>
        </div>
        <h1 class="ut ub-f1 ulev1 ut-s tx-c" tabindex="0">收货地区</h1>
        <div class="nav-btn nav-bt green_color" id="nav-right">
            <div class="fa fa-ellipsis-h ulev2"></div>
        </div>
        <div class="backChoose" style="display:none">
            <div class="Item back1" onClick="location.href='index.html';$('.backChoose').hide();">首页</div>
            <div class="Item back2" onClick="location.href='sort.html';$('.backChoose').hide();">分类</div>
            <div class="Item back3" onClick="location.href='shopping_cart.html';$('.backChoose').hide();">购物车</div>
            <div class="Item back4" onClick="location.href='mine.html';$('.backChoose').hide();">我的</div>
        </div>
    </div>
    <!--header结束--><!--content开始-->
    <section class="ub ub-ver ub-f1 ub-fv sc-bg" style="overflow-y: auto">
        <div class="ub bc-bg uinn " style="padding-left:3em;padding-right:3em">
            <div class="search_c uinput ub basic-border uba uc-a2 ub-f1 uinn" >
                <div class="uinn fa fa-search search_fontc"></div>
                <input placeholder="请输入地址名称" id="search" type="text" class="ub-f1" onChange="search()" onKeyUp="search()">
            </div>
        </div>
        
        <div class="container">
          <div class="city bc-bg">
              <div class="city-list"><span class="city-letter" id="">热门城市</span>
              <p data-id="110100">北京市</p>
              <p data-id="310100">上海市</p>
              <p data-id="440100">广州市</p>
              <p data-id="440300">深圳市</p>
              <p data-id="120100">天津市</p>
              <p data-id="330100">杭州市</p>
              <p data-id="500100">重庆市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="A1">A</span>
              <p data-id="210300">鞍山市</p>
              <p data-id="152900">阿拉善盟</p>
              <p data-id="340800">安庆市</p>
              <p data-id="410500">安阳市</p>
              <p data-id="542500">阿里地区</p>
              <p data-id="610900">安康市</p>
              <p data-id="520400">安顺市</p>
              <p data-id="513200">阿坝藏族羌族自治州</p>
              <p data-id="659002">阿拉尔市</p>
              <p data-id="652900">阿克苏地区</p>
              <p data-id="820100">澳门特别行政区</p>
              <p data-id="654300">阿勒泰地区</p>
            </div>
            <div class="city-list"><span class="city-letter" id="B1">B</span>
              <p data-id="220800">白城市</p>
              <p data-id="150200">包头市</p>
              <p data-id="150800">巴彦淖尔市</p>
              <p data-id="130600">保定市</p>
              <p data-id="210500">本溪市</p>
              <p data-id="220600">白山市</p>
              <p data-id="341600">亳州市</p>
              <p data-id="340300">蚌埠市</p>
              <p data-id="371600">滨州市</p>
              <p data-id="620400">白银市</p>
              <p data-id="610300">宝鸡市</p>
              <p data-id="530500">保山市</p>
              <p data-id="469030">白沙黎族自治县</p>
              <p data-id="451000">百色市</p>
              <p data-id="522401">毕节市</p>
              <p data-id="450500">北海市</p>
              <p data-id="511900">巴中市</p>
              <p data-id="469035">保亭黎族苗族自治县</p>
              <p data-id="652800">巴音郭楞蒙古自治州</p>
              <p data-id="652700">博尔塔拉蒙古自治州</p>
              <p data-id="110100">北京市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="C1">C</span>
              <p data-id="140400">长治市</p>
              <p data-id="130900">沧州市</p>
              <p data-id="320400">常州市</p>
              <p data-id="330282">慈溪市</p>
              <p data-id="320581">常熟市</p>
              <p data-id="130800">承德市</p>
              <p data-id="150400">赤峰市</p>
              <p data-id="220100">长春市</p>
              <p data-id="431000">郴州市</p>
              <p data-id="430100">长沙市</p>
              <p data-id="341100">滁州市</p>
              <p data-id="430700">常德市</p>
              <p data-id="341400">巢湖市</p>
              <p data-id="341700">池州市</p>
              <p data-id="469027">澄迈县</p>
              <p data-id="451400">崇左市</p>
              <p data-id="469031">昌江黎族自治县</p>
              <p data-id="532300">楚雄彝族自治州</p>
              <p data-id="445100">潮州市</p>
              <p data-id="500100">重庆市</p>
              <p data-id="510100">成都市</p>
              <p data-id="542100">昌都地区</p>
              <p data-id="652300">昌吉回族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="D1">D</span>
              <p data-id="232700">大兴安岭地区</p>
              <p data-id="140200">大同市</p>
              <p data-id="230600">大庆市</p>
              <p data-id="321181">丹阳市</p>
              <p data-id="210200">大连市</p>
              <p data-id="210600">丹东市</p>
              <p data-id="370500">东营市</p>
              <p data-id="371400">德州市</p>
              <p data-id="511700">达州市</p>
              <p data-id="532900">大理白族自治州</p>
              <p data-id="469003">儋州市</p>
              <p data-id="469025">定安县</p>
              <p data-id="533400">迪庆藏族自治州</p>
              <p data-id="510600">德阳市</p>
              <p data-id="469007">东方市</p>
              <p data-id="533100">德宏傣族景颇族自治州</p>
              <p data-id="441900">东莞市</p>
              <p data-id="621100">定西市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="E1">E</span>
              <p data-id="150600">鄂尔多斯市</p>
              <p data-id="420700">鄂州市</p>
              <p data-id="422800">恩施土家族苗族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="F1">F</span>
              <p data-id="210900">阜新市</p>
              <p data-id="210400">抚顺市</p>
              <p data-id="350181">福清市</p>
              <p data-id="341200">阜阳市</p>
              <p data-id="370983">肥城市</p>
              <p data-id="361000">抚州市</p>
              <p data-id="350100">福州市</p>
              <p data-id="440600">佛山市</p>
              <p data-id="450600">防城港市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="G1">G</span>
              <p data-id="440100">广州市</p>
              <p data-id="360700">赣州市</p>
              <p data-id="510800">广元市</p>
              <p data-id="511600">广安市</p>
              <p data-id="450300">桂林市</p>
              <p data-id="450800">贵港市</p>
              <p data-id="520100">贵阳市</p>
              <p data-id="513300">甘孜藏族自治州</p>
              <p data-id="623000">甘南藏族自治州</p>
              <p data-id="640400">固原市</p>
              <p data-id="632600">果洛藏族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="H1">H</span>
              <p data-id="231100">黑河市</p>
              <p data-id="211400">葫芦岛市</p>
              <p data-id="330481">海宁市</p>
              <p data-id="320800">淮安市</p>
              <p data-id="131100">衡水市</p>
              <p data-id="150100">呼和浩特市</p>
              <p data-id="330500">湖州市</p>
              <p data-id="230400">鹤岗市</p>
              <p data-id="150700">呼伦贝尔市</p>
              <p data-id="230100">哈尔滨市</p>
              <p data-id="130400">邯郸市</p>
              <p data-id="330100">杭州市</p>
              <p data-id="410600">鹤壁市</p>
              <p data-id="371700">菏泽市</p>
              <p data-id="420200">黄石市</p>
              <p data-id="431200">怀化市</p>
              <p data-id="340600">淮北市</p>
              <p data-id="421100">黄冈市</p>
              <p data-id="430400">衡阳市</p>
              <p data-id="340100">合肥市</p>
              <p data-id="340400">淮南市</p>
              <p data-id="341000">黄山市</p>
              <p data-id="451200">河池市</p>
              <p data-id="460100">海口市</p>
              <p data-id="441600">河源市</p>
              <p data-id="532500">红河哈尼族彝族自治州</p>
              <p data-id="441300">惠州市</p>
              <p data-id="610700">汉中市</p>
              <p data-id="451100">贺州市</p>
              <p data-id="632800">海西蒙古族藏族自治州</p>
              <p data-id="632100">海东市</p>
              <p data-id="632300">黄南藏族自治州</p>
              <p data-id="652200">哈密地区</p>
              <p data-id="632200">海北藏族自治州</p>
              <p data-id="653200">和田地区</p>
              <p data-id="632500">海南藏族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="J1">J</span>
              <p data-id="210700">锦州市</p>
              <p data-id="330700">金华市</p>
              <p data-id="140700">晋中市</p>
              <p data-id="320281">江阴市</p>
              <p data-id="220200">吉林市</p>
              <p data-id="230800">佳木斯市</p>
              <p data-id="230300">鸡西市</p>
              <p data-id="330400">嘉兴市</p>
              <p data-id="140500">晋城市</p>
              <p data-id="350582">晋江市</p>
              <p data-id="370282">即墨市</p>
              <p data-id="360800">吉安市</p>
              <p data-id="370100">济南市</p>
              <p data-id="420800">荆门市</p>
              <p data-id="410800">焦作市</p>
              <p data-id="370800">济宁市</p>
              <p data-id="410881">济源市</p>
              <p data-id="421000">荆州市</p>
              <p data-id="360400">九江市</p>
              <p data-id="360200">景德镇市</p>
              <p data-id="445200">揭阳市</p>
              <p data-id="620300">金昌市</p>
              <p data-id="440700">江门市</p>
              <p data-id="620200">嘉峪关市</p>
              <p data-id="620900">酒泉市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="K1">K</span>
              <p data-id="320583">昆山市</p>
              <p data-id="410200">开封市</p>
              <p data-id="530100">昆明市</p>
              <p data-id="650200">克拉玛依市</p>
              <p data-id="653000">克孜勒苏柯尔克孜自治州</p>
              <p data-id="653100">喀什地区</p>
            </div>
            <div class="city-list"><span class="city-letter" id="L1">L</span>
              <p data-id="141000">临汾市</p>
              <p data-id="131000">廊坊市</p>
              <p data-id="211000">辽阳市</p>
              <p data-id="220400">辽源市</p>
              <p data-id="141100">吕梁市</p>
              <p data-id="320700">连云港市</p>
              <p data-id="371200">莱芜市</p>
              <p data-id="411100">漯河市</p>
              <p data-id="331100">丽水市</p>
              <p data-id="341500">六安市</p>
              <p data-id="431300">娄底市</p>
              <p data-id="350800">龙岩市</p>
              <p data-id="370681">龙口市</p>
              <p data-id="371300">临沂市</p>
              <p data-id="410300">洛阳市</p>
              <p data-id="371500">聊城市</p>
              <p data-id="530700">丽江市</p>
              <p data-id="451300">来宾市</p>
              <p data-id="510500">泸州市</p>
              <p data-id="530900">临沧市</p>
              <p data-id="469033">乐东黎族自治县</p>
              <p data-id="511100">乐山市</p>
              <p data-id="620100">兰州市</p>
              <p data-id="450200">柳州市</p>
              <p data-id="513400">凉山彝族自治州</p>
              <p data-id="469034">陵水黎族自治县</p>
              <p data-id="542600">林芝地区</p>
              <p data-id="469028">临高县</p>
              <p data-id="540100">拉萨市</p>
              <p data-id="520200">六盘水市</p>
              <p data-id="621200">陇南市</p>
              <p data-id="622900">临夏回族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="M1">M</span>
              <p data-id="231000">牡丹江市</p>
              <p data-id="340500">马鞍山市</p>
              <p data-id="510700">绵阳市</p>
              <p data-id="511400">眉山市</p>
              <p data-id="440900">茂名市</p>
              <p data-id="441400">梅州市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="N1">N</span>
              <p data-id="320100">南京市</p>
              <p data-id="330200">宁波市</p>
              <p data-id="320600">南通市</p>
              <p data-id="360100">南昌市</p>
              <p data-id="411300">南阳市</p>
              <p data-id="350700">南平市</p>
              <p data-id="350900">宁德市</p>
              <p data-id="350583">南安市</p>
              <p data-id="542400">那曲地区</p>
              <p data-id="450100">南宁市</p>
              <p data-id="511300">南充市</p>
              <p data-id="511000">内江市</p>
              <p data-id="533300">怒江傈僳族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="P1">P</span>
              <p data-id="211100">盘锦市</p>
              <p data-id="360300">萍乡市</p>
              <p data-id="410400">平顶山市</p>
              <p data-id="410900">濮阳市</p>
              <p data-id="350300">莆田市</p>
              <p data-id="510400">攀枝花市</p>
              <p data-id="530800">普洱市</p>
              <p data-id="620800">平凉市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="Q1">Q</span>
              <p data-id="130300">秦皇岛市</p>
              <p data-id="230200">齐齐哈尔市</p>
              <p data-id="230900">七台河市</p>
              <p data-id="350500">泉州市</p>
              <p data-id="429005">潜江市</p>
              <p data-id="370200">青岛市</p>
              <p data-id="330800">衢州市</p>
              <p data-id="441800">清远市</p>
              <p data-id="522700">黔南布依族苗族自治州</p>
              <p data-id="450700">钦州市</p>
              <p data-id="530300">曲靖市</p>
              <p data-id="522300">黔西南布依族苗族自治州</p>
              <p data-id="621000">庆阳市</p>
              <p data-id="522600">黔东南苗族侗族自治州</p>
              <p data-id="469002">琼海市</p>
              <p data-id="469036">琼中黎族苗族自治县</p>
            </div>
            <div class="city-list"><span class="city-letter" id="R1">R</span>
              <p data-id="320682">如皋市</p>
              <p data-id="371082">荣成市</p>
              <p data-id="371100">日照市</p>
              <p data-id="542301">日喀则市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="S1">S</span>
              <p data-id="220300">四平市</p>
              <p data-id="231200">绥化市</p>
              <p data-id="220700">松原市</p>
              <p data-id="320500">苏州市</p>
              <p data-id="310100">上海市</p>
              <p data-id="321300">宿迁市</p>
              <p data-id="330600">绍兴市</p>
              <p data-id="140600">朔州市</p>
              <p data-id="230500">双鸭山市</p>
              <p data-id="210100">沈阳市</p>
              <p data-id="330682">上虞市</p>
              <p data-id="130100">石家庄市</p>
              <p data-id="440500">汕头市</p>
              <p data-id="350400">三明市</p>
              <p data-id="429021">神农架林区</p>
              <p data-id="361100">上饶市</p>
              <p data-id="411400">商丘市</p>
              <p data-id="421300">随州市</p>
              <p data-id="341300">宿州市</p>
              <p data-id="411200">三门峡市</p>
              <p data-id="420300">十堰市</p>
              <p data-id="440300">深圳市</p>
              <p data-id="430500">邵阳市</p>
              <p data-id="440200">韶关市</p>
              <p data-id="441500">汕尾市</p>
              <p data-id="510900">遂宁市</p>
              <p data-id="611000">商洛市</p>
              <p data-id="542200">山南地区</p>
              <p data-id="460200">三亚市</p>
              <p data-id="640200">石嘴山市</p>
              <p data-id="659001">石河子市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="T1">T</span>
              <p data-id="140100">太原市</p>
              <p data-id="211200">铁岭市</p>
              <p data-id="220500">通化市</p>
              <p data-id="130200">唐山市</p>
              <p data-id="320585">太仓市</p>
              <p data-id="120100">天津市</p>
              <p data-id="321200">泰州市</p>
              <p data-id="150500">通辽市</p>
              <p data-id="331000">台州市</p>
              <p data-id="370900">泰安市</p>
              <p data-id="429006">天门市</p>
              <p data-id="340700">铜陵市</p>
              <p data-id="522201">铜仁市</p>
              <p data-id="469026">屯昌县</p>
              <p data-id="610200">铜川市</p>
              <p data-id="620500">天水市</p>
              <p data-id="654200">塔城地区</p>
              <p data-id="659003">图木舒克市</p>
              <p data-id="652100">吐鲁番地区</p>
              <p data-id="710100">台湾</p>
            </div>
            <div class="city-list"><span class="city-letter" id="W1">W</span>
              <p data-id="330300">温州市</p>
              <p data-id="320200">无锡市</p>
              <p data-id="150900">乌兰察布市</p>
              <p data-id="150300">乌海市</p>
              <p data-id="340200">芜湖市</p>
              <p data-id="420100">武汉市</p>
              <p data-id="370700">潍坊市</p>
              <p data-id="371000">威海市</p>
              <p data-id="469006">万宁市</p>
              <p data-id="610500">渭南市</p>
              <p data-id="469005">文昌市</p>
              <p data-id="469001">五指山市</p>
              <p data-id="620600">武威市</p>
              <p data-id="450400">梧州市</p>
              <p data-id="532600">文山壮族苗族自治州</p>
              <p data-id="659004">五家渠市</p>
              <p data-id="640300">吴忠市</p>
              <p data-id="650100">乌鲁木齐市</p>
            </div>
            <div class="city-list"><span class="city-letter" id="X1">X</span>
              <p data-id="140900">忻州市</p>
              <p data-id="152500">锡林郭勒盟</p>
              <p data-id="130500">邢台市</p>
              <p data-id="152200">兴安盟</p>
              <p data-id="320300">徐州市</p>
              <p data-id="410700">新乡市</p>
              <p data-id="420600">襄阳市</p>
              <p data-id="360500">新余市</p>
              <p data-id="411500">信阳市</p>
              <p data-id="429004">仙桃市</p>
              <p data-id="411000">许昌市</p>
              <p data-id="430300">湘潭市</p>
              <p data-id="350200">厦门市</p>
              <p data-id="341800">宣城市</p>
              <p data-id="420900">孝感市</p>
              <p data-id="421200">咸宁市</p>
              <p data-id="433100">湘西土家族苗族自治州</p>
              <p data-id="610100">西安市</p>
              <p data-id="610400">咸阳市</p>
              <p data-id="532800">西双版纳傣族自治州</p>
              <p data-id="630100">西宁市</p>
              <p data-id="810100">香港特别行政区</p>
            </div>
            <div class="city-list"><span class="city-letter" id="Y1">Y</span>
              <p data-id="320282">宜兴市</p>
              <p data-id="222400">延边朝鲜族自治州</p>
              <p data-id="321000">扬州市</p>
              <p data-id="140800">运城市</p>
              <p data-id="320900">盐城市</p>
              <p data-id="140300">阳泉市</p>
              <p data-id="330281">余姚市</p>
              <p data-id="230700">伊春市</p>
              <p data-id="210800">营口市</p>
              <p data-id="370600">烟台市</p>
              <p data-id="420500">宜昌市</p>
              <p data-id="430600">岳阳市</p>
              <p data-id="360900">宜春市</p>
              <p data-id="430900">益阳市</p>
              <p data-id="330782">义乌市</p>
              <p data-id="360600">鹰潭市</p>
              <p data-id="431100">永州市</p>
              <p data-id="450900">玉林市</p>
              <p data-id="511800">雅安市</p>
              <p data-id="530400">玉溪市</p>
              <p data-id="441700">阳江市</p>
              <p data-id="610800">榆林市</p>
              <p data-id="511500">宜宾市</p>
              <p data-id="445300">云浮市</p>
              <p data-id="610600">延安市</p>
              <p data-id="654000">伊犁哈萨克自治州</p>
              <p data-id="640100">银川市</p>
              <p data-id="632700">玉树藏族自治州</p>
            </div>
            <div class="city-list"><span class="city-letter" id="Z1">Z</span>
              <p data-id="130700">张家口市</p>
              <p data-id="330681">诸暨市</p>
              <p data-id="321100">镇江市</p>
              <p data-id="320582">张家港市</p>
              <p data-id="211300">朝阳市</p>
              <p data-id="430800">张家界市</p>
              <p data-id="410100">郑州市</p>
              <p data-id="370400">枣庄市</p>
              <p data-id="330900">舟山市</p>
              <p data-id="440183">增城市</p>
              <p data-id="440400">珠海市</p>
              <p data-id="411600">周口市</p>
              <p data-id="370300">淄博市</p>
              <p data-id="430200">株洲市</p>
              <p data-id="350600">漳州市</p>
              <p data-id="411700">驻马店市</p>
              <p data-id="440800">湛江市</p>
              <p data-id="520300">遵义市</p>
              <p data-id="510300">自贡市</p>
              <p data-id="530600">昭通市</p>
              <p data-id="441200">肇庆市</p>
              <p data-id="442000" id="111">中山市</p>
              <p data-id="620700">张掖市</p>
              <p data-id="512000">资阳市</p>
              <p data-id="640500">中卫市</p>
            </div>
          </div>
          <div class="letter">
            <ul>
              <li><a href="#A1">A</a></li>
              <li><a href="#B1">B</a></li>
              <li><a href="#C1">C</a></li>
              <li><a href="#D1">D</a></li>
              <li><a href="#E1">E</a></li>
              <li><a href="#F1">F</a></li>
              <li><a href="#G1">G</a></li>
              <li><a href="#H1">H</a></li>
              <li><a href="#J1">J</a></li>
              <li><a href="#K1">K</a></li>
              <li><a href="#L1">L</a></li>
              <li><a href="#M1">M</a></li>
              <li><a href="#N1">N</a></li>
              <li><a href="#P1">P</a></li>
              <li><a href="#Q1">Q</a></li>
              <li><a href="#R1">R</a></li>
              <li><a href="#S1">S</a></li>
              <li><a href="#T1">T</a></li>
              <li><a href="#W1">W</a></li>
              <li><a href="#X1">X</a></li>
              <li><a href="#Y1">Y</a></li>
              <li><a href="#Z1">Z</a></li>
            </ul>
          </div>
        </div>
    </section>
    </div>
<!--content结束-->
<script type="text/javascript" src="js/project/areaSelected.js"></script>
<script type="text/javascript">

$(document).ready(function(){
    
})


//选择城市 start
$('body').on('click', '.city-list p', function () {
    var type = $('.container').data('type');
    //alert("id="+$(this).attr('data-id')+",name="+$(this).html());
	var new_url = "/shop_address_areaselect1"+$("input[name='paraminfo']").val()+"&from=${from}&cityname="+$(this).html()+"&citycode="+$(this).attr('data-id');
	//alert(new_url);
	window.location.href = new_url;
});


$('#nav-right').on('click',function(){
    $('.backChoose').toggle();
});



function search()
{
    var searchText = $('#search').val();//获取你输入的关键字；
    if('' == searchText || undefined == typeof(searchText))
    {
        return false;
    }
    $('p').each(function()//遍历文章；
    {
        var html = $(this).html();
        if(-1 != html.indexOf(searchText))
        {
            var container = $('section'),
            scrollTo = $(this).eq(0);
        
            container.scrollTop(
                scrollTo.offset().top - container.offset().top + container.scrollTop()
            );
            return false;
        }
        
    });
}
</script>


</body>
</html>
