package tudu.web.dwr.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectRetrievalFailureException;

import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.security.PermissionDeniedException;
import tudu.service.TodoListsManager;
import tudu.service.UserManager;
import tudu.web.dwr.TodoListsDwr;
import uk.ltd.getahead.dwr.ExecutionContext;

/**
 * Implementation of the tudu.service.TodoListsManager interface.
 * 
 * @author Julien Dubois
 */
public class TodoListsDwrImpl extends AbstractCommonDwr implements TodoListsDwr {
    
    private final Log log = LogFactory.getLog(TodoListsDwrImpl.class);

    private TodoListsManager todoListsManager = null;
    
    private UserManager userManager = null;

    public void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#getTodoListName(java.lang.String)
     */
    public String getTodoListName(String listId) {
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            return todoList.getName();
        } catch (PermissionDeniedException pde) {
            return "";
        }
    }
    
    /**
     * @see tudu.web.dwr.TodoListsDwr#getTodoListRss(java.lang.String)
     */
    public String getTodoListRss(String listId) {
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            if (todoList.isRssAllowed()) {
                return "1";
            } else {
                return "0";
            }
        } catch (PermissionDeniedException pde) {
            return "";
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#getTodoListUsers(java.lang.String)
     */
    public String[] getTodoListUsers(String listId) {
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            String currentLogin = userManager.getCurrentUser().getLogin();
            Collection<User> users = todoList.getUsers();
            Collection<String> logins = new TreeSet<String>();
            for (User user : users) {
                if (!currentLogin.equals(user.getLogin())) {
                    logins.add(user.getLogin());
                }
            }
            return (String[]) logins.toArray(new String[logins.size()]);
        } catch (PermissionDeniedException pde) {
            return new String[0];
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#addTodoListUser(java.lang.String, java.lang.String)
     */
    public String addTodoListUser(String listId, String login) {
        login = login.toLowerCase();
        try {
            todoListsManager.addTodoListUser(listId, login);
        } catch (ObjectRetrievalFailureException orfe) {
            return "ObjectRetrievalFailureException";
        } catch (PermissionDeniedException pde) {
            return "";
        }
        return "";
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#deleteTodoListUser(java.lang.String, java.lang.String)
     */
    public void deleteTodoListUser(String listId, String login) {
        login = login.toLowerCase();
        try {
            todoListsManager.deleteTodoListUser(listId, login);
        } catch (PermissionDeniedException pde) {
            // ignore the exception
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#renderTodoLists()
     */
    public String renderTodoLists() {
        log.debug("Render AJAX table");
        HttpServletRequest request = ExecutionContext.get().getHttpServletRequest();
        User user = userManager.getCurrentUser();
        Set<TodoList> todoLists = user.getTodoLists();
        request.setAttribute("todoLists", new TreeSet<TodoList>(todoLists));
        try {
            return ExecutionContext.get().forwardToString(
                    "/WEB-INF/jspf/todo_lists_table.jsp");
        } catch (ServletException e) {
            log.error("ServletException : " + e);
            return "";
        } catch (IOException ioe) {
            log.error("IOException : " + ioe);
            return "";
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#addTodoList(java.lang.String, java.lang.String)
     */
    public String addTodoList(String name, String rssAllowed) {
        log.debug("Execute addTodoList action");
        boolean rssAllowedBool = false;
        if (rssAllowed != null && rssAllowed.equals("1")) {
            rssAllowedBool = true;
        }
        TodoList todoList = new TodoList();
        todoList.setName(name);
        todoList.setRssAllowed(rssAllowedBool);
        todoListsManager.createTodoList(todoList);
        return renderTodoLists();
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#editTodoListName(java.lang.String,
     *      java.lang.String)
     */
    public String editTodoListName(String listId, String name) {
        log.debug("Execute editTodoList action");
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            if (name != null && !name.equals("")) {
                todoList.setName(name);
            }
            todoListsManager.updateTodoList(todoList);
            return renderTodoLists();
        } catch (PermissionDeniedException pde) {
            return renderPermissionException();
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#updateRssAllowed(java.lang.String,
     *      java.lang.String)
     */
    public String updateRssAllowed(String listId, String rssAllowed) {
        log.debug("Execute editTodoList action");
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            if (rssAllowed != null && !rssAllowed.equals("")) {
                boolean rssAllowedBool = false;
                if (rssAllowed.equals("1")) {
                    rssAllowedBool = true;
                }
                todoList.setRssAllowed(rssAllowedBool);
            }
            todoListsManager.updateTodoList(todoList);
            return renderTodoLists();
        } catch (PermissionDeniedException pde) {
            return renderPermissionException();
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#deleteTodoList(java.lang.String)
     */
    public String deleteTodoList(String listId) {
        log.debug("Execute deleteTodoList action");
        try {
            todoListsManager.deleteTodoList(listId);
            return renderTodoLists();
        } catch (PermissionDeniedException pde) {
            return renderPermissionException();
        }
    }
}
