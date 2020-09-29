package fr.exagone.datetime.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateCompare {

	public static void main(String[] args) {
	
		try {
		
			// comparaison de date avec l objet Calendar.
			// CalendarCompare();
			
			// comparaison de deux dates : >0 <0 ==
			compareDate();
			

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void compareDate() throws ParseException {
		   
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate1 = "30/10/2010";
		String strDate2 = "31/11/2011"; 
		
		int result = compareDate(strDate1, strDate2, sdf);
		if(result > 0){
			System.out.println(strDate1 + " is after " + strDate2);
		}else if(result < 0){
			System.out.println(strDate1 + " is before Date2 " + strDate2);
		}else if(result == 0){
			System.out.println(strDate1 + " is equal " + strDate2);
		}else{
			System.out.println("How to get here?");
		}
	
	}
	
    /**
     * renvoit: 
	 * > 0 : si date1 > date2
	 * < 0 : si date1 > date2
	 * == 0 : si date1 == date2
     * @param strDate1
     * @param strDate2
     * @param df
     * @return
     * @throws ParseException
     */
    private static int compareDate(final String strDate1, final String strDate2, final DateFormat df) throws ParseException {
    	Date date1 = df.parse(strDate1);
    	Date date2 = df.parse(strDate2);
    	return compareDate(date1, date2);
    }	
    	
	/**
	 * renvoit: 
	 * > 0 : si date1 > date2
	 * < 0 : si date1 > date2
	 * == 0 : si date1 == date2
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static int compareDate(final Date date1, final Date date2) {
		return date1.compareTo(date2);
	}
	
	
	/**
	 * Permet de distinguer les traitements en fonction de la compaison des dates.
	 * @throws ParseException
	 */
	private static void CalendarCompare() throws ParseException {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String strDate1 = "31/01/2010";
		String strDate2 = "31/01/2010";
    	Date date1 = df.parse(strDate1);
    	Date date2 = df.parse(strDate2);

    	System.out.println(df.format(date1));
    	System.out.println(df.format(date2));

    	Calendar cal1 = Calendar.getInstance();
    	Calendar cal2 = Calendar.getInstance();
    	cal1.setTime(date1);
    	cal2.setTime(date2);

    	if(cal1.after(cal2)){
    		System.out.println(strDate1 + " is after " + strDate2);
    	}

    	if(cal1.before(cal2)){
    		System.out.println(strDate1 + " is before " + strDate2 );
    	}

    	if(cal1.equals(cal2)){
    		System.out.println(strDate1 + " is equal Date2 " + strDate2 );
    	}
		
	}
	
	
	
	
}
