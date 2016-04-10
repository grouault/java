package com.book.chapter4.french;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ReferenceTitreMusique {
	
	private final static String PREFIX = "/TitreMusique/";

	private TitreMusique titreMusique;
	
	@XmlElement
	private String url;
	
	public ReferenceTitreMusique() {
	}

	public ReferenceTitreMusique(final TitreMusique titreMusique) {
		this.titreMusique = titreMusique;
		if (titreMusique != null) {
			this.url = PREFIX + titreMusique.getIdentifiant();
		}
	}

	@XmlTransient
	public TitreMusique getTitreMusique() {
		if (this.titreMusique == null) {
			this.setTitreMusique();
		}
		return this.titreMusique;
	}

	public void setTitreMusique(final TitreMusique titreMusique) {
		this.titreMusique = titreMusique;
	}

	@XmlTransient
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
		this.setTitreMusique();
	}
	
	private void setTitreMusique() {
		if (this.url == null || !this.url.startsWith(PREFIX)) {
			return;
		}
		final String id = this.url.substring(PREFIX.length());
		try {
			final Integer identifiant = Integer.valueOf(id);
			this.titreMusique = Factory.getTitre(identifiant);
		} catch (NumberFormatException e) {
			final String loggerName = getClass().getName();
			final Logger logger = Logger.getLogger(loggerName);
			final Level level = Level.SEVERE;
			final String message = "url TitreMusique incorrecte : " + this.url;
			logger.log(level, message, e);
		}
	}
}
