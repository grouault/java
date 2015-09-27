package fr.customtype.utils;

public class BooleanUtil {

	/**
	 * 
	 * @param p_bool
	 * @return
	 */
    public static String toStringValue(Boolean p_bool){
    	
        String strEtat;
        
        if (p_bool != null) {
        	strEtat = p_bool.booleanValue() ? "0" : "1";
        } else {
            strEtat = "1";
        }
        
        return strEtat;
    }

    /**
     * 
     * @param p_str
     * @return
     */
    public static Boolean toBooleanValue(String p_str){
    	
    	Boolean bVal = Boolean.FALSE;
        if (p_str != null && p_str.equalsIgnoreCase("0")) {
        	bVal = Boolean.TRUE;
        }
        
        return bVal;
    }
	
}
