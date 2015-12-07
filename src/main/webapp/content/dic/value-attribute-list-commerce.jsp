<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>词典属性管理</title>
    <%@include file="/common/s2.jsp"%>
  </head>
  <body class="page-header-fixed">
    <%@include file="/common/header2.jsp"%>
    <div class="page-container">
    	<%@include file="/common/menu.jsp"%>
    	<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper"> <!-- begin page-content-wrapper -->
			<div class="page-content"> <!-- begin page-content-->
				<%@include file="/common/setting.jsp"%>
				<div class="row">
				  <div class="col-md-12">
				  	<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption"><i class="fa fa-book"></i>词典属性管理</div>
						</div>
						<div class="portlet-body">
							<article class="m-widget" id="btnAcrticle">
								<div class="pull-left">
									<button class="btn btn-sm green" id="addValueDictionary">
										词典值</button>
									<!--  	
									<button class="btn btn-sm green" id="importValueDictionary">
										批量导入</button>
										
									<button class="btn btn-sm green" id="exportValueDictionary">
										批量导出</button>
									-->	
									<button class="btn btn-sm red" onclick="$('#searchAcrticle').slideToggle(200);">
										查询<i class="fa fa-chevron-down"></i></button>
								</div>
								<div class="pull-right" >
									<label for="pageSize">每页显示</label>
									<select id="pageSize" class="m-page-size"> 
									    <option value="10">10条</option> 
									    <option value="20">20条</option>
									    <option value="50">50条</option>
									 </select>
								</div>
							</article>
							
							<article class="m-widget" id="searchAcrticle">
								<form name="searchForm" method="post" action="value-attribute-list-commerce.do" class="form-inline">
								    <label for="attributeName">属性名称</label>
								    <input type="text" id="attributeName" name="attributeName" value="${param.attributeName}" class="form-control">
								    
								    <label for="columnName">字段</label>
								    <input type="text" id="columnName" name="columnName" value="${param.columnName}" class="form-control">
								    
								    <label for="status">状态</label>
								    <input type="text" id="status" name="status" value="${param.status}" class="form-control">
									<button class="btn btn-sm red" onclick="document.searchForm.submit()">查询<i class="fa fa-search"></i></button>
								 </form>
							</article>
							<article class="m-widget">
									<form id="dynamicGridForm" name="dynamicGridForm" action="value-attribute-remove.do" class="m-form-dynamic">
										<table id="dynamicGrid" class="m-table table-striped table-bordered table-hover">
											<thead>
								                <tr>
								                	<th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
								                    <th class="sorting" name="ATTR_NAME">属性名</th>
								                    <th class="sorting" name="ID">唯一标识</th>
								                    <th class="sorting" name="COLUMN_NAME">字段名</th>
								                    <th class="sorting" name="VCLS_ID">所属类</th>
								                    <th class="sorting" name="DESCN">说明</th>
								                    <th class="sorting" name="STATUS">状态</th>
								                    <th class="sorting" name="CREATE_TIME">创建时间</th>
								                    <th>操作</th>
								                </tr>
								            </thead>
								            <tbody>
											<c:forEach items="${pageView.results}" var="item">
								                <tr>
								                	<td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
								                    <td>${item.attributeName}</td>
								                    <td>${item.id}</td>
								                    <td>${item.columnName}</td>
								                    <td>${item.valueClass.id}</td>
								                    <td>${item.descn}</td>
								                    <td id="${item.id}status">${item.status}</td>
								                    <td>
								                    	<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>
								                    </td>
								                    <td>
								                    	<!--
								                    	<a class="btn btn-sm red valueDictionaryModal2-add-on" urlAttr='${ctx}/dic/value-dictionary-byvattrid.do?vAttrId=${item.id}'>所有值</a>
								                    	  <!--
								                    	<a href="value-attribute-input.do?id=${item.id}" class="btn btn-sm red">编辑</a>
								                    	-->
								                    </td>
								                </tr>
											</c:forEach>
								            </tbody>
										</table>
									</form>
								</article>
					      	</div>
					   </div>
						    <div class="m-page-info pull-left">
							  共100条记录 显示1到10条记录
							</div>
							<div class="btn-group m-pagination pull-right">
							  <button class="btn red">&lt;</button>
							  <button class="btn red">1</button>
							  <button class="btn red">&gt;</button>
							</div>
						    <div class="m-clear"></div>
				    </div>
				</div>
			</div>
		</div>
	</div>
	
	<!--词典值-->
	<div class="modal fade" id="valueDictionaryModal" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">词典值</h4>
			</div>
			<div class="modal-body">
				<article class="m-widget">
				<form id="valueDictionaryToAddForm" method="post" class="m-form-blank"></form>
				</article>
			
				<article class="m-widget">
					<form id="valueDictionaryBtnForm" name="valueDictionaryBtnForm" method="post" action="" class="form-inline" onsubmit="return false;">
						<button type="button" class="btn btn-sm red" onclick="$('#valueDictionaryToAddForm').submit()">确定</button>
						<button type="button" class="btn btn-sm red" onclick="reviseValueDictionary();">修改</button>
						<button type="button" class="btn btn-sm green" onclick="deleteValueDictionary();">删除</button>
					</form>
				</article>
				<article class="m-widget">
				<form id="valueDictionaryHasAddForm" action="" method="post" class="m-form-blank" style="max-height:280px;overflow-y: scroll;"></form>
				</article>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="importValueDictionaryModal" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">批量添加值</h4>
			</div>
			<div class="modal-body">
				<article class="m-widget">
				<form class="dropzone" id="importValueDictionaryForm" action="value-attribute-to-import-dictionary.do" enctype="multipart/form-data" method="post" class="m-form-blank">
				<input id="valueAttributeId" name="valueAttributeId" type="hidden">
				</form>
				</article>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
	<script type="text/javascript">
	$(function() {
	    App.init(); 
	});
	
	var config = {
	 	    id: 'dynamicGrid',
	 	    pageNo: ${pageView.pageNo},
	 	    pageSize: ${pageView.pageSize},
	 	    totalCount: ${pageView.totalCount},
	 	    resultSize: ${pageView.resultSize},
	 	    pageCount: ${pageView.pageCount},
	 	    orderBy: '${pageView.orderBy == null ? "" : pageView.orderBy}',
	 	    asc: ${pageView.asc},
	 	    params: {
	 	        'attributeName': '${param.attributeName}',
	 	        'columnName': '${param.columnName}',
	 	        'status': '${param.status}'
	 	    },
	 		selectedItemClass: 'selectedItem',
	 		gridFormId: 'dynamicGridForm',
	 		exportUrl: 'fre-box-export.do'
	};
	
	var table;
	
	$(function() {
		table = new Table(config);
	    table.configPagination('.m-pagination');
	    table.configPageInfo('.m-page-info');
	    table.configPageSize('.m-page-size');
	});
	
	$(function() {
		new createListPicker({
			title:'电子词典值列表', //标题
			modalId: 'valueDictionaryModal2',//modalID
			formId:'valueDictionaryForm2',//提交的form
			sumitAction:'xx',//提交的action
			thead:['可用值','状态'],//列表头 数组
			tbody:['valueContent', 'status'],//字段数据 数组
			tableId:'alueDictionaryTable',//表ID
			multiple: false,//是否多选
			canSelect:false,//是否可返回值
			valueType:'xx',//需要的取值字段
			valueInput:'xxx',//取值填至何处
			displayType:'xx',//显示的取值字段
			displayInput:'xx'//显示填至何处
		});
	});
	
