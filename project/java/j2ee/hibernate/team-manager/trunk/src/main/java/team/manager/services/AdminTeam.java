package team.manager.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import team.manager.exceptions.FatalException;
import team.manager.model.Coach;
import team.manager.model.Game;
import team.manager.model.Player;
import team.manager.model.Team;
import team.manager.utils.HibernateUtil;

public class AdminTeam {

	private static Log log = LogFactory.getLog(AdminCoach.class);
	
	/**
	 * permet de récupérer une instance de Team.
	 * @param sessionHibernate
	 * @param id
	 * @return
	 */
	public Team getTeam (Session sessionHibernate, Integer id) throws HibernateException{
		Team team = (Team) sessionHibernate.get(Team.class, id);
		return team;
	}
	
	/**
	 * permet d'ajouter une nouvelle equipe.
	 * @param sessionHibernate
	 * @return
	 */
	public void addTeam (Session sessionHibernate, Team teamToPersist) throws HibernateException {
		sessionHibernate.persist(teamToPersist);
	}
	
	/**
	 * permet de supprimer une instance persistante d'equipe.
	 * @param sessionHibernate
	 * @param teamToDelete
	 * @throws HibernateException
	 */
	public void removeTeam (Session sessionHibernate, Team teamToDelete) throws HibernateException {
		sessionHibernate.delete(teamToDelete);
	}
	
	/**
	 * permet d'ajouter un player.
	 * @param sessionHibernate
	 * @param idTeam
	 * @param idPlayer
	 * @return
	 */
	public Boolean addPlayerToTeam (Session sessionHibernate, Integer idTeam, Integer idPlayer) {
		Team team = (Team) sessionHibernate.get(Team.class, idTeam);
		Player player = (Player) sessionHibernate.get(Player.class, idPlayer);
		Boolean bInsert = team.getPlayers().add(player);
		return bInsert;
	}
	
	/**
	 * permet de supprimer le player.
	 * @param sessionHibernate
	 * @param idTeam
	 * @param idPlayer
	 * @return
	 */
	public Boolean removePlayer (Session sessionHibernate, Integer idTeam, Integer idPlayer) {
		Team team = (Team) sessionHibernate.get(Team.class, idTeam);
		Player player = (Player) sessionHibernate.get(Player.class, idPlayer);
		return team.getPlayers().remove(player);
	}
	
	/**
	 * Renvoie le nom des équipes et leur id.
	 * @param sessionHibernate
	 * @return
	 */
	public List<Object[]> getNameTeams (Session sessionHibernate) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("Select team.id, team.name from Team team");
		Query query = sessionHibernate.createQuery(queryString.toString());
		List<Object[]> result = (List<Object[]>) query.list();
		return result;
	}
	
	/**
	 * permet de récupérer un game pour une date donnée.
	 * @param sessionHibernate
	 * @param idTeam
	 * @param strDate
	 * @return
	 * @throws FatalException
	 */
	public Game getHomeGame(Session sessionHibernate, final Integer idTeam, final String strDate) throws FatalException {	
		Team team = null;
		Game homeGame = null;
		try {
			team = (Team) sessionHibernate.get(Team.class, idTeam);	
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
 			Date date = format.parse(strDate);
			homeGame = team.getHomeGames().get(date);
			log.debug("Home GAME : player = " + homeGame.getMostValuablePlayer().getName());
		} catch (ParseException e) {
			log.error("ParseException : " + e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return homeGame;
	} 
		
}
