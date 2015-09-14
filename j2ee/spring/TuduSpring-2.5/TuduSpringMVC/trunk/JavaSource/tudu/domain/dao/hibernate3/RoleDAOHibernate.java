package tudu.domain.dao.hibernate3;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tudu.domain.dao.RoleDAO;
import tudu.domain.model.Role;

/**
 * Hibernate implementation of the tudu.domain.dao.RoleDAO interface.
 * 
 * @author Julien Dubois
 */
public class RoleDAOHibernate extends HibernateDaoSupport implements RoleDAO {

    /***
     * @see tudu.domain.dao.RoleDAO#getRole(java.lang.String)
     */
    public Role getRole(String roleName) {
        Role role = (Role) getHibernateTemplate().get(Role.class, roleName);
        if (role == null) {
            throw new ObjectRetrievalFailureException(Role.class, roleName);
        }
        return role;
    }

}
