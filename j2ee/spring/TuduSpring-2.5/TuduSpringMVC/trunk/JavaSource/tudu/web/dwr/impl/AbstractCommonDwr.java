package tudu.web.dwr.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.ltd.getahead.dwr.ExecutionContext;

/**
 * Common methods used by DWR beans. 
 * 
 * @author Julien Dubois
 */
public abstract class AbstractCommonDwr {

    private final Log log = LogFactory.getLog(AbstractCommonDwr.class);
    
    /**
     * Render a permission exception JSP fragment.
     */
    public String renderPermissionException() {
        log.debug("Render Permission Exception");
        try {
            return ExecutionContext.get().forwardToString(
                    "/WEB-INF/jsp/permission_denied.jsp");
        } catch (ServletException e) {
            log.error("ServletException : " + e);
            return "";
        } catch (IOException ioe) {
            log.error("IOException : " + ioe);
            return "";
        }
    }
}
