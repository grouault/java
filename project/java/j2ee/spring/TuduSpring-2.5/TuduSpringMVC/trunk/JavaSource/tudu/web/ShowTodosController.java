package tudu.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import tudu.domain.model.TodoList;
import tudu.service.UserManager;

/**
 * Show the Todos belonging to the current List.
 * 
 * @author Julien Dubois
 */
public class ShowTodosController implements Controller {

    private final Log log = LogFactory.getLog(ShowTodosController.class);
    
    private UserManager userManager = null;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Show the Todos main page.
     */
	public ModelAndView handleRequest(HttpServletRequest request,
							HttpServletResponse response) throws Exception {

        log.debug("Execute show action");
        Collection<TodoList> todoLists = 
            new TreeSet<TodoList>(userManager.getCurrentUser().getTodoLists());
        
        Map<String,Object> model=new HashMap<String,Object>();
        if (!todoLists.isEmpty()) {
            String listId = RequestUtils.getStringParameter(request,"listId");
            if (listId != null) {
            	model.put("defaultList", listId);
            } else {                
            	model.put("defaultList", todoLists.iterator().next().getListId());
            }
        }
        return new ModelAndView("todos",model);
	}
}
