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
    
    <title>活动详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<table class="dv-table" border="0" style="width:100%;">
	<tr>
		<td class="dv-label" style="width:10%;" >标&nbsp;&nbsp;&nbsp;&nbsp;题: </td>
		<td style="width:40%;" colspan="3">${request.modelLazy.title}</td>
	</tr>
	
	<tr>
		<td class="dv-label">开始日期：</td>
		<td>${request.modelLazy.beginDate}</td>
		<td class="dv-label">结束日期：</td>
		<td>${request.modelLazy.endDate}</td>
	</tr>
	<tr>
		<td class="dv-label">时&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
		<td>${request.modelLazy.timePoint}</td>
		<td class="dv-label">地&nbsp;&nbsp;&nbsp;&nbsp;点：</td>
		<td>${request.modelLazy.location}</td>
	</tr>
	<tr>
		<td class="dv-label">发布者：</td>
		<td>${request.modelLazy.publisher}</td>
		<td class="dv-label">发布时间：</td>
		<td>${request.modelLazy.createTime}</td>
	</tr>
	<tr>
		<td class="dv-label">活动内容：</td>
	</tr>
	<tr>
		<td colspan="4"><textarea style="width:100%;height:100%;border:0;overflow-y:hidden;overflow-x:hidden;" readOnly>${request.modelLazy.content}</textarea></td>
	</tr>
</table>
  </body>
</html>
