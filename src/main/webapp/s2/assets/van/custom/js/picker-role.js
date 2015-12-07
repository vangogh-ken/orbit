
var createRolePicker = function(conf) {
	if (!conf) {
		conf = {
			modalId: 'rolePicker',
			multiple: false,
			url: '${ctx}/role/role-all.do'
		};
	}
	//如果还没有ID为conf.modalId的元素
	if ($('#' + conf.modalId).length == 0) {
		var initHtml = 
			'<div id="' + conf.modalId + '" class="modal fade">'
			+ '<div class="modal-content">'
			+'  <div class="modal-header">'
			+'    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+'    <h4 class="modal-title">选择角色</h4>'
			+'  </div>'
			+'  <div class="modal-body" style="max-height:400px;">'
			+'      <article class="m-widget">'
			+'  <table id="' + conf.modalId +'Grid" class="m-table table-bordered table-hover">'
			+'    <thead>'
			+'      <tr>'
			+'        <th colspan="4"><input id="searchRole" value="" class="form-control" placeholder="搜索"></th>'
			+'      </tr>'
			+'    </thead>'
			+'    <thead>'
			+'      <tr>'
			+'        <th width="60" class="m-table-check">选择&nbsp;</th>'
			+'        <th>角色名称</th>'
			+'        <th>角色KEY</th>'
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
			+'    	<a id="' + conf.modalId +'BtnSelect" href="#" class="btn btn-sm red">选择</a>'
			+'  	</div>'
			+'  </div>'
			+'</div>';
		
			$(document.body).append(initHtml);
	}

	$(document).delegate('.'+conf.modalId +' .add-on', 'click', function(e) {
		$('#' + conf.modalId).data(conf.modalId, $(this).parent());
		
		var margin = (window.screen.availWidth - 400)/2;
		$('#' + conf.modalId).css("margin-left", margin);
		$('#' + conf.modalId).css("width","450px");
		$('#' + conf.tableId).css("width","400px");
		$('#' + conf.modalId).modal();
		$.ajax({
			url: conf.url,
			type:'post',
			dataType:'json',
			success: function(data) {
				var html = '';
				$.each(data["roleAll"], function(i, item){
					html +='<tr>';
					if(conf.multiple){
						html +='<td><input type="checkbox" class="selectedItem" name="selectedItem" value="' + item.id + '" roleName="'+item.roleName+'"></td>';
					}else{
						html +='<td><input type="radio" class="selectedItem" name="selectedItem" value="' + item.id + '" roleName="'+item.roleName+'"></td>';
					}
					
					html +='<td>'+ item.roleName + '</td>';
					html +='<td>'+ item.roleKey + '</td>';
					html +='</tr>';
				});
				
				$('#' + conf.modalId + 'Body').html(html);
			}
		});
	});
	
	$(document).delegate('#searchRole', 'change', function(e) {
		$('#' + conf.modalId + 'Grid tr').each(function(i, item){
			if($(item).children('td').length > 0){
				$(item).show();
			}
		});
		$('#' + conf.modalId + 'Grid tr').each(function(i, item){
			if($(item).text().indexOf($('#searchRole').val()) < 0){
				if($(item).children('td').length > 0){
					$(item).hide();
				}
			}
		});
	});

	$(document).delegate('#' + conf.modalId + 'BtnSelect', 'click', function(e) {
		$('#' + conf.modalId).modal('hide');
		
		if(conf.multiple){
			var result = "";
			$('.selectedItem:checked').each(function(i, item){
				if(conf.valType == 'roleName'){
					result += $(item).attr('roleName') + ",";
				}else if(conf.valType == 'id'){
					result += $(item).val() + ",";
				}
				
			});
			
			if(result != ""){
				result = result.substring(0, result.length - 1);
			}
			
			$('#' + conf.valInput).val(result);
		}else{
			if(conf.valType == 'roleName'){
				$('#' + conf.valInput).val($('.selectedItem:checked').attr('roleName'));
			}else if(conf.valType == 'id'){
				$('#' + conf.valInput).val($('.selectedItem:checked').val());
			}
		}
		
	});
}
