<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工作通讯录维护</title>
    
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
	var deptTree;
		function MyTree(url,targets){
	  	  this.createTreeData=createTreeData;	
		  function createTreeData(id,data){
			  var children=[];
			  $.each(data,function(i,n){
				  if(n.pid==id){
				  		n['text']=n.name;
				  		if(n.isSystem=="是"){
				  			n['iconCls']='icon-rainbow';
				  		}
				  		n['attributes']={isSystem:n.isSystem};
				  		n['children']=createTreeData(n.id,data);
					  children.push(n);
				  }
			  });
			  return children;
		  }
		  this.loadTreeData=loadTreeData;
		  function loadTreeData(){
			  $.post(url,function(result){
				  var treeData=createTreeData(0,result);
				  $.each(targets,function(i,n){
					  $(n).combotree('loadData',treeData);
					   $(n).combotree({
					   		onSelect:function(record){
					   			if('是'!=record.attributes.isSystem){
						   			$(n).combotree('clear');
						   			$.messager.alert('提醒','不能选择学院，只能选择系别！','warning');
					   			}
					   		}
					  	 }
					   );
				  });
			  },'json');		
		  } 
  } 
	var departmentTree;
		function DepartmentTree(url,targets){
	  	  this.createTreeData=createTreeData;	
		  function createTreeData(id,data){
			  var children=[];
			  $.each(data,function(i,n){
				  if(n.pid==id){
				  		n['text']=n.name;
				  		n['children']=createTreeData(n.id,data);
					  children.push(n);
				  }
			  });
			  return children;
		  }
		  this.loadTreeData=loadTreeData;
		  function loadTreeData(){
			  $.post(url,function(result){
				  var treeData=createTreeData(0,result);
				  $.each(targets,function(i,n){
					  $(n).combotree('loadData',treeData);
					   $(n).combotree({
					   		onSelect:function(record){
					   			/* if('是'!=record.attributes.isSystem){
						   			$(n).combotree('clear');
						   			$.messager.alert('提醒','不能选择学院，只能选择系别！','warning');
					   			} */
					   		}
					  	 }
					   );
				  });
			  },'json');		
		  } 
  } 
