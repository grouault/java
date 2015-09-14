package tudu.web;

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
 * Backup a Todo List.
 * 
 * @author Julien Dubois
 */
public class BackupTodoListController implements Controller {

    private final Log log = LogFactory.getLog(BackupTodoListController.class);

    private TodoListsManager todoListsManager = null;

    public final void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    /**
     * Backup a Todo List.
     */
    public final ModelAndView handleRequest(HttpServletRequest request,
    				HttpServletResponse response) throws Exception {
        log.debug("Execute action");
        String listId = RequestUtils.getStringParameter(request,"listId");
        TodoList todoList = todoListsManager.findTodoList(listId);
        return new ModelAndView("backup","todoList",todoList);
    }
}
