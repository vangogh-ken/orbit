//电子词典值 在表格中
$(function(){
	//初始调用
	vAttrDblClick();
	initSelect2();
});

function initSelect2(){
	$('select:not(.dictionary)').each(function(i, item){
		var $item = $(item);
		if($item.children('option').length > 10){
			$item.select2({
				   //data.text: $(this).val(),
			       placeholder: "请选择正确的值",
			       allowClear: true
			});
		}
	});/**/
	
	//$('.bs-select').each(function(i, item){
	/*$('td select:not(.dictionary)').each(function(i, item){
		var $item = $(item);
		if($item.children('option').length > 5){
			$item.addClass('bs-select');
			$item.attr('data-live-search', 'true');
			$item.selectpicker({
				iconBase: 'fa',
		        tickIcon: 'fa-check'
			});
		}
	});
	**/
}

function vAttrDblClick(){
	//$(document).delegate('.dictionary:not(.readonly)','dblclick', function(e){//修改为单击
	$(document).delegate('.dictionary:not(select)','click', function(e){
		var $item = $(this);
		if($item.attr('readonly') == 'readonly' || $item.attr('disabled') == 'disabled'){
			return;
		}
		
		var orValue = $item.val();//原值
		var vAttrId = $item.attr('vAttrId') == undefined ? '' : $item.attr('vAttrId');//属性ID
		var vClsId = $item.attr('vClsId') == undefined ? '' : $item.attr('vClsId');//类型ID
		var vColumn = $item.attr('vColumn') == undefined ? '' : $item.attr('vColumn');//字段
		var vFilterId = $item.attr('vFilterId') == undefined ? '' : $item.attr('vFilterId');
		
		var fieldId = $item.attr('fieldId') == undefined ? '' : $item.attr('fieldId');//用于forBox字段填报信息

		if(vAttrId == '' && (vClsId == '' || vColumn == '')){
			return false;
		}
		var id = $item.attr('id');
		var name = $item.attr('name');
		var clazz = $item.attr('class') == undefined ? '' : $item.attr('class');
		var style = $item.attr('style') == undefined ? '' : $item.attr('style');
		var multiple = $item.attr('multiple') == undefined ? '' : 'multiple';
		/**
		var url = '../dic/value-dictionary-byvattrid.do?vAttrId=' + vAttrId + '&vClsId=' + vClsId + '&vColumn=' + vColumn + '&vFilterId=' + vFilterId;
		$item.select2({
			 placeholder: "Search for a movie",
	            minimumInputLength: 1,
	            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
	                url: url,
	                dataType: 'json',
	                data: function (term, page) {
	                    return {
	                        q: term, // search term
	                        //page_limit: 10,
	                        //apikey: "ju6z9mjyajq2djue3gbvv26t" // please do not use so this example keeps working
	                    };
	                },
	                results: function (data, page) { // parse the results into the format expected by Select2.
	                    // since we are using custom formatting functions we do not need to alter remote JSON data
	                    return {
	                        results: data.movies
	                    };
	                }
	            }
		});
		**/
		var html = '<select id="' + id + '" name="'+ name +'" class="';
		html += clazz + '" vAttrId="' + vAttrId + '" vClsId="' + vClsId + '" vColumn="';
		html += vColumn + '" vFilterId="' + vFilterId + '" style="' + style + '" ' + multiple + ' fieldId="' + fieldId + '">';
		if(orValue == ''){
			html += '<option value=""></option>';
		}
		$.post('../dic/value-dictionary-byvattrid.do?vAttrId=' + vAttrId + '&vClsId=' + vClsId + '&vColumn=' + vColumn + '&vFilterId=' + vFilterId, function(data){
			$.each(data, function(n, ele){
				if(orValue == ele.valueContent){
					html += '<option value="'+ ele.valueContent +'" selected>';
				}else{
					html += '<option value="'+ ele.valueContent +'">';
				}
				html += ele.valueContent;
				html += '</option>';
			});
			html += '</select>';
			var $parent = $('td:has(#'+id+')');
			$parent.html(html);
			//SELECT2
			if($parent.children('#'+id).children('option').length > 7){
				$parent.children('#'+id).select2({
			       placeholder: "请选择正确的值",
			       allowClear: true
				});
				/*var $item = $parent.children('#'+id);
				if($item.children('option').length > 5){
					$item.addClass('bs-select');
					$item.attr('data-live-search', 'true');
					$item.selectpicker({
						iconBase: 'fa',
				        tickIcon: 'fa-check'
					});
				}
				*/
			}
			
			//vAttrChange();//相互调用,取消此提高效率
		});
	});
}

//电子词典获取值,使用属性ID或者是类型ID与字段名的组合
/**
function vAttrDblClick(){
	//$(document).delegate('.dictionary:not(.readonly)','dblclick', function(e){//修改为单击
	$(document).delegate('.dictionary:not(select)','click', function(e){
		var $item = $(this);
		
		if($item.attr('readonly') == 'readonly' || $item.attr('disabled') == 'disabled'){
			return;
		}
		
		var orValue = $item.val();//原值
		var vAttrId = $item.attr('vAttrId') == undefined ? '' : $item.attr('vAttrId');//属性ID
		var vClsId = $item.attr('vClsId') == undefined ? '' : $item.attr('vClsId');//类型ID
		var vColumn = $item.attr('vColumn') == undefined ? '' : $item.attr('vColumn');//字段
		var vFilterId = $item.attr('vFilterId') == undefined ? '' : $item.attr('vFilterId');

		if(vAttrId == '' && (vClsId == '' || vColumn == '')){
			return false;
		}
		var id = $item.attr('id');
		var name = $item.attr('name');
		var clazz = $item.attr('class');
		var style = $item.attr('style');
		var html = '<select id="' + id + '" name="'+ name +'" class="' + clazz + '" vAttrId="' + vAttrId + '" vClsId="' + vClsId + '" vColumn="' + vColumn + '" vFilterId="' + vFilterId + '" style="' + style + '">';
		//var html = '<select id="' + id + '" name="'+ name +'" class="' + clazz + '" vAttrId="' + vAttrId + '">';
		if(orValue == ''){
			html += '<option value=""></option>';
		}
		$.post('../dic/value-dictionary-byvattrid.do?vAttrId=' + vAttrId + '&vClsId=' + vClsId + '&vColumn=' + vColumn + '&vFilterId=' + vFilterId, function(data){
			$.each(data, function(n, ele){
				if(orValue == ele.valueContent){
					html += '<option value="'+ ele.valueContent +'" selected>';
				}else{
					html += '<option value="'+ ele.valueContent +'">';
				}
				html += ele.valueContent;
				html += '</option>';
			});
			html += '</select>';
			var $parent = $('td:has(#'+id+')');
			$parent.html(html);
			//vAttrChange();//相互调用,取消此提高效率
		});
	});
}
**/
//电子词典回复原值
function vAttrChange(){
	$(document).delegate('.dictionary:not(.readonly)','change',function(e){
		var $item = $(this);
		var orValue = $item.val();//原值
		var vAttrId = $item.attr('vAttrId');//属性ID
		if(vAttrId == ''){
			return false;
		}
		var id = $item.attr('id');
		var name = $item.attr('name');
		var clazz = $item.attr('class');
		var html = '<input id="' + id + '" name="'+ name +'" class="' + clazz + '" vAttrId="' + vAttrId + '" value="'+orValue+'">';
		var $parent = $('td:has(#'+id+')');
		$parent.html(html);
		vAttrDblClick();//相互调用
	});
}