<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门维护</title>
    
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
<script>
		function refresh(){
				$('#table_up').treegrid('reload');
				//loadTreeData();
		}
		/* function loadTreeData(){
			$.post('addressbook/addressbookPublic!showTree',function(result){
							$('#cc').combotree('loadData',result);
							$('#cc_edit').combotree('loadData',result);		
						},'json');		
		}  
		function loadTreeData(){
			$.post('addressbook/addressbook/addressbookPublic!show',function(result){
							var treeData=createTreeData(0,result.rows);
							$('#cc').combotree('loadData',treeData);
							$('#cc_edit').combotree('loadData',treeData);		
						},'json');		
		} */
		function createTreeData(id,data){
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
		
		$(function(){
			$('#table_up').treegrid({
				iconCls:'icon-save',
				width:700,
				height:350,
				nowrap: false,
				fit: true,
				fitColumns:true,
				autoRowHeight:false,
				rownumbers: false,
				animate:false,
				collapsible:false,
				url:'addressbook/addressbookPublic!show',
				idField:'id',
				treeField:'name',
				frozenColumns:[[
	               
	                {field:'name',title:'部门名',width:250}
				]],
				toolbar:'#tab-toolbar_up',
				columns:[[
					 {title:'顺序',field:'orders',rowspan:2,align:'center',width:200},
					{field:'tel',title:'电话',width:200,rowspan:2},
					{field:'address',title:'地址',width:200,rowspan:2},
					{field:'createTime',title:'发布时间',width:200,rowspan:2},
					{field:'publisher',title:'发布人',width:200,rowspan:2}
				]],
				onLoadSuccess:function(row, data){
											var treeData=createTreeData(0,data.rows);
											treeData.push(new node(0,'无上级部门', 'icon-xiaolian',[]));
											$('#cc').combotree('loadData',treeData);
											$('#cc_edit').combotree('loadData',treeData);	
										},
				onClickRow:function(row){
											
											//$('#table_up').treegrid("unselect",row.id);
										},
				onDblClickRow:function(row){
											//unselectAllUp();
											$('#table_up').treegrid("select",row.id);
											//pid = row.id;
											editUp();
          								
										},
				onContextMenu: function(e,row){
					e.preventDefault();
					$(this).treegrid('unselectAll');
					$(this).treegrid('select', row.id);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			});//treeGrid
		});//$

		var url_up;
		function newUp(){
			var row = $('#table_up').treegrid('getSelected');
		
			$('#dlg_up').dialog('open').dialog('setTitle','添加通讯录');
				$('#fm_up').form('clear');
			if(row){
				$('#fm_up').form('load',{_parentId:row.id});
			}else{
				$('#fm_up').form('load',{_parentId:0});
			}
			//$('#cc').combotree('setValue', 2);
			url_up = 'addressbook/addressbookPublic!save';
		}
		function unselectAllUp(){
			 $('#table_up').treegrid("unselectAll");
		}
		function editUp(){
			
			var row = $('#table_up').treegrid('getSelected');
			if(row){
				$('#dlg_up_edit').dialog('open').dialog('setTitle','修改通讯录');
				$('#fm_up_edit').form('clear');
				$('#fm_up_edit').form('load',row);
				url_up_edit = 'addressbook/addressbookPublic!update';
			}else{
				$.messager.alert('提醒','请选择需要修改的通讯录!','warning');
			} 
		}
		function saveUpEdit(){
			var pid = $('#cc_edit').combotree('getValue');
			var row = $('#table_up').treegrid('getSelected');
			if(pid==row.id){
					$.messager.alert('提醒','上级部门不能更改为当前部门!','warning');
			}else{
					var rows = $('#table_up').treegrid('getChildren', row.id);
					var flag = false;
					for(var i=0; i<rows.length; i++){
							if(pid==rows[i].id){
								flag = true;
							} 
					}
					if(flag){
							$.messager.alert('提醒','上级部门不能更改为当前部门的下级部门!','warning');
					}else{
							$('#fm_up_edit').form('submit',{
									url: url_up_edit,
									onSubmit: function(){
										if($(this).form('validate')){
											return true;
										}else{
											return false;
										}
									},
									success: function(result){
										var result = eval('(' + result + ')');
										if (result.success){
											$('#dlg_up_edit').dialog('close');
											//$('#table_up').treegrid('reload');//以后换为刷新行
											//loadTreeData();
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
							}); //form
					}//else flag
				}//else pid==row.id
		
		}
		function saveUp(){
			$('#fm_up').form('submit',{
				url: url_up,
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					if (result.success){
						$('#dlg_up').dialog('close');
						//$('#table_up').treegrid('reload');
					//	loadTreeData();
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
		function removeUp(){
			var row = $('#table_up').treegrid('getSelected');
			if (row){
				var rows = $('#table_up').treegrid('getChildren', row.id);
				var names = [];
				var msg =row.name;
				for(var i=0; i<rows.length; i++){
					names.push( rows[i].name) ;
				}
				if(names.length>0){
					msg += "以及它的下级部门："+names.join('、');
				}
				$.messager.confirm('提示：','你确定要删除部门：'+msg+'吗?',function(r){
					if (r){
						
						$.post('addressbook/addressbookPublic!delete',{id:row.id},function(result){
							if (result.success){
								unselectAllUp();//清空选择器里面的row，以免修改混乱
								$('#table_up').treegrid('reload');
								loadTreeData();
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
				$.messager.alert('提醒','请选择需要删除的部门!','warning');
			}
		}//remove
	</script>
</head>
<body>
		<table id="table_up"></table>
		<div id="dlg_up" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" collapsible="true"  closed="true" buttons="#dlg-buttons_up"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写部门信息</h2></div>
					</div> 
					<form id="fm_up" method="post" novalidate>
						<table align ="center"  >
								<tr>
									<td>上级部门：</td>
									<td><input id="cc" name = "_parentId" class="easyui-combotree" value="0" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>部门名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">顺序：</td>
									<td>	<input  name = "orders" class="easyui-numberspinner" data-options="min:1,max:1000,required:true"  style="width:200px;border:0px solid #ccc;padding:2px;"/></td>
								</tr>
								<tr>
									<td>部门地址：</td>
									<td><input name = "address" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>联系电话：</td>
									<td><input name = "tel" class="easyui-validatebox" data-options="required:false,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_up">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUp()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up').dialog('close')">取消</a>
			</div>
	</div>
	
	<div id="dlg_up_edit" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" collapsible="true"  closed="true" buttons="#dlg-buttons_up_edit"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写部门信息</h2></div>
					</div> 
					<form id="fm_up_edit" method="post" novalidate>
						<input name = "id" type="hidden">
						<table align ="center"  >
								<tr>
									<td>上级部门：</td>
									<td><input id="cc_edit" name = "_parentId" value = "0" class="easyui-combotree" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>部门名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">顺序：</td>
									<td>	<input  name = "orders" class="easyui-numberspinner" data-options="min:1,max:1000,required:true"  style="width:200px;border:0px solid #ccc;padding:2px;"/></td>
								</tr>
								<tr>
									<td>部门地址：</td>
									<td><input name = "address" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td>联系电话：</td>
									<td><input name = "tel" class="easyui-validatebox" data-options="required:false,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_up_edit">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUpEdit()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_edit').dialog('close')">取消</a>
			</div>
	</div>
	
	
	<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newUp()">添加部门</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="removeUp()">删除部门</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editUp()">修改部门</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="refresh()">刷新列表</a>
					</td>
				</tr>
			</table>			
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div data-options="iconCls:'icon-reload'" onclick="refresh()">刷新列表</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-add'" onclick="newUp()">添加部门</div>
		<div data-options="iconCls:'icon-cut'" onclick="removeUp()">删除部门</div>
		<div data-options="iconCls:'icon-edit'" onclick="editUp()">修改部门</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-tuichu'" >退出</div>
	</div>
</body>
</html>
