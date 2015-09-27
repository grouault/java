package hibernate.reference.services;

import hibernate.reference.model.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

public class AdminPerson {

	private static Log log = LogFactory.getLog(AdminPerson.class);
	
	public AdminPerson() {
	}
	
	
	/**
	 * permet de retourner une personnne à partir de son identifiant.
	 * @param sessionHibernate
	 * @param id
	 * @return
	 */
	public Person get(Session sessionHibernate, Integer id){
		Person person = null;
		person = (Person)sessionHibernate.get(Person.class, id);
		return person;
	}
	
	/**
	 * permet d'insérer une personne en base.
	 * @param sessionHibernate
	 * @param person
	 */
	public void insererPerson(Session sessionHibernate, Person person){
		if (person!=null) {
			sessionHibernate.saveOrUpdate(person);
			log.info("insertion de l'objet personne réalisé: " + person.getLastname());
		}
		else{
			log.error("L'objet person est null - insertino impossible!");
		}
	} 
	
}
