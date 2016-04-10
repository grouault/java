package com.book.chapter4.french;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class TitreMusique {

	private int identifiant;
	
	private String titre;
	
	private float duree;
	
	private List<String> artistes;
	
	private String style;
	
	@XmlElement
	@XmlJavaTypeAdapter(AdaptateurAlbumMusical.class)
	private AlbumMusical album;
	
	public TitreMusique() {
		this.artistes = new ArrayList<String>();
	}
	
	public TitreMusique(final String titre, final float duree) {
		this();
		this.titre = titre;
		this.duree = duree;
	}
	
	@XmlTransient
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

	public String getTitre() {
		return this.titre;
	}
	
	public void setTitre(final String titre) {
		this.titre = titre;
	}
	
	public float getDuree() {
		return this.duree;
	}
	
	public void setDuree(final float duree) {
		this.duree = duree;
	}
	
	public List<String> getArtistes() {
		return this.artistes;
	}
	
	public void setArtistes(final List<String> artistes) {
		this.artistes = artistes;
		if (this.artistes == null) {
			this.artistes = new ArrayList<String>();
		}
	}
	
	public void ajouteArtiste(final String artiste) {
		if (artiste == null) {
			return;
		}
		this.artistes.add(artiste);
	}
	
	public String getStyle() {
		return this.style;
	}
	
	public void setStyle(final String style) {
		this.style = style;
	}
	
	@XmlTransient
	public AlbumMusical getAlbum() {
		return this.album;
	}
	
	public void setAlbum(final AlbumMusical album) {
		if (album == null) {
			return;
		}
		this._setAlbum(album);
		this.album._ajouteTitre(this);
	}
	
	public void _setAlbum(final AlbumMusical album) {
		if (album == null) {
			return;
		}
		this.album = album;
	}
	
	public String toString() {
		final StringBuilder response = new StringBuilder();
		response.append(getClass().getCanonicalName());
		
		response.append(" - Identifiant : ");
		response.append(this.identifiant);
		
		response.append(" - Titre : ");
		response.append(this.titre);
		
		response.append(" - Durée : ");
		response.append(this.duree);
		
		response.append(" - Style : ");
		response.append(this.style);
		
		response.append(" - Album : ");
		response.append(this.album);
		
		response.append(" - Artistes : ");
		final StringBuilder artists = new StringBuilder();
		for (String artist : this.artistes) {
			if (artists.length() > 0) {
				artists.append(", ");
			}
			artists.append(artist);
		}
		response.append(artists);
		
		return response.toString();
	}
}