package com.inkwell.internet.slogan.portlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.inkwell.internet.slogan.NoSuchSloganException;
import com.inkwell.internet.slogan.model.Slogan;
import com.inkwell.internet.slogan.model.impl.SloganImpl;
import com.inkwell.internet.slogan.service.SloganLocalServiceUtil;
import com.inkwell.internet.slogan.util.ActionUtil;
import com.inkwell.internet.slogan.util.SloganValidator;
import com.inkwell.internet.slogan.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class SloganContest
 */
public class SloganContest extends MVCPortlet {
 
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		
        try {
            Slogan slogan = null;

            long resourcePrimKey = ParamUtil.getLong(renderRequest, "resourcePrimKey");

            if (resourcePrimKey > 0) {
                slogan = SloganLocalServiceUtil.getSlogan(resourcePrimKey);
            }
            else {
                slogan = new SloganImpl();
            }

            renderRequest.setAttribute(WebKeys.SLOGAN_ENTRY, slogan);
        }
        catch (Exception e) {
            if (e instanceof NoSuchSloganException) {
                SessionErrors.add(renderRequest, e.getClass().getName());
            }
            else {
                throw new PortletException(e);
            }
        }
                
		super.render(renderRequest, renderResponse);
		
	}

    /**
     * Called when a user is either adding or updating a statement. If the
     * primary key is greater than 0, an update is performed, because there's an
     * existing key. Otherwise, an add is performed.
     *
     * @param request
     * @param response
     * @throws SystemException
     * @throws PortalException
     */
    @SuppressWarnings("unchecked")
    public void updateSlogan(
        ActionRequest request, ActionResponse response)
        throws PortalException, SystemException {

        Slogan slogan = ActionUtil.sloganFromRequest(request);
        ArrayList<String> errors = new ArrayList();
        ServiceContext serviceContext = ServiceContextFactory.getInstance(Slogan.class.getName(), request);
        
        if (SloganValidator.validateSlogan(slogan, errors)) {
            if (slogan.getSloganId() > 0) {
                // Updating
                try {
                    Slogan fromDB =
                        SloganLocalServiceUtil.getSlogan(slogan.getSloganId());

                    if (fromDB != null &&
                        (slogan.getSloganId() == fromDB.getSloganId())) {
                        fromDB = SloganLocalServiceUtil.updateSlogan(slogan);
                        SessionMessages.add(request, "slogan-added");
                    }
                }
                catch (PortalException e) {
                    errors.add("failed-to-update");
                }
                catch (SystemException e) {
                    errors.add("failed-to-update");
                }
            }
            else {
                // Adding
                try {
                    SloganLocalServiceUtil.addSlogan(slogan, slogan.getUserId(), serviceContext);
                }
                catch (SystemException e) {
                    errors.add("failed-to-add");
                }
                catch (PortalException e) {
                    errors.add("failed-to-add");
                }
            }
            response.setRenderParameter("jspPage", "/html/view.jsp");
        }
        else {
            for (String error : errors) {
                SessionErrors.add(request, error);
            }
            request.setAttribute(WebKeys.SLOGAN_ENTRY, slogan);
            response.setRenderParameter("jspPage", "/html/edit_slogan.jsp");
        }
    }
	

	
}
