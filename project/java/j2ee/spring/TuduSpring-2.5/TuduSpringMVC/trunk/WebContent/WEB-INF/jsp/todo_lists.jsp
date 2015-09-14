<%@ include file="/WEB-INF/jspf/header.jsp"%>

<script type='text/javascript' src='${ctx}/secure/dwr/interface/todo_lists.js'></script>

<script type="text/javascript">
// Show the "add a new todo list" layer.
function showAddTodoList() {
 hideTodoListsLayers();
 document.getElementById("addNewListDiv").style.display='inline';
 document.forms.addNewListForm.name.focus();
}

// Show the "edit a todo list" layer.
function showEditTodoList(listId) {
 hideTodoListsLayers();
 document.forms.editListForm.listId.value = listId;
 document.getElementById("editListDiv").style.display='inline';
 DWREngine.beginBatch();
 todo_lists.getTodoListName(replyEditTodoListName, listId);
 todo_lists.getTodoListRss(replyEditTodoListRss, listId);
 todo_lists.getTodoListUsers(replyEditTodoListUsers, listId);
 DWREngine.endBatch();
 document.forms.editListForm.name.focus();
}

var replyEditTodoListName = function(data) {
 document.forms.editListForm.name.value = DWRUtil.toDescriptiveString(data, 1);
}  

var replyEditTodoListRss = function(data) {
 var rssAllowed = DWRUtil.toDescriptiveString(data, 1);
 if (rssAllowed == 1) {
  document.forms.editListForm.rssAllowed.checked = true;
 } else {
  document.forms.editListForm.rssAllowed.checked = false;
 }
}  

var replyEditTodoListUsers = function(data) {
 DWRUtil.removeAllRows("usersTableBody");
 DWRUtil.addRows("usersTableBody", data, [ direct, deleteTodoListUserLink ]);
} 

function direct(data) {
 return data;
}

function deleteTodoListUserLink(data) {
 return "<a href=\"javascript:deleteTodoListUser('" + data + "')\">Remove</a>";
}

// Add a TodoList user.
function addTodoListUser() {
 var listId = document.forms.editListForm.listId.value;
 var login = document.forms.editListForm.login.value;
 DWREngine.beginBatch();
 todo_lists.addTodoListUser(replyAddTodoListUser,listId, login);
 todo_lists.getTodoListUsers(replyEditTodoListUsers, listId);
 DWREngine.endBatch();
}

var replyAddTodoListUser = function(data) {
 if (data != null && typeof data == 'object') {
  alert(DWRUtil.toDescriptiveString(data, 2));
 } else {
  var errorMsg = DWRUtil.toDescriptiveString(data, 1);
  if (errorMsg == "ObjectRetrievalFailureException") {
   alert("<fmt:message key="todo.lists.edit.add.error"/>");
  }
 }
}

// Delete a TodoList user.
function deleteTodoListUser(login) {
 var listId = document.forms.editListForm.listId.value;
 DWREngine.beginBatch();
 todo_lists.deleteTodoListUser(listId, login);
 todo_lists.getTodoListUsers(replyEditTodoListUsers, listId);
 DWREngine.endBatch();
}

// Hide the "add" and "edit" layers.
function hideTodoListsLayers() {
 document.getElementById("addNewListDiv").style.display='none';
 document.forms.addNewListForm.name.value = '';
 document.forms.addNewListForm.rssAllowed.checked = false;
 document.getElementById("editListDiv").style.display='none';
 document.forms.editListForm.name.value = '';
 document.forms.editListForm.login.value = '';
}

//Render the main todo lists table.
function renderTable() {
 todo_lists.renderTodoLists(replyRenderTable);
}
var replyRenderTable = function(data) {
 DWRUtil.setValue('todoListsTable', DWRUtil.toDescriptiveString(data, 1));
}

//Add a todo list
function addTodoList(name) {
 var name = document.forms.addNewListForm.name.value;
 var rssAllowed = 0;
 if (document.forms.addNewListForm.rssAllowed.checked) {
  rssAllowed = 1;
 }
 hideTodoListsLayers();
 todo_lists.addTodoList(replyRenderTable, name, rssAllowed);
}

