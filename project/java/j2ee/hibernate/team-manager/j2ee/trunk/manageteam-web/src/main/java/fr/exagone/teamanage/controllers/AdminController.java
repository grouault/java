package fr.exagone.teamanage.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AdminController extends MultiActionController {

	/**
	 * méthode de test.
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView testAdmin(HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView mav = new ModelAndView("testAdmin1");
		return mav;
	}
	
}
