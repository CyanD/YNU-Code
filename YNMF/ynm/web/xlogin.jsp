<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>客户管理系统登陆</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
	<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" /> 
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" /> 
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css"></link>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/all.css" />
     <script src="${pageContext.request.contextPath}/Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<style type="text/css">
.centerAlign {
	margin-top: 12%;
	margin-right: auto;
	margin-bottom: auto;
	margin-left: auto;
}

.input_width{
  width:80%;
}
.sizeControl{
	width: 25%;
	height: 20%;
}

.marginLR{
	padding-left: 10px;
	padding-right: 10px;
}

.floatRight{
	float: right;
}

.transparent{
	opacity: 0.7;
	filter:Alpha(Opacity='70');
}

.non_transparent{
	opacity: 1;
	filter:Alpha(Opacity='100');
}

body {
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-position: center;
	background-size:100% 100%;
	background-image: url(images/LoginBackground.png);
}
</style>
 <script type="text/javascript">
     function flogin(text,e)
            {     
               
                    form.submit();
                   
              
            }
	
</script>  
</head>
<body>
<form id="form" method="post" name="login" action="sys/xlogin.do">
<div class="sizeControl centerAlign transparent">
	<div class="panel panel-primary">
		<div class="panel-heading">系统登陆</div>
  		<div class="panel-body">
    		<form class="form-horizontal" role="form" onSubmit="return false;">
  				<div class="form-group marginLR">
    				<label class="sr-only" for="exampleInputFile">userName</label>
   			 		<input type="text" class="form-control non_transparent" name="user.userName"  id="USERNAME" placeholder="Enter userName">
  				</div>
  				<div class="form-group marginLR">
    				<label class="sr-only" for="exampleInputFile">Password</label>
    				<input type="password" class="form-control non_transparent" name="user.password"  id="PASSWORD" placeholder="Password">
  				</div>	
  				<div class = "form-group marginLR floatRight"><img id="codeImg" src="" width="65" height="30"  onclick="changeValidateCode()"/> </div>
  				<button type="submit"  class="btn btn-primary marginLR non_transparent"  onclick="javascript:submit()" onkeypress="flogin(this,event)">&nbsp;&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;&nbsp;&nbsp;</button>
				<div class="checkbox floatRight marginLR">
    				<label>
      					<input type="checkbox"> Remember me
    				</label>
  				</div>
			</form>
  		</div>		
	</div>
</div>
</form>
</body>
</html>