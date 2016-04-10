package com.book.chapter4.french;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ExceptionDeStatut extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlTransient
	private Status statut;
	
	@XmlTransient
	private String contentType;
	
	@XmlElement
	private String url;
	
	public ExceptionDeStatut() {
	}
	
	public ExceptionDeStatut(final Status statut, final String contentType, final String url) {
		super();
		this.statut = statut;
		this.contentType = contentType;
		this.url = url;
	}

	public Status getStatut() {
		return this.statut;
	}
	
	public String getContentType() {
		return this.contentType;
	}

	public String getUrl() {
		return this.url;
	}
}