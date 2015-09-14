<%@ include file="/WEB-INF/jspf/header.jsp"%>

<%
System.out.println("######################");
for(java.util.Enumeration e=request.getAttributeNames();e.hasMoreElements();) {
	String key=(String)e.nextElement();
	Object value=request.getAttribute(key);
	System.out.println("- "+key+" : "+value);
}
System.out.println("######################");
%>

<div align="center">
 <h3><fmt:message key="register.title"/></h3>
 <div style="width:350px">
  <ul>
   <li>
    <fmt:message key="register.info.1"/>
   </li>
   <li>
    <fmt:message key="register.info.2"/>
   </li>
  </ul>
 </div>

 <spring:bind path="register">
  <font color="red">
    <b><c:out value="${status.errorMessage}"/></b>
  </font>
 </spring:bind>

 <form focus="login" action="<c:url value="/register.action"/>" method="POST">
 <table class="list" style="width:400px">
  <tr>
   <th colspan="2">
    <fmt:message key="register.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.login"/> *
    </td>
    <td>
     <spring:bind path="register.login">
      <input type="text" name="login" value="<c:out value="${status.value}"/>" size="20" maxlength="50"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.first.name"/> *
    </td>
    <td>
     <spring:bind path="register.firstName">
      <input type="text" name="firstName" value="<c:out value="${status.value}"/>" size="15" maxlength="60"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.last.name"/> *
    </td>
    <td>
     <spring:bind path="register.lastName">
      <input type="text" name="lastName" value="<c:out value="${status.value}"/>" size="15" maxlength="60"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.email"/>
    </td>
    <td>
     <spring:bind path="register.email">
      <input type="text" name="email" value="<c:out value="${status.value}"/>" size="30" maxlength="100"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.password"/> *
    </td>
    <td>
     <spring:bind path="register.password">
      <input type="password" name="password" value="<c:out value="${status.value}"/>" size="15" maxlength="32"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.verifypassword"/> *
    </td>
    <td>
     <spring:bind path="register.verifyPassword">
      <input type="password" name="verifyPassword" value="<c:out value="${status.value}"/>" size="15" maxlength="32"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <br/>
  <input type="submit" value="<fmt:message key="form.submit"/>">
  <input type="button" onclick="document.location.href='<c:url value="welcome.action"/>';" value="<fmt:message key="form.cancel"/>"/>
 </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
