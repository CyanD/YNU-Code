<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色维护</title>
    
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
	<script type="text/javascript" src="resources/js/json/json2.js"></script>
	<script type="text/javascript" >
function NewsGrid(tableId,url,addDlgId,addFormId,editDlgId,editFromId){
		var lastIndex;
		this.manageImg=manageImg;
		function manageImg(){
				var row = getSelectedRow();
				var pid = imgGrid.getPid();
				if(pid!=row.id){
						imgGrid.setPRow(row);
						imgGrid.activeGrid();
						$('#layout_down').panel({
							title:"相关图片维护（所属角色：《"+row.title+"》！）"
						 });
				}
		}
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
				fitColumns:true,
				autoRowHeight:true,
				striped: true,
				fit: true,
				//collapsible:true,
				url:url+'!show',
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: true,
				idField:'id',
				columns:[[
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true},
					{field:'name',title:'角色名',width:80,align:'center',rowspan:2,editor:{type:'validatebox',options:{required:true,validType:'length[1,30]'}}},
					{field:'sys',title:'系统维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
	                {field:'news',title:'新闻维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
	                {field:'anniversary',title:'校庆维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
					{field:'bus',title:'校巴维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
	                {field:'map',title:'地图维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
	                {field:'course',title:'课程维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
					{field:'addressBook',title:'通讯录维护权限',width:30,align:'center',rowspan:2,sortable:true,editor:{type:'checkbox',options:{on:'有',off:'无'}}},
					{field:'createTime',title:'发布时间',width:50,align:'center',rowspan:2,sortable:true},
					{field:'publisher',title:'发布人',width:50,align:'center',rowspan:2,sortable:true}
				]],
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				onLoadSuccess:function (data){
					unselectAll();
				 },
				onClickRow:function(index,row){
											unselectAll();
											$(tableId).datagrid("selectRow",index);
										},
				onDblClickRow:function(index,row){
											unselectAll();
											$(tableId).datagrid("selectRow",index);
											edit();
										},
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
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
		this.add=add;
		function add(){
			cancel();
			$(tableId).datagrid('appendRow',{});
			lastIndex = $(tableId).datagrid('getRows').length-1;
			$(tableId).datagrid('selectRow', lastIndex);
			$(tableId).datagrid('beginEdit', lastIndex);
		}
		function unselectAll(){
			 $(tableId).datagrid("unselectAll");
		}
		this.edit=edit;
		function edit(){
			var rowIndex =  $(tableId).datagrid("getRowIndex",getSelectedRow());
			cancel();
			$(tableId).datagrid('beginEdit', rowIndex);
			lastIndex = rowIndex;
		}
		/* function checkChange(){
			 $(tableId).datagrid('endEdit', lastIndex);
			 var changed=$(tableId).datagrid('getChanges');
			 if(changed.length>0){
			 		var index = $(tableId).datagrid("getRowIndex",changed[0]);
			 		$.messager.confirm('提示：','第'+(index+1)+'行的修改还未保存，是否需要保存？',function(r){
						if (r){
							save();
						}
					});//confirm
					cancel();
			 }
		} */
		this.save=save;
		function save(){
			 $(tableId).datagrid('endEdit', lastIndex);
			 if ($(tableId).datagrid('getChanges').length>0) {
				 var inserted = $(tableId).datagrid('getChanges', "inserted");
			 	 var updated = $(tableId).datagrid('getChanges', "updated");
			 	 //alert(JSON.stringify(updated));
			 	 if(inserted.length){
			 	 		if(inserted[0].name){
				 	 		$.post(url+'!add',inserted[0],function(result){
								reload();	
								if (result.success){
									$.messager.show({	
										title: '成功',
										msg: result.msg,
										showType:'show',
										timeout:10000
									});
									cancel();
								} else {
									$.messager.alert( '失败',result.msg,'error');
								}
							},'json');
						}else{
							$.messager.alert('提醒','角色名不能为空!','warning');
						}
			 	 }
			 	 if(updated.length){
			 	 		$.post(url+'!edit',updated[0],function(result){
							if (result.success){
								reload();	
								$.messager.show({	
									title: '成功',
									msg: result.msg,
									showType:'show',
									timeout:10000
								});
								cancel();
							} else {
								$.messager.alert( '失败',result.msg,'error');
							}
						},'json');
			 	 }
			}
		}
		this.cancel=cancel;
		function cancel(){
			$(tableId).datagrid('rejectChanges');
		}
		this.remove=remove;
		function remove(){
			cancel();
			var row = getSelectedRow();
			if (row){
				$.messager.confirm('提示：','你确定要删除角色'+row.name+'吗？',function(r){
					if (r){
						$.post(url+'!delete',{id:row.id},function(result){
							reload();	
							if (result.success){
								unselectAll();//清空选择器里面的row，以免修改混乱
								$.messager.show({	
									title: '成功',
									msg: result.msg,
									showType:'show',
									timeout:10000
								});
							} else {
								$.messager.alert( '失败',result.msg,'error');
							}
						},'json');
					}
				});//confirm
			}else{
				$.messager.alert('提醒','请选择需要删除的角色!','warning');
			}
		}//remove
}//newsGrid
		//Up
	</script>
</head>
<body >
		<table id="table_up"></table>
		<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newsGrid.add()">添加角色</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除角色</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'"  onclick="newsGrid.save()">保存修改</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'"  onclick="newsGrid.cancel()">取消修改</a>
					</td>
				</tr>
			</table>			
		</div>
		
		<div id="mm_up" class="easyui-menu" style="width:120px;">
			<div data-options="iconCls:'icon-reload'" onclick="newsGrid.reload();">刷新列表</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除角色</div>
			<div data-options="iconCls:'icon-edit'" onclick="newsGrid.edit()">修改角色</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
</body>
<script type="text/javascript">
var newsGrid = new NewsGrid('#table_up','sys/sysRole','#dlg_up_add','#fm_up_add','#dlg_up_edit','#fm_up_edit');
newsGrid.createGrid();
</script>
</html>