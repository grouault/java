package tudu.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.output.DOMOutputter;
import org.springframework.web.servlet.view.xslt.AbstractXsltView;

import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;

/**
 * Backup a Todo List.
 * 
 * @author Julien Dubois
 */
public class BackupTodoListView extends AbstractXsltView {

    private final Log log = LogFactory.getLog(BackupTodoListView.class);

    private TodoListsManager todoListsManager = null;

    public final void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    /**
     * Contruct the XML document.
     */
	protected Source createXsltSource(
			Map model, String root, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setContentType(response);
		
    	TodoList todoList = (TodoList)model.get("todoList");
        Document doc = todoListsManager.backupTodoList(todoList);
        return new DOMSource(new DOMOutputter().output( doc ));
    }

	private void setContentType(HttpServletResponse response) {
		response.setContentType("text/xml; charset=ISO-8859-1");
	}
}
