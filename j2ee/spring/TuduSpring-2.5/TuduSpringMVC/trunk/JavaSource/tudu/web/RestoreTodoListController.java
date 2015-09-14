package tudu.web;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;
import tudu.web.bean.RestoreData;

/**
 * Restore a Todo List.
 * 
 * @author Julien Dubois
 */
public class RestoreTodoListController extends SimpleFormController {

    private final Log log = LogFactory.getLog(RestoreTodoListController.class);

    private TodoListsManager todoListsManager = null;

    public final void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }
    
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws ServletException {
    	binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

    /**
     * Display the "restore info" page.
     */
    @Override
	protected Object formBackingObject(HttpServletRequest request)
    										throws ServletException {
        RestoreData restoreData = new RestoreData();
        String listId = RequestUtils.getRequiredStringParameter(request,"listId");
        restoreData.setListId(listId);
        
        if (restoreData.getRestoreChoice() == null) {
        	restoreData.setRestoreChoice("create");
        }

        return restoreData;
    }

    @Override
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        RestoreData restoreData=(RestoreData)command;
    	TodoList todoList = todoListsManager.findTodoList(restoreData.getListId());

    	Map<String,Object> model=new HashMap<String,Object>();
    	model.put("todoList", todoList);
    	return model;
	}

	/**
     * Restore a Todo List.
     */
    protected ModelAndView onSubmit(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors) throws Exception { 

        log.debug("Execute restore action");
        RestoreData restoreData=(RestoreData)command;
        try {
            todoListsManager.restoreTodoList(restoreData.getRestoreChoice(), 
                    restoreData.getListId(), new ByteArrayInputStream(restoreData.getBackupFile()));
            
        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException : " + e.getMessage());
            errors.reject("restore.file.error");
            return showForm(request,response,errors);
        } catch (IOException e) {
            log.info("IOException : " + e.getMessage());
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            errors.reject("restore.file.error");
            return showForm(request,response,errors);
        } catch (JDOMException e) {
            log.info("JDOMException : " + e.getMessage());
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            errors.reject("restore.file.error");
            return showForm(request,response,errors);
        } catch (Exception e) {
            log.info("Exception : " + e.getMessage());
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            errors.reject("restore.file.error");
            return showForm(request,response,errors);
        }

        return new ModelAndView(getSuccessView());
    }

    /**
     * Restore a Todo List.
     */
    /*public ActionForward restore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        
        log.debug("Execute restore action");
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        RestoreTodoListForm restoreTodoListForm = (RestoreTodoListForm) form;
        try {
            todoListsManager.restoreTodoList(restoreTodoListForm.getRestoreChoice(), 
                    restoreTodoListForm.getListId(), restoreTodoListForm.getBackupFile().getInputStream());
            
        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        } catch (IOException e) {
            log.info("IOException : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (JDOMException e) {
            log.info("JDOMException : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.info("Exception : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        return mapping.findForward("showTodosAction");
    }*/

    /**
     * @see tudu.web.TuduDispatchAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    /*@Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Execute cancel action");
        return mapping.findForward("showTodosAction");
    }*/
    
}
