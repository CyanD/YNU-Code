$(document).ready(function(){
	$.ajaxSetup({cache:false});
				$.ajax({
				//请求方式为get
				type:"GET",
				data:{},
				async:false,
				//json文件位置
				url:"../../ccb/sys/findModule.do",
				//返回数据格式为json
				dataType: "json",
				//请求成功完成后要执行的方法
				success: function(data){			
					
					var b="";
					b+="<li><a href='/ccb/index.jsp'><i class='fa fa-home'></i><span>首页</span></a></li>";
					var data1=data.menu;
		
					for(var i = 0; i < data1.length; i++) { //循环后台传过来的Json数组  
						var datas = data1[i];
						var aa="";
				  			  
						var d=datas.root;
						var dd=datas.submenu;
				  
	//			  var dd1=dd[0].child;

						var c1="";
						
						for(var j=0;j<dd.length;j++){
							var da=dd[j];
							var da1=da.child;                   	 
							var c="";

							c+="<li id='"+da1.smallicon+"'><a href='"+da1.url+"'>"+da1.moudleName+"</a></li>";
						
							c1+=c;
						}
					
					aa+= "<li id='"+d.smallicon+"'><a class='dropdown-toggle' href='"+d.url+"'><i class='fa fa-arrow-circle-right' id='icon-group'></i><span>"+d.moudleName+"</span><i class='fa fa-chevron-down' id='icon-chevron-down'></i></a>"
                    +"<ul class='submenu'>"+c1
                    +"</ul>"
                    +"</li>";                     
						
				    b+=aa;	
				    //  alert(b);  
			   	  }  
 
				  $("#dashboard-menu").append(b);
				  
				 
				  var point=document.getElementById("rol");
				  point.setAttribute("class", "active");
				  point.setAttribute("className", "active");
				  
				  var ul=point.getElementsByTagName("ul");
				  ul[0].setAttribute("class","active submenu");
				  var li=document.getElementById("roleM");
				  //alert(li.innerHTML);
				  li.firstChild.setAttribute("class","active");
				  li.setAttribute("className","active");
				  
				  var style=document.createElement("div");
				  style.setAttribute("class","pointer");
				  style.innerHTML="<div class='arrow'></div><div class='arrow_border'></div>";
				  point.insertBefore(style,point.firstChild); 
				  
//				  alert(point.parentNode.innerHTML);
				  
				  },
				error:function(error){
					alert("error");
				}
			});
		 //  var cc=document.getElementById("dashboard-menu");
	//	 alert("ok");
		
});




$(document).ready(function(){
	$.ajaxSetup({cache:false});
			$.ajax({
	        //请求方式为get
	        type:"GET",
	        data:{},
	        async:false,
	    
	        //json文件位置
	//       url:"data/user.json",
	         url:"../../ccb/sys/pageroles2.do",
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        	var b="";
				var c="";
				var data1=data.list;
				c="<thead><tr><th>主键</th><th>角色名</th><th>部门</th><th>公司名称</th><th>职位</th><th>权限</th><th>操作</th></tr></thead>";
				var abc="";
				abc="<tbody>";
				for(var i = 0; i < data1.length; i++) { //循环后台传过来的Json数组  
   					  var datas = data1[i];
   					  var aa="";  					  
   					 
   					  aa+= "<tr><td>" + datas.id + "</td>"+ "<td>" + datas.roleName + "</td>"+ "<td>" + datas.depName + "</td>"+"<td>"+datas.companyName+"</td>"+"<td>" + datas.positionName + "</td>"+ "<td>" + datas.issuper + "</td>"+
   					  "<td style='width:230px'><ul class='actions'><li id='edit' onclick='edit("+datas.id+")'><span class='label label-warning'><i class='fa fa-wrench'>修改</i></span></li><li class='last' onclick='dele("+datas.id+")'><span class='label label-danger'><i class='fa fa-times'>删除</i></span></li></ul></td></tr>";  
  					  					 	
 					 b+=aa;		  
				}  
				abc+=b;
				abc+="</tbody>";
				$("#myTb1").append(c);
				$("#myTb1").append(abc);
				//alert(document.getElementById("myTb1").innerHTML);
	        },
	        error:function(error){
					alert("error");
            }
	    });
	//	alert("123");
}); 


function refresh(){
	
	location.reload(true);
	
}


