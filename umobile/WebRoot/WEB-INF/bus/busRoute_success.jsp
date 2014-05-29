<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校巴管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校巴维护</title>
		<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/demo.css">
	<script type="text/javascript" src="resources/js/easyui/jq.eui.loc.js"></script>
	<script>
		//route
		var tableId = 'test';
		var routeId=null;
		var routeName=null;
		$(function(){
			$('#'+tableId).datagrid({
				//title:'校巴线路列表',
				iconCls:'icon-save',
				nowrap: true,
				fitColumns:true,
				autoRowHeight:false,
				nowrap: false,
				striped: true,
				fit: true,
				//collapsible:true,
				url:'bus/busRoute!show',
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: true,
				idField:'id',
				frozenColumns:[[
	                {field:'ck',checkbox:true,autocheck:false},
	                {title:'编号',field:'id',width:30,rowspan:2,hidden:true,sortable:true}
				]],
				columns:[[
					{field:'name',title:'名称',width:100,rowspan:2},
					{field:'startStation',title:'始发站',width:100,rowspan:2},
					{field:'endStation',title:'终点站',width:100,rowspan:2},
					{field:'viaStation',title:'途径站',width:100,rowspan:2},
					{field:'byCarPlace',title:'坐车地点',width:100,rowspan:2},
					{field:'createTime',title:'发布时间',width:150,rowspan:2},
					{field:'publisher',title:'发布人',width:100,rowspan:2}
				]],
				pagination:true,
				rownumbers:false,
				//singleSelect:true,
				onClickRow:function(index,row){
											$('#'+tableId).datagrid("unselectRow",index);
										},
				onDblClickRow:function(index,row){
											routeId = row.id;
											routeName = row.name;
											$('#table_schedule').datagrid({
													url:'bus/busSchedule!show', 
		                    						queryParams:{pid:routeId}
          									 });
										},
				toolbar:'#tab-toolbar'
			});//drid
	})//$
		var url_route;
		function newBusRoute(){
			$('#dlg').dialog('open').dialog('setTitle','添加线路');
			$('#fm').form('clear');
			url_route = 'bus/busRoute!save';
		}
		function unselectAllRoute(){
			 $('#'+tableId).datagrid("unselectAll");
		}
		function updateBusRoute(index){
				var row = $('#'+tableId).datagrid("getRows")[index];
				$('#dlg').dialog('open').dialog('setTitle','修改线路');
				$('#fm').form('clear');
				$('#fm').form('load',row);
				url_route = 'bus/busRoute!update?id='+row.id;
		}
		function editBusRoute(){
			var row = $('#'+tableId).datagrid('getSelected');
			//unselectAllRoute();
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','修改线路');
				$('#fm').form('clear');
				$('#fm').form('load',row);
				url_route = 'bus/busRoute!update?id='+row.id;
			}else{
				$.messager.alert('提醒','请选择需要修改的线路!','warning');
			}
		}
		function saveBusRoute(){
			$('#fm').form('submit',{
				url: url_route,
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					$('#dlg').dialog('close');
					if (result.success){
						$('#'+tableId).datagrid('reload');
						$.messager.show({
							title: '成功',
							msg: result.msg,
							showType:'show',
							timeout:5000
						});
					} else {
						$.messager.show({
							title: '失败',
							msg: result.msg,
							showType:'show',
							timeout:5000
						});
					}
				}
			});
		}
		function removeBusRoute(){
			
			var rows = $('#'+tableId).datagrid('getSelections');
			if (rows.length>0){
				$.messager.confirm('提示：','此操作会同时删除与之相关的时刻，你确定要删除吗?',function(r){
					if (r){
						var idss = [];
						var needInit = false;
						for(var i=0;i<rows.length;i++){
							if(routeId==rows[i].id){
								needInit=true;
							}
							idss.push(rows[i].id);
						}
						$.post('bus/busRoute!delete',{ids:idss.join(',')},function(result){
							if (result.success){
								unselectAllRoute();//清空选择器里面的row，以免修改混乱
								if(needInit){
										initGrid();//重新设置schedule，以免添加已经被删的数据
								}
								$('#'+tableId).datagrid('reload');	
								$.messager.show({	
									title: '成功',
									msg: result.msg,
									showType:'show',
									timeout:5000
								});
							} else {
								$.messager.show({	
									title: '失败',
									msg: result.msg,
									showType:'show',
									timeout:5000
								});
							}
						},'json');
					}
				});//confirm
			}else{
				$.messager.alert('提醒','请选择需要删除的线路!','warning');
			}
		}
		//route
		//schedule
		function initGrid(){
			$('#table_schedule').datagrid({
													url:null
		                    				});
          						routeId=null;
          $('#table_schedule').datagrid('loadData',{"rows":[],"total":0});
		}
		var tableId_schedule = 'table_schedule';
		$(function(){
			$('#'+tableId_schedule).datagrid({
				//title:'校巴线路列表',
				iconCls:'icon-save',
				nowrap: true,
				fitColumns:true,
				autoRowHeight:false,
				nowrap: false,
				striped: true,
				fit: true,
				//collapsible:true,
				//url:shcheduleURL,
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: true,
				idField:'id',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'所属线路',field:'sss',width:200,rowspan:2,formatter:function(value,row,index){
	                	return routeName;
	                }},
	                {title:'线路编号',field:'pid',width:10,rowspan:2,hidden:true,sortable:true}
				]],
				columns:[[
					{field:'startTime',title:'发车时间',width:50,rowspan:2},
					{field:'costTime',title:'预计耗时',width:50,rowspan:2},
					{field:'createTime',title:'发布时间',width:100,rowspan:2},
					{field:'publisher',title:'发布人',width:50,rowspan:2}
				]],
				pagination:true,
				rownumbers:false,
				toolbar:'#tab-toolbar_schedule'
			});//drid
	})//$
		var url;
		function newBusSchedule(){
			if(routeId!=null){
				$('#dlg_schedule').dialog('open').dialog('setTitle','添加时刻');
				$('#fm_schedule').form('clear');
				url = 'bus/busSchedule!save?pid='+routeId;
			}else{
				$.messager.alert('提醒','请先选择线路！ 双击需要维护的线路即可！','warning');
			}
		}
		function unselectAllSche(){
			 $('#'+tableId_schedule).datagrid("unselectAll");
		}
		function editBusSchedule(){
			var row = $('#'+tableId_schedule).datagrid('getSelected');
			//unselectAllSche();
			if (row){
				$('#dlg_schedule').dialog('open').dialog('setTitle','修改线路');
				$('#fm_schedule').form('clear');
				$('#fm_schedule').form('load',row);
				url = 'bus/busSchedule!update?id='+row.id+'&pid='+routeId;
			}else{
				$.messager.alert('提醒','请选择需要修改的时刻!','warning');
			}
		}
		function saveBusSchedule(){
			$('#fm_schedule').form('submit',{
				url: url,
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					$('#dlg_schedule').dialog('close');
					if (result.success){
						$('#'+tableId_schedule).datagrid('reload');
						$.messager.show({
							title: '成功',
							msg: result.msg,
							showType:'show',
							timeout:5000
						});
					} else {
						$.messager.show({
							title: '失败',
							msg: result.msg,
							showType:'show',
							timeout:5000
						});
					}
				}
			});
		}
		function removeBusSchedule(){
			
			var rows = $('#'+tableId_schedule).datagrid('getSelections');
			if (rows.length>0){
				$.messager.confirm('提示：','你确定要删除吗?',function(r){
					if (r){
						var idss = [];
						for(var i=0;i<rows.length;i++){
							idss.push(rows[i].id);
						}
						$.post('bus/busSchedule!delete',{ids:idss.join(',')},function(result){
							if (result.success){
								unselectAllSche();
								$('#'+tableId_schedule).datagrid('reload');	// reload the user data
								$.messager.show({	// show error message
									title: '成功',
									msg: result.msg,
									showType:'show',
									timeout:5000
								});
							} else {
								$.messager.show({	// show error message
									title: '失败',
									msg: result.msg,
									showType:'show',
									timeout:5000
								});
							}
						},'json');
					}
				});//confirm
			}else{
				$.messager.alert('提醒','请选择需要删除的时刻!','warning');
			}
		}
		//schedule
		$(function(){
        
$(".datebox :text").attr("readonly","readonly");
 
})
	</script>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" title="时刻表维护" style="height:400px;width:400px;overflow:hidden;">
		<table id="table_schedule"></table>
		<div id="dlg_schedule" class="easyui-dialog" style="width:300px;height:280px;padding:10px 20px"
				closed="true" buttons="#dlg-buttons_schedule">
				<div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>时刻表</h2></div>
					</div> 
					<form id="fm_schedule" method="post" novalidate>
						<table align ="center"  >
								<tr>
									<td>发车时间：</td>
									<td><input  name="startTime" class="easyui-timespinner" data-options="required:true,showSeconds:false" style="width:120px;"  />
									</td>
								</tr>
								<tr>
									<td>预计耗时：</td>
									<td><input  name="costTime" class="easyui-timespinner" data-options="required:true,showSeconds:false" style="width:120px;" ></td>
								</tr>
						</table>
				</form>
		</div>
		<div id="dlg-buttons_schedule">
			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveBusSchedule()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_schedule').dialog('close')">取消</a>
		</div>
		<div id="tab-toolbar_schedule">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newBusSchedule()">添加时刻</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="removeBusSchedule()">删除时刻</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editBusSchedule()">修改时刻</a>
					</td>
			</tr>
		</table>
						
		</div>
