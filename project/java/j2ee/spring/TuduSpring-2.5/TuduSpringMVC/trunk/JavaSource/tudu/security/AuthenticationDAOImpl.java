package tudu.security;

import java.util.Calendar;
import java.util.Set;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.GrantedAuthorityImpl;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.AuthenticationDao;
import net.sf.acegisecurity.providers.dao.UsernameNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.UserManager;

/**
 * An implementation of Acegi Security's AuthenticationDao.
 * 
 * @see net.sf.acegisecurity.providers.dao.AuthenticationDao
 * @author Julien Dubois
 */
public class AuthenticationDAOImpl implements AuthenticationDao {
    
    private final Log log = LogFactory.getLog(AuthenticationDAOImpl.class);

    private UserManager userManager = null;
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Load a user for Acegi Security.
     */
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
        login = login.toLowerCase();
        if (log.isDebugEnabled()) {
            log.debug("Security verification for user '" + login + "'");
        }
        User user = userManager.findUser(login);
        
        user.setLastAccessDate(Calendar.getInstance().getTime());
        userManager.updateUser(user);
        
        Set<Role> roles = user.getRoles();
        GrantedAuthority[] arrayAuths = new GrantedAuthority[roles.size()];
        int index = 0;
        for (Role role : roles) {
            GrantedAuthorityImpl authority = new GrantedAuthorityImpl(role.getRole());
            arrayAuths[index++] = authority;
        }
        
        net.sf.acegisecurity.providers.dao.User acegiUser = 
            new net.sf.acegisecurity.providers.dao.User(
                    login, user.getPassword(), user.isEnabled(),
                    true, true, true, arrayAuths);
        
        return acegiUser;
    }
}
