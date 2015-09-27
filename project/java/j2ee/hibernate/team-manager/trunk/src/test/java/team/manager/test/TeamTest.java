package team.manager.test;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import team.manager.model.Game;
import team.manager.model.Team;
import team.manager.services.AdminServices;

public class TeamTest {

	private static Logger log = Logger.getLogger(TeamTest.class);
	private Team team;
	
	@Test
	public void getTeam() throws Exception{
		log.debug("[TeamTest - getTeam()]");
		team = AdminServices.getInstance().getTeam(1);
		Assert.assertNotNull(team);
	}
	
	@Test
	public void addNewTeam() throws Exception {
		log.debug("[TeamTest - addNewTeam()]");
		Team newTeam = new Team();
		newTeam.setName("NEWTEAM");
		Assert.assertTrue(AdminServices.getInstance().addNewTeam(newTeam, 5));
	}
	
	@Test
	public void removeTeam() throws Exception {
		log.debug("[TeamTest - removeTeam()]");
		Assert.assertTrue(AdminServices.getInstance().removeTeam(5));
	}
	
	@Test
	public void removePlayerToTeam() throws Exception {
		log.debug("[TeamTest - removePlayer()]");
		Assert.assertTrue(AdminServices.getInstance().removePlayerToTeam(1, 3));
	}
	
	@Test
	public void addPlayerToTeam() throws Exception{
		log.debug("[TeamTest - addPlayer()]");
		Assert.assertTrue(AdminServices.getInstance().addPlayerToTeam(1, 3));
	}
	
	@Test
	public void getNameTeams() throws Exception {
		log.debug("[TeamTest - getTeams()]");
		Assert.assertNotNull(AdminServices.getInstance().getNameTeams());
	}
	
	@Test
	public void getHomeGame() throws Exception {
		log.debug("[TeamTest - getHomeGame()]");
		String strDate = "01/02/2012";
		Integer idTeam = 1;
		Game homeGame = AdminServices.getInstance().getHomeGame(idTeam, strDate);
		Assert.assertNotNull(homeGame);
	}
	
}
