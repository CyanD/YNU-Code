<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>云大故事维护</title>
    
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
var editor_add;
function NewsGrid(tableId,url,addDlgId,addFormId,uploadDivId){

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
													href:'publicservice/ynuStory!showDetail?id='+row.id,
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
				frozenColumns:[[
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true},
					{field:'headpath',title:'头像',width:80,align:'center',rowspan:2,formatter:function(value,row,index){
					    if(value=="")
					        return "";
					    else
							return '<img src="download/download!showImage?path=' + value + '" >';
					}}
					]],
				columns:[[
					//{field:'ck',checkbox:true},
					{field:'title',title:'主题',width:400,align:'center',rowspan:2,sortable:true},
					{field:'happenTime',title:'发生时间',width:200,align:'center',rowspan:2,sortable:true},
					{field:'createTime',title:'创建/修改时间',width:200,align:'center',rowspan:2,sortable:true},
					{field:'publisher',title:'创建/修改人',width:180,align:'center',rowspan:2,sortable:true}
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
		    $(uploadDivId).empty();
			$(uploadDivId).append('<input type="file" name="Filedata" id="file_upload" />');
			uploadImg.initUpload('上传头像');
			
			$(addDlgId).dialog('open').dialog('setTitle','添加云大故事信息');
			$(addFormId).form('clear');
			$(addFormId).form('load',{sex:'男'});
			updateUrl=url+'!add';
			
			if (!editor_add) {
				this.createEditor();
			} else {
    			editor_add.html('');
			}  
		}
		function unselectAll(){
			 $(tableId).datagrid("unselectAll");
		}
		this.edit=edit;
		function edit(){
			var row = getSelectedRow();
			if (row){
			    $(uploadDivId).empty();
	 			$(uploadDivId).append('<img src="download/download!showImage?path=' + row.headpath + '" >');
	 			$(uploadDivId).append('<input type="file" name="Filedata" id="file_upload" />');
	 			$(uploadDivId).append('<input type="hidden" name="headpath" value="'+row.headpath+'" />');
				uploadImg.initUpload('更换头像');
				
				$(addDlgId).dialog('open').dialog('setTitle','修改云大故事信息');
				$(addFormId).form('clear');
				$(addFormId).form('load',row);
				var rc = row.content.replace(new RegExp("src='/ynumobile",'gm'), "src='/umobile");
				$(addFormId).form('load',{content:rc});
				updateUrl=url+'!edit?id='+row.id;
				
				if (!editor_add) {
					createEditor();
				}else{
    				editor_add.html('');
    				editor_add.html( row.content.replace(new RegExp("src='/ynumobile",'gm'), "src='/umobile") );
				}	
			}else{
				$.messager.alert('提醒','请选择需要修改的云大故事!','warning');
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
					$.messager.confirm('提示：','你确定要删除主题为：'+titles.join('、')+'的云大故事吗？',function(r){
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
		function search(value,name){
			
			var keyValue=name+':'+value;
			//alert(keyValue);
			/*$.post(url+'!search',{param:keyValue},function(result){alert("aa="+result.success);
							if (result.success){alert("11");
								unselectAll();//清空选择器里面的row，以免修改混乱
								reload();	
								$.messager.show({	
									title: '成功',
									msg: result.msg,
									showType:'show',
									timeout:10000
								});
							} else {alert("22");
								$.messager.alert( '失败',result.msg,'error');	
							}
						},'json');*/
						
				unselectAll();
				$(tableId).datagrid({
						url:url+'!search',  
						columns:defaultColumns,
	  					queryParams:{param:keyValue}
				 });
			
		}
		
}//newsGrid
		//Up
function UploadFile(fileId,url,fileTypeExts){
			this.initUpload=function(buttonText){
					$(fileId).uploadify( {
						'auto' : true,
						'multi': false,
						//'simUploadLimit':10,
						'fileTypeExts' : fileTypeExts,
						'fileObjName' : 'Filedata',
						'swf' : 'resources/js/uploadify/uploadify.swf',
						'uploader' : url+'jsessionid=<%=session.getId()%>',
						'cancelImage' : 'resources/js/uploadify/uploadify-cancel.png',  
						//'uploadLimit' :5,
						'rollover':true,
						//'displayData':'speed',
						'buttonText' : buttonText,
						'removeCompleted':false,
						//'preventCaching':false,
						'onUploadStart' : function(file) {
							/* $("#file_upload").uploadify("settings", 'formData', {
							}); */
						},
						 'onQueueComplete' : function(queueData) { 
						 	
				        }, 
						'onUploadSuccess' : function(file, data, response){ 
						 		var result = eval('(' + data + ')');
						 		if(result.success){
						 			 $('#Div_uploadImg').empty();
						 			 $('#Div_uploadImg').append('<img src="download/download!showImage?path=' + result.msg + '" >');
						 			 $('#Div_uploadImg').append('<input type="file" name="Filedata" id="file_upload" />');
						 			 $('#Div_uploadImg').append('<input type="hidden" name="headpath" value="'+result.msg+'" />');
						 			 
									uploadImg.initUpload('更换头像');
						 		}else{
						 		
						 		}
						  }
					});
			}//this.initupload
		this.saveDownNew=saveDownNew;
		function saveDownNew(){
					$(fileId).uploadify('upload','*');
		}
		this.clearList=function(){
			$(fileId).uploadify('cancel', '*');
		}
		function stopUpload(){
			$(fileId).uploadify('stop');
		}
		function destroyUpload(){
			$(fileId).uploadify('destroy');
		}
}
	</script>
</head>
<body >

		<table id="table_up"></table>
		<div id="dlg_up_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="true" collapsible="true"  closed="true" fit="true" modal="true" buttons="#dlg-buttons_up"><br>
					 <div class="demo-info" style="margin-bottom:10px;">
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写云大故事信息</h2></div>
					</div> 
					<form id="fm_up_add" method="post" novalidate>
					<input id="contentEditor" type = "hidden" name ="contentEditorName"/>
						<table align ="center"  cellspacing="10" >
								<tr>
									<td>主题：</td>
									<td><input name = "title" class="easyui-validatebox" data-options="required:true,validType:'length[1,80]'" style="width:500px;border:1px solid #ccc;padding:2px;"></td>
								  
								</tr>
								

								<tr>
									<td>发生时间：</td>
									<td><input name = "happenTime" class="easyui-validatebox" data-options="required:false" style="width:500px;border:1px solid #ccc;padding:2px;"></td>
								    <td class="dv-label"></td>
								    <td>
										<div id = "Div_uploadImg" align="center"></div>
									</td>
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
	
	
		<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_add'" onclick="newsGrid.add()">添加云大故事</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="newsGrid.edit()">修改云大故事</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_remove'"  onclick="newsGrid.remove()">删除云大故事</a>
					    	
					</td>
					<!-- <td align=right><input class="easyui-searchbox" data-options="prompt:'Please Input Value',menu:'#mm',
			searcher:function(value,name){alert(value+':'+name)}" style="width:300px"></input></td>  -->
			<td align=right><input class="easyui-searchbox" data-options="searcher:newsGrid.search,prompt:'Please Input Value',menu:'#mm'" style="width:300px"></input></td>
				</tr>
			</table>			
		</div>
		<div id="mm" style="width:120px">
			<div data-options="name:'all',iconCls:'icon-ok'">所有字段</div>
			<div data-options="name:'title'">主题</div>
			<div data-options="name:'happenTime'">发生时间</div>
			<div data-options="name:'createTime'">创建时间</div>
			<div data-options="name:'publisher'">创建人</div>			
	    </div>
		
		<div id="mm_up" class="easyui-menu" style="width:120px;">
			<div data-options="iconCls:'icon-reload'" onclick="newsGrid.reload();">刷新列表</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-application_remove'"  onclick="newsGrid.remove()">删除云大故事</div>
			<div data-options="iconCls:'icon-application_edit'" onclick="newsGrid.edit()">修改云大故事</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
</body>
<script type="text/javascript">
var uploadImg = new UploadFile('#file_upload','publicservice/ynuStory!addHead?','*.jpg;*.png');
var newsGrid = new NewsGrid('#table_up','publicservice/ynuStory','#dlg_up_add','#fm_up_add','#Div_uploadImg');
newsGrid.createGrid();
//   addressbook/addressbookAlumni
			
</script>
</html>