package eni.jse.tp.io.serialisation;


public class BaseDeDonneesException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseDeDonneesException(Exception e, String fichier) {
		super("Opération impossible sur " + fichier, e);
	}
}
