<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程维护</title>
    
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
	<script type="text/javascript" src="resources/js/myjs/mytree.js"></script>
	<script type="text/javascript" >
	var courseCategoryTree;
	var newsGrid;
	var uploadImg;
	var imgGri;
	var courseId;
	function refreshAll(){
		 imgGrid.reload();
		 newsGrid.reload();
	}
	function fixWidth(percent){  
			return document.body.clientWidth*percent;  
	}  
		//Up
function CourseCategoryTree(url,targets){
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
				  });
			  },'json');		
		  } 
  } 
function NewsGrid(tableId,url,dlgId,formId){
		this.manageImg=manageImg;
		function manageImg(){
				var row = getSelectedRow();
				var pid = imgGrid.getPid();
				if(pid!=row.id){
						imgGrid.setPRow(row);
						imgGrid.activeGrid();
						$('#layout_down').panel({
							title:"相关视频维护（所属课程：《"+row.title+"》！）"
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
													href:'course/courseCourse!showDetail?id='+row.id,
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
				loadFilter:function(data){
						$(data.rows).each(function(){
							this['categoryId']=this.courseCategory.id;
							this['categoryName']=this.courseCategory.name;
						});
						return data;
				},
				columns:[[
	                {title:'编号',field:'id',rowspan:2,width:20,sortable:true,hidden:true},
					{field:'categoryId',title:'类别编号',width:100,align:'center',rowspan:2,hidden:true},
					{field:'title',title:'标题',width:300,align:'center',rowspan:2},
					{title:'顺序',field:'orders',rowspan:2,align:'center',width:50,sortable:true},
					{field:'categoryName',title:'类别',dth:200,align:'center',rowspan:2},
					{field:'visitors',title:'浏览量',width:50,align:'center',rowspan:2,sortable:true},
	                {field:'lecturer',title:'主讲',width:100,align:'center',rowspan:2,sortable:true,hidden:true},
	                {field:'status',title:'状态',width:50,align:'center',rowspan:2,sortable:true},
					{field:'createTime',title:'创建/修改时间',width:150,align:'center',rowspan:2,sortable:true},
					{field:'publisher',title:'创建/修改人',width:50,align:'center',rowspan:2,sortable:true,hidden:true},
					{field:'description',title:'描述',width:50,align:'center',rowspan:2,sortable:true,hidden:true}
				]],
				pagination:true,
				rownumbers:false,
				singleSelect:false,
				onClickRow:function(index,row){
											//unselectAll();
											//$(tableId).datagrid("selectRow",index);
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
			$(dlgId).dialog('open').dialog('setTitle','添加课程');
			$(formId).form('clear');
			$(formId).form('load',{});
			updateUrl=url+'!add';
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
			var row= getSelectedRow();
			if (row){
					$(dlgId).dialog('open').dialog('setTitle','修改课程');
					$(formId).form('clear');
					$(formId).form('load',row);
					updateUrl=url+'!edit?id='+row.id;
			}else{
				$.messager.alert('提醒','请选择需要修改的课程!','warning');
			}
		}
		this.save=save;
		function save(){
			$(formId).form('submit',{
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
					$(dlgId).dialog('close');
					reload();
					parent.showMsg(result);
				}
			});
		}
		this.editCover=editCover;
		function editCover(){
			var row = getSelectedRow();
			if (row){
				$('#Div_cover').empty();
	 			$('#Div_cover').append('<img src="download/download!showImage?path=' + row.coverPath + '" >');
	 			$('#Div_cover').append('<input type="file" name="Filedata" id="file_cover" />');
				uploadCover.initUpload('更换封面');
				$('#dlg_up_cover').dialog('open').dialog('setTitle','修改记录');
				courseId=row.id;
			}else{
				$.messager.alert('提醒','请选择需要修改的记录!','warning');
			}
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
				$.messager.confirm('提示：','此操作会同时删除与之相关的所有视频，你确定要删除标题为：'+titles.join('、')+'的课程吗？',function(r){
					if (r){
						$.post(url+'!remove',{ids:ids},function(result){
							if (result.success){
								unselectAll();//清空选择器里面的row，以免修改混乱
								if(needInit){
										imgGrid.initGridDown();//重新设置Down，以免添加已经被删的数据
										$('#layout_down').panel({
												title:"相关视频维护（暂未选择课程！）"
										});
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
				$.messager.alert('提醒','请选择需要删除的课程!','warning');
			}
		}//remove
		this.activateStatus=activateStatus;
		function activateStatus(){
			var rows = $(tableId).datagrid('getSelections');
			if (rows.length>0){
				var msg='';
				var all =[];
				var idss = [];
				var titles=[];
				var isAct = [];
				for(var i=0;i<rows.length;i++){
					if('已发布'==rows[i].status){
						isAct.push(rows[i].title);
					}else{
						idss.push(rows[i].id);
						titles.push(rows[i].title);
					}
						all.push(rows[i].title);
				}
				if(idss.length<1){
						$.messager.alert('提醒','课程：'+all.join(',')+'被选中，并且已经发布，无需重复发布！','warning');
				}else{
						if(isAct.length>0){
								msg = '课程：'+all.join(',')+'被选中，其中'+isAct.join(',')+'已经发布，';
						}
						msg+='你确定要发布课程：'+titles.join(',')+'吗？';
						var ids = idss.join(',');
						$.messager.confirm('提示：',msg,function(r){
							if (r){
								$.post(url+'!editStatus',{ids:ids,status:'已发布'},function(result){
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
				$.messager.alert('提醒','请选择需要发布的课程!','warning');
			}
		}
		this.revokeStatus=revokeStatus;
		function revokeStatus(){
				var rows = $(tableId).datagrid('getSelections');
				if (rows.length>0){
					var msg='';
					var all =[];
					var idss = [];
					var titles=[];
					var isAct = [];
					for(var i=0;i<rows.length;i++){
						if('未发布'==rows[i].status){
							isAct.push(rows[i].title);
						}else{
							idss.push(rows[i].id);
							titles.push(rows[i].title);
						}
							all.push(rows[i].title);
					}
					if(idss.length<1){
							$.messager.alert('提醒','课程：'+all.join(',')+'被选中，并且全部处于未发布状态，无需撤销！','warning');
					}else{
							if(isAct.length>0){
									msg = '课程：'+all.join(',')+'被选中，其课程：'+isAct.join(',')+'处于未发布状态，';
							}
							msg+='你确定要撤销课程：'+titles.join(',')+'吗？';
							var ids = idss.join(',');
							$.messager.confirm('提示：',msg,function(r){
								if (r){
									$.post(url+'!editStatus',{ids:ids,status:'未发布'},function(result){
											reload();	
											parent.showMsg(result);
									},'json');
								}
							});//confirm
					}
				}else{
					$.messager.alert('提醒','请选择需要撤销的课程!','warning');
				}
		}
}//newsGrid
		//Up
		//Down
function ImgGrid(tableId,url,uploadDivId,addDlgId,editDlgId,editFromId){
		var pRow=null;
		this.setPRow=setPRow;
		function setPRow(newsRow){
			pRow=newsRow;
		}
		this.getPid=getPid;
		function getPid(){
			if(pRow!=null){
				return pRow.id;
			}else{
				return 0;
			}
		}
		this.activeGrid=activeGrid;
		function activeGrid(){
						unselectAll();
						$(tableId).datagrid({
								url:url+'!show',  
			  					queryParams:{pid:pRow.id}
						 });
		}
		this.initGridDown=initGridDown;
		function initGridDown(){
			$(tableId).datagrid({
													url:null
		                    				});
          						pRow=null;
         	 $(tableId).datagrid('loadData',{"rows":[],"total":0});
		}
		this.reload=reload;
		function reload(){
			$(tableId).datagrid('reload');
		}
		this.getSelectedRow=getSelectedRow;
		function getSelectedRow(){
				return $(tableId).datagrid('getSelected')
		}
		this.createGrid=function(){
			$(tableId).datagrid({
				iconCls:'icon-save',
				nowrap: true,
				fitColumns:true,
				autoRowHeight:false,
				striped: true,
				fit: true,
				sortName: 'orders',
				sortOrder: 'asc',
				remoteSort: true,
				idField:'id',
				columns:[[
					{field:'name',title:'名称',width:150,rowspan:2,sortable:true},
					{field:'orders',title:'顺序',width:150,rowspan:2,sortable:true},
					{field:'path',title:'路径',width:150,rowspan:2,sortable:true,formatter:function(value,row,index){
							return '<a href="download/download!downloadFile?path='+row.path+'&name='+row.name+'" >'+row.name+'</a>';
					}}
				]],
				pagination:true,
				pageSize:10,
				pageList:[10],
				rownumbers:false,
				onClickRow:function(index,row){
											//$(tableId).datagrid("unselectRow",index);
											
										},
				onDblClickRow:function(index,row){
											unselectAll();
											$(tableId).datagrid("selectRow",index);
											edit();//用this后会报错，可以是因为jquery封装了this
										},
				onRowContextMenu: function(e, index, row){
											e.preventDefault();
											unselectAll();
											$(tableId).datagrid("selectRow",index);
											$('#mm_down').menu('show', {
												left: e.pageX,
												top: e.pageY
											});
										},
				toolbar:'#tab-toolbar_down'
			});//drid
			initGridDown();
	}//createGrid$
		this.add=add;
		function add(){
			if(pRow!=null){
				//clearList();
				$(uploadDivId).empty();
				$(uploadDivId).append('<p><span class="c-label">视频将被添加至课程：《'+pRow.title+'》！</span> </p>');
				$(uploadDivId).append('<input type="file" name="Filedata" id="file_upload" />');
				uploadImg.initUpload(pRow.id);
				$(addDlgId).dialog('open').dialog('setTitle','添加视频');
			}else{
				$.messager.alert('提醒','请先选择课程！','warning');
			}
		}
		this.closeAddDlg=closeAddDlg;
		function closeAddDlg(){
			$(addDlgId).dialog('close')
		}
		this.closeEditDlg=closeEditDlg;
		function closeEditDlg(){
			$(editDlgId).dialog('close');
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
					$(editDlgId).dialog('open').dialog('setTitle','设置顺序');
					$(editFromId).form('clear');
					$(editFromId).form('load',row);
			}else{
				$.messager.alert('提醒','请先选择视频!','warning');
			}
		}
		this.saveEdit=saveEdit;
		function saveEdit(){
			$(editFromId).form('submit',{
				url: url+'!edit',
				onSubmit: function(){
					if($(this).form('validate')){
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
						closeEditDlg();
						refreshAll();//不是本类
					parent.showMsg(result);
				}
			});
		}
		this.editIsCorver=editIsCorver;
		function editIsCorver(){
			var row = getSelectedRow();
			if (row){
					if('是' !=row.isCover){
						$.post(url+'!editIsCorver',{id:row.id},function(result){
							refreshAll();//不是本类
							parent.showMsg(result);
						},'json');
					}else{
						$.messager.alert('提醒','该视频已经是封面，无需重复设置！','warning');
					}
			}else{
				$.messager.alert('提醒','请先选择视频!','warning');
			}
			
		}
		this.remove=remove;
		function remove(){
			
			var rows = $(tableId).datagrid('getSelections');
			if (rows.length>0){
				var idss = [];
				var msg ="";
				var names=[];
				for(var i=0;i<rows.length;i++){
					idss.push(rows[i].id);
					idss.push(rows[i].name)
				}
				var ids = idss.join(',');
				$.messager.confirm('提示：','<div>你确定要删除视频：<br>'+names.join('、')+'<br>吗?</div><br>'+msg,function(r){
					if (r){
						$.post(url+'!remove',{ids:ids},function(result){
								unselectAll();
								refreshAll();//不是本类
								parent.showMsg(result);
						},'json');
					}
				});//confirm
			}else{
				$.messager.alert('提醒','请选择需要删除的视频!','warning');
			}
		}//removeDown
}//GridImgDown

	//upload
function UploadFile(fileId,url,buttonText,fileTypeExts){
			var successNumber = 0;
			var errorNumber = 0;
			var errorName = "";
			var errorInfo ="";
			this.initUpload=function(pid){
					initAfterEdit();
					$(fileId).uploadify( {
						'auto' : false,
						'multi': true,
						//'simUploadLimit':10,
						'fileTypeExts' : fileTypeExts,
						'fileObjName' : 'Filedata',
						'swf' : 'resources/js/uploadify/uploadify.swf',
						'uploader' : url+'jsessionid=<%=session.getId()%>',
						'cancelImage' : 'resources/js/uploadify/uploadify-cancel.png',  
						//'uploadLimit' :5,
						'rollover':true,
						'displayData':'speed',
						'buttonText' : buttonText,
						'removeCompleted':false,
						//'preventCaching':false,
						'onUploadStart' : function(file) {
							$("#file_upload").uploadify("settings", 'formData', {
								'pid' :pid
							});
						},
						 'onQueueComplete' : function(queueData) { 
						 			var msg = '本次上传，成功'+successNumber+'个，失败'+errorNumber+'个！';
						 			if(errorNumber>0){
						 				msg+='<br>失败文件：<br>'+errorName+'<br>失败信息：<br>'+errorInfo;
						 				$.messager.alert('上传信息',msg,'info');
						 			}else{
						 					$.messager.show({	
														title: '上传完毕',
														msg: msg,
														showType:'show',
														timeout:15000
													});
						 			}
				            		initAfterEdit();
				            		refreshAll();//不是本类中的
				        }, 
						'onUploadSuccess' : function(file, data, response){ 
						 		var result = eval('(' + data + ')');
						 				//alert(response.success); 
											if (result.success){
													successNumber++;
													$.messager.show({	
														title: '成功',
														msg: "视频："+file.name+"上传成功！",
														showType:'show',
														timeout:10000
													});
												} else {
													errorNumber++;
													errorName+=file.name+'，';
													errorInfo=result.msg;
												}
						  }/*  ,
						  'onUploadError' : function(file, errorCode, errorMsg, errorString) { 
						  				$.messager.alert( '错误提示','文件'+file.name+'上传失败，请认真检查所所传文件是否符合要求后再上传！','error');
			            				
			       			 }  */
						  
					});
			}//this.initupload
		function initAfterEdit(){
			 successNumber = 0;
			 errorNumber = 0;
			 errorName = "";
			 errorInfo ="";
		}
		this.saveDownNew=saveDownNew;
		function saveDownNew(){
					$(fileId).uploadify('upload','*');
		}
		this.clearList=function(){
			successNumber = 0;
			errorNumber = 0;
			errorName = "";
			errorInfo ="";
			$(fileId).uploadify('cancel', '*');
		}
		function stopUpload(){
			$(fileId).uploadify('stop');
		}
		function destroyUpload(){
			$(fileId).uploadify('destroy');
		}
}//uploadFile

function UploadCover(fileId,url,fileTypeExts){
			this.initUpload=function(buttontext){
					$(fileId).uploadify( {
						'auto' : true,
						'multi': false,
						//'simUploadLimit':10,
						'fileTypeExts' : fileTypeExts,
						'fileObjName' : 'Filedata',
						'swf' : 'resources/js/uploadify/uploadify.swf?var='+(new Date()).getTime(),
						'uploader' : url+'jsessionid=<%=session.getId()%>',
						'cancelImage' : 'resources/js/uploadify/uploadify-cancel.png',  
						//'uploadLimit' :5,
						'rollover':true,
						//'displayData':'speed',
						'buttonText' : buttontext,
						'removeCompleted':false,
						//'preventCaching':false,
						'onUploadStart' : function(file) {
							$(fileId).uploadify("settings", 'formData', {
								'id' :courseId
							});
						},
						'onUploadSuccess' : function(file, data, response){ 
						 		var result = eval('(' + data + ')');
						 		if(result.success){
						 			 $('#Div_cover').empty();
						 			 $('#Div_cover').append('<img src="download/download!showImage?path=' + result.msg + '" >');
						 			 $('#Div_cover').append('<input type="file" name="Filedata" id="file_cover" />');
						 			 $('#Div_cover').append('<input type="hidden" name="coverPath" value="'+result.msg+'" />');
									uploadCover.initUpload('更换封面');
						 		}else{
						 			parent.showMsg(result);
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
}//uploadFile
	</script>
</head>
<body class="easyui-layout"  id="body" style="display:none;">
<div id="layout_down" data-options="region:'center',iconCls:'icon-picture_key'" title="相关视频维护（暂未选择课程！）" style="height:400px;width:400px;overflow:hidden;">
		<table id="table_down"></table>
		<div id="dlg_down_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  collapsible="true"  modal ="true"   closed="true" buttons="#dlg-buttons__down_add""><br>
				<div id = "Div_uploadImg" align="center"></div>
				<div id="dlg-buttons__down_add">
					<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-'+divId_file_up" onclick="uploadImg.saveDownNew()">开始上传</a>
					<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-chacha" onclick="uploadImg.clearList()">清空列表</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tuichu" onclick="imgGrid.closeAddDlg()">退出</a>
				</div>
		</div>
		<div id="dlg_down_edit" class="easyui-dialog" style="width:500px;height:430px;padding:10px 20px"
				maximizable = "true"  modal="true" collapsible="true"  fit="false" closed="true" buttons="#dlg-buttons_down">
							<form id="fm_down_edit" method="post" novalidate>
							<table align ="center"  >
								<tr>
									<td>
										<input type ="hidden"  name = "id">
										<input type ="hidden"  name = "pid">
										<input  name = "orders" class="easyui-numberbox" data-options="min:0,max:200,required:true"/>
									</td>
								</tr>
								</table>
						</form>
				</div>
		
				<div id="dlg-buttons_down">
					<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="imgGrid.saveEdit()">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="imgGrid.closeEditDlg()">取消</a>
				</div>
		<div id="tab-toolbar_down">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-picture_add'" onclick="imgGrid.add()">添加视频</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-picture_delete'"  onclick="imgGrid.remove()">删除视频</a>
					</td>
					<td 	style="align:left">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true" onclick="imgGrid.selectAll()">全选</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true"  onclick="imgGrid.unselectAll()">反选</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="mm_down" class="easyui-menu" style="width:120px;">
			<div data-options="iconCls:'icon-reload'" onclick="imgGrid.reload();">刷新列表</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-picture_delete'"  onclick="imgGrid.remove()">删除视频</div>
			<div data-options="iconCls:'icon-picture_edit'" onclick="imgGrid.edit()">设置顺序</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
</div>
<div data-options="region:'west',split:true,iconCls:'icon'" title="课程维护" style="width:700px;height:300px;overflow:hidden;">
		<table id="table_up"></table>
		<div id="dlg_up_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="false" collapsible="true"   fit="true" modal ="true"   closed="true" buttons="#dlg-buttons_up"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写课程信息</h2></div>
					</div> 
					<form id="fm_up_add" method="post" novalidate>
						<input id="newsAlbumCategoryNameAdd" type = "hidden" name ="categoryName"/>
						<table align ="center"  >
								<tr>
									<td class="dv-label">标题：</td>
									<td><input name = "title" class="easyui-validatebox" data-options="required:true,validType:'length[1,200]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">课程类别：</td>
									<td><input id="courseCategoryTree" name = "categoryId" class="easyui-combotree" value="0" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">主讲：</td>
									<td><input name = "lecturer" class="easyui-validatebox" data-options="required:true,validType:'length[1,100]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">顺序：</td>
									<td>	<input  name = "orders" class="easyui-numberspinner" data-options="required:true"  style="width:400px;border:0px solid #ccc;padding:2px;"/></td>
								</tr>
								<tr>
									<td class="dv-label">简介：</td>
									<td><textarea name = "content" class="easyui-validatebox" data-options="required:true,validType:'length[1,10000]'" style="height:200px;width:400px;border:1px solid #ccc;	padding:2px;"></textarea></td>
								</tr>
								
						</table>
				</form>
			<div id="dlg-buttons_up">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="newsGrid.save()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_add').dialog('close')">取消</a>
			</div>
		</div>
		<div id="dlg_up_cover" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
					maximizable = "true"  draggable="true" fit="true" modal="true" collapsible="true"  closed="true" buttons="#dlg-buttons_cover"><br>
						 <div class="demo-info" style="margin-bottom:10px;" >
							<div class="demo-tip icon-tip"></div>
							<div align = "center"><h2>封面设置</h2></div>
						</div> 
						<form id="fm_up_cover" method="post" novalidate>
							<table align ="center"  cellspacing="20" >
									<tr>
										<td class="dv-label">
										</td>
										<td rowspan="2">
											<div id = "Div_cover" align="center"></div>
										</td>
									</tr>
							</table>
					</form>
				<div id="dlg-buttons_cover">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tuichu" onclick="javascript:$('#dlg_up_cover').dialog('close')">退出</a>
				</div>
		</div>
		<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="newsGrid.add()">添加课程</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除课程</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fabu2'"  onclick="newsGrid.activateStatus()">发布课程</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fabu'" onclick="newsGrid.revokeStatus()">撤销发布</a>
					</td>
					<td 	style="align:left">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true" onclick="newsGrid.selectAll()">全选</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true"  onclick="newsGrid.unselectAll()">反选</a>
					</td>
				</tr>
			</table>			
		</div>
		
		<div id="mm_up" class="easyui-menu" style="width:120px;">
			<div data-options="iconCls:'icon-reload'" onclick="newsGrid.reload();">刷新列表</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-remove'"  onclick="newsGrid.remove()">删除课程</div>
			<div data-options="iconCls:'icon-edit'" onclick="newsGrid.edit()">修改课程</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-fabu2'"  onclick="newsGrid.activateStatus()">发布课程</div>
			<div data-options="iconCls:'icon-fabu'" onclick="newsGrid.revokeStatus()">撤销发布</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-picture_key'" onclick="newsGrid.editCover()">封面设置</div>
			<div data-options="iconCls:'icon-picture_key'" onclick="newsGrid.manageImg()">维护视频</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
		
		
	</div>	
	
	
</body>
<script type="text/javascript">
		window.onload=function (){
			$('#body').show();
			$.parser.parse();
			uploadImg = new UploadFile('#file_upload','course/courseVideo!add?','上传视频','*;');
			uploadCover = new UploadCover('#file_cover','course/courseCourse!editCover?','*.jpg;');
			imgGrid = new ImgGrid('#table_down','course/courseVideo','#Div_uploadImg','#dlg_down_add','#dlg_down_edit','#fm_down_edit');
			newsGrid = new NewsGrid('#table_up','course/courseCourse','#dlg_up_add','#fm_up_add');
			UploadCover
			imgGrid.createGrid();
			newsGrid.createGrid();
			courseCategoryTree=new CourseCategoryTree('public/public!showCourseCategoryTree',['#courseCategoryTree']);
			courseCategoryTree.loadTreeData();
		}
</script>
</html>