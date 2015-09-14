<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<title><fmt:message key="error.title"/></title>

<h3><fmt:message key="error.title"/></h3>
<div align="left">
<p>
<pre>
<% 
    Exception ex = (Exception) request.getAttribute("exception");
    if (ex != null) {
    	if( ex instanceof org.springframework.core.NestedRuntimeException ) {
    		org.springframework.core.NestedRuntimeException tmpEx
    					= (org.springframework.core.NestedRuntimeException)ex;
    		Throwable nestedEx = tmpEx.getCause();
	        ex.printStackTrace(new java.io.PrintWriter(out));
	        out.println("<hr/>");
	        nestedEx.printStackTrace(new java.io.PrintWriter(out));
    	} else {
	        ex.printStackTrace(new java.io.PrintWriter(out));
    	}
    }
%>
</pre>
<hr/>
<pre>
<%
    if (exception !=null) {
        if (exception instanceof org.springframework.orm.ObjectRetrievalFailureException) {
%>
<fmt:message key="error.object.retrieval"/>
<%
        } else {
        	exception.printStackTrace(new java.io.PrintWriter(out));
        }
    }
%>
</pre>
</p>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
