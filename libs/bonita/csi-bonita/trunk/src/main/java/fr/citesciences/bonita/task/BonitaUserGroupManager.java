package fr.citesciences.bonita.task;

import org.apache.log4j.Logger;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.AlreadyExistsException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.CreationException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.Group;
import org.bonitasoft.engine.identity.GroupNotFoundException;
import org.bonitasoft.engine.identity.Role;
import org.bonitasoft.engine.identity.RoleNotFoundException;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserMembership;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.session.APISession;

import fr.citesciences.bonita.exceptions.TechnicalException;

public class BonitaUserGroupManager {
	

	
	private static Logger logger = Logger.getLogger(BonitaUserGroupManager.class);
	
	
	
	public boolean affectRoleToUser(final APISession session, final String login,  final String groupPath,final String roleName) throws AlreadyExistsException, CreationException, GroupNotFoundException, RoleNotFoundException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, UserNotFoundException, TechnicalException{
	
	
	final IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
	
	
	User user = identityAPI.getUserByUserName(login);
	if (user==null){
		throw new TechnicalException(" user not find ");
	}
	
	Role role = identityAPI.getRoleByName(roleName);
	if (role==null){
		throw new TechnicalException(" role not find ");
	}
	
	
	Group group = identityAPI.getGroupByPath(groupPath);
	if (group==null){
		throw new TechnicalException(" group not find ");
	}
	
	UserMembership userMembership = identityAPI.addUserMembership(user.getId(), group.getId(), role.getId());
	
	return (userMembership!=null)?true:false;
	}
}
