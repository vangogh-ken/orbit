<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
		
		<!-- BEGIN FLASH MSG -->
		<c:if test="${not empty flashMessages}">
			<div id="m-success-message" style="display:none;">
			  <ul>
			  <c:forEach items="${flashMessages}" var="item">
			    <li>${item}</li>
			  </c:forEach>
			  </ul>
			</div>
		</c:if>
		<!-- END FLASH MSG -->
	
		<!-- BEGIN LOGO -->
		<a class="navbar-brand" href="${ctx}/basis/dashboard" style="top:-20px;">
			<img src="${ctx}/s2/assets/img/logo.png" alt="chinacloud" class="img-responsive" height="20px" width="120px" />
		</a>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse"> <img src="${ctx}/s2/assets/img/menu-toggler.png" alt="" />
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN NOTIFICATION DROPDOWN -->
			<!--  
			<li class="dropdown" id="header_notification_bar"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true"> <i class="fa fa-warning"></i> <span
					class="badge"> 6 </span>
			</a>
				<ul class="dropdown-menu extended notification">
					<li>
						<p>You have 14 new notifications</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li><a href="#"> <span
									class="label label-sm label-icon label-success"> <i
										class="fa fa-plus"></i>
								</span> New user registered. <span class="time"> Just now </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-danger"> <i
										class="fa fa-bolt"></i>
								</span> Server #12 overloaded. <span class="time"> 15 mins </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-warning"> <i
										class="fa fa-bell-o"></i>
								</span> Server #2 not responding. <span class="time"> 22 mins </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-info"> <i
										class="fa fa-bullhorn"></i>
								</span> Application error. <span class="time"> 40 mins </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-danger"> <i
										class="fa fa-bolt"></i>
								</span> Database overloaded 68%. <span class="time"> 2 hrs </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-danger"> <i
										class="fa fa-bolt"></i>
								</span> 2 user IP blocked. <span class="time"> 5 hrs </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-warning"> <i
										class="fa fa-bell-o"></i>
								</span> Storage Server #4 not responding. <span class="time"> 45
										mins </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-info"> <i
										class="fa fa-bullhorn"></i>
								</span> System Error. <span class="time"> 55 mins </span>
							</a></li>
							<li><a href="#"> <span
									class="label label-sm label-icon label-danger"> <i
										class="fa fa-bolt"></i>
								</span> Database overloaded 68%. <span class="time"> 2 hrs </span>
							</a></li>
						</ul>
					</li>
					<li class="external"><a href="#"> See all notifications <i
							class="m-icon-swapright"></i>
					</a></li>
				</ul></li>
				-->
			<!-- END NOTIFICATION DROPDOWN -->
			
			<!-- BEGIN INBOX DROPDOWN 
			<li class="dropdown" id="header_inbox_bar"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true"> <i class="fa fa-envelope"></i> <span
					class="badge" id="unreadMailsSizeSpan">${unreadMails.size()}</span>
			</a>
				<ul class="dropdown-menu extended inbox">
					<li>
						<p id="unreadMailsSizeP">你有${unreadMails == null ? 0 : unreadMails.size()}封未读邮件</p>
					</li>
					<li>
						<ul id="unreadMailsUl" class="dropdown-menu-list scroller" style="height: 250px;">
							<c:forEach items="${unreadMails}" var="mail" varStatus="varStatus">
							<c:if test="${varStatus.index < 10}">
							<li>
								<a href="${ctx}/out/mail-receive-view.do?id=${mail.id}">
								<span class="photo"><img src="${ctx}/s2/assets/img/avatar1.jpg" alt="" /></span> 
								<span class="subject"> 
								<span class="from">${mail.addressFrom}</span> 
								<span class="time">${mail.status}</span>
								</span> 
								<span class="message">${mail.subject}</span>
								</a>
							</li>
							</c:if>
							</c:forEach>
						</ul>
					</li>
					<li class="external">
					<a href="${ctx}/out/mail-receive-list.do">查看所有<i class="m-icon-swapright"></i></a>
					</li>
				</ul></li>
				-->
			<!-- END INBOX DROPDOWN -->
			
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> 
					<img alt="" src="${ctx}/icon/${userSession.icon}" style="height: 29px;width:29px;">
					<span class="username">${userSession.displayName}</span> 
					<i class="fa fa-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li>
					<a href="${ctx}/user/user-profile.do">
						<i class="fa fa-user"></i> 个人资料
					</a>
					</li>
					<li>
					<a href="${ctx}/user/change-password-input.do">
						<i class="fa fa-unlock-alt"></i> 修改密码
					</a>
					</li>
					<!--  
					<li><a href="inbox.html">
							<i class="fa fa-envelope"></i> My Inbox <span
							class="badge badge-danger"> 3 </span>
					</a>
					</li>
					<li><a href="#"> <i class="fa fa-tasks"></i> My Tasks <span
							class="badge badge-success"> 7 </span>
					</a>
					</li>
					-->
					<li class="divider"></li>
					<li><a href="${ctx}/base/login.do"><i class="fa fa-key"></i>安全退出</a></li>
				</ul>
			  </li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->