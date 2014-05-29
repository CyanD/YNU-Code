<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生园地维护</title>
    
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
  <script type="text/javascript" src="resources/js/easyui/datagrid-detailview.js"></script>
  	<script type="text/javascript" src="resources/js/json/json2.js"></script>
  	
  	<script charset="utf-8" src="<%=basePath%>resources/js/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="resources/js/kindeditor/zh_CN.js"></script>
		
	<script type="text/javascript" >
	var newsGrid;
    var editor_add;
	
    function TeacherHomeTree(url,targets){
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
                      if(i==2){
                      	 treeData.push(new node(0,'显示全部', 'icon-xiaolian',[]));
                      }
					  $(n).combotree('loadData',treeData);
				  });
			  },'json');		
		  } 
		  
		  function node(id,text,iconCls,children){
	  			this.id=id;
	  			this.text=text;
	  			this.iconCls=iconCls;
	  			this.children=children;
		  }
	} 
  
	function NewsGrid(tableId,url,addDlgId,addFormId){
	    var defaultColumns=[];
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
			    view: detailview,
				detailFormatter:function(index,row){
						return '<div id="ddv-' + index + '" style="padding:5px 0"></div>';
				},
				onExpandRow: function(index,row){
												unselectAll();
												$(tableId).datagrid("selectRow",index);
												$('#ddv-'+index).panel({
													border:false,
													cache:false,
													href:'publicservice/studentField!showDetail?id='+row.id,
													onLoad:function(){
														$(tableId).datagrid('fixDetailRowHeight',index);
													}
												});
												$(tableId).datagrid('fixDetailRowHeight',index);
											},
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
				onBeforeLoad:function(){
				    unselectAll();
				},
				loadFilter:function(data){
						$(data.rows).each(function(){
							this['kindId']=this.studentFieldKindTree.id;
							this['kindName']=this.studentFieldKindTree.name;
						});
						return data;
				},
				frozenColumns:[[
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true}
					]],
				columns:[[
				    {title:'标题',field:'title',width:300,align:'center',rowspan:2,sortable:true},
	                {title:'类别',field:'kindName',rowspan:2,align:'center',width:200},
	                {title:'创建/修改时间',field:'createTime',width:250,align:'center',rowspan:2,sortable:true},
					{title:'创建/修改人',field:'publisher',width:270,align:'center',rowspan:2,sortable:true}
				]],
				pagination:true,
				rownumbers:false,
				singleSelect:false,
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
			defaultColumns=$(tableId).datagrid('options').columns;
	}
	
	    this.createEditor=createEditor;
	    function createEditor(){
	    	$.getScript('resources/js/kindeditor/kindeditor-min.js', function() {
					var options = {
            			//cssPath : 'kindeditor/plugins/code/prettify.css',
            			uploadJson : '<%=basePath%>ke/upload_json.jsp',
            			fileManagerJson : '<%=basePath%>ke/file_manager_json.jsp',
            			allowFileManager : true,
           				resizeType : 0,
                    	afterCreate : function() {
               				this.sync();
           	       		},
                   		afterBlur : function() {
                        	this.sync();
                   		}
               		 };
					 editor_add = KindEditor.create('textarea[name="content"]',options);
					 editor_add.sync();
				});
	    }
	    
		var updateUrl;
		this.add=add;
		function add(){
			$(addDlgId).dialog('open').dialog('setTitle','添加学生园地');
			$(addFormId).form('clear');
			$(addFormId).form('load',{});
			updateUrl=url+'!add';	
			
			if (!editor_add) {
				this.createEditor();
			} else {
    			editor_add.html('');
			}    
		}
		this.unselectAll=unselectAll;
		function unselectAll(){
			 $(tableId).datagrid("unselectAll");
		}
		this.selectAll=selectAll;
		function selectAll(){
			 $(tableId).datagrid("selectAll");
		}
		this.edit=edit;
		function edit(){
			var row = getSelectedRow();
			if (row){
				$(addFormId).form('clear');
				$(addFormId).form('load',row);
				var rc = row.content.replace(new RegExp("src='/ynumobile",'gm'), "src='/umobile");
				$(addFormId).form('load',{content:rc});
				$(addDlgId).dialog('open').dialog('setTitle','修改学生园地');
				updateUrl=url+'!edit?id='+row.id;

				if (!editor_add) {
					createEditor();
				}else{
    				editor_add.html('');
    				editor_add.html( row.content.replace(new RegExp("src='/ynumobile",'gm'), "src='/umobile") );
				}	
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
						if(editor_add.isEmpty()){alert("提示：请输入内容！");
					    	return false;
					    }else{//alert(editor_add.html());
					        $('#contentEditor').val(editor_add.html());
							return true;
						}
					
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
			
				var rows = $(tableId).datagrid('getSelections');
				if (rows.length>0){
					var idss = [];
					var titles=[];
					for(var i=0;i<rows.length;i++){
						idss.push(rows[i].id);
						titles.push(rows[i].title);
					}
					var ids = idss.join('&');
					$.messager.confirm('提示：','你确定要删除标题为：'+titles.join('、')+'的学生园地吗？',function(r){
					if (r){
						$.post(url+'!remove',{ids:ids},function(result){
							if (result.success){
								unselectAll();//清空选择器里面的row，以免修改混乱
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
					$.messager.alert('提醒','请选择需要删除的记录!','warning');
				}
			}//remove
			this.search=search;
			function search(kid){
			$('#table_up').datagrid({
						url:'publicservice/studentField'+'!search',  
						columns:defaultColumns,
	  					queryParams:{kindId:kid}
				 });
			}
			this.editKind=editKind;
			function editKind(){	
				var rows = $(tableId).datagrid('getSelections');
				if (rows.length>0){
				
					$('#dlg_up_editKind').dialog('open').dialog('setTitle','修改学生园地类别');
			    	$('#dlg_up_editKind').form('clear');
					$('#dlg_up_editKind').form('load',{});
					//updateUrl=url+'!editKindConfirm?id='+row.id;	
			
					var ids = [];
					var titles=[];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
						titles.push(rows[i].title);
					}
					$('#show_titles').val(titles.join('、'));
					$('#e_ids').val(ids.join('&'));					
				}else{
					$.messager.alert('提醒','请选择需要修改的记录!','warning');
				}
			}//editKind
		
		this.editKindConfirm=editKindConfirm;
		function editKindConfirm(){
		$.messager.confirm('提示：','你确定要修改标题为：'+($('#show_titles').val())+'的学生园地类别吗？',function(r){
			if (r){
						$('#fm_up_editKind').form('submit',{
				url:'publicservice/studentField!editKindConfirm',
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					$('#dlg_up_editKind').dialog('close');
						reload();
					parent.showMsg(result);
				}
			   });
			}
		});	
			
		}
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
						<div align = "center"><h2>请填写学生园地信息</h2></div>
					</div> 
					<form id="fm_up_add" method="post" novalidate>
					<input id="newsAlbumCategoryNameAdd" type = "hidden" name ="kindName"/>
					<input id="contentEditor" type = "hidden" name ="contentEditorName"/>
						<table align ="center"  cellspacing="10" >
								<tr>
									<td class="dv-label">标题：</td>
									<td ><input name = "title" class="easyui-validatebox" data-options="required:true" style="width:380px;border:1px solid #ccc;padding:2px;"></td>
									<td class="dv-label">类别：</td>
									<td><input id="kindTree" name = "kindId" class="easyui-combotree" value="0" data-options="required:true" style="width:350px;border:1px solid #ccc;padding:2px;"></td>							
								</tr>
								<tr>
									<td class="dv-label">内容：</td>
									<td colspan="3"><textarea name = "content" class="easyui-validatebox" data-options="required:false" style="height:300px;width:800px;border:1px solid #ccc;padding:2px;"></textarea></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_up">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="newsGrid.saveAdd()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_add').dialog('close')">取消</a>
			</div>
	</div>
	
	
	
	<div id="dlg_up_editKind" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" modal="true" collapsible="true"  closed="true" buttons="#dlg-buttons_up_editKind"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请选择新的学生园地类别</h2></div>
					</div> 
					<form id="fm_up_editKind" method="post" novalidate>
					<input id="show_titles" type = "hidden" name ="show_titles"/>
					<input id="e_ids" type = "hidden" name ="ids"/>
					<input id="newsEditKind" type = "hidden" name ="kindName"/>
						<table align ="center"  cellspacing="10" >
								<tr>
									<td class="dv-label">类别：</td>
									<td><input id="editKindTree" name = "kindId" class="easyui-combotree" value="0" data-options="required:true" style="width:350px;border:1px solid #ccc;padding:2px;"></td>							
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_up_editKind">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="newsGrid.editKindConfirm()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_editKind').dialog('close')">取消</a>
			</div>
	</div>
	
	
	
	
		<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newsGrid.add()">添加学生园地</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'"  onclick="newsGrid.edit()">修改学生园地</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除学生园地</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fabu2'"  onclick="newsGrid.editKind()">修改学生园地类别</a>
					</td>
					<td><input id="search_kindTree" name = "search_kindId" class="easyui-combotree" data-options="required:false" value="请选择查询条件" style="width:300px;border:1px solid #ccc;padding:2px;"></td>			
					
					<td style="align:left">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true" onclick="newsGrid.selectAll()">全选</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true"  onclick="newsGrid.unselectAll()">反选</a>
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
			newsGrid = new NewsGrid('#table_up','publicservice/studentField','#dlg_up_add','#fm_up_add');
			newsGrid.createGrid();
			teacherHomeTree=new TeacherHomeTree('public/public!showStudentFieldKindTree',['#kindTree','#editKindTree','#search_kindTree']);
			teacherHomeTree.loadTreeData();
		
			$('#search_kindTree').combotree( {
    			onSelect : function(node) {
          			newsGrid.unselectAll();
                    newsGrid.search(node.id);					
   				}  
 			}); 		

		}
</script>
</html>