package team.manager.services;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import team.manager.model.Game;

public class AdminGame {

	private static Log log = LogFactory.getLog(AdminGame.class);
	
	/**
	 * recupère un match par son id.
	 * @param idGame
	 * @return
	 */
	public Game getGameById(final Integer idGame, Session session) {
		Game game = (Game) session.get(Game.class, idGame);
		return game;
	}
	
	/**
	 * récupère les matchs du mois.
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Game> getGamesCurrentMonth (Session session) {
		List<Game> monthGames = null;
		Calendar dateDuJour = Calendar.getInstance();
		Calendar dDebut = Calendar.getInstance();
		Calendar dFin = Calendar.getInstance();
		int firstDayOfMonth = dateDuJour.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastDayOfMonth = dateDuJour.getActualMaximum(Calendar.DAY_OF_MONTH);
		dDebut.set(Calendar.DAY_OF_MONTH, firstDayOfMonth);
		dFin.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
		// recuperation des games entre dDebut et dFin.
		Criteria crit = session.createCriteria(Game.class);
		crit.add(Restrictions.between("dateGame", dDebut.getTime(), dFin.getTime()));
		monthGames = crit.list();
		return monthGames;
	}
	
	
}
