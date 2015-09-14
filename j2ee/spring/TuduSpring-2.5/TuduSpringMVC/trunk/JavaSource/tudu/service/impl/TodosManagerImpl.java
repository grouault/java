package tudu.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tudu.domain.dao.TodoDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.security.PermissionDeniedException;
import tudu.service.TodoListsManager;
import tudu.service.TodosManager;
import tudu.service.UserManager;

/**
 * Implementation of the tudu.service.TodosManager interface.
 * 
 * @author Julien Dubois
 */
public class TodosManagerImpl implements TodosManager {

    private final Log log = LogFactory.getLog(TodosManagerImpl.class);

    private TodoDAO todoDAO = null;

    private TodoListsManager todoListsManager = null;
    
    private UserManager userManager = null;

    public void setTodoDAO(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Find a Todo by ID.
     * 
     * @see tudu.service.TodosManager#findTodo(java.lang.String)
     */
    public Todo findTodo(final String todoId) {
        if (log.isDebugEnabled()) {
            log.debug("Finding Todo with ID " + todoId);
        }
        Todo todo = todoDAO.getTodo(todoId);
        TodoList todoList = todo.getTodoList();
        User user = userManager.getCurrentUser();
        if (!user.getTodoLists().contains(todoList)) {
            if (log.isInfoEnabled()) {
                log.info("Permission denied when finding Todo ID '" + todoId
                        + "' for User '" + user.getLogin() + "'");
            }

            throw new PermissionDeniedException(
                    "Permission denied to access this Todo.");
            
        }
        return todo;
    }

    /**
     * Create a new Todo.
     * 
     * @see tudu.service.TodosManager#createTodo(
     *      java.lang.String listId, tudu.domain.model.Todo)
     */
    public void createTodo(final String listId,
            final Todo todo) {

        if (log.isDebugEnabled()) {
            log.debug("Creating a new Todo with description "
                    + todo.getDescription());
        }
        Date now = Calendar.getInstance().getTime();
        todo.setCreationDate(now);
        TodoList todoList = todoListsManager.findTodoList(listId);
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        todoDAO.saveTodo(todo);
        todoListsManager.updateTodoList(todoList);
    }

    /**
     * Update a Todo.
     * 
     * @see tudu.service.TodosManager#updateTodo(tudu.domain.model.Todo)
     */
    public void updateTodo(final Todo todo) {
        if (log.isDebugEnabled()) {
            log.debug("Update the Todo with id " + todo.getTodoId());
        }
        todoDAO.saveTodo(todo);
    }

    /**
     * Delete a Todo.
     * 
     * @see tudu.service.TodosManager#deleteTodo(java.lang.String)
     */
    public void deleteTodo(final String todoId) {
        if (log.isDebugEnabled()) {
            log.debug("Delete the Todo with id " + todoId);
        }
        Todo todo = this.findTodo(todoId);
        TodoList todoList = todo.getTodoList();
        todoList.getTodos().remove(todo);
        todoDAO.removeTodo(todoId);
    }

    /**
     * @see tudu.service.TodosManager#completeTodo(java.lang.String)
     */
    public Todo completeTodo(String todoId) {
        Todo todo = this.findTodo(todoId);
        todo.setCompleted(true);
        todo.setCompletionDate(Calendar.getInstance().getTime());
        return todo;
    }

    /**
     * @see tudu.service.TodosManager#reopenTodo(java.lang.String)
     */
    public Todo reopenTodo(String todoId) {
        Todo todo = this.findTodo(todoId);
        todo.setCompleted(false);
        todo.setCompletionDate(null);
        return todo;
    }

}
