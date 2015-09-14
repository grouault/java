package fr.exagone.spring25.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class WelcomeController extends MultiActionController {

	/**
	 * welcome.action
	 * @param request
	 * @param reponse
	 * @return
	 */
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse reponse){		
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}

	public ModelAndView test(HttpServletRequest request, HttpServletResponse reponse){		
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
	
}