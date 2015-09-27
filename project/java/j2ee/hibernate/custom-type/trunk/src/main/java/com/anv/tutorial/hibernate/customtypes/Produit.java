package com.anv.tutorial.hibernate.customtypes;

public class Produit {

	private Boolean off;
	private Boolean occ;
	private Boolean sof;
	
	public boolean isOff() {
		return off;
	}
	public void setOff(boolean off) {
		this.off = off;
	}
	public boolean isOcc() {
		return occ;
	}
	public void setOcc(boolean occ) {
		this.occ = occ;
	}
	public boolean isSof() {
		return sof;
	}
	public void setSof(boolean sof) {
		this.sof = sof;
	}
	
}
