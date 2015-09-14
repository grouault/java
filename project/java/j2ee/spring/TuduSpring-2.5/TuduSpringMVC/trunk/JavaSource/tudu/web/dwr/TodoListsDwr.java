package tudu.web.dwr;


/**
 * Todo Lists actions presented with DWR.
 * 
 * @author Julien Dubois
 */
public interface TodoListsDwr {
    
    /**
     * Get a Todo List name by ID.
     * 
     * @param listId The ID of the Todo List
     */
    String getTodoListName(String listId);
    
    /**
     * Get a Todo List rssAllowed attribute by ID.
     * 
     * @param listId The ID of the Todo List
     */
    String getTodoListRss(String listId);

    /**
     * Get an array containing the logins of the users currently
     * sharing the Todo List.
     * 
     * @param listId The ID of the Todo List
     */
    String[] getTodoListUsers(String listId);
    
    /**
     * Add a User to a Todo List
     * 
     * @param listId The ID of the Todo List
     * @param login The user login
     */
    String addTodoListUser(String listId, String login);
    
    /**
     * Delete a User from a Todo List
     * 
     * @param listId The ID of the Todo List
     * @param login The user login
     */
    void deleteTodoListUser(String listId, String login);
    
    /**
     * Render the Todo Lists table.
     * 
     * @return The HTML list
     */
    String renderTodoLists();
    
    /**
     * Add a new Todo List.
     * 
     * @param name The list name
     * @param rssAllowed If the RSS feed is enabled for this list
     * @return The HTML list
     */
    String addTodoList(String name, String rssAllowed);
    
    /**
     * Edit a Todo List name.
     * 
     * @param listId The ID of the Todo List
     * @param name The list name
     * @return The HTML list
     */
    String editTodoListName(String listId, String name);
    
    /**
     * Edit a Todo List rssAllowed attribute.
     * 
     * @param listId The ID of the Todo List
     * @param rssAllowed If the RSS feed is enabled for this list
     * @return The HTML list
     */
    String updateRssAllowed(String listId, String rssAllowed);
    
    /**
     * Delete a Todo List.
     * 
     * @param listId The ID of the Todo List
     * @return The HTML list
     */
    String deleteTodoList(String listId);
}
