package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import tudu.domain.model.User;
import tudu.service.UserManager;
import tudu.web.bean.RecoverPasswordData;

/**
 * Recover a user's lost password.
 * 
 * @author Julien Dubois
 */
public class RecoverPasswordController extends SimpleFormController {

    private final Log log = LogFactory.getLog(RecoverPasswordController.class);

    private UserManager userManager = null;

    public final void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    /**
     * Send an email with the new password to the user.
     */
	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response,
			Object command, BindException errors) throws Exception {
    	
    	RecoverPasswordData data = (RecoverPasswordData)command;
        String login = data.getLogin();
        login = login.toLowerCase();

        User user = null;
        try {
            user = userManager.findUser(login);
        } catch (ObjectRetrievalFailureException orfe) {
			errors.rejectValue("login", "recover.password.no.user", new Object[] {login}, "");
			return showForm(request, response, errors);
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
			errors.reject("recover.password.no.email", new Object[] {login}, "");
			return showForm(request, response, errors);
        }
        try {
            userManager.sendPassword(user);
        } catch (RuntimeException e) {
			errors.reject("recover.password.smtp.error");
			return showForm(request, response, errors);
        }

        return new ModelAndView(getSuccessView(),"success","true");
    }
    
}
