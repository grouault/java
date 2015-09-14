<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath()); 
 %>
 
<c:if test="${not empty todoLists}">
 <table class="list" style="width: 550px">
  <tr>
   <td colspan="3" style="text-align: right;">
    [ <a href="javascript:renderTable()"><fmt:message key="common.refresh"/></a> ]
   </td>
  </tr>
  <tr>
   <th style="width: 350px"><fmt:message key="todo.lists.name"/></th>
   <th style="width: 80px"><fmt:message key="todo.lists.rss"/></th>
   <th style="width: 120px"><fmt:message key="todo.lists.actions"/></th>
  </tr>
  <c:set var="row" value="0"/>
  <c:forEach var="todoList" items="${todoLists}">
   <c:set var="row" value="${row + 1}"/>
   <c:set var="trStyle" value="${row % 2 eq 0 ? 'even' : 'odd'}"/>
   <c:set var="style" value="" scope="page"/>
   <tr class="${trStyle}" id="${todoList.listId}">
    <td class="${tdStyle}">
     ${todoList.name}
    </td>
    <td class="${tdStyle}">
     <c:if test="${todoList.rssAllowed eq true}">
      <img width="30" height="14" alt="ok" src="${ctx}/images/rss.gif"/>
     </c:if>
    </td>
    <td class="${tdStyle}">
     [ <a href="javascript:showEditTodoList('${todoList.listId}')"><fmt:message key="todo.lists.actions.edit"/></a> |
     <a href="javascript:deleteTodoList('${todoList.listId}')"><fmt:message key="todo.lists.actions.delete"/></a> ]
    </td>
   </tr>
  </c:forEach>
 </table>
</c:if>
