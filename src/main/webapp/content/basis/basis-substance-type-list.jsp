<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>类型管理</title>
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
							<div class="caption"><i class="fa fa-align-justify"></i>类型管理</div>
						</div>
						<div class="portlet-body">
							<article class="m-widget" id="btnAcrticle">
								<div class="pull-left">
										<button class="btn btn-sm red" onclick="location.href='basis-substance-type-input.do'">
											新增<i class="fa fa-arrows"></i></button>
									
										<button class="btn btn-sm red" onclick="table.removeAll();">
											删除<i class="fa fa-arrows-alt"></i></button>
											
										<button class="btn btn-sm green" id="addBasisAttribute">
											属性管理</button>
										<!--  	
										<button class="btn btn-sm green" id="addBasisApplication">
											应用管理</button>
											
										<button class="btn btn-sm green" id="addBasisSchema">
											界面管理</button>
											
										<button class="btn btn-sm green" id="addBasisStatus">
											状态管理</button>
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
								<form name="searchForm" method="post" action="basis-substance-list.do" class="form-inline">
								    <label for="typeName">类型名称</label>
								    <input type="text" id="typeName" name="typeName" value="${param.typeName}" class="form-control">
								    
								    <label for="status">状态</label>
								    <select id="status" name="status" class="form-control">
										<option value=""></option>
										<option value="T"<c:if test="${item.param == 'T'}">selected</c:if> >已启用</option>
										<option value="F"<c:if test="${item.param == 'F'}">selected</c:if> >已停用</option>
									</select>
									<button class="btn btn-sm red" onclick="document.searchForm.submit()">查询<i class="fa fa-search"></i></button>
								 </form>
							</article>
							<article class="m-widget">
									<form id="dynamicGridForm" name="dynamicGridForm" action="basis-substance-type-remove.do" class="m-form-dynamic" method="post">
										<table id="dynamicGrid" class="m-table table-striped table-bordered table-hover">
											<thead>
								                <tr>
								                	<th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
								                    <th class="sorting" name="ID">类型ID</th>
								                    <th class="sorting" name="TYPE_NAME">类型名称</th>
								                    <th class="sorting" name="DESCN">说明</th>
								                    <th class="sorting" name="STATUS">状态</th>
								                    <th class="sorting" name="CREATE_TIME">创建时间</th>
								                    <th class="sorting" name="MODIFY_TIME">修改时间</th>
								                    <th class="sorting" name="DISP_INX">序号</th>
								                    <th>操作</th>
								                </tr>
								            </thead>
								            <tbody>
											<c:forEach items="${pager.results}" var="item">
								                <tr>
								                	<td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
								                    <td>${item.id}</td>
								                    <td>${item.typeName}</td>
								                    <td>${item.descn}</td>
								                    <td id="${item.id}status">${item.status == 'T'?'已启用':'已停用'}</td>
								                    <td>
								                    	<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>
								                    </td>
								                    <td>
								                    	<fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd"/>
								                    </td>
								                    <td>${item.displayIndex}</td>
								                    <td >
								                    	<a href="basis-substance-type-input.do?id=${item.id}" class="btn btn-sm red">编辑</a>
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
	
	<div class="modal fade" id="basisAttributeModal" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">属性</h4>
			</div>
			<div class="modal-body">
				<article class="m-widget">
				<form id="basisAttributeToAddForm" method="post" class="m-form-blank"></form>
				</article>
			
				<article class="m-widget">
					<form id="basisAttributeBtnForm" name="basisAttributeBtnForm" method="post" action="" class="form-inline" onsubmit="return false;">
						<button type="button" class="btn btn-sm red" onclick="$('#basisAttributeToAddForm').submit()">添加</button>
						<!--  
						<button type="button" class="btn btn-sm red" onclick="batchBasisAttribute();">批量</button>
						-->
						<button type="button" class="btn btn-sm red" onclick="reviseBasisAttribute();">修改</button>
						<button type="button" class="btn btn-sm green" onclick="deleteBasisAttribute();">删除</button>
					</form>
				</article>
				<article class="m-widget" style="max-height:300px;overflow-y: scroll;">
				<form id="basisAttributeHasAddForm" action="" method="post" class="m-form-blank" ></form>
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
	 	    pageNo: ${pager.pageNo},
	 	    pageSize: ${pager.pageSize},
	 	    totalCount: ${pager.totalCount},
	 	    resultSize: ${pager.resultSize},
	 	    pageCount: ${pager.pageCount},
	 	    orderBy: '${pager.orderBy == null ? "" : pager.orderBy}',
	 	    asc: ${pager.asc},
	 	    params: {
	 	        'typeName': '${param.typeName}',
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
	    
	    var href = window.location.href;
     	if(href.indexOf('?') < 0){
     	    var stateObject = {};
  			var title = '';
  			var url = table.buildUrl(table.config.params);
     	    history.pushState(stateObject, title, url);//HTML5新特性，不刷新页面的情况下修改URL地址
     	}
	});
	
	////////////////////////////////////属性
	$(document).delegate('#addBasisAttribute', 'click',function(e){
		if(addBasisAttribute()){
			var margin = (window.screen.availWidth - 1200)/2;
			$('#basisAttributeModal').css("margin-left", margin);
			$('#basisAttributeModal').css("width","1200px");
			$('#basisAttributeModal').modal();
		}
	});
	
	function addBasisAttribute(){
		if($('.selectedItem:checked').length != 1){
			alert('请选择一条数据!');
			return false;
		}else{
			var basisSubstanceTypeId = $('.selectedItem:checked').val();
			var status = $('#' + basisSubstanceTypeId + 'status').text();
			if(status != '已启用'){
				alert('已禁用的动作不能做任何操作！');
				return false;
			}else{
				$.ajax({
					url:'${ctx}/basis/basis-attribute-to-add-attribute?basisSubstanceTypeId=' + basisSubstanceTypeId,
					type:'post',
					dataType:'json',
					async:true,
					success:function(data){
						var hasAddData = data.hasAddData;
						var html = '<input id="basisSubstanceTypeId" type="hidden" value="' + basisSubstanceTypeId + '">';
						html += '<input id="basisAttributeId" type="hidden" value="">';
						html += '<table class="m-table table-bordered table-hover">';
						html += '<thead><tr><th>字段名</th><th>字段库</th><th>字段类型</th><th>是否必须</th><th>顺序</th></tr></thead><tbody>';
						html += '<tr><td><input id="attributeName" name="attributeName" value="" class="form-control required"></td>';
						html += '<td><input id="attributeColumn" name="attributeColumn" value="" class="form-control required"></td>';
						html += '<td><select id="attributeType" name="attributeType" class="form-control required" >';
						html += '<option value="VARCHAR">VARCHAR</option><option value="TEXT">TEXT</option><option value="INT">INT</option>';
						html += '<option value="DOUBLE">DOUBLE</option><option value="DATETIME">DATETIME</option><option value="TIMESTAMP">TIMESTAMP</option>';
						html += '</select></td>';
						html += '<td><select id="required" name="required" class="form-control required" >';
						html += '<option value="T">是</option><option value="F">否</option>';
						html += '</select></td>';
						var displayIndex = (hasAddData == null || hasAddData.length == 0) ? 1 : (hasAddData[0].displayIndex + 1);
						html += '<td><input id="displayIndex" name="displayIndex" value="' + displayIndex + '" class="form-control required number"></td>';
						html += '</tr>'
						
						//html += '<tr><td>批量添加数</td><td><input id="batchCount" value="1" class="form-control number"></td>';
						//html += '<td>批量起始数</td><td><input id="batchStart" value="0" class="form-control number"></td>';
						//html += '<td>批量终止数</td><td><input id="batchEnd" value="0" class="form-control number"></td></tr>';
						
						$('#basisAttributeToAddForm').html(html);
						html = '<table class="m-table table-bordered table-hover">';
						html += '<thead><tr><th width="10" class="m-table-check"><input type="checkbox" class="selectedItemIdAll"/></th>';
						html += '<th>字段名</th><th>字段库</th><th>字段类型</th><th>是否必须</th><th>顺序</th></tr></thead><tbody>';
						$.each(hasAddData, function(i, item){
							html += '<tr><td><input class="selectedItemId a-check" name="basisAttributeId" type="checkbox" value="'+item.id+'" /></td>';
							html += '<td>' + item.attributeName + '</td>';
							html += '<td>' + item.attributeColumn + '</td>';
							html += '<td>' + item.attributeType + '</td>';
							html += '<td>' + item.required + '</td>';
							html += '<td>' + item.displayIndex + '</td></tr>';
						});
						html += "</tbody></table>";
						$('#basisAttributeHasAddForm').html(html);
					},
					error:function(){
					}
				});
				return true;
			}
		}
	}
	//修改字段
	function reviseBasisAttribute(){
		if($('#basisAttributeHasAddForm .selectedItemId:checked').length != 1){
			alert('请选择一条数据！');
			return false;
		}else{
			var basisAttributeId = $('#basisAttributeHasAddForm .selectedItemId:checked').val();
			var url = '${ctx}/basis/basis-attribute-to-revise-attribute?basisAttributeId=' + basisAttributeId;
			$.post(url, function(data){
				var basisAttribute = data.basisAttribute;
				$('#basisAttributeToAddForm #basisAttributeId').val(basisAttribute.id);
				$('#basisAttributeToAddForm #attributeName').val(basisAttribute.attributeName);
				$('#basisAttributeToAddForm #attributeColumn').val(basisAttribute.attributeColumn);
				$('#basisAttributeToAddForm #attributeType').val(basisAttribute.attributeType);
				$('#basisAttributeToAddForm #canSelect').val(basisAttribute.canSelect);
				$('#basisAttributeToAddForm #vAttrId').val(basisAttribute.vAttrId);
				$('#basisAttributeToAddForm #vClsId').val(basisAttribute.vClsId);
				$('#basisAttributeToAddForm #vColumn').val(basisAttribute.vColumn);
				$('#basisAttributeToAddForm #vFilterId').val(basisAttribute.vFilterId);
				$('#basisAttributeToAddForm #displayIndex').val(basisAttribute.displayIndex);
			});
		}
	}
	$(function() {
		$("#basisAttributeToAddForm").validate({
	        submitHandler: function(form) {
				var basisSubstanceTypeId = $('#basisAttributeToAddForm #basisSubstanceTypeId').val();
				var basisAttributeId = $('#basisAttributeToAddForm #basisAttributeId').val();//修改单条费用时使用
				if(basisSubstanceTypeId == undefined || basisSubstanceTypeId == ''){
	    			alert('请重新操作!');
	    			return false;
				}
				var data = toJsonString('basisAttributeToAddForm');
				var url = '${ctx}/basis/basis-attribute-done-add-attribute?basisSubstanceTypeId=' + basisSubstanceTypeId + '&basisAttributeId=' + basisAttributeId;
				$.ajax({
	    			type: 'POST',
	    			data: data,
	    			url: url,
	    			contentType: 'application/json',
	    			success:function(data){
	    				addBasisAttribute();
	    			}
	    		});
	        },
	        errorClass: 'validate-error',
	        rules: {
	        	attributeName: {
   	                remote: {
   	                    url: '${ctx}/basis/basis-attribute-check-attributename.do',
   	                    data: {
   	                    	basisSubstanceTypeId: function() {
   	                            return $('#basisAttributeToAddForm #basisSubstanceTypeId').val();
   	                        },
   	                        id: function() {
   	                            return $('#basisAttributeToAddForm #basisAttributeId').val();
   	                        }
   	                    }
   	                }
   	            },
   	         attributeColumn: {
	                remote: {
	                    url: '${ctx}/basis/basis-attribute-check-attributecolumn.do',
	                    data: {
	                    	basisSubstanceTypeId: function() {
   	                            return $('#basisAttributeToAddForm #basisSubstanceTypeId').val();
   	                        },
   	                     	id: function() {
	                            return $('#basisAttributeToAddForm #basisAttributeId').val();
	                        }
	                    }
	                }
	            }
   	        },
   	        messages: {
   	        	attributeName: {
   	                remote: "存在重复"
   	            },
   	            attributeColumn: {
	                remote: "存在重复"
	            }
   	        }
		});
	});
	function deleteBasisAttribute(){
		var url = '${ctx}/basis/basis-attribute-done-remove-attribute?';
		if($('#basisAttributeHasAddForm .selectedItemId:checked').length == 0){
			alert('请选择数据！');
			return false;
		}else{
			$.post(url, $('#basisAttributeHasAddForm').serialize(),  function(data){
				if(data == 'success'){
					addBasisAttribute();
				}else{
					alert('删除失败！');
				}
			});
		}
	}
	//批量添加属性
	function batchBasisAttribute(){
		var basisSubstanceTypeId = $('#basisAttributeToAddForm #basisSubstanceTypeId').val();
		var batchCount = $('#basisAttributeToAddForm #batchCount').val();
		var batchStart = $('#basisAttributeToAddForm #batchStart').val();
		var batchEnd = $('#basisAttributeToAddForm #batchEnd').val();
		if(basisSubstanceTypeId == undefined || basisSubstanceTypeId == ''){
			alert('请重新操作!');
			return false;
		}
		var data = toJsonString('basisAttributeToAddForm');
		var url = '${ctx}/basis/basis-attribute-done-add-batch?basisSubstanceTypeId=' + basisSubstanceTypeId + '&batchCount=' + batchCount + '&batchStart=' + batchStart + '&batchEnd=' + batchEnd;
		$.ajax({
			type: 'POST',
			data: data,
			url: url,
			contentType: 'application/json',
			success:function(data){
				addBasisAttribute();
			}
		});
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
	//针对数组
	function toJsonStringArray(formId){
		var fields = $('#' + formId).serializeArray();
		var data = '[{';
		$.each(fields, function(i, item){
			if(i == 0){
   				data += '"' + item.name + '":"' + item.value + '"';
   			}else{
   				if(item.name == 'id'){
   					data += '},{"' + item.name + '":"' + item.value + '"';
   				}else{
   					data += ',"' + item.name + '":"' + item.value + '"';
   				}
   			}
		});
		data += '}]';
		return data;
	}
	
    </script>
  </body>

</html>
