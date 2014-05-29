<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校历管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校历维护</title>
		<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/js/easyui/demo.css">
	<script type="text/javascript" src="resources/js/easyui/jq.eui.loc.js"></script>
	<script>
		//route
		var categoryId=null;
		var categoryName=null;
		var routeId=null;
		var routeName=null;
		var tablecId = 'table_category';
			$(function(){
			$('#'+tablecId).datagrid({
				iconCls:'icon-save',
				nowrap: true,
				fitColumns:true,
				autoRowHeight:false,
				nowrap: false,
				striped: true,
				fit: true,
				//collapsible:true,
				url:'calendar/calendarCategory!show',
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: true,
				idField:'id',
				frozenColumns:[[
	                {field:'ck',checkbox:true,autocheck:false},
	                {title:'编号',field:'id',width:30,rowspan:2,hidden:true,sortable:true}
				]],
				columns:[[
					{field:'title',title:'学期分类',width:100,rowspan:2},
					{field:'beginDate',title:'起始日期',width:100,rowspan:2},
				    {field:'weeknum',title:'学期周数',width:100,rowspan:2},
					{field:'createTime',title:'发布时间',width:150,rowspan:2},
					{field:'publisher',title:'发布人',width:100,rowspan:2}
				]],
				pagination:true,
				rownumbers:false,
				//singleSelect:true,
				onClickRow:function(index,row){
											$('#'+tablecId).datagrid("unselectRow",index);
										},
				onDblClickRow:function(index,row){
											categoryId = row.id;
											categoryName = row.title;
											$('#test').datagrid({
													url:'calendar/schoolCalendar!show', 
		                    						queryParams:{cid:categoryId}
          									 });
										},
				toolbar:'#tab-toolbar_category'
			});//drid
	})//$
	
	
		var url_route1;
		function newCalendarCategory(){
			$('#dlg_category').dialog('open').dialog('setTitle','添加学期分类');
			$('#fm_category').form('clear');
			url_route1 = 'calendar/calendarCategory!save';
		}
		function unselectAllRoute(){
			 $('#'+tablecId).datagrid("unselectAll");
		}
		
		function updateCalendarCategory(index){
				var row = $('#'+tablecId).datagrid("getRows")[index];
				$('#dlg_category').dialog('open').dialog('setTitle','修改学期分类');
				$('#fm_category').form('clear');
				$('#fm_category').form('load',row);
				url_route1 = 'calendar/calendarCategory!update?id='+row.id;
		}
		function editCalendarCategory(){
			var row = $('#'+tablecId).datagrid('getSelected');
			//unselectAllRoute();
			if (row){
				$('#dlg_category').dialog('open').dialog('setTitle','修改学期分类');
				$('#fm_category').form('clear');
				$('#fm_category').form('load',row);
				url_route1 = 'calendar/calendarCategory!update?id='+row.id;
			}else{
				$.messager.alert('提醒','请选择需要修改的学期分类!','warning');
			}
		}
		function saveCalendarCategory(){
			$('#fm_category').form('submit',{
				url: url_route1,
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					$('#dlg_category').dialog('close');
					if (result.success){
						$('#'+tablecId).datagrid('reload');
					
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
		function removeCalendarCategory(){
			
			var rows = $('#'+tablecId).datagrid('getSelections');
			if (rows.length>0){
				$.messager.confirm('提示：','此操作会同时删除与之相关的活动，你确定要删除吗?',function(r){
					if (r){
						var idss = [];
						var needInit = false;
						for(var i=0;i<rows.length;i++){
							if(categoryId==rows[i].id){
								needInit=true;
							}
							idss.push(rows[i].id);
						}
						$.post('calendar/calendarCategory!delete',{ids:idss.join(',')},function(result){
							if (result.success){
								unselectAllRoute();//清空选择器里面的row，以免修改混乱
								if(needInit){
										initGridcategory();
										initGrid();
								}
								$('#'+tablecId).datagrid('reload');	
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
				$.messager.alert('提醒','请选择需要删除的学期分类!','warning');
			}
		}
		
		
			
		function initGridcategory(){
			$('#test').datagrid({url:null });
          	categoryId=null;
            $('#test').datagrid('loadData',{"rows":[],"total":0});
		}
		var tableId = 'test';
		$(function(){
			$('#'+tableId).datagrid({
				//title:'校历活动时间列表',
				iconCls:'icon-save',
				nowrap: true,
				fitColumns:true,
				autoRowHeight:false,
				nowrap: false,
				striped: true,
				fit: true,
				//collapsible:true,
				//url:'calendar/schoolCalendar!show',
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: true,
			  	idField:'id',
			    frozenColumns:[[
	                {field:'ck',checkbox:true,autocheck:false},
	                //{title:'编号',field:'id',width:30,rowspan:2,hidden:true,sortable:true}
	                {title:'所属学期分类',field:'rrr',width:200,rowspan:2,formatter:function(value,row,index){
	                	return categoryName;
	                }},
	                {title:'学期分类编号',field:'cid',width:10,rowspan:2,hidden:true,sortable:true}
				]],
				columns:[[
					{field:'acttime',title:'活动时间',width:100,rowspan:2},
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
											//alert(row.id);
											routeName = row.acttime;
											$('#table_schedule').datagrid({
													url:'calendar/schoolCalendarSub!show', 
		                    						queryParams:{pid:routeId}
          									 });
										},
				toolbar:'#tab-toolbar'
			});//drid
	})//$
		var url;
		function newSchoolCalendar(){
			if(categoryId!=null){
				$('#dlg').dialog('open').dialog('setTitle','添加活动');
				$('#fm').form('clear');
				url = 'calendar/schoolCalendar!save?cid='+categoryId;
			}else{
				$.messager.alert('提醒','请先选择学期分类！ 双击需要维护的学期分类即可！','warning');
			}
		}
		function unselectAllSche(){
			 $('#'+tableId).datagrid("unselectAll");
		}
		
		function updateSchoolCalendar(index){
				var row = $('#'+tableId).datagrid("getRows")[index];
				$('#dlg').dialog('open').dialog('setTitle','修改活动时间');
				$('#fm').form('clear');
				$('#fm').form('load',row);
				url_route = 'calendar/schoolCalendar!update?id='+row.id+'&cid='+categoryId;
		}
		function editSchoolCalendar(){
			var row = $('#'+tableId).datagrid('getSelected');
			//unselectAllSche();
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','修改活动时间');
				$('#fm').form('clear');
				$('#fm').form('load',row);
				url = 'calendar/schoolCalendar!update?id='+row.id+'&cid='+categoryId;
			}else{
				$.messager.alert('提醒','请选择需要修改的活动时间!','warning');
			}
		}
		function saveSchoolCalendar(){
			$('#fm').form('submit',{
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
		function removeSchoolCalendar(){
			
					var rows = $('#'+tableId).datagrid('getSelections');
			if (rows.length>0){
				$.messager.confirm('提示：','此操作会同时删除与之相关的活动，你确定要删除吗?',function(r){
					if (r){
						var idss = [];
						var needInit = false;
						for(var i=0;i<rows.length;i++){
							if(routeId==rows[i].id){
								needInit=true;
							}
							idss.push(rows[i].id);
						}
						$.post('calendar/schoolCalendar!delete',{ids:idss.join(',')},function(result){
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
				$.messager.alert('提醒','请选择需要删除的活动时间!','warning');
			}
		}
		
			
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
				//title:'校历活动时间列表',
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
	                {title:'所属时间',field:'sss',width:200,rowspan:2,formatter:function(value,row,index){
	                	return routeName;
	                }},
	                {title:'活动时间编号',field:'pid',width:10,rowspan:2,hidden:true,sortable:true}
				]],
				columns:[[
					{field:'category',title:'分类',width:50,rowspan:2},
					{field:'title',title:'活动内容',width:50,rowspan:2},
					{field:'createTime',title:'发布时间',width:100,rowspan:2},
					{field:'publisher',title:'发布人',width:50,rowspan:2}
				]],
				pagination:true,
				rownumbers:false,
				toolbar:'#tab-toolbar_schedule'
			});//drid
	})//$
		var url;
		function newSchoolCalendarSub(){
			if(routeId!=null){
				$('#dlg_schedule').dialog('open').dialog('setTitle','添加活动');
				$('#fm_schedule').form('clear');
				url = 'calendar/schoolCalendarSub!save?pid='+routeId;
			}else{
				$.messager.alert('提醒','请先选择活动时间！ 双击需要维护的活动时间即可！','warning');
			}
		}
		function unselectAllSche1(){
			 $('#'+tableId_schedule).datagrid("unselectAll");
		}
		function editSchoolCalendarSub(){
			var row = $('#'+tableId_schedule).datagrid('getSelected');
			//alert(row.id);
			unselectAllSche();
			if (row){
				$('#dlg_schedule').dialog('open').dialog('setTitle','修改活动内容');
				$('#fm_schedule').form('clear');
				$('#fm_schedule').form('load',row);
				url = 'calendar/schoolCalendarSub!update?id='+row.id+'&pid='+routeId;
			    $('#'+tableId_schedule).datagrid("unselectAll");
			}else{
				$.messager.alert('提醒','请选择需要修改的活动!','warning');
			}
		}
		function saveSchoolCalendarSub(){
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
		function removeSchoolCalendarSub(){
			
			var rows = $('#'+tableId_schedule).datagrid('getSelections');
			if (rows.length>0){
				$.messager.confirm('提示：','你确定要删除吗?',function(r){
					if (r){
						var idss = [];
						for(var i=0;i<rows.length;i++){
							idss.push(rows[i].id);
						}
						$.post('calendar/schoolCalendarSub!delete',{ids:idss.join(',')},function(result){
							if (result.success){
								unselectAllSche1();
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
				$.messager.alert('提醒','请选择需要删除的活动!','warning');
			}
		}
	$(function(){
        
     $(".datebox :text").attr("readonly","readonly");
 
    })
	</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" title="类别维护" style="height:200px;width:400px;overflow:hidden;">
	<table id="table_category"></table>
		<div id="dlg_category" class="easyui-dialog" style="width:300px;height:280px;padding:10px 20px"
			 fit="true" modal ="true"   draggable="false" 	closed="true" buttons="#dlg-buttons_category"><br>
			<div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>学期分类表</h2></div>
		    </div> 
					<form id="fm_category" method="post" novalidate>
							<table align ="center"  >
								<tr>
									<td>学期分类：</td>
									<td><input name = "title" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:150px;border:1px solid #ccc;padding:2px;">
									</td>
								</tr>
								<tr>
									<td>起始日期：</td>
									<td><input name = "beginDate"   class="easyui-datebox" data-options="required:true,validType:'length[1,50]'"       style="width:150px;border:1px solid #ccc;padding:2px;">
									</td>
								</tr>
								<tr>
									<td>学期周数：</td>
									<td><input name="weeknum" id="weeknum" type="text" class="easyui-numberbox" min="1" max="54" size="2" maxlength="2"  style="width:150px;border:1px solid #ccc;padding:2px;"/> </td>
								</tr>
						</table>
				</form>
		</div>
		<div id="dlg-buttons_category">
			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCalendarCategory()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_category').dialog('close')">取消</a>
		</div>
		<div id="tab-toolbar_category">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newCalendarCategory()">添加学期分类</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="removeCalendarCategory()">删除学期分类</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editCalendarCategory()">修改学期分类</a>
					</td>
			</tr>
		</table>			
		</div>

</div>




<div data-options="region:'center'" title="校历维护" style="height:300px;overflow:hidden;">
	<table id="test"></table>
		<div id="dlg" class="easyui-dialog" style="width:300px;height:280px;padding:10px 20px"
			 fit="true" modal ="true"   draggable="false" 	closed="true" buttons="#dlg-buttons"><br>
			<div class="ftitle" align = "center">请填写校历信息</div>
					<form id="fm" method="post" novalidate>
							<table align ="center"  >
								<tr>
									<td class="dv-label">活动时间：</td>
									<td><input name = "acttime" class="easyui-datebox" data-options="required:true,validType:'length[1,100]'"   style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSchoolCalendar()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div id="tab-toolbar">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newSchoolCalendar()">添加活动时间</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="removeSchoolCalendar()">删除活动时间</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editSchoolCalendar()">修改活动时间</a>
					</td>
			</tr>
		</table>			
		</div>
	</div>	






<div data-options="region:'south'" title="活动维护" style="height:200px;width:400px;overflow:hidden;">
		<table id="table_schedule"></table>
		<div id="dlg_schedule" class="easyui-dialog" style="width:300px;height:280px;padding:10px 20px"
				closed="true" buttons="#dlg-buttons_schedule">
				<div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>活动表</h2></div>
					</div> 
					<form id="fm_schedule" method="post" novalidate>
						<table align ="center"  >
								<tr>
								
									<td>分类：</td>
									<td><input name = "category" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:150px;border:1px solid #ccc;padding:2px;">
									</td>
								</tr>
								<tr>
									<td>活动内容：</td>
									<td><input name = "title" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:150px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
						</table>
				</form>
		</div>
		<div id="dlg-buttons_schedule">
			<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSchoolCalendarSub()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_schedule').dialog('close')">取消</a>
		</div>
		<div id="tab-toolbar_schedule">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newSchoolCalendarSub()">添加活动</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cut'"  onclick="removeSchoolCalendarSub()">删除活动</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editSchoolCalendarSub()">修改活动</a>
					</td>
			</tr>
		</table>
						
		</div>
</div>

</body>
</html>