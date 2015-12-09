<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.cc.automate.core.Constant" %>
<%
//pageContext.setAttribute(Constant.USER_ID,  request.getSession().getAttribute(Constant.USER_ID));
pageContext.setAttribute(Constant.USER_ID,  "admin");
%>
<%pageContext.setAttribute("ctx",  request.getContextPath());%>
<%pageContext.setAttribute("locale",  request.getLocale());%>
<%pageContext.setAttribute("currentUser",  request.getSession().getAttribute("userSession"));%>
<%pageContext.setAttribute("currentResource",  request.getSession().getAttribute("currentResource"));%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
