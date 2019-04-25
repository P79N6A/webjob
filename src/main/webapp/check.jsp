<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
  <script src="js/jquery.js"></script>
</head>

<body>

<input type="checkbox" name="num"  value="94_2">94_2
<input type="checkbox" name="num"  value="95_1">95_1
<input type="checkbox" name="num"  value="96_3">96_3
<input type="submit" id="submit" name="tijiao"  value="orderConfirm" >
<input type="button" id="getCart" name="getCart"  value="getCart" >
<pre id="result">

</pre>
</body>

<script language="javascript">
   $(function(){
     $('#submit').click(function(){
		 var num = $("input[name='num']:checked").serialize();
         $.ajax({
             type: "POST",
             url: "orderConfirm",
             data: num,
             dataType: "json",
             success: function(data){
				     data = syntaxHighlight(data);
                         $('#result').html(data);
                      }
         });
    });
	
	 $('#getCart').click(function(){
         $.ajax({
             type: "POST",
             url: "getCart",
             dataType: "json",
             success: function(data){
				     data = syntaxHighlight(data);
                         $('#result').html(data);
                      }
         });
    });
	
});

function syntaxHighlight(json) {
    if (typeof json != 'string') {
         json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}
</script>
</html>