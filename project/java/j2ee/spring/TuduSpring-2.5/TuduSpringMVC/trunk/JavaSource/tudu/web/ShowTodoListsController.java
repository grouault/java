package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Show the Todo Lists belonging to the current user.
 * 
 * @author Julien Dubois
 */
public class ShowTodoListsController implements Controller {

    private final Log log = LogFactory.getLog(ShowTodoListsController.class);

    /**
     * Show the Todo Lists main page.
     */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.debug("Execute show action");
        return new ModelAndView("todo_lists");
    }
}
