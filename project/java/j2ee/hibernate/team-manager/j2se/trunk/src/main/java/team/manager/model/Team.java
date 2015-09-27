package team.manager.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Team {

	private Integer id;
	private String name;
	
	private Integer nbWon;
	private Integer nbLost;
	private Integer nbPlayed;
	
	private Coach coach;
	private Set<Player> players;
	private Map<Date, Game> homeGames;
	private Map<Date, Game> awayGames;
	
	// propriété non persistente.
	private transient Map<Date, Game> games;
	private transient Set<Game> wonGames;
	private transient Set<Game> lostGames;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNbWon() {
		return nbWon;
	}
	public void setNbWon(Integer nbWon) {
		this.nbWon = nbWon;
	}
	public Integer getNbLost() {
		return nbLost;
	}
	public void setNbLost(Integer nbLost) {
		this.nbLost = nbLost;
	}
	public Integer getNbPlayed() {
		return nbPlayed;
	}
	public void setNbPlayed(Integer nbPlayed) {
		this.nbPlayed = nbPlayed;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Map<Date, Game> getHomeGames() {
		return homeGames;
	}
	public void setHomeGames(Map<Date, Game> homeGames) {
		this.homeGames = homeGames;
	}
	public Map<Date, Game> getAwayGames() {
		return awayGames;
	}
	public void setAwayGames(Map<Date, Game> awayGames) {
		this.awayGames = awayGames;
	}
	public Map<Date, Game> getGames() {
		return games;
	}
	public void setGames(Map<Date, Game> games) {
		this.games = games;
	}
	public void setWonGames(Set<Game> wonGames) {
		this.wonGames = wonGames;
	}
	public Set<Game> getLostGames() {
		return lostGames;
	}
	public void setLostGames(Set<Game> lostGames) {
		this.lostGames = lostGames;
	}
	
	/**
	 * deux equipes sont egales si
	 * 	elles ont le meme id.
	 */
	@Override
	public boolean equals(Object obj) {
		
		// test si les references sont egales.
		if (this == obj) {
			return true;
		}
		
		// verification que l'objet de comparaison est une équipe.
		if (! (obj instanceof Team)) {
			return false;
		} 
			
		Team teamCompare = (Team) obj;
		// deux equipes son egales, si elles ont le memme id.
		return this.getId() == teamCompare.getId();
	
	}
	
	//
	// methode metier.
	//
	
	/**
	 * retourne le nombre de match nul.
	 * @return
	 */
	public int getNbNullGames () {
		return this.getNbPlayed() - this.getNbWon() - this.getNbLost();
	}
	
	
	/**
	 * retourne le nombre de match gagne.
	 * @return
	 */
	public Set<Game> getWonGames(){
		// TODO : A implementer.
		return this.wonGames;
	}
	
	
	
}
