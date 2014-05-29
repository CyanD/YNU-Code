<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String TreeName = "系别";
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
	<script type="text/javascript" >
		function TreeGrid(tableId,preUrl,dlgAdd,fmAdd,dlgEdit,fmEdit,treeName){
				function createTreeData(id,data){
					var children=[];
					$.each(data,function(i,n){
						if(n._parentId==id&&n.isSystem!='是'){
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
			this.refresh=refresh;
			function refresh(){
				$(tableId).treegrid('reload');
			}
			this.createTreeGrid=createTreeGrid;
			function createTreeGrid(){
				$(tableId).treegrid({
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
					url:preUrl+'!show',
					idField:'id',
					treeField:'name',
					loadFilter:function(data){
						$(data.rows).each(function(){	
							if(this.isSystem=='是'){
								this['iconCls']='icon-rainbow';
							}
						});
						return data;
					},
					frozenColumns:[[
		               
		                {field:'name',title:treeName+'名称',width:400}
					]],
					toolbar:'#tab-toolbar',
					columns:[[
						 {title:'顺序',field:'orders',rowspan:2,align:'center',width:200},
						{field:'remark',title:'备注',width:200,rowspan:2},
						{field:'createTime',title:'创建 / 修改时间',width:200,rowspan:2},
						{field:'publisher',title:'创建 / 修改人',width:200,rowspan:2}
					]],
					onLoadSuccess:function(row, data){
												var treeData=createTreeData(0,data.rows);
												$('#cc').combotree('loadData',treeData);
												$('#cc_edit').combotree('loadData',treeData);	
											},
					onClickRow:function(row){
												if(row.isSystem!='是'){
													unselectAll();
												}
												//$(tableId).treegrid("unselect",row.id);
											},
					onDblClickRow:function(row){
												if(row.isSystem!='是'){
													unselectAll();
													return false;
												}
												//pid = row.id;
												edit();
	          								
											},
					onContextMenu: function(e,row){
						e.preventDefault();
						if(row.isSystem!='是'){
							return false;
						}
						$(this).treegrid('unselectAll');
						$(this).treegrid('select', row.id);
						$('#mm').menu('show', {
							left: e.pageX,
							top: e.pageY
						});
					}
				});//treeGrid
			}//createTreeGrid
			this.add=add;
			function add(){
				var row = $(tableId).treegrid('getSelected');
			
				$(dlgAdd).dialog('open').dialog('setTitle','添加'+treeName);
					$(fmAdd).form('clear');
				if(row){
					$(fmAdd).form('load',{_parentId:row._parentId});
				}else{
					$(fmAdd).form('load',{_parentId:0});
				}
			}
			function unselectAll(){
				 $(tableId).treegrid("unselectAll");
			}
			this.edit=edit;
			function edit(){
				
				var row = $(tableId).treegrid('getSelected');
				if(row){
					$(dlgEdit).dialog('open').dialog('setTitle','修改'+treeName);
					$(fmEdit).form('clear');
					$(fmEdit).form('load',row);
				}else{
					$.messager.alert('提醒','请选择需要修改的'+treeName+'！','warning');
				} 
			}
			this.closeDlgEdit=closeDlgEdit;
			function closeDlgEdit(){
				$(dlgEdit).dialog('close')
			}
			this.saveEdit=saveEdit;
			function saveEdit(){
				var pid = $('#cc_edit').combotree('getValue');
				var row = $(tableId).treegrid('getSelected');
				if(pid==row.id){
						$.messager.alert('提醒','上级'+treeName+'不能更改为当前'+treeName+'！','warning');
				}else{
						var rows = $(tableId).treegrid('getChildren', row.id);
						var flag = false;
						for(var i=0; i<rows.length; i++){
								if(pid==rows[i].id){
									flag = true;
								} 
						}
						if(flag){
								$.messager.alert('提醒','上级'+treeName+'不能更改为当前'+treeName+'的下级'+treeName+'！','warning');
						}else{
								$(fmEdit).form('submit',{
										url: preUrl+'!edit',
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
												$(tableId).treegrid('reload');
												closeDlgEdit();
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
			this.saveAdd=saveAdd;
			function saveAdd(){
				$(fmAdd).form('submit',{
					url: preUrl+'!add',
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
							closeDlgAdd()
							$(tableId).treegrid('reload');
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
			this.closeDlgAdd=closeDlgAdd;
			function closeDlgAdd(){
				$(dlgAdd).dialog('close')
			}
			this.remove=remove;
			function remove(){
				var row = $(tableId).treegrid('getSelected');
				if (row){
					var rows = $(tableId).treegrid('getChildren', row.id);
					var names = [];
					var msg =row.name;
					for(var i=0; i<rows.length; i++){
						names.push( rows[i].name) ;
					}
					if(names.length>0){
						msg += treeName+'包含下级'+treeName+'：'+names.join('、')+'，不能删除！';
						$.messager.alert('提醒',msg,'warning');
						return false;
					}
					$.messager.confirm('提示：','你确定要删除'+treeName+'：'+msg+'吗?',function(r){
						if (r){
							
							$.post(preUrl+'!remove',{id:row.id},function(result){
								if (result.success){
									unselectAll();//清空选择器里面的row，以免修改混乱
									$(tableId).treegrid('reload');
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
					$.messager.alert('提醒','请选择需要删除的'+treeName+'！','warning');
				}
			}//remove
		}//
	</script>

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
									<td class="dv-label">上级<%=TreeName%>：</td>
									<td><input id="cc" name = "_parentId" class="easyui-combotree" value="0" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label"><%=TreeName%>名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">顺序：</td>
									<td>	<input  name = "orders" class="easyui-numberspinner" data-options="min:1,max:1000,required:true"  style="width:200px;border:0px solid #ccc;padding:2px;"/></td>
								</tr>
								<tr>
									<td class="dv-label">备注：</td>
									<td><textarea name = "remark" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'" style="height:100px;width:400px;border:1px solid #ccc;	padding:2px;"></textarea></td>
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
									<td class="dv-label">上级<%=TreeName%>：</td>
									<td><input id="cc_edit" name = "_parentId" value = "0" class="easyui-combotree" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label"><%=TreeName%>名称：</td>
									<td><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">顺序：</td>
									<td>	<input  name = "orders" class="easyui-numberspinner" data-options="min:1,max:1000,required:true"  style="width:200px;border:0px solid #ccc;padding:2px;"/></td>
								</tr>
								<tr>
									<td class="dv-label">备注：</td>
									<td><textarea name = "remark" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'" style="height:100px;width:400px;border:1px solid #ccc;	padding:2px;"></textarea></td>
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
	<div id="mm" class="easyui-menu" style="width:140px;">
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
 var treeGrid = new TreeGrid('#table','addressbook/addressbookDeptSystem','#dlg_add','#fm_add','#dlg_edit','#fm_edit','<%=TreeName%>');
 treeGrid.createTreeGrid();
</script>
</html>
