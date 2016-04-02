package fr.exagone.general.controllers;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
public class InfosUserController {

	@RenderMapping
	public ModelAndView defaultMapping(RenderRequest request, RenderResponse response) {
		ModelAndView mav = new ModelAndView("view");
		return mav;
	}
	
}