//修改
function edit(id){           
	document.getElementById("myModalLabel").innerHTML="修改角色信息";
        //alert(id);
	onEditRole_Init_Pos(id);
	onEditRole_Init_Dep(id);
        $('#edit').attr("data-toggle","modal");
        $('#edit').attr("data-target","#myModal");

         $('#myModal').modal('show');
         $.ajaxSetup({cache:false});
			$.ajax({
	        //请求方式为get
	        type:"GET",
	        data:{},
//	        async:false,
	    
	        //json文件位置
	        url:"../../ccb/sys/pageroles2.do",
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        	var data1=data.list;
				for(var i = 0; i < data1.length; i++) { //循环后台传过来的Json数组  
   					  var datas = data1[i];
   					  	if(datas.id==id){
   					  	$("#id").val(datas.id);
   						$("#roleName").val(datas.roleName);
   						$("#pos_id").val(datas.pos_id);
   						$("#pos_id").find("option[text='"+datas.dep_id+"']").attr("selected",true);
   						$("#depName").val(datas.depName);
   						$("#dep_id").val(datas.dep_id); 	
   						$("#companyName").val(datas.companyName);
   					  	}
   						  
				} 
				comSelChanged();
   					
	        },
	        error:function(error){
					alert("error");
            }
	    });	
	
}
function comSelChanged(){
	var tcomId = $("#companyName").val();
	onEditRole_Init_Pos(tcomId);
	onEditRole_Init_Dep(tcomId);
}
function onEditRole_Init_Pos(id) {
	//var tcomId = $("#companyName").val();
	//alert('tcomId:'+tcomId);
	$("#pos_id").empty();
	$.ajaxSetup({cache:false});
	$.ajax({
		type : "POST",
		url : "/ccb/sys/findPositionSelByCom.do",
		data : "selComId=" + id,
		dataType: "json",
		success : function(data) {
			
			for ( var i = 0; i < data.length; i++) {
				var topt = "<option value='"+data[i].id+"'>"
						+ data[i].positionName + "</option>";
				jQuery("#pos_id").append(topt);
			}
			return false;
		},
		error : function() {
			alert('连接失败');
		},
		statusCode : {
			404 : function() {
				alert('连接失败');
			}
		}
	});
}

function onEditRole_Init_Dep(id) {
	//var tcomId = $("#companyName").val();
	//alert('onEditRole_Init_Dep tcomId:'+id);

	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	if(treeObj!=null){
		var nodes = treeObj.getNodes();
		if(nodes!=null){
			for (var i = 0, l = nodes.length; i < l; i++) {
			    treeObj.removeNode(nodes[i]);
			}
		}
		
	}
	$.ajaxSetup({cache:false});
	$.ajax({
		type : "post",
		url : "/ccb/sys/selOrgTree2.do",
		data : "selComId=" + id,
		//dataType: "json", 
		success : function(data) {
			//ffll
			//alert("selOrgTree2 data:" + data);
			zNodes = eval("(" + data + ")");
			//zNodes = data;
			$.fn.zTree.init($("#treeDemo"),setting, zNodes);
		},
		error : function(XMLHttpRequest,textStatus, errorThrown) {
			//alert("textStatus:" + textStatus+ ", errorThrown" + errorThrown);
			location.href="access-denied.jsp";
		}
	});
}

//保存修改
	function save(btn) {
//	alert("save");
	    var params = $("#frm1").serialize();
//		var aErr = document.getElementById("actionErr");
//		var await = document.getElementById("indicator");
//		aErr.style.display = "none";
//		aErr.innerHTML = "";

		btn.style.display = "none";
//		await.style.display = "";
//		 alert("params:"+params);
		//alert(params);
		$.ajaxSetup({cache:false});
		$.ajax({
					type : "POST",
					data :params,//data: {"user.suid":model.suid},//"role.sroleid="+model.sroleid,
					 async:true,
					
					url : "../../ccb/sys/addOrUpdateRole.do",					
					success : function(responseText, statusText) {
						var rs = eval('(' + responseText + ')');
//						alert(responseText);
						btn.style.display = "";
//						await.style.display = "none";
//						alert('rs.message:' + rs.message)
						if (rs.message == 'input') {
							//$("#actionErr").val(rs.validateMsgs);            	 
//							aErr.innerHTML = rs.actionValMsgs;
//							aErr.style.display = "";
							//alert('检查输入');

						} else if (rs.message == 'success') {
//							var now = new Date();
//							var turl = 'editCompany.do?company.id=' + rs.obj.id
//									+ '&dtm=' + now.getTime();
//							alert('turl' + turl);
//							location.href = turl
							alert('保存成功');
							location.reload(true);
							
						}
					},
					error : function() {
						//alert('连接失败e');
						//btn.style.display = "";
//						await.style.display = "none";
						location.href="access-denied.jsp";
					},
					statusCode : {
						404 : function() {
							//alert('连接失败');
							//btn.style.display = "";
//							await.style.display = "none";
							location.href="access-denied.jsp";
						}
					}
				});
			}

