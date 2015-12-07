<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">

  <head>
    <%@include file="/common/meta.jsp"%>
    <title>登录</title>
	<%@include file="/common/s2.jsp"%>
  </head>
  <body class="login" onload="$('#username').focus();">
<br><br>    
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="${ctx}">
		<img src="${ctx}/s2/assets/img/logo-big.png" alt=""/>
	</a>
</div>
<!-- END LOGO -->
<br>
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form id="loginForm" class="login-form" action="${ctx}/base/loginCheck.do" method="post">
		<h3 class="form-title" style="font-family: 黑体;">登陆</h3>
		<div class="alert alert-danger" ${error==true ? '' : 'style="display:none"'}>
			<button class="close" data-close="alert"></button>
			<span style="font-family: 黑体;font-size:16px;">
				 ${error != true? 'Enter any username and password.' : message}
			</span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix required" type="text" autocomplete="off" placeholder="用户名" id="username" name="username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix required" type="password" autocomplete="off" placeholder="密码" id="password" name="password"/>
			</div>
		</div>
		<div class="form-actions">
			<label class="checkbox" style="margin-left: 20px;font-size: 16px;">
			<input type="checkbox" name="remember" value="1"/>记住我</label>
			<button type="submit" class="btn red pull-right">
			登陆 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
	</form>
	<!-- END LOGIN FORM -->
</div>
<div class="copyright">
	2014 &copy; Van. BPM & Resource Platform. Copyright Reserved.
</div>
<!-- END LOGIN -->
<%@include file="/common/footer.jsp"%>
<script type="text/javascript">
$(function() {
	App.init();
	Login.init();
	$('.footer').hide();
});

$(function() {
    $('#loginForm').validate({
        submitHandler: function(form) {
			bootbox.animate(false);
			var box = bootbox.dialog('<div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div>');
            form.submit();
        },
        errorClass: 'validate-error'
    });
});

</script>
  </body>
   
</html>
