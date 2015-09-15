package fr.citesciences.bonita.task;

import org.apache.log4j.Logger;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.session.APISession;

public class BonitaUserManager {
	private static Logger logger = Logger.getLogger(BonitaUserManager.class);
	
	
	
	
	
	public User  getUserInfo(final APISession session,final String login) throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, UserNotFoundException{
		
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		User user = identityAPI.getUserByUserName(login);
		
		
		return user;
		
	}

}
