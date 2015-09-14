<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="recover.password.title"/></h3>
 <form action="<c:url value="/recoverPassword.action"/>" method="POST" focus="login">
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="recover.password.success"/></span>
 </c:if>
 
 <spring:bind path="recover">
  <font color="red">
    <b><c:out value="${status.errorMessage}"/></b>
  </font>
 </spring:bind>

 <table class="list" style="width:250px">
  <tr>
   <th colspan="2">
    <fmt:message key="recover.password.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.login"/> *
    </td>
    <td>
     <spring:bind path="recover.login">
       <input type="text" name="login" value="<c:out value="${status.value}"/>" size="20" maxlength="50"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <input type="submit" value="<fmt:message key="form.submit"/>"/>
  <input type="button" onclick="document.location.href='<c:url value="welcome.action"/>';" value="<fmt:message key="form.cancel"/>"/>
 </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
