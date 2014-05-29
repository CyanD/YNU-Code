<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户维护</title>
    
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
	<script type="text/javascript" src="resources/js/myjs/mytree.js"></script>
	<script type="text/javascript" >
	$(function(){
			$('#roleComb').combogrid({
				panelWidth:700,
				idField:'roleId',
				editable:false,
				striped: true,
				textField:'name',
				url:'sys/sysUser!showRoleCombogrid',
				columns:[[
					{title:'编号',field:'id',width:1,sortable:true,hidden:true},
					{field:'name',title:'角色名',width:100,align:'center'},
					{field:'sys',title:'系统维护权限',width:80,align:'center'},
	                {field:'news',title:'角色维护权限',width:80,align:'center'},
	                {field:'anniversary',title:'校庆维护权限',width:80,align:'center'},
					{field:'bus',title:'校巴维护权限',width:80,align:'center'},
	                {field:'map',title:'地图维护权限',width:80,align:'center'},
	                {field:'course',title:'课程维护权限',width:80,align:'center'},
					{field:'addressBook',title:'通讯录维护权限',width:100,align:'center'}
				]]
			});
		});
	/* function createTreeData(id,data){
				var children=[];
				$.each(data,function(i,n){
					if(n._parentId==id){
						children.push(new node(n.id,n.name, null,createTreeData(n.id,data)));
					}
				});
				return children;
	}
	function node(id,text,iconCls,children){
				this.id=id;
				this.text=text;
				this.iconCls=iconCls;
				this.children=children;
	}
	function loadTreeData(){
		$.post('sys/sysUser!showSysDeptTree',function(result){
						var treeData=createTreeData(0,result);
						$('#deptTree').combotree('loadData',treeData);
					},'json');		
	} 
	loadTreeData(); */
