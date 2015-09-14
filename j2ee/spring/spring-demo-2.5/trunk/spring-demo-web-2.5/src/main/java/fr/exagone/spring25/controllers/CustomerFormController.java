package fr.exagone.spring25.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fr.exagone.spring25.beans.Customer;
import fr.exagone.spring25.utils.ConstantUtil;

public class CustomerFormController extends SimpleFormController {
	
	private Log _logger = LogFactory.getLog(CustomerFormController.class); 
	
	/*-
	 * Soumission du formulaire.
	 * -*/
	@Override
	protected boolean isFormSubmission(HttpServletRequest request) {
		return super.isFormSubmission(request);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		if (_logger.isTraceEnabled() ) {
			_logger.trace("formBackinObject");
		}
		Customer customerForm = new Customer();
		return customerForm;
	
	}
	
	/* pour postionner des editeurs. */ 
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(
				Date.class, new CustomDateEditor(
						new SimpleDateFormat(ConstantUtil.DATE_FORMAT), true, ConstantUtil.DATE_LENGTH) );
		super.initBinder(request, binder);
	}
		
	@SuppressWarnings("rawtypes")
	@Override
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors, Map controlModel)
			throws Exception {
		return super.showForm(request, response, errors, controlModel);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		return referenceData;
	}
	
	/*-- step-validation --*/
	
	/* avant la validation */
	@Override
	protected void onBind(HttpServletRequest request, Object command,
			BindException errors) throws Exception {
		super.onBind(request, command, errors);
	}

	/* après binding et validation */
	@Override
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		super.onBindAndValidate(request, command, errors);
	}
	
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		return super.processFormSubmission(request, response, command, errors);
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		Customer customer = (Customer)command;
		return new ModelAndView(getSuccessView(), "customer", customer);
		
	}
}
