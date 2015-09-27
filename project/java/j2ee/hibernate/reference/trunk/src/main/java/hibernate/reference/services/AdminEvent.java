package hibernate.reference.services;

import hibernate.reference.model.Event;
import hibernate.reference.model.Person;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

/**
 * 
 * @author Gildas
 *
 */
public class AdminEvent {

	private static Log log = LogFactory.getLog(AdminEvent.class);
	
	public AdminEvent() {
	}
	
	/**
	 * retourne un objet Event à partir de son id.
	 * @param sessionHibernate
	 * @param id
	 * @return
	 */
	public Event get(Session sessionHibernate, Integer id){
		Event event = null;
		event = (Event)sessionHibernate.get(Event.class, id);
		return event;
	}
	
	/**
	 * retourne la liste des personnes assoicés à
	 * l'évènement dont l'id est passé en paramètre.
	 * @param sessionHibernate
	 * @param id
	 * @return
	 */
	public Set<Person> getPersons(Session sessionHibernate, Integer id){
		Set<Person> persons = null;
		Set<Person> persons2 = new HashSet<Person>();
		Event event = (Event)sessionHibernate.get(Event.class, id);
		persons = event.getPersons();
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			persons.remove(person);
			persons2.add(person);
		}
		return persons2;
	}
	
}