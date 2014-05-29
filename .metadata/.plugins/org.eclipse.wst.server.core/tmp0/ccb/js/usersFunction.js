


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
				  
				 
				  var point=document.getElementById("user");
				  point.setAttribute("class", "active");
				  point.setAttribute("className", "active");
				  
				  var ul=point.getElementsByTagName("ul");
				  ul[0].setAttribute("class","active submenu");
				  var li=document.getElementById("userM");
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









//方法


function formartDate(dataFormate, time) {
    var date = new Date();
    date.setTime(time);
    return date.pattern(dataFormate);
}
Date.prototype.pattern = function(fmt) {
    var o = {
        "M+" : this.getMonth() + 1, //月份     
        "d+" : this.getDate(), //日     
        "h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时     
        "H+" : this.getHours(), //小时     
        "m+" : this.getMinutes(), //分     
        "s+" : this.getSeconds(), //秒     
        "q+" : Math.floor((this.getMonth() + 3) / 3), //季度     
        "S" : this.getMilliseconds()
    //毫秒     
    };
    var week = {
        "0" : "日",
        "1" : "一",
        "2" : "二",
        "3" : "三",
        "4" : "四",
        "5" : "五",
        "6" : "六"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1,
                ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周")
                        : "")
                        + week[this.getDay() + ""]);
    }
    if (/(e+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1,
                ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周")
                        : "")
                        + this.getDay());
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                    : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}