// Edit a todo list name.
function editTodoListName() {
 var listId = document.forms.editListForm.listId.value;
 var name = document.forms.editListForm.name.value;
 todo_lists.editTodoListName(replyRenderTable, listId, name);
}

// Update the RSS allowed attribute
function updateRssAllowed() {
 var listId = document.forms.editListForm.listId.value;
 var rssAllowed = 0;
 if (document.forms.editListForm.rssAllowed.checked) {
  rssAllowed = 1;
 }
 todo_lists.updateRssAllowed(replyRenderTable, listId, rssAllowed);
}

// Delete a todo list.
function deleteTodoList(listId) {
 hideTodoListsLayers();
 var sure = confirm("<fmt:message key="todo.lists.delete.confirm"/>");
 if (sure) {
  todo_lists.deleteTodoList(replyRenderTable, listId);
 }
}

</script>

[ <a href="javascript:showAddTodoList()"><fmt:message key="todo.lists.actions.add"/></a> ]
<br/><br/>
<div id="addNewListDiv" style="display: none">
 <form name="addNewListForm">
  <table class="list" style="width:350px">
   <tr>
    <th colspan="2">
     <fmt:message key="todo.lists.actions.add"/>
    </th>
   </tr>
   <tr>
    <td>
     <fmt:message key="todo.lists.name"/>
    </td>
    <td>
     <input type="text" name="name" size="25" maxlength="50"/>
    </td>
   </tr>
   <tr>
    <td>
     <fmt:message key="todo.lists.edit.rss"/>
    </td>
    <td>
     <input type="checkbox" name="rssAllowed"/>
    </td>
   </tr>
   <tr>
    <td colspan="2" style="text-align: center;">
     [ <a href="javascript:addTodoList();"><fmt:message key="form.submit"/></a> ]
     &nbsp;&nbsp;
     [ <a href="javascript:hideTodoListsLayers();"><fmt:message key="form.cancel"/></a> ]
    </td>
   </tr>
  </table>
  <br/>
 </form>
</div>

<div id="editListDiv" style="display: none">
 <form name="editListForm" focus="name">
  <table class="list" style="width:500px">
   <tr>
    <td colspan="2" style="text-align: right;">
     [ <a href="javascript:hideTodoListsLayers();"><fmt:message key="common.hide"/></a> ]
    </td>
   </tr>
   <tr>
    <th colspan="2">
     <fmt:message key="todo.lists.edit.title"/>
     <input type="hidden" name="listId"/>
    </th>
   </tr>
   <tr>
    <td>
     <fmt:message key="todo.lists.name"/>
    </td>
    <td>
     <input type="text" name="name" size="20"/>
     &nbsp;&nbsp;
     <a href="javascript:editTodoListName();"><fmt:message key="todo.lists.edit.name"/></a>
    </td>
   </tr>
   <tr>
    <td>
     <fmt:message key="todo.lists.edit.rss"/>
    </td>
    <td>
     <input type="checkbox" onclick="updateRssAllowed();" name="rssAllowed"/>
    </td>
   </tr>
   <th colspan="2">
     <fmt:message key="todo.lists.edit.users"/>
    </th>
   <tr>
    <td>
     <fmt:message key="todo.lists.edit.add"/>
    </td>
    <td>
     <input type="text" name="login" size="20" maxlength="50"/>
     &nbsp;&nbsp;
     <a href="javascript:addTodoListUser();"><fmt:message key="todo.lists.edit.submit"/></a>
    </td>
   </tr>
   <tr>
    <td style="vertical-align: top">
     <fmt:message key="todo.lists.edit.currentusers"/>
    </td>
    <td>
     <table id="usersTable">
      <tbody style="text-align: left;" id="usersTableBody"></tbody>
     </table>
    </td>
   </tr>
  </table>
  <br/>
 </form>
</div>

<div id="todoListsTable"></div>

<script type="text/javascript">
 // The todo lists table should be refreshed automatically every 2 minutes.
 function reloadingTable() {
  renderTable();
  setTimeout('reloadingTable();', 2 * 60 * 1000);
 }
 reloadingTable();
</script>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
