<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><% 
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer-Form</title>
</head>
<body>
<h1>Customer-Form</h1>
<form:form name="customer-form" method="post" acceptCharset="utf-8"
	commandName="customer"  
	action="<%= request.getContextPath() %>/customer/customer-form.do">
<ul>
	<li>
		<form:label path="firstName">Pr&eacute;nom :</form:label>
		<form:input path="firstName" />
	</li>
	<li>
		<form:label path="lastName">Nom :</form:label>
		<form:input path="lastName" />		
	</li>
</ul>
<div class="buttons">
	<input type="submit" value="valider" />
</div>
</form:form>
</body>
</html>