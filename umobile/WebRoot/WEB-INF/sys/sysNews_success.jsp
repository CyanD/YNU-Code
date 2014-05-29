<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新闻类别后台维护</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/demo.css">
	<script type="text/javascript" src="resources/js/easyui/jq.eui.loc.js"></script>
	<script  type="text/javascript" src="resources/js/myjs/main.js"></script>
</head>
<body class="easyui-layout">
		<div data-options="region:'west',split:true,iconCls:'icon-main'" title="新闻后台数据维护" style="width:150px;padding1:1px;overflow:hidden;">
					<div title="类别维护" style="padding:10px;overflow:auto;" align = "center">
						<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('新闻类别维护','sys/newsNewsCategory','icon-default')"><img src="resources/images/user_64.png"><br>新闻类别维护</a><br>
						<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('公告类别维护','sys/newsNoticeCategory','icon-default')"><img src="resources/images/user_64.png"><br>公告类别维护</a><br>
						<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('相册类别维护','sys/newsAlbumCategory','icon-default')"><img src="resources/images/user_64.png"><br>相册类别维护</a><br>
					</div>
		</div>
		<div data-options="region:'center'" title="类别维护" style="overflow:hidden;">
			<div id="tabs"  class="easyui-tabs" data-options="fit:true,border:false">
			</div>
		</div>
		
		
		<div id="mm" class="easyui-menu" style="width:150px;">
	         <div id="refresh" data-options="iconCls:'icon-reload'" >刷新</div>
	         <div class="menu-sep"></div>
	         <div id="close" data-options="iconCls:'icon-chacha2'"  >关闭当前页</div>
	         <div id="closeall" data-options="iconCls:'icon-chacha'"  >全部关闭</div>
	         <div id="closeother" data-options="iconCls:'icon-chacha3'" >除此之外全部关闭</div>
	         <div class="menu-sep"></div>
	         <div id="closeright" data-options="iconCls:'icon-you'" >当前页右侧全部关闭</div>
	         <div id="closeleft" data-options="iconCls:'icon-zuo'" >当前页左侧全部关闭</div>
	         <div class="menu-sep"></div>
	         <div id="exit" data-options="iconCls:'icon-tuichu'" >退出</div>
	 </div>
</body>
</html>
