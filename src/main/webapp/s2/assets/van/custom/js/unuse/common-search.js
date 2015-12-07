//通用业务高级查询
var createCommonSearch = function(conf) {
	if (!conf) {
		conf = {
			title:'xx', //标题
			modalId: 'xx',//modalID
			formId:'xx',//提交的form
			sumitAction:'xx',//提交的action
			tableId:'xx',//表ID
			thead:{},//列表头 数组
			tbody:{},//字段数据 数组
			multiple: false,//是否多选
			canSelect:false,//是否可选择
			valueType:'',//需要的取值字段
			valueInput:''//取值填至何处
		};
	}

	if ($('#' + conf.modalId).length == 0) {
		var initHtml = 
			'<div id="' + conf.modalId + '" class="modal fade">'
			+ '<div class="modal-content">'
			+'  <div class="modal-header">'
			+'    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+'    <h4 class="modal-title">' + conf.title + '</h4>'
			+'  </div>'
			+'  <div class="modal-body">'
			+'      <article class="m-widget">'
			+' 			<form id="'+conf.formId+'" action="' + conf.sumitAction + '" method="post" class="m-form-blank"></form>'
			+'      </article>'
			+'  </div>'
			+'  <div class="modal-footer" style="width:800px;">'
			+'    <span id="Result"></span>'
			+'    <button id="BtnClose" href="javascript:void(0);" class="btn btn-sm default" data-dismiss="modal">关闭</button>';
			
		if(conf.canSelect){
				initHtml +='<button id="' + conf.modalId +'BtnSelect" href="javascript:void(0);" class="btn btn-sm red">选择</button>'
		}
			
		initHtml +='</div></div></div>';
		
			$(document.body).append(initHtml);
	}
	

	$(document).delegate('.'+conf.modalId +'-add-on', 'click', function(e) {
		$('#' + conf.modalId).data(conf.modalId, $(this).parent());
		$('#' + conf.modalId).modal();

		var dataUrl = $(this).attr('urlAttr');
		var htmlContent = '<table id="' + conf.tableId + '" class="m-table table-bordered table-hover">'
						+'    <thead>'
						+'      <tr>'
						+'        <th colspan="' + conf.thead.length + '"><input id="searchList' + conf.tableId + '" value="" class="form-control" placeholder="搜索"></th>'
						+'      </tr>'
						+'    </thead>';
		htmlContent += '<thead><tr>';

		for(var i =0, len = conf.thead.length; i<len; i++){

			if(conf.thead[i] == 'ID' || conf.thead[i] == 'id'){
				//如果可多选
				if(conf.multiple){
					htmlContent += '<th width="10" class="m-table-check">';
					htmlContent += '<input type="checkbox" id="toggleSelectedItem' + conf.modalId +'"/>';
					htmlContent += '</th>';
				}else{
					htmlContent += '<th width="10" class="m-table-check"></th>';
				}
			}else{
				htmlContent +='<th>' + conf.thead[i] + '</th>';
			}
		}

		htmlContent += '</tr></thead><tbody>';

		$.ajax({
			url: dataUrl,
			type:'post',
			dataType:'json',
			success: function(data) {
				$.each(data, function(i, item){
					htmlContent += '<tr>';
					for(var j =0, len = conf.tbody.length; j<len; j++){
						if(conf.tbody[j] == 'ID' || conf.tbody[j] == 'id'){
							if(conf.multiple){
								htmlContent +='<td><input type="checkbox" class="selectedItemList" value="' + item[conf.tbody[j]] + '" name="selectedItemList' + conf.modalId + '" ';
								for(var i=0, len=conf.tbody.length; i<len; i++){
									if(conf.tbody[i] != 'id' && conf.tbody[i] != 'ID'){
										htmlContent += conf.tbody[i] + '="' + item[conf.tbody[i]] + '" ';
									}
								}
								
								htmlContent +='></td>';
							}else{
								htmlContent +='<td><input type="radio" class="selectedItemList" value="' + item[conf.tbody[j]] + '" name="selectedItemList' + conf.modalId + '" ';
								for(var i=0, len=conf.tbody.length; i<len; i++){
									if(conf.tbody[i] != 'id' && conf.tbody[i] != 'ID'){
										htmlContent += conf.tbody[i] + '="' + item[conf.tbody[i]] + '" ';	
									}
								}
								
								htmlContent +='></td>';
							}
						}else{
							htmlContent +='<td>' + item[conf.tbody[j]] + '</td>';
						}
						
					}
					htmlContent +='</tr>';
				});
				
				htmlContent += "</tbody></table>";

				$('#' + conf.formId).html(htmlContent);
				
				var margin = (window.screen.availWidth - 800)/2;
				$('#' + conf.modalId).css("margin-left", margin);
				$('#' + conf.modalId).css("width","850px");
				$('#' + conf.tableId).css("width","800px");
				$('#' + conf.modalId).modal();
			}
		});
		
	});
	
	//搜索事件
	$(document).delegate('#searchList' + conf.tableId, 'change', function(e) {
		
		$('#' + conf.tableId + ' tr').each(function(i, item){
			if($(item).children('td').length > 0){
				$(item).show();
			}
		});
		$('#' + conf.tableId + ' tr').each(function(i, item){
			if($(item).text().indexOf($('#searchList' + conf.tableId).val()) < 0){
				if($(item).children('td').length > 0){
					$(item).hide();
				}
			}
		});
	});
	
	//点击选择事件,返回最终的值
	$(document).delegate('#' + conf.modalId + 'BtnSelect', 'click', function(e) {
		$('#' + conf.modalId).modal('hide');
		//处理实际需要取的值
		if(conf.multiple){
			var result = "";
			$('.selectedItemList:checked').each(function(i, item){
				result += $(item).attr(conf.valueType) + ",";
			});
			
			if(result != ""){
				result = result.substring(0, result.length - 1);
			}
			
			$('#' + conf.valueInput).val(result);
		}else{
			$('#' + conf.valueInput).val($('.selectedItemList:checked').attr(conf.valueType));
		}
	});
	
	//多选时,添加全选事件
	$(document).delegate('#toggleSelectedItem' + conf.modalId, 'click', function(e) {
		var checked = $('#toggleSelectedItem' + conf.modalId).attr('checked');
		
		$('#' + conf.tableId + ' tr .selectedItemList').each(function(i, item){
			if(checked == 'checked'){
				$(item).attr('checked', checked);
			}else{
				$(item).removeAttr('checked');
			}
			
		});
	});
}