function NewsGrid(tableId,url,addDlgId,addFormId){
		this.search=search;
		function search(){
			var departmentId=$('#departmentTreeSearch').combotree('getValue');
			var name=$('#nameSearch').val();
			var position=$('#positionSearch').val();
			$(tableId).datagrid({
					//	url:url+'!show',  
	  					queryParams:{departmentId:departmentId,name:name,position:position}
			});
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
				fitColumns:false,
				autoRowHeight:true,
				striped: true,
				fit: true,
				//collapsible:true,
				url:url+'!show',
				sortName: 'orders',
				sortOrder: 'ASC',
				remoteSort: true,
				idField:'id',
				loadFilter:function(data){
						$(data.rows).each(function(){	
								this['deptName']=this.addressbookDept.name;
								if(this.addressbookDepartment){
									this['departmentName']=this.addressbookDepartment.name;
									this['departmentId']=this.addressbookDepartment.id;
								}
								this['deptId']=this.addressbookDept.id;
						});
						return data;
					},
				frozenColumns:[[
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true},
					{field:'name',title:'姓名',width:80,align:'center',rowspan:2,sortable:true}
					]],
				columns:[[
					{field:'jobNumber',title:'工号',width:100,align:'center',rowspan:2},
					{field:'password',title:'密码',width:80,align:'center',rowspan:2,sortable:true},
	                {title:'职务',field:'position',rowspan:2,align:'center',width:150,sortable:true},
	                {title:'职称',field:'jobTitle',rowspan:2,align:'center',width:50,sortable:true},
	                {title:'顺序',field:'orders',rowspan:2,align:'center',width:50,sortable:true},
	                {title:'院系',field:'deptName',rowspan:2,align:'center',width:200,sortable:true},
	                {title:'领导',field:'isLeader',rowspan:2,align:'center',width:100,sortable:true},
	                {title:'部门',field:'departmentName',rowspan:2,align:'center',width:200,sortable:true},
					{field:'sex',title:'性别',width:50,align:'center',rowspan:2,sortable:true},
					{field:'age',title:'年龄',width:50,align:'center',rowspan:2,sortable:true},
					{field:'partisan',title:'党派',width:100,align:'center',rowspan:2,sortable:true},
					{field:'birthday',title:'生日',width:100,align:'center',rowspan:2,sortable:true},
					{field:'mobilePhone',title:'手机电话',width:100,align:'center',rowspan:2,sortable:true},
					{field:'canmsg',title:'能否发短信',width:100,align:'center',rowspan:2,sortable:true},
					{field:'homePhone',title:'家庭电话',width:100,align:'center',rowspan:2,sortable:true},
					{field:'officePhone',title:'办公电话',width:100,align:'center',rowspan:2,sortable:true},
					{field:'fax',title:'传真',width:100,align:'center',rowspan:2,sortable:true},
					{field:'email',title:'邮箱',width:150,align:'center',rowspan:2,sortable:true},
	                {field:'remark',title:'备注',width:200,align:'center',rowspan:2,sortable:true},
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
			$('#departmentTree').combo('disable');
			$('#departmentTree').combo({required:false});
			$(addDlgId).dialog('open').dialog('setTitle','添加记录');
			$(addFormId).form('clear');
			$(addFormId).form('load',{sex:'男',isLeader:'否',canmsg:'不能'});
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
				if(row.isLeader=="是"){
					$('#departmentTree').combo('enable');
					$('#departmentTree').combo({required:true});
				}else{
					$('#departmentTree').combo('disable');
					$('#departmentTree').combo({required:false});
				}
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
									<td ><input name = "name" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">工号：</td>
									<td><input name = "jobNumber" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:200px;border:1px solid #ccc;padding:2px;">
									</td>
								</tr>
								<tr>
									<td class="dv-label">领导：</td>
									<td><input type="radio" name="isLeader" value="是"><span>是</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="isLeader" value="否"><span>否</span>
									</td>
									<td class="dv-label">领导所属部门：</td>
									<td >
										<input id="departmentTree"  name = "departmentId" class="easyui-combotree" readOnly data-options="required:true" style="width:200px;border:1px solid #ccc;padding:2px;">
									</td>
								</tr>
								<tr>
									<td class="dv-label">性别：</td>
									<td><input type="radio" name="sex" value="男"><span>男</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="sex" value="女"><span>女</span>
									</td>
									<td class="dv-label">密码：</td>
									<td><input name = "password" class="easyui-validatebox" data-options="required:true,validType:'length[1,30]'"  style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">职务：</td>
									<td><input name = "position" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">职称：</td>
									<td><input   name ="jobTitle" data-options="required:true,validType:'length[1,100]'"   style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">院系：</td>
									<td><input id="deptTree" name = "deptId" class="easyui-combotree" data-options="required:true" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">生日：</td>
									<td><input name = "birthday" class="easyui-datebox" data-options="required:false,editable:false" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">手机号码：</td>
									<td><input name = "mobilePhone" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'"  style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">家庭电话：</td>
									<td><input name = "homePhone" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'"  style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">办公电话：</td>
									<td><input name = "officePhone" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'"  style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">传真：</td>
									<td><input name = "fax" class="easyui-validatebox" data-options="required:false,validType:'length[1,100]'"  style="width:200px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">邮箱：</td>
									<td><input name = "email" class="easyui-validatebox" data-options="required:false,validType:'length[1,200]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">顺序：</td>
									<td>	<input  name = "orders" class="easyui-numberspinner" data-options="min:1,max:1000,required:true"  style="width:200px;border:0px solid #ccc;padding:2px;"/></td>
								</tr>
								<tr>
									<td class="dv-label">党派：</td>
									<td><input name = "partisan" class="easyui-validatebox" data-options="required:false,validType:'length[1,200]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">能否发送短信：</td>
									<td><input type="radio" name="canmsg" value="能" ><span>能</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="canmsg" value="不能"><span>不能</span></td>
								</tr>
								<tr>
									<td class="dv-label">备注：</td>
									<td><input name = "remark" class="easyui-validatebox" data-options="required:false,validType:'length[1,30]'" style="width:200px;border:1px solid #ccc;padding:2px;"></td>
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
					<td style="padding-right:2px">
						姓名：<input id ="nameSearch" name = "name" style="width:200px;border:1px solid #ccc;padding:2px;">
						职务：<input id="positionSearch"  name = "position"  style="width:200px;border:1px solid #ccc;padding:2px;">
						领导所属部门：<input id="departmentTreeSearch"  name = "departmentId" class="easyui-combotree"  style="width:200px;border:1px solid #ccc;padding:2px;">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"  onclick="newsGrid.search()">查询</a>
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
			newsGrid = new NewsGrid('#table_up','addressbook/addressbookWork','#dlg_up_add','#fm_up_add');
			newsGrid.createGrid();
			deptTree=new MyTree('public/public!showAdressbookDeptTree',['#deptTree']);
			deptTree.loadTreeData();
			departmentTree=new DepartmentTree('public/public!showAdressbookDepartmentTree',['#departmentTree','#departmentTreeSearch']);
			departmentTree.loadTreeData();
			$('input[type=radio][name=isLeader]').change(function(){
					$('#departmentTree').combotree('clear');
				if(this.value=='是'){
					$('#departmentTree').combo('enable');
					$('#departmentTree').combo({required:true});
				}else{
					$('#departmentTree').combo('disable');
					$('#departmentTree').combo({required:false});
				}
			});
}
</script>
</html>