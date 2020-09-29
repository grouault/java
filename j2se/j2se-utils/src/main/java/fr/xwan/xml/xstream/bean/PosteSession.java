package fr.xwan.xml.xstream.bean;

import java.util.Calendar;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("SESSION")
@XStreamConverter(value=PosteSessionConverter.class)
public class PosteSession {
	
 	private Calendar dateDebut;

	private Calendar dateFin;

	private Calendar heureDebut;

	private Calendar heureFin;
	
	private int timeOut;

	private List<String> jours;

	public Calendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Calendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	public Calendar getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Calendar heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Calendar getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Calendar heureFin) {
		this.heureFin = heureFin;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public List<String> getJours() {
		return jours;
	}

	public void setJours(List<String> jours) {
		this.jours = jours;
	}


		
}
