package team.manager.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import team.manager.exceptions.FatalException;
import team.manager.model.Coach;
import team.manager.model.Game;
import team.manager.model.Player;
import team.manager.model.Team;
import team.manager.utils.HibernateUtil;

public class AdminServices {

	private static Log log = LogFactory.getLog(AdminServices.class);
	
	private static AdminServices objInstance = null;
	
	private AdminCoach adminCoach = null;
	private AdminTeam adminTeam = null;
	private AdminPlayer adminPlayer = null;
	private AdminGame adminGame = null;
	
	public static synchronized AdminServices getInstance(){
		if (objInstance==null) {
			objInstance = new AdminServices();
		}
		return objInstance;
	}
	
	/**
	 * constructeur mis en private.
	 */
	private AdminServices(){
		adminCoach = new AdminCoach();
		adminTeam = new AdminTeam();
		adminPlayer = new AdminPlayer();
		adminGame = new AdminGame();
	}
		
	/**
	 * permet de récupérer une instance de Team.
	 * @param id
	 * @return
	 * @throws FatalException
	 */
	public Team getTeam(final Integer id) throws FatalException {
		Team team = null;
		try {
			team = adminTeam.getTeam(HibernateUtil.getSession(), id);
			log.debug("Team-name" + team.getName());
		} finally {
		  HibernateUtil.closeSession();
		}
		return team;
	} 
	
	/**
	 * permet de rendre persistante une nouvelle team.
	 * @param newTeam
	 * @return
	 * @throws FatalException
	 */
	public boolean addNewTeam (Team newTeam, final int coachId) throws FatalException {
		boolean bCreate = false;
		try {
			HibernateUtil.beginTransaction();
			Session sessionHibernate = HibernateUtil.getSession();
			newTeam.setCoach(adminCoach.getCoach(sessionHibernate, coachId)); // mise à jour du coach.
			adminTeam.addTeam(sessionHibernate, newTeam);
			HibernateUtil.commitTransaction();
			bCreate = true;
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
			throw new FatalException("[addNewTeam] - " + e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return bCreate;
	}
	
	/**
	 * supprimer une equipe.
	 * 	- Consequence sur les joueurs attaches (?).
	 *  - Consequence sur le coach (?)
	 * @param team
	 * @return
	 */
	public boolean removeTeam (final int idTeam) throws FatalException {
		boolean bDelete = false;
		try {
			HibernateUtil.beginTransaction();
			Session sessionHibernate = HibernateUtil.getSession();
			adminTeam.removeTeam(sessionHibernate, adminTeam.getTeam(sessionHibernate, idTeam));
			HibernateUtil.commitTransaction();
			bDelete = true;
		}catch (FatalException e) {
			HibernateUtil.rollbackTransaction();
			throw new FatalException("[removeTeam] - " + e.getMessage());
		}
		return bDelete;
	}
	
	/**
	 * permet de récupérer un game pour une date donnée.
	 * @param idTeam
	 * @param strDate
	 * @return
	 * @throws FatalException
	 */
	public Game getHomeGame(final Integer idTeam, final String strDate) throws FatalException {	
		Game homeGame = null;
		try {
			homeGame = adminTeam.getHomeGame(HibernateUtil.getSession(), idTeam, strDate);
		} finally {
			HibernateUtil.closeSession();
		}
		return homeGame;
	}
	
	
	/**
	 * permet de récupérer la liste des équipes.
	 * @return
	 * @throws FatalException
	 */
	public List<Object[]> getNameTeams() throws FatalException {
		List<Object[]> teams = adminTeam.getNameTeams(HibernateUtil.getSession());
		return teams;
	}
	
	/**
	 * permet d'ajouter un player.
	 * @param idTeam
	 * @param idPlayer
	 * @return
	 * @throws FatalException
	 */
	public Boolean addPlayerToTeam (final Integer idTeam, final Integer idPlayer) throws FatalException  {
		Boolean bInsert = false;
		try {
			HibernateUtil.beginTransaction();
			bInsert = adminTeam.addPlayerToTeam(HibernateUtil.getSession(), idTeam, idPlayer);
			HibernateUtil.commitTransaction();
		} catch (FatalException e) {
			HibernateUtil.rollbackTransaction();
			log.error("[AdminServices - addPlayer] - error " + e.getMessage());
		} finally {
		  HibernateUtil.closeSession();
		}
		return bInsert;
	}
	
	/**
	 * permet de supprimer un player d'une equipe.
	 * @param idTeam
	 * @param idPlayer
	 * @return
	 * @throws FatalException
	 */
	public Boolean removePlayerToTeam (final Integer idTeam, final Integer idPlayer) throws FatalException  {
		Boolean bDelete = false;
		try {
			HibernateUtil.beginTransaction();
			bDelete = adminTeam.removePlayer(HibernateUtil.getSession(), idTeam, idPlayer);
			HibernateUtil.commitTransaction();
		} catch (FatalException e) {
			HibernateUtil.rollbackTransaction();
			log.error("[AdminServices - removePlayer] - error " + e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return bDelete;
	}
	
	/**
	 * Permet de retourner un coach partir de son id.
	 * @param id
	 * @return
	 * @throws FatalException 
	 */
	public Coach getCoach(final Integer id) throws FatalException{
		Coach coach = null;
		try {
			coach = adminCoach.getCoach(HibernateUtil.getSession(), id);
			log.info("[AdminServices] - getCoach : id = " + id);
		} catch (FatalException e) { 
			log.error("[AdminServices - GetCoach] - error - " + e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return coach;
	}	
	
	/**
	 * recuperation d'une instance persitante de player a partir de son id.
	 * @param id
	 * @return
	 */
	public Player getPlayer (final Integer id) throws FatalException {
		Player player = null;
		try {
			player = adminPlayer.getPlayer(HibernateUtil.getSession(), id);
		} catch (HibernateException e) {
			throw new FatalException("[GetPlayer] - " + e.getMessage());
		}
		return player;
	}
	
	/**
	 * mettre à jour le coach dans une equipe a partir
	 * de coach.
	 * @return
	 */
	public Coach updateCoachTeam(final Integer idCoach, final Integer idTeam) throws FatalException{
		Coach coach = null;
		Team team = null;
		try {
			HibernateUtil.beginTransaction();
			Session sessionHibernate = HibernateUtil.getSession();
			coach = adminCoach.getCoach(sessionHibernate, idCoach);
			team = adminTeam.getTeam(sessionHibernate, idTeam);
			coach.setTeam(team);
			sessionHibernate.persist(coach);
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction();
			throw new FatalException(e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return coach;
	}
	
	/**
	 * retourne un match par son identifiant.
	 * @param idGame
	 * @return Game
	 * @throws FatalException 
	 */
	public Game getGameById(final Integer idGame) throws FatalException {
		Game game = null;
		try {
			Session session = HibernateUtil.getSession();
			game = adminGame.getGameById(idGame, session); 
		    Hibernate.initialize(game.getAwayTeam());
		} finally {
			HibernateUtil.closeSession();
		}	
		return game;
	}
	
	/**
	 * retourne les games du mois en cours.
	 * @return List<Game>
	 * @throws FatalException 
	 */
	public List<Game> getGamesCurrentMonth() throws FatalException {
		List<Game> monthGames = null;
		try {
			Session session = HibernateUtil.getSession();
			monthGames = adminGame.getGamesCurrentMonth(session);
		} finally {
			HibernateUtil.closeSession();
		}
		return monthGames;
	}
	
}
