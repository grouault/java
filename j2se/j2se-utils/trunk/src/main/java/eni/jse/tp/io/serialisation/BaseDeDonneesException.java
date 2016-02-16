package eni.jse.tp.io.serialisation;


public class BaseDeDonneesException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseDeDonneesException(Exception e, String fichier) {
		super("Op�ration impossible sur " + fichier, e);
	}
}
