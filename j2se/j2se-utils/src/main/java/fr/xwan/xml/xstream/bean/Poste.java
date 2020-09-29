package fr.xwan.xml.xstream.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("POSTE")
@XStreamConverter(value=PosteConverter.class)
public class Poste {

	private String nom;
	
	private String etat;

	private List<PosteSession> sessions;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public void setSessions(List<PosteSession> sessions) {
		this.sessions = sessions;
	}

	public List<PosteSession> getSessions() {
		return sessions;
	}
	
}
