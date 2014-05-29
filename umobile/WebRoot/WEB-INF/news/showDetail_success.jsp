<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新闻详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
<table class="dv-table" border="0" style="width:100%;">
	<tr>
		<td class="dv-label" style="width:10%;" >标&nbsp;&nbsp;&nbsp;&nbsp;题: </td>
		<td style="width:40%;">${request.modelLazy.title}</td>
		<td rowspan="8"  style="width:50%;">
		<img src="download/download!showImage?path=${request.modelLazy.coverPath}" style="height:200px;width:300px;"/></td>
	</tr>
	
	<tr>
		<td class="dv-label">作&nbsp;&nbsp;&nbsp;&nbsp;者：</td>
		<td>${request.modelLazy.author}</td>
	</tr>
	<tr>
		<td class="dv-label">出&nbsp;&nbsp;&nbsp;&nbsp;处：</td>
		<td>${request.modelLazy.deptName}</td>
	</tr>
	<tr>
		<td class="dv-label">头&nbsp;&nbsp;&nbsp;&nbsp;条：</td>
		<td>${request.modelLazy.headline}</td>
	</tr>
	<tr>
		<td class="dv-label">摄&nbsp;&nbsp;&nbsp;&nbsp;影：</td>
		<td>${request.modelLazy.photographer}</td>
	</tr>
	<tr>
		<td class="dv-label">状&nbsp;&nbsp;&nbsp;&nbsp;态：</td>
		<td>${request.modelLazy.status}</td>
	</tr>
	<tr>
		<td class="dv-label">类&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		<td>${request.modelLazy.categoryName}</td>
	</tr>
	<tr>
		<td class="dv-label">浏览量：</td>
		<td>${request.modelLazy.visitors}</td>
	</tr>
	<tr>
		<td class="dv-label">关键字：</td>
		<td >${request.modelLazy.keywords}</td>
	</tr>
	<tr>
		<td class="dv-label">发布者：</td>
		<td>${request.modelLazy.publisher}</td>
	</tr>
	<tr>
		<td class="dv-label">发布时间：</td>
		<td>${request.modelLazy.createTime}</td>
	</tr>
	<tr>
		<td class="dv-label">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
	</tr>
	<tr>
		<td colspan="3"><textarea style="width:100%;height:100%;border:0;overflow-y:hidden;overflow-x:hidden;" readOnly>${request.modelLazy.content}</textarea></td>
	</tr>
</table>
  </body>
</html>
