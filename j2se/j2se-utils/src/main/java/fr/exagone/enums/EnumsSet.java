package fr.exagone.enums;

import java.util.ArrayList;
import java.util.EnumSet;

public class EnumsSet {

	enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }
	
	public static void main(String[] args) {
		
		ArrayList<Day> days = getAll();
		System.out.println("days = " + days);
		
	}

	public void range() {
		
		// range sur enumeration.
	    for (Day d : EnumSet.range(Day.MONDAY, Day.FRIDAY))
	        System.out.println(d);
	    	
	    // enumeration constitué d'élément spécifique.		
	    for (Day d : EnumSet.of(Day.MONDAY, Day.FRIDAY))
	        System.out.println(d);	
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<Day> getAll() {
		ArrayList<Day> days = new ArrayList(EnumSet.allOf(Day.class));
		return days;
	}
	
}
