package team.manager.test;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import team.manager.model.Game;
import team.manager.model.Team;
import team.manager.services.AdminServices;

public class GameTest {
	
	private static Logger log = Logger.getLogger(CoachTest.class);
	private Game game;
	
	@Test
	public void getGameById() throws Exception {
		log.debug("[GameTest - getGame()]");
		game = AdminServices.getInstance().getGameById(1);
	    Assert.assertNotNull(game);
	    Team awayTeam = game.getAwayTeam();
	    log.debug("awayTeam : nbLost =" + awayTeam.getNbLost());
	}
	
	@Test
	public void getGamesCurrentMonth() throws Exception {
		log.debug("[GameTest - getMonthGame]");
		List<Game> games = AdminServices.getInstance().getGamesCurrentMonth();
		Assert.assertNotNull(games);
	}
	

}
