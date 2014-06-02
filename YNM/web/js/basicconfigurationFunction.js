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
				  
				 
				  var point=document.getElementById("basic");
				  point.setAttribute("class", "active");
				  point.setAttribute("className", "active");
				  
				  var ul=point.getElementsByTagName("ul");
				  ul[0].setAttribute("class","active submenu");
				  var li=document.getElementById("basicInfo");
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
	         url:"../../ccb/sys/findBasicconfiguration.do",
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        	var b="";
				var c="";
				var data1=data.list;
				
				c="<thead><tr><th>ID</th><th>公司类型</th><th>操作</th></tr></thead>";
				var abc="";
				abc="<tbody>";
				for(var i = 0; i < data1.length; i++) { //循环后台传过来的Json数组  
   					  var datas = data1[i];
   					  var aa="";  					  
   					
   					
   					  
   					  aa+= "<tr><td>" + datas.id + "</td>"+ "<td>" + datas.typename + "</td>"+ "<td><ul class='actions'><li id='edit' onclick='edit("+datas.id+")'><span class='label label-warning'><i class='fa fa-wrench'>修改</i></span></li><li class='last' onclick='dele("+datas.id+")'><span class='label label-danger'><i class='fa fa-times'>删除</i></span></li></ul></td></tr>";  
  					  					 	
 					 b+=aa;		  
				}  
				abc+=b;
				abc+="</tbody>";
				$("#myTb1").append(c);
				$("#myTb1").append(abc);
				//alert(document.getElementById("myTb1").innerHTML);
				
				
			//	var last=JSON.stringify(data);
	            //location.href="../sys/basicconfigurationResult.jsp";
				//alert(last);
	        },
	        error:function(error){
					//alert("error");
					location.href="access-denied.jsp";
            }
	    });
	//	alert("123");
}); 


function refresh(){
	
	location.reload(true);
	
}




//修改
function edit(id){           

	document.getElementById("myModalLabel").innerHTML="修改配置信息";
    //alert(id);
    
    $('#edit').attr("data-toggle","modal");
    $('#edit').attr("data-target","#myModal");

     $('#myModal').modal('show');
     
     
var fm=document.getElementsByName("frm1");
var sp=document.getElementById("actionErr");
//   alert(fm[0].innerHTML);
    if(!document.getElementById("id")){
 //   alert("123");
    
       var id=document.createElement("input");
       id.setAttribute("name","basicconfiguration.id");
       id.setAttribute("id","id");
       id.setAttribute("theme","simple");
       id.setAttribute("type","hidden");
    
    sp.parentNode.insertBefore(id,sp);
//    alert(fm[0].innerHTML);
}
    $.ajaxSetup({cache:false});
			$.ajax({
	        //请求方式为get
	        type:"GET",
	        data:{},
	        async:false,
	    
	        //json文件位置
	         url:"../../ccb/sys/editBasicconfiguration.do?basicconfiguration.id="+id,
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        //var datac=data.comPers;
	        var datas=data[0];
	       // var date=formartDate("yyyy-MM-dd",datas.changedate.time);
   					//  alert(datas.id);
	                $("#id").val(datas.id);
	                $("#TYPENAME").val(datas.typename);
   					
	        },
	        error:function(error){
					//alert("error");
					location.href="access-denied.jsp";
            }
	    });	
	
}

//保存修改
	function save(btn) {
	    var params = $("#frm1").serialize();
//		 alert("params:"+params);
	    $.ajaxSetup({cache:false});
		$.ajax({
					type : "POST",
					data :params,//data: {"user.suid":model.suid},//"role.sroleid="+model.sroleid,
					 async:true,
					
					url : "../../ccb/sys/addOrUpdateBasicconfiguration.do",					
					success : function(responseText, statusText) {
						var rs = eval('(' + responseText + ')');
//						alert(responseText);
//						btn.style.display = "";
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
//						btn.style.display = "";
//						await.style.display = "none";
						location.href="access-denied.jsp";
					},
					statusCode : {
						404 : function() {
							alert('连接失败');
							//btn.style.display = "";
//							await.style.display = "none";
						}
					}
				});
			}

//删除
function dele(id){	
		    //alert(id);
	$.ajaxSetup({cache:false});
			$.ajax({
	        //请求方式为get
	        type:"GET",
	        data:{},
	        async:false,
	    
	        //json文件位置
	         url:"../../ccb/sys/removeBasicconfiguration.do?basicconfiguration.id="+id,
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
	            
	        	
	        	//var last=JSON.stringify(data);
	            //location.href="../sys/basicconfigurationResult.jsp";
	        	//alert(last);
				location.reload(true);
				
	        },
	        error:function(error){
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
					
					url : "../../ccb/sys/addOrUpdateBasicconfiguration.do",					
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
							location.href="basicconfigurationResult.jsp";
							
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
    	"basicconfiguration.typename": {
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