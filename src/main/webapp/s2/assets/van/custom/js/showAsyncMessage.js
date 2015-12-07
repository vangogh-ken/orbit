function showAsyncMessage (msg){
	$('body').prepend(
    	'<div id="m-async-message" style="display:none;">'
		+  '<ul>'
		+    '<li>' + msg + '</li>'
		+  '</ul>'
		+'</div>'
    	);
    	
    	$.showMessage($('#m-async-message').html(), {
	        position: 'top',
	        size: '130',
	        fontSize: '20px'
	    });
}
//异步提交数据
function asyncSubmitForm(url, data){
	bootbox.animate(false);
	var box = bootbox.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
	
	$.post(url, data, function(){
		showAsyncMessage('操作成功');
		box.modal("hide");
	});
}