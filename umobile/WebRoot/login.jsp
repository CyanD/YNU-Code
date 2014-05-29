<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ynumobile后台登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/demo.css">
	<script type="text/javascript" src="resources/js/jquery/jquery-1.7.2.min.js"></script>
	
<style type="text/css">
<!--
a{ color:#008EE3}
a:link  { text-decoration: none;color:#008EE3}
A:visited {text-decoration: none;color:#666666}
A:active {text-decoration: underline}
A:hover {text-decoration: underline;color: #0066CC}
A.b:link {
	text-decoration: none;
	font-size:12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}
A.b:visited {
	text-decoration: none;
	font-size:12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}
A.b:active {
	text-decoration: underline;
	color: #FF0000;

}
A.b:hover {text-decoration: underline; color: #ffffff}

.table1 {
	border: 1px solid #CCCCCC;
}
.font {
	font-size: 12px;
	text-decoration: none;
	color: #999999;
	line-height: 20px;
	

}
.input {
	font-size: 12px;
	color: #999999;
	text-decoration: none;
	border: 0px none #999999;


}

td {
	font-size: 12px;
	color: #007AB5;
}
form {
	margin: 1px;
	padding: 1px;
}
input {
	border: 0px;
	height: 26px;
	color: #007AB5;

	.unnamed1 {
	border: thin none #FFFFFF;
}
.unnamed1 {
	border: thin none #FFFFFF;
}
select {
	border: 1px solid #cccccc;
	height: 18px;
	color: #666666;

	.unnamed1 {
	border: thin none #FFFFFF;
}
body {
	background-repeat: no-repeat;
	background-color: #9CDCF9;
	background-position: 0px 0px;

}
.tablelinenotop {
	border-top: 0px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	border-bottom: 0px solid #CCCCCC;
	border-left: 1px solid #CCCCCC;
}
.tablelinenotopdown {

	border-top: 1px solid #eeeeee;
	border-right: 1px solid #eeeeee;
	border-bottom: 1px solid #eeeeee;
	border-left: 1px solid #eeeeee;
}
.style6 {FONT-SIZE: 9pt; color: #7b8ac3; }

-->
</style>
 <script type="text/javascript">
 $(function(){
 changeValidateCode();
 })      
    function changeValidateCode() {      
        var timenow = new Date().getTime();      
        document.getElementById("codeImg").src="login/login!showImage?d="+timenow;      
    } 
    var flag = false;     
    function checkForm(){
    	var account =$.trim($('#account').val());
    	var password = $.trim($('#password').val());
    	var securityCode = $.trim($('#securityCode').val());
    	var flag = false;
    	 $.post('login/login!doValidate',{account:account,password:password,securityCode:securityCode},function(result){
    				if(result.success){
    					window.location.href="login/login?account="+account+"&password="+password+"&securityCode="+securityCode;
					}else{
						$('#msg').html('<img src="resources/images/login/tip.gif" width="16" height="16"><font color="red">'+ result.msg+'</font>');
					}  
			  },'json'); 
		return flag;
    } 
</script>  
</head>
<body>
<table width="681" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:120px">
  <tr>
    <td width="353" height="259" align="center" valign="bottom" background="resources/images/login/login_1.gif">
    <table width="90%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td align="right" valign="bottom" style="color:#05B8E4">Copyright © 2012 云南大学高性能计算中心</td>
      </tr>
    </table></td>
    <td width="195" background="resources/images/login/login_2.gif">
    
    <table width="190" height="106" border="0" align="center" cellpadding="2" cellspacing="0">
    		<form onSubmit="return false;">
            <tr>
              <td height="50" colspan="2" align="left">&nbsp;</td>
            </tr>
            <tr>
              <td width="60" height="30" align="left">登陆账号</td>
              <td><input id= "account" name="account" type="TEXT" style="background:url(resources/images/login/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="UserName"size="14"></td>
            </tr>
            <tr>
              <td height="30" align="left">登陆密码</td>
              <td><input id ="password" name="password" TYPE="PASSWORD" style="background:url(resources/images/login/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="Password" size="16"></td>
            </tr>
            <tr>
              <td height="30"> 验 证 码 </td>
			  <td valign="top"><input id="securityCode"  name="securityCode" type="text" id="Code" size="3" style="background:url(resources/images/login/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" maxlength="4">
		      <img id="codeImg" src="" width="65" height="30"  onclick="changeValidateCode()"/> </td>
            </tr>
            <tr>
              <td id="msg" height="40" colspan="2" align="center"><img src="resources/images/login/tip.gif" width="16" height="16"> 请勿非法登陆！</td>
          </tr>
          <tr>
              <td colspan="2" align="center"><input type="submit" name="submit" style="background:url(resources/images/login/login_5.gif) no-repeat" value=" 登  陆 "  onclick="checkForm();"> 
			  <input type="reset" name="Submit" style="background:url(resources/images/login/login_5.gif) no-repeat" value=" 取  消 "></td>
           </tr>
            <tr>
              <td height="5" colspan="2"></td>
              </tr>
              </form>
    </table></td>
    <td width="133" background="resources/images/login/login_3.gif">&nbsp;</td>
  </tr>
  <tr>
    <td height="161" colspan="3" background="resources/images/login/login_4.gif"></td>
  </tr>
</table>
</body>
</html>