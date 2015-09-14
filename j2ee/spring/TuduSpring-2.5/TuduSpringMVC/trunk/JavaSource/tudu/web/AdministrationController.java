package tudu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import tudu.service.ConfigurationManager;
import tudu.web.bean.AdministrationData;

/**
 * Application administration actions.
 * 
 * @author Julien Dubois
 */
public class AdministrationController extends SimpleFormController {

    private final Log log = LogFactory.getLog(AdministrationController.class);

    private ConfigurationManager configurationManager;
    
    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

	/**
     * Show the administration page action.
     */
    @Override
	protected Object formBackingObject(HttpServletRequest request)
												throws ServletException {

    	AdministrationData data=new AdministrationData();
        String smtpHost = configurationManager.getProperty("smtp.host").getValue();
        data.setSmtpHost(smtpHost);
        String smtpPort = configurationManager.getProperty("smtp.port").getValue();
        data.setSmtpPort(smtpPort);
        String smtpUser = configurationManager.getProperty("smtp.user").getValue();
        data.setSmtpUser(smtpUser);
        String smtpPassword = configurationManager.getProperty("smtp.password").getValue();
        data.setSmtpPassword(smtpPassword);
        String smtpFrom =configurationManager.getProperty("smtp.from").getValue();
        data.setSmtpFrom(smtpFrom);
        return data;
	}

    /**
     * Show the administration page action.
     */
    protected ModelAndView onSubmit(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors) throws Exception { 

    	log.debug("Execute update action");
        AdministrationData data = (AdministrationData) command;
        String smtpHost = (String) data.getSmtpHost();
        String smtpPort = (String) data.getSmtpPort();
        String smtpUser = (String) data.getSmtpUser();
        String smtpPassword = (String) data.getSmtpPassword();
        String smtpFrom = (String) data.getSmtpFrom();
        configurationManager.updateEmailProperties(smtpHost, smtpPort, smtpUser, smtpPassword,
                smtpFrom);
        
        return showForm(request,response,errors);
    }
}
