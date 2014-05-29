<%@ page language="java" import="java.util.*,com.util.Sys" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'batchEdit.jsp' starting page</title>
    
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
	<script type="text/javascript" src="resources/js/json/json2.js"></script>
	<style type="text/css">
		.assigned{
			border:1px solid #BC2A4D;
		}
	
		#div_imgSource .divStyle{
			display:inline;
			margin-top:10px;
			margin-left:10px;
			float:left;
			width:152px;
			height:150px; 
			border:1px solid #5C9CC0; 
			background-color: #F2FAFF 
		}
		#div_imgSource .divStyle_Form{ 
			width: 150px; 
			height: 50px; 
		} 
		#div_imgSource .divStyle .imgStyle{
			width:150px;
			height:105px;
			border:1px solid #333;
		}
		.textareaStyle{
			width:150px;
			height:50px;
			border:0;
			overflow-y:hidden;
			overflow-x:hidden;
		}
		#div_imgCover .divStyle{
			display:inline;
			margin-top:10px;  
			margin-left:10px;
			float:left;
			width:150px;
			height:150px; 
			border:2;
		}
		#div_imgCover .divStyle_Form{ 
			display:none;
		} 
		
		#div_imgCover  .imgStyle{
			width: 225px; 
			height: 145px; 
			border:1px solid #333;
		}
		#div_imgDelete .divStyle{
			display:inline;
			margin-top:3px;  
			margin-left:3px;
			float:left;
			width:70px;
			height:49px;
			border:2;
		}
		#div_imgDelete .divStyle .imgStyle{
			width:70px;
			height:49px;
			border:1px solid #333;
		}
		#div_imgDelete .divStyle_Form{ 
			display:none;
		} 
		#div_imgSource1 { 
			margin: 0px auto; 
			border:2px solid #9BDF70; 
			background-color: #F0FBEB 
		} 
		#div_imgSource { 
			width: 830px; 
			height: 400px; 
		} 
		#div_imgCover { 
			width: 230px; 
			height: 150px; 
			margin-left:2px;
		} 
		#div_imgCover1 { 
			margin: 0px auto;
			border:2px solid #FFCC00; 
			background-color: #FFFFF7 
		} 
		#div_imgDelete { 
			width: 230px; 
			height: 220px; 
		} 
		#div_imgDelete1 { 
			margin: 0px auto;
			border:2px solid #F8B3D0; 
			background-color: #FFF5FA 
		} 
		
	</style>
	<script>
		function ImgBatchEdit(){
				var pRow = null;
				var defaultImg='<img class="imgStyle" src="download/download!showImage?path=<%=Sys.DEFAULTPICTUREPATH%>"/>';
				this.initDragDrop=initDragDrop;
				function initDragDrop(){
					$('.imgStyle').draggable({
						proxy:'clone',
						revert:true,
						onStartDrag:function(){
						},
						onStopDrag:function(){
						}
					});
					$('.divStyle').droppable({
						accept:'#div_imgSource .divStyle .imgStyle',
						onDragOver:function(e,source){
						},
						onDragLeave:function(e,source){
						},
						onDrop:function(e,source){
							if($(this)[0].id!=$(source).parent()[0].id&&$(source).parent()[0].id!='div_imgCover'){
								$(source).parent().insertBefore(this);
							}
							
						}
					});
					$('#div_imgCover').droppable({
						accept:'#div_imgSource .divStyle .imgStyle',
						onDragEnter:function(e,source){
						},
						onDragLeave:function(e,source){
						},
						onDrop:function(e,source){
								//$("#div_imgSource").append($(this).children());
							if($(source).parent().parent()[0].id=='div_imgSource'){
									$('#imgCoverId').val($(source).parent()[0].id);
									var c = $(source).clone().addClass('imgStyle');
									$(this).empty().append(c);
									c.draggable({
										proxy:'clone',
										revert:true
									});
								//}
							}
						}
					});
					$('#div_imgSource').droppable({
						accept:'#div_imgDelete .divStyle .imgStyle',
						onDragEnter:function(e,source){
						},
						onDragLeave:function(e,source){
						},
						onDrop:function(e,source){
							if($(source).parent().parent()[0].id=='div_imgDelete'){
								$(this).append($(source).parent());
							}
						}
					});
					$('#div_imgDelete').droppable({
						accept:':not(#div_imgDelete .divStyle .imgStyle)',
						onDragEnter:function(e,source){
						},
						onDragLeave:function(e,source){
						},
						onDrop:function(e,source){
							if($(source).parent().parent()[0].id=='div_imgSource'){
								$(this).append($(source).parent());
								if($(source).parent()[0].id==$('#imgCoverId').val()){
									$('#imgCoverId').val('');
									$('#div_imgCover').empty().append(defaultImg);
								}
							}else if($(source).parent()[0].id=='div_imgCover'){
								setTimeout(
								function(){
									$('#imgCoverId').val('');
									$('#div_imgCover').empty().append(defaultImg);
								},200);
								
							}
						}
					});
				};//initDragDrop
				this.loadImg=loadImg;
				function loadImg(datas,prow){
					initPage();
					pRow=prow;
					var imgs=[];
					$.each(datas,function(i,n){
						imgs.push('<div id="'+n.id+'" class="divStyle">');
						imgs.push('<img class="imgStyle" src="download/download!showImage?path='+n.path+'"/>');
						imgs.push('<div class="divStyle_Form">');
						imgs.push('<form id="form1">');
						imgs.push('<input id="path'+n.id+'"  type="hidden" value="'+n.path+'"/>');
						imgs.push('<input id="pid'+n.id+'"  type="hidden" value="'+n.pid+'"/>');
						if(n.description==null||n.description==""){ 
							imgs.push('<textarea id="description'+n.id+'" class="textareaStyle" onblur="onBlurText(this);" onfocus="onFocusText(this);" style="color: #d1d1d1;">点击添加描述！</textarea>');
						}else{ 
							imgs.push('<textarea id="description'+n.id+'"  class="textareaStyle" onblur="onBlurText(this);" onfocus="onFocusText(this);">'+n.description+'</textarea>');
						} 
						imgs.push('</form>');
						imgs.push('</div>');
						imgs.push('</div>');
						if('是'==n.isCover){
							$('#imgCoverId').val(n.id);
							$('#div_imgCover').append('<img class="imgStyle" src="download/download!showImage?path='+n.path+'"/>');
						}
					});
					$('#div_imgSource').append(imgs.join(''));
					if(''==$('#imgCoverId').val()){
						$('#div_imgCover').append(defaultImg);
					}
					initDragDrop();
				}
				this.initPage=initPage;
				function initPage(){
					pRow = null
					$('#div_imgSource').empty();
					$('#div_imgDelete').empty();
					$('#imgCoverId').val('');
					$('#div_imgCover').empty();
				}
				this.save=save;
				function save(){
					var edits=[];
					$.each($("#div_imgSource").children(),function(i,n){
						var id = n.id;
						if($("#description"+n.id).val()=='点击添加描述！'){
							var description =null;
						}else{
							var description = $("#description"+n.id).val();
						}
						var orders=i;
						var path = $("#path"+n.id).val();
						var pid = $("#pid"+n.id).val();
						edits.push(new picture(id,pid,path,description,orders));
					});
					var deletes=[];
					$.each($("#div_imgDelete").children(),function(i,n){
							var id = n.id;
							var description = $("#description"+n.id).val();
							var orders=i;
							var path = $("#path"+n.id).val();
							var pid = $("#pid"+n.id).val();
							deletes.push(new picture(id,pid,path,description,orders));
					});
					var coverId=$('#imgCoverId').val();
					$.messager.confirm('提示：','你确定要修改吗？',function(r){
									if (r){
										$.post('news/newsNewsPicture!batchEdit',{edits:JSON.stringify(edits),deletes:JSON.stringify(deletes),id:coverId,pid:pRow.id},function(result){
												parent.imgGrid.afterSaveBatchEdit(result);
										},'json');
									}
								});//confirm
				}
				function picture(id,pid,path,description,orders){
					this.id=id;
					this.pid=pid;
					this.path=path;
					this.description=description;
					this.orders=orders;
				}
		}
		var imgBatchEdit=new ImgBatchEdit();
		function onBlurText(target){
			if($(target).val()==''){
				$(target).val('点击添加描述！');
				target.style.color='#D1D1D1';
			}
		}
		function onFocusText(target){
			if($(target).val()=='点击添加描述！'){
				$(target).val('');
				target.style.color='#000';
			}
		}
	</script>
</head>
<body>
<table align="center" >
<tr>
	<td valign = "top" rowspan = "2" >
		<div id="div_imgSource1" >
		 <h5 align="center" style="margin: 0px;background-color: #C2ECA7;height: 17px;">编辑区域</h5>
		<div id="div_imgSource" >
		</div>
		</div>
	</td>
	<td valign = "top" >
			<div  id="div_imgCover1"><h5 align="center"  style="margin: 1px;background-color: #FFCC00;height: 17px;">封面</h5>
				<input id="imgCoverId" type="hidden" value="">
				<div id="div_imgCover">
				</div>
			</div>
	</td>
	</tr>
	<tr>
	<td valign = "top" >
			<div id="div_imgDelete1" >
				<h5 align="center"  style="margin: 1px;background-color: #F8B3D0 ;height: 17px;">删除区域</h5>
				<div id="div_imgDelete" >
				</div>
			</div>
	</td>
	<tr>
	</table>
</body>
</html>
