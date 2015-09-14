package tudu.service;

import tudu.domain.model.Todo;

/**
 * Manage Todos.
 * 
 * @author Julien Dubois
 */
public interface TodosManager {

    /**
     * Find a Todo by ID.
     * 
     * @param todoId
     *            The Todo ID
     */
    Todo findTodo(String todoId);

    /**
     * Create a new Todo.
     * 
     * @param listId
     *            The ID of the Todo List to which the Todo belongs
     * @param todo
     *            The Todo to create
     */
    void createTodo(String listId, Todo todo);

    /**
     * Update a Todo.
     * 
     * @param todo
     *            The Todo to update
     */
    void updateTodo(Todo todo);

    /**
     * Delete a Todo.
     * 
     * @param todoId
     *            The ID of the Todo to delete
     */
    void deleteTodo(String todoId);
    
    /**
     * Complete a Todo.
     * 
     * @param todoId The ID of the todo
     */
    Todo completeTodo(String todoId);

    /**
     * Re-open a Todo.
     * 
     * @param todoId The ID of the todo
     */
    Todo reopenTodo(String todoId);
}
