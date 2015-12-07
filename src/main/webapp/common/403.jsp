<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@include file="/common/taglibs.jsp"%>
<%response.setStatus(200);%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>403 - <spring:message code="core.404.notfound" text="无权访问"/></title>
	<%@include file="/common/s2.jsp"%>
  </head>
  <body class="page-404-3">
	<div class="page-inner">
	<img src="${ctx}/s2/assets/img/pages/earth.jpg" class="img-responsive" alt="">
	</div>
	<div class="container error-404">
		<h1>403</h1>
		<h2>You are not allowed.</h2>
		<p>
			 您的权限等级好像还不够。请确认。
		</p>
		<p>
			 You should check you account for this request.
		</p>
		<p>
			<a href="${ctx}">
				 Return home
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="history.back();" style="cursor: pointer;">
				 Back to
			</a>
			<br>
		</p>
	</div>
	<br><br><br><br><br><br><br><br>
<%@include file="/common/footer.jsp"%>
<script>
	$(function() {    
	   App.init();
	});
</script>
  </body>

</html>
