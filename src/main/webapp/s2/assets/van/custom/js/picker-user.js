
var createUserPicker = function(conf) {
	if (!conf) {
		conf = {
			modalId: 'userPicker',
			multiple: false,
			url: '${ctx}/user/user-all.do'
		};
	}
	//如果还没有ID为conf.modalId的元素
	if ($('#' + conf.modalId).length == 0) {
		var initHtml = 
			'<div id="' + conf.modalId + '" class="modal fade">'
			+ '<div class="modal-content">'
			+'  <div class="modal-header">'
			+'    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+'    <h4 class="modal-title">选择用户</h4>'
			+'  </div>'
			+'  <div class="modal-body" style="max-height:400px;">'
			+'      <article class="m-widget">'
			+'  <table id="' + conf.modalId +'Grid" class="m-table table-bordered table-hover">'
			+'    <thead>'
			+'      <tr>'
			+'        <th colspan="4"><input id="searchUser" value="" class="form-control" placeholder="搜索"></th>'
			+'      </tr>'
			+'    </thead>'
			+'    <thead>'
			+'      <tr>'
			+'        <th width="60" class="m-table-check">选择&nbsp;</th>'
			+'        <th>姓名</th>'
			+'        <th>职位</th>'
			+'        <th>部门</th>'
			+'      </tr>'
			+'    </thead>'
			
			+'    <tbody id="' + conf.modalId +'Body">'
			+'    </tbody>'
			+'  </table>'
			+'      </article>'
			+'  </div>'
			+'  	<div class="modal-footer">'
			+'    	<span id="' + conf.modalId +'Result"></span>'
			+'    	<a id="' + conf.modalId +'BtnClose" href="#" class="btn btn-sm default" data-dismiss="modal">关闭</a>'
			+'    	<a id="' + conf.modalId +'BtnSelect" href="#" class="btn btn-sm red">确认</a>'
			+'  	</div>'
			+'  </div>'
			+'</div>';
		
			$(document.body).append(initHtml);
	}

	$(document).delegate('.'+conf.modalId +' .add-on', 'click', function(e) {
		$('#' + conf.modalId).data(conf.modalId, $(this).parent());
		
		var margin = (window.screen.availWidth - 600)/2;
		$('#' + conf.modalId).css("margin-left", margin);
		$('#' + conf.modalId).css("width","650px");
		$('#' + conf.modalId).modal();
		$.ajax({
			url: conf.url,
			type:'post',
			dataType:'json',
			success: function(data) {
				var html = '';
				$.each(data, function(i, item){
					html +='<tr>';
					if(conf.multiple){
						html +='<td><input type="checkbox" class="selectedItemUser" name="selectedItem" value="' + item.id + '" displayName="'+item.displayName+'"></td>';
					}else{
						html +='<td><input type="radio" class="selectedItemUser" name="selectedItem" value="' + item.id + '" displayName="'+item.displayName+'"></td>';
					}
					
					html +='<td>'+ item.displayName + '</td>';
					var position = (item.position == null || item.position == '') ? '空': item.position.positionName;
					html +='<td>'+ position + '</td>';
					var orgName = (item.orgEntity == null || item.orgEntity == '') ? '空': item.orgEntity.orgEntityName;
					html +='<td>'+ orgName + '</td>';
					html +='</tr>';
				});
				
				$('#' + conf.modalId + 'Body').html(html);
			}
		});
	});
	
	$(document).delegate('#searchUser', 'change', function(e) {
		$('#' + conf.modalId + 'Grid tr').each(function(i, item){
			if($(item).children('td').length > 0){
				$(item).show();
			}
		});
		$('#' + conf.modalId + 'Grid tr').each(function(i, item){
			if($(item).text().indexOf($('#searchUser').val()) < 0){
				if($(item).children('td').length > 0){
					$(item).hide();
				}
			}
		});
	});

	$(document).delegate('#' + conf.modalId + 'BtnSelect', 'click', function(e) {
		$('#' + conf.modalId).modal('hide');
		//处理实际需要取的值
		if(conf.multiple){
			var result = "";
			$('.selectedItemUser:checked').each(function(i, item){
				if(conf.valType == 'displayName'){
					result += $(item).attr('displayName') + ",";
				}else if(conf.valType == 'id'){
					result += $(item).val() + ",";
				}
				
			});
			
			if(result != ""){
				result = result.substring(0, result.length - 1);
			}
			
			$('#' + conf.valInput).val(result);
		}else{
			if(conf.valType == 'displayName'){
				$('#' + conf.valInput).val($('.selectedItemUser:checked').attr('displayName'));
			}else if(conf.valType == 'id'){
				$('#' + conf.valInput).val($('.selectedItemUser:checked').val());
			}
		}
		//如果配备有需要显示的ID
		if(conf.display != null && conf.display != undefined){
			var result = "";
			$('.selectedItemUser:checked').each(function(i, item){
				result += $(item).attr('displayName') + ",";
				
			});
			
			if(result != ""){
				result = result.substring(0, result.length - 1);
			}
			
			$('#' + conf.display).val(result);
		}
		
	});
}
