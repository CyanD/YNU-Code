  	function MyTree(url,targets){
	  	  this.createTreeData=createTreeData;	
		  function createTreeData(id,data){
			  var children=[];
			  $.each(data,function(i,n){
				  if(n._parentId==id){
					  children.push(new node(n.id,n.name, null,createTreeData(n.id,data)));
				  }
			  });
			  return children;
		  }
		  this.node=node;
		  function node(id,text,iconCls,children){
			  this.id=id;
			  this.text=text;
			  this.iconCls=iconCls;
			  this.children=children;
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
