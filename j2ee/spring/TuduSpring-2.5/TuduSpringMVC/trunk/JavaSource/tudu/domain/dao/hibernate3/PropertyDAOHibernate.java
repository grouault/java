package tudu.domain.dao.hibernate3;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tudu.domain.dao.PropertyDAO;
import tudu.domain.model.Property;

/**
 * Hibernate implementation of the tudu.domain.dao.PropertyDAO interface.
 * 
 * @author Julien Dubois
 */
public class PropertyDAOHibernate extends HibernateDaoSupport implements PropertyDAO {

    public Property getProperty(String key) {
        return (Property) getHibernateTemplate().get(Property.class, key);
    }    
    
    /**
     * @see tudu.domain.dao.PropertyDAO#updateProperty(tudu.domain.model.Property)
     */
    public void updateProperty(Property property) {
        getHibernateTemplate().update(property);
    }

}
