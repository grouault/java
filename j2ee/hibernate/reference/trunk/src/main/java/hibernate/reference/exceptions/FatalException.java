package hibernate.reference.exceptions;

import hibernate.reference.utils.HibernateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FatalException extends Exception {
	
	private static Log log = LogFactory.getLog(HibernateUtil.class);
	
	public FatalException() {
		// TODO Auto-generated constructor stub
	}
	
	public FatalException(Exception ex){
		log.debug(ex.getMessage());
	}
	
	public FatalException(String mess){
		super(mess);
	}
	
}
