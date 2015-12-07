<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>动作字段信息</title>
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
							<div class="caption"><i class="fa fa-align-justify"></i>动作字段信息</div>
						</div>
						<div class="portlet-body form">
						<!-- BEGIN FORM-->
							<form id="editDomainForm" method="post" action="fre-action-field-save.do?operationMode=STORE" class="form-horizontal">
								 <c:if test="${item.id != null}">
								  <input id="id" type="hidden" name="id" value="${item.id}">
								 </c:if>
								 <div class="form-body">
									<table class="table-input" style="margin-left:10%;width:80%">
										<thead>
											<tr><th colspan="6">动作字段信息</th></tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<label class="td-label" for="freightActionTypeId">所属动作类型</label>
												</td>
												<td >
													<select id="freightActionTypeId" name="freightActionTypeId" class="form-control required">
														<c:forEach items="${freightActionTypes}" var="freightActionType">
														<option value="${freightActionType.id}"<c:if test="${item.freightActionType.id == freightActionType.id}">selected</c:if> >${freightActionType.typeName}</option>
														</c:forEach>
													</select>
												</td>
												<td>
													<label class="td-label" for="fieldType">字段类型</label>
												</td>
												<td>
													<select id="fieldType" name="fieldType" class="form-control required">
														<option value="VARCHAR"<c:if test="${item.fieldType == 'VARCHAR'}">selected</c:if> >字符串</option>
														<option value="TEXT"<c:if test="${item.fieldType == 'TEXT'}">selected</c:if> >大文本</option>
														<option value="INT"<c:if test="${item.fieldType == 'INT'}">selected</c:if> >整数</option>
														<option value="DOUBLE"<c:if test="${item.fieldType == 'DOUBLE'}">selected</c:if> >小数</option>
														<option value="DATETIME"<c:if test="${item.fieldType == 'DATETIME'}">selected</c:if> >日期(年月日)</option>
														<option value="TIMESTAMP"<c:if test="${item.fieldType == 'TIMESTAMP'}">selected</c:if> >时间(年月日时分秒)</option>
													</select>
												</td>
											</tr>
											
											<tr>
												<td>
													<label class="td-label" for="fieldName">字段名</label>
												</td>
												<td>
													<input id="fieldName" type="text" name="fieldName" value="${item.fieldName}" 
													size="40" minlength="2" maxlength="50" class="form-control required" >
												</td>
												<td>
													<label class="td-label" for="fieldColumn">字段(库)</label>
												</td>
												<td>
													<input id="fieldColumn" type="text" name="fieldColumn" value="${item.fieldColumn}" 
													size="40" minlength="2" maxlength="50" class="form-control required" >
												</td>
											</tr>
											
											<tr>
												<td>
													<label class="td-label" for="required">是否必须</label>
												</td>
												<td>
													<select id="required" name="required" class="form-control required">
														<option value="T" <c:if test="${item.required == 'T'}">selected</c:if> >是</option>
														<option value="F" <c:if test="${item.required == 'F'}">selected</c:if> >否</option>
													</select>
												</td>
												<td>
													<label class="td-label" for="participate">是否共享</label>
												</td>
												<td>
													<select id="participate" name="participate" class="form-control required">
														<option value="F" <c:if test="${item.participate == 'F'}">selected</c:if> >否</option>
														<option value="T" <c:if test="${item.participate == 'T'}">selected</c:if> >是</option>
													</select>
												</td>
											</tr>
											
											<tr>
												<td>
													<label class="td-label" for="canSelect">是否可选值</label>
												</td>
												<td>
													<select id="canSelect" name="canSelect" class="form-control required">
														<option value="F" <c:if test="${item.canSelect == 'F'}">selected</c:if> >否</option>
														<option value="T" <c:if test="${item.canSelect == 'T'}">selected</c:if> >是</option>
													</select>
												</td>
												<td>
													<label class="td-label" for="vAttrId">电子词典属性</label>
												</td>
												<td>
													<select id="vAttrId" name="vAttrId" class="form-control">
														<option value="">无</option>
														<c:forEach items="${valueAttributes}" var="valueAttribute">
														<option value="${valueAttribute.id}"<c:if test="${vAttrId.id == valueAttribute.id}">selected</c:if> >${valueAttribute.attributeName}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											
											<tr>
												<td>
													<label class="td-label" for="vClsId">电子词典类型</label>
												</td>
												<td>
													<select id="vClsId" name="vClsId" class="form-control">
														<option value="">无</option>
														<c:forEach items="${valueClasses}" var="valueClass">
														<option value="${valueClass.id}"<c:if test="${item.vClsId == valueClass.id}">selected</c:if> >${valueClass.className}</option>
														</c:forEach>
													</select>
												</td>
												<td>
													<label class="td-label" for="vColumn">电子词典字段</label>
												</td>
												<td>
													<input id="vColumn" type="text" name="vColumn" value="${item.vColumn}" 
													size="40" minlength="2" maxlength="50" class="form-control" >
												</td>
											</tr>
											
											<tr>
												<td>
													<label class="td-label" for="descn">说明</label>
												</td>
												<td>
													<input id="descn" type="text" name="descn" value="${item.descn}" 
													size="40" minlength="1" maxlength="256" class="form-control required" >
												</td>
												<td>
													<label class="td-label" for="status">状态</label>
												</td>
												<td>
													<select id="status" name="status" class="form-control required">
														<option value="T"<c:if test="${item.status == 'T'}">selected</c:if> >已启用</option>
														<option value="F"<c:if test="${item.status == 'F'}">selected</c:if> >已停用</option>
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
            	fieldName: {
   	                remote: {
   	                    url: 'fre-action-field-check-fieldname.do',
   	                    data: {
   	                    	freightActionTypeId: function() {
   	                            return $('#freightActionTypeId').val();
   	                        }
   	                        <c:if test="${item.id != null}">
   	                   		,
   	                        id: function() {
   	                            return $('#id').val();
   	                        }
   	                        </c:if>
   	                    }
   	                }
   	            },
   	         	fieldColumn: {
	                remote: {
	                    url: 'fre-action-field-check-fieldcolumn.do',
	                    data: {
	                    	freightActionTypeId: function() {
   	                            return $('#freightActionTypeId').val();
   	                        }
	                        <c:if test="${item.id != null}">
	                        ,
	                        id: function() {
	                            return $('#id').val();
	                        }
	                        </c:if>
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
    
    </script>
  </body>

</html>
