//this function used to check email string
function isEmail(_email)
{
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
	
	if(!myreg.test(_email))
    {
    	return false;
    }else
	{
		return true;
	}
}


function isCellphone(_cellphone)
{
	var reg0=/^13\d{5,9}$/; //130--139。至少7位
	var reg1=/^15\d{5,9}$/; 
	var reg2=/^17\d{5,9}$/;//虚拟运营商电话17X
	var reg3=/^18\d{5,9}$/;
	var iphoneNum = false;
	if(reg0.test(_cellphone) || reg1.test(_cellphone) || reg2.test(_cellphone) || reg3.test(_cellphone))
	{
		iphoneNum = true;
	}
	return iphoneNum;
}


function isRegisterUserName(_userName)
{
	   var regexString = /^[a-zA-Z]{1}([a-zA-Z0-9]|[-_]){4,19}$/;
	   var bValidate = regexString.test(_userName);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   }  
}

function isPassword(_password)
{
	   var bValidate = RegExp(/^[\@A-Za-z0-9\!\#\$\%\^\&\*\_\-\.\~]{6,22}$/).test(_password);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   }  
}

/**
  * 检查字符串是否为合法手机号码
  * @param {String} 字符串
  * @return {bool} 是否为合法手机号码
*/
 function isPhone(aPhone)
  {
       var bValidate = RegExp(/^(0|86|17951)?(13[0-9]|15[0-9]|18[0-9]|17[0-9])[0-9]{8}$/).test(aPhone);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   }        
 }
 
 function isPositiveInteger(_number)
 {
 	   var bValidate = RegExp(/^[1-9]\d*$/).test(_number);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   } 
 }

 function isChinese(_str)
 {
 	   var bValidate = RegExp(/^[\u4E00-\u9FA5]+$/).test(_str);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   } 
 }
 
 function isMobileVerifyCode(_verifyCode)
 {
 	   var bValidate = RegExp(/^\d{4}$/).test(_verifyCode);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   } 
 }
 
  function isTempPwd(_pwd)
 {
 	   var bValidate = RegExp(/^\d{6}$/).test(_pwd);
       if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   } 
 }

function isChePai(_chepai)
{
	var bValidate = RegExp(/^[A-Za-z0-9]{5}$/).test(_chepai);
	 if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   } 
}

function isCardNo(card)  
{  
   // 身份证号码为18位，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   	var bValidate = RegExp(/^[1-9]\d{16}[\d|X]$/).test(card);
	 if (bValidate) 
	   {
            return true;
       }
       else
	   {
	   	    return false;
	   } 
}  

function isHKEmploeeID(_emploeeID)
{
	 //员工工号以HKG开头
   	var bValidate = RegExp(/^HKG|TSG.*$/).test(_emploeeID);
	if (bValidate) 
	{
         return true;
    }
   else
	{
	   return false;
	} 
}

/**强制转化为两位小数**/
function toDecimal2(x) { 
          var f = parseFloat(x); 
          if (isNaN(f)) { 
            return false; 
          } 
          var f = Math.round(x*100)/100; 
          var s = f.toString(); 
          var rs = s.indexOf('.'); 
          if (rs < 0) { 
            rs = s.length; 
            s += '.'; 
          } 
          while (s.length <= rs + 2) { 
            s += '0'; 
          } 
          return s; 
        } 
/**转化为百分数**/
function toPercent(num)
{
   return (Math.round(num * 10000)/100).toFixed(2) + '%'; 
}
