package fr.exagone.teamanage.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.exagone.teamanage.bean.Player;
import fr.exagone.teamanage.services.PlayerManager;




/**
 * 
 * @author Gildas
 *
 * @spring.bean id="playerManager"
 * @spring.property name="sessionFactory" ref="sessionFactory"
 */
public class PlayerManagerImpl extends HibernateDaoSupport implements
		PlayerManager {

	public static final Log logger = LogFactory.getLog(PlayerManagerImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayers() {
		List<Player> players = null;
		players = getHibernateTemplate().loadAll(Player.class);
		return players;
	}

}
