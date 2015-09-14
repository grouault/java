<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="restore.title"/></h3>

 <spring:bind path="restore">
  <font color="red">
    <b><c:out value="${status.value}"/></b>
    <b><c:out value="${status.errorMessage}"/></b>
  </font>
 </spring:bind>

 <form action="<c:url value="/secure/restoreTodoList.action"/>" method="POST" enctype="multipart/form-data">
  <spring:bind path="restore.listId">
   <input type="hidden" name="listId" value="<c:out value="${status.value}"/>"/>
  </spring:bind>
 <table class="list" style="width:600px">
  <tr>
   <th colspan="2">
    <fmt:message key="restore.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="restore.file"/>
    </td>
    <td>
     <br/>
     <spring:bind path="restore.backupFile">
      <input type="file" name="backupFile" value="<c:out value="${status.value}"/>"/>
      &#160;<font color="red"><c:out value="${status.errorMessage}"/></font>
     </spring:bind>
     <br/><br/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="restore.choice"/>
    </td>
    <td>
     <br/>
     <spring:bind path="restore.restoreChoice">
      <c:choose>
       <c:when test="${(status.value) == 'create'}">
        <input type="radio" name="restoreChoice" value="create" checked=""/>
       </c:when>
       <c:otherwise>
        <input type="radio" name="restoreChoice" value="create"/>
       </c:otherwise>
      </c:choose>
      <fmt:message key="restore.choice.create"/>
      <br/><br/>
      <c:choose>
       <c:when test="${(status.value) == 'replace'}">
        <input type="radio" name="restoreChoice" value="replace" checked=""/>
       </c:when>
       <c:otherwise>
        <input type="radio" name="restoreChoice" value="replace"/>
       </c:otherwise>
      </c:choose>
      <fmt:message key="restore.choice.replace">
       <fmt:param value="${todoList.name}"/>
      </fmt:message>
      <br/><br/>
      <c:choose>
       <c:when test="${(status.value) == 'merge'}">
        <input type="radio" name="restoreChoice" value="merge" checked=""/>
       </c:when>
       <c:otherwise>
        <input type="radio" name="restoreChoice" value="merge"/>
       </c:otherwise>
      </c:choose>
      <fmt:message key="restore.choice.merge">
       <fmt:param value="${todoList.name}"/>
      </fmt:message>
      <br/><br/>
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
