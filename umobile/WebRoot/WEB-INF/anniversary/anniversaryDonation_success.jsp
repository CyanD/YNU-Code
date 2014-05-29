<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校友捐赠维护</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/demo.css">
	<link href="resources/js/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="resources/js/easyui/jq.eui.loc.js"></script>
		<script type="text/javascript" src="resources/js/uploadify/jquery.uploadify.min.js"></script>
  <script type="text/javascript" src="resources/js/uploadify/jquery.tmpl.js"></script>
  	<script type="text/javascript" src="resources/js/json/json2.js"></script>
	<script type="text/javascript" >
	var newsGrid;
function NewsGrid(tableId,url,addDlgId,addFormId){
		this.reload=reload;
		function reload(){
			$(tableId).datagrid('reload');
		}
		this.getSelectedRow=getSelectedRow;
		function getSelectedRow(){
				return $(tableId).datagrid('getSelected')
		}
		this.createGrid=createGrid;
		function createGrid(){
			$(tableId).datagrid({
				iconCls:'icon-save',
				nowrap: false,
				fitColumns:false,
				autoRowHeight:true,
				striped: true,
				fit: true,
				//collapsible:true,
				url:url+'!show',
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: true,
				idField:'id',
				frozenColumns:[[
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true},
					{field:'name',title:'姓名',width:80,align:'center',rowspan:2,sortable:true}
					]],
				columns:[[
	                {title:'姓名',field:'name',rowspan:2,align:'center',width:80,sortable:true},
	                {title:'金额',field:'money',rowspan:2,align:'center',width:150,sortable:true},
	                {title:'地点',field:'location',rowspan:2,align:'center',width:100,sortable:true},
					{field:'donateTime',title:'捐赠时间',width:100,align:'center',rowspan:2},
					{field:'createTime',title:'创建/修改时间',width:150,align:'center',rowspan:2,sortable:true},
					{field:'publisher',title:'创建/修改人',width:80,align:'center',rowspan:2,sortable:true}
				]],
				pagination:true,
				rownumbers:false,
				singleSelect:true,
				onLoadSuccess:function (data){
					unselectAll();
				 },
				onClickRow:function(index,row){
											/* unselectAll();
											$(tableId).datagrid("selectRow",index); */
										},
				onDblClickRow:function(index,row){
											unselectAll();
											$(tableId).datagrid("selectRow",index);
											edit();
										},
				onRowContextMenu: function(e, index, row){
											e.preventDefault();
											unselectAll();
											$(tableId).datagrid("selectRow",index);
											$('#mm_up').menu('show', {
												left: e.pageX,
												top: e.pageY
											});
										},
				toolbar:'#tab-toolbar_up'
			});//drid
	}
		var updateUrl;
		this.add=add;
		function add(){
			$(addDlgId).dialog('open').dialog('setTitle','添加记录');
			$(addFormId).form('clear');
			$(addFormId).form('load',{sex:'男'});
			updateUrl=url+'!add';
		
		}
		function unselectAll(){
			 $(tableId).datagrid("unselectAll");
		}
		this.edit=edit;
		function edit(){
			var row = getSelectedRow();
			if (row){
				$(addFormId).form('clear');
				$(addFormId).form('load',row);
				$(addDlgId).dialog('open').dialog('setTitle','修改记录');
				updateUrl=url+'!edit?id='+row.id;
			}else{
				$.messager.alert('提醒','请选择需要修改的记录!','warning');
			}
		}
		this.saveAdd=saveAdd;
		function saveAdd(){
			$(addFormId).form('submit',{
				url:updateUrl,
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					$(addDlgId).dialog('close');
						reload();
					parent.showMsg(result);
				}
			});
		}
			this.remove=remove;
			function remove(){
				var row = getSelectedRow();
				if (row){
					$.messager.confirm('提示：','你确定要删除记录：<font color=red>'+row.name+'</font>吗？',function(r){
						if (r){
							$.post(url+'!remove',{id:row.id},function(result){
								reload();	
								parent.showMsg(result);
							 	if (result.success){
									unselectAll();//清空选择器里面的row，以免修改混乱
								}  
							},'json');
						}
					});//confirm
				}else{
					$.messager.alert('提醒','请选择需要删除的记录!','warning');
				}
			}//remove
}//newsGrid
		//Up
	</script>
</head>
<body id="body" style="display:none;">

		<table id="table_up"></table>
		<div id="dlg_up_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" fit="true" modal="true" collapsible="true"  closed="true" buttons="#dlg-buttons_up"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写记录信息</h2></div>
					</div> 
					<form id="fm_up_add" method="post" novalidate>
						<table align ="center"  cellspacing="20" >
								<tr>
									<td class="dv-label">姓名：</td>
									<td ><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">金额：</td>
									<td><input name = "money" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'"  style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">地点：</td>
									<td><input   name ="location" data-options="required:true,validType:'length[1,30]'"   style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">时间：</td>
									<td><input name = "donateTime" class="easyui-datebox" data-options="required:false,editable:false" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_up">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="newsGrid.saveAdd()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_add').dialog('close')">取消</a>
			</div>
	</div>
	
		<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newsGrid.add()">添加</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'"  onclick="newsGrid.edit()">修改</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除</a>
					</td>
				</tr>
			</table>			
		</div>
		<div id="mm_up" class="easyui-menu" style="width:120px;">
			<div data-options="iconCls:'icon-reload'" onclick="newsGrid.reload();">刷新列表</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除</div>
			<div data-options="iconCls:'icon-edit'" onclick="newsGrid.edit()">修改</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
</body>
<script type="text/javascript">
		window.onload=function (){
			$('#body').show();
			$.parser.parse();
			newsGrid = new NewsGrid('#table_up','anniversary/anniversaryDonation','#dlg_up_add','#fm_up_add');
			newsGrid.createGrid();
		}
</script>
</html>