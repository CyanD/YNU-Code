<%@ page language="java" import="java.util.*,com.model.login.LoginUser,com.util.Sys" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		LoginUser loginUser = (LoginUser)session.getAttribute(Sys.LOGINUSER);
		String username=loginUser.getName();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>mobile后台维护系统</title>
    
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
	<script  type="text/javascript" src="resources/js/myjs/main.js"></script>
</head>
<body class="easyui-layout" id="body" style="display:none;">
		<div data-options="region:'west',split:true,iconCls:'icon-main'" title="后台数据维护" style="width:250px;padding1:1px;overflow:hidden;">
				<div class="easyui-accordion" data-options="fit:true,border:false">
					<% if("有".equals(loginUser.getLoginRole().getSys())) { %>
					<div title="系统维护" style="padding:10px;overflow:auto;">
							<div align = "center">
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('用户维护','sys/sysUser','icon-default')"><img src="resources/images/user2_64.png"><br>用户维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('部门维护','sys/sysDept','icon-default')"><img src="resources/images/user_64.png"><br>部门维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('角色维护','sys/sysRole','icon-default')"><img src="resources/images/user_64.png"><br>角色维护</a>					
							</div>
				</div>
				<%} %>
				<% if("有".equals(loginUser.getLoginRole().getNews())) { %>
				<div title="新闻维护" style="padding:10px;overflow:auto;">
							<div align = "center">
							    <a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('新闻类别维护','sys/newsNewsCategory','icon-default')"><img src="resources/images/user_64.png"><br>新闻类别维护</a>
								<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('公告类别维护','notice/noticeCategory','icon-default')"><img src="resources/images/user_64.png"><br>公告类别维护</a>
								<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('相册类别维护','sys/newsAlbumCategory','icon-default')"><img src="resources/images/user_64.png"><br>相册类别维护</a>
								<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('新闻维护','news/newsNews','icon-default')"><img src="resources/images/news_64.png"><br />新闻维护</a>
							</div>
				</div>
				<%} %>
				<% if("有".equals(loginUser.getLoginRole().getAnniversary())) { %>
				<%} %>
				<% if("有".equals(loginUser.getLoginRole().getBus())) { %>			
				<%} %>
				<% if("有".equals(loginUser.getLoginRole().getAddressBook())) { %>
					<div title="通讯录维护" style="padding:10px;overflow:auto;">
								<div align = "center">
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('云大黄页维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />云大黄页维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校友通讯录维护','addressbook/addressbookAlumni','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />校友通讯录维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('工作通讯录维护','addressbook/addressbookWork','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />工作通讯录维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('领导部门维护','addressbook/addressbookDepartment','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />领导部门维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('学院维护','addressbook/addressbookDept','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />学院维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('系别维护','addressbook/addressbookDeptSystem','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />系别维护</a>
						</div>
					</div>
				<%} %>
				<div title="校历维护" style="padding:10px;overflow:auto;">
								<div align = "center">
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校历维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />校历维护</a>
								
								</div>
				</div>
				<%-- <% if("有".equals(loginUser.getLoginRole().getCalendar())) { %>									
				<%} %> --%>
				<%-- <% if("有".equals(loginUser.getLoginRole().getMap())) { %> 
				<%} %> --%>
				<div title="地图维护" style="padding:10px;overflow:auto;">
								<div align = "center">
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('地图维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />地图维护</a>					
								</div>
				</div>
				<div title="数字云大维护" style="padding:10px;overflow:auto;">
								<div align = "center">
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('公开课类别维护','course/courseCategory','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />公开课类别维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('在线课堂维护','course/courseCourse','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />在线课堂维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('云大相册维护','news/newsAlbum','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />云大相册维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('电子资源维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />电子资源维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('数字博物馆维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />数字博物馆维护</a>
								</div>
				</div>
				<div title="公共服务维护" style="padding:10px;overflow:auto;">
								<div align = "center">
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校巴维护','bus/busRoute','icon-default')"><img src="resources/images/bus_64.png"><br />校巴维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('云大活动维护','publicservice/publicserviceActivity','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />云大活动维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('图书馆维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />图书馆维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('跳蚤市场维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />跳蚤市场维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('云大故事维护','publicservice/ynuStory','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />云大故事维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校历维护','calendar/calendarCategory','icon-default')"><img src="resources/images/calendar.jpg" style="height:64px;width:64px"><br />校历维护</a>
								</div>
				</div>
				<div title="校庆维护" style="padding:10px;overflow:auto;">
								<div align = "center">								
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校庆公告维护','notice/notice','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />校庆公告维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校友会维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />校友会维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('祝福云大维护','addressbook/addressbookPublic','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />祝福云大维护</a>
									<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('校友捐赠维护','anniversary/anniversaryDonation','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />校友捐赠维护</a>
								</div>
				</div>
				
				<% if("有".equals(loginUser.getLoginRole().getCourse())) { %>
				<%} %>
				
				<div title="教师之家维护" style="padding:10px;overflow:auto;">
					<div align="center">
						<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('教师之家类别维护','publicservice/teacherHomeKind','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />教师之家类别维护</a>
				    	<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('教师之家维护','publicservice/teacherHome','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />教师之家维护</a>
					</div>
				</div>
				
				<div title="学生园地维护" style="padding:10px;overflow:auto;">
					<div align="center">
						<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('学生园地类别维护','publicservice/studentFieldKind','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />学生园地类别维护</a>
						<a class="easyui-linkbutton" data-options="plain:true" href="javascript:void(0)" onclick="addTab('学生园地维护','publicservice/studentField','icon-default')"><img src="resources/images/addressbook_128.png" style="height:64px;width:64px"><br />学生园地维护</a>
					</div>
				</div>
				
							
				</div>
		</div>
		<div data-options="region:'center'" title="<%=loginUser.getName()%>!欢迎您！" style="overflow:hidden;">
			<div id="tabs"  class="easyui-tabs" data-options="fit:true,border:false">
					<div title="主页"  data-options="iconCls:'icon-home'" style="padding:20px;overflow:hidden;"> 
					<div style="margin-top:20px;">
					</div>
				</div>
			</div>
		</div>
		
		
		<div id="mm" class="easyui-menu" style="width:150px;">
	         <div id="refresh" data-options="iconCls:'icon-reload'" >刷新</div>
	         <div class="menu-sep"></div>
	         <div id="close" data-options="iconCls:'icon-chacha2'"  >关闭当前页</div>
	         <div id="closeall" data-options="iconCls:'icon-chacha'"  >全部关闭</div>
	         <div id="closeother" data-options="iconCls:'icon-chacha3'" >除此之外全部关闭</div>
	         <div class="menu-sep"></div>
	         <div id="closeright" data-options="iconCls:'icon-you'" >当前页右侧全部关闭</div>
	         <div id="closeleft" data-options="iconCls:'icon-zuo'" >当前页左侧全部关闭</div>
	         <div class="menu-sep"></div>
	         <div id="exit" data-options="iconCls:'icon-tuichu'" >退出</div>
	 </div>
</body>
<script type="text/javascript">
		function showMsg(result){
			if (result.success){
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
		window.onload=function (){
			$('#body').show();
			$.parser.parse();
		}
</script>
</html>
