package team.manager.test;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import team.manager.model.Player;
import team.manager.services.AdminServices;

public class PlayerTest {

	private static Logger log = Logger.getLogger(PlayerTest.class);
	private Player player = null;
	
	/**
	 * recuperaton d'une instance persistante.
	 * @throws Exception
	 */
	@Test
	public void getPlayer() throws Exception {
		log.debug("[PlayerTest - getPlayer]");
		player = AdminServices.getInstance().getPlayer(1);
		// a ce stade, school est dans le proxy.
		log.debug("School = " + player.getSchool().getName());
		Assert.assertNotNull(player);
	}
}
