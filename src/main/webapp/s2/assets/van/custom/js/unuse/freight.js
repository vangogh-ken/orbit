//货运js
//拼接Html内容 > 确定可以添加  > 添加form值Body > 类型选择List > validate
function addMaintain(){
	if($('#maintainModal').length > 0){
		$('#maintainModal').remove();
	}
	var html = '';
	html += '<div id="maintainModal" class="modal fade" aria-hidden="true">'
		 +'<div class="modal-content">'
		 +'<div class="modal-header">'
		 +'		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>'
		 +'		<h4 class="modal-title">新增操作范围</h4>'
		 +'	</div>'
		 +'	<div class="modal-body">'
		 +'		<article class="m-widget">'
		 +'		<form id="maintainForm" action="" method="post" class="m-form-blank" onsubmit="return false;">'
		 +'			<input id="freightOrderId" type="hidden" value="">'
		 +'			<input id="parentMaintainId" type="hidden" value="">'
		 +'			<input id="freightMaintainTypeId" type="hidden" value="">'
		 +'			<table class="table-input">'
		 +'				<tbody>'
		 +'					<tr>'
		 +'						<td style="width:200px;">'
		 +'							<label class="td-label" for="maintainName">操作范围名称</label>'
		 +'						</td>'
		 +'						<td>'
		 +'							<div class="input-icon listPicker right">'
		 +'							  <i class="fa fa-user maintainTypeModal-add-on" urlAttr="fre-maintain-type-all.do" style="cursor: pointer;"></i>'
		 +'							  <input type="text" id="freightMaintainTypeName" class="form-control">'
		 +'						    </div>'
		 +'						</td>'
		 +'					</tr>'
		 +'					<tr>'
		 +'						<td>'
		 +'							<label class="td-label" for="displayIndex">执行顺序</label>'
		 +'						</td>'
		 +'						<td>'
		 +'							<input id="displayIndex" type="text" name="displayIndex" value="" '
		 +'								class="form-control required number" >'
		 +'						</td>'
		 +'					</tr>'
		 +'					<tr>'
		 +'						<td>'
		 +'							<label class="td-label" for="descn">基本说明</label>'
		 +'						</td>'
		 +'						<td>'
		 +'							<textarea id="descn" name="descn" '
		 +'								style="min-height: 100px;" class="form-control required"></textarea>'
		 +'						</td>'
		 +'					</tr>'
		 +'				</tbody>'
		 +'			</table>'
		 +'		</form>'
		 +'		</article>'
		 +'	</div>'
		 +'	<div class="modal-footer">'
		 +'		<button type="button" class="btn btn-sm default" data-dismiss="modal" id="maintainFormCloseButton">关闭</button>'
		 +'		<button type="button" class="btn btn-sm red" onclick="if(true) $(\'#maintainForm\').submit();">确认</button>'
		 +'	</div>'
		 +'</div>'
		 +'</div>';
	$('body').append(html);//确定可以添加时再添加至Body
		//订单数据没有保存
		if($('#freightOrderForm #id').length == 0){
			alert('请首先保存订单数据!');
			return false;
		}else{
			if(selectTreeTId != ''){
				var node = zTreeObject.getNodeByTId(selectTreeTId);
				var nodeType = node.nodeType;
				if(nodeType == 'action'){
					alert('不能在动作节点下添加!');
					return false;
				}else if(nodeType == 'maintain'){
					var children = node.children;
					var count = 0;
					if(children != undefined){//如果为undifined说明还没有子节点
						for(var i=0, len = children.length; i<len; i++){
							if(children[i].nodeType == 'maintain'){
								count++;
							}
						}
					}
					
					$('#maintainForm #displayIndex').val(count);
					$('#maintainForm #parentMaintainId').val(node.id);
				}
			}
		}
		//需要考虑没有选择节点的时候就是默认添加至订单节点
		if($('#maintainForm #displayIndex').val() == ''){
			$('#maintainForm #displayIndex').val(zTreeObject.getNodes()[0].children.length);
		}
		$('#maintainForm #freightOrderId').val($('#freightOrderForm #id').val());

		var margin = (window.screen.availWidth - 800)/2;
		$('#maintainModal').css("margin-left", margin);
		$('#maintainModal').css("width","850px");
		$('#maintainModal').modal();

		//选择MAINTAIN类型
		new createListPicker({
			title:'动作类型列表', //标题
			modalId: 'maintainTypeModal',//modalID
			formId:'maintainTypeForm',//提交的form
			sumitAction:'xx',//提交的action
			thead:['id','名称', '优先级'],//列表头 数组
			tbody:['id', 'typeName','priority'],//字段数据 数组
			tableId:'maintainTypeTable',//表ID
			multiple: false,//是否多选
			canSelect:true,//是否可返回值
			valueType:'id',//需要的取值字段
			valueInput:'freightMaintainTypeId',//取值填至何处
			displayType:'typeName',//显示的取值字段
			displayInput:'freightMaintainTypeName'//显示填至何处
		});
		
		/*$(document).delegate('#maintainFormCloseButton', 'click', function(e){
			$('#maintainModal').modal('hide');
			$('#maintainModal').remove();
		});*/
		
		//操作数据校验
		$("#maintainForm").validate({
	        submitHandler: function(form) {
	            $('#maintainModal').modal('hide');
	    		bootbox.animate(false);
	    		var box = bootbox
	    				.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
				var freightOrderId = $('#maintainForm #freightOrderId').val();
				var parentMaintainId = $('#maintainForm #parentMaintainId').val();
				var freightMaintainTypeId = $('#maintainForm #freightMaintainTypeId').val();
				
				if(freightOrderId == undefined || freightOrderId == ''){
	    			alert('添加节点之前,请先保存业务信息!');
	    			box.modal('hide');
	    			return false;
				}
				var data = toJsonString('maintainForm');
				var url = 'fre-maintain-save-async.do?freightOrderId=' + freightOrderId + '&freightMaintainTypeId=' + freightMaintainTypeId;
				if(parentMaintainId != ''){
					url += '&parentMaintainId=' + parentMaintainId;
				}
	    		$.ajax({
	    			type: 'POST',
	    			data: data,
	    			url: url,
	    			contentType: 'application/json',
	    			success:function(data){
	    				init();
	    				box.modal('hide');
	    				$('#maintainModal').remove();//注销
	    			}
	    		});
	        },
	        errorClass: 'validate-error'
		});
}

