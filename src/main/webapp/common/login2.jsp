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
<!-- BEGIN LOGO 
<div class="logo" style="margin-top: 7%;margin-right: 32%;padding-left:0px;">
	<a href="${ctx}" >
		<img src="${ctx}/s2/assets/img/logo-big3.png" alt="" style="margin-top:-20px;margin-left: -15%;"/>
	</a>
	<span style="font-size:32px;font-family: 黑体;padding-top:20px;">成都创源信息化平台</span>
</div>
-->
<!-- END LOGO -->
<br>
<!-- BEGIN LOGIN -->
<div class="content" style="width:100%;background-color:rgb(0, 92, 87);border: 2px solid rgb(0, 92, 87);">
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-6">
			<div id="myCarousel" class="carousel image-carousel slide">
				<div class="carousel-inner">
					<div class="item" style="max-height: 230px;">
						<img src="${ctx}/s2/assets/img/login/image1.jpg" class="img-responsive" alt="" style="width:100%;">
						<div class="carousel-caption">
							<h4>
							<a href="page_news_item.html" >
								 VANBPMRP
							</a>
							</h4>
							<p>
								 The core is business process management.
							</p>
						</div>
					</div>
					<div class="item" style="max-height:230px;">
						<img src="${ctx}/s2/assets/img/login/image2.jpg" class="img-responsive" alt="" style="width:100%;">
						<div class="carousel-caption">
							<h4>
							<a href="page_news_item.html" >
								 VANBPMRP
							</a>
							</h4>
							<p>
								The aim is resource integration.
							</p>
						</div>
					</div>
					<div class="item" style="max-height: 230px;">
						<img src="${ctx}/s2/assets/img/login/image3.jpg" class="img-responsive" alt="" style="width:100%;">
						<div class="carousel-caption">
							<h4>
							<a href="page_news_item.html" >
								 VANBPMRP
							</a>
							</h4>
							<p>
								Give you what you want.
							</p>
						</div>
					</div>
					<div class="active item" style="max-height: 230px;">
						<img src="${ctx}/s2/assets/img/login/image4.jpg" class="img-responsive" alt="" style="width:100%;">
						<div class="carousel-caption">
							<h4>
							<a href="page_news_item.html" >
								 VANBPMRP
							</a>
							</h4>
							<p>
								Give you what you want.
							</p>
						</div>
					</div>
				</div>
				
				<a class="carousel-control left" href="#myCarousel" data-slide="prev">
					<i class="m-icon-big-swapleft m-icon-white"></i>
				</a>
				<a class="carousel-control right" href="#myCarousel" data-slide="next">
					<i class="m-icon-big-swapright m-icon-white"></i>
				</a>
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0">
					</li>
					<li data-target="#myCarousel" data-slide-to="1">
					</li>
					<li data-target="#myCarousel" data-slide-to="2">
					</li>
					<li data-target="#myCarousel" data-slide-to="3" class="active">
					</li>
				</ol>
			</div>
		</div>
		<!--end col-md-5
		<div class="col-md-1">
		</div>
		-->
		<div class="col-md-4" style="border: 1px solid gray;height:230px;width:370px;margin-top:0%;margin-bottom:0%;background-color:#fff;">
			<!-- BEGIN LOGIN FORM -->
			<!--  
			<form id="loginForm" class="login-form" style="width:20%;margin-left:60%;" action="${ctx}/base/loginCheck.do" method="post">
			-->
				
			<form id="loginForm" class="login-form" action="${ctx}/base/loginCheck.do" method="post" style="background-color: #fff;">
				<h3 class="form-title" style="font-family: 黑体;font-size:20px;font-weight: bolder;color:black;">用户登陆</h3>
				
				<div class="alert alert-danger" ${error==true ? 'style="margin-top:-30px;margin-bottom:5px;margin-left:10%;width:80%;margin-right:10%;max-height:30px;padding:0 0 0 0;z-index:1000;background-color:#fff;"' : 'style="display:none;"'}>
				<button class="close" data-close="alert"></button>
				<span style="font-family: 宋体;font-size:14px;font-weight:border;margin-bottom: 10px;margin-top:-10px;padding-top:0px;color:red;">
					 ${error != true? 'Enter any username and password.' : message}
				</span>
				</div>
				
				<div class="form-group" style="margin-left:10%;width:80%;margin-right:10%;">
					<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
					<label class="control-label visible-ie8 visible-ie9">用户名</label>
					<div class="input-icon">
						<i class="fa fa-user"></i>
						<input class="form-control placeholder-no-fix required" type="text" autocomplete="off" placeholder="用户名" id="username" name="username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME'] == null ? param.username : sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>
					</div>
				</div>
				<div class="form-group" style="margin-left:10%;width:80%;margin-right:10%;">
					<label class="control-label visible-ie8 visible-ie9">密码</label>
					<div class="input-icon">
						<i class="fa fa-lock"></i>
						<input class="form-control placeholder-no-fix required" type="password" autocomplete="off" placeholder="密码" id="password" name="password"/>
					</div>
				</div>
				
				<div class="form-actions" style="margin-right:0%;">
					<label class="checkbox" style="font-size: 16px;margin-left:18%;color:black;">
					<input type="checkbox" name="remember" value="1" style="width:15px;height:15px;border: 1px solid black;"/>记住我</label>
					<button type="submit" class="btn red pull-right">
					登陆 <i class="m-icon-swapright m-icon-white"></i>
					</button>
				</div>
				 
			</form>
			<!-- END LOGIN FORM -->
		</div>
		<div class="col-md-1">
		</div>
	</div>
</div>
<div class="copyright" style="color:black;font-family: Arial;font-size: 12px;margin-top: 3%;">
	2015 &copy; VAN. BPM & Resource Platform. Copyright Reserved.
</div>
<!-- END LOGIN -->
<%@include file="/common/footer.jsp"%>
<script type="text/javascript">
	$(function() {
		App.init();
		//Login.init();
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
	
	/* $(function(){
		$.backstretch([
	        "${ctx}/s2/assets/img/login/image1.jpg",
	        "${ctx}/s2/assets/img/login/image2.jpg",
	        "${ctx}/s2/assets/img/login/image3.jpg",
	        "${ctx}/s2/assets/img/login/image4.jpg"
	        ], {
	          fade: 1000,
	          duration: 8000
	    });
	}); */
</script>

  </body>
</html>