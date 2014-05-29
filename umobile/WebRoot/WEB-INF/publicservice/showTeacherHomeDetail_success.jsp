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
		<td>${request.modelLazy.title}</td>
	</tr>
	<tr>
		<td class="dv-label">类&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		<td>${request.modelLazy.kindTree.name}</td>
	</tr>
	<tr>
		<td class="dv-label">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
	</tr>
	<tr>
	 <td colspan="3"><%= ((com.model.publicservice.TeacherHome)request.getAttribute("modelLazy")).getContent().replaceAll("src='/ynumobile", "src='/umobile") %> </td>
	</tr>
	
	<!-- <tr>${request.modelLazy.content}
		<td  style="width:40%;"><textarea style="width:100%;height:100%;border:0;overflow-y:hidden;overflow-x:hidden;" readOnly></textarea></td>
	</tr> -->
</table>
  </body>
</html>
