package hibernate.reference.main;


import hibernate.reference.model.Event;
import hibernate.reference.model.Person;
import hibernate.reference.exceptions.FatalException;
import hibernate.reference.services.AdminServices;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class Test {

    private static Log log = LogFactory.getLog(Test.class);

	public static void main(String [] args){
		
		//insérer une personne.
		//Test.insererPerson();
	
		//mes évènement.
		//Test.mesEvenements();
	
		//Personnes à l'anniversaire de Chloé.
		// Test.personsAnniversaireChloe();
	
		//Ajout de Noel à la liste de mes evènements.
		Test.addEventToPerson();
	}
	
	/**
	 * insertion personne
	 */
	public static void insererPerson(){
		Person person = new Person();
		person.setAge(34);
		person.setFirstname("Anne-Sophie");
		person.setLastname("ROUAULT");
		AdminServices.getInstance().insererPerson(person);
		log.debug("peronne insérée");
	}
	
	/**
	 * mes évènements
	 */
	public static void mesEvenements(){
		try {
			Set<Event> events = AdminServices.getInstance().getEventsForPerson(new Integer(1));
			System.out.println("Nombre de mes évènemnts: " + events.size());
			if (events!=null && !events.isEmpty()) {
				System.out.println("Liste de mes évènements:");
				for (Iterator<Event> iterator = events.iterator(); iterator.hasNext();) {
					Event event = (Event) iterator.next();
					System.out.println(event.getTitle());
				}
			}

		} catch (FatalException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Personne présente à l'anniversaire de Chloé.
	 */
	public static void personsAnniversaireChloe(){
		try {
			Set<Person> persons = null;
			persons = AdminServices.getInstance().getPersonsForEvent(new Integer(1));
			if (persons!=null && !persons.isEmpty()) {
				System.out.println("ANNIVERSAIRE CHLOE: ");
				System.out.println("Nombre de personnes présentes: " + persons.size());
				for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
					Person person = (Person) iterator.next();
					System.out.println(person.getLastname());
				}
			}
		} catch (FatalException e) {
			e.printStackTrace();
		}
	}
	
	public static void addEventToPerson(){
		Integer idEvent = Integer.valueOf(2); //Noel
		Integer idPerson = Integer.valueOf(1); //Gilou
		try {
			log.info("début - transaction - addEventToPerson");
			System.out.println("début");
			AdminServices.getInstance().addEventToPerson(idPerson, idEvent);
			System.out.println("fin");
			log.info("fin - transaction - addEventToPerson");
		} catch (FatalException e) {
			e.printStackTrace();
		}
	}
}
