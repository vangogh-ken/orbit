$(function(){
	var clsId = $('#clsId').val();
	var controllerId = $('#controllerId').val();
	var initHtml = '<div class="modal fade" id="importDataByCsvModal" aria-hidden="true">'
	+ '<div class="modal-content"> '
	+ '	<div class="modal-header"> '
	+ '		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> '
	+ '		<h4 class="modal-title">导入数据(*.csv)</h4> '
	+ '		</div> '
	+ '		<div class="modal-body"> '
	+ '			<article class="m-widget"> '
	+ '			<form id="importDataByCsvForm" name="importDataByCsvForm" action="common-business-import-save.do" method="post" enctype="multipart/form-data" class="m-form-blank">'
	+ '			  <input type="hidden" name="clsId" value="'+clsId+'">'
	+ '			  <input type="hidden" name="controllerId" value="'+controllerId+'">'
	+ '			  <table class="table-input">'
	+ '			  <tbody>'
	+ '			  <tr>'
	+ '			    <td>'
	+ '			    <label for="muiltFile" class="td-label">上传数据文件(*.csv)</label>'
	+ '			    </td>'
	+ '			    <td>'
	+ '			    <input id="muiltFile" name="muiltFile" type="file" class="required"> '
	+ '			    </td>'
	+ '			  </tr>'
	+ '			  </tbody>'
	+ '			  </table>'
	+ '			</form> '
	+ '			</article> '
	+ '		</div> '
	+ '		<div class="modal-footer"> '
	+ '			<button type="button" class="btn btn-sm default" data-dismiss="modal">关闭</button> '
	+ '			<button type="button" class="btn btn-sm red" onclick="importDataByCsvSubmit();">确定</button> '
	+ '		</div> '
	+ '	</div> '
	+ '</div> ';
	
	$(document.body).append(initHtml);
	
	$("input:file").not('[data-no-uniform="true"],#uniform-is-ajax').uniform({
        fileDefaultHtml: '还未选择文件',
        fileButtonHtml: '选择文件'
    });
});

function importDataByCsv(){
	var margin = (window.screen.availWidth - 600) / 2;
	$('#importDataByCsvModal').css("margin-left", margin);
	$('#importDataByCsvModal').css("width", "650px");
	$('#importDataByCsvModal').modal();
}

function importDataByCsvSubmit(){
	var muiltFile = $('#muiltFile').val();
	$('#importDataByCsvModal small').remove();
	if(muiltFile == '' || muiltFile.indexOf('.csv')<0){
		$('#muiltFile').after("<small style='color:red;'>上传CSV文件</small>");
		return false;
	}else{
		$('#importDataByCsvModal small').remove();
		document.importDataByCsvForm.submit();
	}
}

