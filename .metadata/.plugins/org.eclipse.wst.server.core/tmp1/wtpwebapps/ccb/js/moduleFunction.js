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
				  
				 
				  var point=document.getElementById("sys");
				  point.setAttribute("class", "active");
				  point.setAttribute("className", "active");
				  
				  var ul=point.getElementsByTagName("ul");
				  ul[0].setAttribute("class","active submenu");
				  var li=document.getElementById("mod");
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

    