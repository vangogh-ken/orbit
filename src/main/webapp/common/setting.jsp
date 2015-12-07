<%@page language="java" pageEncoding="UTF-8"%>
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Modal title</h4>
			</div>
			<div class="modal-body">Widget settings form goes here</div>
			<div class="modal-footer">
				<button type="button" class="btn blue">Save changes</button>
				<button type="button" class="btn default" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

<!-- BEGIN STYLE CUSTOMIZER -->
  
<div class="theme-panel hidden-xs hidden-sm">
	<div class="toggler" style="height: 20px;width:20px;"></div>
	<div class="toggler-close"></div>
	<div class="theme-options">
		<div class="theme-option theme-colors clearfix">
			<span> 主题颜色 </span>
			<ul>
				<li class="color-black current color-default" data-style="default">
				</li>
				<li class="color-blue" data-style="blue"></li>
				<li class="color-brown" data-style="brown"></li>
				<li class="color-purple" data-style="purple"></li>
				<li class="color-grey" data-style="grey"></li>
				<li class="color-white color-light" data-style="light"></li>
			</ul>
		</div>
		<div class="theme-option">
			<span> 整体布局 </span> <select
				class="layout-option form-control input-small">
				<option value="fluid" selected="selected">流动</option>
				<option value="boxed">包裹</option>
			</select>
		</div>
		<div class="theme-option">
			<span> 头部 </span> <select
				class="header-option form-control input-small">
				<option value="fixed" selected="selected">固定</option>
				<option value="default">默认</option>
			</select>
		</div>
		<div class="theme-option">
			<span> 侧边 </span> <select
				class="sidebar-option form-control input-small">
				<option value="fixed">Fixed</option>
				<option value="default" selected="selected">默认</option>
			</select>
		</div>
		<div class="theme-option">
			<span> 侧边位置 </span> <select
				class="sidebar-pos-option form-control input-small">
				<option value="left" selected="selected">左</option>
				<option value="right">右</option>
			</select>
		</div>
		<div class="theme-option">
			<span> 底部 </span> <select
				class="footer-option form-control input-small">
				<option value="fixed">固定</option>
				<option value="default" selected="selected">默认</option>
			</select>
		</div>
	</div>
</div>
<!---->
<!-- END STYLE CUSTOMIZER -->

<!-- BEGIN PAGE HEADER-->
<div class="row" style="height:50px;">
	<div class="col-md-12" style="height:50px;">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<!-- 
		<h3 class="page-title">
			Dashboard <small>statistics and more</small>
		</h3>
		 -->
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> 
			<a href="${ctx}">Dashboard</a> 
			<i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">上级菜单名称</a>
			<i class="fa fa-angle-right"></i>
			</li>
			<li><a href="${ctx}">当前菜单</a></li>
			<!-- 
			<li class="pull-right">
				<div id="dashboard-report-range"
					class="dashboard-date-range tooltips" data-placement="top"
					data-original-title="Change dashboard date range">
					<i class="fa fa-calendar"></i> <span> </span> <i
						class="fa fa-angle-down"></i>
				</div>
			</li>
			 -->
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
	</div>
<!-- END PAGE HEADER-->