<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%
 request.setAttribute("ctx", request.getContextPath()); 
 %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Tudu Lists</title>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
    <link href="${ctx}/css/global.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" media="screen"
        href="${ctx}/css/tabs.css" />
    <script type="text/javascript" src="${ctx}/scripts/tabs.js"></script>
<%
 if (request.isUserInRole("ROLE_USER")) {
%>
    <script type="text/javascript" src="${ctx}/secure/dwr/engine.js"></script>
    <script type="text/javascript" src="${ctx}/secure/dwr/util.js"></script>
</head>
<body marginwidth="0" marginheight="0" class="composite" onload="initMenu();DWRUtil.useLoadingMessage();">
<menu:useMenuDisplayer 	name="TabbedMenu" bundle="messages">
  						<!-- bundle="org.apache.struts.action.MESSAGE" -->
  						
  <menu:displayMenu name="Info"/>
  <menu:displayMenu name="TodoLists"/>
  <menu:displayMenu name="Todos"/>
  <%
   if (request.isUserInRole("ROLE_ADMIN")) {
  %>
   <menu:displayMenu name="Administration"/>
   <menu:displayMenu name="Monitoring"/>
  <%
   }
  %>
  <menu:displayMenu name="Logout"/>
</menu:useMenuDisplayer>
<%
 } else {
%>
</head>
<body class="composite" onload="initMenu();">
<menu:useMenuDisplayer 	name="TabbedMenu" bundle="messages">
  						<!-- bundle="org.apache.struts.action.MESSAGE" -->
  						
  <menu:displayMenu name="Welcome"/>
  <menu:displayMenu name="Register"/>
</menu:useMenuDisplayer>
<%
 }
%>
 <table align="center" style="width: 95%">
  <tr>
   <td>
    <div id="head">Tudu Lists</div>
   </td>
   <td>
    <% if (request.getRemoteUser() != null) { %>
     <div style="text-align: right"><fmt:message key="header.user"/> <%=request.getRemoteUser()%></div>
    <% } %>
   </td>
  </tr>
  <tr>
   <td class="content" align="center" colspan="2">
    <div id="content">
    