function addAction(){
	if($('#actionModal').length > 0){
		$('#actionModal').remove();
	}
	
	var html = '';
	html += '<div id="actionModal" class="modal fade" aria-hidden="true">'
	+'<div class="modal-content">'
	+'	<div class="modal-header">'
	+'		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>'
	+'		<h4 class="modal-title">新增动作</h4>'
	+'	</div>'
	+'	<div class="modal-body">'
	+'		<article class="m-widget">'
	+'		<form id="actionForm" action="" method="post" class="m-form-blank" onsubmit="return false;">'
	+'			<input type="hidden" id="freightActionTypeId" value="">'
	+'			<input type="hidden" id="freightMaintainId" value="">'
	+'			<table class="table-input">'
	+'				<tbody>'
	+'					<tr>'
	+'						<td style="width:200px;">'
	+'							<label class="td-label" for="freightActionTypeName">动作类型</label>'
	+'						</td>'
	+'						<td>'
	+'							<div class="input-icon listPicker right">'
	+'							  <i class="fa fa-user actionTypeModal-add-on" urlAttr="fre-action-type-all.do" style="cursor: pointer;"></i>'
	+'							  <input type="text" id="freightActionTypeName" class="form-control">'
	+'						    </div>'
	+'						</td>'
	+'						<td>'
	+'							<label class="td-label" for="displayIndex">执行顺序</label>'
	+'						</td>'
	+'						<td>'
	+'							<input id="displayIndex" type="text" name="displayIndex" value="" '
	+'								class="form-control required number" >'
	+'						</td>'
	+'					</tr>'
	+'					<tr>'
	+'						<td style="width:200px;">'
	+'							<label class="td-label" for="descn">是否发送委托</label>'
	+'						</td>'
	+'						<td >'
	+'							<select id="delegate" name="delegate" class="form-control required">'
	+'								<option value="T">是</option>'
	+'								<option value="F">否</option>'
	+'							</select>'
	+'						</td>'
	+'						<td>'
	+'							<label class="td-label" for="descn">是否对内</label>'
	+'						</td>'
	+'						<td>'
	+'							<select id="internal" name="internal" class="form-control required">'
	+'								<option value="T">是</option>'
	+'								<option value="F">否</option>'
	+'							</select>'
	+'						</td>'
	+'					</tr>'
	+'					<tr>'
	+'						<td>'
	+'							<label class="td-label" for="prime">是否有填报界面</label>'
	+'						</td>'
	+'						<td>'
	+'							<select id="prime" name="prime" class="form-control required">'
	+'								<option value="T">是</option>'
	+'								<option value="F">否</option>'
	+'							</select>'
	+'						</td>'
	+'						<td>'
	+'							<label class="td-label" for="descn">状态</label>'
	+'						</td>'
	+'						<td>'
	+'							<select id="status" name="status" class="form-control required">'
	+'								<option value="未执行">未执行</option>'
	+'								<option value="已执行">已执行</option>'
	+'							</select>'
	+'						</td>'
	+'					</tr>'
	+'				</tbody>'
	+'			</table>'
	+'		</form>'
	+'		</article>'
	+'	</div>'
	+'	<div class="modal-footer">'
	+'		<button type="button" class="btn btn-sm default" data-dismiss="modal" id="actionFormCloseButton">关闭</button>'
	+'		<button type="button" class="btn btn-sm red" onclick="if(true) $(\'#actionForm\').submit();">确认</button>'
	+'	</div>'
	+'</div>'
	+'</div>';
	
	
	
	if(selectTreeTId == ''){
		alert('请首先选择一个操作!');
		return false;
	}else{
		var node = zTreeObject.getNodeByTId(selectTreeTId);
		var nodeType = node.nodeType;
		if(nodeType == 'order' || nodeType == 'action'){
			alert('请首先选择一个操作!不能直接在动作或者订单节点下直接添加!');
			return false;
		}else{
			var children = node.children;
			var count = 0;
			if(children != undefined){//如果为undifined说明还没有子节点
				for(var i=0, len=children.length; i<len; i++){
					if(children[i].nodeType == 'action'){
						count++;
					}
				}
			}
			$('body').append(html);//确定可以添加时再添加至Body
			
			$('#actionForm #displayIndex').val(count);
			$('#actionForm #freightMaintainId').val(node.id);
			
			var margin = (window.screen.availWidth - 800)/2;
			$('#actionModal').css("margin-left", margin);
			$('#actionModal').css("width","850px");
			$('#actionModal').modal();
		}
	}
	
	new createListPicker({
		title:'动作类型列表', //标题
		modalId: 'actionTypeModal',//modalID
		formId:'actionTypeForm',//提交的form
		sumitAction:'xx',//提交的action
		thead:['id','名称', '范围'],//列表头 数组
		tbody:['id', 'typeName','scope'],//字段数据 数组
		tableId:'actionTypeTable',//表ID
		multiple: false,//是否多选
		canSelect:true,//是否可返回值
		valueType:'id',//需要的取值字段
		valueInput:'freightActionTypeId',//取值填至何处
		displayType:'typeName',//显示的取值字段
		displayInput:'freightActionTypeName'//显示填至何处
	});
	
	/*$(document).delegate('#actionFormCloseButton', 'click', function(e){
		$('#actionModal').modal('hide');
		$('#actionModal').remove();
	});*/
	
	$("#actionForm").validate({
        submitHandler: function(form) {
            $('#actionModal').modal('hide');
    		bootbox.animate(false);
    		var box = bootbox
    				.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
			var freightMaintainId = $('#actionForm #freightMaintainId').val();
    		var freightActionTypeId = $('#actionForm #freightActionTypeId').val();
    		
			if(freightMaintainId == undefined || freightMaintainId == ''){
    			alert('未选择相应的操作范围!');
    			box.modal('hide');
    			return false;
			}
			var data = toJsonString('actionForm');
			var url = 'fre-action-save-async.do?freightMaintainId=' + freightMaintainId + '&freightActionTypeId=' + freightActionTypeId;
    		//alert(url);
			$.ajax({
    			type: 'POST',
    			data: data,
    			url: url,
    			contentType: 'application/json',
    			success:function(data){
    				box.modal('hide');
    				$('#actionModal').remove();//注销
    				init();
    			}
    		});
        },
        errorClass: 'validate-error'
	});
}