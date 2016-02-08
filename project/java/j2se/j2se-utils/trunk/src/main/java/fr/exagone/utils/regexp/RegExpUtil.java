package fr.exagone.utils.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {

	private static String SUBSTRING_BY_REGEXP_1 = "DAM00000004";
	private static String SUBSTRING_BY_REGEXP_2 = "00000004";
	
	private static String DAM_VALUE = "DAM@zero_value@@Id_dam@";
	
	public static void main(String[] args) {
//		System.out.println("test 1 = " + substringByRegExp(SUBSTRING_BY_REGEXP_1));
//		System.out.println("test 2 = " + substringByRegExp(SUBSTRING_BY_REGEXP_2));
//		System.out.println("TransformToDam = " + transformToDam());
//		testNumeric();
		testListSepByPointVirgule();
	}

	public static String substringByRegExp(String value){
	    
		String idDoc = "";
	    String damValue = "";
//	    String pattern1 = "DAM[0-9]*&";
	    String pattern1 = "DAM[0-9]*";
	    String pattern2 = "[1-9][0-9]*";
	    Pattern  p1 = Pattern.compile(pattern1);
	    Pattern  p2 = Pattern.compile(pattern2);
	    if (value.length() > 0) {
	        Matcher m1 = p1.matcher(value);
	        if (m1.find()){
	            damValue = value.substring(m1.start(), m1.end());
	        }
	        Matcher m2 = p2.matcher(damValue);
	        if (m2.find()){
	            idDoc = damValue.substring(m2.start(), m2.end());
	        }
	    }
	    return idDoc;
	}
	
	public static String transformToDam () {
		String newValue = null;
		int lengthDamValue = 10;
		String idDam = "130";
		String zeroValue = "0";
		int nbZero = lengthDamValue - (idDam.length() + 3);
		System.out.println("nbZero = " + nbZero);
		for (int i=0; i<nbZero; i++) {
			zeroValue += "0";
		}
		newValue = DAM_VALUE.replaceAll("@Id_dam@", idDam).replaceAll("@zero_value@", zeroValue);	
		return newValue;
	}
	
	public static void testNumeric () {
		String regex = "[0-9 ]+"; 
		String data = "1997 1998 p"; 
		System.out.println(data.matches(regex));
	}
	
	public static void testListSepByPointVirgule () {
		String regex = "[0-9;]+"; 
		String data = "1997;1998 1999"; 
		System.out.println(data.matches(regex));
	}
	
}


