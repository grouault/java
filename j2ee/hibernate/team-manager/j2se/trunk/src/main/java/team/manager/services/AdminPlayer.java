package team.manager.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import team.manager.model.Player;

public class AdminPlayer {

	private static Log log = LogFactory.getLog(AdminPlayer.class);

	/**
	 * recuperation d'une instance persistante de player.
	 * @param sessionHibernate
	 * @param id
	 * @return
	 */
	public Player getPlayer (Session sessionHibernate, final int id) throws HibernateException {
		Player player = (Player) sessionHibernate.get(Player.class, id);
		return player;
	}
}
