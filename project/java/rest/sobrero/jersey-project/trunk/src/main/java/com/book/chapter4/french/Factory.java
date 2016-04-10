package com.book.chapter4.french;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Factory {
	
	private static Factory instance;
	
	private Map<Integer, AlbumMusical> albums;
	private Map<Integer, TitreMusique> titres;
	private int titresMaxId = 0;
	
	private Factory() {
		this.albums = new HashMap<Integer, AlbumMusical>();
		this.titres = new HashMap<Integer, TitreMusique>();
		
		for (int i=0; i <10; i++) {
			final AlbumMusical album = creerAlbum(i);
			this.albums.put(album.getIdentifiant(), album);
			for (TitreMusique titre : album.getTitres()) {
				final int identifiantTitre = titre.getIdentifiant();
				this.titres.put(identifiantTitre, titre);
				if (identifiantTitre > this.titresMaxId) {
					this.titresMaxId = identifiantTitre;
				}
			}
		}
	}
	
	private static Factory getInstance() {
		if (instance == null ){
			instance = new Factory();
		}
		return instance;
	}
	
	public static AlbumMusical getAlbum(final int identifiant) {
		return getInstance().albums.get(identifiant);
	}
	
	public static TitreMusique getTitre(final int identifiant) {
		return getInstance().titres.get(identifiant);
	}
	
	public static TitreMusique enregistrerTitre(final TitreMusique titreMusique) {
		if (titreMusique == null) {
			return null;
		}
		titreMusique.setIdentifiant(++getInstance().titresMaxId);
		getInstance().titres.put(titreMusique.getIdentifiant(), titreMusique);
		return titreMusique;
	}
	
	public static TitreMusique supprimerTitre(final TitreMusique titreMusique) {
		if (titreMusique == null) {
			return null;
		}
		getInstance().titres.remove(titreMusique.getIdentifiant());
		return titreMusique;
	}
	
	public static TitreMusique modifierTitre(final TitreMusique titreMusique) {
		if (titreMusique == null) {
			return null;
		}
		getInstance().titres.put(titreMusique.getIdentifiant(), titreMusique);
		return titreMusique;
	}

	private static AlbumMusical creerAlbum(final int identifiant) {
		final AlbumMusical monAlbumMusical = new AlbumMusical();
		monAlbumMusical.setNom("AlbumMetalSymphonique" + identifiant);
		monAlbumMusical.setDate(new Date());
		monAlbumMusical.setIdentifiant(identifiant);
		
		for (int i = 10 * identifiant; i < 10 * identifiant + 5; i++) {
			final TitreMusique monTitreMusique = creerTitre(i);
			monAlbumMusical.ajouteTitre(monTitreMusique);
		}
		
		return monAlbumMusical;
	}
	
	private static TitreMusique creerTitre(final int identifiant) {
		final float duree = (float) Math.round(Math.random() * 250) + 218F;
		final TitreMusique monTitreMusique = new TitreMusique("MetalSymphonique" + identifiant, duree);
		monTitreMusique.setStyle("MetalSymphonique");
		monTitreMusique.ajouteArtiste("Artiste" + identifiant);
		monTitreMusique.setIdentifiant(identifiant);
		
		return monTitreMusique;
	}
	
}
