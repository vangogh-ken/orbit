$(function(){
	var initHtml = '<div class="modal fade" id="completeTaskModal" aria-hidden="true">'
	+ '<div class="modal-content"> '
	+ '	<div class="modal-header"> '
	+ '		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button> '
	+ '		<h4 class="modal-title">发送任务</h4> '
	+ '		</div> '
	+ '		<div class="modal-body"> '
	+ '			<article class="m-widget"> '
	+ '			<form id="completeTaskForm" action="${ctx}/bpm/bpm-business-transact-next" method="post" class="m-form-blank"></form> '
	+ '			</article> '
	+ '		</div> '
	+ '		<div class="modal-footer"> '
	+ '			<button type="button" class="btn btn-sm default" data-dismiss="modal">关闭</button> '
	+ '			<button type="button" class="btn btn-sm red" onclick="completeTask();">发送</button> '
	+ '		</div> '
	+ '	</div> '
	+ '</div> ';
	
	$(document.body).append(initHtml);
	
});

/**
 * 关闭页面,保持刷新
 */		
function closeWindow() {
	var parent = window.opener.location;
	var href = parent.href;
	//处理掉#,避免页面不刷新
	if (href.indexOf('#') >= 0) {
		href = href.substring(0, href.length - 1);
	}

	parent.href = href;
	window.close();
}

var hasSaved = 0;//给是否保存或者下发一个标记，只有经过此，以后 的数据更改才触发校验

function completeTask() {
	var activityId = $('#completeTaskForm').serialize();
	if (activityId == '' || activityId == 'undifined') {
		return false;
	}
	hasSaved++;
	if ($("#basisSubstanceForm").valid()) {
		$('#completeTaskModal').modal('hide');
		bootbox.animate(false);
		var box = bootbox
				.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
		$.post('../bpm/workspance-task-done-complete-task.do', $('#basisSubstanceForm').serialize() + '&' + activityId, function(data, status) {
			if (status = 'success') {
				var parent = window.opener.location;
				var href = parent.href;
				//处理掉#,避免页面不刷新
				if (href.indexOf('#') >= 0) {
					href = href.substring(0, href.length - 1);
				}

				parent.href = href;
				window.close();
			} else {
				alert('发送任务失败');
			}
		});
	}else{
		$('#completeTaskModal').modal('hide');
	}
}
/**
 * 获取下发节点信息
 */
