package com.anv.tutorial.hibernate.customtypes;

public class Section {

	private Integer id;
	
	private Produit produit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}	
	

}