function NewsGrid(tableId,url,addDlgId,addFormId,editDlgId,editFromId){
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
					{field:'ck',checkbox:true},
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true},
	                {title:'角色编号',field:'roleId',rowspan:2,width:20,sortable:true,hidden:true},
	                {title:'部门编号',field:'deptId',rowspan:2,width:20,sortable:true,hidden:true},
					{field:'account',title:'账号',width:50,align:'center',rowspan:2},
					{field:'password',title:'密码',width:50,align:'center',rowspan:2,sortable:true},
					{field:'name',title:'姓名',width:50,align:'center',rowspan:2,sortable:true},
					{field:'roleName',title:'角色',width:50,align:'center',rowspan:2,sortable:true},
					{field:'deptName',title:'部门',width:50,align:'center',rowspan:2,sortable:true},
					{field:'tel',title:'电话',width:50,align:'center',rowspan:2,sortable:true},
					{field:'email',title:'邮箱',width:50,align:'center',rowspan:2,sortable:true},
	                {field:'status',title:'状态',width:50,align:'center',rowspan:2,sortable:true},
					{field:'createTime',title:'创建/修改时间',width:80,align:'center',rowspan:2,sortable:true},
					{field:'publisher',title:'创建/修改人',width:50,align:'center',rowspan:2,sortable:true}
				]],
				pagination:true,
				rownumbers:false,
				//singleSelect:true,
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
			$(addDlgId).dialog('open').dialog('setTitle','添加用户');
			$(addFormId).form('clear');
			$(addFormId).form('load',{status:'未激活'});
			updateUrl=url+'!add';
		}
		function unselectAll(){
			 $(tableId).datagrid("unselectAll");
		}
		this.edit=edit;
		function edit(){
			var row = getSelectedRow();
			if (row){
				$(addDlgId).dialog('open').dialog('setTitle','修改用户');
				$(addFormId).form('clear');
				$(addFormId).form('load',row);
				//$('#roleComb').combogrid('setValue', row.roleId);
				updateUrl=url+'!edit?id='+row.id;
			}else{
				$.messager.alert('提醒','请选择需要修改的用户!','warning');
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
					if (result.success){
						reload();
						$.messager.show({
							title: '成功',
							msg: result.msg,
							showType:'show',
							timeout:10000
						});
					} else {
						$.messager.alert( '失败',result.msg,'error');
					}
				}
			});
		}
		this.remove=remove;
		function remove(){
			var rows = $(tableId).datagrid('getSelections');
			if (rows.length>0){
				var idss = [];
				var titles=[];
				var needInit = false;
				for(var i=0;i<rows.length;i++){
					if(imgGrid.getPid()==rows[i].id){
						needInit=true;
					}
					idss.push(rows[i].id);
					titles.push(rows[i].title);
				}
				var ids = idss.join(',');
				$.messager.confirm('提示：','此操作会同时删除与之相关的所有图片，你确定要删除标题为：'+titles.join('、')+'的用户吗？',function(r){
					if (r){
						$.post(url+'!delete',{ids:ids},function(result){
							if (result.success){
								unselectAll();//清空选择器里面的row，以免修改混乱
								if(needInit){
										imgGrid.initGridDown();//重新设置Down，以免添加已经被删的数据
										//不是本类
								}
								reload();	
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
				$.messager.alert('提醒','请选择需要删除的用户!','warning');
			}
		}//remove
		this.activateStatus=activateStatus;
		function activateStatus(){
			var rows = $(tableId).datagrid('getSelections');
			if (rows.length>0){
				var msg='';
				var all =[];
				var idss = [];
				var isAct = [];
				for(var i=0;i<rows.length;i++){
					if('已激活'==rows[i].status){
						isAct.push(rows[i].id);
					}else{
						idss.push(rows[i].id);
					}
						all.push(rows[i].id);
				}
				if(idss.length<1){
						$.messager.alert('提醒','编号为'+all.join(',')+'的用户被选中，并且已经激活，无需重复激活！','warning');
				}else{
						if(isAct.length>0){
								msg = '编号为'+all.join(',')+'的用户被选中，其中编号为'+isAct.join(',')+'的用户已经激活，';
						}
						msg+='你确定要激活编号为'+idss.join(',')+'的用户吗？';
						var ids = idss.join(',');
						$.messager.confirm('提示：',msg,function(r){
							if (r){
								$.post(url+'!updateStatus',{ids:ids,status:'已激活'},function(result){
									if (result.success){
										reload();	
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
				}
			}else{
				$.messager.alert('提醒','请选择需要激活的用户!','warning');
			}
		}
		this.revokeStatus=revokeStatus;
		function revokeStatus(){
				var rows = $(tableId).datagrid('getSelections');
				if (rows.length>0){
					var msg='';
					var all =[];
					var idss = [];
					var isAct = [];
					for(var i=0;i<rows.length;i++){
						if('未激活'==rows[i].status){
							isAct.push(rows[i].id);
						}else{
							idss.push(rows[i].id);
						}
							all.push(rows[i].id);
					}
					if(idss.length<1){
							$.messager.alert('提醒','编号为'+all.join(',')+'的用户被选中，并且全部处于未激活状态，无需禁用！','warning');
					}else{
							if(isAct.length>0){
									msg = '编号为'+all.join(',')+'的用户被选中，其中编号为'+isAct.join(',')+'的用户处于未激活状态，';
							}
							msg+='你确定要禁用编号为'+idss.join(',')+'的用户吗？';
							var ids = idss.join(',');
							$.messager.confirm('提示：',msg,function(r){
								if (r){
									$.post(url+'!updateStatus',{ids:ids,status:'未激活'},function(result){
										if (result.success){
											reload();	
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
					}
				}else{
					$.messager.alert('提醒','请选择需要禁用的用户!','warning');
				}
		}
}//newsGrid
		//Up

	</script>
</head>
<body >

		<table id="table_up"></table>
		<div id="dlg_up_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" collapsible="true"  closed="true" buttons="#dlg-buttons_up"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写用户信息</h2></div>
					</div> 
					<form id="fm_up_add" method="post" novalidate>
						<table align ="center"  >
								<tr>
									<td>账号：</td>
									<td><input name = "account" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>密码：</td>
									<td><input name = "password" class="easyui-validatebox" data-options="required:true" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>确认密码：</td>
									<td><input name = "password2" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>姓名：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>角色：</td>
									<td>    <select  id="roleComb" name = "roleId"  data-options="required:true"  style="width:200px;border:1px solid #ccc;padding:2px;"></select></td>
								</tr>
								<tr>
									<td>部门：</td>
									<td><input id="deptTree" name = "deptId" class="easyui-combotree" value="0" data-options="required:true" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>电话：</td>
									<td><input name = "tel" class="easyui-validatebox" data-options="required:true" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>邮箱：</td>
									<td><input name = "email" class="easyui-validatebox" data-options="required:true" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>激活用户：</td>
									<td><input type="radio" name="status" value="已激活" ><span>是</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="status" value="未激活"><span>否</span></td>
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
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_add'" onclick="newsGrid.add()">添加用户</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_remove'"  onclick="newsGrid.remove()">删除用户</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fabu2'"  onclick="newsGrid.activateStatus()">激活用户</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fabu'" onclick="newsGrid.revokeStatus()">禁用激活</a>
					</td>
				</tr>
			</table>			
		</div>
		<div id="mm_up" class="easyui-menu" style="width:120px;">
			<div data-options="iconCls:'icon-reload'" onclick="newsGrid.reload();">刷新列表</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-application_remove'"  onclick="newsGrid.remove()">删除用户</div>
			<div data-options="iconCls:'icon-application_edit'" onclick="newsGrid.edit()">修改用户</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-fabu2'"  onclick="newsGrid.activateStatus()">激活用户</div>
			<div data-options="iconCls:'icon-fabu'" onclick="newsGrid.revokeStatus()">禁用激活</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
</body>
<script type="text/javascript">
var newsGrid = new NewsGrid('#table_up','sys/sysUser','#dlg_up_add','#fm_up_add','#dlg_up_edit','#fm_up_edit');
newsGrid.createGrid();
var deptTree=new MyTree('public/public!showSysDeptTree',['#deptTree']);
deptTree.loadTreeData();
</script>
</html>