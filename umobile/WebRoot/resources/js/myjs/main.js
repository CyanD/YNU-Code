var index = 0;
function addTab(title,url,icon){
			var content =createFrame(url) ;
			if($('#tabs').tabs('exists',title)){
				 //refreshByTitle(title,content);
				 $('#tabs').tabs('select',title);
			}else{
				$('#tabs').tabs('add',{
					title:title,
					iconCls:icon,
					content : content ,
					closable:true
				});
			}
}
function createFrame(url){
	index++;
	return '<iframe name="'+index+'"id="'+index+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>';
}
function refreshByTitle(title,content){
	var tab = $('#tabs').tabs('getTab',title);
	var iframe = $(tab.panel('options').content);
    //var src = iframe.attr('src');
    $('#tabs').tabs('update', {
        tab: tab,
        options: {
            content: content
        }
    });
	
}


var onlyOpenTitle='主页';
function closeTab(action)
{
var alltabs = $('#tabs').tabs('tabs');
var currentTab =$('#tabs').tabs('getSelected');
var allTabtitle = [];
$.each(alltabs,function(i,n){
    allTabtitle.push($(n).panel('options').title);
})


switch (action) {
    case "refresh":
        var iframe = $(currentTab.panel('options').content);
        var url = iframe.attr('src');
        $('#tabs').tabs('update', {
            tab: currentTab,
            options: {
                content: createFrame(url)
            }
        });
        break;
    case "close":
        var currtab_title = currentTab.panel('options').title;
          if (currtab_title != onlyOpenTitle){
                    $('#tabs').tabs('close', currtab_title);
           }
        break;
    case "closeall":
    	
        $.each(allTabtitle, function (i, n) {
            if (n != onlyOpenTitle){
                $('#tabs').tabs('close', n);
            }
        });
        break;
    case "closeother":
        var currtab_title = currentTab.panel('options').title;
        $.each(allTabtitle, function (i, n) {
            if (n != currtab_title && n != onlyOpenTitle) {
                $('#tabs').tabs('close', n);
            }
        });
        break;
    case "closeright":
        var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
        if (tabIndex == alltabs.length - 1){
            return false;
        }
        $.each(allTabtitle, function (i, n) {
            if (i > tabIndex) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
                }
            }
        });

        break;
    case "closeleft":
        var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
        if (tabIndex == 1) {
            return false;
        }
        $.each(allTabtitle, function (i, n) {
            if (i < tabIndex) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
                }
            }
        });

        break;
    case "exit":
        $('#mm').menu('hide');
        break;
}
}

function tabCloseEven() {
$('#mm').menu({
    onClick: function (item) {
        closeTab(item.id);
    }
});

return false;
}
$(function(){
tabCloseEven();
})
$(function(){
		$('#tabs').bind('contextmenu',function(e){
			e.preventDefault();
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		});
	});