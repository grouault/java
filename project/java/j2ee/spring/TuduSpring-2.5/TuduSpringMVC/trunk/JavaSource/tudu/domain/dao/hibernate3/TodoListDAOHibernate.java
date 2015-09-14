package tudu.domain.dao.hibernate3;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tudu.domain.dao.TodoListDAO;
import tudu.domain.model.TodoList;

/**
 * Hibernate implementation of the tudu.domain.dao.TodoListDAO interface.
 * 
 * @author Julien Dubois
 */
public class TodoListDAOHibernate extends HibernateDaoSupport implements
        TodoListDAO {

    /**
     * Find a Todo List by ID.
     * 
     * @see tudu.domain.dao.TodoListDAO#getTodoList(String)
     */
    public final TodoList getTodoList(final String listId) {
        TodoList todoList = (TodoList) getHibernateTemplate().get(
                TodoList.class, listId);
        if (todoList == null) {
            throw new ObjectRetrievalFailureException(TodoList.class, listId);
        }
        return todoList;
    }

    /**
     * Save a Todo List.
     * 
     * @see tudu.domain.dao.TodoListDAO#saveTodoList(tudu.domain.model.TodoList)
     */
    public final void saveTodoList(final TodoList todoList) {
        getHibernateTemplate().saveOrUpdate(todoList);
        if (logger.isDebugEnabled()) {
            logger.debug("listId set to: " + todoList.getListId());
        }
    }
    
    /**
     * Update a Todo List.
     * 
     * @see tudu.domain.dao.TodoListDAO#updateTodoList(tudu.domain.model.TodoList)
     */
    public final void updateTodoList(final TodoList todoList) {
        getHibernateTemplate().update(todoList);
    }

    /**
     * Delete a Todo List by ID.
     * 
     * @see tudu.domain.dao.TodoListDAO#removeTodoList(String)
     */
    public final void removeTodoList(final String listId) {
        if (logger.isDebugEnabled()) {
            logger.debug("Deleting Todo List with ID=" + listId);
        }
        TodoList todoList = getTodoList(listId);
        getHibernateTemplate().delete(todoList);
    }
}
