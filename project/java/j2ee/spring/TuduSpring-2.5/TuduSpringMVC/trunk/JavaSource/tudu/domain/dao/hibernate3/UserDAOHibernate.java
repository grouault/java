package tudu.domain.dao.hibernate3;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tudu.domain.dao.UserDAO;
import tudu.domain.model.User;

/**
 * Hibernate implementation of the tudu.domain.dao.UserDAO interface.
 * 
 * @author Julien Dubois
 */
public class UserDAOHibernate extends HibernateDaoSupport implements UserDAO {

    /**
     * Get a specific user.
     * 
     * @see tudu.domain.dao.UserDAO#getUser(String)
     */
    public final User getUser(final String login) {
        return (User) getHibernateTemplate().get(User.class, login);
    }

    /**
     * Save a user.
     * 
     * @see tudu.domain.dao.UserDAO#saveUser(tudu.domain.model.User)
     */
    public final void saveUser(final User user) {
        getHibernateTemplate().save(user);

        if (logger.isDebugEnabled()) {
            logger.debug("userId set to: " + user.getLogin());
        }
    }

    /**
     * Update a user.
     * 
     * @see tudu.domain.dao.UserDAO#updateUser(tudu.domain.model.User)
     */
    public final void updateUser(final User user) {
        getHibernateTemplate().update(user);
    }

}
