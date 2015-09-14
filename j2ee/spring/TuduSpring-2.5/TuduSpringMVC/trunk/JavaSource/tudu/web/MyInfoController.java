package tudu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import tudu.domain.model.User;
import tudu.service.UserManager;
import tudu.web.bean.UserInfoData;

/**
 * Show the Todo Lists belonging to the current user.
 * 
 * @author Julien Dubois
 */
public class MyInfoController extends SimpleFormController {

    private final Log log = LogFactory.getLog(MyInfoController.class);

    private UserManager userManager = null;

    public final void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Display the "my user info" page.
     */
    @Override
	protected Object formBackingObject(HttpServletRequest request)
    										throws ServletException {

    	log.debug("Execute display action");
        String login = request.getRemoteUser();
        User user = userManager.findUser(login);
        UserInfoData data=new UserInfoData();
        data.setPassword(user.getPassword());
        data.setVerifyPassword(user.getPassword());
        data.setFirstName(user.getFirstName());
        data.setLastName(user.getLastName());
        data.setEmail(user.getEmail());
        return data;
    }

    /**
     * Update user information.
     */
    protected ModelAndView onSubmit(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors) throws Exception { 

    	log.debug("Execute update action");
        UserInfoData data = (UserInfoData) command;
        String password = data.getPassword();
        String firstName = data.getFirstName();
        String lastName = data.getLastName();
        String email = data.getEmail();
        String login = request.getRemoteUser();
        User user = userManager.findUser(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userManager.updateUser(user);
        return showForm(request,response,errors);
    }
}
