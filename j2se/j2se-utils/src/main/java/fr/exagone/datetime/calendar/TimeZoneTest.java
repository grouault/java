package fr.exagone.datetime.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {

	public static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssZz");
	public static DateFormat dfSimple = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) throws InterruptedException, ParseException {
		
		TimeZone timeZone1 = TimeZone.getTimeZone("America/Los_Angeles");
		TimeZone timeZone2 = TimeZone.getTimeZone("Europe/Paris");

		Calendar calendar = Calendar.getInstance();
		// printInfoByCal(calendar, timeZone1, 1580074350000L, df);
		// printInfoByCal(calendar, timeZone2, 1580074350000L, df);
		printInfoByCal(calendar, timeZone1, 1579996800000L, df);
		printInfoByCal(calendar, timeZone2, 1579996800000L, df);
		
		String strDate = "26/01/2020 22:38:30";
		printInfoByDateString(strDate, timeZone1, df);
		printInfoByDateString(strDate, timeZone2, df);	
		
		System.out.println("***************");
		getDateAtUTC();
		
	}
	
	public static void printInfoByCal(Calendar cal, TimeZone timeZone, Long timeMillis, DateFormat df) {
		cal.setTimeZone(timeZone);
		if (timeMillis != null && timeMillis > -1) {
			cal.setTimeInMillis(timeMillis);
		}
		System.out.println("-- INFO : printInfoByCal --");
		System.out.println("timeZone = " + timeZone.getDisplayName());
		System.out.println("time = " + cal.getTimeInMillis());
		// System.out.println("hour = " + cal.get(Calendar.HOUR_OF_DAY));
		df.setTimeZone(timeZone);
		System.out.println(df.format(cal.getTime()));
	}
	
	public static void printInfoByDateString(String strDate, TimeZone timeZone, DateFormat df) throws ParseException {
		dfSimple.setTimeZone(timeZone);
		Calendar cal = Calendar.getInstance();
		Date date = dfSimple.parse(strDate);
		cal.setTime(date);

		System.out.println("-- INFO : printInfoByDateString --");
		System.out.println("strDate = " + strDate);
		System.out.println("timeZone = " + timeZone.getDisplayName());
		System.out.println("timeMillis  = " + cal.getTimeInMillis());
		System.out.println("offSet = " + timeZone.getOffset(date.getTime()));
		System.out.println("somme = " + (cal.getTimeInMillis() + timeZone.getOffset(date.getTime())) );
	}
	
	
	public static void getDateAtUTC() {

		
		// 
		// urls
		//
		// Local Date Time
		// https://stackoverflow.com/questions/34626382/convert-localdatetime-to-localdatetime-in-utc
		// https://stackoverflow.com/questions/56361337/localdatetime-to-specific-timezone
		// https://www.codebyamir.com/blog/add-a-timezone-to-localdatetime-with-zoneddatetime-in-java-8
		// https://www.baeldung.com/java-zoneddatetime-offsetdatetime
		// https://www.baeldung.com/java-zone-offset
		//
		
		// Date
		// https://stackoverflow.com/questions/6543174/how-can-i-parse-utc-date-time-string-into-something-more-readable
		// https://stackoverflow.com/questions/36915822/how-to-get-time-zone-from-date#:~:text=A%20java.,a%20pure%20time%20in%20UTC.
		//
		// LocalDateTime, Date : ne stocke pas d'information sur la timezone.
		// Quel est la timezone original ?
		// C'est celle du System.
		//
		// Dans l'exemple ci-dessus, la date est parsée dans le timezone UTC et Europe/Paris.
		// Le timezone du parsing donne la référence
		// 3h33min33s parser en UTC donne 5h33min33s en Europe/Paris
		// 3h33min33s parser en Europe/Paris donne 3h33min33s en Europe/Paris
		//
		// Server FTP parse en Europe/Paris ==> Pas bon.
		// 
		
		String strDateInit = "20130417033333";
	    
		SimpleDateFormat dateFormatParis = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyyMMddHHmmss");
		
	    TimeZone timeZoneParis = TimeZone.getTimeZone("Europe/Paris");
	    TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC"); // UTC
	    
	    dateFormatParis.setTimeZone(timeZoneParis);
	    dateFormatUTC.setTimeZone(timeZoneUTC);
	    
	    try {

	        Date modificationTimeUTC = dateFormatUTC.parse(strDateInit);
	        System.out.println("File modification time UTC: " + modificationTimeUTC);
	        
	        Date modificationTime = dateFormatParis.parse(strDateInit);
	        System.out.println("File modification time PARIS: " + modificationTime);

	        
	        
	    } catch (ParseException ex) {
	        ex.printStackTrace();
	    }
	    
	}
	
}
