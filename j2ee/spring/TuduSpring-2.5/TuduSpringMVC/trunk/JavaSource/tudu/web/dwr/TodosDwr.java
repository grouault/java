package tudu.web.dwr;

import tudu.web.dwr.bean.RemoteTodo;
import tudu.web.dwr.bean.RemoteTodoList;

/**
 * Todos actions presented with DWR.
 * 
 * @author Julien Dubois
 */
public interface TodosDwr {
    
    /**
     * Get an array containing the user's Todo Lists
     */
    RemoteTodoList[] getCurrentTodoLists();
    
    /**
     * Get a Todo by ID.
     * 
     * @param todoId The Todo ID
     * @return The Todo
     */
    RemoteTodo getTodoById(String todoId);
    
    /**
     * Render the Todo List.
     * 
     * @param listId The List ID
     * @return The HTML list
     */
    String renderTodos(String listId);
    
    /**
     * Add a new Todo.
     * 
     * @param listId The list ID
     * @param description The description
     * @param priority The priority
     * @param dueDate The due date
     * @return The HTML list
     */
    String addTodo(String listId, String description, String priority, String dueDate);
    
    /**
     * Re open a Todo.
     * 
     * @param todoId The todo ID
     * @return The HTML list
     */
    String reopenTodo(String todoId);
    
    /**
     * Complete a Todo.
     * 
     * @param todoId The todo ID
     * @return The HTML list
     */
    String completeTodo(String todoId);
    
    /**
     * Edit a Todo.
     * 
     * @param todoId The Todo ID
     * @param description The description
     * @param priority The priority
     * @param dueDate The due date
     * @return The HTML list
     */
    String editTodo(String todoId, String description, String priority, String dueDate);
    
    /**
     * Delete a Todo.
     * 
     * @param todoId The todo ID
     * @return The HTML list
     */
    String deleteTodo(String todoId);
}
