package fr.exagone.spring25.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import fr.exagone.spring25.beans.Customer;

public class CustomerFormController extends SimpleFormController {

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		Customer customerForm = new Customer();
		return customerForm;
	
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();
		return referenceData;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		Customer customer = (Customer)command;
		return new ModelAndView(getSuccessView(), "customer", customer);
		
	}
}
