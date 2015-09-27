package team.manager.test;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import team.manager.model.Coach;
import team.manager.services.AdminServices;

public class CoachTest {

	private static Logger log = Logger.getLogger(CoachTest.class);
	private Coach coach;
	
	@Test
	public void getCoach() throws Exception {
		log.debug("[CoachTest - GetCoach]");
	    coach = AdminServices.getInstance().getCoach(4);
	    Assert.assertNotNull(coach);
	}
	
	/**
	 * permet de mettre a jour le coach d'une équipe
	 * 	à partir de l'entité coach.
	 * @throws Exception
	 */
	@Test
	public void updateCoach() throws Exception {
		log.debug("[CoachTeset] - UpdateCoachTeam");
		coach = AdminServices.getInstance().updateCoachTeam(5, 4);
		Assert.assertNotNull(coach);
	}
	
}
