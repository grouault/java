package team.manager.model;

import java.util.Date;

public class Game {

	private Integer id;
	private Integer homeTeamScore;
	private Integer awayTeamScore;
	private Date dateGame;
	private Player mostValuablePlayer;
	private Team homeTeam;
	private Team awayTeam;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(Integer homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public Integer getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(Integer awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
	public Date getDateGame() {
		return dateGame;
	}
	public void setDateGame(Date dateGame) {
		this.dateGame = dateGame;
	}

	public Player getMostValuablePlayer() {
		return mostValuablePlayer;
	}
	public void setMostValuablePlayer(Player mostValuablePlayer) {
		this.mostValuablePlayer = mostValuablePlayer;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	
}
