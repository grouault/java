package tudu.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.view.xslt.AbstractXsltView;

import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class RssFeedView extends AbstractXsltView implements InitializingBean {

    private final Log log = LogFactory.getLog(RssFeedView.class);

    /**
     * Default feed type.
     */
    private static final String FEED_TYPE = "rss_2.0";
    
    /**
     * Default mime type.
     */
    private static final String MIME_TYPE = "application/xml; charset=UTF-8";

    /**
     * Contruct the RSS document.
     */
	protected Source createXsltSource(
			Map model, String root, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        TodoList todoList = (TodoList) model.get("todoList");
        String link = (String) model.get("link");
        Collection<Todo> todos = new TreeSet<Todo>(todoList.getTodos());
        
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(FEED_TYPE);

        feed.setTitle(todoList.getName());
        System.out.println("link = "+link);
        feed.setLink(link);
        feed.setDescription("Tudu Lists | " + todoList.getName());
        
        List<SyndEntry> entries = new ArrayList<SyndEntry>();
        for (Todo todo : todos) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setLink(link + "?listId=" + todoList.getListId() + "#todoId" + todo.getTodoId());
            SyndContent description = new SyndContentImpl();
            description.setType("text/plain");
            if (todo.isCompleted()) {
                entry.setTitle("Completed : " + todo.getDescription());
                entry.setPublishedDate(todo.getCompletionDate());
                description.setValue("Completed : " + todo.getDescription());
            } else {
                entry.setTitle(todo.getDescription());
                entry.setPublishedDate(todo.getCreationDate());
                description.setValue(todo.getDescription());
            }
            entry.setDescription(description);
            entries.add(entry);
        }
        feed.setEntries(entries);
        
        //response.setContentType(MIME_TYPE);
        SyndFeedOutput output = new SyndFeedOutput();
        try {
            return new DOMSource(output.outputW3CDom(feed));
        } catch (FeedException fe) {
            String msg = "The RSS feed could not be generated.";
            log.error("Error while generating the RSS feed : " + fe.getMessage());
            throw new ServletException(msg,fe);
        }
	}

	public void afterPropertiesSet() throws Exception {
		setContentType(MIME_TYPE);
	}
}
