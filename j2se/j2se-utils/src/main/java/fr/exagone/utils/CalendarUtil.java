package fr.exagone.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class CalendarUtil {

	private static CalendarUtil _instance = null;
	private Calendar cal = Calendar.getInstance();
	
	private CalendarUtil(){	
	}
	
	public static CalendarUtil getInstance(){
		if(_instance == null) {
			_instance = new CalendarUtil();
		}
		return _instance;
	}
	
	public Date getDate(final String strDate, final String format){
		Date date = null;
		try {
			if (!StringUtils.isEmpty(strDate) && !StringUtils.isEmpty(format)) {
				DateFormat formatDate = new SimpleDateFormat(format);	
				date = formatDate.parse(strDate);
			}
		} catch (ParseException e) {			
			e.printStackTrace();
		}	
		return date;
	}
	
}
