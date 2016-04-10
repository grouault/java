<%@page import="java.io.PrintWriter, java.util.Enumeration"%><%@ 
page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"
%><%
if (exception == null) {
	exception = (Throwable) request.getAttribute("exception");
}
if (exception == null) {
	exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
}
if (exception == null) {
	exception = (Throwable) request.getAttribute("javax.servlet.jsp.jspException");
}
if (exception != null) {
	%>
	<h1><%=exception.getClass() %></h1>
	<%
	if (exception.getMessage() != null) {
		%>
		<h2><%=exception.getMessage() %></h2>
		<%
	}
	%>
	<pre>
	<%
	exception.printStackTrace(new PrintWriter(out));
	%>
	</pre>
	<%
} else {
	%>
	<h1>Exception inconnue</h1>
	<ul>
	<%
	final Enumeration attributes = request.getAttributeNames();
    if (attributes != null) {
    	while (attributes.hasMoreElements()) {
    		final String name = (String) attributes.nextElement();
    		final Object value = request.getAttribute(name);
    		%>
    		<li>getAttribute(<%=name %>) : <%=value%></li>
    		<%
    	}
    }
    %></ul><%
}
%>