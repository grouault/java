package com.book.chapter4.french;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class AlbumMusical {

	@XmlTransient
	private int identifiant;
	
	@XmlElement
	private String nom;
	
	@XmlElement
	@XmlJavaTypeAdapter(AdaptateurTitreMusique.class)
	private List<TitreMusique> titres;
	
	@XmlElement
	@XmlJavaTypeAdapter(AdaptateurDate.class)
	private Date date;
	
	public AlbumMusical() {
		this.titres = new ArrayList<TitreMusique>();
	}
	
	public int getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	
	@XmlID
	@XmlElement(name="identifiant")
	public String getId() {
		return Integer.toString(this.identifiant);
	}

	public void setId(final String id) {
		this.identifiant = Integer.valueOf(id);
	}

	@XmlTransient
	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	@XmlTransient
	public List<TitreMusique> getTitres() {
		return this.titres;
	}

	public void setTitres(final List<TitreMusique> titres) {
		if (titres != null) {
			for (TitreMusique title : titres) {
				this.ajouteTitre(title);
			}
		}
	}

	public void ajouteTitre(final TitreMusique titreMusique) {
		if (titreMusique == null) {
			return;
		}
		this._ajouteTitre(titreMusique);
		titreMusique._setAlbum(this);
	}
	
	public void _ajouteTitre(final TitreMusique titreMusique) {
		if (titreMusique == null) {
			return;
		}
		this.titres.add(titreMusique);
	}
	
	@XmlTransient
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String toString() {
		final StringBuilder response = new StringBuilder();
		response.append(getClass().getCanonicalName());
		
		response.append(" - Identifiant : ");
		response.append(this.identifiant);
		
		response.append(" - Nom : ");
		response.append(this.nom);
		
		if (this.date != null) {
			final Locale locale = Locale.FRENCH;
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy", locale);
			response.append(" - Date de commercialisation : ");
			response.append(simpleDateFormat.format(this.date));
		}
		
		response.append(" - Titres : ");
		final StringBuilder titles = new StringBuilder();
		for (TitreMusique title : this.titres) {
			if (titles.length() > 0) {
				titles.append(", ");
			}
			titles.append(title.getTitre());
		}
		response.append(titles);
		
		return response.toString();
	}
}