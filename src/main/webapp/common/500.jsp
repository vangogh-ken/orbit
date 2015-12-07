<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@include file="/common/taglibs.jsp"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="java.net.InetAddress"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="org.slf4j.Logger"%>
<%@ page import="org.slf4j.LoggerFactory"%>
<%response.setStatus(200);%>
<%
Throwable ex = null;
if (exception != null) {
	ex = exception;
}
if (request.getAttribute("javax.servlet.error.exception") != null) {
	ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
}
//记录日志
Logger logger = LoggerFactory.getLogger("500.jsp");
String requestInfo = "";
try {
	StringBuilder content = new StringBuilder();
	content.append(InetAddress.getLocalHost()).append("\n");
	content.append("Header....\n");
    Enumeration<String> e = request.getHeaderNames();
    String key;
    while(e.hasMoreElements()){
        key = e.nextElement();
        content.append(key).append("=").append(request.getHeader(key)).append("\n");
    }
    content.append("Attribute....\n");
    e = request.getAttributeNames();
    while(e.hasMoreElements()){
        key = e.nextElement();
        content.append(key).append("=").append(request.getAttribute(key)).append("\n");
    }

    content.append("Parameter....\n");
    e = request.getParameterNames();
    while(e.hasMoreElements()){
        key = e.nextElement();
        content.append(key).append("=")
			.append(java.util.Arrays.asList(request.getParameterValues(key))).append("\n");
    }
	requestInfo = content.toString().replaceAll("<", "&lt;");
} catch(Throwable t) {
    logger.error("fetch request info error", t);
}
logger.error(requestInfo, ex);
%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>500 - <spring:message code="core.404.notfound" text="内部错误"/></title>
	<%@include file="/common/s2.jsp"%>
	
	<script type="text/javascript">
$(function() {
	$('#targetContentDiv').height($(window).innerHeight() - 150);
})
    </script>
	<style type="text/css">
#targetContentDiv {
	background-color: #b8b8b8;
	padding: 70px 0 80px;
	text-align: center;
}

#targetContentDiv h1 {
	font-size: 120px;
	letter-spacing: -2px;
    line-height: 1;
}

#targetContentDiv p {
	font-size: 40px;
    font-weight: 200;
    line-height: 1.25;
	font-weight: bold;
	padding: 10px;
}

#targetContentDiv li {
	display: inline;
	list-style: none outside none;
}
	</style>
  </head>
  <body class="page-404-3">
	<div class="page-inner">
	<img src="${ctx }/s2/assets/img/pages/earth.jpg" class="img-responsive" alt="">
	</div>
	<div class="container error-404">
		<h1>500</h1>
		<h2>Oops! Something went wrong.</h2>
		<p>
			 我们正在处理。请稍后尝试或者报告管理员。
		</p>
		<p>
			 We are fixing it! Please come back in a while.
		</p>
		<p>
			<a href="${ctx}">
				 Return home
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="history.back();" style="cursor: pointer;">
				 Back to
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="$('#errorInfo').toggle(400);" style="cursor: pointer;">
				 Details
			</a>
			<!--  
			<a onclick="$('#output').show();$('#targetContentDiv').hide();" style="cursor: pointer;">
				 Details
			</a>
			-->
			<br>
		</p>
	</div>
	<div class="row" >
		<div id="errorInfo" class="col-md-12" style="margin-left:10%;width:80%;color: white;font-size: 14px;font-family: 宋体;display:none;overflow: hidden;">
		<%=requestInfo%>
		<hr>
		<%
		StringWriter writer = new StringWriter();
		ex.printStackTrace(new PrintWriter(writer));
		out.println(writer.toString());
		%>
		</div>
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
