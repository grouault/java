package tudu.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Specific Struts Exception handler for logging all exceptions.
 * 
 * @author Julien Dubois
 */
public class TuduExceptionHandler {//extends ExceptionHandler {

    private final Log log = LogFactory.getLog(TuduExceptionHandler.class);

    /*public final ActionForward execute(Exception ex, ExceptionConfig ae,
            ActionMapping mapping, ActionForm formInstance,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        if (ex instanceof ObjectRetrievalFailureException) {
            if (log.isDebugEnabled()) {
                log.debug("ObjectRetrievalFailureException : " + ex.getMessage());
            }
            return mapping.findForward("objectRetrievalFailureException");
        }
        if (ex instanceof PermissionDeniedException) {
            if (log.isDebugEnabled()) {
                log.debug("Permission denied : " + ex.getMessage());
            }
            return mapping.findForward("permissionDenied");
        }
        log.error("An error has occured: " + ex.getMessage());
        if (log.isDebugEnabled()) {
            ex.printStackTrace();
        }
        return super.execute(ex, ae, mapping, formInstance, request, response);
    }*/
}
