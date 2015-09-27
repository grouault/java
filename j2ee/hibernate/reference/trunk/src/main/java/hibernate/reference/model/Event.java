package hibernate.reference.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Event {
	
    private Integer id;

    private String title;
    private Date date;
    private Set<Person> persons;

    public Event() {
    	persons = new HashSet<Person>();
    }

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	

}