package tudu.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * The Log out action.
 * 
 * @author Julien Dubois
 */
public class LogoutController implements Controller {

    private final Log log = LogFactory.getLog(LogoutController.class);

    /**
     * Log out action.
     */
    public final ModelAndView handleRequest(HttpServletRequest request,
    					HttpServletResponse response) throws Exception {
        log.debug("Execute action");
        request.getSession().invalidate();
        Cookie terminate = new Cookie(TokenBasedRememberMeServices.ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE_KEY, null);
        terminate.setMaxAge(0);
        response.addCookie(terminate);
        return new ModelAndView("logout");
    }
}
