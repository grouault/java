<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title>Test</title>
	<link href="<%= request.getContextPath() %>/css/manage-team.css" rel="stylesheet" type="text/css" />
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/j_spring_security_check">
  Identifiant  :<input name="j_username" value="" type="text" /><br/>
  Mot de passe :<input name="j_password" type="password" />
  <input value="Valider" type="submit" />
</form>

</body>
</html>