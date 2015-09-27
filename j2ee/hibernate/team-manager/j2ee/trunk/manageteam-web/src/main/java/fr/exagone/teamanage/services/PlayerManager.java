package fr.exagone.teamanage.services;

import java.util.List;

import fr.exagone.teamanage.bean.Player;

public interface PlayerManager {
	
	/**
	 * retourne la liste des joueurs.
	 * @return
	 */
	public List<Player> getPlayers();
	
}
