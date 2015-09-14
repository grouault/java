package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import tudu.domain.RolesEnum;

/**
 * The Welcome action.
 * 
 * @author Julien Dubois
 */
public class WelcomeController implements Controller {

    private final Log log = LogFactory.getLog(WelcomeController.class);
    
    private String showTodosView;
    
    /**
     * Welcome action.
     */
	public ModelAndView handleRequest(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
        log.debug("Execute action");
        if (request.isUserInRole(RolesEnum.ROLE_USER.toString())) {
            return new ModelAndView(getShowTodosView());
        }
        return new ModelAndView("login");
	}

	public String getShowTodosView() {
		return showTodosView;
	}

	public void setShowTodosView(String showTodosView) {
		this.showTodosView = showTodosView;
	}
}
