<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>词典值管理</title>
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
							<div class="caption"><i class="fa fa-book"></i>词典值管理</div>
						</div>
						<div class="portlet-body">
							<article class="m-widget" id="btnAcrticle">
								<div class="pull-left">
										<button class="btn btn-sm red" onclick="location.href='value-dictionary-input.do'">
											新增<i class="fa fa-arrows"></i></button>
									
										<button class="btn btn-sm red" onclick="table.removeAll();">
											删除<i class="fa fa-arrows-alt"></i></button>
									
									<button class="btn btn-sm green" id="importValueDictionary">
										批量导入</button>
											
									<button class="btn btn-sm green" id="exportValueDictionary">
										批量导出</button>
										
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
								<form name="searchForm" method="post" action="value-dictionary-list.do" class="form-inline">
								    <label for="valueAttributeId">属性值</label>
								    <select id="valueAttributeId" name="valueAttributeId" class="form-control required">
										<option value="">全部</option>
										<c:forEach items="${valueAttributes}" var="valueAttribute">
											<option value="${valueAttribute.id}"<c:if test="${item.valueAttribute.id == valueAttribute.id}">selected</c:if> >${valueAttribute.attributeName}</option>
										</c:forEach>
									</select>
								    
								    <label for="status">状态</label>
								    <input type="text" id="status" name="status" value="${param.status}" class="form-control input-medium">
									<button class="btn btn-sm red" onclick="document.searchForm.submit()">查询<i class="fa fa-search"></i></button>
								 </form>
							</article>
							<article class="m-widget">
									<form id="dynamicGridForm" name="dynamicGridForm" action="value-dictionary-remove.do" class="m-form-dynamic">
										<table id="dynamicGrid" class="m-table table-striped table-bordered table-hover">
											<thead>
								                <tr>
								                	<th class="m-table-check"><input type="checkbox" name="checkAll" onchange="toggleSelectedItems(this.checked)"></th>
								                    <th class="sorting" name="VALUE_CONTENT">值</th>
								                    <th class="sorting" name="VATTR_ID">所属属性</th>
								                    <th class="sorting" name="DESCN">说明</th>
								                    <th class="sorting" name="STATUS">状态</th>
								                    <th class="sorting" name="CREATE_TIME">创建时间</th>
								                    <th class="sorting" name="MODIFY_TIME">修改时间</th>
								                    <th>操作</th>
								                </tr>
								            </thead>
								            <tbody>
											<c:forEach items="${pageView.results}" var="item">
								                <tr>
								                	<td><input type="checkbox" class="selectedItem a-check" name="selectedItem" value="${item.id}"></td>
								                    <td>${item.valueContent}</td>
								                    <td>${item.valueAttribute.attributeName}</td>
								                    <td>${item.descn}</td>
								                    <td>${item.status}</td>
								                    <td>
								                    	<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								                    </td>
								                    <td>
								                    	<fmt:formatDate value="${modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								                    </td>
								                    <td>
								                    	<a href="value-dictionary-input.do?id=${item.id}" class="btn btn-sm red">编辑</a>
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
	
	<div class="modal fade" id="importValueDictionaryModal" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">批量添加值</h4>
			</div>
			<div class="modal-body">
				<article class="m-widget">
				<form class="dropzone" id="importValueDictionaryForm" action="value-dictionary-to-import-dictionary.do" enctype="multipart/form-data" method="post" class="m-form-blank">
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
	 	        'valueAttributeId': '${param.valueAttributeId}',
	 	        'status': '${param.status}'
	 	    },
	 		selectedItemClass: 'selectedItem',
	 		gridFormId: 'dynamicGridForm',
	 		exportUrl: 'department-export.do'
	};
	
	var table;
	
	$(function() {
		table = new Table(config);
	    table.configPagination('.m-pagination');
	    table.configPageInfo('.m-page-info');
	    table.configPageSize('.m-page-size');
	});
	
	
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
		var margin = (window.screen.availWidth -800)/2;
		$('#importValueDictionaryModal').css("margin-left", margin);
		$('#importValueDictionaryModal').css("width","800px");
		$('#importValueDictionaryModal').modal();
	});
	
	$(document).delegate('#exportValueDictionary', 'click',function(e){
		window.open('value-dictionary-to-export-dictionary.do');
	});
    </script>
  </body>

</html>
