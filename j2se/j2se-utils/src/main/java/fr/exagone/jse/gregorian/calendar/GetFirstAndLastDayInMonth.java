package fr.exagone.jse.gregorian.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetFirstAndLastDayInMonth {
	
	public static void main(String[] args) {
				
		 Calendar calendar = new GregorianCalendar();
		 //Date currentTime = new Date();
		//calendar.setTime(currentTime);
		 //Calendar calendar = new GregorianCalendar(2010, 10, 4);
		 Date dateCourante = calendar.getTime();
		 
		 
		 System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
		 System.out.println("FIRST_DAY_IN_MONTH: " + calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		 System.out.println("LAST_DAY_IN_MONTH: " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		 
		 Calendar calendarDeb = new GregorianCalendar(
				 calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMinimum(Calendar.DAY_OF_MONTH) );
		 Calendar calendarFin = new GregorianCalendar(
				 calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH) );

		 Date dateDeb = calendarDeb.getTime();
		 Date dateFin = calendarFin.getTime();
		 DateFormat formatTest = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat formatJour = DateFormat.getDateInstance(DateFormat.FULL);
		 System.out.println("DATE COURANTE: " + formatJour.format(dateCourante));
		 System.out.println("DATE DEBUT MOIS: " + formatJour.format(dateDeb));
		 System.out.println("DATE FIN MOIS: " + formatJour.format(dateFin));
		 
	}
	
}
