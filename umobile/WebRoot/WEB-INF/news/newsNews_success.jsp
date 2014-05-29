<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新闻维护</title>
    
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
	var newsNewsCategoryTree;
	var newsGrid;
	var uploadImg;
	var imgGri;
	function refreshAll(){
		 imgGrid.reload();
		 newsGrid.reload();
	}
	function fixWidth(percent){  
			return document.body.clientWidth*percent;  
	}  
		//Up
function NewsGrid(tableId,url,addDlgId,addFormId,editDlgId,editFromId){
		this.manageImg=manageImg;
		function manageImg(){
				var row = getSelectedRow();
				var pid = imgGrid.getPid();
				if(pid!=row.id){
						imgGrid.setPRow(row);
						imgGrid.activeGrid();
						$('#layout_down').panel({
							title:"相关图片维护（所属新闻：《"+row.title+"》！）"
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
													href:'news/newsNews!showDetail?id='+row.id,
													onLoad:function(){
														$(tableId).datagrid('fixDetailRowHeight',index);
													}
												});
												$(tableId).datagrid('fixDetailRowHeight',index);
											},
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
					{field:'title',title:'标题',width:100,align:'center',rowspan:2},
					{field:'visitors',title:'浏览量',width:20,align:'center',rowspan:2,sortable:true},
	                {field:'status',title:'状态',width:20,align:'center',rowspan:2,sortable:true},
					{field:'createTime',title:'发布时间',width:50,align:'center',rowspan:2,sortable:true}
				]],
				pagination:true,
				pageSize:20,
				pageList:[20,40,60,80,100],
				rownumbers:false,
				//singleSelect:true,
				onBeforeLoad:function(){
					unselectAll();
				},
				onClickRow:function(index,row){
											//unselectAll();
											//$(tableId).datagrid("selectRow",index);
										},
				onDblClickRow:function(index,row){
											unselectAll();
											$(tableId).datagrid('selectRow',index);
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
		this.add=add;
		function add(){
			$(addDlgId).dialog('open').dialog('setTitle','添加新闻');
			$(addFormId).form('clear');
			$(addFormId).form('load',{headline:'否'});
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
					$.post(url+'!initEdit',{id:row.id},function(result){
							if (result){
								$(editDlgId).dialog('open').dialog('setTitle','修改新闻');
								$(editFromId).form('clear');
								$(editFromId).form('load',result);
							} else {
								$.messager.alert( '失败','该记录不存在，请刷新后重试！','error');
							}
						},'json');
			}else{
				$.messager.alert('提醒','请选择需要修改的新闻!','warning');
			}
		}
		this.saveEdit=saveEdit;
		function saveEdit(){
			$(editFromId).form('submit',{
				url: url+'!edit',
				onSubmit: function(){
					if($(this).form('validate')){
						$('#newsNewsCategoryNameEdit').val($('#newsNewsCategoryTreeEdit').combotree('getText'));
						return true;
					}else{
						return false;
					}
				},
				success: function(result){
					var result = eval('(' + result + ')');
					$(editDlgId).dialog('close');
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
		this.saveAdd=saveAdd;
		function saveAdd(){
			$(addFormId).form('submit',{
				url:url+'!add',
				onSubmit: function(){
					if($(this).form('validate')){
						$('#newsNewsCategoryNameAdd').val($('#newsNewsCategoryTreeAdd').combotree('getText'));
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
				$.messager.confirm('提示：','此操作会同时删除与之相关的所有图片，你确定要删除标题为：'+titles.join('、')+'的新闻吗？',function(r){
					if (r){
						$.post(url+'!remove',{ids:ids},function(result){
							if (result.success){
								unselectAll();//清空选择器里面的row，以免修改混乱
								if(needInit){
										imgGrid.initGridDown();//重新设置Down，以免添加已经被删的数据
										$('#layout_down').panel({
												title:"相关图片维护（暂未选择新闻！）"
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
				$.messager.alert('提醒','请选择需要删除的新闻!','warning');
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
						$.messager.alert('提醒','新闻：'+all.join(',')+'被选中，并且已经发布，无需重复发布！','warning');
				}else{
						if(isAct.length>0){
								msg = '新闻：'+all.join(',')+'被选中，其中'+isAct.join(',')+'已经发布，';
						}
						msg+='你确定要发布新闻：'+titles.join(',')+'吗？';
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
				$.messager.alert('提醒','请选择需要发布的新闻!','warning');
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
							$.messager.alert('提醒','新闻：'+all.join(',')+'被选中，并且全部处于未发布状态，无需撤销！','warning');
					}else{
							if(isAct.length>0){
									msg = '新闻：'+all.join(',')+'被选中，其新闻：'+isAct.join(',')+'处于未发布状态，';
							}
							msg+='你确定要撤销新闻：'+titles.join(',')+'吗？';
							var ids = idss.join(',');
							$.messager.confirm('提示：',msg,function(r){
								if (r){
									$.post(url+'!editStatus',{ids:ids,status:'未发布'},function(result){
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
					$.messager.alert('提醒','请选择需要撤销的新闻!','warning');
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
				view: cardview_down,
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
					{field:'orders',title:'顺序',width:150,rowspan:2,sortable:true},
					{field:'isCover',title:'封面',width:100,rowspan:2,sortable:true,hidden:true},
					{field:'createTime',title:'上传时间',width:150,rowspan:2,sortable:true},
					{field:'publisher',title:'上传人',width:100,rowspan:2,sortable:true,hidden:true},
					{field:'description',title:'描述',width:100,rowspan:2,sortable:true,hidden:false,hidden:true,formatter:function(value,row,index){
																	var u="";
																	if(value==null||value==""){
																			u="该图片还没有描述，双击可以添加描述！";
																	}
																return u;
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
				$(uploadDivId).append('<p><span class="c-label">图片将被添加至新闻：《'+pRow.title+'》！</span> </p>');
				$(uploadDivId).append('<input type="file" name="Filedata" id="file_upload" />');
				uploadImg.initUpload(pRow.id);
				$(addDlgId).dialog('open').dialog('setTitle','添加图片');
			}else{
				$.messager.alert('提醒','请先选择新闻！','warning');
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
		this.batchEdit=batchEdit;
		function batchEdit(){
			var datas =  $(tableId).datagrid("getData");
			if(datas.total>0){
				$('#dlg_down_batchEdit').dialog('open').dialog('setTitle','批量修改');
				batchEditIframe.window.imgBatchEdit.loadImg(datas.rows,pRow);
			}else{
				$.messager.alert('提醒','暂无可修改图片！','warning');
			}
		}
		this.saveBatchEdit=saveBatchEdit;
		function saveBatchEdit(){
			batchEditIframe.window.imgBatchEdit.save();
		}
		this.afterSaveBatchEdit=afterSaveBatchEdit;
		function afterSaveBatchEdit(result){
			if (result.success){
				$('#dlg_down_batchEdit').dialog('close');
				unselectAll();
				refreshAll();
				$.messager.show({	// show error message
						title: '成功',
						msg: result.msg,
						showType:'show',
						timeout:10000
					});
				} else {
					$.messager.alert( '失败',result.msg,'error');
				}
			
		}
		this.edit=edit;
		function edit(){
			var row = getSelectedRow();
			if (row){
					$('#description_img').empty();
					$('#description_img').append('<img src="download/download!showImage?path=' + row.path + '"  style="height:200px;width:300px;">');
					$(editDlgId).dialog('open').dialog('setTitle','添加描述');
					$(editFromId).form('clear');
					$(editFromId).form('load',row);
			}else{
				$.messager.alert('提醒','请先选择图片!','warning');
			}
		}
		this.saveEdit=saveEdit;
		function saveEdit(){
			$(editFromId).form('submit',{
				url: url+'!editDescription',
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
						closeEditDlg();
						refreshAll();//不是本类
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
		this.editIsCorver=editIsCorver;
		function editIsCorver(){
			var row = getSelectedRow();
			if (row){
					if('是' !=row.isCover){
						$.post(url+'!editIsCorver',{id:row.id},function(result){
							if (result.success){
								refreshAll();//不是本类
								$.messager.show({	// show error message
									title: '成功',
									msg: result.msg,
									showType:'show',
									timeout:10000
								});
							} else {
								$.messager.alert( '失败',result.msg,'error');
							}
						},'json');
					}else{
						$.messager.alert('提醒','该图片已经是封面，无需重复设置！','warning');
					}
			}else{
				$.messager.alert('提醒','请先选择图片!','warning');
			}
			
		}
		this.remove=remove;
		function remove(){
			
			var rows = $(tableId).datagrid('getSelections');
			if (rows.length>0){
				var idss = [];
				var msg ="";
				var imgs=[];
				for(var i=0;i<rows.length;i++){
					if('是'==rows[i].isCover){
						var msg='其中图片：<br><img src="download/download!showImage?path=' + rows[i].path + '"  style="height:40px;width:50px;"><br>已被设为新闻封面！';
					}
					idss.push(rows[i].id);
					imgs.push('<img src="download/download!showImage?path=' + rows[i].path + '"  style="margin-top:3px;margin-left:3px;height:40px;width:50px;">');
					
				}
				var ids = idss.join(',');
				$.messager.confirm('提示：','<div>你确定要删除图片：<br>'+imgs.join('')+'<br>吗?</div><br>'+msg,function(r){
					if (r){
						$.post(url+'!remove',{ids:ids},function(result){
							if (result.success){
								unselectAll();
								refreshAll();//不是本类
								$.messager.show({	// show error message
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
				$.messager.alert('提醒','请选择需要删除的图片!','warning');
			}
		}//removeDown
		var cardview_down = $.extend({}, $.fn.datagrid.defaults.view, {
			renderRow: function(target, fields, frozen, rowIndex, rowData){
				var cc = [];
				cc.push('<td colspan=' + (fields.length-1) + ' style="padding:10px 5px;border:0;">');
				if (!frozen){
					cc.push('<img src="download/download!showImage?path=' + rowData.path + '"  style="height:170px;width:250px;float:left">');
					cc.push('</td>');
					cc.push('<td  style="padding:10px 5px;border:0;">');
					cc.push('<div style="margin-left:1px;width:200px;height:170px; overflow:auto; border:0;">');
					cc.push('<table class="dv-table" border="0" style="width:100%;">');
					for(var i=0; i<fields.length-1; i++){
						cc.push('<tr>');
						cc.push('<td class="dv-label" style="width:40%;" >');
						var copts = $(target).datagrid('getColumnOption', fields[i]);
						cc.push(copts.title+'：</td><td style="width:60%;">'+rowData[fields[i]]);
						cc.push('</td></tr>');
					}
					cc.push('<tr><td colspan="2"><font color="#15428B">描述：</font>');
					if( rowData[fields[i]]==null||rowData[fields[i]]==''){
						cc.push('该图片还没有描述，双击可以添加描述！');
					}else{
						cc.push(rowData.description);
					}
					cc.push('</td></tr>');
					cc.push('</table>');
					cc.push('</div>');
				}
				cc.push('</td>');
				return cc.join('');
			}
		});//cardview_down
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
														msg: "图片："+file.name+"上传成功！",
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
	</script>
</head>
<body class="easyui-layout"  id="body" style="display:none;">
<div id="layout_down" data-options="region:'center',iconCls:'icon-picture_key'" title="相关图片维护（暂未选择新闻！）" style="height:400px;width:400px;overflow:hidden;">
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
										<div id="description_img"></div>
									</td>
								</tr>
								<tr>
									<td>
										<textarea name = "description" class="easyui-validatebox" data-options="required:false,validType:'length[1,50]'" style="height:100px;width:300px;border:1px solid #ccc;padding:2px;overflow-y:hidden;overflow-x:hidden;"></textarea>
									</td>
								</tr>
								</table>
						</form>
				</div>
		
				<div id="dlg-buttons_down">
					<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="imgGrid.saveEdit()">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="imgGrid.closeEditDlg()">取消</a>
				</div>
		<div id="dlg_down_batchEdit" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="false"  fit="true" modal ="true"  collapsible="true"  closed="true" buttons="#dlg-buttons_down_batchEdit">
				<iframe name = "batchEditIframe"  id="batchEditIframe" src="news/newsNewsPicture!initBatchEdit"  width="1130px" height="450px"  frameborder="0" scrolling="no" ></iframe>
			<div id="dlg-buttons_down_batchEdit">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="imgGrid.saveBatchEdit()">保存修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_down_batchEdit').dialog('close')">取消</a>
			</div>
		</div>
		<div id="tab-toolbar_down">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-picture_add'" onclick="imgGrid.add()">添加图片</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-picture_delete'"  onclick="imgGrid.remove()">删除图片</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-picture_delete'"  onclick="imgGrid.batchEdit()">批量修改</a>
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
			<div data-options="iconCls:'icon-picture_delete'"  onclick="imgGrid.remove()">删除图片</div>
			<div data-options="iconCls:'icon-picture_edit'" onclick="imgGrid.edit()">添加描述</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-picture_delete'"  onclick="imgGrid.editIsCorver()">设为封面</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
</div>
<div data-options="region:'west',split:true,iconCls:'icon-application'" title="新闻维护" style="width:700px;height:300px;overflow:hidden;">
		<table id="table_up"></table>
		<div id="dlg_up_add" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="false" collapsible="true"   fit="true" modal ="true"   closed="true" buttons="#dlg-buttons_up"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写新闻信息</h2></div>
					</div> 
					<form id="fm_up_add" method="post" novalidate>
						<input id="newsNewsCategoryNameAdd" type = "hidden" name ="categoryName"/>
						<table align ="center"  >
								<tr>
									<td class="dv-label">标题：</td>
									<td><input name = "title" class="easyui-validatebox" data-options="required:true,validType:'length[1,200]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">新闻类别：</td>
									<td><input id="newsNewsCategoryTreeAdd" name = "categoryId" class="easyui-combotree" value="0" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">记者：</td>
									<td><input name = "author" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">摄影：</td>
									<td><input name = "photographer" class="easyui-validatebox" data-options="validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">关键字：</td>
									<td><input name = "keywords" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">头条：</td>
									<td><input type="radio" name="headline" value="是" ><span>是</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="headline" value="否"><span>否</span></td>
								</tr>
								<tr>
									<td class="dv-label">摘要：</td>
									<td><input name = "abstracts" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">内容：</td>
									<td><textarea name = "content" class="easyui-validatebox" data-options="required:true,validType:'length[1,10000]'" style="height:200px;width:400px;border:1px solid #ccc;	padding:2px;"></textarea></td>
								</tr>
								
						</table>
				</form>
			<div id="dlg-buttons_up">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="newsGrid.saveAdd()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_add').dialog('close')">取消</a>
			</div>
	</div>
	
	<div id="dlg_up_edit" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"
				maximizable = "true"  draggable="false" collapsible="true"   fit="true" modal ="true"   closed="true" buttons="#dlg-buttons_up_edit"><br>
					 <div class="demo-info" style="margin-bottom:10px;" >
						<div class="demo-tip icon-tip"></div>
						<div align = "center"><h2>请填写新闻信息</h2></div>
					</div> 
					<form id="fm_up_edit" method="post" novalidate>
						<input type = "hidden" name ="id"/>
						<input type = "hidden" name ="coverPath"/>
						<input id="newsNewsCategoryNameEdit" type = "hidden" name ="categoryName"/>
						<input type = "hidden" name ="status"/>
						<table align ="center"  >
								<tr>
									<td class="dv-label">标题：</td>
									<td><input name = "title" class="easyui-validatebox" data-options="required:true,validType:'length[1,200]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">新闻类别：</td>
									<td><input id="newsNewsCategoryTreeEdit" name = "categoryId" class="easyui-combotree" value="0" data-options="required:true" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">记者：</td>
									<td><input name = "author" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">摄影：</td>
									<td><input name = "photographer" class="easyui-validatebox" data-options="validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">关键字：</td>
									<td><input name = "keywords" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">头条：</td>
									<td><input type="radio" name="headline" value="是" ><span>是</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="headline" value="否"><span>否</span></td>
								</tr>
								<tr>
									<td class="dv-label">摘要：</td>
									<td><input name = "abstracts" class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" style="width:400px;border:1px solid #ccc;padding:2px;"></td>
								</tr>
								<tr>
									<td class="dv-label">内容：</td>
									<td><textarea name = "content" class="easyui-validatebox" data-options="required:true,validType:'length[1,3000]'" style="height:200px;width:400px;border:1px solid #ccc;	padding:2px;"></textarea></td>
								</tr>
						</table>
				</form>
			<div id="dlg-buttons_up_edit">
				<a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-ok" onclick="newsGrid.saveEdit()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_up_edit').dialog('close')">取消</a>
			</div>
	</div>
		<div id="tab-toolbar_up">
			<table cellpadding="0" cellspacing="0" style="width:100%">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_add'" onclick="newsGrid.add()">添加新闻</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_remove'"  onclick="newsGrid.remove()">删除新闻</a>
						<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-fabu2'"  onclick="newsGrid.activateStatus()">发布新闻</a>
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
			<div data-options="iconCls:'icon-application_remove'"  onclick="newsGrid.remove()">删除新闻</div>
			<div data-options="iconCls:'icon-application_edit'" onclick="newsGrid.edit()">修改新闻</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-fabu2'"  onclick="newsGrid.activateStatus()">发布新闻</div>
			<div data-options="iconCls:'icon-fabu'" onclick="newsGrid.revokeStatus()">撤销发布</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-picture_key'" onclick="newsGrid.manageImg()">维护图片</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-tuichu'" >退出</div>
		</div>
		
		
	</div>	
	
	
</body>
<script type="text/javascript">
		window.onload=function (){
			$('#body').show();
			$.parser.parse();
			uploadImg = new UploadFile('#file_upload','news/newsNewsPicture!add?','上传图片','*.jpg;');
			imgGrid = new ImgGrid('#table_down','news/newsNewsPicture','#Div_uploadImg','#dlg_down_add','#dlg_down_edit','#fm_down_edit');
			newsGrid = new NewsGrid('#table_up','news/newsNews','#dlg_up_add','#fm_up_add','#dlg_up_edit','#fm_up_edit');
			imgGrid.createGrid();
			newsGrid.createGrid();
			newsNewsCategoryTree=new MyTree('public/public!showNewsNewsCategoryTree',['#newsNewsCategoryTreeAdd','#newsNewsCategoryTreeEdit']);
			newsNewsCategoryTree.loadTreeData();
		}
	//$('#batchEditIframe')[0].src='news/newsNewsPicture!initBatchEdit';
</script>
</html>