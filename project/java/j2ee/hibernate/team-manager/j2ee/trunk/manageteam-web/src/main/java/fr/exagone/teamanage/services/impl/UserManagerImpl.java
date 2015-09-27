package fr.exagone.teamanage.services.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.exagone.teamanage.bean.User;
import fr.exagone.teamanage.services.UserManager;

/**
 * 
 * @author Gildas
 *
 * @spring.bean id="userManager"
 * @spring.property name="sessionFactory" ref="sessionFactory"
 */
public class UserManagerImpl extends HibernateDaoSupport implements UserManager {

	UserManager userManager;
	
	public User findByLogin(String userLogin) {
		User currentUser;
		Session session = getSession();
		try{
			Criteria crit = session.createCriteria(User.class);
			crit.add(Expression.eq("login", userLogin));
			currentUser = (User)crit.uniqueResult();
		}
		finally{
			if (session!=null) {
				session.close();
			}
		}
		return currentUser;
	}

}
