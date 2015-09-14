<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath()); 
 request.setAttribute("now", java.util.Calendar.getInstance().getTime());
 %>
 
<c:if test="${not empty todoList}">
 <div style="font: 18px/1.3em Arial,Helvetica,sans-serif;">${todoList.name}</div>
 [ <a href="javascript:showAddTodo()"><fmt:message key="todos.actions.add"/></a> ]
 <br/><br/>
</c:if>
<c:if test="${empty todos}">
 <div style="font: 18px/1.3em Arial,Helvetica,sans-serif; width: 600px"><fmt:message key="todos.nothing.to.display"/></div>
 <br/><br/>
</c:if>
<c:if test="${not empty todos}">
 <table align="center">
  <tr>
   <td style="padding: 0px;width: ${completion * 10}px;background-color: #8080FF;">
   </td>
   <td style="padding: 0px;width: ${1000 - completion * 10}px;background-color: #FF8080;">
   </td>
   <td>
    &nbsp;(${completion}%)
   </td>
  </tr>
 </table>
 <table class="list">
  <tr>
   <th style="width: auto; text-align: center"><fmt:message key="todos.description"/></th>
   <th style="width: 80px; text-align: center"><fmt:message key="todos.priority"/></th>
   <th style="width: 80px; text-align: center"><fmt:message key="todos.due.date"/></th>
   <th style="width: 90px; text-align: center"><fmt:message key="todos.completed"/></th>
   <th style="width: 120px; text-align: center"><fmt:message key="todos.actions"/></th>
  </tr>
  <c:set var="row" value="0"/>
  <c:forEach var="todo" items="${todos}">
   <c:set var="row" value="${row + 1}"/>
   <c:set var="trStyle" value="${row % 2 eq 0 ? 'even' : 'odd'}"/>
   <c:set var="method" value="completeTodo" scope="page"/>
   <c:set var="checked" value="" scope="page"/>
   <c:set var="style" value="" scope="page"/>
   <c:if test="${todo.completed}">
    <c:set var="method" value="reopenTodo" scope="page"/>
    <c:set var="checked" value="checked" scope="page"/>
    <c:set var="tdStyle" value="completed" scope="page"/>
   </c:if>
   <tr class="${trStyle}" id="${todo.todoId}">
    <td class="${tdStyle}">
     <c:if test="${not(todo.completed) and (todo.dueDate.time le now.time)}">
      <img src="${ctx}/images/warning.gif" width="14" height="13" alt="!"/>
     </c:if>
     ${todo.description}
    </td>
    <td class="${tdStyle}" style="text-align: center">
     ${todo.priority}
    </td>
    <td class="${tdStyle}" style="text-align: center; font-size: 80%">
     <fmt:formatDate value="${todo.dueDate}" type="date" pattern="MM/dd/yyyy"/>
    </td>
    <td class="${tdStyle}" style="text-align: center">
     <input type="checkbox" 
            onClick="${method}('${todo.todoId}')" ${checked}>
    </td>
    <td class="${tdStyle}">
    [ <a href="javascript:showEditTodo('${todo.todoId}')"><fmt:message key="todos.actions.edit"/></a> |
     <a href="javascript:deleteTodo('${todo.todoId}')"><fmt:message key="todos.actions.delete"/></a> ]
    </td>
   </tr>
  </c:forEach>
 </table>
 <br/><br/>
</c:if>
<c:if test="${not empty todoList}">
 <c:if test="${todoList.rssAllowed eq true}">
  <a href="${ctx}/rss/showRssFeed.action?listId=${todoList.listId}">
   <img width="30" height="14" alt="RSS" src="${ctx}/images/rss.gif" border="0"/>
  </a>
  <link rel="alternate" title="RSS feed" href="${ctx}/rss/showRssFeed.action?listId=${todoList.listId}" TYPE="application/rss+xml">
  | 
 </c:if>
 <a href="${ctx}/secure/backupTodoList.action?listId=${todoList.listId}">Backup <img width="9" height="10" alt="Backup" src="${ctx}/images/asc.gif" border="0"/></a> | 
 <a href="${ctx}/secure/restoreTodoList.action?listId=${todoList.listId}">Restore <img width="9" height="10" alt="Restore" src="${ctx}/images/desc.gif" border="0"/></a>
</c:if>
 