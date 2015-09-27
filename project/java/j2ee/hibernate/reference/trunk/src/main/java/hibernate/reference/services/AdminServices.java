package hibernate.reference.services;

import hibernate.reference.model.Event;
import hibernate.reference.model.Person;
import hibernate.reference.exceptions.FatalException;
import hibernate.reference.utils.HibernateUtil;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AdminServices {

	private static Log log = LogFactory.getLog(AdminPerson.class);
	
	private AdminPerson adminPerson = null;
	
	private AdminEvent adminEvent = null;
	
	private static AdminServices objInstance = null;
	
	public static synchronized AdminServices getInstance(){
		if (objInstance==null) {
			objInstance = new AdminServices();
		}
		return objInstance;
	} 
	
	/**
	 * constructeur mis en private.
	 */
	private AdminServices(){
		adminPerson = new AdminPerson();
		adminEvent = new AdminEvent();
	}
	
	/**$
	 * Permet de retourner une personne à partir de son id.
	 * @param id
	 * @return
	 * @throws FatalException 
	 */
	public Person getPerson(Integer id) throws FatalException{
		Person person = null;
		try {
			HibernateUtil.beginTransaction();
			person = adminPerson.get(HibernateUtil.getSession(), id);
			HibernateUtil.commitTransaction();
		} catch (FatalException e) {
			HibernateUtil.rollbackTransaction();
		} finally{
			HibernateUtil.closeSession();
		}
		
		return person;
	}
	
	/**
	 * Liste des évènements d'une personne.
	 * @param id
	 * @return
	 * @throws FatalException
	 */
	public Set<Event> getEventsForPerson(Integer id) throws FatalException{
		Set<Event> events = null;
		Person person = null;
		try {
			HibernateUtil.beginTransaction();
			person = adminPerson.get(HibernateUtil.getSession(), id);
			events = person.getEvents();
			HibernateUtil.commitTransaction();
		} catch (FatalException e) {
			HibernateUtil.rollbackTransaction();
		} finally{
			HibernateUtil.closeSession();
		}
		return events;
	}
	
	/**
	 * insertion d'un personne
	 */
	public void insererPerson(Person person){
		if (person!=null) {
			try {
				HibernateUtil.beginTransaction();
				adminPerson.insererPerson(HibernateUtil.getSession(), person);
				HibernateUtil.commitTransaction();
			} catch (FatalException e) {
				try {
					HibernateUtil.rollbackTransaction();
				} catch (FatalException e1) {
					e1.printStackTrace();
				}
			}
			finally{
				try {
					HibernateUtil.closeSession();
				} catch (FatalException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * retourne la liste des personnes
	 * dont l'id de l'évènement est passé en paramètre.
	 * @param id
	 * @throws FatalException 
	 */
	public Set<Person> getPersonsForEvent(Integer id) throws FatalException{
		Set<Person> persons = null;
		try {
			HibernateUtil.beginTransaction();
			persons = adminEvent.getPersons(HibernateUtil.getSession(), id);
			HibernateUtil.commitTransaction();
		} catch (FatalException e) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
		return persons;
	}
	
	/**
	 * Permet d'ajouter un evènement à une personne.
	 * @param p
	 * @param e
	 * @throws FatalException 
	 */
	public void addEventToPerson(Integer idPerson, Integer idEvent) throws FatalException{
		Person person = null;
		Event event = null;
		try {
			HibernateUtil.beginTransaction();
			person = adminPerson.get(HibernateUtil.getSession(), idPerson);
			event = adminEvent.get(HibernateUtil.getSession(), idEvent);
			person.getEvents().add(event);
			HibernateUtil.commitTransaction();
		} catch (FatalException e1) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	
}