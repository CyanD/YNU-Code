<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String TreeName = "相册类别";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=TreeName%>维护</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/demo.css">
	<script type="text/javascript" src="resources/js/easyui/jq.eui.loc.js"></script>
	<script type="text/javascript" src="resources/js/myjs/treegrid.js"></script>

</head>
<body>
		<table id="table"></table>
		<div id="dlg_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" collapsible="true"  closed="true" buttons="#dlg-buttons"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写<%=TreeName%>信息</h2></div>
					</div> 
					<form id="fm_add" method="post" novalidate>
						<table align ="center"  >
								<tr>
									<td>上级<%=TreeName%>：</td>
									<td><input id="cc" name = "_parentId" class="easyui-combotree" value="0" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td><%=TreeName%>名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="treeGrid.saveAdd()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="treeGrid.closeDlgAdd()">取消</a>
			</div>
	</div>
	
	<div id="dlg_edit" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" collapsible="true"  closed="true" buttons="#dlg-buttons_edit"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写<%=TreeName%>信息</h2></div>
					</div> 
					<form id="fm_edit" method="post" novalidate>
						<input name = "id"  type="hidden">
						<table align ="center"  >
								<tr>
									<td>上级<%=TreeName%>：</td>
									<td><input id="cc_edit" name = "_parentId" value = "0" class="easyui-combotree" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td><%=TreeName%>名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_edit">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="treeGrid.saveEdit()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="treeGrid.closeDlgEdit()">取消</a>
			</div>
	</div>
	
	
	<div id="tab-toolbar">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="treeGrid.add()">添加<%=TreeName%></a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="treeGrid.remove()">删除<%=TreeName%></a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="treeGrid.edit()">修改<%=TreeName%></a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="treeGrid.refresh()">刷新列表</a>
					</td>
				</tr>
			</table>			
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div data-options="iconCls:'icon-reload'" onclick="treeGrid.refresh()">刷新列表</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-add'" onclick="treeGrid.add()">添加<%=TreeName%></div>
		<div data-options="iconCls:'icon-cut'" onclick="treeGrid.remove()">删除<%=TreeName%></div>
		<div data-options="iconCls:'icon-edit'" onclick="treeGrid.edit()">修改<%=TreeName%></div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-tuichu'" >退出</div>
	</div>
</body>
<script type="text/javascript">
 var treeGrid = new TreeGrid('#table','sys/newsAlbumCategory','#dlg_add','#fm_add','#dlg_edit','#fm_edit','<%=TreeName%>');
 treeGrid.createTreeGrid();
</script>
</html>
