//**************************LIST PICKER************************************************//
var createListPicker = function(conf) {
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
			valueInput:'',//取值填至何处
			displayType:'',//显示的取值字段
			displayInput:''//显示填至何处
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
			+'  <div class="modal-body" style="max-height:400px;">'
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
				if(data != null && data.length > 0){
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
								//htmlContent +='<td>' + item[conf.tbody[j]] + '</td>';
								var attr = conf.tbody[j];
								if(attr.indexOf('.') > 0){
									var attrs = attr.split('.');
									var value = '';
									for(var n=0, size = attrs.length; n<size; n++){
										if(value == ''){
											value = item[attrs[n]];
										}else{
											value = value[attrs[n]];
										}
										
										if(value == null){//如果值为空时,则返回
											value = '无';
											break;
										}
									}
									if(attrs[attrs.length - 1].indexOf('Time') == (attrs[attrs.length - 1].length - 4)){
										htmlContent +='<td>' + new Date(value).toLocaleString() + '</td>';
									}else{
										htmlContent +='<td>' + value + '</td>';
									}
								}else{
									if(attr.indexOf('Time') == (attr.length - 4)){
										htmlContent +='<td>' + new Date(item[attr]).toLocaleString() + '</td>';
									}else{
										htmlContent +='<td>' + (item[attr] == null ? '' : item[attr]) + '</td>';
									}
								}
							}
							
						}
						htmlContent +='</tr>';
					});
				}
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
		//是否需要单独的显示信息
		var isNeedDisplay = false;
		if(conf.displayType != '' && conf.displayInput != ''){
			isNeedDisplay = true;
		}
		//处理实际需要取的值
		if(conf.multiple){
			var result = "";
			var displayResult = '';
			$('#' + conf.modalId + ' .selectedItemList:checked').each(function(i, item){
				if(conf.valueType == 'ID' || conf.valueType == 'id'){
					result += $(item).val() + ",";
				}else{
					result += $(item).attr(conf.valueType) + ",";
				}
				
				
				if(isNeedDisplay){
					if(conf.displayType == 'ID' || conf.displayType == 'id'){
						displayResult += $(item).val() + ",";
					}else{
						displayResult += $(item).attr(conf.displayType) + ",";
					}
				}
			});
			
			if(result != ""){
				result = result.substring(0, result.length - 1);
				
				if(isNeedDisplay){
					displayResult = displayResult.substring(0, displayResult.length - 1);
				}
			}
			
			$('#' + conf.valueInput).val(result);
			
			if(isNeedDisplay){
				$('#' + conf.displayInput).val(displayResult);
			}
		}else{
			if(conf.valueType == 'ID' || conf.valueType == 'id'){
				$('#' + conf.valueInput).val($('#' + conf.modalId + ' .selectedItemList:checked').val());
			}else{
				$('#' + conf.valueInput).val($('#' + conf.modalId + ' .selectedItemList:checked').attr(conf.valueType));
			}
			
			if(isNeedDisplay){
				if(conf.displayType == 'ID' || conf.displayType == 'id'){
					$('#' + conf.displayInput).val($('#' + conf.modalId + ' .selectedItemList:checked').val());
				}else{
					$('#' + conf.displayInput).val($('#' + conf.modalId + ' .selectedItemList:checked').attr(conf.displayType));
				}
			}
		}
	});
	
	//多选时,添加全选事件
	$(document).delegate('#toggleSelectedItem' + conf.modalId, 'click', function(e) {
		var checked = $('#toggleSelectedItem' + conf.modalId).attr('checked');
		
		$('#' + conf.tableId + ' tr .selectedItemList:visible').each(function(i, item){
			if(checked == 'checked'){
				$(item).attr('checked', checked);
			}else{
				$(item).removeAttr('checked');
			}
			
		});
	});
}

/*
 * 此方法仅查看,单独传递url,且可减小页面大小
 * url 地址
 * title 标题  
 * thead 表头
 * tbody 所要取的数据  
 * width 宽度
 */
function listData(url, title, width, thead, tbody){
	if($('#listDataModal').length > 0 ){
		$('#listDataModal').remove();
	}
	var initHtml = 
		'<div id="listDataModal" class="modal fade">'
		+ '<div class="modal-content">'
		+'  <div class="modal-header">'
		+'    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
		+'    <h4 class="modal-title">' + title + '</h4>'
		+'  </div>'
		+'  <div class="modal-body" style="max-height:450px;">'
		+'      <article class="m-widget">'
		+' 			<form id="listDataForm" action="" method="post" class="m-form-blank"></form>'
		+'      </article>'
		+'  </div>'
		+'  <div class="modal-footer" style="width:' + (width - 20) +'px;">'
		+'    <span id="Result"></span>'
		+'    <button id="BtnClose" href="javascript:void(0);" class="btn btn-sm default" data-dismiss="modal" >关闭</button>';
	    +'	</div>'
	    +' </div>'
	    +'</div>';
	
	$(document.body).append(initHtml);
	
	$.post(url, function(data){
		var htmlContent = '<table id="listDataTable" class="m-table table-bordered table-hover">'
		+'    <thead>'
		+'      <tr>'
		+'        <th colspan="' + thead.length + '"><input id="searchListData" value="" class="form-control" placeholder="搜索"></th>'
		+'      </tr>'
		+'    </thead>';
		+'<thead><tr>';
		for(var i =0, len = thead.length; i<len; i++){
			htmlContent +='<th>' + thead[i] + '</th>';
		}

		htmlContent += '</tr></thead><tbody>';
		if(data != null && data.length > 0){
			$.each(data, function(i, item){
				htmlContent +='<tr>';
				for(var j =0, len = tbody.length; j<len; j++){
					var attr = tbody[j];
					if(attr.indexOf('.') > 0){
						var attrs = attr.split('.');
						var value = '';
						for(var n=0, size = attrs.length; n<size; n++){
							if(value == ''){
								value = item[attrs[n]];
							}else{
								value = value[attrs[n]];
							}
							
							if(value == null){//如果值为空时,则返回
								value = '无';
								break;
							}
						}
						//htmlContent +='<td>' + value + '</td>';
						if(attrs[attrs.length - 1].indexOf('Time') == (attrs[attrs.length - 1].length - 4)){
							htmlContent +='<td>' + new Date(value).toLocaleString() + '</td>';
						}else{
							htmlContent +='<td>' + value + '</td>';
						}
					}else{
						if(attr.indexOf('Time') == (attr.length - 4)){
							htmlContent +='<td>' + new Date(item[attr]).toLocaleString() + '</td>';
						}else{
							htmlContent +='<td>' + (item[attr] == null ? '' : item[attr]) + '</td>';
						}
					}
				}
				htmlContent +='</tr>';
			});
		}
		
		htmlContent += "</tbody></table>";

		$('#listDataForm').html(htmlContent);
		
		var margin = (window.screen.availWidth - width)/2;
		$('#listDataModal').css("margin-left", margin);
		$('#listDataModal').css("width", width + "px");
		$('#listDataModal').modal();
	});
}
//搜索
$(document).delegate('#searchListData', 'change', function(e) {
	$('#listDataTable tr').each(function(i, item){
		if($(item).children('td').length > 0){
			$(item).show();
		}
	});
	$('#listDataTable tr').each(function(i, item){
		if($(item).text().indexOf($('#searchListData').val()) < 0){
			if($(item).children('td').length > 0){
				$(item).hide();
			}
		}
	});
});