$(document).ready(function(){
	$.ajaxSetup({cache:false});
			$.ajax({
	        //请求方式为get
	        type:"GET",
	        data:{},
	        async:false,
	    
	        //json文件位置
	//       url:"data/user.json",
	         url:"../../ccb/sys/pageUsers2.do",
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
//	        	alert("data:"+data);
	  
	        	var b="";
				var c="";
				var data1=data.list;
				c="<thead><tr><th>角色名称</th><th>公司名称</th><th>用户名</th><th>ID</th><th>证件类型</th><th>证件号码</th>"
				+"<th>失效日期</th><th>邮箱</th><th>操作</th></tr></thead>";
				var abc="";
				abc="<tbody class='fillform'>";
				for(var i = 0; i < data1.length; i++) { //循环后台传过来的Json数组  
				   //   alert("haha");
   					  var datas = data1[i];
   					  var aa="";
   					
   					  if(datas.failureDate!=null){
   					var fdate=formartDate("yyyy-MM-dd",datas.failureDate.time);
   					  }else{
   						  
   						  var fdate="";
   					  }
					
   					  aa+= "<tr><td>" + datas.roleName + "</td>"+ "<td>" + datas.companyName + "</td>"+ "<td>" + datas.userName + "</td>"+ 
   					  "<td>" + datas.id + "</td>"+ "<td>" + datas.cardType + "</td>"+ "<td>" + datas.idnum + "</td>"+ "<td>" + fdate + "</td>"+ "<td>" + datas.email + "</td>"
   					  +"<td><ul class='actions'><li id='detail' onclick='detail("+datas.id+")'><span class='label label-info'><i class='fa fa-th-list' >详细</i></span></li><li id='edit' onclick='edit("+datas.id+")'><span class='label label-warning'><i class='fa fa-wrench'>修改</i></span></li><li class='last' onclick='dele("+datas.id+")'><span class='label label-danger'><i class='fa fa-times'>删除</i></span></li></ul></td></tr>";  
 					 	
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
//		alert("123");
 	
});  


function refresh(){
	
	location.reload(true);
	
}

function detail(id){           
	//       alert(id);
	       
	        	$('#detail').attr("data-toggle","modal");
	        	$('#detail').attr("data-target","#myModal2");
	        	$('#myModal2').modal('show');
	        	
	        	$("#myTb2").empty();
	        	
	        	var c="";
				c="<thead><tr><th>属性名</th><th>属性值</th></tr></thead>";
	        	$("#myTb2").append(c);
	        	$.ajaxSetup({cache:false});
				$.ajax({
		        //请求方式为get
		        type:"GET",
		        data:{},
		        async:false,
		    
		        //json文件位置
		         url:"../../ccb/sys/editUsers.do?viewuser.id="+id,
		        //返回数据格式为json
		        dataType: "json",
		        //请求成功完成后要执行的方法
		        success: function(data){
		           var datac=data[0];
	   		//			  alert(datac.id);
	   				 
		            
					var b="";
					
					
					
					b="<tbody><tr><td>公司名称</td><td>" + datac.comId + "</td></tr><tr>" + "<td>角色名称</td><td>" + datac.rolId + "</td></tr><tr>"+ "<td>用户名称</td><td>" + datac.userName + "</td></tr><tr>"+ "<td>密码</td><td>" + datac.password + "</td></tr><tr>"+ "<td>证件类型</td><td>" + datac.cardType + "</td></tr><tr>"+ "<td>证件号码</td><td>" + datac.idnum + "</td></tr><tr>"+ "<td>失效时间</td><td>" + datac.failureDate + "</td></tr><tr>"+ "<td>用户状态</td><td>" + datac.customerStatus + "</td></tr><tr>"+ 
	   					  "<td>邮箱</td><td>" + datac.email + "</td></tr><tr>"+ "<td>移动电话</td><td>" + datac.cellphone + "</td></tr><tr>"+ "<td>固定电话</td><td>" + datac.tel + "</td></tr><tr>"+ "<td>联系地址</td><td>" + datac.address + "</td></tr><tr>"+ "<td>邮政编码</td><td>" + datac.zipCode + "</td></tr><tr>"+ "<td>性别</td><td>" + datac.sex + "</td></tr><tr>"+ "<td>出生日期</td><td>" + datac.birthday + "</td></tr><tr>"+ "<td>学历</td><td>" + datac.educationalBackground+ "</td></tr><tr>"+ "<td>职业</td><td>" + datac.occupation + "</td></tr><tr>"+ "<td>备注</td><td>" + 
	   					  datac.remarks + "</td></tr><tr>"+ "<td>用户等级</td><td>" + datac.customerLev + "</td></tr><tr>"+ "<td>忠诚度</td><td>" + datac.loyalty + "</td></tr><tr>"+ "</tr></tbody>";   
					
					$("#myTb2").append(b);					    
					  
		        },
		        error:function(error){
						alert("error");
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
	         url:"../../ccb/sys/removeUser.do?viewuser.id="+id,
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	            alert("删除成功");				    
	            location.reload(true);
	        },
	        error:function(error){
					//alert("error");
	        	location.href="access-denied.jsp";
           }
	    });	
}


function edit(id){           

    //alert(id);
    
    $('#edit').attr("data-toggle","modal");
    $('#edit').attr("data-target","#myModal");

    $('#myModal').modal('show');
    $.ajaxSetup({cache:false});
        $.ajax({ 
        type: "get", 
        url: "../../ccb/sys/editUsers.do?viewuser.id="+id, 
        dataType: "json", 
        success: function (data) {
        	var datas=data[0];
        	
        	//alert(datas.id);
        	    $("#id").val(datas.id);
        	    $("#comId").val(datas.comId);
				$("#rolId").val(datas.rolId);
				$("#userName").val(datas.userName);
				$("#password").val(datas.password);
				$("#cardType").val(datas.cardType);
				$("#idnum").val(datas.idnum);
				$("#failureDate").val(datas.failureDate);
				$("#customerStatus").val(datas.customerStatus);
				$("#email").val(datas.email);
				$("#cellphone").val(datas.cellphone);
				$("#tel").val(datas.tel);
				$("#address").val(datas.address);
				$("#zipCode").val(datas.zipCode);
				$("#sex").val(datas.sex);
				$("#birthday").val(datas.birthday);
				$("#educationalBackground").val(datas.educationalBackground);
				$("#occupation").val(datas.occupation);
				$("#remarks").val(datas.remarks);
				$("#customerLev").val(datas.customerLev);
				$("#loyalty").val(datas.loyalty);
				
        }, 
        error:function(error){
			alert("error");
        }
   });
}



function save() {
//	alert("save");
	    var params = $("#frm1").serialize();

		$.ajaxSetup({cache:false});
		$.ajax({
					type : "POST",
					data :params,//data: {"user.suid":model.suid},//"role.sroleid="+model.sroleid,
				//	 async:false,
					
					url : "../../ccb/sys/addOrUpdateUser.do",
					
					success : function(responseText, statusText) {
						var rs = eval('(' + responseText + ')');

						if (rs.addorupdate == 'input') {


						} else if (rs.addorupdate == 'success') {
							
							alert('保存成功');
							location.href="Users.jsp";
						}
					},
					error : function() {
						alert('连接失败e');
					},
					statusCode : {
						404 : function() {
							alert('连接失败');
						}
					}
				});
			}


//增加
function add() {
	//alert("save");
	    var params = $("#frm1").serialize();
//		var aErr = document.getElementById("actionErr");
//		var await = document.getElementById("indicator");
//		aErr.style.display = "none";
//		aErr.innerHTML = "";

//		btn.style.display = "none";
//		await.style.display = "";
//		 alert("params:"+params);
	    $.ajaxSetup({cache:false});
		$.ajax({
					type : "POST",
					data :params,//data: {"user.suid":model.suid},//"role.sroleid="+model.sroleid,
					async:true,
					
					url : "../../ccb/sys/addOrUpdateUser.do",					
					success : function(responseText, statusText) {
						var rs = eval('(' + responseText + ')');
//						alert(responseText);
//						alert(rs.message);
//						btn.style.display = "";
//						await.style.display = "none";
//						alert('rs.message:' + rs.message)
						if (rs.addorupdate == 'input') {
							//$("#actionErr").val(rs.validateMsgs);            	 
//							aErr.innerHTML = rs.actionValMsgs;
//							aErr.style.display = "";
							//alert('检查输入');

						} else if (rs.addorupdate == 'success') {
//							var now = new Date();
//							var turl = 'editCompany.do?company.id=' + rs.obj.id
//									+ '&dtm=' + now.getTime();
//							alert('turl' + turl);
//							location.href = turl
//							//location.reload('');
							alert('保存成功');
							location.href="Users.jsp";
							
						}
					},
					error : function() {
						alert('连接失败e');
//						btn.style.display = "";
//						await.style.display = "none";
					},
					statusCode : {
						404 : function() {
							alert('连接失败');
//							btn.style.display = "";
//							await.style.display = "none";
						}
					}
				});
	}


$(document).ready(function(){

	$('#frm1').validate({
    rules: {
    	"user.comId": {
        minlength: 1,
        required: true
      },
      "user.rolId": {
	        minlength: 1,
	        required: true
	  },
	  "user.userName": {
		  
		   
	  },
	  "user.password": {
			minlength: 6,
			required: true
	  },
	  "user.cardType": {
		    minlength: 1,
		    required: true
	  },
	  "user.idnum": {
		    minlength: 10,
		    required: true
	  },
	  "user.failureDate": {
		  minlength: 1,
			required: true
	  },
	  "user.email": {
		    email: true,
		    required: true
	  },
	  "user.cellphone": {
		  rangelength:[11,11],
			digits:true,
			required: true
	  },
	  "user.tel": {
		  rangelength:[7,8],
			digits:true,
			required: true
	  },
	  "user.address": {
		    minlength: 5,
		    required: true
	  },
	  "user.zipCode": {
		  rangelength:[6,6],
			digits:true,
			required: true
	  },
	  "user.customerStatus": {
		    minlength: 1,
		    required: true
	  },
	  "user.sex": {
		    minlength: 1,
		    required: true
	  },
	  "user.birthday": {
		  minlength: 1,
			required: true
	  },
	  "user.educationalBackground": {
		    minlength: 1,
		    required: true
	  },
	  "user.occupation": {
		    minlength: 1,
		    required: true
	  },
	  "user.remarks": {
		    minlength: 1,
		    required: true
	  },
	  "user.customerLev": {
		    minlength: 1,
		    required: true
	  },
	  "user.loyalty": {
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