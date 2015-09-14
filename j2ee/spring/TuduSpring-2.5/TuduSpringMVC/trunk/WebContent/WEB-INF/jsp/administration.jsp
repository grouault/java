<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="administration.title"/></h3>

 <spring:bind path="administration">
  <font color="red">
    <b><c:out value="${status.errorMessage}"/></b>
  </font>
 </spring:bind>

 <form action="<c:url value="/secure/admin/administration.action"/>" method="POST" focus="smtpHost">
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="form.success"/></span>
 </c:if>
 
 <table class="list" style="width:450px">
  <tr>
   <th colspan="2">
    <fmt:message key="administration.email"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="administration.email.host"/>
    </td>
    <td>
     <spring:bind path="administration.smtpHost">
      <input type="text" name="smtpHost" value="<c:out value="${status.value}"/>" size="30" maxlength="100"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="administration.email.port"/>
    </td>
    <td>
     <spring:bind path="administration.smtpPort">
      <input type="text" name="smtpPort" value="<c:out value="${status.value}"/>" size="30" maxlength="200"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="administration.email.user"/>
    </td>
    <td>
     <spring:bind path="administration.smtpUser">
      <input type="text" name="smtpUser" value="<c:out value="${status.value}"/>" size="30" maxlength="200"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="administration.email.password"/>
    </td>
    <td>
     <spring:bind path="administration.smtpPassword">
      <input type="text" name="smtpPassword" value="<c:out value="${status.value}"/>" size="30" maxlength="200"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="administration.email.from"/>
    </td>
    <td>
     <spring:bind path="administration.smtpFrom">
      <input type="text" name="smtpFrom" value="<c:out value="${status.value}"/>" size="30" maxlength="200"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <input type="submit" value="<fmt:message key="form.submit"/>"/>
  <input type="button" onclick="document.location.href='<c:url value="../../welcome.action"/>';" value="<fmt:message key="form.cancel"/>"/>
 </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
