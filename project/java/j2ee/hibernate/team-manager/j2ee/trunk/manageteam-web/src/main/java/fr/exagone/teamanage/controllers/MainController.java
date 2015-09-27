package fr.exagone.teamanage.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import fr.exagone.teamanage.bean.Player;
import fr.exagone.teamanage.services.PlayerManager;

/**
 * 
 * @author Gildas
 *
 * @spring.bean id="mainController"
 * @spring.property name="playerManager" ref="playerManager"
 */
public class MainController extends MultiActionController {

	private static final Log LOGGER = LogFactory.getLog(MainController.class); 
	
	private PlayerManager playerManager;
	
	public ModelAndView accueil(HttpServletRequest request,
			HttpServletResponse response){
		
		LOGGER.debug("accueil");
		ModelAndView mav = new ModelAndView("accueil");
		
		//recherche de la liste des players.
		List<Player> players = playerManager.getPlayers();
		mav.addObject("players", players);
		
		return mav;
	}
	
	/**
	 * redirection vers la page indiquant un accès refusé à la page 
	 * précédemment sollicité.
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView denied(HttpServletRequest request, 
			HttpServletResponse response){
		ModelAndView mav = new ModelAndView("denied");
		return mav;
		
	}
	
	public void setPlayerManager(final PlayerManager playerManager) {
		this.playerManager = playerManager;
	}	
	
}
