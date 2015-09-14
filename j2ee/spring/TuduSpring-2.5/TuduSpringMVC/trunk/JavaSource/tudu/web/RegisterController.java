package tudu.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;
import tudu.web.bean.RegisterData;

/**
 * Register a new Tudu Lists user.
 * 
 * @author Julien Dubois
 */
public class RegisterController extends SimpleFormController {

    private final Log log = LogFactory.getLog(RegisterController.class);

    private UserManager userManager = null;

    public final void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Show the "register a new user" page.
     */
    /*@Override
    public final ActionForward display(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute showRegistrationPage action");
        return mapping.findForward("register");
    }*/
    /*public final ModelAndView d(HttpServletRequest request,
    				HttpServletResponse response) throws Exception {

        log.debug("Execute showRegistrationPage action");
        return new ModelAndView("register");
    }*/

    /**
     * Register a new user.
     */
    public final ModelAndView onSubmit(Object command) {

        log.debug("Execute register action");
        RegisterData data = (RegisterData) command;
        String login = data.getLogin();
        login = login.toLowerCase();
        String password = data.getPassword();
        String firstName = data.getFirstName();
        String lastName = data.getLastName();
        String email = data.getEmail();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        try {
            userManager.createUser(user);
        } catch (UserAlreadyExistsException e) {
            /*ActionMessage message = new ActionMessage(
                    "register.user.already.exists",  login);
            
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();*/
        	return new ModelAndView(getFormView());
        }
        return new ModelAndView(getSuccessView(),"login", login);
    }
    
    /**
     * Cancel the action.
     */
    /*@Override
    public final ActionForward cancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute cancel action");
        return mapping.findForward("cancel");
    }*/
}
