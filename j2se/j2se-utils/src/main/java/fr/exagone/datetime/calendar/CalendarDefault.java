package fr.exagone.datetime.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDefault {

	public static void main(String[] args) {
	  try {  
		 // String str_date="11-June-2011";
		 String str_date = "3/01/2021";
	     DateFormat formatter ; 
	     Date date ; 
	     // formatter = new SimpleDateFormat("dd-MMM-yyyy");
	     formatter = new SimpleDateFormat("dd/MM/yyyy");
	     date = (Date)formatter.parse(str_date); 
	     Calendar cal=Calendar.getInstance();
	     cal.setMinimalDaysInFirstWeek(4);
	     System.out.println(cal.getMinimalDaysInFirstWeek());
	     cal.setTime(date);
	     System.out.println("time millis = " + cal.getTimeInMillis());
	     System.out.println("time date =  " + cal.getTime());
	     System.out.println("Today is " + cal );
	     System.out.println("Numero de semaine : " + cal.get(Calendar.WEEK_OF_YEAR));
	  } catch (ParseException e){
		  System.out.println("Exception :" + e);  
	  }  
	 
	 }
}


