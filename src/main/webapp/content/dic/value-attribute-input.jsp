<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
  <head>
    <%@include file="/common/meta.jsp"%>
    <title>词典属性信息</title>
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
							<div class="caption"><i class="fa fa-book"></i>词典属性信息</div>
						</div>
						<div class="portlet-body form">
						<!-- BEGIN FORM-->
							<form id="editDomainForm" method="post" action="value-attribute-save.do?operationMode=STORE" class="form-horizontal">
								 <c:if test="${item.id != null}">
								  <input id="id" type="hidden" name="id" value="${item.id}">
								 </c:if>
								 <div class="form-body">
									<table class="table-input" style="margin-left:10%;width:80%">
										<thead>
											<tr><th colspan="6">词典属性信息</th></tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<label class="td-label" for="attributeName">属性名</label>
												</td>
												<td>
													<input id="attributeName" type="text" name="attributeName" value="${item.attributeName}" 
													size="40" minlength="2" maxlength="50" class="form-control required" >
												</td>
												<td>
													<label class="td-label" for="columnName">字段名</label>
												</td>
												<td>
													<input id="columnName" type="text" name="columnName" value="${item.columnName}" 
													size="40" minlength="2" maxlength="50" class="form-control required" >
												</td>
											</tr>
											
											<tr>
												<td>
													<label class="td-label" for="descn">说明</label>
												</td>
												<td>
													<input id="descn" type="text" name="descn" value="${item.descn}" 
													size="40" minlength="2" maxlength="50" class="form-control required" >
												</td>
												<td>
													<label class="td-label" for="status">状态</label>
												</td>
												<td>
													<select id="status" name="status" class="form-control required">
														<option value="已启用"<c:if test="${item.status == '已启用'}">selected</c:if> >已启用</option>
														<option value="已禁用"<c:if test="${item.status == '已禁用'}">selected</c:if> >已禁用</option>
													</select>
												</td>
											</tr>
										</tbody>
									</table>
								</div>	
								<div class="form-actions fluid">
									<div class="row">
										<div class="col-md-6">
											<div class="col-md-offset-6 col-md-9">
												<button type="submit" class="btn green">保存</button>
												<button type="button" class="btn default" onclick="history.back();">返回</button>
											</div>
										</div>
										<div class="col-md-6">
										</div>
									</div>
								</div>
							</form>
							<!-- END FORM-->
							</div>
					   </div>
				    </div>
				</div>
			</div>
		</div>
	</div>
    
    <%@include file="/common/footer.jsp"%>
    <script type="text/javascript">
    $(function() {
        App.init();
    });
    
    $(function() {
        $("#editDomainForm").validate({
            submitHandler: function(form) {
    			bootbox.animate(false);
    			var box = bootbox.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
                form.submit();
            },
            errorClass: 'validate-error',
	        rules: {
	        	attributeName: {
   	                remote: {
   	                    url: 'value-attribute-check-attributename.do',
   	                    data: {
   	                    	<c:if test="${item.id != null}">
   	                    	id: function() {
   	                            return $('#editDomainForm #id').val();
   	                        }
   	                    	</c:if>
   	                    }
   	                }
   	            },
   	         columnName: {
	                remote: {
	                    url: 'value-attribute-check-attributecolumn.do',
	                    data: {
	                    	<c:if test="${item.id != null}">
   	                    	id: function() {
   	                            return $('#editDomainForm #id').val();
   	                        }
   	                    	</c:if>
	                    }
	                }
	            }
   	        },
   	        messages: {
   	        	attributeName: {
   	                remote: "存在重复"
   	            },
   	         	columnName: {
	                remote: "存在重复"
	            }
   	        }
        });
    });
    
    </script>
  </body>

</html>