function getNextActivityDetails(id) {
	var htmlContent = "<table class='m-table table-bordered table-hover'>";
	htmlContent += "<thead><tr>";
	htmlContent += "<th width='10' class='m-table-check'>";
	htmlContent += '<th>下个任务节点名称</th><th style="min-width:200px;">候选人</th></tr></thead><tbody>';
	$.ajax({
				url : '../bpm/bpm-business-next-activity.do?taskId=' + id,
				type : 'post',
				dataType : 'json',
				async : true,
				success : function(data) {
					var type = '';
					for ( var activity in data) {
						if (activity == 'type') {
							type = data[activity];
						}
					}
					var count = 0;
					for ( var activity in data) {
						if (activity != 'type') {
							var details = data[activity].split('<!!>');
							if (details.length == 2) {
								htmlContent += "<input type='hidden' name='activityId' value='"
										+ activity + "'/>";
								if(activity.indexOf('gateway') > -1){
									continue;
								}
								htmlContent += "<tr><td>";
								if (type == 'userTask') {
									htmlContent += "<input class='selectedItemActivity a-check' type='radio' name='activityId' value='"
											+ activity
											+ "' checked='checked'/>";
								} else if (type == 'exclusiveGateway') {
									htmlContent += "<input class='selectedItemActivity a-check' type='radio' name='activityId' value='"
											+ activity + "'/>";
								} else if (type == 'inclusiveGateway') {
									htmlContent += "<input class='selectedItemActivity a-check' type='checkbox' name='activityId' value='"
											+ activity
											+ "' checked='checked'/>";
								} else if (type == 'parallelGateway') {
									htmlContent += "<input class='selectedItemActivity a-check' type='checkbox' name='activityId' value='"
											+ activity
											+ "' checked='checked' disabled='disabled'/>";
								}else if (type == 'endEvent') {
									htmlContent += "<input class='selectedItemActivity a-check' type='radio' name='activityId' value='"
										+ activity
										+ "' checked='checked' />";
								}
								
								htmlContent += "</td>";
								htmlContent += "<td colspan='3'>" + details[1] + "</td></tr>";
								
							} else if (details.length == 4) {
								htmlContent += "<tr><td>";
								if (type == 'userTask') {
									htmlContent += "<input class='selectedItemActivity a-check' type='radio' name='activityId' value='"
											+ activity
											+ "' checked='checked'/>";
								} else if (type == 'exclusiveGateway') {
									//if(count == 1){//如果是排他网关，则第一个选项默认被选中
									if(details[1].indexOf('_同意') > 0){//如果包含同意，则默认选中
										htmlContent += "<input class='selectedItemActivity a-check' type='radio' name='activityId' value='"
											+ activity + "' checked='checked'/>";
									}else{
										htmlContent += "<input class='selectedItemActivity a-check' type='radio' name='activityId' value='"
											+ activity + "'/>";
									}
									
								} else if (type == 'inclusiveGateway') {
									htmlContent += "<input class='selectedItemActivity a-check' type='checkbox' name='activityId' value='"
											+ activity
											+ "' checked='checked'/>";
								} else if (type == 'parallelGateway') {
									htmlContent += "<input class='selectedItemActivity a-check' type='checkbox' name='activityId' value='"
											+ activity
											+ "' checked='checked' disabled='disabled'/>";
								}
								
								htmlContent += "</td>";
								htmlContent += "<td>" + details[1] + "</td>";
								htmlContent += "<td>" + details[3] + "</td>";
								htmlContent += "</tr>";
							}
						}
						
						count++;
					}
					/**
					if (type == 'endEvent') {
						htmlContent += "<tr><td>";
						htmlContent += "<input class='selectedItemActivity a-check' type='radio' checked='checked'>";
						htmlContent += "</td>";
						htmlContent += "<td colspan='3'>流程结束</td>";
						htmlContent += "</tr>";
					}
					**/
					htmlContent += "</tbody></table>";
					$('#completeTaskForm').html(htmlContent);
				},
				error : function() {
				}
			});

	var margin = (window.screen.availWidth - 800) / 2;
	$('#completeTaskModal').css("margin-left", margin);
	$('#completeTaskModal').css("margin-top", 100);
	$('#completeTaskModal').css("width", "850px");
	$('#completeTaskModal').modal();
}

/**
 * 数据改变之后的校验
 */
$(document).delegate('#basisSubstanceForm input,textarea,select', 'change', function(e){
	if(hasSaved > 0){
		$("#basisSubstanceForm").valid();
	}
});
/**
 * 异步保存数据, 也有验证
 */
$(function() {
	$('#basisSubstanceForm').validate({
		onsubmit:true,
		onfocusout:false,
		onkeyup:false,
		submitHandler : function(form) {
			hasSaved++;
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
			
			$.ajax({
				url: '../basis/basis-substance-done-prime-substance.do',
				type: 'post',
				dataType: 'text',
				data: $('#basisSubstanceForm').serialize(),
				success: function(data){
					var parent = window.opener.location;
					var href = parent.href;
					if (href.indexOf('#') >= 0) {
						href = href.substring(0, href.length - 1);
					}

					parent.href = href;
					window.close();
				},
				error:function(){
					alert('ERROR 请联系管理员！');
				}
			});
		},
		invalidHandler:function(form, validator){
			return false;
		},
		errorClass : 'validate-error'
	});
});


//隐藏第一个栏之后的数据
/*
$(function(){
	$('.tr-title:not(:first)').nextUntil('.tr-title').hide();
	$('.tr-title').click(function(){
		$(this).nextUntil('.tr-title').toggle(300);
	});
});
*/