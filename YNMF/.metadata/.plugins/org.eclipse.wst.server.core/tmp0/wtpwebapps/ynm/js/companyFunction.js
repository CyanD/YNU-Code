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
				  
				 
				  var point=document.getElementById("com");
				  point.setAttribute("class", "active");
				  point.setAttribute("className", "active");
				  
				  var ul=point.getElementsByTagName("ul");
				  ul[0].setAttribute("class","active submenu");
				  var li=document.getElementById("comM");
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
//<!-- DQL 20131230 -->

function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = {
		"Y" : "ps",
		"N" : "ps"
	};
	zTree.setting.check.chkboxType = type;
}

var setting = {
		view : {
			dblClickExpand : false
		},
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : beforeClick,
			onClick : onClick
		}
	};

	function beforeClick(treeId, treeNode) {
		//var check = (treeNode && !treeNode.isParent);
		//if (!check) alert("只能部门...");
		//return check;
	}

	function onClick(e, treeId, treeNode) {
	
	}
function onEditComPerm(id) {

	
	$('#comPerm').attr("data-toggle","modal");
	$('#comPerm').attr("data-target","#myModal3");
	$('#myModal3').modal('show');
	
	
	//alert('id:'+id);
	//alert('treeDemo:'+$("#treeDemo"));
	//$("#menuContent").css({left:obj.offsetLeft+10, top:obj.offsetTop + 22}).slideDown("fast");
	//$("body").bind("mousedown", onBodyDown);
	$("#editComId").val(id);
	//alert('editComId:'+$("#editComId").val());
	$.ajaxSetup({cache:false});
	$.ajax({
		type : "POST",
		url : "/ccb/com/findCompanysTree.do",
		data : "company.id=" + id,
		success : function(responseText, statusText) {
			//alert("responseText:"+responseText);
			//alert("statusText:"+statusText);
			// var rs = $.parseJSON(responseText);
			var zNodes = eval('(' + responseText + ')');
			//var zNodes = rs;//rs.datas;
			//                       alert('zNodes:'+zNodes)
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
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

	//alert('has deleted:'+model.suid);
}
function beforeClick(treeId, treeNode) {
	//var check = (treeNode && !treeNode.isParent);
	//if (!check) alert("只能部门...");
	//return check;
}

function onClick(e, treeId, treeNode) {	
	//var nameObj = $("#depName");
	//var idObj = $("#dep_id");

	//nameObj.attr("value", treeNode.DEPARTMENTNAME);
	//idObj.attr("value", treeNode.id);
}

function saveComPerm() {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var funIdsStr = "";//typeof(tfunid) != "undefined"
	var comId = $("#editComId").val();
	for ( var i = 0; i < nodes.length; i++) {
		var tfunid = nodes[i].id;
		if(tfunid.indexOf("T")>-1){
			continue;
		}
		//alert('typeof(tfunid):'+typeof(tfunid));
		//alert('tfunid != null: '+(tfunid != null ));
		//alert(' tfunid != "":'+( tfunid != ""));
		//alert('!isNaN(tfunid):'+(!isNaN(tfunid)));
		// alert('tfunid:'+tfunid);
		if (typeof (tfunid) != "undefined" && tfunid != null
				&& tfunid != "" && !isNaN(tfunid) && tfunid > 0) {
			if (funIdsStr.length > 0)
				funIdsStr = funIdsStr + ",";
			funIdsStr = funIdsStr + tfunid;
		}

	}
	$.ajaxSetup({cache:false});
	$.ajax({
		type : "POST",
		url : "/ccb/com/saveComperms.do",
		data : {
			"selIds" : funIdsStr,
			"company.id" : comId
		},//"role.sroleid="+model.sroleid,
		success : function(responseText, statusText) {
			// alert("responseText:"+responseText);
			// alert("statusText:"+statusText);
			var zNodes = eval('(' + responseText + ')');
			//var zNodes = rs;//rs.datas;
			//                       alert('zNodes:'+zNodes)
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
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
	//alert('funIdsStr'+funIdsStr)
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
	         url:"../../ccb/com/pagecompanys2.do",
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        	var b="";
				var c="";
				var data1=data.list;
				c="<thead><tr><th>电话</th><th>公司名称</th><th>ID</th><th>法人代表</th><th>证书号</th>"
				+"<th>贷款数额</th><th>资产</th><th>操作</th></tr></thead>";
				var abc="";
				abc="<tbody>";
				for(var i = 0; i < data1.length; i++) { //循环后台传过来的Json数组  
   					  var datas = data1[i];
   					  var aa="";  					  
   					//<!-- DQL 20131230 -->
   					  //alert(datas.companyName);
   					if(datas.businessRegistrationNo==""){
   						datas.businessRegistrationNo="【无权限查看】";						
					}
   					if(datas.cellPhoneNumber==""){
   						datas.cellPhoneNumber="【无权限查看】";						
					}
   					if(datas.companyName==""){
   						datas.companyName="【无权限查看】";						
					}
   					if(datas.id==""){
   						datas.id="【无权限查看】";						
					}
   					if(datas.legalRepresentative==""){
   						datas.legalRepresentative="【无权限查看】";						
					}
   					if(datas.licenseNum==""){
   						datas.licenseNum="【无权限查看】";						
					}
   					if(datas.loanNum==""){
   						datas.loanNum="【无权限查看】";						
					}
   					if(datas.property==""){
   						datas.property="【无权限查看】";						
					}
   					
   					  
   					  
   					  aa+= "<tr><td>" + datas.cellPhoneNumber + "</td>"+ "<td>" + datas.companyName + "</td><td>" + datas.id + "</td>"+ "<td>" + datas.legalRepresentative + "</td>"+ "<td>" + datas.licenseNum + "</td>"+ "<td>" + datas.loanNum + "</td>"+ "<td>" + datas.property + "</td>"+ 
   					  "<td><ul class='actions'><li id='comPerm' onclick='onEditComPerm("+datas.id+")'><span class='label label-success'><i class='fa fa-sitemap' >授权</i></span></li><li id='detail' onclick='detail("+datas.id+")'><span class='label label-info'><i class='fa fa-th-list' >详细</i></span></li><li id='edit' onclick='edit("+datas.id+")'><span class='label label-warning'><i class='fa fa-wrench'>修改</i></span></li><li class='last' onclick='dele("+datas.id+")'><span class='label label-danger'><i class='fa fa-times'>删除</i></span></li></ul></td></tr>";  
  					  	
   					  //alert(datas.companyName);
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


function detail(id){           
	 //       alert(id);
	       
	        	$('#detail').attr("data-toggle","modal");
	        	$('#detail').attr("data-target","#myModal2");
	        	$('#myModal2').modal('show');
	        	
	        	$("#myTb2").empty();

	        	$.ajaxSetup({cache:false});
				$.ajax({
		        //请求方式为get
		        type:"GET",
		        data:{},
		        async:false,
		    
		        //json文件位置
		         url:"../../ccb/com/editCompany2.do?company.id="+id,
		        //返回数据格式为json
		        dataType: "json",
		        //请求成功完成后要执行的方法
		        success: function(data){
		        	var datas=data.comPers;
		        	var datac=data.company;
		        //  alert(datas.id);
		        	
	        		var c="";
	        		c+="<thead><tr><th>属性名</th><th>属性值</th></tr></thead>";
	        		       
	        		if(datac.foundingTime!=null){
	        		     var date=formartDate("yyyy-MM-dd",datac.foundingTime.time);
	        		}else{
	        			var date="";
	        			
	        		}
					var b="";

					if(datas.companyName==0){
						datac.companyName="【无权限查看】";
					}
					if(datas.loanNum==0){
						datac.loanNum="【无权限查看】";
					}
					if(datas.registeredCapital==0){
						datac.registeredCapital="【无权限查看】";
					}
					if(datas.certificatesNum==0){
						datac.certificatesNum="【无权限查看】";
					}
					if(datas.legalRepresentative==0){
						datac.legalRepresentative="【无权限查看】";
					}
					if(datas.depositNum==0){
						datac.depositNum="【无权限查看】";
					}
					if(datas.scope==0){
						datac.scope="【无权限查看】";
					}
					if(datas.contacts==0){
						datac.contacts="【无权限查看】";
					}
					if(datas.facility==0){
						datac.facility="【无权限查看】";
					}
					if(datas.address==0){
						datac.address="【无权限查看】";
					}
					if(datas.remarks==0){
						datac.remarks="【无权限查看】";
					}
					if(datas.property==0){
						datac.property="【无权限查看】";
					}
					if(datas.zipCode==0){
						datac.zipCode="【无权限查看】";
					}
					if(datas.types==0){
						datac.types="【无权限查看】";
					}
					if(datas.customerLev==0){
						datac.customerLev="【无权限查看】";
					}
					if(datas.byTheWay==0){
						datac.byTheWay="【无权限查看】";
					}
					if(datas.id==0){
						datac.id="【无权限查看】";
					}
					if(datas.groupStatus==0){
						datac.groupStatus="【无权限查看】";
					}
					if(datas.cellPhoneNumber==0){
						datac.cellPhoneNumber="【无权限查看】";
					}
					if(datas.businessRegistrationNo==0){
						datac.businessRegistrationNo="【无权限查看】";
					}
					if(datas.taxRegistrationNum==0){
						datac.taxRegistrationNum="【无权限查看】";
					}
					if(datas.licenseNum==0){
						datac.licenseNum="【无权限查看】";
					}
					if(datas.leverage==0){
						datac.leverage="【无权限查看】";
					}
					if(datas.creditScore==0){
						datac.creditScore="【无权限查看】";
					}
					if(datas.contactNumber==0){
						datac.contactNumber="【无权限查看】";
					}
					if(datas.foundingTime==0){
						date="【无权限查看】";
					}
					if(datas.creditLimit==0){
						datac.creditLimit="【无权限查看】";
					}
					if(datas.loyalty==0){
						datac.loyalty="【无权限查看】";
					}
					if(datas.fax==0){
						datac.fax="【无权限查看】";
					}
					if(datas.certificatesType==0){
						datac.certificatesType="【无权限查看】";
					}
					if(datas.remind==0){
						datac.remind="【无权限查看】";
					}
					
					
					
					b+="<tbody><tr><td>地址</td><td>" + datac.address + "</td></tr><tr><td>工商注册号</td><td>" + datac.businessRegistrationNo + "</td></tr><tr><td>支用方式</td><td>" + datac.byTheWay + "</td></tr><tr><td id='cellPhoneNumber1'>手机号码</td><td>" + datac.cellPhoneNumber + "</td></tr><tr><td>证件号码</td><td>" + datac.certificatesNum + "</td></tr><tr><td>证件类型</td><td>" + datac.certificatesType + "</td></tr><tr><td>公司名称</td><td>" + datac.companyName + "</td></tr><tr><td>联系电话</td><td>" + datac.contactNumber + "</td></tr><tr><td>联系人</td><td>" + datac.contacts + "</td></tr>"
					+"<tr><td>信用额度</td><td>" + datac.creditLimit + "</td></tr><tr><td>信用分数</td><td>" + datac.creditScore + "</td></tr><tr><td>客户等级</td><td>" + datac.customerLev + "</td></tr><tr><td>存款次数</td><td>" + datac.depositNum + "</td></tr><tr><td>授信额度</td><td>" + datac.facility + "</td></tr><tr><td>传真</td><td>" + datac.fax + "</td></tr><tr><td>成立时间</td><td>" + date + "</td></tr><tr><td>集团状态</td><td>" + datac.groupStatus + "</td></tr><tr><td>公司ID</td><td>" + 
	   					  datac.id + "</td></tr><tr><td>法人代表</td><td>" + datac.legalRepresentative + "</td></tr><tr><td>杠杆率</td><td>" + datac.leverage + "</td></tr><tr><td>经营许可证号</td><td>" + datac.licenseNum + "</td></tr><tr><td>贷款次数</td><td>" + datac.loanNum + "</td></tr><tr><td>忠诚度</td><td>" + datac.loyalty + "</td></tr><tr><td>性质</td><td>" + datac.property + "</td></tr><tr><td>注册资金</td><td>" + datac.registeredCapital + "</td></tr><tr><td>备注</td><td>" + datac.remarks + "</td></tr><tr><td>到期提醒</td><td>" + datac.remind + 
	   					  "</td></tr><tr><td>业务范围</td><td>" + datac.scope + "</td></tr><tr><td>税务登记号</td><td>" + datac.taxRegistrationNum + "</td></tr><tr><td>类型</td><td>" + datac.types + "</td></tr><tr><td>邮编</td><td>" + datac.zipCode + "</td></tr></tbody>";   
					
						
						
					$("#myTb2").append(c);
					$("#myTb2").append(b);	
					
		        },
		        error:function(error){
						alert("error");
	            }
		    });	
		   
}


//修改
function edit(id){           

        //alert(id);
        
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
	         url:"../../ccb/com/editCompany2.do?company.id="+id,
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        //var datac=data.comPers;
	        var datas=data.company;
	        
	        if(datas.foundingTime!=null){
   		     var date=formartDate("yyyy-MM-dd",datas.foundingTime.time);
   		}else{
   			var date="";
   			
   		}
	        
	    //  alert(datas.id);
            $("#id").val(datas.id);
				$("#companyName").val(datas.companyName);
				$("#types").val(datas.types);
				$("#property").val(datas.property);
				$("#legalRepresentative").val(datas.legalRepresentative);
				$("#contacts").val(datas.contacts);
				$("#contactNumber").val(datas.contactNumber);
				$("#fax").val(datas.fax);
				$("#cellPhoneNumber").val(datas.cellPhoneNumber);
				$("#address").val(datas.address);
				$("#zipCode").val(datas.zipCode);
				$("#certificatesType").val(datas.certificatesType);
				$("#certificatesNum").val(datas.certificatesNum);
				$("#licenseNum").val(datas.licenseNum);
				$("#businessRegistrationNo").val(datas.businessRegistrationNo);
				$("#taxRegistrationNum").val(datas.taxRegistrationNum);
				$("#registeredCapital").val(datas.registeredCapital);
				
								
				$("#foundingTime").val(date);
				
				$("#groupStatus").val(datas.groupStatus);
				$("#scope").val(datas.scope);	
				$("#depositNum").val(datas.depositNum);	
				$("#loanNum").val(datas.loanNum);	
				$("#creditScore").val(datas.creditScore);	
				$("#creditLimit").val(datas.creditLimit);	
				$("#facility").val(datas.facility);	
				$("#leverage").val(datas.leverage);	
				$("#byTheWay").val(datas.byTheWay);	
				$("#remind").val(datas.remind);	
				$("#remarks").val(datas.remarks);	
				$("#customerLev").val(datas.customerLev);	
				$("#loyalty").val(datas.loyalty);
   					
	        },
	        error:function(error){
					//alert("error");
	        	location.href = "access-denied.jsp";
            }
	    });	
	
}

//保存修改
	function save(btn) {
	//alert("save");
	    var params = $("#frm1").serialize();
		var aErr = document.getElementById("actionErr");
//		var await = document.getElementById("indicator");
		aErr.style.display = "none";
		aErr.innerHTML = "";

		btn.style.display = "none";
//		await.style.display = "";
//		 alert("params:"+params);
		$.ajaxSetup({cache:false});
		$.ajax({
					type : "POST",
					data :params,//data: {"user.suid":model.suid},//"role.sroleid="+model.sroleid,
					 async:true,
					
					url : "../../ccb/com/addOrUpdateCompany.do",					
					success : function(responseText, statusText) {
						var rs = eval('(' + responseText + ')');
//						alert(responseText);
						btn.style.display = "";
//						await.style.display = "none";
//						alert('rs.message:' + rs.message)
						if (rs.message == 'input') {
							//$("#actionErr").val(rs.validateMsgs);            	 
							aErr.innerHTML = rs.actionValMsgs;
							aErr.style.display = "";
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
						alert('连接失败');
						btn.style.display = "";
//						await.style.display = "none";
					},
					statusCode : {
						404 : function() {
							alert('连接失败');
							btn.style.display = "";
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
	         url:"../../ccb/com/removeCompany.do?company.id="+id,
	        //返回数据格式为json
	        dataType: "json",
	        //请求成功完成后要执行的方法
	        success: function(data){
	        	//var rs = eval('(' + responseText + ')');
	        	if(data.message=="success"){
	        		alert("删除成功");	
	        	}else{
	        		alert("删除失败："+data.message);	
	        	}
	            
	            location.reload(true);
				  
	        },
	        error:function(error){
					alert("error");
            }
	    });	
}



//增加
function add() {
//	alert("save");
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
				//	async:false,
					
					url : "../../ccb/com/addOrUpdateCompany.do",					
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
							location.href = "Company.jsp";
							
						}
					},
					error : function() {
						alert('连接失败');
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
      "company.companyName": {
        minlength: 2,
        required: true
      },
      "company.types": {
	        minlength: 0,
	        required: true
	  },
	  "company.property": {
		    minlength: 1,
		    required: true
	  },
	  "company.legalRepresentative": {
			minlength: 2,
			required: true
	  },
	  "company.contacts": {
		    minlength: 2,
			required: true
	   },
	   "company.contactNumber": {
		   rangelength:[7,8],
			digits:true,
			required: true
		},
		"company.fax": {
			rangelength:[7,8],
			digits:true,
			required: true
		},
		"company.cellPhoneNumber": {
			rangelength:[11,11],
			digits:true,
			required: true
		},
		"company.address": {
			minlength: 5,
			required: true
		},
		"company.zipCode": {
			rangelength:[6,6],
			digits:true,
			required: true
		},
		"company.certificatesType": {
			minlength: 0,
			required: true
		},
		"company.certificatesNum": {
			minlength: 10,
			required: true
		},
		"company.foundingTime": {
			minlength: 1,
			required: true
		},
		"company.licenseNum": {
			rangelength:[10,12],
			digits:true,
			required: true
		},
		"company.businessRegistrationNo": {
			rangelength:[13,15],
			digits:true,
			required: true
		},
		"company.taxRegistrationNum": {
			rangelength:[15,18],
			digits:true,
			required: true
		},
		"company.registeredCapital": {
			minlength: 5,
			required: true
		},
		"company.groupStatus": {
			minlength: 2,
			required: true
		},
		"company.scope": {
			minlength: 2,
			required: true
		},
		"company.depositNum": {
			minlength: 1,
			digits:true,
			required: true
		},
		"company.loanNum": {
			minlength: 1,
			digits:true,
			required: true
		},
		"company.creditScore": {
			minlength: 2,
			digits:true,
			required: true
		},
		"company.creditLimit": {
			minlength: 2,
			digits:true,
			required: true
		},
		"company.facility": {
			minlength: 2,
			required: true
		},
		"company.leverage": {
			minlength: 2,
			required: true
		},
		"company.byTheWay": {
			minlength: 2,
			required: true
		},
		"company.remind": {
			minlength: 2,
			required: true
		},
		"company.remarks": {
			minlength: 2,
			required: true
		},
		"company.customerLev": {
			minlength: 1,
			required: true
		},
		"company.loyalty": {
			minlength: 2,
			required: true
		}
    },
    
      messages: {
    	  "company.contactNumber":"请输入正确的电话号码格式",
    	  "company.fax":"请输入正确的传真格式",
    	  "company.cellPhoneNumber":"手机号码长度为11位",
    //	  "company.licenseNum":"请输入正确的许可证号码格式",
    //	  "company.businessRegistrationNo":"请输入正确的工商注册号格式",
    //	  "company.taxRegistrationNum":"请输入正确的税务登记号格式",
    	  "company.registeredCapital":"请输入真实的注册金额",
    	  "company.zipCode":"请输入正确的邮编格式",
    	  },
		highlight: function(element) {
//			alert(element);
			$(element).closest('.field-box').removeClass('success').addClass('error');
			
//			if($(element).attr(readonly)==true){
//			alert($(element).attr(readonly));
//				$(element).closest('.field-box').removeClass('success');
//				$(element).closest('.field-box').removeClass('error');
				
//			}
		},
		success: function(element) {
			element
			.text('OK!').addClass('valid')
			.closest('.field-box').removeClass('error').addClass('success');
//			alert($(element).attr(readonly));
		}
  });

}); // end document.ready