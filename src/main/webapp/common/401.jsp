<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@include file="/common/taglibs.jsp"%>
<%response.setStatus(200);%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>401 - <spring:message code="core.404.notfound" text="认证失败"/></title>
	<%@include file="/common/s2.jsp"%>
  </head>
  <body class="page-404-3">
	<div class="page-inner">
	<img src="${ctx }/s2/assets/img/pages/earth.jpg" class="img-responsive" alt="">
	</div>
	<div class="container error-404">
		<h1>401</h1>
		<h2>Authentication failed</h2>
		<p>
			 您好像是非法用户。请确认。
		</p>
		<p>
			 Please login to system for next.
		</p>
		<p>
			<a href="${ctx}/base/login.do">
				 Retry to login
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