//////////////////////////////////////////////////动作字段
	//添加字段
	$(document).delegate('#addValueDictionary', 'click',function(e){
		if(addValueDictionary()){
			var margin = (window.screen.availWidth -800)/2;
			$('#valueDictionaryModal').css("margin-left", margin);
			$('#valueDictionaryModal').css("width","800px");
			$('#valueDictionaryModal').modal();
		}
	});
	
	//添加字段
	function addValueDictionary(){
		if($('.selectedItem:checked').length != 1){
			alert('请选择一条数据!');
			return false;
		}else{
			var valueAttributeId = $('.selectedItem:checked').val();
			var status = $('#' + valueAttributeId + 'status').text();
			if(status != '已启用'){
				alert('已禁用的动作不能做任何操作！');
				return false;
			}else{
				$.ajax({
					url:'value-dictionary-to-add-dictionary.do?valueAttributeId=' + valueAttributeId,
					type:'post',
					dataType:'json',
					async:true,
					success:function(data){
						var hasAddData = data.hasAddData;
						var html = '<input id="valueAttributeId" type="hidden" value="' + valueAttributeId + '">';
						html += '<input id="valueDictionaryId" type="hidden" value="">';
						html += '<table class="m-table table-bordered table-hover">';
						html += '<thead><tr><th>值</th><th>状态</th><th>顺序</th></tr></thead><tbody>';
						html += '<tr><td><input id="valueContent" name="valueContent" class="form-control required"></td>';
						html += '<td><select id="status" name="status" class="form-control required"><option value="已启用">已启用</option><option value="已禁用">已禁用</option></select></td>';
						var displayIndex = (hasAddData == null || hasAddData.length == 0) ? 1 : (hasAddData[hasAddData.length -1].displayIndex + 1);
						html += '<td><input id="displayIndex" name="displayIndex" value="'+ displayIndex +'" class="form-control required number"></td>';
						html += '</tr>'
						$('#valueDictionaryToAddForm').html(html);
						html = '<table class="m-table table-bordered table-hover">';
						html += '<thead><tr><th width="10" class="m-table-check"><input type="checkbox" class="selectedItemIdAll"/></th>';
						html += '<th>值</th><th>状态</th><th>顺序</th></tr></thead><tbody>';
						$.each(hasAddData, function(i, item){
							html += '<tr><td><input class="selectedItemId a-check" type="checkbox" id="valueDictionaryId" name="valueDictionaryId" value="'+item.id+'" /></td>';
							html += '<td>' + item.valueContent + '</td>';
							html += '<td>' + item.status + '</td>';
							html += '<td>' + item.displayIndex + '</td</tr>';
						});
						html += "</tbody></table>";
						$('#valueDictionaryHasAddForm').html(html);
					},
					error:function(){
					}
				});
				return true;
			}
		}
	}
	//修改字段
	function reviseValueDictionary(){
		if($('#valueDictionaryHasAddForm .selectedItemId:checked').length != 1){
			alert('请选择一条数据！');
			return false;
		}else{
			var valueDictionaryId = $('#valueDictionaryHasAddForm .selectedItemId:checked').val();
			var url = 'value-dictionary-to-revise-dictionary.do?valueDictionaryId=' + valueDictionaryId;
			$.post(url, function(data){
				var valueDictionary = data.valueDictionary;
				$('#valueDictionaryToAddForm #valueDictionaryId').val(valueDictionary.id);
				$('#valueDictionaryToAddForm #valueContent').val(valueDictionary.valueContent);
				$('#valueDictionaryToAddForm #status').val(valueDictionary.status);
				$('#valueDictionaryToAddForm #displayIndex').val(valueDictionary.displayIndex);
			});
		}
	}
	//保存数据
	$(function() {
		$("#valueDictionaryToAddForm").validate({
	        submitHandler: function(form) {
				var valueAttributeId = $('#valueDictionaryToAddForm #valueAttributeId').val();
				var valueDictionaryId = $('#valueDictionaryToAddForm #valueDictionaryId').val();//修改单条费用时使用
				if(valueAttributeId == undefined || valueAttributeId == ''){
	    			alert('请重新操作!');
	    			return false;
				}
				var data = toJsonString('valueDictionaryToAddForm');
				var url = 'value-dictionary-done-add-dictionary.do?valueAttributeId=' + valueAttributeId + '&valueDictionaryId=' + valueDictionaryId;
				$.ajax({
	    			type: 'POST',
	    			data: data,
	    			url: url,
	    			contentType: 'application/json',
	    			success:function(data){
	    				addValueDictionary();
	    			}
	    		});
	        },
	        errorClass: 'validate-error',
	        rules: {
            	fieldName: {
   	                remote: {
   	                    url: 'fre-action-field-check-fieldname.do',
   	                    data: {
   	                    	freightActionTypeId: function() {
   	                            return $('#actionFieldToAddForm #freightActionTypeId').val();
   	                        },
   	                        id: function() {
   	                            return $('#actionFieldToAddForm #freightActionFieldId').val();
   	                        }
   	                    }
   	                }
   	            },
   	         	fieldColumn: {
	                remote: {
	                    url: 'fre-action-field-check-fieldcolumn.do',
	                    data: {
	                    	freightActionTypeId: function() {
   	                            return $('#actionFieldToAddForm #freightActionTypeId').val();
   	                        },
   	                     	id: function() {
	                            return $('#actionFieldToAddForm #freightActionFieldId').val();
	                        }
	                    }
	                }
	            }
   	        },
   	        messages: {
   	        	fieldName: {
   	                remote: "存在重复"
   	            },
   	         	fieldColumn: {
	                remote: "存在重复"
	            }
   	        }
		});
	});
	
	//删除字段
	function deleteValueDictionary(){
		var url = 'value-dictionary-done-remove-dictionary.do?';
		if($('#valueDictionaryHasAddForm .selectedItemId:checked').length == 0){
			alert('请选择数据！');
			return false;
		}else{
			$.post('value-dictionary-done-remove-dictionary.do', $('#valueDictionaryHasAddForm').serialize(), function(data){
				if(data == 'success'){
					addValueDictionary();
				}else{
					alert('删除失败！');
				}
			});
		}
	}
	
	//拼接成json数据类型
	function toJsonString(formId){
		var fields = $('#' + formId).serializeArray();
		var data = '{';
		$.each(fields, function(i, item){
				if(i == 0){
   				data += '"' + item.name + '":"' + item.value + '"';
   			}else{
   				data += ',"' + item.name + '":"' + item.value + '"';
   			}
		});
		data += '}';
		return data;
	}
	
	//批量添值
	$(function(){
		Dropzone.options.myDropzone = {
                init: function() {
                    this.on("addedfile", function(file) {
                        var removeButton = Dropzone.createElement("<button class='btn btn-sm btn-block'>Remove file</button>");
                        var _this = this;
                        removeButton.addEventListener("click", function(e) {
                          e.preventDefault();
                          e.stopPropagation();
                          _this.removeFile(file);
                        });
                        file.previewElement.appendChild(removeButton);
                    });
                    this.on('complete', function(data){
                    	var result = data.xhr.responseText;
                    	if(result == 'success'){
                    		$('#batchValueDictionaryModal').modal('hide');
                    	}
                    });
                }            
            }
	});
	
	$(document).delegate('#importValueDictionary', 'click',function(e){
		if($('.selectedItem:checked').length != 1){
			alert('请选择一条数据!');
			return false;
		}else{
			$('#importValueDictionaryForm #valueAttributeId').val($('.selectedItem:checked').val());
			var margin = (window.screen.availWidth -800)/2;
			$('#importValueDictionaryModal').css("margin-left", margin);
			$('#importValueDictionaryModal').css("width","800px");
			$('#importValueDictionaryModal').modal();
		}
	});
	
	$(document).delegate('#exportValueDictionary', 'click',function(e){
		if($('.selectedItem:checked').length != 1){
			alert('请选择一条数据!');
			return false;
		}else{
			window.open('value-attribute-to-export-dictionary.do?valueAttributeId=' + $('.selectedItem:checked').val());
		}
	});
    </script>
  </body>

</html>