//删除
function dele(id){	
		   // alert(id);
	$.ajaxSetup({cache:false});
			$.ajax({
	        //请求方式为get
	        type:"GET",
	        data:{},
	        async:false,
	    
	        //json文件位置
	         url:"../../ccb/sys/removeRole.do?role.id="+id,
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        	//alert(data.message);
	        	if(data.message=="success"){
	        		alert("删除成功");	
	        	}else{
	        		alert("删除失败："+data.message);	
	        	}
	            
	            location.reload(true);
				  
	        },
	        error:function(error){
					//alert("error");
					location.href="access-denied.jsp";
            }
	    });	
}



//增加
function add() {
	    var params = $("#frm1").serialize();
//		 alert("params:"+params);
	    $.ajaxSetup({cache:false});
		$.ajax({
					type : "POST",
					data :params,//data: {"user.suid":model.suid},//"role.sroleid="+model.sroleid,
				//	async:false,
					
					url : "../../ccb/sys/addOrUpdateRole.do",					
					success : function(responseText, statusText) {
						var rs = eval('(' + responseText + ')');
//						alert(responseText);
//						alert(rs.message);
//						btn.style.display = "";
//						await.style.display = "none";
//						alert('rs.message:' + rs.message)
						if (rs.message == 'input') {
							//$("#actionErr").val(rs.validateMsgs);            	 
	//						aErr.innerHTML = rs.actionValMsgs;
	//						aErr.style.display = "";
							//alert('检查输入');

						} else if (rs.message == 'success') {
//							var now = new Date();
//							var turl = 'editCompany.do?company.id=' + rs.obj.id
//									+ '&dtm=' + now.getTime();
//							alert('turl' + turl);
//							location.href = turl
//							//location.reload('');
							alert('保存成功');
							location.href="Role.jsp";
							
						}
					},
					error : function() {
						//alert('连接失败e');
//						btn.style.display = "";
//						await.style.display = "none";
						location.href="access-denied.jsp";
					},
					statusCode : {
						404 : function() {
							//alert('连接失败');
//							btn.style.display = "";
//							await.style.display = "none";
							location.href="access-denied.jsp";
						}
					}
				});
	}

$(document).ready(function(){

	$('#frm1').validate({
    rules: {
      "role.roleName": {
        minlength: 1,
        required: true
      },
      "role.companyName": {
	        minlength: 1,
	        required: true
	  },
	  "role.dep_id": {
		    minlength: 0,
		    required: true
	  },
	  "role.pos_id": {
			minlength: 1,
			required: true
	  }
    },
    
      messages: {
    	  
    	  },
		highlight: function(element) {
			$(element).closest('.field-box').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.text('OK!').addClass('valid')
			.closest('.field-box').removeClass('error').addClass('success');
		}
  });

}); // end document.ready

function comSelChanged(){
	var tcomId = $("#companyName").val();
	onEditRole_Init_Pos(tcomId);
	onEditRole_Init_Dep(tcomId);
}
function onEditRole_Init_Pos(id) {
	//var tcomId = $("#companyName").val();
	//alert('tcomId:'+tcomId);
	$("#pos_id").empty();
	$.ajaxSetup({cache:false});
	$.ajax({
		type : "POST",
		url : "/ccb/sys/findPositionSelByCom.do",
		data : "selComId=" + id,
		dataType: "json",
		success : function(data) {
			
			for ( var i = 0; i < data.length; i++) {
				var topt = "<option value='"+data[i].id+"'>"
						+ data[i].positionName + "</option>";
				jQuery("#pos_id").append(topt);
			}
			return false;
		},
		error : function() {
			alert('连接失败');
		},
		statusCode : {
			404 : function() {
				alert('连接失败');
			}
		}
	});
}

function onEditRole_Init_Dep(id) {
	//var tcomId = $("#companyName").val();
	//alert('onEditRole_Init_Dep tcomId:'+id);

	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	if(treeObj!=null){
		var nodes = treeObj.getNodes();
		if(nodes!=null){
			for (var i = 0, l = nodes.length; i < l; i++) {
			    treeObj.removeNode(nodes[i]);
			}
		}
		
	}
	$.ajaxSetup({cache:false});
	$.ajax({
		type : "post",
		url : "/ccb/sys/selOrgTree2.do",
		data : "selComId=" + id,
		//dataType: "json", 
		success : function(data) {
			//ffll
			//alert("selOrgTree2 data:" + data);
			zNodes = eval("(" + data + ")");
			//zNodes = data;
			$.fn.zTree.init($("#treeDemo"),setting, zNodes);
		},
		error : function(XMLHttpRequest,textStatus, errorThrown) {
			alert("textStatus:" + textStatus
					+ ", errorThrown" + errorThrown);
		}
	});
}

