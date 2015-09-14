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
 * Show the Todo Lists belonging to the current user.
 * 
 * @author Julien Dubois
 */
public class ShowTodoListsReportController implements Controller {

    private final Log log = LogFactory.getLog(ShowTodoListsReportController.class);

    private TodoListsManager todoListsManager;
    
    /**
     * Show the Todo Lists main page.
     */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.debug("Execute show action");
        TodoList todoLists=todoListsManager.findTodoList(
        		RequestUtils.getRequiredStringParameter(request,"listId"));
        Map<String,Object> model=new HashMap<String,Object>();
        model.put("todolist",todoLists);
        return new ModelAndView("todo_lists_report",model);
    }

	public TodoListsManager getTodoListsManager() {
		return todoListsManager;
	}

	public void setTodoListsManager(TodoListsManager todoListsManager) {
		this.todoListsManager = todoListsManager;
	}
}
