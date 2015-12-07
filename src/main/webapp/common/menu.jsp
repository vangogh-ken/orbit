<%@page language="java" pageEncoding="UTF-8" %>
<!-- 侧边菜单 -->
<div class="page-sidebar-wrapper">
	<div class="page-sidebar navbar-collapse collapse">
		<!-- add "navbar-no-scroll" class to disable the scrolling of the sidebar menu -->
		<!-- BEGIN SIDEBAR MENU -->
		<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
			<li class="sidebar-toggler-wrapper">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler hidden-phone">
				</div>
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			</li>
			<li class="sidebar-search-wrapper">
				<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
				<form class="sidebar-search" action="extra_search.html" method="POST">
					<div class="form-container">
						<div class="input-box">
							<a href="javascript:;" class="remove">
							</a>
							<input type="text" placeholder="Search..."/>
							<input type="button" class="submit" value=" "/>
						</div>
					</div>
				</form>
				<!-- END RESPONSIVE QUICK SEARCH FORM -->
			</li>
			<li class="start <c:if test="${curentUrl == '/basis/dashboard'}" >active</c:if> " >
				<a href="${ctx}/basis/dashboard">
					<i class="fa fa-home"></i>
					<span class="title">主页</span><span class="selected"></span>
				</a>
			</li>
			<li id="${curentUrl}" <c:if test="${curentUrl != '/basis/dashboard'}">class="active open"</c:if>>
			<a href="javascript:;">
				<i class="fa fa-home"></i>
				<span class="title">基础管理</span>
				<span class="arrow <c:if test="${curentUrl != '/basis/dashboard'}">open</c:if>" ></span>
				<span class="selected"></span>
			</a>
			<ul class="sub-menu">
				<li id="/basis/basis-substance-type-list" <c:if test="${curentUrl == '/basis/basis-substance-type-list'}">class="active"</c:if>>
					<a href="${ctx}/basis/basis-substance-type-list">
						<span class="fa fa-setting" style="margin-left: 0px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span>类型管理</span>
					</a>
				</li>
	         </ul>
	         </li>
		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
</div>