<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<title>出错了</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <!-- bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/non-responsive.css" rel="stylesheet" />

    <!-- libraries -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" type="text/css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/lib/jquery.dataTables.css" type="text/css" rel="stylesheet" />
    

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/compiled/layout.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/compiled/elements.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/compiled/icons.css" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->


<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>  
  <script src="${pageContext.request.contextPath}/js/menu.js"></script>    
<script src="${pageContext.request.contextPath}/js/theme.js"></script> 

</head>

<body>
    <!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
       <div class="main-header" style="background-color:#000">
       <div class="navbar-header">
            <a href="index.html">
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo" />
            </a>
        </div>
        <div class="logo pull-left">
         
           	 <h2 style="color:#666;margin-left:25px;"><strong>客户信息管理系统</strong></h2>
        </div>
     
        <ul class="nav navbar-nav pull-right hidden-xs">
            <li class="settings hidden-xs hidden-sm">
                <a href="signin.html" role="button">
                    <i class="fa fa-mail-forward"></i>
                </a>
            </li>
        </ul>
        </div>
    </header>
    <!-- end navbar -->

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">                          
        </ul>
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content" style="background-color:#dddddd">
    <img src="${pageContext.request.contextPath}/img/access.jpg" style="margin-left:30%">
    
  </div>


  	<!-- scripts -->

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.2.custom.min.js"></script>

</body>
</html>