</div>
<div data-options="region:'north',split:true" title="线路维护" style="height:300px;overflow:hidden;">
	<table id="test"></table>
		<div id="dlg" class="easyui-dialog" style="width:300px;height:280px;padding:10px 20px"
			 fit="true" modal ="true"   draggable="false" 	closed="true" buttons="#dlg-buttons"><br>
			<div class="ftitle" align = "center">请填写校巴信息</div>
					<form id="fm" method="post" novalidate>
							<table align ="center"  >
								<tr>
									<td class="dv-label">名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">起始站：</td>
									<td><input name = "startStation" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">终点站：</td>
									<td><input name = "endStation" class="easyui-validatebox" data-options="validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">乘车点：</td>
									<td><input name = "byCarPlace" class="easyui-validatebox" data-options="validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">途经站：</td>
									<td><textarea name = "viaStation" class="easyui-validatebox" data-options="validType:'length[1,1000]'" style="height:200px;width:400px;border:1px solid #ccc;	padding:2px;"></textarea></td>
								</tr>
						</table>
				</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveBusRoute()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div id="tab-toolbar">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newBusRoute()">添加线路</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="removeBusRoute()">删除线路</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editBusRoute()">修改线路</a>
					</td>
			</tr>
		</table>			
		</div>
	</div>	
</body>
</html>