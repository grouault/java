<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><% 
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%
%>
<c:set var="root" value="${pageContext.request.contextPath}" scope="request" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="form.customer.title"/></title>
	<link href="${root}/css/general-form.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1><spring:message code="form.customer.title"/></h1>
<form:errors path="*" cssClass="errorblock" element="div"/>
<form:form name="customer-form" method="post" acceptCharset="utf-8"
	commandName="customerForm"
	action="${root}/customer/customer-form.do">

	<fieldset>
	<legend><spring:message code="form.legend.general.informations" /></legend>
		<ul>
			<li>
				
				<form:label path="firstName"><spring:message code="form.label.prenom" /></form:label>
				<form:input path="firstName" />
				<form:errors path="firstName" cssClass="error" />
			</li>
			<li>
				<form:label path="lastName"><spring:message code="form.label.nom"/></form:label>
				<form:input path="lastName" />		
				<form:errors path="lastName" cssClass="error" />
			</li>
			<li>
				<form:label path="birthdate"><spring:message code="form.label.birthdate"/></form:label>
				<form:input path="birthdate"/>
				<form:errors path="birthdate" cssClass="error" />
			</li>
		</ul>
	</fieldset>
	<div class="buttons">
		<input type="submit" value='<spring:message code="form.button.valider"/>' />
	</div>
</form:form>
</body>
</html>