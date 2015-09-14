package tudu.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;

/**
 * Generate the RSS feed.
 * 
 * @author Julien Dubois
 */
public class ShowRssFeedController implements Controller {

    private final Log log = LogFactory.getLog(ShowRssFeedController.class);

    private TodoListsManager todoListsManager = null;

    public final void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    /**
     */
	public ModelAndView handleRequest(HttpServletRequest request,
						HttpServletResponse response) throws Exception {

        log.debug("Execute action");
        String listId = RequestUtils.getRequiredStringParameter(request,"listId");

        TodoList todoList = todoListsManager.unsecuredFindTodoList(listId);
        
        if (todoList.isRssAllowed()) {
            if (log.isDebugEnabled()) {
                log.debug("Rendering RSS feed for Todo List ID '" +
                        todoList.getListId() +
                        "', named '" +
                        todoList.getName()
                        + "'");
            }
            
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("todoList", todoList);
            model.put("link", 
                    request.getScheme()
                    + "://"
                    + request.getServerName()
                    + ":"
                    + request.getServerPort()
                    + request.getContextPath()
                    + "/secure/showTodos.action");
            
            return new ModelAndView("rssFeed",model);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Rendering RSS feed for Todo List ID '" +
                        todoList.getListId() +
                        "' is not allowed");
            }
            return new ModelAndView("rssNotAllowed");
        }
    }
}
