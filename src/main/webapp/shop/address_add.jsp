        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
                <!DOCTYPE html>
                <html class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
                <head>
                <meta charset="UTF-8">
                <title>我的地址管理</title>

                <%@ include file="../public/head.jsp" %>
                <%@ include file="../public/meta.jsp" %>

                </head>
                <body class="um-vp " ontouchstart>
                <div id="page_0" class="up ub ub-ver bc-bg" tabindex="0">
                <!--header开始-->
                <div id="header" class="uh ub">
                <div class="nav-btn green_color ulev1" id="nav-left" onClick="location.href='/mine_address_list';">
                <div class="fa fa-angle-left fa-2x "></div>
                <div class="ub ub-ac ub-pc umar-l" >返回</div>
                </div>
                <h1 class="ut ub-f1 ulev1 ut-s tx-c" tabindex="0" id="myTitle">添加地址</h1>
                <div class="nav-btn nav-bt green_color" id="nav-right">
                保存
                </div>
                </div>
                <!--header结束--><!--content开始-->
                <section class="ub ub-ver ub-f1 ub-fv sc-bg" style="overflow-y: auto">
                <div class="ub-ver umar-t">
                <div class="ub-ver">
                <form name="form1" class="ub umar-t bc-bg ub-ver" action="mine_address_edit1" method="post">
                <input type="hidden" name="id" value="${address.id}" />
                <input type="hidden" name="userid" value="${logclient.id}" />
                <input type="hidden" name="username" value="${logclient.username}" />
                <div class="uinput ub ub-f1 border_b" style="padding-left:1.5em">
                <input placeholder="姓名" type="text" class="ub-f1 umh5" name="truename" value="${address.truename}">
                </div>
                <div class="uinput ub ub-f1 border_b" style="padding-left:1.5em">
                <input placeholder="手机号码" maxlength="11" type="text" class="ub-f1 umh5" name="mobile"  value="${address.mobile}">
                </div>

                <c:set value="${ fn:split(address.cityname, ' ') }" var="str1" />

                <div class="ub ub-f1 border_b umh6 ub-ac" style="padding-left:1.5em" onClick="selectProvince()">
                <div class="ub ub-f1">省份</div>
                <div class="fa fa-angle-right ub-pe uinn ulev">${str1[0]}</div>
                <input type="hidden" name="cityname" value="${address.cityname}"/>
                <input type="hidden" name="citycode" value="${address.citycode}"/>
                </div>
                <div class="ub ub-f1 border_b umh6 ub-ac" style="padding-left:1.5em" onClick="selectCity()">
                <div class="ub ub-f1">城市</div>
                <div class="fa fa-angle-right ub-pe uinn ulev">${str1[1]}</div>
                </div>
                <div class="ub ub-f1 border_b umh6 ub-ac" style="padding-left:1.5em" onClick="selectArea()">
                <div class="ub ub-f1">区县</div>
                <div class="fa fa-angle-right ub-pe uinn ulev">${str1[2]}</div>
                </div>

                <div class="uinput ub ub-f1 border_b" style="padding-left:1.5em">
                <textarea class="ulev1" placeholder="详细地址" style="resize:none;padding-top:0.8em" name="areainfo">${address.areainfo}</textarea>
                </div>

                <input type="hidden" name="fromUrl" value="address_select">
                </form>

                </div>
                <!--有订单 end-->

                </div>

                </section>
                <c:choose>
                        <c:when test="${result == 'false'}">
                                <script  type="text/javascript">
                                alert(' 操作失败！失败原因：${loginfo}');
                                </script>
                        </c:when>
                </c:choose>

                <!--content结束-->
                <!--content结束-->
                <footer id="footer"  class="sc-bg basic-border" style="display:none">
                <div class="ub ub-f1 uinn ub-ac ub-pc">
                <div class="ub uinn addAddressBtn ub-ac ub-pc" onClick="deleteAddress()">删除收货地址</div>
                </div>

                </footer>

                </div>
                <script src="../js/appcan.js"></script>
                <script src="../js/appcan.control.js"></script>
                <script src="../js/regex.js"></script>
                </body>
                <script>
                $(function(){
                var valID = $("input[name='id']").val();
                if(valID=="")//不带参数   新增地址
                {
                $('#myTitle').html('添加地址');
                $('#footer').hide();
                }
                else                                    //带参数  编辑地址    需要初始化数据
                {
                $('#myTitle').html('编辑地址');
                $('#footer').show();
                }
                });


                $('#nav-right').on('click',function(){
                var val = $("input[name='truename']").val();
                if(val==null ||val==''){
                alert('姓名必须填写.');
                return;
                }


                if(!isChinese(val)){
                alert('姓名必须是汉字.');
                return;
                }

                val = $("input[name='mobile']").val();
                if(val==null ||val==''){
                alert('手机号码必须填写.');
                return;
                }

                if(!isPhone(val)){
                alert('手机号码格式不正确.');
                return;
                }

                val = $("input[name='citycode']").val();
                if(val==null ||val==''){
                alert('所在地区必须选择.');
                return;
                }


                val = $("textarea[name='areainfo']").val();
                if(val==null ||val==''){
                alert('详细地址必须填写.');
                return;
                }

                //提交数据
                form1.submit();
                });

                //sxw 删除地址
                function deleteAddress(){
                if(confirm("确认要删除改地址吗？")){
                var valID = $("input[name='id']").val();
                window.location.href = "/mine_address_delete?id="+valID;
                }
                }


                //sxw 选择地址
                function selectCommon(url){
                var old_id = getObjVal($("input[name='id']"));
                var old_userid = getObjVal($("input[name='userid']"));
                var old_username = getObjVal($("input[name='username']"));
                var old_truename = getObjVal($("input[name='truename']"));
                var old_mobile = getObjVal($("input[name='mobile']"));
                var old_cityname = getObjVal($("input[name='cityname']"));
                var old_citycode = getObjVal($("input[name='citycode']"));

                var old_areainfo = getObjVal($("textarea[name='areainfo']"));
                var new_url = url+"?id="+old_id+"&userid="+old_userid+"&username="+old_username+"&truename="+old_truename+"&mobile="+old_mobile+"&cityname="+old_cityname+"&citycode="+old_citycode+"&areainfo="+old_areainfo;
                //alert(new_url);
                window.location.href = new_url;
                }

                //选择省份
                function selectProvince(){
                selectCommon('/mine_address_selectProvince');
                }

                //选择城市
                function selectCity(){
                var old_citycode = getObjVal($("input[name='citycode']"));
                if(old_citycode==''){
                alert('请先选择省份.');
                return false;
                }
                selectCommon('/mine_address_selectCity');
                }

                //选择地区
                function selectArea(){
                var old_citycode = getObjVal($("input[name='citycode']"));
                if(old_citycode==''){
                alert('请先选择省份.');
                return false;
                }
                //选择过城市之后才能选择地区 也就是最后四位不能为0
                if(old_citycode.indexOf("0000")>0){
                alert('请先选择城市.');
                return false;
                }

                selectCommon('/mine_address_selectArea');
                }



                function getObjVal(obj){
                var objVal = "";
                if(obj.length>0){
                objVal = obj.val();
                }
                return objVal;
                }
                </script>
                </body>
                </html>
