package team.manager.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import team.manager.model.Coach;

public class AdminCoach {
	
	private static Log log = LogFactory.getLog(AdminCoach.class);
	
	public Coach getCoach(Session sessionHibernate, Integer id){
		Coach coach = null;
		coach = (Coach) sessionHibernate.get(Coach.class, id);
		return coach;
	}
	
}
