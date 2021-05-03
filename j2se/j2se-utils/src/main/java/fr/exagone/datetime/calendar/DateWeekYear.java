package fr.exagone.datetime.calendar;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class DateWeekYear {

	public static void main(String[] args) {
		
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.DAY_OF_MONTH, 3);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2021);
        System.out.println(DateWeekYear.yearWeekYearFromDate(cal.getTime()));
        // assertEquals(, "2009/53");
		
	}
	
	//
	//https://w3blog.fr/2012/10/04/obtenir-le-numero-de-la-semaine-et-lannee-en-java/
	public static String yearWeekYearFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		//La première semaine de l'année est celle contenant au moins 4 jours
		cal.setMinimalDaysInFirstWeek(4);
		cal.setTime(date);
		//On récupère le numéro de la semaine
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int annee;
		//On récupère l'année du premier jour de cette semaine
		if (week >= 52) {
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		    annee = cal.get(Calendar.YEAR);
		} else if (week == 1) {
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		    annee = cal.get(Calendar.YEAR);
		} else {
		    annee = cal.get(Calendar.YEAR);
		}
		DecimalFormat df = new DecimalFormat("00");
		return annee + "/" + df.format(week);
	}

}
