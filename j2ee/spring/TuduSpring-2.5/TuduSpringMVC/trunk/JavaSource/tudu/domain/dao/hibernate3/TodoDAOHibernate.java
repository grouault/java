package tudu.domain.dao.hibernate3;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tudu.domain.dao.TodoDAO;
import tudu.domain.model.Todo;

/**
 * Hibernate implementation of the tudu.domain.dao.TodoDAO interface.
 * 
 * @author Julien Dubois
 */
public class TodoDAOHibernate extends HibernateDaoSupport implements TodoDAO {

    /**
     * Find a Todo by ID.
     * 
     * @see tudu.domain.dao.TodoDAO#getTodo(String)
     */
    public Todo getTodo(String todoId) {
        Todo todo = (Todo) getHibernateTemplate().get(Todo.class, todoId);
        if (todo == null) {
            throw new ObjectRetrievalFailureException(Todo.class, todoId);
        }
        return todo;
    }

    /**
     * Save a Todo.
     * 
     * @see tudu.domain.dao.TodoDAO#saveTodo(tudu.domain.model.Todo)
     */
    public void saveTodo(Todo todo) {
        getHibernateTemplate().saveOrUpdate(todo);
        if (logger.isDebugEnabled()) {
            logger.debug("todoId set to: " + todo.getTodoId());
        }
    }

    /**
     * Delete a Todo.
     * 
     * @see tudu.domain.dao.TodoDAO#removeTodo(String)
     */
    public void removeTodo(String todoId) {
        if (logger.isDebugEnabled()) {
            logger.debug("Deleting Todo with ID=" + todoId);
        }
        getHibernateTemplate().delete(getTodo(todoId));
    }
}
