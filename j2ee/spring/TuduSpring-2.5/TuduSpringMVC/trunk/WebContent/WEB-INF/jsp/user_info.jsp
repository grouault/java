<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="user.info.title"/></h3>

<%
for(java.util.Enumeration e=request.getAttributeNames();e.hasMoreElements();) {
	String key=(String)e.nextElement();
	Object value=request.getAttribute(key);
	System.out.println("- "+key+" : "+value);
}
%>

 <spring:bind path="userinfo">
  <font color="red">
    <b><c:out value="${status.value}"/></b>
    <b><c:out value="${status.errorMessage}"/></b>
  </font>
 </spring:bind>

 <form action="<c:url value="/secure/myInfo.action"/>" method="POST" focus="firstName">
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="form.success"/></span>
 </c:if>
 <table class="list" style="width:400px">
  <tr>
   <th colspan="2">
    <fmt:message key="user.info.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.first.name"/>
    </td>
    <td>
     <spring:bind path="userinfo.firstName">
      <input type="text" name="firstName" value="<c:out value="${status.value}"/>" size="15" maxlength="60"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.last.name"/>
    </td>
    <td>
     <spring:bind path="userinfo.lastName">
      <input type="text" name="lastName" value="<c:out value="${status.value}"/>" size="15" maxlength="60"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.email"/>
    </td>
    <td>
     <spring:bind path="userinfo.email">
      <input type="text" name="email" value="<c:out value="${status.value}"/>" size="30" maxlength="100"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.password"/>
    </td>
    <td>
     <spring:bind path="userinfo.password">
      <input type="password" name="password" value="<c:out value="${status.value}"/>" size="15" maxlength="32"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.verifypassword"/>
    </td>
    <td>
     <spring:bind path="userinfo.verifyPassword">
      <input type="password" name="verifyPassword" value="<c:out value="${status.value}"/>" size="15" maxlength="32"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <input type="submit" value="<fmt:message key="form.submit"/>"/>
  <input type="button" onclick="document.location.href='<c:url value="../welcome.action"/>';" value="<fmt:message key="form.cancel"/>"/>
 </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
