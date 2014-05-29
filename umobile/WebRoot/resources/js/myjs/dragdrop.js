function beginDragDrop(){
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
							$('#div_imgCover').empty().append('<span>暂无封面</span>');
						}
					}else if($(source).parent()[0].id=='div_imgCover'){
						setTimeout(
						function(){
							$('#imgCoverId').val('');
							$('#div_imgCover').empty().append('<span>暂无封面</span>');
						},200);
						
					}
				}
			});
		};