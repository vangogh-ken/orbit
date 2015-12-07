<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/meta.jsp"%>
<title>Dashboard</title>
</head>
<body>
<h1>Dashboard : ${ctx}</h1>
<p id="testTitle">环境配置表单</p>
<form id="testForm">
	<table border="1px solid">
		<thead><tr><th>名称</th><th>内容</th><th>名称</th><th>内容</th></tr></thead>
		<tbody>
		</tbody>
	</table>
</form>
<hr></hr>
<select id="formId" onchange="updateForm()">
	<option></option>
	<option value="HJPZ">环境配置</option>
	<option value="JDPZ">节点配置</option>
	<option value="CCPZ">存储配置</option>
</select>
<select id="method">
	<option value="GET">GET</option>
	<option value="POST">POST</option>
	<option value="PUT">PUT</option>
	<option value="DELETE">DELETE</option>
</select>
<button id="doBtn" onclick="doTest();">GO</button>
</body>

<%@include file="/common/footer.jsp"%>
<script type="text/javascript">
	function updateForm(){
		var basisSubstanceTypeId = $('#formId').val() == 'HJPZ' ? '5b959b67-91b1-11e5-bf87-10604b6fd31b' : $('#formId').val() == 'JDPZ' ? '6fe2b956-91b1-11e5-bf87-10604b6fd31b' : '74ef1aed-91b1-11e5-bf87-10604b6fd31b';
		$('#testTitle').text($('#formId').val() + '配置');
		$.ajax({
			url: '${ctx}/basis/basis-attribute-to-add-attribute?basisSubstanceTypeId=' + basisSubstanceTypeId,
			type: 'POST',
			dataType:'json',
			success: function(data){
				var basisAttrbutes = data.hasAddData;
				//$('#testForm tbody tr').remove();
				var html = '';
				$.each(basisAttrbutes, function(i, item){
					if(i%2 == 0){
						html += '<tr><td>' + item.attributeName + '</td><td><input name="' + item.attributeColumn + '" type="text"></td>';
					}else{
						html += '<td>' + item.attributeName + '</td><td><input name="' + item.attributeColumn + '" type="text"></td></tr>';
					}
				});
				
				html += '<tr><td>URL</td><td><input id="URL" type="text" value="' + ($('#formId').val() == 'HJPZ' ? '/environments' : $('#formId').val() == 'JDPZ' ? '/nodehosts' : '/storageschemes') + '"></td></tr>';
				$('#testForm tbody').html(html);
			},
			error: function(data){
				
			}
		});
		//$('#' + $('#formId').val()).show();
	}
	
	function doTest(){
		$.ajax({
			url: $('#testForm #URL').val() + ($('#method').val() == 'GET' ? '' : '?_method=' + $('#method').val()),
			type: $('#method').val() == 'GET' ? 'GET' : 'POST',
			data: $('#testForm').serialize(),
			success: function(data){
				if($('#method').val() == 'GET' && $('#testForm #URL').val().split('/').length == 3){
					$.each($('#testForm input'), function(n, input){
						for(a in data){
							if(a == $(input).attr('name')){
								$(input).val(data[a]);
							}
						}
					});
				}
			},
			error: function(data){
				
			}
		});
	}
	
	$(document).delegate('#doBtn', 'click', function(event){
		
	});
</script>
</html>