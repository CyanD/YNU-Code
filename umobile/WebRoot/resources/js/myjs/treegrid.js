function TreeGrid(tableId,preUrl,dlgAdd,fmAdd,dlgEdit,fmEdit,treeName){
				function createTreeData(id,data){
					var children=[];
					$.each(data,function(i,n){
						if(n._parentId==id){
							children.push(new node(n.id,n.name, null,createTreeData(n.id,data)));
						}
					});
					return children;
			}
			function node(id,text,iconCls,children){
	  			this.id=id;
	  			this.text=text;
	  			this.iconCls=iconCls;
	  			this.children=children;
			}
			this.refresh=refresh;
			function refresh(){
				$(tableId).treegrid('reload');
			}
			this.createTreeGrid=createTreeGrid;
			function createTreeGrid(){
				$(tableId).treegrid({
					iconCls:'icon-save',
					width:700,
					height:350,
					nowrap: false,
					fit: true,
					fitColumns:true,
					autoRowHeight:false,
					rownumbers: false,
					animate:false,
					collapsible:false,
					url:preUrl+'!show',
					idField:'id',
					treeField:'name',
					frozenColumns:[[
		               
		                {field:'name',title:treeName+'名称',width:400}
					]],
					toolbar:'#tab-toolbar',
					columns:[[
						{field:'createTime',title:'创建 / 修改时间',width:200,rowspan:2},
						{field:'publisher',title:'创建 / 修改人',width:200,rowspan:2}
					]],
					onLoadSuccess:function(row, data){
												var treeData=createTreeData(0,data.rows);
												treeData.push(new node(0,'无上级'+treeName, 'icon-xiaolian',[]));
												$('#cc').combotree('loadData',treeData);
												$('#cc_edit').combotree('loadData',treeData);	
											},
					onClickRow:function(row){
												
												//$(tableId).treegrid("unselect",row.id);
											},
					onDblClickRow:function(row){
												//unselectAll();
												$(tableId).treegrid("select",row.id);
												//pid = row.id;
												edit();
	          								
											},
					onContextMenu: function(e,row){
						e.preventDefault();
						$(this).treegrid('unselectAll');
						$(this).treegrid('select', row.id);
						$('#mm').menu('show', {
							left: e.pageX,
							top: e.pageY
						});
					}
				});//treeGrid
			}//createTreeGrid
			this.add=add;
			function add(){
				var row = $(tableId).treegrid('getSelected');
			
				$(dlgAdd).dialog('open').dialog('setTitle','添加'+treeName);
					$(fmAdd).form('clear');
				if(row){
					$(fmAdd).form('load',{_parentId:row.id});
				}else{
					$(fmAdd).form('load',{_parentId:0});
				}
			}
			function unselectAll(){
				 $(tableId).treegrid("unselectAll");
			}
			this.edit=edit;
			function edit(){
				
				var row = $(tableId).treegrid('getSelected');
				if(row){
					$(dlgEdit).dialog('open').dialog('setTitle','修改'+treeName);
					$(fmEdit).form('clear');
					$(fmEdit).form('load',row);
				}else{
					$.messager.alert('提醒','请选择需要修改的'+treeName+'！','warning');
				} 
			}
			this.closeDlgEdit=closeDlgEdit;
			function closeDlgEdit(){
				$(dlgEdit).dialog('close')
			}
			this.saveEdit=saveEdit;
			function saveEdit(){
				var pid = $('#cc_edit').combotree('getValue');
				var row = $(tableId).treegrid('getSelected');
				if(pid==row.id){
						$.messager.alert('提醒','上级'+treeName+'不能更改为当前'+treeName+'！','warning');
				}else{
						var rows = $(tableId).treegrid('getChildren', row.id);
						var flag = false;
						for(var i=0; i<rows.length; i++){
								if(pid==rows[i].id){
									flag = true;
								} 
						}
						if(flag){
								$.messager.alert('提醒','上级'+treeName+'不能更改为当前'+treeName+'的下级'+treeName+'！','warning');
						}else{
								$(fmEdit).form('submit',{
										url: preUrl+'!edit',
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
												$(tableId).treegrid('reload');
												closeDlgEdit();
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
								}); //form
						}//else flag
					}//else pid==row.id
			
			}
			this.saveAdd=saveAdd;
			function saveAdd(){
				$(fmAdd).form('submit',{
					url: preUrl+'!add',
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
							closeDlgAdd()
							$(tableId).treegrid('reload');
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
			this.closeDlgAdd=closeDlgAdd;
			function closeDlgAdd(){
				$(dlgAdd).dialog('close')
			}
			this.remove=remove;
			function remove(){
				var row = $(tableId).treegrid('getSelected');
				if (row){
					var rows = $(tableId).treegrid('getChildren', row.id);
					var names = [];
					var msg =row.name;
					for(var i=0; i<rows.length; i++){
						names.push( rows[i].name) ;
					}
					if(names.length>0){
						msg += '以及它的下级'+treeName+'：'+names.join('、');
					}
					$.messager.confirm('提示：','你确定要删除'+treeName+'：'+msg+'吗?',function(r){
						if (r){
							
							$.post(preUrl+'!remove',{id:row.id},function(result){
								if (result.success){
									unselectAll();//清空选择器里面的row，以免修改混乱
									$(tableId).treegrid('reload');
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
					$.messager.alert('提醒','请选择需要删除的'+treeName+'！','warning');
				}
			}//remove
		}//