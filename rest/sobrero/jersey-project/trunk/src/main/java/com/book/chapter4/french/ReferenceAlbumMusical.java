package com.book.chapter4.french;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ReferenceAlbumMusical {
	
	private final static String PREFIX = "/AlbumMusical/";

	private AlbumMusical albumMusical;
	
	@XmlElement
	private String url;
	
	public ReferenceAlbumMusical() {
	}

	public ReferenceAlbumMusical(final AlbumMusical albumMusical) {
		this.albumMusical = albumMusical;
		if (albumMusical != null) {
			this.url = PREFIX + albumMusical.getIdentifiant();
		}
	}

	@XmlTransient
	public AlbumMusical getAlbumMusical() {
		if (this.albumMusical == null) {
			this.setAlbumMusical();
		}
		return this.albumMusical;
	}

	public void setAlbumMusical(final AlbumMusical albumMusical) {
		this.albumMusical = albumMusical;
	}

	@XmlTransient
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
		this.setAlbumMusical();
	}
	
	private void setAlbumMusical() {
		if (this.url == null || !this.url.startsWith(PREFIX)) {
			return;
		}
		final String id = this.url.substring(PREFIX.length());
		try {
			final Integer identifiant = Integer.valueOf(id);
			this.albumMusical = Factory.getAlbum(identifiant);
		} catch (NumberFormatException e) {
			final String loggerName = getClass().getName();
			final Logger logger = Logger.getLogger(loggerName);
			final Level level = Level.SEVERE;
			final String message = "url TitreMusique incorrecte : " + this.url;
			logger.log(level, message, e);
		}
	}
}
